package com.sunruncn.unitdemo.dto.request;

import javax.annotation.Nullable;

/**
 * 干预信息
 * {@link Chat1Request#updates}
 */

public class Updates {
    //干预方式，抛弃系统解析结果，转而由updates字段来定义，其值为DEFINE
    @Nullable
    private String type;
    //具体操作集
    @Nullable
    private Optional[] ops;

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public Optional[] getOps() {
        return ops;
    }

    public void setOps(@Nullable Optional[] ops) {
        this.ops = ops;
    }


    /**
     * 干预信息中的具体操作集
     * {@link Updates#ops}
     */
    public class Optional {

        //操作方式，定义一个对象，其值当前仅支持为DEFINE
        @Nullable
        private String op;
        //操作针对的对象，可选值为意图（INTENT）、词槽（SLOT）
        @Nullable
        private String target;
        /**
         * target对象的值
         * target为意图（INTENT）时,value为一个string，代表要干预的意图。
         * target为SLOT时，value为一个object，代表要干预的词槽,{@link Values}
         */
        @Nullable
        private Object value;


        @Nullable
        public String getOp() {
            return op;
        }

        public void setOp(@Nullable String op) {
            this.op = op;
        }

        @Nullable
        public String getTarget() {
            return target;
        }

        public void setTarget(@Nullable String target) {
            this.target = target;
        }

        @Nullable
        public Object getValue() {
            return value;
        }

        public void setValue(@Nullable Object value) {
            this.value = value;
        }

        class Values {
            //如果是query中不包含的词槽（如位置信息），取值-1
            private int begin;
            //如果是query中不包含的词槽（如位置信息），取值-1
            private int length;
            //词槽值
            private String original_word;
            //归一化后的词槽值（标准表达）
            private String normalized_word;
            //预留字段
            private String word_type;
            //词槽名
            private String name;
        }
    }
}
