package com.sunruncn.unitdemo.dto.request;

/**
 * {@link Chat1Request#client_session}
 */

public class CandidateOptions {
    /**
     * optional,是否对当前结果进行记录，解析相同query时，默认选择同一结果
     */
    private boolean remember;

    /**
     * 一组kv，记录该候选的若干属性及属性值，attr_name可自行设定,如： {"movie_name":"花样年华", "year":"1998", "actor":"梁朝伟"}
     */
    private String attributes;
    /**
     * 用户提供的候选项所对应的词槽，即选择成功以后会加入到解析结果中的词槽信息,如：{"user_movie": "花样年华1998"}
     */
    private String slotUpdates;

    public boolean getRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getSlotUpdates() {
        return slotUpdates;
    }

    public void setSlotUpdates(String slotUpdates) {
        this.slotUpdates = slotUpdates;
    }
}