package apiDistancia;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest=chain.request().newBuilder()
                .header("Authorization", "Bearer " + "BVzyPLRREwmSLtObJKQJe4j/YApzVdfmbmeducoe0rM=")
                .build();

        return chain.proceed(newRequest);
    }
}
