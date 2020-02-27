package com.sara.gnamm.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.sara.gnamm.R;
import com.sara.gnamm.models.user.User;
import com.sara.gnamm.repository.UserRepositoryMock;
import com.sara.gnamm.services.LoginService;
import com.sara.gnamm.services.ServiceError;
import com.sara.gnamm.services.ServiceListener;

public class LoginFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    static final int RC_SIGN_IN = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        UserRepositoryMock repo = new UserRepositoryMock();

        AppCompatEditText username = view.findViewById(R.id.login_username_value);
        AppCompatEditText psw = view.findViewById(R.id.login_password_value);

        view.findViewById(R.id.login_btn).setOnClickListener(v -> {
            User user = repo.findByUsernameAndPassword(username.getText(), psw.getText());
            //TODO Check user null

            //TODO Do login
        });

        view.findViewById(R.id.login_forgot_psw_btn).setOnClickListener(v -> {
            //TODO call forgot psw
        });

        view.findViewById(R.id.google_sign_in_btn).setOnClickListener(v -> {
            handleGoogleSignIn();
        });
    }

    private void handleGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id_web_app))
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

//         if the user is already signed in the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null) {
            //todo continue to main activity
            Toast.makeText(getActivity(), "User already logged in as:" + GoogleSignIn.getLastSignedInAccount(getActivity()).getDisplayName(), Toast.LENGTH_LONG).show();

            loginWithGoogle(account);

            return;
        }

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void loginWithGoogle(GoogleSignInAccount account) {
        if (account == null) {
            throw new RuntimeException("Account is null");
        }

        new LoginService().loginWithGoogle(account.getEmail(), account.getIdToken(), new ServiceListener<User>() {
            @Override
            public void onSuccess(User result) {
                Toast.makeText(getActivity(), "User:" + result.getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(int code, ServiceError serviceError, String message) {
                Toast.makeText(getActivity(), serviceError + " [ code " + code + " ] : " + message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            loginWithGoogle(task.getResult());
            //TODO call backend task.getResult().getIdToken()
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
