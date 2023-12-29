package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    boolean clickCounter = false;
    boolean start= false;
    Button[][] buttonMat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMat=new Button[3][3];
        buttonMat[0][0]=findViewById(R.id.button11);
        buttonMat[0][1]=findViewById(R.id.button12);
        buttonMat[0][2]=findViewById(R.id.button13);

        buttonMat[1][0]=findViewById(R.id.button21);
        buttonMat[1][1]=findViewById(R.id.button22);
        buttonMat[1][2]=findViewById(R.id.button23);

        buttonMat[2][0]=findViewById(R.id.button31);
        buttonMat[2][1]=findViewById(R.id.button32);
        buttonMat[2][2]=findViewById(R.id.button33);

    }

    public void startGame(View v){
        start = true;
        Snackbar.make(findViewById(R.id.start), "Game started", Snackbar.LENGTH_LONG).show();
    }

    public int  gameFinished(){
        for(int i=0;i<3;i++) {
            int count = 0;
            int count2 = 0;
            for (int j = 1; j < 3; j++) {
                if (buttonMat[i][j].getText().length() == 1 && buttonMat[i][j].getText().equals(buttonMat[i][j - 1].getText()))
                    count++;
                if (buttonMat[j][i].getText().length() == 1 && buttonMat[j][i].getText().equals(buttonMat[j - 1][i].getText()))
                    count2++;
            }
            if (count == 2)
                return buttonMat[i][0].getText().equals("O") ? 0 : 1;

            if (count2 == 2)
                return buttonMat[0][i].getText().equals("0") ? 0 : 1;
        }

        int count1=0,count2=0;
        for(int i=0;i<2;i++)
        {
            if(buttonMat[i][i].getText().length()==1 && buttonMat[i][i].getText().equals(buttonMat[i+1][i+1].getText()))count1++;
            if(buttonMat[i][2-i].getText().length()==1 && buttonMat[i][2-i].getText().equals((buttonMat[i+1][2-i-1]).getText()))count2++;
        }
        if(count1==2 || count2==2)
            return buttonMat[1][1].getText().equals("O") ? 0 : 1;

        return -1;
    }


    public void resetGame(View v)
    {

        start = false;
        clickCounter = false;
        for(var i:buttonMat)
            for(var j:i)j.setText("Button");

    }
    public void clickButton(View v){
        if(!start){
            Snackbar.make(findViewById(R.id.start), "Game not started", Snackbar.LENGTH_LONG).show();
            return;
        }
        Button button = (Button) v;
        if(!button.getText().equals("Button")){
            Snackbar.make(v,"Button Already Clicked", Snackbar.LENGTH_LONG).show();
            return;
        }
        if(clickCounter)button.setText("X");
        else button.setText(("O"));
        clickCounter = !clickCounter;
        switch (gameFinished()){
            case 0: // Game finished by player 1
                Snackbar.make(v,"Game Finished by Player 1", Snackbar.LENGTH_LONG).show();
                break;

            case 1:
                Snackbar.make(v,"Game Finished by Player 2", Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}