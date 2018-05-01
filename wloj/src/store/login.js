export default {
  // common information
  state: JSON.parse(sessionStorage.getItem('isLogin')) || {'isLogin': false},
  // chanege the state by $store.commit('methodName)
  mutations: {
    loginSuccess (state) {
      sessionStorage.setItem('isLogin', JSON.parse(true))
      Object.assign(state)
    },

    loginFail (state) {
      state.isLogin = false
    }
  },
  // change the state
  actions: {
    loginSuccess ({commit}) {
      commit('loginSuccess')
    },
    loginFail ({commit}) {
      commit('loginFail')
    }
  }
}
