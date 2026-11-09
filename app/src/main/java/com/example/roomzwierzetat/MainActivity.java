package com.example.roomzwierzetat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ZwierzetaDataBase zwierzetaDataBase;
    Button button, buttonAktualizuj;
    EditText editText, editText2, editText3;
    ListView listView;
    List<Zwierze> zwierzeList;
    ArrayAdapter<Zwierze> arrayAdapter;
    Zwierze kliknieteZwierze;

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

        button = findViewById(R.id.button);
        buttonAktualizuj = findViewById(R.id.buttonAktualizacja);
        editText = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        editText3 = findViewById(R.id.editTextText3);

        listView = findViewById(R.id.listView);
        zwierzeList = zwierzetaDataBase.getZwierzeDAO().zwrocWszystkieZwierzetaZBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                zwierzeList);
        listView.setAdapter(arrayAdapter);
    }
}