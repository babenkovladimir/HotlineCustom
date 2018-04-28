package com.example.vladimirbabenko.hotlinecustom.fragments.j;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;
import com.example.vladimirbabenko.hotlinecustom.entity.User;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragmentJ extends Fragment {

  @BindView(R.id.civCircleImageView) CircleImageView mCircleImageView;
  @BindView(R.id.tvProfileId) TextView tvId;
  @BindView(R.id.tvProfileName) TextView tvName;
  @BindView(R.id.tvProfileEmail) TextView tvEmail;
//  @BindView(R.id.tvProfileEmailFirebase) TextView tvFireBaseEmail;
  @BindView(R.id.fabChangeProfileInformation) FloatingActionButton fab;

  private DataManager mDataManager;

  public static ProfileFragmentJ newInstance(Bundle args) {
    ProfileFragmentJ fragment = new ProfileFragmentJ();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDataManager = DataManager.Companion.getCreate();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_profile, container, false);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    User currentUser = mDataManager.getPrefs().getUserInJson();

    //Picasso
    //    .get()
    //    .load(mPreferencesHelper.getUserPhotoUrl( ))
    //    .error(R.drawable.logo_activity_umbrella)
    //    .into(mCircleImageView);

    tvId.setText(currentUser.getId());
    tvName.setText(currentUser.getGivenName());
    tvName.setText(currentUser.getDisplayedName());
    tvEmail.setText(currentUser.getEmail());
    //tvFireBaseEmail.setText(mDataManager.getPrefs().getUserEmail());


    Picasso
        .get()
        .load(currentUser.getFotoUrl())
        .error(R.drawable.mr_white)
        .into(mCircleImageView);

    setupUI();

  }

  private void setupUI() {
    fab.setOnClickListener(view ->{

    });
  }
}
