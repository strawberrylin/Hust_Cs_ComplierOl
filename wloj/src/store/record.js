export default {
  state: JSON.parse(sessionStorage.getItem('record')) || {},
  mutations: {
    storeRecord (state, record) {
      sessionStorage.setItem('record', JSON.stringify(record))
      Object.assign(state, record)
    }
  },
  actions: {
    storeRecord ({commit}, record) {
      commit('storeRecord', record)
    }
  }
}
