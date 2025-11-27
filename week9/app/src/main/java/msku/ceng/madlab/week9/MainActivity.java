package msku.ceng.madlab.week9;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteFragment.OnNoteListInteractionListener {

    boolean displayingEditor = false;
    Note editingNote;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        notes = retrieveNotes();
        if (!displayingEditor){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, NoteFragment.newInstance(notes));
            ft.commit();
        }
        else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container,EditNoteFragment.newInstance(readContent(editingNote)));
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    private String readContent(Note editingNote) {
    }

    private ArrayList<Note> retrieveNotes() {

    }

    @Override
    public void onNoteSelected(Note note) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}