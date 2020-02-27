package com.sara.gnamm.services;

import android.text.TextUtils;
import android.util.Patterns;

import com.sara.gnamm.models.user.User;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class LoginService {

    private class GoogleSignInRequest {
        String idToken;
        String email;

        GoogleSignInRequest(String idToken, String email) {

            if (TextUtils.isEmpty(idToken)) {
                throw new RuntimeException("Idtoken is missing");
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                throw new RuntimeException("Email " + email + " not valid");
            }

            this.idToken = idToken;
            this.email = email;
        }
    }

    public interface LoginServiceInterface {
        @POST("/auth/login-with-google")
        Call<User> loginWithGoogle(@Body GoogleSignInRequest request);
    }

    public void loginWithGoogle(@NotNull String email, @NotNull String idToken, @NotNull ServiceListener<User> listener) {

        LoginServiceInterface svc = RetrofitService.getInstance().create(LoginServiceInterface.class);
        Call<User> userCall = svc.loginWithGoogle(new GoogleSignInRequest(idToken, email));

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                    return;
                }

                String errorMessage = "GENERIC_ERROR";
                try {
                    if (response.errorBody() != null) {
                        errorMessage = response.errorBody().string();
                    }
                } catch (IOException e) {
                    //do nothing
                }

                ServiceError svcError = ServiceError.valueOf(errorMessage);
                listener.onError(0, svcError, errorMessage);
            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                listener.onError(0, ServiceError.GENERIC_ERROR, t.getMessage());
            }
        });
    }
}
