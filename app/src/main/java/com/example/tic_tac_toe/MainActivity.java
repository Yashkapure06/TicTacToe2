package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    0 = yellow and 1 = red;

    int activePlayer = 0;

    boolean gameIsActive = true;

//     2 means unplayed

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [] [] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    @SuppressLint("SetTextI18n")
    public void dropIn(View view){

        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;


            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(400);

            for(int[] winningPosition : winningPositions){
//                This means all three are the same
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2){


//                    Someone has won!
                    gameIsActive = false;
                    String winner = "Red";
                    if(gameState[winningPosition[0]] == 0){

                        winner = "Yellow";

                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                }else{
                    boolean gameIsOver = true;
                    for (int counterState: gameState){
                        if(counterState ==2) gameIsOver = false;
                    }
                    if(gameIsOver){
                        TextView winnerMessage = findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a Draw!");

                        LinearLayout layout = findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }

    }

    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout =  findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
//this sets the state of the game to original  i.e; the game restarts when any of the player has won
        for (int  i = 0; i<gameState.length; i++){
            gameState [i] = 2;
        }
//        GridLayout gridLayout = findViewById(R.id.gridLayout);
////getChildCount helps in showing the number of child elements in the grid layout
//        for(int i = 0; i<gridLayout.getChildCount(); i++){
////            this will reset  the images of the red and yellow player to blank
//            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
//        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}