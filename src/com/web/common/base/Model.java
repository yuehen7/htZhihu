package com.web.common.base;

import java.io.Serializable;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.random.R;

/**
 * Created by lppeng on 2017/5/5.
 */
public abstract class Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Comment("创建时间")
    @Prev(els = @EL("$me.now()"))
    @ColDefine(type = ColType.TIMESTAMP)
    private String created_at;

    @Column
    @Comment("修改时间")
    @Prev(els = @EL("$me.now()"))
    @ColDefine(type = ColType.TIMESTAMP)
    private String updated_at;
    
	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
    
    public String toString() {
        return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }
    
    public Timestamp now() {
    	Timestamp now = new Timestamp(System.currentTimeMillis());
        return now;
    }
    
    public String uuid() {
        return R.UU32().toLowerCase();
    }
}
