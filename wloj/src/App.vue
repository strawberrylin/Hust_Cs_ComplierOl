<template>
  <div id="app" >
    <router-view></router-view>
  </div>
</template>

<script>
import Student from './pages/student.vue'
import Login from './pages/login.vue'
import Experiment from './pages/experiment.vue'
import Person from './pages/person.vue'

export default {
  components: {
    Student,
    Login,
    Experiment,
    Person
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
  }
  html, body, * {
    margin: 0;
  }
</style>
