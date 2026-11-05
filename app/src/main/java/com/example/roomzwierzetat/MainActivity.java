package com.example.roomzwierzetat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    ZwierzetaDataBase zwierzetaDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zwierzetaDataBase = Room.databaseBuilder(
                getApplicationContext(), ZwierzetaDataBase.class, "zwierzeta_db" )
                .allowMainThreadQueries().build();

        zwierzetaDataBase.getZwierzeDAO().wstawZwierzeDoBazy(
                new Zwierze("pies", "ssak", 12, true)
        );
    }
}