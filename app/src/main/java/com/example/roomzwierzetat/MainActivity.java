package com.example.roomzwierzetat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ZwierzetaDataBase zwierzetaDataBase;
    Button button, buttonAktualizuj;
    CheckBox checkBox;
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

//        zwierzetaDataBase.getZwierzeDAO().wstawZwierzeDoBazy(
//                new Zwierze("pies", "ssak", 12, true)
//        );

        button = findViewById(R.id.button);
        buttonAktualizuj = findViewById(R.id.buttonAktualizacja);
        checkBox = findViewById(R.id.checkBox);
        editText = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        editText3 = findViewById(R.id.editTextText3);

        listView = findViewById(R.id.listView);
        zwierzeList = zwierzetaDataBase.getZwierzeDAO().zwrocWszystkieZwierzetaZBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                zwierzeList);
        listView.setAdapter(arrayAdapter);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Zwierze zwierze = new Zwierze(
                                editText.getText().toString(),
                                editText2.getText().toString(),
                                Integer.parseInt(editText3.getText().toString()),
                                checkBox.isChecked()
                        );
                        zwierzetaDataBase.getZwierzeDAO().wstawZwierzeDoBazy(zwierze);
                        //nauczycielList = szkolaDataBase.getNauczycielDAO().zwrocWszystkichNauczycieliZBazy();
                        zwierzeList.add(zwierze);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        zwierzetaDataBase.getZwierzeDAO().usunZwierzeZBazy(zwierzeList.get(i));
                        zwierzeList.remove(zwierzeList.get(i));
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        kliknieteZwierze = zwierzeList.get(i);
                        //wypelniony formularz
                        editText.setText(kliknieteZwierze.nazwa);
                        editText2.setText(kliknieteZwierze.gatunek);
                        editText3.setText(kliknieteZwierze.waga+"");
                        checkBox.setChecked(kliknieteZwierze.czyMiesoZerny);

                        buttonAktualizuj.setVisibility(View.VISIBLE);
                    }
                }
        );

        buttonAktualizuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        kliknieteZwierze.nazwa = editText.getText().toString();
                        kliknieteZwierze.gatunek = editText2.getText().toString();
                        kliknieteZwierze.waga = Integer.parseInt(editText3.getText().toString());

                        zwierzetaDataBase.getZwierzeDAO().zaktualizujDaneZwierzecia(kliknieteZwierze);
                        buttonAktualizuj.setVisibility(View.INVISIBLE);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
    }
}