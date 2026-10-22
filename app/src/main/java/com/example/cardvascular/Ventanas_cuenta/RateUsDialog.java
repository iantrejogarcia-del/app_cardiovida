package com.example.cardvascular.Ventanas_cuenta;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.example.cardvascular.R;

public class RateUsDialog extends Dialog {

    private float userRate = 0;
    public RateUsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_us_dialog_layout);

        final AppCompatButton CalfAhoraBtn = findViewById(R.id.CalfAhoraBtn);
        final AppCompatButton DespuesBtn = findViewById(R.id.DespuesBtn);
        final ImageView imgcamb = findViewById(R.id.imgcamb);
        final RatingBar ratingBar = findViewById(R.id.RatinBar);

        CalfAhoraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Calificación enviada correctamente", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        DespuesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 1) {
                    imgcamb.setImageResource(R.drawable.enojado);
                } else if (rating <= 2) {
                    imgcamb.setImageResource(R.drawable.mee);
                } else if (rating <= 3) {
                    imgcamb.setImageResource(R.drawable.mehh);
                } else if (rating <= 4) {
                    imgcamb.setImageResource(R.drawable.meencanta);
                } else if (rating <= 5) {
                    imgcamb.setImageResource(R.drawable.megusta);
                }

                animateImage(imgcamb);

                userRate = rating;
            }
        });
    }

    private void animateImage(ImageView ratinImg){

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratinImg.startAnimation(scaleAnimation);
    }
}
