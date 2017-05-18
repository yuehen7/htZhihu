import Vue from 'vue'

export default {
  login: function (email, pass, cb) {
    cb = arguments[arguments.length - 1]
    if (localStorage.token) {
      if (cb) cb(true)
      return
    }
    Vue.axios.post('api/platform/login/login', { email: email, password: pass }).then((resp) => {
      return resp.data
    }, (resp) => {
      console.log('服务器异常！')
      return { ok: false, msg: '服务器异常~' }
    }).then((res) => {
      if (res.ok) {
        localStorage.token = res.msg
      }
      if (cb) cb(res)
    })
  },
  getToken: function () {
    return localStorage.token
  },
  loggedIn: function () {
    return !!localStorage.token
  },
  logout: function (cb) {
    delete localStorage.token
    Vue.axios.post('api/platform/login/logout')
    if (cb) cb()
  },
  signup: function (parm, cb) {
    Vue.axios.post('api/platform/user/signup', parm).then((resp) => {
      return resp.data
    }, (resp) => {
      console.log('服务器异常！')
      return { ok: false, msg: '服务器异常~' }
    }).then((res) => {
      if (cb) cb(res)
    })
  }
}
