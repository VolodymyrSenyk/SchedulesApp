package com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "pairs",
        foreignKeys = @ForeignKey(
                entity = DayDataEntity.class, parentColumns = "day_id", childColumns = "day_id", onDelete = CASCADE
        ),
        indices = @Index(value = "day_id")
)
public class PairDataEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "pair_id")
    public long id;

    @NonNull
    @ColumnInfo(name = "day_id")
    public long dayId;

    @ColumnInfo(name = "pair_time")
    public long time;

    @ColumnInfo(name = "pair_name")
    public String name;

    @ColumnInfo(name = "pair_teacher")
    public String teacher;

    @ColumnInfo(name = "pair_type")
    public int type;

    @ColumnInfo(name = "pair_place")
    public String place;

    @ColumnInfo(name = "pair_info")
    public String additionalInfo;
}
