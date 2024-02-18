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
        <el-menu-item index="1" :style="{height:'59px'}" @click="backh">
          <i class="el-icon-s-home"></i>主页
        </el-menu-item>
        <el-submenu index="2" :style="{float:'right',height:50,marginRight:'50px',border:0}">
          <template slot="title">
            <a>
              <div class="logo"/>
            </a>
          </template>
          <el-menu-item index="5-1" :style="{paddingLeft:'70px'}">个人中心</el-menu-item>
          <el-menu-item index="5-2" :style="{paddingLeft:'70px'}" @click="logout">退出登录</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-header>
    <el-container class="home-container">

      <el-main class="firstmain">

        <h1 style="height: 100px;margin-top: 75px;text-align: center">填写申诉信息</h1>
        <h2>
          <el-form ref="form" :model="form" label-width="150px" class="Form1">
            <el-form-item label="申诉机构名称:" >
              <el-input v-model="form.complainInstitutionName" class="form1"></el-input>
            </el-form-item>
            <el-form-item label="申诉内容:" >
              <el-input type="textarea" :rows="2"  v-model="form.complainText" class="form2"  ></el-input>
            </el-form-item>

          </el-form>
        </h2>
        <el-row class="btns">
          <el-button type="primary" @click="submit" style="position: relative;left: 60px;top: 60px">提交</el-button>
          <el-button type="danger" @click="backh" style="position: relative;left: 90px;top: 60px">返回</el-button>
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
        complainMessageId:'',
        companyId:'',
        complainText:'',
        complainInstitutionName:'',
        createTime:'',
      }
    }
  },
  methods:{
    submit() {
      this.$refs.form.validate((valid)=> {
        if(valid) {
          this.$axios.post('http://localhost:8081/editComplainmessage',this.form).then(res => {
            console.log(res);
            if(res.data.code != 0) return this.$message.error("申诉失败！")
            this.$message.success("申诉成功！！")
            this.$router.push('/home2')

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
    },
    backh(){
      this.$router.push('/home2')
    }

  },
}
</script>

<style scoped>
/******************************/
.el-menu-demo{
  width:100%;
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
.btns{
  display: flex;
  position: relative;
  left: 600px;

}
.Form1{
  position: relative;
  left: 200px;

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
  background-color: #545c64;
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
