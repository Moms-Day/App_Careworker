package momsday.careworker.connecter;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @POST("signup")
    @Headers("Content-Type: application/json")
    Single<Response<ResponseBody>> signUp(@Body JsonObject req);
}
