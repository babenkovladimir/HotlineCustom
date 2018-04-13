package com.example.vladimirbabenko.hotlinecustom.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.j.MainScreenActivityJ;
import com.example.vladimirbabenko.hotlinecustom.R;

public class SignUpFragmentJ extends DialogFragment {

  @BindView(R.id.etFragmentSignInEmail) EditText etEmail;
  @BindView(R.id.etFragmentSignInPassword) EditText etPassword;
  @BindView(R.id.btFragmentSignIn) Button btSignIn;

  public static SignUpFragmentJ newInstance(Bundle args) {
    SignUpFragmentJ fragment = new SignUpFragmentJ();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setCancelable(true);
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

    btSignIn.setOnClickListener(view -> {
      boolean emailError = false;
      boolean passwordError = false;

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

      if (!emailError && !passwordError) {
        Toast.makeText(getContext(), "User is valid can continue", Toast.LENGTH_LONG).show();

        //TODO FireBase??
        //TODO Preferences
        startActivity(new Intent(getContext(), MainScreenActivityJ.class));
      }
    });
  }

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
}
