package com.example.cookit.favoritemeals.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookit.R;
import com.example.cookit.itemPage.view.ItemPageActivity;
import com.example.cookit.model.MealModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.ViewHolder> {
    Context context;
    List<MealModel> model;
    OnFavClickListner onFavClickListner;
    public FavoriteMealsAdapter(Context context, List<MealModel> mealModelList,OnFavClickListner onFavClickListner){
        this.context=context;
        this.model=mealModelList;
        this.onFavClickListner = onFavClickListner;
    }
    @NonNull
    @Override
    public FavoriteMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.favorite_meal_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.meal_name.setText(model.get(position).getStrMeal());
        Glide.with(context).load(model.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.meal_image.getWidth(),holder.meal_image.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.meal_image);
        holder.remove_from_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavClickListner.onRemoveFavClick(model.get(position));
            }
        });
//        holder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, ItemPageActivity.class);
//                intent.putExtra("mealName",model.get(holder.getAdapterPosition()).getStrMeal());
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public void setList(List<MealModel> model){
        this.model = model ;
    }

    public void removeFavorite(MealModel mealModel){
        this.model.remove(mealModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView meal_name;
        CircleImageView meal_image;
        ImageButton remove_from_fav;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_name =itemView.findViewById(R.id.ingredient_meal_name);
            meal_image =itemView.findViewById(R.id.profileUserImage);
            remove_from_fav =itemView.findViewById(R.id.remove_from_fav);
            item=itemView.findViewById(R.id.favoriteItemCard);
        }
    }
}

