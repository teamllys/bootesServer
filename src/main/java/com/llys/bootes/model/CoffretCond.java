package com.llys.bootes.model;

public class CoffretCond {

    private Long userId;
    private String createTimeBegin;
    private String createTimeEnd;
    private Long coffretId;
    
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
    public Long getCoffretId() {
        return coffretId;
    }
    public void setCoffretId(Long coffretId) {
        this.coffretId = coffretId;
    }
}
