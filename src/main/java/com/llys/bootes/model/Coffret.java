package com.llys.bootes.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="tb_coffret")
public class Coffret {
    
    
    private Long coffretId;
    private String subject;
    private String content;
    private Long userId;
    private Date createTime;
    private Date updateTime;
    private Long replyBoardId;
    @JsonManagedReference
    private List<Item> items;
    
    @Id @GeneratedValue
    @Column(name = "coffret_id")
    public Long getCoffretId() {
        return coffretId;
    }
    public void setCoffretId(Long coffretId) {
        this.coffretId = coffretId;
    }
    
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    @Column(name = "content")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name = "update_time", insertable = false, updatable = false)
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy="coffret", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    @Column(name = "reply_board_id")
	public Long getReplyBoardId() {
		return replyBoardId;
	}
	public void setReplyBoardId(Long replyBoardId) {
		this.replyBoardId = replyBoardId;
	}
}
