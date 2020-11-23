package com.example.tugas2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_ukm")
public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "name")
    private String name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
