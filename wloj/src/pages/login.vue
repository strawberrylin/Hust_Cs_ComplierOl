<template>
  <div id="login">
    <img src="../assets/logo.png">
    <p>Enjoy Geek</p>
    <p>{{msg}}</p>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="帐号">
        <el-input v-model="form.name" placeholder="请输入学号"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password" placeholder="初始密码为123456"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// import mapActions from vuex
import {
  mapActions
} from 'vuex'

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
      this.$ajxj.get('/test/persons', {
        name: this.form.name,
        password: this.form.password
      })
        .then((response) => {
          let data = response.data
          if (data.code === 200) {
            this.loginSuccess()
            this.logIn(data.data)
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

</style>
