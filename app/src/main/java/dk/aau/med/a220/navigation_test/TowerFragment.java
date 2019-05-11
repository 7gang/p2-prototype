package dk.aau.med.a220.navigation_test;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
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
   private FragmentTowerBinding binding;
   private Bitmap lv1;
   private Bitmap lv2;
   private Bitmap lv3;
   private ImageView img;
   private AnimationDrawable frameAnimation;
   private int level = Game.getUserTeamLevel();
   private boolean newLevel = Game.userTeamHasEarnedNewLevel();
   private int nextLevel = Game.getUserTeamPointsUntilNextLevel();
   private float score = Game.getUserTeamScores();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tower,container,false);
        View view = binding.getRoot();

        lv1 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv1));
        lv2 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv2));
        lv3 = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv3));
        binding.textView32.setText("Level: " + level + ", Population: " + Math.round(score));
        if (newLevel) binding.textView33.setText("Your team has earned a new level!");
        else binding.textView33.setText("Your team needs " + nextLevel + "  points until next level!");

        //binding.imageView2.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildinglv2)));
        //updateBuilding(3);
        img = binding.imageView10;
        img.setBackgroundResource(R.drawable.anim);
        frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();
        return view;
    }

    @Override
    public void onStart() {
        // update the user's tower level every time the user navigates to this screen
        updateBuilding(Game.getUserTeamLevel());
        setLargeText("" + Game.getUserTeamStanding() + "/4 TEAM MEMBERS STANDING");
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
