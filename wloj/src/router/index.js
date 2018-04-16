import Vue from 'vue'
import Router from 'vue-router'
import Login from '../pages/login.vue'
import Student from '../pages/student.vue'
import Person from '../pages/person.vue'
import Teacher from '../pages/teacher.vue'
import Experiment from '../pages/experiment.vue'
import Admin from '../pages/admin.vue'
import Test from '../components/test.vue'

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
      path: '/person',
      name: 'person',
      component: Person
    },
    {
      path: '/teacher',
      name: 'teacher',
      component: Teacher
    },
    {
      path: '/experiment',
      name: 'experiment',
      component: Experiment
    },
    {
      path: '/admin',
      name: 'admin',
      component: Admin,
      children: [
        {
          path: '/admin/1-1',
          name: 'test',
          component: Test
        }
      ]
    }
  ]
})
