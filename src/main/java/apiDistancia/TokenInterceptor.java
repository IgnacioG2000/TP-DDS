package apiDistancia;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest=chain.request().newBuilder()
                .addHeader("Authorization", "Bearer" + "GI2kVyZ8lfcQCHNuFkQZ/EdSMx/IB3JnuBCMxEPkDH4=")
                .build();

        return chain.proceed(newRequest);
    }
}
