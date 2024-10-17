package com.example.tiktac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean active_game=true;
    private MaterialButton clear;
    private String player1 = "X";
    private String player2 = "O";
    private String currentPlayer = player1;
    int Opoint,Xpoint;
    private MaterialButton[][] board = new MaterialButton[3][3]; // Двумерный массив для кнопок
    TextView res,XpointView,OpointView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Xpoint=0;
        Opoint=0;
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.res);
        XpointView=findViewById(R.id.Xpoint);
        OpointView=findViewById(R.id.OPoint);
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(this);

        // Инициализация массива кнопок в цикле
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Формируем id кнопок динамически
                String buttonID = "r" + (i + 1) + "c" + (j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                board[i][j] = findViewById(resID);
                board[i][j].setOnClickListener(this); // Устанавливаем обработчик кликов
            }
        }


    }


    @Override
    public void onClick(View view) {
        try {
            if(!active_game)
                gamereset();
            MaterialButton button = (MaterialButton) view;
            String buttonText = button.getText().toString();

            if (view.getId() == R.id.clear) {
                // Очищаем текст всех кнопок в массиве board
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        board[i][j].setText(""); // Очищаем текст на кнопках
                    }
                }
                currentPlayer = player1; // Сбрасываем текущего игрока на игрока 1
                Xpoint=0;
                Opoint=0;
                XpointView.setText("X point "+Xpoint);
                OpointView.setText("O point "+Opoint);
            } else {
                if (buttonText.isEmpty()&& !checkForVictory(board,currentPlayer)) {
                    button.setText(currentPlayer);
                    checkGameState(board, currentPlayer);
                    if (currentPlayer.equals(player1)) {
                        currentPlayer = player2;
                        res.setText(currentPlayer);
                    } else {
                        currentPlayer = player1;
                        res.setText(currentPlayer);
                    }
                }

            }

        } catch (Exception e) {
            Log.e("MyApp", "Произошло исключение: " + e.toString());
            e.printStackTrace();
        }
    }

    private void checkGameState(MaterialButton[][] board, String currentPlayer) {
        // Проверка победы
        if (checkForVictory(board, currentPlayer)) {
            // Логика, когда текущий игрок победил
            if(currentPlayer.equals(player1))
                Xpoint++;
            else Opoint++;
            // Здесь можно показать сообщение о победе или что-то еще
            Toast.makeText(this, "Победил " + currentPlayer, Toast.LENGTH_SHORT).show();
            XpointView.setText("X point "+Xpoint);
            OpointView.setText("O point "+Opoint);
            active_game=false;
        } else if (isBoardFull()) {
            // Логика ничьей
            // Сообщение о ничьей
            Toast.makeText(this, "Ничья!", Toast.LENGTH_SHORT).show();
            active_game=false;
        }
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TextUtils.isEmpty(board[i][j].getText())) {
                    return false; // Если хоть одна ячейка пуста, возвращаем false
                }
            }
        }
        return true; // Все ячейки заполнены
    }

    void gamereset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setTextColor(Color.BLACK);// Очищаем текст на кнопках
            }
        }
        currentPlayer = player1; // Сбрасываем текущего игрока

        active_game=true;
    }
    }