import annotations.CustomDateFormat;
import annotations.JsonValue;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Processor {

    String result = "";

    public Processor() {
    }

    public String toJson(Object o) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        result = result + "{\n";
        for(Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(o);
            result = result + "\"" + inspectJsonValue(field) + "\"" + " : " + "\"" + value + "\"" + "\n";
        }
        result = result + "}";
        return result;
    }

    private String inspectJsonValue(Field field) {
        if(field.isAnnotationPresent(JsonValue.class)) {
            return field.getAnnotation(JsonValue.class).name();
        } else {
            return field.getName();
        }
    }

    private String inspectCustomDateFormat(Field field) throws ParseException {
        if(field.isAnnotationPresent(CustomDateFormat.class)) {
            return new SimpleDateFormat(field.getAnnotation(CustomDateFormat.class).format())
                    .parse(field.toString())
                    .toString();
        } else {
            return field.toString();
        }
    }
}
