package com.example.cardvascular.Onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardvascular.R;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardinViewHolder>{

    private List<OnboardingItems> onboardingItems;

    public OnboardingAdapter(List<OnboardingItems> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.onboarding_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardinViewHolder holder, int position) {
        holder.setOnboardingdata(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardinViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageOnboarding;


        public OnboardinViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle=itemView.findViewById(R.id.textTitle);
            textDescription=itemView.findViewById(R.id.textDescription);
            imageOnboarding=itemView.findViewById(R.id.imageOnboarding);
        }
        void setOnboardingdata(OnboardingItems onboardingItems){
            textTitle.setText(onboardingItems.getTitle());
            textDescription.setText(onboardingItems.getDescription());
            imageOnboarding.setImageResource(onboardingItems.getImage());
        }
    }
}
