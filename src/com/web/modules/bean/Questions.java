package com.web.modules.bean;

import java.io.Serializable;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Index;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

import com.web.common.base.Model;

@Table("t_questions")
public class Questions extends Model implements Serializable{

	@Name
    @Column("id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
	
    @Column("title")
    @Comment("标题")
    @ColDefine(type = ColType.VARCHAR,width = 80)
    private String title;
    
    @Column("body")
    @Comment("内容")
    @ColDefine(type = ColType.TEXT)
    private String body;
    
    @Column("user_id")
    @Comment("创建者ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String user_id;
    
    @Column("comments_count")
    @Comment("评论数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer comments_count;
    
    @Column("followers_count")
    @Comment("收藏数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer followers_count;
    
    @Column("answers_count")
    @Comment("回答数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer answers_count;
    
    @Column("close_comment")
    @Comment("是关闭")
    @ColDefine(type = ColType.VARCHAR, width = 1)
    @Default(value = "F")
    private String close_comment;
    
    @Column("is_hidden")
    @Comment("是否隐藏")
    @ColDefine(type = ColType.VARCHAR, width = 1)
    @Default(value = "F")
    private String is_hidden;
    
    @ManyMany(relation="t_question_topic", 
            from="question_id:id",
            to="topic_id:id")
    private List<Topics> topics;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getComments_count() {
		return comments_count;
	}

	public void setComments_count(Integer comments_count) {
		this.comments_count = comments_count;
	}

	public Integer getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(Integer followers_count) {
		this.followers_count = followers_count;
	}

	public Integer getAnswers_count() {
		return answers_count;
	}

	public void setAnswers_count(Integer answers_count) {
		this.answers_count = answers_count;
	}

	public String getClose_comment() {
		return close_comment;
	}

	public void setClose_comment(String close_comment) {
		this.close_comment = close_comment;
	}

	public String getIs_hidden() {
		return is_hidden;
	}

	public void setIs_hidden(String is_hidden) {
		this.is_hidden = is_hidden;
	}

	public List<Topics> getTopics() {
		return topics;
	}

	public void setTopics(List<Topics> topics) {
		this.topics = topics;
	}
    
}
