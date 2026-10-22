package com.example.cardvascular.Onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    OnboardingAdapter onboardingAdapter;
    LinearLayout layoutOnboardingIndicator;
    MaterialButton buttonOboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_avtivity);

        buttonOboardingAction=findViewById(R.id.ButtonOnboarding);
        layoutOnboardingIndicator = findViewById(R.id.layoutOnboarding);
        setupOnboardingItems();
        ViewPager2 onboardingViewPager = findViewById(R.id.OnboardingViewpager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicator();
        setCurrentOboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOboardingIndicator(position);
            }
        });

        buttonOboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onboardingViewPager.getCurrentItem()+1<onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems() {
        List<OnboardingItems> onboardingItems = new ArrayList<>();

        OnboardingItems foodItems = new OnboardingItems();
        foodItems.setTitle("CORAZON SALUDABLE");
        foodItems.setDescription("Comienza tu viaje hacia un corazon mas saludable con CARDIO VIDA");
        foodItems.setImage(R.drawable.uno);

        OnboardingItems foodDelivery = new OnboardingItems();
        foodDelivery.setTitle("ALIMENTACION VITAL");
        foodDelivery.setDescription("Encuentra recetas saludables y planes de comidas adaptados a tus necesidades");
        foodDelivery.setImage(R.drawable.onbdos);

        OnboardingItems foodPayment = new OnboardingItems();
        foodPayment.setTitle("CONSEJOS CLAVE");
        foodPayment.setDescription("Descubre como mejorar tu salud fisica y mental con nuestros consejos y herramientas practicas");
        foodPayment.setImage(R.drawable.onbtres);

        onboardingItems.add(foodItems);
        onboardingItems.add(foodDelivery);
        onboardingItems.add(foodPayment);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicator() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);
        for(int i=0; i<indicators.length; i++){
            indicators[i]= new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));

            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentOboardingIndicator(int index){
        int childCount=layoutOnboardingIndicator.getChildCount();
        for(int i=0; i<childCount; i++){
            ImageView imageView=(ImageView) layoutOnboardingIndicator.getChildAt(i);
            if(i== index){
             imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));
            }else{
               imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            }
        }

        if(index==onboardingAdapter.getItemCount()-1){
            buttonOboardingAction.setText("Iniciar");
        }else{
            buttonOboardingAction.setText("Siguiente");
        }
    }
}