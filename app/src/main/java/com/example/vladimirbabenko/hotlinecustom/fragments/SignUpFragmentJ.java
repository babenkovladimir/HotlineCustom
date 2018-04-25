package com.example.vladimirbabenko.hotlinecustom.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.MainScreenActivity;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;
import com.example.vladimirbabenko.hotlinecustom.entity.User;
import com.example.vladimirbabenko.hotlinecustom.j.MainScreenActivityJ;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.Executor;

public class SignUpFragmentJ extends DialogFragment {

  @BindView(R.id.etFragmentSignInEmail) EditText etEmail;
  @BindView(R.id.etFragmentSignInPassword) EditText etPassword;
  @BindView(R.id.etFragmentSignUserName) EditText etUserName;
  @BindView(R.id.btFragmentSignIn) Button btSignIn;

  private DataManager mDataManager;
  private FirebaseAuth mAuth;

  public static SignUpFragmentJ newInstance(Bundle args) {
    SignUpFragmentJ fragment = new SignUpFragmentJ();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDataManager = DataManager.Companion.getCreate();
    mAuth = FirebaseAuth.getInstance();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = null;
    view = inflater.inflate(R.layout.fragment_sign_up, container, false);
    ButterKnife.bind(this, view);
    setupUI();
    return view;
  }

  private void setupUI() {
    setCancelable(true);

    btSignIn.setOnClickListener(view -> {
      boolean emailError = false;
      boolean passwordError = false;
      boolean nameError = false;

      if (!isValidEmail(etEmail.getText())) {
        emailError = true;
        etEmail.setError("email is not valid");
      } else {
        emailError = false;
        etEmail.setError(null);
      }

      if (!isValidPassword(etPassword.getText())) {
        passwordError = true;
        etPassword.setError("password is short");
      } else {
        passwordError = false;
        etPassword.setError(null);
      }

      if (!isValidPassword(etUserName.getText())) {
        nameError = true;
        etUserName.setError("name is short");
      } else {
        nameError = false;
        etUserName.setError(null);
      }

      if (!emailError && !passwordError && !nameError) {
        Toast.makeText(getContext(), "User is valid can continue", Toast.LENGTH_LONG).show();

        mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(),
            etPassword.getText().toString())
            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
              @Override public void onComplete(@NonNull Task<AuthResult> task) {

                FirebaseUser user = mAuth.getCurrentUser();

                UserProfileChangeRequest profileUpdates =
                    new UserProfileChangeRequest.Builder().setDisplayName(
                        etUserName.getText().toString()).build();

                user.updateProfile(profileUpdates);

                signInUser(etEmail.getText().toString(), etPassword.getText().toString());
              }
            })
            .addOnFailureListener(getActivity(), new OnFailureListener() {
              @Override public void onFailure(@NonNull Exception e) {
                // Todo somethinck?
                Log.e("TAG", "createUserWithEmail:error " + e.getMessage());
                dismiss();//????
              }
            });

        //startActivity(new Intent(getContext(), MainScreenActivityJ.class));
      }
    });
  }

  public void signInUser(String email, String password) {

    mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              // Sign in success, update UI with the signed-in user's information

              Log.d("TAGF", "signInWithEmail:success");
              FirebaseUser fireUser = mAuth.getCurrentUser();

              // TODO finish features for firebase authentication
              if (saveUserInfoToPreferences(fireUser)) {
                getActivity().finishAffinity();
                startActivity(new Intent(getActivity(), MainScreenActivity.class));
              }
            } else {
              // If sign in fails, display a message to the user.
              Log.d("TAGF", "signInWithEmail:failure" + task.getException());
              dismiss();
            }
          }
        });
  }

  // Private helpers

  private boolean isValidEmail(CharSequence email) {
    if (TextUtils.isEmpty(email)) {
      return false;
    } else {
      return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
  }

  private boolean isValidPassword(CharSequence password) {
    if (password.length() < 6 && password.length() > 0) return false;
    return true;
  }

  private boolean isValidUserName(CharSequence name) {
    if (name.length() > 5) {
      return true;
    } else {
      return false;
    }
  }

  private boolean saveUserInfoToPreferences(FirebaseUser fireUser) {
    String id = fireUser.getUid();
    String email = fireUser.getEmail();
    String displayedName = fireUser.getDisplayName();
    String givenName = null;
    String familyName = null;
    String photoUrl = String.valueOf(fireUser.getPhotoUrl());
    User user = new User(id, email, displayedName, familyName, givenName, photoUrl);

    mDataManager.getPrefs().setUserEmail(email);
    mDataManager.getPrefs().setUserInJson(user);
    mDataManager.getPrefs().setUserLoggedIn(true);
    return true;
  }
}
