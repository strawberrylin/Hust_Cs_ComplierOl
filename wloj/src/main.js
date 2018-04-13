// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'
import router from './router'
import store from './store'
import axios from './axios'

var VueCodeMirror = require('vue-codemirror-lite')

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

Vue.use(ElementUI, { size: 'small' })
Vue.use(locale)
Vue.use(VueCodeMirror)

Vue.config.productionTip = false
Vue.prototype.$ajxj = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
