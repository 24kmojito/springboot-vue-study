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
        <el-menu-item index="1"  :style="{height:'59px'}" @click="checkDangAn" onload="open(this.index)">
          <i class="el-icon-menu"   ></i>贷款项目待审核公司档案</el-menu-item>
        <el-menu-item index="2" @click="makeDangan" onload="open(this.index)" :style="{height:'59px'}"><i class="el-icon-menu"></i>建立贷款档案</el-menu-item>
        <el-menu-item index="3" @click="Dangan" onload="open(this.index)" :style="{height:'59px'}"><i class="el-icon-menu"></i>查看贷款公司档案</el-menu-item>
        <el-menu-item index="4" :style="{height:'59px'}"><i class="el-icon-menu"></i>金融机构中心</el-menu-item>
        <el-submenu index="6" :style="{float:'right',height:50,marginRight:'50px',border:0}">
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

        <h1 style="height: 100px;margin-top: 75px;text-align: center">填写贷款信息</h1>
        <h2>
          <el-form ref="form" :model="form" label-width="150px" class="Form1">
            <el-form-item label="项目名称:" >
              <el-input v-model="form.projectName" class="form1"></el-input>
            </el-form-item>
            <el-form-item label="项目简介:" >
              <el-input type="textarea" :rows="2"  v-model="form.projectIntroduction" class="form2"  ></el-input>
            </el-form-item>
            <el-form-item label="项目花费:">
              <el-input v-model="form.projectCost" class="form3"></el-input>
            </el-form-item>
            <el-form-item label="项目开始时间:">
              <el-input v-model="form.projectStartTime" class="form3"></el-input>
            </el-form-item>
            <el-form-item label="项目结束时间:">
              <el-input v-model="form.projectEndTime" class="form3"></el-input>
            </el-form-item>
            <el-form-item label="项目评论:">
              <el-input type="textarea" :rows="2" v-model="form.projectComment" class="form6"></el-input>
            </el-form-item>
            <el-form-item label="项目备注:">
              <el-input type="textarea" :rows="2" v-model="form.projectRemark" class="form2" ></el-input>
            </el-form-item>
          </el-form>
        </h2>
        <el-row class="btns">
          <el-button type="info" @click="submit()">提交</el-button>
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
        companyName:'',
        loanIntroduction:'',
        loanPeriod:'',
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
    handleClick(row) {
      console.log(row);
      console.log(row.companyId);
      axios.get('http://localhost:8081/getAllLoanByCompanyId?&companyId=' + row.companyId).then(function (res){
        console.log(res.data.data.records)
        localStorage.setItem("dataloan",JSON.stringify(res.data.data.records))
        var data = localStorage.getItem("dataloan");
        data = JSON.parse(data);
        console.log(data);
      })
      this.$router.push('/loanapproval')

    },
    checkDangAn(){
      this.$router.push('/main1')
    },
    makeDangan(){
      this.$router.push('/makeDangan1')
    },
    Dangan(){
      this.$router.push('/Dangan1')
    },
    toggleCollapse(){
      this.isCollapse=!this.isCollapse
    }

  },
}
</script>

<style scoped>
.btns{
  display: flex;
  position: relative;
  left: 600px;

}
.Form1{
  position: relative;
  left: 175px;

}
.form1{
  width: 40%;
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
  background-color: #447ED9;
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
/******************************/
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
/*******************************/
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
