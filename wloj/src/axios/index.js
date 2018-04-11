import axios from 'axios'
import {
  Message
} from 'element-ui'

// axiossettings
// time out : 3s
axios.defaults.timeout = 3000
// cookie
axios.defaults.withCredentials = true
// if developement environment, baseUrl
if (process.env.NODE_ENV === 'development') {
  axios.defaults.baseURL = 'http://localhost:8888/wloj'
}

// content-type settings
// deal with the problem exist in SpringMVC Controller, can not get the correct param in request
axios.interceptors.request.use(
  config => {
    const data = config.data
    if (!data) {
      return config
    }
    // const key = Object.keys(data)
    // rewrite the data {"name":"name","password":"password"} => name=name&password=password
    // config.data = encodeURI(key.map(name => `${name}=${data[name]}`).join('&'))
    console.log(config.data)
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

axios.interceptors.response.use(response => {
  const res = response.data
  if (res.code !== 200) {
    Message({
      message: res.message,
      type: 'error',
      duration: 5 * 1000
    })
  } else {
    return response.data
  }
}, error => {
  console.log('err:' + error)
  Message({
    message: error.message,
    type: 'error',
    duration: 5 * 1000
  })
  return Promise.reject(error)
})

export default axios
