import Vue from 'vue'
// sessionStorage store
export default {
  // JSON.parse generate a instance from string
  state: JSON.parse(sessionStorage.getItem('user')) || {},
  mutations: {
    logIn (state, user) {
      // store
      sessionStorage.setItem('user', JSON.stringify(user))
      Object.assign(state, user)
    },
    logOut (state) {
      sessionStorage.removeItem('user')
      Object.keys(state).forEach(k => Vue.delete(state, k))
      Object.assign(state, {})
    }
  },
  actions: {
    logIn ({commit}, user) {
      commit('login', user)
    },
    logOut ({commit}) {
      commit('logOut')
    }
  }
}
