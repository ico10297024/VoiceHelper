package com.sunruncn.unitdemo.network;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by ICO on 2017/2/16 0016.
 */

public interface IHttpServer {

    //region 这一块是用来演示如何定义一个http请求

    /**
     * 此函数演示了最普通的http get请求定义
     * get和query搭配使用
     *
     * @param username 用户名
     * @param password 密码
     * @return Call<byte[]>
     */
    @GET("sunrun/login.json")
    Call<byte[]> login(
            @Query("username") String username
            , @Query("password") String password
    );

    /**
     * 此函数演示了最普通的http post请求定义
     * post和field搭配使用
     * get和query搭配使用
     * http://10.221.204.100:8080/HeQieRenting/rest/sunrun/login.json
     *
     * @param username 用户名
     * @param password 密码
     * @return Call<byte[]>
     */
    @FormUrlEncoded
    @POST("sunrun/login.json")
    Call<byte[]> login1(
            @Field("username") String username
            , @Field("password") String password
    );


    /**
     * 此函数演示了文件上传的请求定义
     *
     * @return Call<byte[]>
     */
    @Multipart
    @POST("sunrun/register.json")
    Call<byte[]> register(
            @Part List<MultipartBody.Part> pars
    );

    /**
     * 此函数演示了如何传输一个字符串,这通常用来传递json字符串
     *
     * @return Call<byte[]>
     */
    @POST("sunrun/bluetoothOpenRecordDTO.json")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<byte[]> bluetoothOpenRecordDTO(
            @Body RequestBody body
    );
    //endregion

    /**
     * 此函数演示了如何传输一个字符串,这通常用来传递json字符串
     *
     * @return Call<byte[]>
     */
    @POST("rpc/2.0/unit/bot/chat")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<byte[]> chat(
            @Query("access_token") String access_token,
            @Body RequestBody body
    );

    /**
     * 鉴权,获取Access Token
     *
     * @return Call<byte[]>
     */
    @FormUrlEncoded
    @POST("oauth/2.0/token")
    Call<byte[]> token(
            @Field("grant_type") String client_credentials,
            @Field("client_id") String api_key,
            @Field("client_secret") String secret_key
    );
}
