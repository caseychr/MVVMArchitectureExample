package com.codinginflow.mvvmarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.codinginflow.mvvmarchitecture.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.codinginflow.mvvmarchitecture.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.codinginflow.mvvmarchitecture.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.codinginflow.mvvmarchitecture.EXTRA_ID";

    private EditText mETTitle;
    private EditText mETDescription;
    private NumberPicker mPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mETTitle = findViewById(R.id.et_title);
        mETDescription = findViewById(R.id.et_description);
        mPicker = findViewById(R.id.np_priority);
        mPicker.setMinValue(1);
        mPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            mETTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            mETDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            mPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = mETTitle.getText().toString();
        String description = mETDescription.getText().toString();
        int priority = mPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        int id = getIntent().getIntExtra(EXTRA_ID, 1);
        if(id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
