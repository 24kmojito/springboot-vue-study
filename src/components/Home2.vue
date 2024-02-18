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
          <i class="el-icon-s-home"></i>主页
        </el-menu-item>
        <el-submenu index="2" :style="{float:'right',height:50,marginRight:'50px',border:0}">
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
        <hr style="position: relative;top: 20px;color: #E6E6E6"/>
        <div :style="{height:'150px'}">
          <table style="position: relative;left: 400px;top: 50px">
            <tr>
              <td>贷款公司名称：</td>
              <td style="width: 180px" align="center">{{tableData2.company_name}}</td>
              <td style="width: 100px"></td>
              <td>贷款公司邮箱：</td>
              <td style="width: 180px" align="center">{{tableData2.company_email}}</td>
            </tr>
            <tr style="height: 50px"></tr>
            <tr>
              <td>认证证书：</td>
              <td style="width: 180px" align="center">{{tableData2.company_qualification}}</td>
              <td style="width: 100px"></td>
              <td>起始年份：</td>
              <td style="width: 180px" align="center">{{tableData2.company_initial_year}}</td>
            </tr>
          </table>
        </div>
        <hr style="position: relative;top: 20px;color: #E6E6E6"/>
        <span style="position: relative;left: 150px;top: 80px">贷款信息记录</span>
        <el-table
          :data="tableData"
          border
          style="width: 100%;position: relative;top: 20px;bottom: 20px">
          <el-table-column
            fixed
            prop="createTime"
            label="贷款申请日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="companyName"
            label="贷款申请公司"
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
            prop="loanIntroduction"
            label="贷款用途"
            width="600">
          </el-table-column>
          <el-table-column
            prop="loanRemark"
            label="备注"
            width="120">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template #default="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
              <el-button @click="tianjiashensu" type="text" size="small">申诉</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>


import axios from "axios";

export default {
  created(){
    const _this = this
    console.log(1)
    var datacompany = localStorage.getItem("datacompany");
    datacompany = JSON.parse(datacompany);
    console.log(datacompany)
    _this.tableData2 = datacompany;
    axios.get('http://localhost:8081/getCompanyLoanByCompanyId').then(function (resp){
        console.log(resp.data.data.records)
        _this.tableData = resp.data.data.records
    })
  },

  data() {
    return {
      isCollapse: false,
      tableData:
        [
          {"company_name":"武汉斗鱼网络科技有限公司",
            "company_email":" liuyang1@douyu.tv",
            "company_qualification":10000203203203034123214215,
            "company_initial_year":2015,}

        ]
      ,
      tableData2:
        {
          companyName:'1',
          companyEmail:'1',
          companyQualification:'1',
          companyInitialYear:'1'
        }
      ,
      form:{
        companyName:'1',
        loanIntroduction:'1',
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
    async removeUserById (id) {
      // 弹框询问用户是否删除数据
      const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch()

      const {data: res} = await this.$http.delete('' + id)
      if (res.meta.status !== 200) {
        return this.$message.error('删除用户失败！')
      }
      this.$message.success('删除用户成功！')
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
    logout(){
      window.sessionStorage.clear()
      this.$router.push('/login3')
    },
    handleClick(row) {
      console.log(row);
      console.log(row.companyId);
      this.$router.push('/viewDangan1')
    },
    checkDangAn(){
      this.$router.push('/home2')
    },
    makeDangan(){
      this.$router.push('/economic')
    },
    Dangan(){
      this.$router.push('/Leconomic')
    },

    shenhe(){
      this.$router.push('/checkeconomic')
    },
    toggleCollapse(){
      this.isCollapse=!this.isCollapse
    },
    tianjiashensu(){
      this.$router.push('/addshensu')
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
.mpage{
  margin: 0 auto;
  text-align: center;
}
.form1{
  width: 40%;
  margin-left: 50px;

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
.Form1{
  position: relative;
  left: 0px;
  top: 30px;

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
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>

<style>
.secondheader{
  background-color: white;
  height: 100px;
}

</style>
