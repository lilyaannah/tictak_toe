package com.example.tiktac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView res;
    private MaterialButton clear,r1c1, r1c2, r1c3, r2c1, r2c2, r2c3, r3c1, r3c2, r3c3;
    String player1="X";
    String player2="O";
    String currentPlayer=player1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res=findViewById(R.id.res);
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(this);
        r1c1=findViewById(R.id.r1c1);
        r1c1.setOnClickListener(this);
        r1c2=findViewById(R.id.r1c2);
        r1c2.setOnClickListener(this);
        r1c3=findViewById(R.id.r1c3);
        r1c3.setOnClickListener(this);
        r2c1=findViewById(R.id.r2c1);
        r2c1.setOnClickListener(this);
        r2c2=findViewById(R.id.r2c2);
        r2c2.setOnClickListener(this);
        r2c3=findViewById(R.id.r2c3);
        r2c3.setOnClickListener(this);
        r3c1=findViewById(R.id.r3c1);
        r3c1.setOnClickListener(this);
        r3c2=findViewById(R.id.r3c2);
        r3c2.setOnClickListener(this);
        r3c3=findViewById(R.id.r3c3);
        r3c3.setOnClickListener(this);


    }
    int k=0;
    boolean active_game=true;
    @Override
    public void onClick(View view) {
        try {
            if(!active_game)
                gamereset();
            boolean hasWon=false;
            MaterialButton button = (MaterialButton) view;
            String buttonText = button.getText().toString();

            if (view.getId() == R.id.clear) {
                r1c1.setText("");
                r1c2.setText("");
                r1c3.setText("");
                r2c1.setText("");
                r2c2.setText("");
                r2c3.setText("");
                r3c1.setText("");
                r3c2.setText("");
                r3c3.setText("");
            } else {
                if (buttonText.isEmpty()&& (!hasWon)) {
                    button.setText(currentPlayer);

                    if (currentPlayer.equals(player1)) {
                        currentPlayer = player2;
                        res.setText(currentPlayer);
                    } else {
                        currentPlayer = player1;
                        res.setText(currentPlayer);
                    }
                }

            }
            MaterialButton[][] board = new MaterialButton[3][3];

            board[0][0] = r1c1;
            board[0][1] = r1c2;
            board[0][2] = r1c3;
            board[1][0] = r2c1;
            board[1][1] = r2c2;
            board[1][2] = r2c3;
            board[2][0] = r3c1;
            board[2][1] = r3c2;
            board[2][2] = r3c3;
            // После обновления кнопок, проверяем на выигрыш.
            hasWon = checkForVictory(board,currentPlayer);
            if (hasWon) {
                res.setText(currentPlayer + " Won");
                active_game=false;
            } else {
                // Проверяем на ничью, если все кнопки заполнены.
                if (isBoardFull()) {
                    res.setText("It's a Draw!");
                }
            }
        } catch (Exception e) {
            Log.e("MyApp", "Произошло исключение: " + e.toString());
            e.printStackTrace();
        }
    }
    void gamereset(){
        r1c1.setText("");
        r1c2.setText("");
        r1c3.setText("");
        r2c1.setText("");
        r2c2.setText("");
        r2c3.setText("");
        r3c1.setText("");
        r3c2.setText("");
        r3c3.setText("");

        r1c1.setTextColor(Color.BLACK);
        r1c2.setTextColor(Color.BLACK);
        r1c3.setTextColor(Color.BLACK);
        r2c1.setTextColor(Color.BLACK);
        r2c2.setTextColor(Color.BLACK);
        r2c3.setTextColor(Color.BLACK);
        r3c1.setTextColor(Color.BLACK);
        r3c2.setTextColor(Color.BLACK);
        r3c3.setTextColor(Color.BLACK);
        active_game=true;
    }

    boolean checkForVictory(MaterialButton[][] board,String currentPlayer) {


        // Check the rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().toString().equals(currentPlayer) &&
                    board[i][0].getText().toString().equals(board[i][1].getText().toString()) &&
                    board[i][1].getText().toString().equals(board[i][2].getText().toString())) {
                board[i][0].setTextColor(Color.GRAY);
                board[i][1].setTextColor(Color.GRAY);
                board[i][2].setTextColor(Color.GRAY);

                return true; // We won!
            }
        }

        // Check the columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i].getText().toString().equals(currentPlayer) &&
                    board[0][i].getText().toString().equals(board[1][i].getText().toString()) &&
                    board[1][i].getText().toString().equals(board[2][i].getText().toString())) {
                board[0][i].setTextColor(Color.GRAY);
                board[1][i].setTextColor(Color.GRAY);
                board[2][i].setTextColor(Color.GRAY);
                return true; // We won!
            }
        }

        // Check top left diagonal
        if (board[0][0].getText().toString().equals(currentPlayer) &&
                board[0][0].getText().toString().equals(board[1][1].getText().toString()) &&
                board[1][1].getText().toString().equals(board[2][2].getText().toString())) {
            board[0][0].setTextColor(Color.GRAY);
            board[1][1].setTextColor(Color.GRAY);
            board[2][2].setTextColor(Color.GRAY);

            return true; // We won!
        }

        // Check top right diagonal
        if (board[2][0].getText().toString().equals(currentPlayer) &&
                board[2][0].getText().toString().equals(board[1][1].getText().toString()) &&
                board[1][1].getText().toString().equals(board[0][2].getText().toString())) {
            board[2][0].setTextColor(Color.GRAY);
            board[1][1].setTextColor(Color.GRAY);
            board[0][2].setTextColor(Color.GRAY);

            return true; // We won!
        }

        return false;
    }

    boolean isBoardFull() {
        return !TextUtils.isEmpty(r1c1.getText()) && !TextUtils.isEmpty(r1c2.getText()) && !TextUtils.isEmpty(r1c3.getText()) &&
                !TextUtils.isEmpty(r2c1.getText()) && !TextUtils.isEmpty(r2c2.getText()) && !TextUtils.isEmpty(r2c3.getText()) &&
                !TextUtils.isEmpty(r3c1.getText()) && !TextUtils.isEmpty(r3c2.getText()) && !TextUtils.isEmpty(r3c3.getText());
    }
    }