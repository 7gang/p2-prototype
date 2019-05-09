package dk.aau.med.a220.navigation_test;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dk.aau.med.a220.navigation_test.databinding.FragmentTowerBinding;

public class TowerFragment extends Fragment {
    FragmentTowerBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tower,container,false);
        View view = binding.getRoot();
        binding.imageView2.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv2)));
        updateBuilding(3);
        return view;
    }

    public void updateBuilding(int lv){
        switch (lv){
            case 1:
                binding.imageView2.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv1)));
                break;
            case 2:
                binding.imageView2.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv2)));
                break;
            case 3:
                binding.imageView2.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv3)));
                break;
        }
    }

}
