package com.alc.alcgit.devs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alc.alcgit.R;
import com.alc.alcgit.devdeets.DevDetailActivity;
import com.alc.alcgit.remote.models.Developer;
import com.alc.alcgit.utils.NetworkUtils;
import com.alc.alcgit.utils.Utilities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevelopersFragment extends Fragment implements DevelopersContract.View{

    @BindView(R.id.rv_developers)
    RecyclerView devsRecyclerView;

    @BindView(R.id.loading)
    FrameLayout loading;

    private List<Developer> devs;

    DevelopersContract.UserActionListener actionListener;

    DevelopersAdapter devsAdapter;


    public DevelopersFragment() {
        // Required empty public constructor
    }

    public static DevelopersFragment newInstance(){
        return new DevelopersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        devsAdapter = new DevelopersAdapter(new ArrayList<Developer>(0),developerItemClickedListener);
        actionListener = new DevelopersPresenter(this);
        loadDevelopers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_developers, container, false);

        ButterKnife.bind(this,root);

        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView(){
        devsRecyclerView.setAdapter(devsAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        devsRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadDevelopers(){
        String searchQualifiers = "location:lagos+type:user+language:java";
        actionListener.loadDevelopers(searchQualifiers);
    }



    @Override
    public void showDevelopers(List<Developer> developers) {
        devsAdapter.replaceData(developers);
    }

    @Override
    public void openDevDetails(Developer developer) {
        Intent intent = new Intent(getContext(),DevDetailActivity.class);
        intent.putExtra(DevDetailActivity.EXTRA_DEVELOPER,developer);
        startActivity(intent);
    }

    @Override
    public void hideProgressIndicator() {
        // Hide Load
        loading.setVisibility(View.GONE);
    }

    @Override
    public boolean checkNetworkConnection() {
        return NetworkUtils.isNetworkConnected(getContext());
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Utilities.showToast(getContext(),errorMessage);
    }

    DevelopersAdapter.OnDeveloperItemClickedListener developerItemClickedListener = new DevelopersAdapter.OnDeveloperItemClickedListener() {
        @Override
        public void onDeveloperItemClicked(Developer dev) {
            actionListener.showDevDetail(dev);
        }
    };

    static class DevelopersAdapter extends Adapter<DevelopersAdapter.ViewHolder>{
        private List<Developer> developers;
        private OnDeveloperItemClickedListener developerItemClickedListener;

        DevelopersAdapter(List<Developer> developers,OnDeveloperItemClickedListener developerItemClickedListener){
            this.developers = developers;
            this.developerItemClickedListener = developerItemClickedListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();

            LayoutInflater inflater = LayoutInflater.from(context);

            View devitemView = inflater.inflate(R.layout.dev_card,parent,false);

            return new ViewHolder(devitemView,developerItemClickedListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Developer developer = developers.get(position);

            holder.loadImage(developer.getProfilePicUrl());
            holder.usernameTextView.setText(developer.getUsername());
        }

        @Override
        public int getItemCount() {
            return developers.size();
        }

        public Developer getItem(int position){
            return developers.get(position);
        }

        public void replaceData(List<Developer> developers){
            this.developers = developers;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private OnDeveloperItemClickedListener itemClickedListener;

            @BindView(R.id.iv_github_profile_thumbnail)
            ImageView thumbnailImageView;

            @BindView(R.id.tv_usname)
            TextView usernameTextView;


            public ViewHolder(View itemView,OnDeveloperItemClickedListener itemClickedListener) {
                super(itemView);

                ButterKnife.bind(this,itemView);

                this.itemClickedListener = itemClickedListener;

                itemView.setOnClickListener(cardClickListener);
            }

            View.OnClickListener cardClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Developer developer= getItem(position);
                    itemClickedListener.onDeveloperItemClicked(developer);
                }
            };

            private void loadImage(String imageUrl){
                Context context = thumbnailImageView.getContext();

                Picasso.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .into(thumbnailImageView);
            }


        }

        public interface OnDeveloperItemClickedListener{
            void onDeveloperItemClicked(Developer dev);
        }
    }
}
