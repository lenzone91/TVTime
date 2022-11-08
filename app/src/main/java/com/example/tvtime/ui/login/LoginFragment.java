package com.example.tvtime.ui.login;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tvtime.R;
import com.example.tvtime.databinding.FragmentLoginBinding;
import com.example.tvtime.ui.discover.DiscoverFragment;
import com.example.tvtime.user.UserManager;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Collections;
import java.util.List;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private static final int RC_SIGN_IN = 123;
    private UserManager userManager = UserManager.getInstance();
    private Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setupListeners();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(userManager.isCurrentUserLogged()){
            startProfileActivity();
        } else {
            updateLoginButton();
        }
    }

    private void setupListeners(){
        // Login Button
        binding.login.setOnClickListener(view -> {
            btn = (Button) view.findViewById(R.id.login);
            if(!userManager.isCurrentUserLogged()){
                startSignInActivity();
            }
        });
    }

    // Launching Profile Activity
    private void startProfileActivity(){

        btn.setEnabled(false);
        Fragment fragment = new DiscoverFragment();

        FragmentManager fm =  this.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.login_fragment, fragment);
        transaction.commit();

    }

    // Update Login Button when activity is resuming
    private void updateLoginButton(){
        binding.login.setText(userManager.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
    }

    private void startSignInActivity(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers =
                Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());

        // Launch the activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

    // Show Snack Bar with a message
    private void showSnackBar( String message){
        Snackbar.make(binding.loginFragment, message, Snackbar.LENGTH_SHORT).show();
    }

    // Method that handles response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data){

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            // SUCCESS
            if (resultCode == RESULT_OK) {
                //userManager.createUser();
                showSnackBar(getString(R.string.connection_succeed));
            } else {
                // ERRORS
                if (response == null) {
                    showSnackBar(getString(R.string.error_authentication_canceled));
                } else if (response.getError()!= null) {
                    if(response.getError().getErrorCode() == ErrorCodes.NO_NETWORK){
                        showSnackBar(getString(R.string.error_no_internet));
                    } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        showSnackBar(getString(R.string.error_unknown_error));
                    }
                }
            }
        }
    }

}
