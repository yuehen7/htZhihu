package com.web.modules.bean;

import java.io.Serializable;
import java.util.List;

import org.nutz.dao.entity.annotation.*;

import com.web.common.base.Model;

/**
 * Created by lppeng on 2017/5/5.
 */
@Table("t_user")
@TableIndexes(value = { @Index(name = "idx_t_user_name", fields = {"username"}, unique = true),
		@Index(name = "idx_t_user_email", fields = {"email"}, unique = true) })
public class Sys_user extends Model implements Serializable {
	
	@Name
    @Column("id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    
    @Column("username")
    @Comment("姓名")
    @ColDefine(type = ColType.VARCHAR,width = 50)
    private String username;
    
    @Column("email")
    @Comment("邮箱地址")
    @ColDefine(type = ColType.VARCHAR,width = 50)
    private String email;
    
    @Column("salt")
    @Comment("用户私钥")
    @ColDefine(type = ColType.VARCHAR,width = 50)
    private String salt;
    
    @Column("password")
    @Comment("密码")
    @ColDefine(type = ColType.VARCHAR,width = 50)
    private String password;
    
    @Column("avatar")
    @Comment("头像")
    @ColDefine(type = ColType.VARCHAR,width = 150)
    private String avatar;

    @Column("confirmaction_token")
    @Comment("验证token")
    @ColDefine(type = ColType.VARCHAR,width = 150)
    private String confirmaction_token;
    
    @Column("is_active")
    @Comment("激活标记")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer is_active;
    
    @Column("disabled")
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;
    
    @Column("questions_count")
    @Comment("问题数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer questions_count;
    
    @Column("answers_count")
    @Comment("回复数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer answers_count;
    
    @Column("comments_count")
    @Comment("评论数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer comments_count;
    
    @Column("favortites_count")
    @Comment("收藏数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer favortites_count;
    
    @Column("likes_count")
    @Comment("点赞数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer likes_count;
    
    @Column("followers_count")
    @Comment("关注数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer followers_count;

    @Column("followings_count")
    @Comment("被关注数")
    @ColDefine(type = ColType.INT)
    @Default(value = "0")
    private Integer followings_count;
    
    @Column("settings")
    @Comment("其他设置")
    @ColDefine(type = ColType.MYSQL_JSON)
    private List<String> settings;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getConfirmaction_token() {
		return confirmaction_token;
	}

	public void setConfirmaction_token(String confirmaction_token) {
		this.confirmaction_token = confirmaction_token;
	}

	public Integer getIs_active() {
		return is_active;
	}

	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getQuestions_count() {
		return questions_count;
	}

	public void setQuestions_count(Integer questions_count) {
		this.questions_count = questions_count;
	}

	public Integer getAnswers_count() {
		return answers_count;
	}

	public void setAnswers_count(Integer answers_count) {
		this.answers_count = answers_count;
	}

	public Integer getComments_count() {
		return comments_count;
	}

	public void setComments_count(Integer comments_count) {
		this.comments_count = comments_count;
	}

	public Integer getFavortites_count() {
		return favortites_count;
	}

	public void setFavortites_count(Integer favortites_count) {
		this.favortites_count = favortites_count;
	}

	public Integer getLikes_count() {
		return likes_count;
	}

	public void setLikes_count(Integer likes_count) {
		this.likes_count = likes_count;
	}

	public Integer getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(Integer followers_count) {
		this.followers_count = followers_count;
	}

	public Integer getFollowings_count() {
		return followings_count;
	}

	public void setFollowings_count(Integer followings_count) {
		this.followings_count = followings_count;
	}

	public List<String> getSettings() {
		return settings;
	}

	public void setSettings(List<String> settings) {
		this.settings = settings;
	}

}