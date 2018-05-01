export default {
  state: JSON.parse(sessionStorage.getItem('lab')) || {},
  mutations: {
    storeLab (state, lab) {
      sessionStorage.setItem('lab', JSON.stringify(lab))
      Object.assign(state, lab)
    }
  },
  actions: {
    storeLab ({commit}, lab) {
      commit('storeLab', lab)
    }
  }
}
