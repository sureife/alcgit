package com.alc.alcgit.devdeets;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alc.alcgit.R;
import com.alc.alcgit.remote.models.Developer;

public class DevDetailActivity extends AppCompatActivity {
    public static final String EXTRA_DEVELOPER = "developer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_detail);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_DEVELOPER)){
            Developer dev = (Developer) intent.getSerializableExtra(EXTRA_DEVELOPER);
            DevDetailFragment fragment = DevDetailFragment.newInstance(dev);
            initFragment(fragment);
        }
    }

    private void initFragment(Fragment fragment) {
        // Add the fragment to the layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.detailsContentFrame, fragment);
        transaction.commit();
    }
}
