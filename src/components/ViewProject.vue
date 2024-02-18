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
