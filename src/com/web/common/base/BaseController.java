package com.web.common.base;

import org.nutz.lang.util.NutMap;

public class BaseController {
	
	protected NutMap ajaxOk(Object data) {
		return new NutMap().setv("ok", true).setv("data", data);
	}
	
	protected NutMap ajaxFail(String msg) {
		return new NutMap().setv("ok", false).setv("msg", msg);
	}
}
