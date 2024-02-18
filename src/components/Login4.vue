<template>
  <div id="whole">
    <form>
<!--      登录表单-->
      <div class="login_container">
        <div class="login_box">
          <!--头像区域-->
          <!--登录表单区域-->
          <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="80px" class="login_form">
            <h1>welcome</h1>
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
            <el-link type="primary" href="http://localhost:8080/#/login2">我是企业？</el-link>
            <el-form-item class="btns">
              <el-button type="primary" @click="login">登录</el-button>
              <el-button type="danger" @click="register">注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>

    </form>

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
        user_status: 1
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
    register() {
      this.$router.push('/register1')

    },
    login() {
      this.$refs.loginFormRef.validate((valid)=> {
        if(valid) {
          this.$axios.post('http://localhost:8081/login',this.loginForm).then(res => {
            console.log(res.headers.authorization);
            console.log(res);
            console.log(res.data.code);
            if(res.data.code != 0) return this.$message.error("登录失败！")
            this.$message.success("登录成功！")
            window.sessionStorage.setItem("token",res.headers.authorization)
            this.$router.push('/home1')

          })

        }

      });
    }
  }
}
</script>




<style scoped>
#whole{
  width: 100vw;
  padding: 0;
  margin: 0;
  height: 100vh;
  font-size: 16px;
  background-position: left top;
  background-color: #242645;
  background-image:url("../assets/login.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;

}
#login-box {
  width: 240px;
  height: 280px;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  padding: 50px 40px 40px 40px;
  border:0;
  background-color: rgba(215, 223, 226, 0);
  width: 300px;
}
{
  color: white;
  margin-left: 35px;
  display: block;
}
#login-box .form .item input {
  width: 200px; /* 设置合适的宽度 */
  border: 0; /* 首先将边界取消，方便下面修改下部边界宽度 */
  border-bottom: 5px solid white; /* 将下边界进行修改，显示出横线效果 */
  font-size: 18px; /* 将字体适当的变大加粗 */
  background: #ffffff00; /* 将输入框设置为透明 */
  color: white; /* 上面的文本颜色设置为白色，但是placeholder的颜色要单独设置 */
  padding: 5px 10px; /* 为了placeholder的内容不是顶格显示，增加内部边界 */
}
#login-box .form .item i {
  color: white;
  font-size: 18px;
}
#button{
  width: 150px; /* 设置合适的按钮的长和宽 */
  height: 30px;
  margin-top: 18px; /* 设置合适的上部外框的宽度，增加与上面的password框的距离 */
  margin-left: 25px;
  color: white; /* 修改按钮上文字的颜色 */
  border-radius: 25px; /* 将按钮的左右边框设置为圆弧 */
  background-image: linear-gradient(to right, #00dbde 0%, #fc00ff 100%); /* 为按钮增加渐变颜色 */
}
.login_form{
  position:absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
</style>
