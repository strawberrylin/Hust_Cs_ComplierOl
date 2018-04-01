<template>
  <div>
    <el-button type="success" class="userIcon">{{$store.state.user.username}}</el-button>
    <el-button type="primary" @click="onLogout" round class="signout">注销</el-button>
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
    }
  }
}
</script>

<style>
  .userIcon{

  }
  .userIcon{

  }
</style>
