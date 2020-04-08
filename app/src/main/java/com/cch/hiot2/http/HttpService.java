package com.cch.hiot2.http;

import com.cch.hiot2.entity.DeviceDetailEntity;
import com.cch.hiot2.entity.HolderDeviceEntity;
import com.cch.hiot2.entity.LoginEntity;
import com.cch.hiot2.entity.RegisterEntity;
import com.cch.hiot2.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

//网络请求
public interface HttpService {
    //String base = "http://192.168.10.106/hiot";
//    String base = "http://10.10.16.15/hiot";
//    String base = "http://192.168.9.102/hiot";
    String base = "http://192.168.10.106/hiot";


    String BASE_URL = base + "/";
    String IMAGE_BASE_URL = base;//图片URL前缀

    //登录
    @FormUrlEncoded
    @POST("auth/login")
    Observable<HttpResult<LoginEntity>> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("loginCode") String loginCode
    );

    //注册
    @FormUrlEncoded
    @POST("user/register")
    Observable<HttpResult<RegisterEntity>> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("userType") String userType
    );

    @GET("user")
    Observable<HttpResult<UserEntity>> getUserInfo(
            @Header("Authorization") String Authorization
    );

    @POST("auth/logout")
    Observable<HttpResult> logout(
            @Header("Authorization") String Authorization
    );

    //上传头像
    @POST("user/img")
    @Multipart
    Observable<HttpResult> uploadFile(
            @Part MultipartBody.Part file,
            @Header("Authorization") String authorization
    );

    //查询用户绑定的设备
    //bonding 绑定：1绑定 0未绑定
    @GET("holder/user")
    Observable<HttpResult<List<HolderDeviceEntity>>> getDeviceList(
            @Query("bonding") int bonding,
            @Header("Authorization") String authorization
    );

    //添加设备
    @POST("holder/device/{device_pk}")
    Observable<HttpResult> addDevice(
            @Path("device_pk") String device_pk,
            @Header("Authorization") String authorization
    );

    //设备详情
    @GET("device/{id}")
    Observable<HttpResult<DeviceDetailEntity>> getDeviceDetail(
            @Path("id") String id,
            @Header("Authorization") String authorization
    );

    /**
     * 开关通道控制
     *
     * @param downdatastream_pk 向下通道id
     * @param status            通道状态
     * @param authorization     token
     * @return
     */
    @POST("downdatastream/{downdatastream_pk}/switch")
    Observable<HttpResult> postSwitch(
            @Path("downdatastream_pk") String downdatastream_pk,
            @Query("status") int status,
            @Header("Authorization") String authorization
    );


 /**
     * 线上的版本    开关通道控制
     *
     * @param downdatastream_pk 向下通道id
     * @param order            通道状态  控制指令:字符串格式，对GPS位置型。请按："longitude,latitude,elevation"（","为英文状态下的逗号；elevation可选）格式
     * @param authorization     token
     * @return
     */
   /* @POST("downdatastream/control/{downdatastream_pk}")
    Observable<HttpResult> postSwitch(
            @Path("downdatastream_pk") String downdatastream_pk,
            @Query("order") String order,
            @Header("Authorization") String authorization
    );*/


    /**
     * 获取开关类型历史数据
     *
     * @param data_type      数据类型 2：代表开关类类型
     * @param upDataStreamId 向上通道id
     * @param skip           起始条数索引 从0开始
     * @param limit          总条数
     * @param authorization  token
     * @return
     */
    @GET("mongodb/{data_type}/{upDataStreamId}/{skip}/{limit}/some")
    Observable<HttpResult<List<DeviceDetailEntity.DataList>>> getDataHistory(
            @Path("data_type") int data_type,
            @Path("upDataStreamId") String upDataStreamId,
            @Path("skip") int skip,
            @Path("limit") int limit,
            @Header("Authorization") String authorization
    );

}














