package com.example.vladimirbabenko.hotlinecustom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivityJ;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;
import com.example.vladimirbabenko.hotlinecustom.fragments.SignUpFragmentJ;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import org.jetbrains.annotations.Nullable;

public class LoginActivityJ extends BaseActivityJ implements View.OnClickListener {

  private final int RC_GOOGLE_SIGN_IN = 1234;
  //private SharedPreferences prefs = DataManager.Companion.

  @BindView(R.id.btSignInGoogleButton) SignInButton mGoogleSignInButton;
  @BindView(R.id.btSignOutGoogle) Button btGoogleSignOutButton;
  @BindView(R.id.btCheckForExitingUser) Button btCheckForExitingUser;
  @BindView(R.id.btSignUp) Button btSignUp;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    setContentView(R.layout.activity_login);
    super.onCreate(savedInstanceState);

    setupUI();
    checkForexitingSignedInUser();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RC_GOOGLE_SIGN_IN) {
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case (R.id.btSignInGoogleButton): googleSignIn(); break;
      case (R.id.btSignOutGoogle): googleSigneOut(); break;
      case (R.id.btCheckForExitingUser): checkForexitingSignedInUser(); break;
      case (R.id.btSignUp): showSignUpFragment();
      default: break;
    }
  }

  private void googleSignIn() {
    // TODO solve problem with int key
    GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
  }

  private void googleSigneOut() {
    GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    mGoogleSignInClient.signOut();
  }

  private void setupUI() {
    btGoogleSignOutButton.setOnClickListener(this);
    btCheckForExitingUser.setOnClickListener(this);
    btSignUp.setOnClickListener(this);
    // Set the dimensions of the sign-in button.
    mGoogleSignInButton.setSize(SignInButton.SIZE_STANDARD);
    mGoogleSignInButton.setOnClickListener(this);

  }

  private void showSignUpFragment() {
    SignUpFragmentJ signUpFragmentJ = SignUpFragmentJ.newInstance(null);
    FragmentManager manager = getSupportFragmentManager();
    signUpFragmentJ.show(manager, "SignUpFragmentJ");
  }

  private void checkForexitingSignedInUser() {
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      Log.d(TAG, "chechForexitingSignedInUser: " + account);
      Log.d(TAG, "GoogleSignedInAccount Displayed Name: " + account.getDisplayName());
      Log.d(TAG, "GoogleSignedInAccount Email: " + account.getEmail());
      Log.d(TAG, "GoogleSignedInAccount fhoto url: " + account.getPhotoUrl());
      Log.d(TAG, "GoogleSignedInAccount  Idtoken: " + account.getIdToken());
      Log.d(TAG, "GoogleSignedInAccount  Id: " + account.getId());
      Log.d(TAG, "GoogleSignedInAccount  Id GivenName: " + account.getGivenName());
      Log.d(TAG, "GoogleSignedInAccount  Id familyNmae: " + account.getFamilyName());
    } else {
      Log.d(TAG, "GoogleSignedInAccount is not registred now " + account);
    }
  }

  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);

      // TODO work with sharedPreferences and then go to MainScreenActivity


      startActivity(new Intent(getApplicationContext(), MainScreenActivityJ.class));
      finish();

    } catch (ApiException e) {
      Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
      //updateUI(null);
    }
  }
}
