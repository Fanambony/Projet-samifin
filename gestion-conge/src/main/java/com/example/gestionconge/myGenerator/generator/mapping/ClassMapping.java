
package generator.mapping;

import java.util.HashMap;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalTime;

public class ClassMapping {

    public static HashMap<String, Class> getClassMap(){
        HashMap<String, Class> mapping = new HashMap<>();

        mapping.put("int", Integer.class);
        mapping.put("Integer", Integer.class);
        mapping.put("double", Double.class);
        mapping.put("Double", Double.class);
        mapping.put("Float", Float.class);
        mapping.put("Float", Float.class);
        mapping.put("boolean", Boolean.class);
        mapping.put("Boolean", Boolean.class);
        mapping.put("byte", Byte.class);
        mapping.put("Byte", Byte.class);

        return mapping;
    }

    public static HashMap<String, String> getClassMapDotnet(){
        HashMap<String, String> mapping = new HashMap<>();

        mapping.put("java.lang.Integer", "int");
        mapping.put("java.lang.Double", "double");
        mapping.put("java.lang.Float", "Float");
        mapping.put("java.lang.String", "string");
        mapping.put("java.sql.Timestamp", "DateTime");
        mapping.put("java.lang.Boolean", "bool");
        mapping.put("java.sql.Date", "DateOnly");
        mapping.put("java.lang.Char", "Char");
        mapping.put("java.sql.Time", "TimeOnly");
        mapping.put(" java.math.BigDecimal", "BigDecimal");
        mapping.put("[B", "Byte[]");
        mapping.put("org.postgresql.geometric.PGpoint", "string");
        mapping.put("java.util.List", "List");



        return mapping;
    }

}

