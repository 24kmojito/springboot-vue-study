<template>
  <el-container class="home-container">
    <el-header class="firstheader">
      <div style="align-items: center;display: flex">
        <el-button style="padding:unset;background-color: #4C8AEB;height: 57px;border: none" @click="toggleCollapse"><img src="@/assets/alogo.png" style="height: 60px" alt="无法显示"></el-button>
        <span style="color: white;padding-left: 40px;font-size: 20pt">金融机构信贷管理信息系统</span>
      </div>
      <div style="padding-right: 20px;align-items: center;display: flex">
        <span style="color: white;font-size: 16pt;">已登录</span>
        <el-button @click="logout" style="padding-right: unset;background-color: #447ED9;border: none"><img src="@/assets/tlogo.png"  /></el-button>
      </div>
    </el-header>
    <el-container class="home-container">
      <el-aside :width="isCollapse ? '64.8px' : '250px'">
        <el-menu onload="open(this.index)"
                 default-active="1-1"
                 class="el-menu-vertical-demo;home-container"
                 background-color="#E6E6E6"
                 text-color="black"
                 active-text-color="blue"
                 :collapse="isCollapse"
                 :collapse-transition="false" router>
          <el-submenu index="1" onload="open(this.index)">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>内部管理</span>
            </template>

            <el-menu-item index="1-1" @click="checkDangAn" onload="open(this.index)">
              <template slot="title" >
                <i class="el-icon-menu"></i>
                <span>贷款项目待审核公司档案</span>
              </template>
            </el-menu-item>

            <el-menu-item index="1-2" @click="makeDangan" onload="open(this.index)">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>建立贷款档案</span>
              </template>
            </el-menu-item>

            <el-menu-item index="1-3" @click="Dangan" onload="open(this.index)">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>查看贷款公司档案</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
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
          <el-button type="primary" @click="submit()">提交</el-button>
        </el-row>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>


import axios from "axios";

export default {
  created(){
    const _this = this
    var data = localStorage.getItem("dataproject");
    data = JSON.parse(data);
    console.log(data);
    console.log(data[0].companyId);
    console.log(data[0].loanId);
    _this.data1 = data;
    console.log(_this.data1[0].companyId);
    console.log(_this.data1[0].loanId);
    // this.form.companyId = _this.data1[0].companyId;
    // this.form.loanId = _this.data1[0].loanId
  },
  data() {
    return {
      isCollapse: false,
      data1:{},
      form:{
        projectName:'',
        projectIntroduction:'',
        projectCost:'',
        projectComment:'',
        projectRemark:'',
        projectStartTime:'',
        projectEndTime:'',
        companyId:'2',
        loanId:'3',
      },
    }
  },
  methods:{
    submit() {
      this.$refs.form.validate((valid)=> {
        if(valid) {
          this.$axios.post('http://localhost:8081/editProject',this.form).then(res => {
            console.log(res);
            if(res.data.code != 0) return this.$message.error("项目信息填写错误！")
            this.$message.success("建立项目档案成功！！")

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
    },
    checkDangAn(){
      this.$router.push('/home1')
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
.Timelocation{
  position: relative;
  left: 300px;
}
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
  width: 20%;
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
.firstheader {
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
</style>

<style>
.secondheader{
  background-color: white;
  height: 100px;
}

</style>
