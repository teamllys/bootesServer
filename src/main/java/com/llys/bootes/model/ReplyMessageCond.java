package com.llys.bootes.model;

/**
 * 
 * Conditions of searching reply message.<br><br>
 * 
 * createTimeBegin <= message <= createTimeEnd<br>
 * message >= replyMessageIdGE <br>
 * message = replyBoardId<br>
 * message = userId<br>
 * message's count <= limit<br><br>
 * @author lonslonz
 *
 */
public class ReplyMessageCond {

    private Long replyBoardId;
    private Long userId;
    private String createTimeBegin;
    private String createTimeEnd;
    private Integer limit;
    private Long replyMessageIdGE;
    
    
    public Long getReplyBoardId() {
        return replyBoardId;
    }
    public void setReplyBoardId(Long replyBoardId) {
        this.replyBoardId = replyBoardId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getCreateTimeBegin() {
        return createTimeBegin;
    }
    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }
    public String getCreateTimeEnd() {
        return createTimeEnd;
    }
    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Long getReplyMessageIdGE() {
        return replyMessageIdGE;
    }
    public void setReplyMessageIdGE(Long replyMessageIdGE) {
        this.replyMessageIdGE = replyMessageIdGE;
    }
 
    

}
