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
        <el-menu-item index="1" :style="{height:'59px'}" @click="checkDangAn"><i class="el-icon-menu"></i>待审核金融机构公司档案</el-menu-item>
        <el-menu-item index="2" :style="{height:'59px'}" @click="makeDangan"><i class="el-icon-menu"></i>金融机构档案</el-menu-item>
        <el-menu-item index="3" :style="{height:'59px'}" @click="laheiDangan"><i class="el-icon-menu"></i>已拉黑金融机构档案</el-menu-item>
        <el-menu-item index="4" :style="{height:'59px'}" @click="shenheshensu()"><i class="el-icon-menu"></i>贷款公司申诉信息</el-menu-item>
        <el-submenu index="5" :style="{float:'right',height:50,marginRight:'50px',border:0}">
          <template slot="title">
            <a>
              <div class="logo"/>
            </a>
          </template>
          <el-menu-item index="5-1" :style="{paddingLeft:'70px'}">个人中心</el-menu-item>
          <el-menu-item index="5-2" :style="{paddingLeft:'70px'}"  @click="logout">退出登录</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-header>
    <el-container class="home-container">
      <el-main class="firstmain">
        <el-container>
          <el-header class="secondheader" style="height: 200px">
            <h1 style="height: 100px;margin-top: 75px;font-size: 20pt;font-family: 黑体">金融机构档案</h1>
          </el-header>
          <el-main class="secondmain">
            <el-table
              :data="tableData"
              border
              style="width: 100%">
              <el-table-column
                fixed
                prop="institutionName"
                label="金融机构名称"
                width="200">
              </el-table-column>
              <el-table-column
                prop="institutionEmail"
                label="金融机构电子邮箱"
                width="260">
              </el-table-column>
              <el-table-column
                prop="institutionIntroduction"
                label="金融机构简介"
                width="300">
              </el-table-column>
              <el-table-column
                prop="institutionCertificate"
                label="金融机构资质认证"
                width="300">
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作"
                width="100">
                <template slot-scope="scope">
                  <el-button type="text" size="small" @click="handleClick(scope.row)">拉黑金融机构</el-button>
                </template>

              </el-table-column>
            </el-table>
            <el-pagination class="mpage"
                           background
                           layout="prev, pager, next"
                           :total="1000">
            </el-pagination>
          </el-main>
        </el-container>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>


import axios from "axios";

export default {
  created(){
    const _this = this
    axios.get('http://localhost:8081/getInstitutionsWithoutBlock').then(function (resp){
      console.log(resp.data.data.records)
      _this.tableData = resp.data.data.records

    })
  },
  data() {
    return {
      isCollapse: false,
      tableData: [
        {}
      ]

    }
  },
  methods:{
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
    logout(){
      window.sessionStorage.clear()
      this.$router.push('/login1')
    },
    handleClick(row) {
      console.log(row);
      this.$axios.post('http://localhost:8081/blockInstitution',row).then(res => {
        console.log(res);
        if(res.data.code != 0) return this.$message.error("拉黑未通过！")
        this.$message.success("拉黑成功！")
      })
    },
    checkDangAn(){
      this.$router.push('/home3')
    },
    makeDangan(){
      this.$router.push('/economic')
    },
    laheiDangan(){
      this.$router.push('/leconomic')
    },
    shenheshensu(){
      this.$router.push('/shenheshensu')
    },
    cahk(){
      this.$router.push('/seeconomic')
    },
    toggleCollapse(){
      this.isCollapse=!this.isCollapse
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
