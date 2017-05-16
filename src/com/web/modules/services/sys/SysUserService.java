package com.web.modules.services.sys;

import com.web.common.base.Service;
import com.web.modules.bean.Sys_user;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * Created by lppeng on 2017/5/5.
 */
@IocBean(args = {"refer:dao"})
public class SysUserService extends Service<Sys_user> {
    public SysUserService(Dao dao) {
        super(dao);
    }
    
	public boolean checkPassword(Sys_user user, String password) {
		String face = new Sha1Hash(password, user.getSalt()).toHex();
		return face.equalsIgnoreCase(user.getPassword());
	}

    public boolean userLogin(String email, String password) {
        Sys_user user = dao().fetch(Sys_user.class, Cnd.where("email", "=", email).and("password", "=", password));
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
    
    
    
}
