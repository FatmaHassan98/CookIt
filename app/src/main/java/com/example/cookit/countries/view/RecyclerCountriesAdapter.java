package com.example.cookit.countries.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookit.R;
import com.example.cookit.itemPage.view.ItemPageActivity;
import com.example.cookit.model.MealModel;

import java.io.Serializable;
import java.util.List;

public class RecyclerCountriesAdapter extends RecyclerView.Adapter<RecyclerCountriesAdapter.ViewHolder> {

        private final Context context;
        private List<MealModel> list;
        public static final String TAG = "RECYCLER";

        public static class ViewHolder extends RecyclerView.ViewHolder{
            public ImageView imageView;
            public TextView name;
            public CardView cardItem;
            public View view;

            public ViewHolder(View v){
                super(v);
                view = v;
                imageView = v.findViewById(R.id.mealImage);
                name = v.findViewById(R.id.mealName);
                cardItem = v.findViewById(R.id.mealItemCard);

            }
        }

        public RecyclerCountriesAdapter(Context context, List<MealModel> list) {
            this.context = context;
            this.list = list;
        }
        public void setCountryMealModelList(List<MealModel> countryMealModelList) {
            this.list = countryMealModelList;
        }

        @NonNull
        @Override
        public RecyclerCountriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.mealitem,parent,false);
            RecyclerCountriesAdapter.ViewHolder viewHolder = new RecyclerCountriesAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerCountriesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.name.setText(list.get(position).getStrMeal());
            Glide.with(context).load(list.get(position).getStrMealThumb())
                    .apply(new RequestOptions().override(holder.imageView.getWidth(),holder.imageView.getHeight()))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView);
            holder.cardItem.setOnClickListener(e->{
                Intent myIntent =new Intent(context, ItemPageActivity.class);
                myIntent.putExtra("MEAL_NAME",list.get(position).getStrMeal());
                context.startActivity(myIntent);
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }




