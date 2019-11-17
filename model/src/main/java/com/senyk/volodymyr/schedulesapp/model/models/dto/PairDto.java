package com.senyk.volodymyr.schedulesapp.model.models.dto;

import com.senyk.volodymyr.schedulesapp.model.exceptions.PrivateConstructorEntranceException;
import com.senyk.volodymyr.schedulesapp.model.models.enums.PairType;

import java.util.Date;

public class PairDto {
    private int id;
    private Date time;
    private String name;
    private String teacher;
    private PairType type;
    private String place;
    private String additionalInfo;

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public PairType getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    private PairDto() {
        throw new PrivateConstructorEntranceException();
    }

    public static Builder getBuilder() {
        return new PairDto().new Builder();
    }

    public class Builder {

        private Builder() {
            throw new PrivateConstructorEntranceException();
        }

        public PairDto build() {
            return PairDto.this;
        }

        public Builder setId(int id) {
            PairDto.this.id = id;
            return this;
        }

        public Builder setTime(Date time) {
            PairDto.this.time = time;
            return this;
        }

        public Builder setName(String name) {
            PairDto.this.name = name;
            return this;
        }

        public Builder setTeacher(String teacher) {
            PairDto.this.teacher = teacher;
            return this;
        }

        public Builder setType(PairType type) {
            PairDto.this.type = type;
            return this;
        }

        public Builder setPlace(String place) {
            PairDto.this.place = place;
            return this;
        }

        public Builder setAdditionalInfo(String additionalInfo) {
            PairDto.this.additionalInfo = additionalInfo;
            return this;
        }

    }
}
