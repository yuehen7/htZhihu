package com.web.common.base;

import org.nutz.dao.*;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.service.EntityService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lppeng on 2017/5/5.
 */
public class Service<T> extends EntityService<T> {
    protected final static int DEFAULT_PAGE_NUMBER = 10;
    protected final static JsonFormat jsonFormat = new JsonFormat().setIgnoreNull(false);

    public Service() {
        super();
    }

    public Service(Dao dao) {
        super(dao);
    }

    /**
     * 统计符合条件的对象表条数
     *
     * @param cnd
     * @return
     */
    public int count(Condition cnd) {
        return this.dao().count(this.getEntityClass(), cnd);
    }

    /**
     * 统计对象表条数
     *
     * @return
     */
    public int count() {
        return this.dao().count(this.getEntityClass());
    }

    /**
     * 统计符合条件的记录条数
     *
     * @param tableName
     * @param cnd
     * @return
     */
    public int count(String tableName, Condition cnd) {
        return this.dao().count(tableName, cnd);
    }

    /**
     * 统计表记录条数
     *
     * @param tableName
     * @return
     */
    public int count(String tableName) {
        return this.dao().count(tableName);
    }

    public T fetch(long id) {
        return this.dao().fetch(this.getEntityClass(), id);
    }

    public T fetch(String name) {
        return this.dao().fetch(this.getEntityClass(), name);
    }

    public T fetchLinks(T t, String name) {
        return this.dao().fetchLinks(t, name);
    }

    public T fetchLinks(T t, String name, Condition cnd) {
        return this.dao().fetchLinks(t, name, cnd);
    }

    public int delete(String name) {
        return this.dao().delete(this.getEntityClass(), name);
    }

    public T insert(T t) {
        return this.dao().insert(t);
    }

    public void insert(String tableName, Chain chain) {
        this.dao().insert(tableName, chain);
    }

    public T fastInsert(T t) {
        return this.dao().fastInsert(t);
    }

    public int update(Object obj) {
        return this.dao().update(obj);
    }

    /**
     * 忽略值为null的字段
     *
     * @param obj
     * @return
     */
    public int updateIgnoreNull(Object obj) {
        return this.dao().updateIgnoreNull(obj);
    }

    /**
     * 部分更新实体表
     *
     * @param chain
     * @param cnd
     * @return
     */
    public int update(Chain chain, Condition cnd) {
        return this.dao().update(this.getEntityClass(), chain, cnd);
    }

    /**
     * 部分更新表
     *
     * @param tableName
     * @param chain
     * @param cnd
     * @return
     */
    public int update(String tableName, Chain chain, Condition cnd) {
        return this.dao().update(tableName, chain, cnd);
    }

    public int delete(long id) {
        return this.dao().delete(this.getEntityClass(), id);
    }

    public int delete(int id) {
        return this.dao().delete(this.getEntityClass(), id);
    }

    public int getMaxId() {
        return this.dao().getMaxId(this.getEntityClass());
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void delete(Integer[] ids) {
        this.dao().clear(getEntityClass(), Cnd.where("id", "in", ids));
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void delete(Long[] ids) {
        this.dao().clear(getEntityClass(), Cnd.where("id", "in", ids));
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void delete(String[] ids) {
        this.dao().clear(getEntityClass(), Cnd.where("id", "in", ids));
    }

    /**
     * 伪删除
     *
     * @param id
     * @return
     */
    public int vDelete(String id) {
        return this.dao().update(this.getEntityClass(), Chain.make("delTag", true), Cnd.where("id", "=", id));
    }

    /**
     * 批量伪删除
     *
     * @param ids
     * @return
     */
    public int vDelete(String[] ids) {
        return this.dao().update(this.getEntityClass(), Chain.make("delTag", true), Cnd.where("id", "in", ids));
    }

    /**
     * 通过LONG主键获取部分字段值
     *
     * @param fieldName
     * @param id
     * @return
     */
    public T getField(String fieldName, long id) {
        return Daos.ext(this.dao(), FieldFilter.create(getEntityClass(), fieldName))
                .fetch(getEntityClass(), id);
    }

    /**
     * 通过INT主键获取部分字段值
     *
     * @param fieldName
     * @param id
     * @return
     */
    public T getField(String fieldName, int id) {
        return Daos.ext(this.dao(), FieldFilter.create(getEntityClass(), fieldName))
                .fetch(getEntityClass(), id);
    }


    /**
     * 通过NAME主键获取部分字段值
     *
     * @param fieldName 支持通配符 ^(a|b)$
     * @param name
     * @return
     */
    public T getField(String fieldName, String name) {
        return Daos.ext(this.dao(), FieldFilter.create(getEntityClass(), fieldName))
                .fetch(getEntityClass(), name);
    }

    /**
     * 通过NAME主键获取部分字段值
     *
     * @param fieldName 支持通配符 ^(a|b)$
     * @param cnd
     * @return
     */
    public T getField(String fieldName, Condition cnd) {
        return Daos.ext(this.dao(), FieldFilter.create(getEntityClass(), fieldName))
                .fetch(getEntityClass(), cnd);
    }

    /**
     * 查询获取部分字段
     *
     * @param fieldName 支持通配符 ^(a|b)$
     * @param cnd
     * @return
     */
    public List<T> query(String fieldName, Condition cnd) {
        return Daos.ext(this.dao(), FieldFilter.create(getEntityClass(), fieldName))
                .query(getEntityClass(), cnd);
    }

    /**
     * 自定义SQL统计
     *
     * @param sql
     * @return
     */
    public int count(Sql sql) {
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql)
                    throws SQLException {
                int rsvalue = 0;
                if (rs != null && rs.next()) {
                    rsvalue = rs.getInt(1);
                }
                return rsvalue;
            }
        });
        this.dao().execute(sql);
        return sql.getInt();
    }

    /**
     * 自定义SQL返回Record记录集，Record是个MAP但不区分大小写
     * 别返回Map对象，因为MySql和Oracle中字段名有大小写之分
     *
     * @param sql
     * @return
     */
    public List<Record> list(Sql sql) {
        sql.setCallback(Sqls.callback.records());
        this.dao().execute(sql);
        return sql.getList(Record.class);

    }

    /**
     * 自定义sql获取map key-value
     *
     * @param sql
     * @return
     */
    public Map getMap(Sql sql) {
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql)
                    throws SQLException {
                Map<String, String> map = new HashMap<>();
                while (rs.next()) {
                    map.put(Strings.sNull(rs.getString(1)), Strings.sNull(rs.getString(2)));
                }
                return map;
            }
        });
        this.dao().execute(sql);
        return sql.getObject(Map.class);
    }
}
