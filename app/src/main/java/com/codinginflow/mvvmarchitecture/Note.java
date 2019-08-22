package com.codinginflow.mvvmarchitecture;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Annotation for Room. At compile time it will create a table for this in SQLite.
 * Note is a simple model.
 */
@Entity(tableName = "note_table")
public class Note {

    // SQLite will autogenerate this with each new row
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;

    //@ColumnInfo(name = "priority_column") Can rename the column name in SQLite to priority_column
    //@Ignore Can tell Room to not add this to the table
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
