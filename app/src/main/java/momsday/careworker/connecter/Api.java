package momsday.careworker.connecter;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.reactivex.Flowable;
import momsday.careworker.model.ConditionModel;
import momsday.careworker.model.JWTModel;
import momsday.careworker.model.MealModel;
import momsday.careworker.model.MyPageInfoModel;
import momsday.careworker.model.MyPageModel;
import momsday.careworker.model.PatientResponseModel;
import momsday.careworker.model.PhotoModel;
import momsday.careworker.model.ScheduleListModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("signup")
    @Headers("Content-Type: application/json")
    Flowable<Response<ResponseBody>> signUp(@Body JsonObject req);

    @POST("refresh")
    @Headers("Content-Type: application/json")
    Call<JWTModel> refresh(@Header("Authorization ") String token);

    //    @POST("")
    @POST("auth")
    @Headers("Content-Type: application/json")
    Flowable<Response<JWTModel>> signIn(@Body JsonObject req);

    @GET("patients")
    Call<PatientResponseModel> getPatients(@Header("Authorization") String token);

    @PATCH("patients")
    Call<Void> acceptRequest(@Header("Authorization") String token, @Body JsonObject req);

    @PATCH("patients/{p_id}")
    Call<Void> patchPatientMemo(@Header("Authorization") String token, @Path("p_id") String patientId, @Body JsonObject req);

    @GET("patients/{p_id}")
    Call<String> getPatientMemo(@Header("Authorization") String token, @Path("p_id") String patientId);

    @POST("send/form/meal")
    Call<Void> sendMeal(@Header("Authorization") String token, @Body JsonObject req);

    @POST("send/form/photo")
    Call<Void> sendPhoto(@Header("Authorization") String token, @Body JsonObject req);

    @POST("send/form/schedule")
    Call<Void> sendSchedule(@Header("Authorization") String token, @Body JsonObject req);

    @POST("send/form/condition")
    Call<Void> sendCondition(@Header("Authorization") String token, @Body JsonObject req);

    @PATCH("update/form/meal")
    Call<Void> updateMeal(@Header("Authorization") String token, @Body JsonObject req);

    @PATCH("update/form/photo")
    Call<Void> updatePhoto(@Header("Authorization") String token, @Body JsonObject req);

    @PATCH("update/form/schedule")
    Call<Void> updateSchedule(@Header("Authorization") String token, @Body JsonObject req);

    @PATCH("update/form/condition")
    Call<Void> updateCondition(@Header("Authorization") String token, @Body JsonObject req);

    @GET("send/form/meal/{p_id}")
    Call<MealModel> getMeal(@Header("Authorization") String token, @Path("p_id") String pId);

    @GET("send/form/photo/{p_id}")
    Call<PhotoModel> getPhoto(@Header("Authorization") String token, @Path("p_id") String pId);

    @GET("send/form/schedule/{p_id}")
    Call<ArrayList<ScheduleListModel>> getSchedule(@Header("Authorization") String token, @Path("p_id") String pId);

    @GET("send/form/condition/{p_id}")
    Call<ConditionModel> getCondition(@Header("Authorization") String token, @Path("p_id") String pId);

    @GET("my_page")
    Call<MyPageModel> getMyPage(@Header("Authorization") String token);

    @GET("my_page/modify/info")
    Call<MyPageInfoModel> getMyPageInfo(@Header("Authorization") String token);

    @PATCH("my_page/modify/info")
    Call<Void> patchMyPageInfo(@Header("Authorization") String token, @Body JsonObject req);
}
