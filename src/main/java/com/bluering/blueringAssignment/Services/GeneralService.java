package com.bluering.blueringAssignment.Services;

//import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Service
public class GeneralService {
    public void updateEntity(Map<String, Object> entityDTO, Object entityToUpdate, Class entityToUpdateClass) {
        entityDTO.forEach((k, v) -> {
            try {
                Field field = FieldUtils.getField(entityToUpdateClass, k, true);
                if (field != null) {
                    field.setAccessible(true);
                    if (field.getType() == LocalDate.class && v instanceof String) {
                        // Convert String to LocalDate
                        LocalDate localDate = LocalDate.parse((String) v, DateTimeFormatter.ISO_LOCAL_DATE);
                        field.set(entityToUpdate, localDate);
                    } else {
                        field.set(entityToUpdate, v);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Handle the exception as needed
            }
        });
    }
}
