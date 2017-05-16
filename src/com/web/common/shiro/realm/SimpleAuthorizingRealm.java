package com.web.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.integration.shiro.SimpleShiroToken;
import org.nutz.mvc.Mvcs;

import com.web.modules.bean.Sys_user;

public class SimpleAuthorizingRealm extends AuthorizingRealm{
	
	protected Dao dao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleShiroToken upToken = (SimpleShiroToken) token;
		Sys_user user = dao().fetch(Sys_user.class,Cnd.where("email", "=", (String)upToken.getPrincipal()));
		if (user == null)
			return null;
		if (user.isDisabled())
			throw new LockedAccountException("Account [" + user.getUsername() + "] is locked.");
		return new SimpleAccount(user.getId(), user.getPassword(), getName());
	}
	
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}

	public SimpleAuthorizingRealm() {
		this(null, null);
	}

	public SimpleAuthorizingRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
		super(cacheManager, matcher);
		setAuthenticationTokenClass(SimpleShiroToken.class);
	}

	public SimpleAuthorizingRealm(CacheManager cacheManager) {
		this(cacheManager, null);
	}

	public SimpleAuthorizingRealm(CredentialsMatcher matcher) {
		this(null, matcher);
	}
	
	public Dao dao() {
		if (dao == null) {
			dao = Mvcs.ctx().getDefaultIoc().get(Dao.class, "dao");
			return dao;
		}
		return dao;
	}
}
