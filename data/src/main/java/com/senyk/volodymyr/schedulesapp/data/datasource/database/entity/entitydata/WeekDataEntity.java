package com.senyk.volodymyr.schedulesapp.data.datasource.database.entity.entitydata;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(
        tableName = "weeks",
        foreignKeys = @ForeignKey(
                entity = ScheduleDataEntity.class, parentColumns = "schedule_id", childColumns = "schedule_id", onDelete = CASCADE
        ),
        indices = @Index(value = "schedule_id")
)
public class WeekDataEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "week_id")
    public long id;

    @NonNull
    @ColumnInfo(name = "schedule_id")
    public long scheduleId;

    @NonNull
    @ColumnInfo(name = "week_ordinalNumber")
    public int weekOrdinal;
}
