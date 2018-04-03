<template>
  <div id="app" >
    <router-view></router-view>
  </div>
</template>

<script>
import Student from './pages/student.vue'
import Login from './pages/login.vue'

export default {
  components: {
    Student,
    Login
  },
  name: 'App',
  computed: {
    isLogin () {
      return this.$store.state.login.isLogin
    }
  }
}

</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  .main-container {
    height: 100%;
    border: 1px solid #eee;
    margin: 0 50px 0 50px;
  }
  /* green border */
  .el-container, .el-aside {
    border: 1px solid rgb(79, 200, 236);
  }

</style>
