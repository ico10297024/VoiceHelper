package com.sunruncn.unitdemo.network;

import android.content.Context;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunruncn.unitdemo.dto.AccessTokenDTO;
import com.sunruncn.unitdemo.dto.request.Chat1Request;
import com.sunruncn.unitdemo.dto.request.ChatRequest;
import com.sunruncn.unitdemo.dto.response.ChatResponse;

import java.util.HashMap;

import ico.ico.network.CommonConverter;
import ico.ico.network.HttpCallbackHelper;
import ico.ico.network.HttpUtil;
import ico.ico.network.dispather.EntityDispather;
import ico.ico.util.Common;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Subscription;

/**
 * Created by ICO on 2017/2/16 0016.
 */

public class HttpServer {
    public static String DOMAIN = "https://aip.baidubce.com";
    //    public static String DOMAIN = "http://10.221.204.64:8080";
    public static String PROJECT = "";
    public static String DOMAIN_PROJECT = DOMAIN + "/" + PROJECT;
    public static String baseUrl = DOMAIN_PROJECT;
    static OkHttpClient mOkHttpClient;
    static Retrofit mRetrofit;
    static ObjectMapper mObjectMapper;

    static {
        mObjectMapper = new ObjectMapper();
        mOkHttpClient = new OkHttpClient.Builder().build();
        mRetrofit = new Retrofit.Builder().client(mOkHttpClient).addConverterFactory(CommonConverter.FACTORY).baseUrl(baseUrl).build();
    }

    /**
     * {@link IHttpServer#chat(String, RequestBody)}
     * 演示普通的http请求
     */
    public static Subscription chat(final Context context, final MyHttpCallback myHttpCallback
            , String access_token
            , String data
            , String log_id
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Chat1Request chat1Request = new Chat1Request();
        chat1Request.setUser_id(Common.getUniqueId(context));
        chat1Request.setQuery(data);
        chat1Request.setQuery_info(new Chat1Request.QueryInfoRequest());

        HashMap<String, Object> client_session = new HashMap<>();
        client_session.put("client_results", "");
        client_session.put("candidate_options", new Object[0]);

        chat1Request.setClient_session(objectMapper.writeValueAsString(client_session));

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setRequest(chat1Request);
        chatRequest.setLog_id(log_id);


        String json = objectMapper.writeValueAsString(chatRequest);
//        String json = JSON.toJSONString(chatRequest);
//        String json = "{\"bot_session\":\"\",\"log_id\":\"0\",\"request\":{\"bernard_level\":1,\"query\":\"开办公室门\",\"query_info\":{\"asr_candidates\":[],\"source\":\"KEYBOARD\",\"type\":\"TEXT\"},\"updates\":\"\",\"user_id\":\"dd0675d491044abfbbdacc63b5f48085\",\"client_session\":\"{\\\"client_results\\\":\\\"\\\", \\\"candidate_options\\\":[]}\"},\"bot_id\":\"7848\",\"version\":\"2.0\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        IHttpServer iHttpServer = mRetrofit.create(IHttpServer.class);
        //EntityDispather是一个转发器,会将http返回的数据通过转发器中数据处理函数转化为指定类的实体对象,然后传入到回调函数中
        //注:这里如果想使用post请求就使用login1函数
        return HttpUtil.execute(context, myHttpCallback, iHttpServer.chat(access_token, body)
                , new EntityDispather<ChatResponse>(context, myHttpCallback, ChatResponse.class) {
                    @Override
                    public boolean onCallback(int statusCode, ChatResponse baseDTO) {
                        return myHttpCallback.chat(statusCode, baseDTO);
                    }
                });
    }

    /**
     * {@link IHttpServer#token(String, String, String)}
     * 演示普通的http请求
     */
    public static Subscription token(final Context context, final MyHttpCallback myHttpCallback) {
        IHttpServer iHttpServer = mRetrofit.create(IHttpServer.class);
        //EntityDispather是一个转发器,会将http返回的数据通过转发器中数据处理函数转化为指定类的实体对象,然后传入到回调函数中
        //注:这里如果想使用post请求就使用login1函数
        return HttpUtil.execute(context, myHttpCallback, iHttpServer.token("client_credentials", "70APxwzdfvAMpBDMIzswTVbo", "DtHKebPHeqj67kcaavsP6TLfbGYVh18x")
                , new EntityDispather<AccessTokenDTO>(context, myHttpCallback, AccessTokenDTO.class) {
                    @Override
                    public boolean onCallback(int statusCode, AccessTokenDTO baseDTO) {
                        return myHttpCallback.token(statusCode, baseDTO);
                    }
                });
    }

    /**
     * 用来定义所有的网络请求前台回调
     * 函数中的返回值表示是否消费了这次事件
     */
    public static class MyHttpCallback extends HttpCallbackHelper {

        public MyHttpCallback() {
            super();
        }


        public boolean chat(int statusCode, ChatResponse baseDTO) {
            return false;
        }

        public boolean token(int statusCode, AccessTokenDTO accessTokenDTO) {
            return false;
        }
    }
}
