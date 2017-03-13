package com.alc.alcgit.devdeets;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alc.alcgit.R;
import com.alc.alcgit.remote.models.Developer;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevDetailFragment extends Fragment implements DevDetailContract.View{

    public static final String ARG_DEVELOPER = "developer";

    @BindView(R.id.iv_profile_pic)
    ImageView profilePicImageView;

    @BindView(R.id.btn_github_profile_url)
    Button profileUrlButton;

    @BindView(R.id.tv_username)
    TextView usernameTextView;

    @BindView(R.id.btn_share)
    Button shareButton;

    private DevDetailContract.UserActionListener actionListener;

    public DevDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionListener = new DevDetailPresenter(this);

    }

    public static DevDetailFragment newInstance(Developer dev){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DEVELOPER,dev);

        DevDetailFragment devDetailFragment = new DevDetailFragment();
        devDetailFragment.setArguments(args);

        return devDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  = inflater.inflate(R.layout.fragment_dev_detail, container, false);

        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        actionListener.openDevDetails((Developer) getArguments().getSerializable(ARG_DEVELOPER));
    }


    @Override
    public void showProfileUrl(String profileUrl) {
        profileUrlButton.setText(profileUrl);
    }

    @Override
    public void showProfilePhoto(String profilePhotoUrl) {
        Picasso.with(getContext())
                .load(profilePhotoUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(profilePicImageView);
    }

    @Override
    public void showUsername(String username) {
        usernameTextView.setText(username);
    }

    @Override
    public void launchShareIntent(String username, String profileUrl) {

    }

    @OnClick(R.id.btn_github_profile_url)
    public void openLink(){
        actionListener.openWebPage("lit");
    }

    @Override
    public void openInBrowser(Uri pageUri) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,pageUri);
        if(browserIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivity(browserIntent);
        }
    }
}
