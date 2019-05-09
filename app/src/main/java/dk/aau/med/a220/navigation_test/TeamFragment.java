package dk.aau.med.a220.navigation_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TeamFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team, null);

        rootView.findViewById(R.id.team_1_button).setOnClickListener(this);
        rootView.findViewById(R.id.team_2_button).setOnClickListener(this);
        rootView.findViewById(R.id.team_3_button).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        System.out.println("onClick called!");

        switch(v.getId()) {

            case R.id.team_1_button:
                Game.teamNumber = 1;
                break;

            case R.id.team_2_button:
                Game.teamNumber = 2;
                break;

            case R.id.team_3_button:
                Game.teamNumber = 3;
                break;

            default:
                System.out.println("defaulting...");
                return;

        }
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
