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
   //private Bitmap blv1, blv2, blv3, blv4, blv5,rlv1, rlv2, rlv3, rlv4, rlv5, glv1, glv2, glv3, glv4, glv5;
   private ImageView img;
   private AnimationDrawable frameAnimation;
   private int level = Game.getUserTeamLevel();
   private boolean newLevel = Game.userTeamHasEarnedNewLevel();
   private int nextLevel = Game.getUserTeamPointsUntilNextLevel();
   private float score = Game.getUserTeamScores();
   private Bitmap[] bitmaps = new Bitmap[5];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tower,container,false);
        View view = binding.getRoot();
        switch (Game.teamNumber) {
            case 2:
            bitmaps[0] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b1));
            bitmaps[1] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b2));
            bitmaps[2] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b3));
            bitmaps[3] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b4));
            bitmaps[4] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b5));
            break;
            case 1:
            bitmaps[0] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r1));
            bitmaps[1] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r2));
            bitmaps[2] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r3));
            bitmaps[3] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r4));
            bitmaps[4] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r5));
            break;
            case 3:
            bitmaps[0] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g1));
            bitmaps[1] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g2));
            bitmaps[2] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g3));
            bitmaps[3] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g4));
            bitmaps[4] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g5));
            break;
        }
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
                binding.imageView2.setImageBitmap(bitmaps[0]);
                break;
            case 2:
                binding.imageView2.setImageBitmap(bitmaps[1]);
                break;
            case 3:
                binding.imageView2.setImageBitmap(bitmaps[2]);
                break;
            case 4:
                binding.imageView2.setImageBitmap(bitmaps[3]);
                break;
            case 5:
                binding.imageView2.setImageBitmap(bitmaps[4]);
                break;
        }
    }

}
