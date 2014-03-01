package com.llys.bootes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_reply_board")
public class ReplyBoard {
    private Long replyBoardId;
    private String name;
    private Integer valid;
    private String boardType;
    private Date createTime;
    
    @Id @GeneratedValue
    @Column(name = "reply_board_id")
    public Long getReplyBoardId() {
        return replyBoardId;
    }
    public void setReplyBoardId(Long replyBoardId) {
        this.replyBoardId = replyBoardId;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "valid")
    public Integer getValid() {
        return valid;
    }
    public void setValid(Integer valid) {
        this.valid = valid;
    }
    @Column(name = "board_type")
    public String getBoardType() {
        return boardType;
    }
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
}
