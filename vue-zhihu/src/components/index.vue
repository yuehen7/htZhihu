<template>
  <div class="container">
    <div class="index-main">
      <div class="index-main-body">
        <div class="index-header">
          <h1>知乎</h1>
          <h2 class="subtitle">与世界分享你的知识、经验和见解</h2>
        </div>
        <div class="index-tab-navs">
          <div class="navs-slider">
            <a class="active">注册</a>
            <a>登录</a>
            <span class="navs-slider-bar"></span>
            <span class="navs-slider-bar"></span>
          </div>
        </div>
      </div>
    </div>
    <div class="footer">
      <span>&copy; 2017 知乎</span>
      <span class="dot">·</span>
      <a href="/contact" class="footer-mobile-show">联系我们</a>
    </div>
  </div>
</template>

<script>
export default {

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

.index-header .logo {
  margin: 0 auto;
  width: 160px;
  height: 74px;
  background-size: contain;
}

@media (max-width :740px) {
  .index-header .logo {
    width: 96px;
    height: 44.4px;
  }
}

@media (-webkit-min-device-pixel-ratio:2),
(min-resolution:2dppx),
(min-resolution:192dpi) {}

.index-header .logo.org-logo {
  margin-top: 30px;
  width: 178px;
  height: 41px;
}

@media (max-width :740px) {
  .index-header .logo.org-logo {
    margin-top: 0;
    width: 142.4px;
    height: 32.8px;
  }
}

@media (-webkit-min-device-pixel-ratio:2),
(min-resolution:2dppx),
(min-resolution:192dpi) {}

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
  -webkit-transition: lerft .15s ease-in;
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



.index-tab-navs a.active {
  opacity: 1;
  -ms-filter: "alpha(Opacity=100)";
  color: #0f88eb;
}
</style>
