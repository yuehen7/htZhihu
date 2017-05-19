<template>
  <div class="container">
    <nav class="navbar navbar-fixed-top zu-top">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">知乎</a>
        </div>
        <div class="collapse navbar-collapse top-div">
          <div class="navbar-left top-search-form">
            <form class="navbar-form ">
              <div class="input-group">
                <input type="text" class="top-search-input" placeholder="搜索你感兴趣的内容...">
                <span class="input-group-btn">
                  <button class="top-search-button" type="button">Go!</button>
                </span>
              </div>
            </form>
          </div>
          <div class="navbar-left top-menu">
            <ul class="nav navbar-nav">
              <li v-bind:class="{current:tabIndex ===0?true:false }">
                <a @click="select(0)">首页</a>
              </li>
              <li v-bind:class="{current:tabIndex ===1?true:false }">
                <a @click="select(1)">话题</a>
              </li>
              <li v-bind:class="{current:tabIndex ===2?true:false }">
                <a @click="select(2)">消息</a>
              </li>
            </ul>
          </div>
          <div class="navbar-left">
            <button class="top-add-question" id="top-add-question" @click="open1=true">提问</button>
          </div>
          <div class="navbar-right">
            <dropdown class="top-user-info">
              <button data-role="trigger" class="dropdown-toggle" type="button">
                <span>张三</span>
                <span class="caret"></span>
              </button>
              <template slot="dropdown">
                <li>
                  <a href="#">我的主页</a>
                </li>
                <li>
                  <a href="#">私信</a>
                </li>
                <li>
                  <a href="#">设置</a>
                </li>
                <li role="separator" class="divider"></li>
                <li>
                  <a href="#">退出</a>
                </li>
              </template>
            </dropdown>
          </div>
        </div>
      </div>
    </nav>
    <div class="row" style="height:70px">
      <modal v-model="open1" :backdrop="false" :footer="false">
        <span slot="title">
          <span class="modal-dialog-title-text">提问</span>
        </span>
        <div>
          <form @submit.prevent="signin" style="height:100%">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="">
            </div>
            <div class="form-group">
              <vue-editor v-model="content" :editorToolbar="customToolbar" hei></vue-editor>
            </div>
            <div class="form-group">
              <label for="topic">选择话题:</label>
              <div class="input-group">
                <span class="input-group-addon" id="sizing-topic">Go</span>
                <input type="text" class="form-control" id="topic" placeholder="搜索话题">
              </div>
            </div>
            <div class="form-footer">
              <a @click="open1=false" style="margin-right: 15px;">取消</a>
              <button type="submit" class="btn btn-primary">发布</button>
            </div>
          </form>
        </div>
      </modal>
    </div>
    <div class="row">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { Dropdown, Modal } from 'uiv'
import { VueEditor } from 'vue2-editor'

export default {
  data: function () {
    return {
      tabIndex: 0,
      open1: false,
      content: '',
      customToolbar: [
        ['bold', 'italic', 'underline'],
        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
        ['image', 'code-block']
      ]
    }
  },
  components: {
    'dropdown': Dropdown,
    'modal': Modal,
    'vue-editor': VueEditor
  },
  methods: {
    select: function (val) {
      this.tabIndex = val
    }
  }
}
</script>

<style>
.zu-top {
  border-color: #f7fafc;
  background: linear-gradient(to bottom, #086ed5, #055db5);
  border-bottom: 1px solid #044e97;
  box-shadow: 0 1px 2px rgba(0, 0, 0, .25), inset 0 1px 0 rgba(255, 255, 255, .15);
}

.zu-top a {
  color: #fff;
}

.zu-top a:hover {
  color: #fff;
}

.navbar-header .navbar-brand {
  font-size: 30px;
}

.top-div {
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

.top-search-form,
.top-menu {
  margin-left: 20px;
  margin-right: 30px;
}

.top-search-input {
  padding: 7px 50px 7px 10px;
  width: 350px;
  color: #49525c;
  font-size: 15px;
  line-height: 17px;
  background-color: #e1eaf2;
  border: 1px solid #045bb2;
  border-radius: 4px;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, .2), 0 1px 0 rgba(255, 255, 255, .1);
  outline: 0;
  box-sizing: border-box;
  -webkit-transition: all .2s ease-in-out;
  transition: all .2s ease-in-out;
}

.top-search-button {
  position: absolute;
  top: 0;
  right: 0;
  width: 40px;
  height: 100%;
  background: #0e78e7;
  background: -webkit-linear-gradient(top, #0e7bef, #0d73da);
  background: linear-gradient(to bottom, #0e7bef, #0d73da);
  border: 1px solid #045bb2;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, .05), inset 0 1px 1px rgba(255, 255, 255, .15);
  box-sizing: border-box;
  outline: 0;
  cursor: pointer;
  font-size: 14px;
  color: #fff;
  text-align: center;
}

.nav>li>a:active,
.nav>li>a:visited {
  color: #eee;
}

.nav>li>a:hover {
  text-decoration: none;
  /*background: linear-gradient(to bottom, #086ed5, #055db5);*/
  background: #075fb6;
  background: -webkit-linear-gradient(top, #055fb8, #095fb3);
  background: linear-gradient(to bottom, #055fb8, #095fb3);
  box-shadow: inset 0 0 2px rgba(0, 0, 0, .12);
  cursor: pointer
}

.current {
  background: #075fb6;
  background: -webkit-linear-gradient(top, #055fb8, #095fb3);
  background: linear-gradient(to bottom, #055fb8, #095fb3);
  box-shadow: inset 0 0 2px rgba(0, 0, 0, .12);
}

.top-add-question {
  float: right;
  width: 66px;
  height: 32px;
  line-height: 30px;
  margin-top: 8px;
  margin-left: 80px;
  border: 1px solid #045bb2;
  border-radius: 4px;
  background: #0e78e7;
  background: -webkit-linear-gradient(top, #0e7bef, #0d73da);
  background: linear-gradient(to bottom, #0e7bef, #0d73da);
  box-shadow: 0 1px 2px rgba(0, 0, 0, .05), inset 0 1px 1px rgba(255, 255, 255, .15);
  color: #fff;
  font-size: 14px;
  text-align: center;
  text-shadow: 0 1px 1px rgba(0, 0, 0, .12);
  outline: 0;
  cursor: pointer;
  -webkit-transition: all .2s ease-in-out;
  transition: all .2s ease-in-out;
}

.top-add-question:active {
  background: #096ed5;
  background: -webkit-gradient(linear, left top, left bottom, from(#0e7ceb), to(#0c6fd3));
  background: -moz-linear-gradient(top, #0e7ceb, #0c6fd3);
  border: 1px solid #0659ac;
  box-shadow: 0 1px 0 rgba(255, 255, 255, .06), inset 0 1px 2px rgba(0, 0, 0, .2);
}

.top-user-info {
  margin-top: 10px;
  float: left;
  position: relative;
  min-width: 120px;
}

.top-user-info>button,
.top-user-info>button:focus,
.top-user-info>button:hover,
.top-user-info>button:active {
  background: linear-gradient(to bottom, #086ed5, #055db5);
  border: none;
  color: #fff;
  outline: none;
  text-align: left;
}

.top-user-info>.dropdown-menu {
  min-width: 120px;
}

.modal-content>.modal-header {
  position: relative;
  height: auto;
  margin: -1px -1px 0;
  padding: 10px 15px;
  line-height: 14px;
  background: #1D7FE2;
  background: -webkit-linear-gradient(top, #1D7FE2, #0865C2);
  background: linear-gradient(to bottom, #1D7FE2, #0865C2);
  border: 1px solid #0D6EB8;
  border-radius: 6px 6px 0 0;
  box-shadow: 0 1px 0 rgba(255, 255, 255, .1) inset, 0 1px 1px rgba(0, 0, 0, .2);
}

.modal-header span {
  cursor: pointer;
}

.modal-header .modal-title {
  margin: 0;
  line-height: 1;
}

.modal-dialog-title-text {
  color: #fff;
  text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
  font-size: 15px;
  font-weight: 700;
}

#quill-container {
  max-height: 100px;
}

.form-footer {
  padding-top: 10px;
  text-align: right;
  border-top: 1px solid #e5e5e5;
}

.form-footer a:hover {
  text-decoration: none;
  cursor: pointer;
}
</style>
