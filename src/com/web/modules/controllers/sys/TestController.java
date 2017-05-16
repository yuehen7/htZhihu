package com.web.modules.controllers.sys;

import static org.nutz.integration.jedis.RedisInterceptor.jedis;

import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

@Encoding(input = "UTF-8", output = "UTF-8")
@IocBean // 声明为Ioc容器中的一个Bean
@At("/platform/test") // 整个模块的路径前缀
@Ok("json:{locked:'password|createAt',ignoreNull:true}") // 忽略password和createAt属性,忽略空属性的json输出
@Fail("http:500") // 抛出异常的话,就走500页面
public class TestController {
	 private static final Log log = Logs.get();
	 

	 
	@At("/addTopic")
	@Aop("redis")
	public void addTopic() {
		try {
			String relst = jedis().set("key", "123456");
			System.out.println("relst:" +relst);
	    }catch (Exception e) {
	    	log.debug("addTopic:" + e.getMessage());
		}
	}
	
	@At("/getTopic")
	@Aop("redis")
	public void getTopic(){
		try {
			String result =  jedis().get("key");
			System.out.println("key:" + result);
	    }catch (Exception e) {
	    	log.debug("getTopic:" + e.getMessage());
		}
	}
}
