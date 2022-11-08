package com.example.tvtime.ui.profile;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.tvtime.R;
import com.example.tvtime.databinding.ActivityProfileBinding;
import com.example.tvtime.databinding.FragmentLoginBinding;
import com.example.tvtime.user.UserManager;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends Fragment {

    private ActivityProfileBinding binding;
    private UserManager userManager = UserManager.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupListeners();
        updateUIWithUserData();
        binding = ActivityProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }



    private void setupListeners(){
        binding.updateButton.setOnClickListener(view -> { });
        binding.signOutButton.setOnClickListener(view -> { });
        binding.deleteButton.setOnClickListener(view -> { });
    }

    private void updateUIWithUserData(){
        if(userManager.isCurrentUserLogged()){
            FirebaseUser user = userManager.getCurrentUser();

            if(user.getPhotoUrl() != null){
                setProfilePicture(user.getPhotoUrl());
            }
            setTextUserData(user);
        }
    }

    private void setProfilePicture(Uri profilePictureUrl){
        //Glide.with(this)
                //.load(profilePictureUrl)
                //.apply(RequestOptions.circleCropTransform())
                //.into(binding.profileImageView);
    }

    private void setTextUserData(FirebaseUser user){

        //Get email & username from User
        String email = TextUtils.isEmpty(user.getEmail()) ? getString(R.string.info_no_email_found) : user.getEmail();
        String username = TextUtils.isEmpty(user.getDisplayName()) ? getString(R.string.info_no_username_found) : user.getDisplayName();

        //Update views with data
        binding.usernameEditText.setText(username);
        binding.emailTextView.setText(email);
    }
}
