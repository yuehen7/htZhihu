import Vue from 'vue'

export default {
  loadUsers: function (cb) {
    cb = arguments[arguments.length - 1]
    Vue.http.get('/api/platform/user/test').then((resp) => {
      // success callback
      return resp.json()
    }, (resp) => {
      // error callback
      console.log('服务器异常！')
      console.log(resp)
      if (cb) cb(null)
    }).then((d) => {
      if (cb) cb(d)
    })
  }
}
