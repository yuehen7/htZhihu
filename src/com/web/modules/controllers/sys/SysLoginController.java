package com.web.modules.controllers.sys;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.integration.shiro.SimpleShiroToken;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.web.common.base.BaseController;
import com.web.modules.bean.Sys_user;
import com.web.modules.services.sys.SysUserService;

@Encoding(input = "UTF-8", output = "UTF-8")
@IocBean // 声明为Ioc容器中的一个Bean
@At("/platform/login") // 整个模块的路径前缀
@Ok("json:{locked:'password|createAt',ignoreNull:true}") // 忽略password和createAt属性,忽略空属性的json输出
@Fail("http:500") // 抛出异常的话,就走500页面
@AdaptBy(type=JsonAdaptor.class)
public class SysLoginController extends BaseController{
	private static final Log log = Logs.get();
	@Inject
	SysUserService userService;

	@POST
	@At("/login")
	@Ok("json")
	public Object login(@Param("email") String email, @Param("password") String password) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				return this.ajaxOk("该用户已登陆");
			}
			Sys_user user = userService.fetch(Cnd.where("email", "=", email));
			if(user == null){
				return this.ajaxFail("请输入有效的邮箱~");
			}
			if(user.getIs_active() != 1){
				return this.ajaxFail("请先进行邮箱验证~");
			}
			if (!userService.checkPassword(user, password)) {
				return this.ajaxFail("用户密码错误~");
			}else{
				subject.login(new SimpleShiroToken(user.getId()));
			}
			return this.ajaxOk(user.getId());
		} catch (Exception e) {
			log.error("" + e.getMessage());
			return this.ajaxFail("用户登陆失败~");
		}
	}
	
    @At
    public void logout() {
        try {
        	SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            log.debug("Logout error", e);
        }
    }
}
