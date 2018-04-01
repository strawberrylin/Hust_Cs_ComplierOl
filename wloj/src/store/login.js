export default {
  // common information
  state: {
    // the state of the login
    isLogin: false
  },
  // chanege the state by $store.commit('methodName)
  mutations: {
    loginSuccess (state) {
      state.isLogin = true
    },
    loginFail (state) {
      state.isLogin = false
    }
  },
  // change the state
  actions: {
    loginSuccess ({commit}) {
      console.log('loginsuccess')
      commit('loginSuccess')
    },
    loginFail ({commit}) {
      commit('loginFail')
    }
  }
}
