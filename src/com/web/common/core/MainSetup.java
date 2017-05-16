package com.web.common.core;

import java.text.SimpleDateFormat;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.web.modules.bean.Sys_user;

/**
 * Created by lppeng on 2017/5/5.
 */
public class MainSetup implements Setup {
    private static final Log log = Logs.get();

    public void init(NutConfig config) {
        Ioc ioc = config.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "com.web.modules.bean", false);        
        log.info("\n _  _ _   _ _____ ______      ___  __\n" +
                "| \\| | | | |_   _|_  /\\ \\    / / |/ /\n" +
                "| .` | |_| | | |  / /  \\ \\/\\/ /| ' < \n" +
                "|_|\\_|\\___/  |_| /___|  \\_/\\_/ |_|\\_\\");
    }

    public void destroy(NutConfig config) {
        log.info("MainSetup destroy...");
    }
}
