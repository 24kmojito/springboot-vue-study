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

        <h1 style="height: 100px;margin-top: 75px;text-align: center">贷款项目信息</h1>
        <div class="Timelocation">
          <el-timeline>
            <el-timeline-item  :timestamp="dataproject.updateTime" placement="top" v-for="dataproject in dataproject">
              <el-card>
                <h4>项目名称</h4>
                <p>{{dataproject.projectName}}</p>
              </el-card>
              <el-card>
                <h4>项目简介</h4>
                <p>{{dataproject.projectIntroduction}}</p>
              </el-card>
              <el-card>
                <h4>开始时间</h4>
                <p>{{dataproject.projectStartTime}}</p>
              </el-card>
              <el-card>
                <h4>项目所用金额</h4>
                <p>{{dataproject.projectCost}}</p>
              </el-card>
              <el-card>
                <h4>项目评价</h4>
                <p>{{dataproject.projectComment}}</p>
              </el-card>
              <el-card>
                <h4>项目其他备注</h4>
                <p>{{dataproject.projectRemark}}</p>
              </el-card>
            </el-timeline-item >
          </el-timeline>
        </div>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>


import axios from "axios";

export default {
  created(){
    const _this = this
    var dataproject = localStorage.getItem("dataproject");
    dataproject = JSON.parse(dataproject);
    console.log(dataproject)
    _this.dataproject = dataproject;
  },
  data() {
    return {
      isCollapse: false,
      dataproject:{}
    }
  },
  methods:{
    logout(){
      window.sessionStorage.clear()
      this.$router.push('/login1')
    },
    addproject() {
      this.$router.push('/addproject1')
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
.Timelocation{
  position: relative;
  left: 450px;
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
