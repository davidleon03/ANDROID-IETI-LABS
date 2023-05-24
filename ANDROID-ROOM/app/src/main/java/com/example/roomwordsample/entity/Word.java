package com.example.roomwordsample.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}

    /*@Entity(tableName = "word_table")
    public class Word {

        @PrimaryKey(autoGenerate = true)
        private int id;

        @NonNull
        private String word;
        //..other fields, getters, setters
    }*/
}