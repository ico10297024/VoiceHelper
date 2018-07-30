package com.sunruncn.unitdemo.dto.response;

/**
 * 解析的schema，解析意图、词槽结果都从这里面获取
 */

public class Schema {
    //意图信息
    private String intent;
    //词置信度
    private float intent_confidence;
    //domain分类置信度
    private float domain_confidence;
    //词槽列表
    private SchemaSlots[] slots;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public float getIntent_confidence() {
        return intent_confidence;
    }

    public void setIntent_confidence(float intent_confidence) {
        this.intent_confidence = intent_confidence;
    }

    public float getDomain_confidence() {
        return domain_confidence;
    }

    public void setDomain_confidence(float domain_confidence) {
        this.domain_confidence = domain_confidence;
    }

    public SchemaSlots[] getSlots() {
        return slots;
    }

    public void setSlots(SchemaSlots[] slots) {
        this.slots = slots;
    }

    /**
     * 词槽列表
     */
    public static class SchemaSlots {
        //词槽置信度
        private float confidence;
        //词槽起始
        private int begin;
        //词槽长度
        private int length;
        //词槽值
        private String original_word;
        //归一化词槽值
        //json
        private String normalized_word;
        //词槽值细化类型
        private String word_type;
        //词槽名称
        private String name;
        //词槽是在第几轮对话中引入的
        private int session_offset;
        //引入的方式
        private String merge_method;
        //子词槽list，内部结构同正常词槽。
        private SchemaSlots[] sub_slots;

        public float getConfidence() {
            return confidence;
        }

        public void setConfidence(float confidence) {
            this.confidence = confidence;
        }

        public int getBegin() {
            return begin;
        }

        public void setBegin(int begin) {
            this.begin = begin;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getOriginal_word() {
            return original_word;
        }

        public void setOriginal_word(String original_word) {
            this.original_word = original_word;
        }

        public String getNormalized_word() {
            return normalized_word;
        }

        public void setNormalized_word(String normalized_word) {
            this.normalized_word = normalized_word;
        }

        public String getWord_type() {
            return word_type;
        }

        public void setWord_type(String word_type) {
            this.word_type = word_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSession_offset() {
            return session_offset;
        }

        public void setSession_offset(int session_offset) {
            this.session_offset = session_offset;
        }

        public String getMerge_method() {
            return merge_method;
        }

        public void setMerge_method(String merge_method) {
            this.merge_method = merge_method;
        }

        public SchemaSlots[] getSub_slots() {
            return sub_slots;
        }

        public void setSub_slots(SchemaSlots[] sub_slots) {
            this.sub_slots = sub_slots;
        }
    }
}

