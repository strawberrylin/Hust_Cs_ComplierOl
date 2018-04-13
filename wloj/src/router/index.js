import Vue from 'vue'
import Router from 'vue-router'
import Login from '../pages/login.vue'
import Student from '../pages/student.vue'
import Experiment from '../pages/experiment.vue'
import Person from '../pages/person.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/',
      component: Login
    },
    {
      path: '/student',
      name: 'student',
      component: Student
    },
    {
      path: '/experiment',
      name: 'experiment',
      component: Experiment
    },
    {
      path: '/person',
      name: 'person',
      component: Person
    }
  ]
})
