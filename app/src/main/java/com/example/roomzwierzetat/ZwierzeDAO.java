package com.example.roomzwierzetat;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface ZwierzeDAO {
    @Dao
    public interface NauczycielDAO {
        @Insert
        void wstawNauczycielaDoBazy(Nauczyciel nauczyciel);

        @Insert
        void wstawListeNauczycieli(Nauczyciel... nauczyciele);

        @Delete
        void usunNauczycielaZBazy(Nauczyciel nauczyciel);

        @Update
        void zaktualizujDaneNauczyciela(Nauczyciel nauczyciel);

        @Query("SELECT * from nauczyciele")
        List<Nauczyciel> zwrocWszystkichNauczycieliZBazy();

        @Query("SELECT imie from nauczyciele WHERE przedmiot = :przedmiotArgument")
        List<String> zwrocImionaNauczycieliZPrzedmiotu(String przedmiotArgument);
    }
}
