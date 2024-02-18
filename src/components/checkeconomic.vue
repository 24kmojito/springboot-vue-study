<template>
  <el-container class="home-container">
    <el-header class="firstheader">
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="dodgerblue"
      >
        <el-menu-item index="1" :style="{height:'59px'}" @click="">
          <i class="el-icon-menu"></i>待审核金融机构公司档案
        </el-menu-item>
        <el-menu-item index="2" :style="{height:'59px'}" @click="">
          <i class="el-icon-menu"></i>金融机构档案
        </el-menu-item>
        <el-menu-item index="3" :style="{height:'59px'}" @click="">
          <i class="el-icon-menu"></i>已拉黑金融机构档案
        </el-menu-item>
        <el-menu-item index="4" :style="{height:'59px'}" @click="">
          <i class="el-icon-menu"></i>贷款公司申诉信息
        </el-menu-item>
        <el-submenu index="5" :style="{float:'right',height:50,marginRight:'50px',border:0}">
          <template slot="title">
            <a>
              <div class="logo"/>
            </a>
          </template>
          <el-menu-item index="5-1" :style="{paddingLeft:'70px'}">个人中心</el-menu-item>
          <el-menu-item index="5-2" :style="{paddingLeft:'70px'}">退出登录</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-header>
    <el-container class="home-container">
      <el-main class="firstmain">
        <h1 style="height: 100px;margin-top: 75px;text-align: center">审核金融机构信息</h1>
        <h2>
          <el-form ref="form" :model="form" label-width="150px" class="Form1">
            <el-form-item label="金融机构名称:" >
              <el-input v-model="form.companyName" class="form1" readonly></el-input>
            </el-form-item>
            <el-form-item label="机构简介:" >
              <el-input type="textarea" :rows="2"  v-model="form.loanIntroduction" class="form2" readonly ></el-input>
            </el-form-item>


            <el-form-item label="电子邮箱:">
              <el-input type="textarea" :rows="2" v-model="form.loanBankReconciliation" class="form6" readonly></el-input>
            </el-form-item>

            <el-form-item label="资质认证证书:">
              <el-input type="textarea" :rows="2" v-model="form.loanComment" class="form2" readonly></el-input>
            </el-form-item>
            <el-form-item label="备注:">
              <el-input type="textarea" :rows="2" v-model="form.loanRemark" class="form2" readonly></el-input>
            </el-form-item>
          </el-form>
        </h2>
        <el-row class="btns">
          <el-button type="primary" @click="removeUserById"style="position: relative;left: -100px">审核通过</el-button>
          <el-button type="danger" @click="removeUserById2">审核失败</el-button>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>


import axios from "axios";

export default {
  data() {
    return {
      isCollapse: false,
      tableData: null,
      form:{
        companyName:'1',
        loanIntroduction:'2',
        loanPeriod:'3',
        loanAmmount:'',
        loanInterest:'',
        loanBankReconciliation:'',
        loanRecord:'',
        loanComment:'',
        loanRemark:''

      }
    }
  },
  methods:{
    submit() {
      this.$refs.form.validate((valid)=> {
        if(valid) {
          this.$axios.post('http://localhost:8081/editLoan',this.form).then(res => {
            console.log(res);
            if(res.data.code != 0) return this.$message.error("贷款档案信息填写错误！")
            this.$message.success("建立贷款档案成功！！")

          })

        }

      });

    },
    logout(){
      window.sessionStorage.clear()
      this.$router.push('/login1')
    },
    async removeUserById (id) {
      // 弹框询问用户是否删除数据
      const confirmResult = await this.$confirm('您确定通过该机构的审核吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch()

      const {data: res} = await this.$http.delete('' + id)
      if (res.meta.status !== 200) {
        return this.$message.error('审核失败！')
      }
      this.$message.success('审核成功！')
      await this.getUserList()
    },
    async removeUserById2 (id) {
      // 弹框询问用户是否删除数据
      const confirmResult = await this.$confirm('审核失败该机构所有信息将从系统中删除，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch()

      const {data: res} = await this.$http.delete('' + id)
      if (res.meta.status !== 200) {
        return this.$message.error('审核失败！')
      }
      this.$message.success('审核失败！')
      await this.getUserList()
    },
    async getUserList(){
      const {data: res} = await this.$http.get('http://localhost:8080/#/home1', {
        params: this.tableData

      })
      if (res.meta.status !== 200) {
        return this.$message('获取用户列表失败！')
      }this.tableData=res.data()
      this.total=res.data().total
      await this.getUserList()
    },
    handleClick(row) {
      console.log(row);
    },
    checkDangAn(){
      this.$router.push('/home2')
    },
    makeDangan(){
      this.$router.push('/makeDangan')
    },
    Dangan(){
      this.$router.push('/Dangan')
    },
    toggleCollapse(){
      this.isCollapse=!this.isCollapse
    },
    viewDangan(){
      this.$router.push('/viewDangan')

    },

    editDangan(){
      this.$router.push('/editDangan')
    }

  },
}
</script>

<style scoped>
.el-menu-demo{
  width:1550px;
  border: thin #545c64;
}

.theLogo{
  float: right;
  padding-right: 50px;
}
.logo{
  float: right;
  background: url("../assets/hh.jpg");
  border-radius: 50%;
  width: 40px;height: 40px;
  margin-top: 10px;
  background-size: 40px;
}
/***************************/
.btns{
  display: flex;
  position: relative;
  left: 600px;
  bottom: 20px;
}
.Form1{
  position: relative;
  left: 175px;
  top: -30px;

}
.form1{
  width: 60%;
  margin-left: 20px;

}
.form2{
  width: 60%;
  margin-left: 20px;
  height: 100%;

}
.form3{
  width: 10%;
  margin-left: 20px;
  height: 100%;    /*调整间距*/

}
.form4{
  width: 20%;
  margin-left: 20px;
  height: 100%;    /*调整间距*/

}
.form5{
  width: 10%;
  margin-left: 20px;
  height: 100%;    /*调整间距*/

}
.form6{
  width: 60%;
  margin-left: 20px;
  height: 100%;    /*调整间距*/

}
.mpage{
  margin: 0 auto;
  text-align: center;
}
.firstheader{
  background-color: white;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  padding-bottom: 0;
  padding-top: 0;
  height: 400px;
}
.header1{
  position: absolute;
}
.secondmain{
  background-color: white;
  height: 100vh;
  margin-top: 30px;
}
.el-aside{
  background-color: #E6E6E6;
}
.firstmain{
  background-color: #F4F4F4;
}
.home-container{
  height: 100vh;
}
</style>

<style>
.secondheader{
  background-color: white;
  height: 100px;
}

</style>
