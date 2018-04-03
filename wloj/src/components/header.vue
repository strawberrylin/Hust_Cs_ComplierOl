<template>
  <div>
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown @command="handleCommand">
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown" >
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item command="logout">注销</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-button type="success" class="userIcon">{{$store.state.user.username}}</el-button>
    </el-header>
  </div>
</template>

<script>
import {
  mapActions
} from 'vuex'
export default {
  methods: {
    ...mapActions(['logOut', 'loginFail']),
    onLogout: function () {
      this.logOut()
      this.loginFail()
      this.$router.push({path: '/login'})
    },
    handleCommand (command) {
      if (command === 'logout') {
        this.onLogout()
      }
    }
  }
}
</script>

<style>
  .userIcon{
  }
</style>
