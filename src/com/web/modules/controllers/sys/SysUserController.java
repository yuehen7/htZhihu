package com.web.modules.controllers.sys;

import java.util.regex.Pattern;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.meta.Email;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.web.common.base.BaseController;
import com.web.common.base.Model;
import com.web.common.service.EmailService;
import com.web.common.util.Toolkit;
import com.web.modules.bean.Sys_user;
import com.web.modules.services.sys.SysUserService;

@Encoding(input = "UTF-8", output = "UTF-8")
@IocBean // 声明为Ioc容器中的一个Bean
@At("/platform/user") // 整个模块的路径前缀
@Ok("json:{locked:'password|createAt',ignoreNull:true}") // 忽略password和createAt属性,忽略空属性的json输出
@Fail("http:500") // 抛出异常的话,就走500页面
public class SysUserController extends BaseController{
	private static final Log log = Logs.get();
	
	protected static Pattern P_USERNAME = Pattern.compile("^[a-z][a-z0-9]{4,10}$");
	
	@Inject
	SysUserService userService;
	
	@Inject
	EmailService emailService;

	@POST
	@At("/signup")
	@Ok("json")
	public Object signup(@Param("email") String email, @Param("username") String username,
			@Param("password") String password) {
		try {
			try {
				new Email(email);
			} catch (Exception e) {
				return this.ajaxFail("Email地址不合法");
			}
			int count = userService.count(Cnd.where("email", "=", email));
			if (count != 0) {
				return this.ajaxFail("该Email已经存在");
			}
			if (Strings.isBlank(username) || !P_USERNAME.matcher(username.toLowerCase()).find()) {
				return this.ajaxFail("用户名不合法");
			}
			count = userService.count(Cnd.where("username", "=", username));
			if (count != 0) {
				return this.ajaxFail("用户名已经存在");
			}
			
			Sys_user user = new Sys_user();
			user.setUsername(username);
			user.setEmail(email);
			user.setSalt(Toolkit.getRUU16());
			user.setPassword(new Sha1Hash(password, user.getSalt()).toHex());
			user.setConfirmaction_token(Toolkit.getRUU32());
			
			String token =user.getConfirmaction_token();
			String url =  "http://localhost:8080/HtZhihu/api/platform/user/verify?token=" + token;
			String html = "<div>如果无法点击,请拷贝一下链接到浏览器中打开<p/>注册链接 %s</div>";
			html = String.format(html, url, url);
			boolean sendflag = false;
			try {
				sendflag = emailService.send(email, "注册邮件 -- " + username, html);
			} catch (Exception e) {
				throw new Exception("验证邮件发送失败~");
			}
			if(sendflag){
				userService.insert(user);
			}
			if(!sendflag){
				return this.ajaxFail("用户注册失败~");
			}
			return this.ajaxOk("用户注册成功~");
		} catch (Exception e) {
			log.error(e.getMessage());
			return this.ajaxFail("用户注册失败~");
		}
	}
	
	@GET
	@At("/verify")
	@Ok("json")
	public Object verify(@Param("token")String token) {
		try {
			if (Strings.isBlank(token)){
				return this.ajaxFail("邮箱验证token值为空~");
			}
			Sys_user user = userService.fetch(Cnd.where("confirmaction_token", "=", token));
			if(user == null){
				return this.ajaxFail("邮箱验证失败~");
			}
			user.setIs_active(1);
			user.setConfirmaction_token(Toolkit.getRUU32());
			user.setUpdated_at(user.now().toString());
			userService.update(user);
			return this.ajaxOk("邮箱验证成功~");
		} catch (Exception e) {
			// TODO: handle exception
			return this.ajaxFail("邮箱验证失败~");
		}
	}
}
