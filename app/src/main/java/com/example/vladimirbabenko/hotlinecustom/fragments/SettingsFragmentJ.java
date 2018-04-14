package com.example.vladimirbabenko.hotlinecustom.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.BuildConfig;
import com.example.vladimirbabenko.hotlinecustom.LoginActivity;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.example.vladimirbabenko.hotlinecustom.utils.InternetConnectionHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SettingsFragmentJ extends Fragment {

  @BindView(R.id.btSignOutSettingsFragment) Button btSignOut;
  @BindView(R.id.tvBuildVersionSettingsFragment) TextView tvBuildVersion;
  @BindView(R.id.btBattaryState) Button btBattaryState;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = null;
    view = inflater.inflate(R.layout.fragment_settings, container, false);
    ButterKnife.bind(this, view);

    setuoUI();

    return view;
  }

  private void setuoUI() {
    btSignOut.setOnClickListener(view -> signOut());
    tvBuildVersion.setText(String.format("Build version = %d", BuildConfig.VERSION_CODE));

    btBattaryState.setOnClickListener(view->{

      Toast.makeText(getContext(), "is internet "+ InternetConnectionHelper.Companion.isConnection(), Toast.LENGTH_SHORT).show();

    });
  }

  /*
   *This void cheks fo auth source
   * */
  private void signOut() {

    int src = 1;// source of auth

    switch (src) {
      case 1: {
        GoogleSignInOptions gso =
            new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mGoogleSignInClient.signOut();
        getActivity().finishAffinity();
        startActivity(new Intent(getContext(), LoginActivity.class));
      }
      default:
        break;
    }
  }
}
