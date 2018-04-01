<template>
  <div id="app" >
    <el-container v-if="isLogin" class="main-container">
      <el-header>
        <Header></Header>
      </el-header>
      <el-container>
        <el-main>
        </el-main>
      </el-container>
    </el-container>
    <Login v-if="!isLogin"></Login>
  </div>
</template>

<script>
import Header from './components/header.vue'
import Login from './pages/login.vue'

export default {
  components: {
    Header,
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
    margin-top: 60px;
  }
  .main-container {
    height: 100%;
    border: 1px solid #eee;
    margin: 10px 50px 0 50px;
  }
  /* green border */
  .el-container, .el-aside {
    border: 1px solid green;
  }

</style>
