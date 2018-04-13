<template>
<el-container style="height: 100vh;">
  <el-main>
  </el-main>
  <el-main style="margin-top:180px;text-align:center;">
  <div id="login">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item>
        <el-button type="success" class="submitbtn"  @click="onSubmit">Sign In</el-button>
      </el-form-item>
      <el-form-item label="帐号">
        <el-input v-model="form.name" placeholder="请输入学号"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password" placeholder="初始密码为123456"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="submitbtn"  @click="onSubmit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
  </el-main>
</el-container>
</template>

<script>
// import mapActions from vuex
import {
  mapActions
} from 'vuex'

import md5 from 'js-md5'

let data = {
  form: {
    name: '',
    password: ''
  },
  msg: '',
  onLogging: false
}
export default {
  data: function () {
    return data
  },
  methods: {
    // import function from store.action
    ...mapActions(['logIn', 'loginSuccess']),
    onSubmit: function (e) {
      if (this.onLogging) {
        this.msg = '正在登录中'
        return false
      }
      this.onLogging = true
      if (!this.form.name || !this.form.password) {
        this.msg = '请输入帐号和密码'
        this.onLogging = false
        return
      }
      this.$ajxj({
        method: 'post',
        url: '/user/login',
        params: {
          password: md5(this.form.password),
          username: this.form.name
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            this.loginSuccess()
            this.logIn(data.data)
            this.$router.push({path: '/experiment'})
          } else {
            this.msg = data.message
          }
        }).catch((err) => {
          this.msg = '登录异常'
          console.log(err)
        }).finally(() => {
          this.onLogging = false
        })
    }
  }
}
</script>

<style>
  #login {
    width: 300px;
    margin: 0 auto;
  }
  .submitbtn{
    width: 220px;
  }
  body{
    background-image: url(../assets/back.jpg);
    background-size: cover;
    width: 100%;
    height: 100%;
  }
</style>
