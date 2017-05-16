package com.web.common.core;

import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SessionBy;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Created by lppeng on 2017/5/5.
 */
@Encoding(input = "UTF-8", output = "UTF-8")
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
        "*anno", "com.web",
        "*tx", // 事务拦截 aop
        "*async",
        "*jedis"}) // 异步执行aop
@Modules(scanPackage = true,packages ="com.web")
@SetupBy(MainSetup.class)
@Ok("json:full")
@Fail("jsp:jsp.500")
//@Filters(@By(type=CrossOriginFilter.class))
@At("/api")
@SessionBy(ShiroSessionProvider.class)
public class MainModule {

}
