package com.sunruncn.unitdemo.dto.request;

import javax.annotation.Nullable;

/**
 * BOT的session信息，由BOT创建，client从上轮resonpse中取出并直接传递，不需要了解其内容。
 * 如果为空，则表示清空session（开发者判断用户意图已经切换且下一轮会话不需要继承上一轮会话中的词槽信息时可以把session置空，从而进行新一轮的会话）。
 * 传参时可只传session_id。
 * 以下为bot_session内部格式，仅供参考了解。
 * {@link ChatRequest#bot_session}
 */
public class BotSession {

    //会话ID。
    private String session_id;

    //BOT视图数据（意图、词槽解析的历史与最新结果），一般是与交互元素相关但无直接对应关系，需要推演得出的数据。这种数据在此处缓存，可节约计算开销。
    @Nullable
    private Object bot_views;
    //对话状态数据，供本地化DM使用。
    @Nullable
    private Object dialog_state;
    //历史交互序列，即历史 request/response 序列，序列的每一个元素称作一次交互（interaction），随交互进行而交替插入，格式与上述不断增长直到发生清空操作。
    @Nullable
    private Interaction[] interactions;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    @Nullable
    public Object getBot_views() {
        return bot_views;
    }

    public void setBot_views(@Nullable Object bot_views) {
        this.bot_views = bot_views;
    }

    @Nullable
    public Object getDialog_state() {
        return dialog_state;
    }

    public void setDialog_state(@Nullable Object dialog_state) {
        this.dialog_state = dialog_state;
    }

    @Nullable
    public Interaction[] getInteractions() {
        return interactions;
    }

    public void setInteractions(@Nullable Interaction[] interactions) {
        this.interactions = interactions;
    }


    /**
     * 历史交互序列，即历史 request/response 序列，序列的每一个元素称作一次交互（interaction），随交互进行而交替插入，格式与上述不断增长直到发生清空操作。
     * {@link BotSession#interactions}
     */

    public class Interaction {
        //第 i 次交互的唯一标识。
        @Nullable
        private String interaction_id;
        //第 i 次交互的 request
        @Nullable
        private Object request;
        //第 i 次交互的 response
        @Nullable
        private Object response;

        @Nullable
        public String getInteraction_id() {
            return interaction_id;
        }

        public void setInteraction_id(@Nullable String interaction_id) {
            this.interaction_id = interaction_id;
        }

        @Nullable
        public Object getRequest() {
            return request;
        }

        public void setRequest(@Nullable Object request) {
            this.request = request;
        }

        @Nullable
        public Object getResponse() {
            return response;
        }

        public void setResponse(@Nullable Object response) {
            this.response = response;
        }
    }
}
