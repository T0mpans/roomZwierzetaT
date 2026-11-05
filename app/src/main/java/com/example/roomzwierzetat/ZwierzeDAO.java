package com.example.roomzwierzetat;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

    @Dao
    public interface ZwierzeDAO {
        @Insert
        void wstawZwierzeDoBazy(Zwierze zwierze);

        @Insert
        void wstawListeZwierzat(Zwierze... zwierzeta);

        @Delete
        void usunNauczycielaZBazy(Zwierze zwierze);

        @Update
        void zaktualizujDaneNauczyciela(Zwierze zwierze);


        @Query("SELECT * from zwierzeta")
        List<Zwierze> zwrocWszystkieZwierzetaZBazy();

        @Query("SELECT nazwa from zwierzeta WHERE gatunek = :gatunekArgument")
        List<String> zwrocImionaNauczycieliZPrzedmiotu(String gatunekArgument);
    }

