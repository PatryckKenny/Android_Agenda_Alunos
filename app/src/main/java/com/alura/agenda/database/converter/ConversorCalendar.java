package com.alura.agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {

    @TypeConverter
    public Long paraLong(Calendar valor){
        return valor.getTimeInMillis();

    }

    @TypeConverter
    public Calendar paraCalender (Long valor){
        Calendar momentoAtal = Calendar.getInstance();
        if(valor != null){
            momentoAtal.setTimeInMillis(valor);
        }

        return momentoAtal;
    }
}
