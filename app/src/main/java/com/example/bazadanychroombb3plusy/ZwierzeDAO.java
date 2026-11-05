package com.example.bazadanychroombb3plusy;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ZwierzeDAO {
    @Query("SELECT * FROM zwierzeta")
    List<Zwierze> zwrocWszystkieZwierzetaBazy();
}
