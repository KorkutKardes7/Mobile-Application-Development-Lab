package msku.ceng.madlab.week5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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



        }
    }





}