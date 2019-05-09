package dk.aau.med.a220.navigation_test;

import android.app.Activity;
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

import dk.aau.med.a220.navigation_test.databinding.FragmentTowerBinding;

public class TowerFragment extends Fragment {
   private FragmentTowerBinding binding;
   private Bitmap lv1;
   private Bitmap lv2;
   private Bitmap lv3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tower,container,false);
        View view = binding.getRoot();

        lv1 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv1));
        lv2 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv2));
        lv3 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv3));
        //binding.imageView2.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv2)));
        //updateBuilding(3);
        return view;
    }

    @Override
    public void onStart() {
        // update the user's tower level every time the user navigates to this screen
        updateBuilding(Game.getUserTeamLevel());
        super.onStart();
    }

    public void  setLargeText(String text){
        binding.textView2.setText(text);
    }

    public void  setSmallText(String text){
        binding.textView2.setText(text);
    }

    public void updateBuilding(int lv){
        switch (lv){
            case 1:
                binding.imageView2.setImageBitmap(lv1);
                break;
            case 2:
                binding.imageView2.setImageBitmap(lv2);
                break;
            case 3:
                binding.imageView2.setImageBitmap(lv3);
                break;
        }
    }

}
