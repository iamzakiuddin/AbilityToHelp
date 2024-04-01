package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.customclasses.Constant;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.ModelVideo;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;


public class VideoPlayActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        context = this;
        initDefine();
    }



    ArrayList<ModelVideo> arrOfVideoList;

    private void initDefine() {
        rvVideoList = findViewById(R.id.rvVideoList);
        youTubePlayerView = findViewById(R.id.youTubePlayerView);
        videoTitleOfVideo = findViewById(R.id.videoTitleOfVideo);
        Intent intent = getIntent();
        arrOfVideoList = (ArrayList<ModelVideo>) intent.getSerializableExtra("ArrayOfVideo");
        POSITION = intent.getIntExtra("Position", 0);
        setRvVideoAdapter();
        initVideoPlayer();
    }

    YouTubePlayerView youTubePlayerView;
    int POSITION;
    TextView videoTitleOfVideo;
    YouTubePlayer player = null;

    private void initVideoPlayer() {
        videoTitleOfVideo.setText(arrOfVideoList.get(POSITION).getVideoTitle());
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                player = youTubePlayer;
                player.loadVideo(Constant.VIDEO_ID, 0f);
            }
        });
    }



    RecyclerView rvVideoList;
    VideoAdapter videoAdapter;

    private void setRvVideoAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvVideoList.setLayoutManager(linearLayoutManager);
        videoAdapter = new VideoAdapter();
        rvVideoList.setAdapter(videoAdapter);

    }


    class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

        class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            ImageView imgThumbnail;
            TextView txtVideoTitle;

            ViewHolder(@NonNull View view) {
                super(view);
                this.txtVideoTitle = view.findViewById(R.id.txtVideoDescription);
                this.cardView = view.findViewById(R.id.cardViewMain);
                this.imgThumbnail = view.findViewById(R.id.ivThumbnailView);
            }
        }

        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item_related_list, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {

            viewHolder.txtVideoTitle.setText(arrOfVideoList.get(i).getVideoTitle());

            Glide.with(context)
                    .load(arrOfVideoList.get(i).getVideoThumb())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(viewHolder.imgThumbnail);


            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    POSITION = i;
                    Constant.VIDEO_ID = arrOfVideoList.get(i).getVideoId();
                    if (player != null) {
                        player.loadVideo(Constant.VIDEO_ID,0f);

                    }
                    videoTitleOfVideo.setText(arrOfVideoList.get(POSITION).getVideoTitle());
                }
            });
        }

        public int getItemCount() {
            return arrOfVideoList.size();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
    }
}
