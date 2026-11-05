package com.example.bazadanychroombb3plusy;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "zwierzeta")
public class Zwierze {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String gatunek;
    public int waga;
    public int ileNog;

    public Zwierze(String gatunek, int waga, int ileNog) {
        this.gatunek = gatunek;
        this.waga = waga;
        this.ileNog = ileNog;
    }
}
