/*
 * Apply.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-27 Created
 */
package 工具类.实体类转sql;

/**
 * 审批表
 * 
 * @author genarator
 * @version 1.0 2017-05-27
 */
public class Apply2 {

    /**
	 * 序列化字段
	 */
	private static final long serialVersionUID = 1225093180743309326L;

    /**
     * 主键
     */
    private String id;

    /**
     * 外键
     */
    //
    private String recId;

    //申请类型：延期、挂起
    private String type;

    //延期最后期限
    private Long finishdate;

    //申请描述
    private String content;

    //申请原因
    private String reason;

    //申请人id
    private String userId;

    //申请人姓名
    private String userName;

    //创建时间，申请时间
    private Long createtime;

    //审核人id
    private String applyId;

    //审核人姓名
    private String applyName;

    //审核时间
    private Long applytime;

    //审核结果，通过1，不通过0
    private String applyresult;

    //处置时限 (类型 ：2工作日  1工作时) 格式 （类型-时间）多个处置时限用“|”隔开
    private String dealtime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    public String getRecId() {
        return recId;
    }
    public void setRecId(String recId) {
        this.recId = recId == null ? null : recId.trim();
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    public Long getFinishdate() {
        return finishdate;
    }
    public void setFinishdate(Long finishdate) {
        this.finishdate = finishdate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
    public Long getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
    public String getApplyId() {
        return applyId;
    }
    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }
    public String getApplyName() {
        return applyName;
    }
    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }
    public Long getApplytime() {
        return applytime;
    }
    public void setApplytime(Long applytime) {
        this.applytime = applytime;
    }
    public String getApplyresult() {
        return applyresult;
    }
    public void setApplyresult(String applyresult) {
        this.applyresult = applyresult == null ? null : applyresult.trim();
    }


    public String getDealtime() {
        return dealtime;
    }

    public void setDealtime(String dealtime) {
        this.dealtime = dealtime == null ? null : dealtime.trim();
    }
}