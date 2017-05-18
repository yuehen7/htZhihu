<template>
  <div>
    <div class="row">
      <nav class="navbar navbar-fixed-top zu-top">
        <div class="container">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <a class="navbar-brand" href="#">知乎</a>
          </div>
          <div class="collapse navbar-collapse top-div">
            <div class="navbar-left ">
              <form class="navbar-form ">
                <div class="input-group">
                  <input type="text" class="top-search-input" placeholder="搜索你感兴趣的内容...">
                  <span class="input-group-btn">
                    <button class="top-search-button" type="button">Go!</button>
                  </span>
                </div>
              </form>
            </div>
            <div class="navbar-left">
              <ul class="nav navbar-nav">
                <li v-bind:class="{current:tabIndex ===0?true:false }">
                  <a @click="select(0)">首页</a>
                </li>
                <li v-bind:class="{current:tabIndex ===1?true:false }">
                  <a @click="select(1)">话题</a>
                </li>
              </ul>
            </div>
            <div class="navbar-right">
              <ul class="nav navbar-nav">
                <li>
                  <a href="#">Link</a>
                </li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown
                    <span class="caret"></span>
                  </a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="#">Action</a>
                    </li>
                    <li>
                      <a href="#">Another action</a>
                    </li>
                    <li>
                      <a href="#">Something else here</a>
                    </li>
                    <li role="separator" class="divider"></li>
                    <li>
                      <a href="#">Separated link</a>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </div>
    <div class="row">

    </div>
    <div class="row">

    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      tabIndex: 0
    }
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
</style>
