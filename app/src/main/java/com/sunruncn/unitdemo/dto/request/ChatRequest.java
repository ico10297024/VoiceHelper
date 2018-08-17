package com.sunruncn.unitdemo.dto.request;

/**
 * Created by root on 18-7-23.
 */

public class ChatRequest {
    //当前api版本对应协议版本号为2.0，固定值
    private String version = "2.0";
    //BOT唯一标识，在『我的BOT』的BOT列表中第一列数字即为bot_id
    private String bot_id = "9550";
    //开发者需要在客户端生成的唯一id，用来定位请求，响应中会返回该字段。对话中每轮请求都需要一个log_id。
    private String log_id;
    //本轮请求体
    private Chat1Request request;
    /**
     * BOT的session信息，由BOT创建，client从上轮resonpse中取出并直接传递，不需要了解其内容。
     * 如果为空，则表示清空session（开发者判断用户意图已经切换且下一轮会话不需要继承上一轮会话中的词槽信息时可以把session置空，从而进行新一轮的会话）。
     * 传参时可只传session_id。
     * 以下为bot_session内部格式，仅供参考了解。
     * json字符串
     * {@link BotSession}
     */
    private String bot_session = "";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBot_id() {
        return bot_id;
    }

    public void setBot_id(String bot_id) {
        this.bot_id = bot_id;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public Chat1Request getRequest() {
        return request;
    }

    public void setRequest(Chat1Request request) {
        this.request = request;
    }

    public String getBot_session() {
        return bot_session;
    }

    public void setBot_session(String bot_session) {
        this.bot_session = bot_session;
    }
}
