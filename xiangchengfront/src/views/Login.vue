<template>
    <div class="login" :style="{ backgroundImage: 'url(/images/background.jpg)' }">
      <h1 class="title">乡城县水利数字沙盘</h1>
      <el-card class="login-card">

        <h2>用户登录</h2>
        <el-form ref="form" :model="loginForm" :rules="rules" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input :prefix-icon="User" placeholder="请输入账号" v-model="loginForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input :prefix-icon="Lock" placeholder="请输入密码" type="password" v-model="loginForm.password"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="captcha">
            <div class="captcha-wrapper">
              <el-input v-model="loginForm.captcha"  placeholder="请输入验证码" class="captcha-input"></el-input>
              <img class="captcha-image" src="/api/captcha?type=math" @click="refreshCaptcha">
            </div>
          </el-form-item>
          <el-form-item>
            <el-button class="login_btn" type="primary" @click="login">登录</el-button>
          </el-form-item>
          <el-form-item class="func-row">
            <div class="forgetpassword-wrapper">
              忘记密码?
            </div>
            <div class="register-wrapper">
              注册
            </div>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  import { defineComponent } from "vue";
  import { ElCard, ElForm, ElFormItem, ElInput, ElButton } from "element-plus";
  import axios from "../api/axios";
  import { h } from 'vue'
  import { ElNotification } from 'element-plus'
  import { Lock, User } from '@element-plus/icons-vue'
  export default defineComponent({
    name: "Login",
    components: {
      ElCard,
      ElForm,
      ElFormItem,
      ElInput,
      ElButton
    },
    mounted(){
      if(sessionStorage.getItem('jwt')){
        document.getElementsByClassName('login-card')[0].style.display='none'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, "已经登陆"),
          onClose:()=>{
            this.$router.push({ name: 'index' })
          },
          duration:1000
        })
      }
    },
    data() {
      return {
        loginForm: {
          username: "",
          password: "",
          captcha: ""
        },
        rules: {
          username: [{ required: true, message: "用户名不能为空", trigger: "blur" }],
          password: [{ required: true, message: "密码不能为空", trigger: "blur" }],
          captcha: [{ required: true, message: "验证码不能为空", trigger: "blur" }]
        }
      };
    },
    created() {
      document.body.style.marginTop = '0px';
    },
    methods: {
      async login() {
        try {
          await this.$refs.form.validate();
          const loginForm = {
            username: this.loginForm.username,
            password: this.loginForm.password,
            captcha: this.loginForm.captcha
          };
          const loginResult = await axios.post('/api/user/login', loginForm);
          console.log(loginResult.data.data);
          sessionStorage.setItem("jwt", loginResult.data.data)
          //登录成功后跳转
          this.loginTip("登录成功")
          
        } catch (error) {
          console.error(error.response.data.message); // 打印错误信息
          this.loginTip(error.response.data.message)
        }
      },
      refreshCaptcha() {
        const img = document.querySelector(".captcha-image");
        img.src = "/api/captcha?type=math&random=" + Math.random();
      },
      loginTip (message) {
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, message),
          onClose:()=>{
            this.$router.push({ name: 'index' })
          },
          duration:1000
        })
      }
    }
  });
  </script>
  
  <style>
  .login {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-size: cover; /* 背景图填充整个区域 */
  }
  .title {
    display: flex;
    font-size: 40px;
    color: #fff;
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
    margin-bottom: 30px;
    text-align: center;
  }
  
  .login-card {
    opacity: 0.9;
    width: 400px;
    padding: 30px;
    text-align: center;
  }
  .captcha-wrapper {
    display: flex;
    justify-content: space-between;
  }
  .captcha-input {
    width: 150px;
  }

  .captcha-image {
    margin-left: 10px;
    cursor: pointer;
    width: 120px;
    border-radius: 5px;
  }
  .login_btn{
    margin-left: -80px !important;
    width: 140%;
  }
  .forgetpassword-wrapper, .register-wrapper {
    width: 70px;
    text-align: right;
  }
  .forgetpassword-wrapper:hover, .register-wrapper:hover {
    cursor: pointer;
    color: cornflowerblue;
  }

  .func-row {
    
    margin-left: 100px;
  }
  </style>