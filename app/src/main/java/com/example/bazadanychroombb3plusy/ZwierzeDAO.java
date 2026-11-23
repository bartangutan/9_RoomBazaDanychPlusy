package com.example.bazadanychroombb3plusy;

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
    @Delete
    void usunZwierzeZBazy(Zwierze zwierze);
    @Update
    void zaktualizujDaneZwierzecia(Zwierze zwierze);
    @Query("SELECT * FROM zwierzeta")
    List<Zwierze> zwrocWszystkieZwierzetaBazy();
}
