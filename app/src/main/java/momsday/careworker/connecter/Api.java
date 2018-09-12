package momsday.careworker.connecter;

import com.google.gson.JsonObject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import momsday.careworker.model.JWTModel;
import momsday.careworker.model.PatientResponseModel;
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
    Flowable<Response<Void>> sendPhoto(@Header("Authorization") String token, @Body JsonObject req);

    @POST("send/form/schedule")
    Call<Void> sendSchedule(@Header("Authorization") String token, @Body JsonObject req);

}
