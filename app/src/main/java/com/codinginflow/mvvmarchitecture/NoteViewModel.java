package com.codinginflow.mvvmarchitecture;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Why not extend ViewModel? AndroidViewModel is a subclass of ViewModel and the main difference is in the
 * AndroidViewModel we get passed the Application in the constructor which we can then use. You should NEVER store
 * the context of an Activity or View that references an Activity in the ViewModel since the ViewModel is meant
 * to outlive the View when it dies. This would result in a memory leak.
 */

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
