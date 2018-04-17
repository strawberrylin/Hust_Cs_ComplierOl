<template>
  <div id="app" >
    <router-view></router-view>
  </div>
</template>

<script>
import Login from './pages/login.vue'
import Person from './pages/person.vue'
import Student from './pages/student.vue'
import Teacher from './pages/teacher.vue'
import Experiment from './pages/experiment.vue'
import Admin from './pages/admin.vue'

export default {
  components: {
    Student,
    Login,
    Person,
    Teacher,
    Experiment,
    Admin
  },
  name: 'App'
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
