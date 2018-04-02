package com.example.vladimirbabenko.hotlinecustom.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper;
import com.example.vladimirbabenko.hotlinecustom.entity.User;
import com.liangfeizc.avatarview.AvatarView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragmentJ extends Fragment {

  @BindView(R.id.civCircleImageView) CircleImageView mCircleImageView;
  @BindView(R.id.tvProfileName) TextView tvName;
  @BindView(R.id.tvProfileEmail) TextView tvEmail;
  //TODO how is right to send info to fragment??? by new Instance?
  //TODO use DelegateObservable ? ? ?
  private PreferencesHelper mPreferencesHelper;

  public static ProfileFragmentJ newInstance(Bundle args) {
    ProfileFragmentJ fragment = new ProfileFragmentJ();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view=null;

    view = inflater.inflate(R.layout.fragment_profile, container, false);

    ButterKnife.bind(this, view);
    mPreferencesHelper = new PreferencesHelper(container.getContext());

    return view;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    User currentUser = mPreferencesHelper.getUserFromGson();



    //Picasso
    //    .get()
    //    .load(mPreferencesHelper.getUserPhotoUrl())
    //    .error(R.drawable.logo_activity_umbrella)
    //    .into(mCircleImageView);


    tvName.setText(currentUser.getGivenName());
    tvEmail.setText(currentUser.getEmail());

    Picasso
        .get()
        .load(currentUser.getFotoUrl())
        .error(R.drawable.logo_activity_umbrella)
        .into(mCircleImageView);

  }
}
