package com.sunruncn.unitdemo.dto.response;

/**
 * Created by root on 18-7-24.
 */

public class Actions {
    //动作置信度
    private float confidence;
    //动作ID
    private String action_id;
    //应答话术
    private String say;
    /**
     * 用户自定义应答，如果action_type为event，对应事件定义在此处
     * json
     */
    private String custom_reply;
    /**
     * 类型，具体有以下几种:clarify/satisfy/guide/faqguide/understood(理解达成)/failure(理解失败)/chat(聊天话术)/event
     * (触发事件，在"对话意图--场景bot回应--答复"选择了"执行函数"将返回event类型)
     */
    private String type;
    /**
     * 泛澄清』时，即clarify/guide/faqguide时有效
     */
    private RefineDetail refine_detail;

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }

    public String getCustom_reply() {
        return custom_reply;
    }

    public void setCustom_reply(String custom_reply) {
        this.custom_reply = custom_reply;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RefineDetail getRefine_detail() {
        return refine_detail;
    }

    public void setRefine_detail(RefineDetail refine_detail) {
        this.refine_detail = refine_detail;
    }

    /**
     * 泛澄清』时，即clarify/guide/faqguide时有效
     * {@link Actions#refine_detail}
     */
    public static class RefineDetail {
        /**
         * 具体有以下几种：select/ask/selectandask
         */
        private String interact;
        /**
         * 『泛澄清』选项列表。这里的『选项』是广义的选项，在意图、词槽不置信or缺失澄清，以及faqguide时，也会有一个长度为1的option_list，存放相应细节信息。
         */
        private Options[] option_list;
        /**
         * clarify时有值，表明起因
         * {@link Actions#type}
         */
        private String clarify_reason;

        public String getInteract() {
            return interact;
        }

        public void setInteract(String interact) {
            this.interact = interact;
        }

        public Options[] getOption_list() {
            return option_list;
        }

        public void setOption_list(Options[] option_list) {
            this.option_list = option_list;
        }

        public String getClarify_reason() {
            return clarify_reason;
        }

        public void setClarify_reason(String clarify_reason) {
            this.clarify_reason = clarify_reason;
        }

        /**
         * 『泛澄清』选项列表。这里的『选项』是广义的选项，在意图、词槽不置信or缺失澄清，以及faqguide时，也会有一个长度为1的option_list，存放相应细节信息。
         * {@link RefineDetail#option_list}
         */
        public static class Options {
            //选项文字
            private String option;
            //选项细节信息
            private Object info;

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }

            public Object getInfo() {
                return info;
            }

            public void setInfo(Object info) {
                this.info = info;
            }
        }
    }

}

