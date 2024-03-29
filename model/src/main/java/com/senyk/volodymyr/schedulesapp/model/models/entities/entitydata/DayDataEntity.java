package com.senyk.volodymyr.schedulesapp.model.models.entities.entitydata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "days",
        foreignKeys = @ForeignKey(
                entity = WeekDataEntity.class,
                parentColumns = "week_id",
                childColumns = "week_id",
                onDelete = CASCADE
        ),
        indices = @Index(value = "week_id")
)
public class DayDataEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "day_id")
    public long id;

    @NonNull
    @ColumnInfo(name = "week_id")
    public long weekId;

    @NonNull
    @ColumnInfo(name = "day_ordinalNumber")
    public int dayOrdinal;
}
