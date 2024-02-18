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
        <el-container>
          <el-header class="secondheader" style="height: 200px">
            <h1 style="height: 100px;margin-top: 75px;font-size: 20pt">申请贷款公司档案</h1>
          </el-header>
          <el-main class="secondmain">
              <el-table
                :data="loandata"
                border
                style="width: 100%">
                <el-table-column
                  fixed
                  prop="companyName"
                  label="贷款公司"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="createTime"
                  label="贷款起始时间"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="updateTime"
                  label="贷款结束时间"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="loanIntroduction"
                  label="贷款用途"
                  width="250">
                </el-table-column>
                <el-table-column
                  prop="loanAmmount"
                  label="贷款金额"
                  width="165">
                </el-table-column>
                <el-table-column
                  prop="loanInterest"
                  label="贷款利率"
                  width="120">
                </el-table-column>
                <el-table-column
                  prop="loanPeriod"
                  label="贷款年限"
                  width="150">
                </el-table-column>
                <el-table-column
                  prop="loanRemark"
                  label="贷款期间公司对公账单"
                  width="200">
                  <template #default="scope">
                    <el-button @click="reconciliation(scope.row)"  type="text" size="small">文件</el-button>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="loanRemark"
                  label="贷款期间公司偿还记录"
                  width="200">
                  <template #default="scope">
                    <el-link  type="primary">文件</el-link>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="loanInterest"
                  label="备注"
                  width="300">
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
    var data = localStorage.getItem("dataloan");
    data = JSON.parse(data);
    console.log(data)
    _this.loandata = data;
  },
  data() {
    return {
      isCollapse: false,
      loandata:{}
    }
  },
  methods:{
    reconciliation(row){
      console.log(row)
      console.log(row.loanBankReconciliation)
      location.href = row.loanBankReconciliation

    },
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
      this.$router.push('/viewproject')

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
    }

  }
}
</script>

<style scoped>
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
</style>

<style>
.secondheader{
  background-color: white;
  height: 100px;
}

</style>

