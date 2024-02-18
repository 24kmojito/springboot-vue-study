<template>
  <div class="login_container">
    <div class="login_box">
      <!--头像区域-->
      <h1 style="color: black;margin-left: 150px; display: block">Welcome</h1>
      <!--登录表单区域-->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="80px" class="login_form">
        <!--用户名-->
        <el-form-item  prop="user_email" label="用户名">
          <el-input v-model="loginForm.user_email" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="password" label="密码">
          <el-input v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <el-form-item prop="user_status">
        </el-form-item>
        <!--按钮区域-->
        <el-link type="primary" href="http://localhost:8080/#/login1">金融机构请点击这里</el-link>
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="danger" @click="register1">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

export default {
  data(){
    return{
      //这是登录表单的数据绑定对象
      loginForm: {
        user_email: '',
        password: '',
        user_status: 2
      },
      //这是表单的验证规则对象
      loginFormRules: {
        //验证用户名是否合法
        user_email: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
        ],
        //验证密码是否合法
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }

        ]
      }
    };
  },
  methods: {
    //点击重置按钮
    register1() {
      this.$router.push('/register2')
    },
    login() {
      this.$refs.loginFormRef.validate((valid)=> {
        if(valid) {
          this.$axios.post('http://localhost:8081/login',this.loginForm).then(res => {
            console.log(res);
            if(res.data.code != 0) return this.$message.error("登录失败！")
            this.$message.success("登录成功！")
            console.log(res.data.data);
            // console.log(res.headers.authorization);
            window.sessionStorage.setItem("token",res.headers.authorization)

            localStorage.setItem("datacompany",JSON.stringify(res.data.data))
            var datacompany = localStorage.getItem("datacompany");
            datacompany = JSON.parse(datacompany);
            console.log(datacompany);
            this.$router.push('/home2')
          })

        }

      });
    }
  }
}
</script>

</script>

<style lang="less" scoped>
.login_container{
  background-image:url(../assets/background.png);
  height: 100%;
}

.login_box{
  width: 450px;
  height: 300px;
  background-color:#fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top:50%;
  transform: translate(-50%,-50%);


  .avatar_box{
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%,-50%);
    background-color: #fff;

    img{
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }

}
.login_form{
  position:absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.btns{
  display: flex;
  justify-content: flex-end;

}
</style>
