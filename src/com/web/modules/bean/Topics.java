package com.web.modules.bean;

import java.io.Serializable;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

import com.web.common.base.Model;

@Table("t_topics")
public class Topics extends Model implements Serializable{
	
	@Name
    @Column("id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
	
    @Column("name")
    @Comment("标题")
    @ColDefine(type = ColType.VARCHAR,width = 80)
    private String name;
    
    @Column("bio")
    @Comment("内容")
    @ColDefine(type = ColType.TEXT)
    private String bio;
    
    @Column("questions_count")
    @Comment("问题数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer questions_count;
    
    @Column("favortites_count")
    @Comment("收藏数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer favortites_count;
    
    @ManyMany(relation="t_question_topic", 
    		from="topic_id:id",
            to="question_id:id")
    private List<Questions> questions;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Integer getQuestions_count() {
		return questions_count;
	}

	public void setQuestions_count(Integer questions_count) {
		this.questions_count = questions_count;
	}

	public Integer getFavortites_count() {
		return favortites_count;
	}

	public void setFavortites_count(Integer favortites_count) {
		this.favortites_count = favortites_count;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
    
}
