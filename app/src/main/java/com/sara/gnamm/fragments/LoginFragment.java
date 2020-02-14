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
import com.sara.gnamm.repository.UserRepositoryMock;

public class LoginFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static final int RC_SIGN_IN = 3;

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
            repo.findByUsernameAndPassword(username.getText(), psw.getText());
        });

        view.findViewById(R.id.login_forgot_psw_btn).setOnClickListener(v -> {

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

        // if the user is already signed in the GoogleSignInAccount will be non-null.
        if (GoogleSignIn.getLastSignedInAccount(getActivity()) != null) {
            //todo continue to main activity
        }

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            Toast.makeText(getActivity(), "task:" + task.getResult().getDisplayName() + task.getResult().getIdToken(), Toast.LENGTH_LONG).show();
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
