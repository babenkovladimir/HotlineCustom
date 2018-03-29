package com.example.vladimirbabenko.hotlinecustom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import org.jetbrains.annotations.Nullable;

public class LoginActivityJ extends BaseActivity implements View.OnClickListener {

  private int RC_SIGN_IN = 10101;
  private SignInButton mGoogleSignInButton;
  private GoogleSignInClient mGoogleSignInClient;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    setupGoogleButton();
    setubGoogleSigneoutButto();
    setupCheckUserButton();

    GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    Log.d("TAG", "onCreate: gso" + gso);

    // Build a GoogleSignInClient with the options specified by gso.
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    Log.d("TAG", "onCreate: mGoogleSignInClient" + mGoogleSignInClient);

    checkForexitingSignedInUser();
  }



  private void checkForexitingSignedInUser() {
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    Log.d("TAG", "chechForexitingSignedInUser: " + account);
  }

  @Override protected void onStart() {
    super.onStart();
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    //updateUI(account);
    Log.d("TAG", "onStart: account = "+ account);
    account.toString();
    Log.d("TAG", "GoogleSignedInAccount Displayed Name: "+ account.getDisplayName());
    Log.d("TAG", "GoogleSignedInAccount Email: "+ account.getDisplayName());
    Log.d("TAG", "GoogleSignedInAccount fhoto url: "+ account.getPhotoUrl());
    Log.d("TAG", "GoogleSignedInAccount  Idtoken: "+ account.getId());

  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case (R.id.btSignInGoogleButton):
        signIn();
        break;
      case(R.id.btSignOutGoogle): signOut(); checkForexitingSignedInUser(); ;break;
      case(R.id.btCheckForExitingUser): checkForexitingSignedInUser();break;
      default:
        break;
    }
  }

  private void signOut() {
    mGoogleSignInClient.signOut();
  }

  private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);

  }

  private void setubGoogleSigneoutButto() {
    findViewById(R.id.btSignOutGoogle).setOnClickListener(this);
  }

  private void setupGoogleButton() {
    // Set the dimensions of the sign-in button.
    mGoogleSignInButton = (SignInButton) findViewById(R.id.btSignInGoogleButton);
    mGoogleSignInButton.setSize(SignInButton.SIZE_STANDARD);
    mGoogleSignInButton.setOnClickListener(this);
  }

  private void setupCheckUserButton() {
    findViewById(R.id.btCheckForExitingUser).setOnClickListener(this);
  }
}
