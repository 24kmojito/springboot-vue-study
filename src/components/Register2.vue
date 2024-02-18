<template>
  <div>
    <h1 style="height: 100px;margin-top: 75px;text-align: center">注册公司账号</h1>
    <div class="cc">
      <h2>
        <el-form ref="form" :model="form" label-width="150px" class="Form1">
          <el-form-item label="公司名称:" >
            <el-input v-model="form.companyName" class="form1"></el-input>
          </el-form-item>
          <el-form-item label="公司电子邮箱:" >
            <el-input v-model="form.companyEmail" class="form2"  ></el-input>
          </el-form-item>
          <el-form-item label="密码:">
            <el-input v-model="form.companyPwd" class="form3" type="password"></el-input>
          </el-form-item>
          <el-form-item label="公司起始年份:">
            <el-input v-model="form.companyInitialYear" class="form4"></el-input>
          </el-form-item>
          <el-form-item label="公司资质认证:">
            <el-input type="textarea" :rows="2" v-model="form.companyQualification" class="form5"></el-input>
          </el-form-item>

        </el-form>
      </h2>
      <el-row class="btns">
        <el-button type="primary" @click="submit">提交</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return{
      form:{
        companyName:'',
        companyEmail:'',
        companyPwd:'',
        companyInitialYear:'',
        companyQualification:''
      }

    }
  },
  methods:{
    submit() {
      this.$refs.form.validate((valid)=> {
        if(valid) {
          this.$axios.post('http://localhost:8081/registerCompany',this.form).then(res => {
            console.log(res);
            if(res.data.code != 0) return this.$message.error("注册失败！")
            this.$message.success("公司注册成功！")
            this.$router.push('/login2')
          })

        }

      });

    }
  }
}
</script>

<style scoped>
.cc{
  position: relative;
  left: 175px;

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
  width: 30%;
  margin-left: 20px;
  height: 100%;

}
.form3{
  width: 30%;
  margin-left: 20px;
  height: 100%;    /*调整间距*/

}
.form4{
  width: 10%;
  margin-left: 20px;
  height: 100%;    /*调整间距*/

}
.form5{
  width: 60%;
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
</style>
