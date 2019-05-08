package dk.aau.med.a220.navigation_test;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TitleActivity extends AppCompatActivity{

    private static int fragmentNumber = 0; // keeping track of what fragment is shown to the user
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        this.getSupportActionBar().hide();

        loadFragment(new TitleFragment());

        mButton = this.findViewById(R.id.title_button);
    }

    @Override
    protected void onStart() {
        // Change the button text back to "NEXT" if a user was to re-enter the TitleActivity
        mButton.setText("NEXT");
        super.onStart();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_title, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void nextFragment(View view) {
        switch(fragmentNumber) {

            case 0:
                // TODO: load IntroFragment
                fragmentNumber++;
                break;

            case 1:
                // TODO: load SettingsFragment
                mButton.setText("BEGIN");
                fragmentNumber++;
                break;

            case 2:
                // reset fragment counter and launch in to main activity
                fragmentNumber = 0;

                Intent intent = new Intent(TitleActivity.this, MainActivity.class);
                startActivity(intent);

                //mButton.setText("NEXT");
                break;

        }
    }

}
