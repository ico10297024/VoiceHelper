package com.sunruncn.unitdemo.dto.response;

/**
 * Created by root on 18-7-23.
 */

public class Chat1Response {
    //BOT唯一ID
    private int bot_id;
    //日志唯一ID（用户与BOT的一问一答为一次interaction，其中用户每说一次对应有一个log_id）
    private int log_id;
    //同请求参数
    private String version;
    /**
     * session信息
     * {@link com.sunruncn.unitdemo.dto.request.ChatRequest#bot_session}
     */
    private String bot_session;
    /**
     * 为本轮请求+应答之组合，生成的id
     */
    private String interaction_id;
    /**
     * 本轮应答体
     */
    private Chat2Response response;
    //时间戳
    private String timestamp;

    public int getBot_id() {
        return bot_id;
    }

    public void setBot_id(int bot_id) {
        this.bot_id = bot_id;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBot_session() {
        return bot_session;
    }

    public void setBot_session(String bot_session) {
        this.bot_session = bot_session;
    }

    public String getInteraction_id() {
        return interaction_id;
    }

    public void setInteraction_id(String interaction_id) {
        this.interaction_id = interaction_id;
    }

    public Chat2Response getResponse() {
        return response;
    }

    public void setResponse(Chat2Response response) {
        this.response = response;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
