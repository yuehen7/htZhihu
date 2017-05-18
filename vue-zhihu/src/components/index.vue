<template>
  <div>
    <div class="index-main">
      <div class="index-main-body">
        <div class="index-header">
          <h1 class="title">知乎</h1>
          <h2 class="subtitle">与世界分享你的知识、经验和见解</h2>
        </div>
        <div class="desk-front sign-flow clearfix sign-flow-simple">
          <div class="index-tab-navs">
            <div class="navs-slider" v-bind:data-active-index="tabIndex">
              <a href="#" v-bind:class="{ active: tabIndex ===0?true:false }" @click="select(0)">登陆</a>
              <a href="#" v-bind:class="{ active: tabIndex ===1?true:false }" @click="select(1)">注册</a>
              <span class="navs-slider-bar"></span>
            </div>
          </div>
          <div class="view" v-if="tabIndex ===0">
            <form @submit.prevent="signin">
              <div class="group-inputs">
                <div class="input-wrapper">
                  <input type="email" id="email" name="email" class="form-control" placeholder="邮箱地址" required autofocus v-model="user.email">
                </div>
                <div class="input-wrapper">
                  <input type="password" id="password" name="password" class="form-control" placeholder="密码" required v-model="user.password">
                </div>
              </div>
              <div class="button-wrapper">
                <button class="" type="submit" id="login_button">登陆</button>
              </div>
            </form>
          </div>
          <div class="view" v-else>
            <form @submit.prevent="signup">
              <div class="group-inputs">
                <div class="input-wrapper">
                  <input type="email" id="email" name="email" class="form-control" placeholder="邮箱地址" required autofocus v-model="user.email">
                </div>
                <div class="input-wrapper">
                  <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus v-model="user.username">
                </div>
                <div class="input-wrapper">
                  <input type="password" id="password" name="password" class="form-control" placeholder="密码" required v-model="user.password">
                </div>
                <div class="input-wrapper">
                  <input type="password" id="password_confirmation" name="password_confirmation" class="form-control" placeholder="确认密码" required>
                </div>
              </div>
              <div class="button-wrapper">
                <button class="" type="submit" id="login_button">注册</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="footer">
      <span>&copy; 2017 知乎</span>
      <span class="dot">·</span>
      <a href="/contact" class="footer-mobile-show">联系我们</a>
    </div>
    <alert type="warning" class="alertTop" :closable="true" :duration="3000" v-if="showMsg" @close="showMsg=false">
      <p>{{msg}}</p>
    </alert>
  </div>
</template>

<script>
import auth from '../service/auth'
import { Alert } from 'uiv'
export default {
  data: function () {
    return {
      tabIndex: 0,
      user: {
        username: '',
        password: '',
        email: ''
      },
      showMsg: false,
      msg: ''
    }
  },
  components: {
    'alert': Alert
  },
  methods: {
    select: function (val) {
      this.tabIndex = val
    },
    signin: function () {
      auth.login(this.user.email, this.user.password, (re) => {
        if (re.ok) {
          alert(re.msg)
        } else {
          this.showMsg = true
          this.msg = re.msg
        }
      })
    },
    signup: function () {
      var parm = JSON.stringify(this.user)
      auth.signup(parm, (re) => {
        if (re.ok) {
          this.showMsg = true
          this.msg = '用户注册成功，请前往您的邮箱进行验证~'
        } else {
          this.showMsg = true
          this.msg = re.msg
        }
      })
    }
  }
}
</script>

<style>
.index-main {
  min-height: 100%;
  margin-bottom: -42px;
}

@media (max-width :740px) {
  .index-main {
    margin-bottom: -102px;
  }
}

.index-main-body {
  margin: auto;
  margin-top: 80px;
  padding: 30px 0 102px;
  width: 300px;
}

@media (min-width :741px) {
  .index-main {
    text-align: center;
    white-space: nowrap;
    height: 100%;
    min-height: 720px;
  }

  .index-main:before {
    content: '';
    display: inline-block;
    height: 100%;
    vertical-align: middle;
    margin-right: -.25em;
  }

  .index-main-body {
    padding: 0;
    min-height: 590px;
    display: inline-block;
    vertical-align: middle;
    text-align: left;
    white-space: normal;
  }
}

@media (min-width :741px) and (max-height:600px) {
  .index-main {
    height: auto;
    min-height: auto;
  }
}

.index-header {
  text-align: center;
}

.index-header .title {
  margin: 30px 0 20px;
  font-weight: 400;
  font-size: 55px;
  line-height: 1;
}

.index-header .subtitle {
  margin: 30px 0 20px;
  font-weight: 400;
  font-size: 18px;
  line-height: 1;
}

.footer {
  font-size: 12px;
  line-height: 21px;
  text-align: center;
}

.footer-desktop-hide {
  display: none;
}

@media (max-width :740px) {
  .footer {
    padding-bottom: 60px;
    line-height: 42px;
  }

  .footer :not(.footer-mobile-show) {
    display: none;
  }

  .footer .footer-desktop-hide {
    display: inline-block;
  }
}

.footer span,
.footer a {
  color: #aebdc9;
}

.footer span a {
  color: #698ebf;
}

.footer .dot {
  margin: 0 3px;
}

#particles {
  position: absolute;
  top: 0;
  z-index: -1;
  width: 100%;
  height: 100%;
}

#particles canvas {
  display: block;
}

.index-tab-navs {
  margin-bottom: 10px;
  font-size: 18px;
  text-align: center;
}

.index-tab-navs .navs-slider {
  position: relative;
  display: inline-block;
}

.index-tab-navs .navs-slider .navs-slider-bar {
  position: absolute;
  left: 0;
  bottom: 0;
  margin: 0 .8em;
  width: 2.4em;
  height: 2px;
  background: #0f88eb;
  -webkit-transition: left .15s ease-in;
  transition: left .15s ease-in;
}

.index-tab-navs .navs-slider[data-active-index="1"] .navs-slider-bar {
  left: 4em;
}

.index-tab-navs a {
  float: left;
  width: 4em;
  line-height: 35px;
  opacity: .7;
  -ms-filter: "alpha(Opacity=70)";
  -webkit-transition: opacity .15s, color .15s;
  transition: opacity .15s, color .15s;
}

.index-tab-navs a:hover {
  opacity: 1;
  -ms-filter: "alpha(Opacity=100)";
  text-decoration: none;
}

.index-tab-navs a.active {
  opacity: 1;
  -ms-filter: "alpha(Opacity=100)";
  color: #0f88eb;
  text-decoration: none;
}

.sign-flow {
  text-align: center;
}

.sign-flow .view {
  float: none;
  margin: auto;
  width: 300px;
  text-align: left;
}

.sign-flow .view .group-inputs {
  padding: 1px 0;
  border: 1px solid #d5d5d5;
  border-radius: 3px;
}

.sign-flow .view .input-wrapper {
  position: relative;
  margin: 0;
  overflow: hidden;
}

.sign-flow .view .input-wrapper+.input-wrapper {
  border-top: 1px solid #e8e8e8;
}

.sign-flow .view .input-wrapper input {
  padding: 1em .8em;
  width: 100%;
  box-sizing: border-box;
  height: 100%;
}

.sign-flow .view .input-wrapper input,
.sign-flow .view .input-wrapper input:focus {
  border: 0;
  border-radius: 0;
  box-shadow: none;
  background: #fff;
}

.sign-flow .view .input-wrapper input:-webkit-autofill {
  -webkit-box-shadow: 0 0 0 30px #fff inset;
  -webkit-text-fill-color: #555;
}

.sign-flow a {
  color: inherit;
}

.sign-flow .title {
  display: none;
}

.sign-flow .view .button-wrapper {
  margin-top: 18px;
}

.sign-flow .view .button-wrapper button {
  display: block;
  padding: 0;
  width: 100%;
  font-size: 15px;
  background: #0f88eb;
  box-shadow: none;
  border: 0;
  border-radius: 3px;
  line-height: 41px;
  color: #fff;
  outline: 0;
  cursor: pointer;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}


.alertTop {
    width: 350px;
    position: fixed;
    top: 50px;
    margin: 0 auto;
    left: 0;
    right: 0;
    z-index: 1050;
}
</style>
