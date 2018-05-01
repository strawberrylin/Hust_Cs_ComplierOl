import Vue from 'vue'
import Router from 'vue-router'

import Login from '../pages/login.vue'
import Student from '../pages/student.vue'
import Person from '../pages/person.vue'
import Teacher from '../pages/teacher.vue'
import Detail from '../pages/detail.vue'
import Admin from '../pages/admin.vue'
import AddLab from '../components/addlab.vue'
import UpdateLab from '../components/updatelab.vue'
import DeleteLab from '../components/deletelab.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Login
    },
    {
      path: '/login',
      name: 'login',
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
      path: '/detail',
      name: 'detail',
      component: Detail
    },
    {
      path: '/admin/',
      name: 'admin',
      component: Admin,
      children: [
        {
          path: '1-1',
          name: 'addlab',
          component: AddLab
        },
        {
          path: '1-2',
          name: 'updatelab',
          component: UpdateLab
        },
        {
          path: '1-3',
          name: 'deletelab',
          component: DeleteLab
        }
      ]
    }
  ]
})
