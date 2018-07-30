package com.sunruncn.unitdemo.dto.response;

/**
 * Created by root on 18-7-24.
 */

public class Chat2Response {
    //本轮应答体
    private Actions[] action_list;
    //解析的schema，解析意图、词槽结果都从这里面获取
    private Schema schema;
    //BOT解析的结果
    private QueryResult qu_res;

    public Actions[] getAction_list() {
        return action_list;
    }

    public void setAction_list(Actions[] action_list) {
        this.action_list = action_list;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public QueryResult getQu_res() {
        return qu_res;
    }

    public void setQu_res(QueryResult qu_res) {
        this.qu_res = qu_res;
    }
}

