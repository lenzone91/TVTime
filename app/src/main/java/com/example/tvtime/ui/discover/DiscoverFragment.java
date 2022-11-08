package com.example.tvtime.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.tvtime.R;
import com.example.tvtime.databinding.FragmentDiscoverBinding;
import com.example.tvtime.ui.login.LoginFragment;
import com.example.tvtime.user.UserManager;

public class DiscoverFragment extends Fragment {

    private FragmentDiscoverBinding binding;
    private UserManager userManager = UserManager.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DiscoverViewModel homeViewModel =
                new ViewModelProvider(this).get(DiscoverViewModel.class);

        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!userManager.isCurrentUserLogged()) {
            Fragment fragment = new LoginFragment();

            FragmentManager fm = this.getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.discover_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}