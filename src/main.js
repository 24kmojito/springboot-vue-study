import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import 'ant-design-vue/dist/antd.css'
import axios from 'axios'

Vue.prototype.$http = axios
Vue.prototype.$axios = axios

axios.interceptors.request.use(config => {
  //在最后必须return config
  config.headers.authorization = window.sessionStorage.getItem('token')
  return config
})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
