package momsday.careworker.connecter;

import com.google.gson.JsonObject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import momsday.careworker.model.JWTModel;
import momsday.careworker.model.PatientResponseModel;
import okhttp3.ResponseBody;
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

    @POST("auth")
    @Headers("Content-Type: application/json")
    Flowable<Response<JWTModel>> signIn(@Body JsonObject req);

    @GET("patients")
    Flowable<Response<PatientResponseModel>> getPatients(@Header("Authorization") String token);

    @PATCH("patients")
    Flowable<Response<PatientResponseModel>> acceptRequest(@Header("Authorization") String token, @Body JsonObject req);

    @PATCH("patients/{p_id}")
    Flowable<Response<Void>> patchPatientMemo(@Header("Authorization") String token, @Path("p_id") String patientId, @Body JsonObject req);
}
