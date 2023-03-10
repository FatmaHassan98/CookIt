package com.example.cookit.favoritemeals.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.cookit.favoritemeals.view.FavViewInterface;
import com.example.cookit.model.MealModel;
import com.example.cookit.model.modelFirebase.RepositoryFirebaseInterface;
import com.example.cookit.model.modelFirebase.UserModel;
import com.example.cookit.model.retrofit.RepositoryInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavoriteMealsPresenter implements FavPresenterInterface{
    private RepositoryInterface repositoryInterface;

    private RepositoryFirebaseInterface repositoryFirebaseInterface;


    public FavoriteMealsPresenter(RepositoryInterface repositoryInterface,RepositoryFirebaseInterface repositoryFirebaseInterface){
        this.repositoryInterface = repositoryInterface ;
        this.repositoryFirebaseInterface = repositoryFirebaseInterface;
    }


    @Override
    public Single<List<MealModel>> getAllStoredFavorites() {
        return repositoryInterface.getAllStoredFavorites();
    }

    @Override
    public void removeFromFavorite(MealModel mealModel) {
        repositoryInterface.removeFavorite(mealModel);
    }

    @Override
    public void updateFavoriteInFirebase(UserModel userModel) {
        repositoryFirebaseInterface.updateFavoriteInFirebase(userModel);
    }

    @Override
    public UserModel getSavedData() {
        return repositoryFirebaseInterface.getSavedUserData();
    }
}
