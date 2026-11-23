package com.example.bazadanychroombb3plusy;

import static android.view.View.INVISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    ZooDataBase zooDataBase;
    Button buttonDodaj, buttonAktualizuj;
    EditText editTextGatunek, editTextWaga, editTextIleNog, editTextGatunek2, editTextWaga2, editTextIleNog2;
    ListView listView;
    List<Zwierze> zwierzeList;
    ArrayAdapter<Zwierze> arrayAdapter;
    Zwierze kliknieteZwierze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        zooDataBase = Room.databaseBuilder(
                getApplicationContext(), ZooDataBase.class, "zoo_db"
        ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

//        zooDataBase.getZwierzeDao().wstawZwierzeDoBazy(
//                new Zwierze("Anna", 50, 2)
//        );
        listView = findViewById(R.id.listView1);
        zwierzeList = zooDataBase.getZwierzeDao().zwrocWszystkieZwierzetaBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                zwierzeList);
        listView.setAdapter(arrayAdapter);

        editTextGatunek = findViewById(R.id.editTextGatunek);
        editTextWaga = findViewById(R.id.editTextWaga);
        editTextIleNog = findViewById(R.id.editTextIleNog);

        buttonDodaj = findViewById(R.id.buttonDodaj);
        buttonDodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Zwierze zwierze = new Zwierze(
                                editTextGatunek.getText().toString(),
                                Integer.parseInt(editTextWaga.getText().toString()),
                                Integer.parseInt(editTextIleNog.getText().toString())
                        );
                        zooDataBase.getZwierzeDao().wstawZwierzeDoBazy(zwierze);
                        zwierzeList.add(zwierze);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        zooDataBase.getZwierzeDao().usunZwierzeZBazy(zwierzeList.get(i));
                        zwierzeList.remove(zwierzeList.get(i));
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
        editTextGatunek2 = findViewById(R.id.editTextGatunek2);
        editTextWaga2 = findViewById(R.id.editTextWaga2);
        editTextIleNog2 = findViewById(R.id.editTextIleNog2);
        buttonAktualizuj = findViewById(R.id.buttonAktualizuj);
        buttonAktualizuj.setVisibility(INVISIBLE);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        kliknieteZwierze = zwierzeList.get(i);
                        //wypelniony formularz
                        editTextGatunek2.setText(kliknieteZwierze.gatunek);
                        editTextWaga2.setText(kliknieteZwierze.waga+"");
                        editTextIleNog2.setText(kliknieteZwierze.ileNog+"");

                        buttonAktualizuj.setVisibility(View.VISIBLE);
                    }
                }
        );

        buttonAktualizuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        kliknieteZwierze.gatunek = editTextGatunek2.getText().toString();
                        kliknieteZwierze.waga = Integer.parseInt(editTextWaga2.getText().toString());
                        kliknieteZwierze.ileNog = Integer.parseInt(editTextIleNog2.getText().toString());

                        zooDataBase.getZwierzeDao().zaktualizujDaneZwierzecia(kliknieteZwierze);
                        buttonAktualizuj.setVisibility(View.INVISIBLE);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

}