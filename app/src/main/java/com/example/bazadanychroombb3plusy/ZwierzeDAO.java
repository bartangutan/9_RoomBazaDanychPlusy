package com.example.bazadanychroombb3plusy;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ZwierzeDAO {
    @Insert
    void wstawZwierzeDoBazy(Zwierze zwierze);
    @Query("SELECT * FROM zwierzeta")
    List<Zwierze> zwrocWszystkieZwierzetaBazy();
}
