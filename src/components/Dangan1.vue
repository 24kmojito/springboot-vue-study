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
        <el-menu-item index="1"  :style="{height:'59px'}" @click="checkDangAn" onload="open(this.index)" >
          <i class="el-icon-menu" ></i>贷款项目待审核公司档案</el-menu-item>
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
        <el-container>
          <el-header class="secondheader" style="height: 200px">
            <h1 style="height: 100px;margin-top: 75px;font-size: 20pt;font-family: 黑体">贷款公司档案</h1>
          </el-header>
          <el-main class="secondmain">
            <el-table
              :data="tableData"
              border
              style="width: 100%">
              <el-table-column
                fixed
                prop="createTime"
                label="贷款起始时间"
                width="180">
              </el-table-column>
              <el-table-column
                prop="companyName"
                label="贷款公司"
                width="260">
              </el-table-column>
              <el-table-column
                prop="loanPeriod"
                label="贷款年限"
                width="120">
              </el-table-column>
              <el-table-column
                prop="loanAmmount"
                label="贷款金额"
                width="120">
              </el-table-column>
              <el-table-column
                prop="loanInterest"
                label="贷款利率"
                width="120">
              </el-table-column>
              <el-table-column
                prop="loanIntroduction"
                label="贷款简介"
                width="600">
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作"
                width="100">
                <template #default="scope">
                  <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
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
    axios.get('http://localhost:8081/getAllLoan').then(function (resp){
      console.log(resp.data.data.records)
      _this.tableData = resp.data.data.records
    })
  },
  data() {
    return {
      isCollapse: false,
      tableData: null
    }
  },
  methods:{
    logout(){
      window.sessionStorage.clear()
      this.$router.push('/login1')
    },
    handleClick(row) {
      console.log(row);
      console.log(row.loanId);
      axios.get('http://localhost:8081/getAllProject?loanId='+ row.loanId).then(function (res){
        console.log(res.data.data.records)
        localStorage.setItem("dataproject",JSON.stringify(res.data.data.records))
        var dataproject = localStorage.getItem("dataproject");
        dataproject = JSON.parse(dataproject);
        console.log(dataproject);
      })
      this.$router.push('/viewDangan1')

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
