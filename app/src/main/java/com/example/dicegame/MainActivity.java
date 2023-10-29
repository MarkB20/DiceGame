package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button about;
    Button easyGame;
    Button hardGame;
    EditText score;

    String[] ScoreTxt = new String[1];
    int[] maxScore = {101};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        about = findViewById(R.id.about);
        easyGame = findViewById(R.id.EasyGame);
        hardGame = findViewById(R.id.HardGame);
        score = findViewById(R.id.editScore);
        score.getText();

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadFragment (new AboutFragment());
            }
        });



        //point 1
        easyGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mode("Easy");
            }
        });

        hardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mode("Hard");
            }
        });




    }

        //point 10
        public void mode(String mode){
            ScoreTxt[0] = score.getText().toString();
            if(!ScoreTxt[0].isEmpty()){
                maxScore[0] = Integer.parseInt(score.getText().toString());

            }


            Intent intent = new Intent(getBaseContext(), NewGame.class);
            intent.putExtra("mode", mode);
            intent.putExtra("max", maxScore[0]);
            startActivity(intent);


    }






    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();


    }
}