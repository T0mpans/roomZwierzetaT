package com.example.roomzwierzetat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "zwierzeta")
public class Zwierze {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nazwa;
    public String gatunek;
    public int waga;
    public boolean czyMiesoZerny;

    public Zwierze(String nazwa, String gatunek, int waga, boolean czyMiesoZerny) {
        this.nazwa = nazwa;
        this.gatunek = gatunek;
        this.waga = waga;
        this.czyMiesoZerny = czyMiesoZerny;
    }

    @Override
    public String toString() {
        return "Zwierze{" +
                "nazwa='" + nazwa + '\'' +
                ", gatunek='" + gatunek + '\'' +
                '}';
    }
}
