package com.sunruncn.unitdemo.dto.request;

import javax.annotation.Nullable;

/**
 * Created by root on 18-7-23.
 */

public class Chat1Request {
    //与BOT对话的用户id（如果BOT客户端是用户未登录状态情况下对话的，也需要尽量通过其他标识（比如设备id）来唯一区分用户），方便今后在平台的日志分析模块定位分析问题、从用户维度统计分析相关对话情况。
    private String user_id;
    //本轮请求query（用户说的话），详情见【参数详细说明】
    private String query;
    //本轮请求query的附加信息。
    private QueryInfoRequest query_info;
    //系统自动发现不置信意图/词槽，并据此主动发起澄清确认的敏感程度。取值范围：0(关闭)、1(中敏感度)、2(高敏感度)。取值越高BOT主动发起澄清的频率就越高，建议值为1。
    private int bernard_level = 1;
    /**
     * json格式字符串
     * client希望传给BOT的本地信息，以一组K-V形式保存。
     * 考虑兼容性，这里预定义两个key
     * 如果要使用这个字段，必须包含这两个key（值可以分别设为空串和空list），
     * 然后再加入自己的K-V（自定义KV的Value类型，当前只支持int、float和string）
     * <p>
     * client_results	string(json)	预留字段
     * candidate_options	list<object>	上一轮是client反问用户时有值，存储client端提供的用户可选答案候选，每个候选对应一个object。
     * candidate_options对应{@link CandidateOptions}
     */
    private String client_session;
    /**
     * 干预信息。详情见【参数详细说明】
     * json格式字符串
     * {@link Updates}
     */
    @Nullable
    private String updates;
    /**
     * 影响UNIT内部行为的超参数,支持以string为key，以double、int、string为value的KV。
     * {@link HyperParamsRequest}
     */
    @Nullable
    private HyperParamsRequest hyper_params;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public QueryInfoRequest getQuery_info() {
        return query_info;
    }

    public void setQuery_info(QueryInfoRequest query_info) {
        this.query_info = query_info;
    }

    public int getBernard_level() {
        return bernard_level;
    }

    public void setBernard_level(int bernard_level) {
        this.bernard_level = bernard_level;
    }

    @Nullable
    public String getClient_session() {
        return client_session;
    }

    public void setClient_session(@Nullable String client_session) {
        this.client_session = client_session;
    }

    @Nullable
    public String getUpdates() {
        return updates;
    }

    public void setUpdates(@Nullable String updates) {
        this.updates = updates;
    }

    @Nullable
    public HyperParamsRequest getHyper_params() {
        return hyper_params;
    }

    public void setHyper_params(@Nullable HyperParamsRequest hyper_params) {
        this.hyper_params = hyper_params;
    }


    /**
     * mod_history 本轮query的修改记录，开发者请求时，不需要带这个字段。该字段在本轮request记入bot_session后自动更新。
     * 本轮请求query的附加信息。
     */
    public static class QueryInfoRequest {


        /**
         * "TEXT"，请求信息类型，当前为固定值TEXT，表示客户端请求的内容类型是一段文本。
         * 后续会支持"EVENT"类型,让客户端在BOT里直接触发一个意图或填充一些词槽等操作，比如客户端发现用户打开对话窗口了
         * 可以由BOT主动触发一个请求 给用户问个好、介绍一下自己能解决什么问题。
         * <p>
         * 为EVENT时，为一组K-V（json），且其中必须包含一个名为『event_name』的key，其他自便。
         * 我们预定义三种event_name及对应的数据格式： "CHOICE","@UNIT"和"RESET"
         * CHOICE：用户点击了一个界面选择项，比如用户选择BOT让用户澄清的多个选项中的一个，BOT：有两首您想听的歌，你想听哪首--1……，2……；USER：第1首。
         * \@UNIT：发起对上轮意图/词槽/问答对的干预
         * RESET：清空session，即重置会话状态，会清空当前BOT已存储的意图与词槽信息。
         */
        private String type = "TEXT";
        //请求信息来源，可选值："ASR","KEYBOARD"。ASR为语音输入，KEYBOARD为键盘文本输入，ASR输入的UNIT平台内置了异常信息纠错机制，尝试解决语音输入中的一些常见错误。
        private String source = "KEYBOARD";
        //请求信息来源若为ASR，该字段为ASR候选信息。（如果调用百度语音的API会有词信息，BOT可能会参考该候选信息做综合判断处理。）
        @Nullable
        private AsrCandidates[] asr_candidates;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @Nullable
        public AsrCandidates[] getAsr_candidates() {
            return asr_candidates;
        }

        public void setAsr_candidates(@Nullable AsrCandidates[] asr_candidates) {
            this.asr_candidates = asr_candidates;
        }

        public class AsrCandidates {
            //语音输入候选文本
            @Nullable
            private String text;
            //语音输入候选置信度
            @Nullable
            private float confidence;

            @Nullable
            public String getText() {
                return text;
            }

            public void setText(@Nullable String text) {
                this.text = text;
            }

            @Nullable
            public float getConfidence() {
                return confidence;
            }

            public void setConfidence(@Nullable float confidence) {
                this.confidence = confidence;
            }
        }
    }


    /**
     * 影响UNIT内部行为的超参数,支持以string为key，以double、int、string为value的KV。
     */
    public static class HyperParamsRequest {
        //slu运行级别，默认值为1，值域1、2、3，级别越高模型的泛化能力越强，但越有可能误召回。查看详细说明
        @Nullable
        private int slu_level;
        //slu阈值，默认值为1.0，值域0.0~1.0，越高表示召回的阈值越高，避免误召回。在slu_leve1=2、3时起作用。查看详细说明
        @Nullable
        private double slu_threshold;

        @Nullable
        public int getSlu_level() {
            return slu_level;
        }

        public void setSlu_level(@Nullable int slu_level) {
            this.slu_level = slu_level;
        }

        @Nullable
        public double getSlu_threshold() {
            return slu_threshold;
        }

        public void setSlu_threshold(@Nullable double slu_threshold) {
            this.slu_threshold = slu_threshold;
        }
    }
}



