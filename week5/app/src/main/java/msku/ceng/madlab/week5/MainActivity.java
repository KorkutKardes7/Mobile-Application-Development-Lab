package msku.ceng.madlab.week5;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static final String PLAYER_1 = "X";
    static final String PLAYER_2 = "O";
    boolean player_1_turn = true;
    byte[][] board = new byte[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.board), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TableLayout table = findViewById(R.id.board);
        for (int r = 0; r < 3; r++) {
            TableRow tableRow = (TableRow) table.getChildAt(r);
            for (int c = 0; c < 3; c++) {
                Button button = (Button) tableRow.getChildAt(c);
                button.setOnClickListener(new CellListener(r,c));

            }
        }
    }

    public boolean is_valid_move(int row,int col){
        return board[row][col] == 0;
    }

    public int gameEnded(int row, int column) {
        int symbol = board[row][column];

        // Check column
        boolean win = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][column] != symbol) {
                win = false;
                break;
            }
        }
        if (win) return symbol;

        // Check row
        win = true;
        for (int j = 0; j < 3; j++) {
            if (board[row][j] != symbol) {
                win = false;
                break;
            }
        }
        if (win) return symbol;

        // Check main diagonal
        if (row == column) {
            win = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][i] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return symbol;
        }

        // Check anti-diagonal
        if (row + column == 2) {
            win = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][2 - i] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return symbol;
        }

        // Check for draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) return 0;

        // Game continues
        return -1;
    }



    class CellListener implements View.OnClickListener{

        int row, col;

        public CellListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {

            if (!is_valid_move(row,col)) {
                Toast.makeText(MainActivity.this, "Cell is already occupied", Toast.LENGTH_LONG).show();
                return;
            }

            if (player_1_turn) {
                    ((Button) v).setText(PLAYER_1);
                    board[row][col] = 1;
            }
            else {
                    ((Button) v).setText(PLAYER_2);
                    board[row][col] = 2;
            }

            if(gameEnded(row,col) == -1){
                player_1_turn =! player_1_turn;
            }
            else if(gameEnded(row,col) == 0){
                Toast.makeText(MainActivity.this,"It is a DRAW", Toast.LENGTH_LONG).show();
            }
            else if (gameEnded(row,col) == 1){
                Toast.makeText(MainActivity.this,"player 1 WINS", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(MainActivity.this,"player 2 WINS", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("player1turn",player_1_turn);
        byte[] boardsingle = new byte[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardsingle[3*i+j] = board[i][j];
            }
        }
        outState.putByteArray("board",boardsingle);

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        player_1_turn = savedInstanceState.getBoolean("player1turn");
        byte[] boardsingle = savedInstanceState.getByteArray("board");
        for (int i = 0; i < 9; i++) {
            board[i/3][i%3] = boardsingle[i];
        }

        TableLayout table = findViewById(R.id.board);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) row.getChildAt(j);
                if (board[i][j] == 1){
                    btn.setText("X");
                } else if (board[i][j] == 2) {
                    btn.setText("O");
                }
            }
        }
    }
}