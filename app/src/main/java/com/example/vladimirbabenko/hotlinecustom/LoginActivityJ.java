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
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper;
import com.example.vladimirbabenko.hotlinecustom.fragments.SignUpFragmentJ;
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import org.jetbrains.annotations.Nullable;

public class LoginActivityJ extends BaseActivityJ implements View.OnClickListener {

  //private PreferencesHelper prefs;
  //private DataManager mManager = new DataManager(getApplicationContext());

  @BindView(R.id.btSignInGoogleButton) SignInButton mGoogleSignInButton;
  @BindView(R.id.btSignOutGoogle) Button btGoogleSignOutButton;
  @BindView(R.id.btCheckForExitingUser) Button btCheckForExitingUser;
  @BindView(R.id.btSignUp) Button btSignUp;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    setContentView(R.layout.activity_login);
    super.onCreate(savedInstanceState);
    //prefs = new PreferencesHelper(this);

    setupUI();
    checkForexitingSignedInUser();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == AppConstants.RC_GOOGLE_SIGN_IN.getIntKey()) {
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
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, AppConstants.RC_GOOGLE_SIGN_IN.getIntKey());
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
    // TODO check for the intenet connection???
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);
      saveToPreffs(account);
      Intent intent = new Intent(getApplicationContext(), MainScreenActivityJ.class);
      //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
      finishAffinity();//
      startActivity(intent);
    } catch (ApiException e) {
      Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
      //updateUI(null);
    }
  }

  private void saveToPreffs(GoogleSignInAccount account){
    // TODO add null check for account

    prefs.setUserLoggedIn(true);// by Kotli getter/setter
    prefs.setUserEmail(account.getEmail());
    prefs.setUserDisplayedName(account.getDisplayName());
    prefs.setUserGivenName(account.getGivenName());
    prefs.setUserFamilyName(account.getGivenName());
    prefs.setUserPhotoUrl(account.getPhotoUrl().toString());


  }

}
