package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.tts.UtteranceProgressListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.interfaces.CorrectAnswerCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.customclasses.AppControl;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.customclasses.Constant;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.LearningDataModel;
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Naynesh Patel on 06-Feb-19.
 */
public class ListenGuessAdapter extends RecyclerView.Adapter<ListenGuessAdapter.ViewHolder> {


    Context context;
    ArrayList<LearningDataModel> examQuestionAnswerList;
    LearningDataModel learningDataModel;
    CorrectAnswerCallback answerCallback;

    public ListenGuessAdapter(Context context, ArrayList<LearningDataModel> examQuestionAnswerList, LearningDataModel learningDataModel, CorrectAnswerCallback answerCallback) {
        this.context = context;
        this.examQuestionAnswerList = examQuestionAnswerList;
        this.learningDataModel = learningDataModel;
        this.answerCallback = answerCallback;
        AppControl.textToSpeech.setOnUtteranceProgressListener(new ListenAndGuessUtteranceProgressListener());
    }


//    TextToSpeech textToSpeech;

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        LinearLayout lloutExamAnswer;
        ImageView iVExamAnswer;

        ViewHolder(@NonNull View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.cvCardSubHome);
            this.iVExamAnswer = (ImageView) view.findViewById(R.id.iVExamAnswer);
            this.lloutExamAnswer = (LinearLayout) view.findViewById(R.id.lloutExamAnswer);
//            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
//                @Override
//                public void onInit(int status) {
//                    if (status != TextToSpeech.ERROR) {
//                        textToSpeech.setLanguage(Locale.UK);
//                    }
//                }
//            });
        }
    }


    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_exam_phonics, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(context).load(examQuestionAnswerList.get(i).image).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.iVExamAnswer);
        viewHolder.cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bubble_anim));

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (learningDataModel.showTitle.equals(examQuestionAnswerList.get(i).showTitle)) {
                    Toast.makeText(context, "Correct Answer", Toast.LENGTH_SHORT).show();
                    viewHolder.lloutExamAnswer.setBackgroundColor(context.getResources().getColor(R.color.colorCorrect));
                    if (Utils.getPref(Constant.SOUND,true)) {
                        AppControl.textToSpeech.speak("Correct Answer", TextToSpeech.QUEUE_FLUSH, null,"LISTENANDGUESS");
                    }
                } else {
                    Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    viewHolder.lloutExamAnswer.setBackgroundColor(context.getResources().getColor(R.color.colorWrong));
                    if (Utils.getPref(Constant.SOUND,true)) {
                        AppControl.textToSpeech.speak("Wrong Answer", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });
    }

    public int getItemCount() {
        return examQuestionAnswerList.size();
    }


private class ListenAndGuessUtteranceProgressListener extends UtteranceProgressListener {

        @Override
        public void onStart(String utteranceId) {
            // Called when TTS starts speaking
        }

        @Override
        public void onDone(String utteranceId) {

            if (utteranceId.contentEquals("LISTENANDGUESS")){
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        answerCallback.onAnswerSelected();
                    }
                });
            }
        }

        @Override
        public void onError(String utteranceId) {

        }
    }

}