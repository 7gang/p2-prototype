package dk.aau.med.a220.navigation_test;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.ArrayList;

import dk.aau.med.a220.navigation_test.databinding.FragmentCityBinding;

public class CityFragment extends Fragment {
    private FragmentCityBinding binding;
    private Bitmap lv1;
    private Bitmap lv2;
    private Bitmap lv3;
    private ImageView[] imageViewsA = new ImageView[3];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_city,container,false);
        View view = binding.getRoot();
        lv1 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv1));
        lv2 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv2));
        lv3 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv3));
        imageViewsA[0] = binding.imageView2;
        imageViewsA[1] = binding.imageView;
        imageViewsA[2] = binding.imageView3;
        return view;
    }

    public void onStart() {
        // update the user's tower level every time the user navigates to this screen
        updateBuilding();
        super.onStart();
    }

    public void updateBuilding(){
        for(int i =0 ;i<=2;i++){
            switch (Game.getLevels()[i]){
                case 1:
                    imageViewsA[i].setImageBitmap(lv1);
                    break;
                case 2:
                    imageViewsA[i].setImageBitmap(lv2);
                    break;
                case 3:
                    imageViewsA[i].setImageBitmap(lv3);
                    break;
            }}
    }
}
