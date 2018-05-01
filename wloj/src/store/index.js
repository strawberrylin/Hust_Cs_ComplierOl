import Vue from 'vue'
import Vuex from 'vuex'

import login from './login'
import user from './user'
import lab from './lab'
import record from './record'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    login,
    user,
    lab,
    record
  }
})
