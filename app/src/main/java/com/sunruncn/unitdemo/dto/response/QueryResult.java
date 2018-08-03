package com.sunruncn.unitdemo.dto.response;

import java.util.Map;

/**
 * BOT解析的结果
 */
public class QueryResult {
    //query结果时间戳
    private long timestamp;
    //query结果状态
    private int status;
    //原始query
    private String raw_query;
    //意图候选项
    private Candidates[] candidates;

    /**
     * 最终qu结果，内部格式同result.response.qu_res.candidates[]
     * {@link QueryResult#candidates}
     * json
     */
    private String qu_res_chosen;
    //query的词法分析结果
    private LexicalAnalysis[] lexical_analysis;
    //query的情感分析结果
    private SentimentAnalysis sentiment_analysis;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRaw_query() {
        return raw_query;
    }

    public void setRaw_query(String raw_query) {
        this.raw_query = raw_query;
    }

    public Candidates[] getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates[] candidates) {
        this.candidates = candidates;
    }

    public String getQu_res_chosen() {
        return qu_res_chosen;
    }

    public void setQu_res_chosen(String qu_res_chosen) {
        this.qu_res_chosen = qu_res_chosen;
    }

    public LexicalAnalysis[] getLexical_analysis() {
        return lexical_analysis;
    }

    public void setLexical_analysis(LexicalAnalysis[] lexical_analysis) {
        this.lexical_analysis = lexical_analysis;
    }

    public SentimentAnalysis getSentiment_analysis() {
        return sentiment_analysis;
    }

    public void setSentiment_analysis(SentimentAnalysis sentiment_analysis) {
        this.sentiment_analysis = sentiment_analysis;
    }

    /**
     * 意图候选项
     */
    public static class Candidates {
        //解析结果整体的（综合意图和词槽）置信度，如果返回结果中无该字段，请重新训练后尝试。
        private float confidence;
        //候选项意图名称
        private String intent;
        //候选项意图置信度
        private float intent_confidence;
        //候选项domain分类置信度
        private float domain_confidence;
        //意图是否需要澄清
        private boolean intent_need_clarify;
        //候选项词槽列表
        private CandidatesSlots[] slots;
        //来自哪个qu策略（smart-qu对应对话模板，ml-qu对应对话样本学习）
        private String from_who;
        //query匹配信息
        private String match_info;
        //候选项附加信息
        //kvdict
        private Map extra_info;

        public float getConfidence() {
            return confidence;
        }

        public void setConfidence(float confidence) {
            this.confidence = confidence;
        }

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

        public boolean getIntent_need_clarify() {
            return intent_need_clarify;
        }

        public void setIntent_need_clarify(boolean intent_need_clarify) {
            this.intent_need_clarify = intent_need_clarify;
        }

        public CandidatesSlots[] getSlots() {
            return slots;
        }

        public void setSlots(CandidatesSlots[] slots) {
            this.slots = slots;
        }

        public String getFrom_who() {
            return from_who;
        }

        public void setFrom_who(String from_who) {
            this.from_who = from_who;
        }

        public String getMatch_info() {
            return match_info;
        }

        public void setMatch_info(String match_info) {
            this.match_info = match_info;
        }

        public Map getExtra_info() {
            return extra_info;
        }

        public void setExtra_info(Map extra_info) {
            this.extra_info = extra_info;
        }

        public static class CandidatesSlots {

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
            //分类置信度
            private boolean need_clarify;
            //父词槽index，非子词槽，取值-1
            private int father_idx;

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

            public boolean getNeed_clarify() {
                return need_clarify;
            }

            public void setNeed_clarify(boolean need_clarify) {
                this.need_clarify = need_clarify;
            }

            public int getFather_idx() {
                return father_idx;
            }

            public void setFather_idx(int father_idx) {
                this.father_idx = father_idx;
            }
        }
    }

    /**
     * query的词法分析结果
     */
    public static class LexicalAnalysis {
        //词汇(含命名实体)))
        private String term;
        //重要性权重
        private float weight;
        //词性或专名类别
        private String type;
        //构成词汇的基本词
        private String[] basic_word;

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String[] getBasic_word() {
            return basic_word;
        }

        public void setBasic_word(String[] basic_word) {
            this.basic_word = basic_word;
        }
    }

    /**
     * query的情感分析结果
     */
    public static class SentimentAnalysis {
        //情感标签
        private String label;
        //置信度
        private float pval;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public float getPval() {
            return pval;
        }

        public void setPval(float pval) {
            this.pval = pval;
        }
    }
}

