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
import android.widget.TextView;


import java.util.ArrayList;

import dk.aau.med.a220.navigation_test.databinding.FragmentCityBinding;

public class CityFragment extends Fragment {
    private FragmentCityBinding binding;
    private Bitmap[] bitmaps = new Bitmap[5];
    private ImageView[] imageViewsA = new ImageView[3];
    private String team1 = "Team 1";
    private String team2 = "Team 2";
    private String team3 = "Team 3";
    private float [] rates = Game.getRates();
    private float [] scores = Game.getScores();
    private int [] pointsToNextLevel = Game.getPointsUntilNextLevel();
    private boolean [] newLevel = Game.hasEarnedNewLevel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_city,container,false);
        View view = binding.getRoot();

        imageViewsA[0] = binding.imageView2;
        imageViewsA[1] = binding.imageView;
        imageViewsA[2] = binding.imageView3;

        binding.textView.setText("Pop: " + Math.round(scores[0]));
        binding.textView4.setText("Pop: " + Math.round(scores[1]));
        binding.textView5.setText("Pop: " + Math.round(scores[2]));
        binding.textView6.setText(team1);
        binding.textView2.setText(team2);
        binding.textView3.setText(team3);
        binding.textView27.setText("Team 1: " + Math.round(rates[0]));
        binding.textView26.setText("Team 2: " + Math.round(rates[1]));
        binding.textView25.setText("Team 3: " + Math.round(rates[2]));
        binding.textView30.setText("Until next level: " + pointsToNextLevel[0]);
        binding.textView29.setText("Until next level: " + pointsToNextLevel[1]);
        binding.textView31.setText("Until next level: " + pointsToNextLevel[2]);
        return view;
    }

    public void onStart() {
        // show the selected team in the city overview
        updateBuilding();
        switch (Game.teamNumber){
            case 1:
                binding.textView6.setText("Your team");
                break;
            case 2:
                binding.textView2.setText("Your team");
                break;
            case 3:
                binding.textView3.setText("Your team");
                break;
        }
        if (newLevel[0]) binding.textView28.setText("Team 1 has earned a new level!");
        if (newLevel[1]) binding.textView28.setText("Team 2 has earned a new level!");
        if (newLevel[2]) binding.textView28.setText("Team 3 has earned a new level!");
        if (newLevel[0] && newLevel[1]) binding.textView28.setText("Team 1 & 2 have earned a new level!");
        if (newLevel[0] && newLevel[2]) binding.textView28.setText("Team 1 & 3 have earned a new level!");
        if (newLevel[1] && newLevel[2]) binding.textView28.setText("Team 2 & 3 have earned a new level!");
        if (newLevel[0] && newLevel[1] && newLevel[2]) binding.textView28.setText("All teams have earned a new level!");
        super.onStart();
    }

    public void updateBuilding(){
        for(int i =0 ;i<=2;i++){
            getColor(i);
            switch (Game.getLevels()[i]){
                case 1:
                    imageViewsA[i].setImageBitmap(bitmaps[0]);
                    break;
                case 2:
                    imageViewsA[i].setImageBitmap(bitmaps[1]);
                    break;
                case 3:
                    imageViewsA[i].setImageBitmap(bitmaps[2]);
                    break;
                case 4:
                    imageViewsA[i].setImageBitmap(bitmaps[3]);
                    break;
                case 5:
                    imageViewsA[i].setImageBitmap(bitmaps[4]);
                    break;
            }}
    }
    void getColor(int input){
        switch (input) {
            case 1:
                bitmaps[0] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b1));
                bitmaps[1] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b2));
                bitmaps[2] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b3));
                bitmaps[3] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b4));
                bitmaps[4] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b5));
                break;
            case 0:
                bitmaps[0] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r1));
                bitmaps[1] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r2));
                bitmaps[2] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r3));
                bitmaps[3] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r4));
                bitmaps[4] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.r5));
                break;
            case 2:
                bitmaps[0] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g1));
                bitmaps[1] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g2));
                bitmaps[2] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g3));
                bitmaps[3] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g4));
                bitmaps[4] = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g5));
                break;
        }
    }
}
