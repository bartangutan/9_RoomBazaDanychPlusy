package com.example.bazadanychroombb3plusy;

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
    Button buttonDodaj;
    EditText editTextGatunek, editTextWaga, editTextIleNog;
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
    }
}