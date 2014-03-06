package com.llys.bootes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_reply_message")
public class ReplyMessage {

    private Long replyMessageId;
    private Long replyBoardId;
    private String subject;
    private String content;
    private Long parentMessageId;
    private Long userId;
    private Date createTime;
    
    @Id @GeneratedValue
    @Column(name = "reply_message_id")
    public Long getReplyMessageId() {
        return replyMessageId;
    }
    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }
    @Column(name = "reply_board_id")
    public Long getReplyBoardId() {
        return replyBoardId;
    }
    public void setReplyBoardId(Long replyBoardId) {
        this.replyBoardId = replyBoardId;
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
    @Column(name = "parent_message_id")
	public Long getParentMessageId() {
		return parentMessageId;
	}
	public void setParentMessageId(Long parentMessageId) {
		this.parentMessageId = parentMessageId;
	}
    
}
