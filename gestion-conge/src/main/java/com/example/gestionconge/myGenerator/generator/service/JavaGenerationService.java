
package generator.service;

import generator.utils.ObjectUtility;
import generator.utils.ExtractSubstring;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JavaGenerationService {

    public static String getPackage(String packageName){
        packageName = packageName.replace(ExtractSubstring.getSeparatorAfter(), ".");
        return "package "+packageName+".Model.Entity;";
    }

    public static List<String> getAllImports(HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();

        lst.add("import jakarta.persistence.*;\n");

        for (Map.Entry<String, String> set : columns.entrySet()) {
            if(set.getValue().equals("java.sql.Date"))
                lst.add("import java.sql.Date;\n");
            else if(set.getValue().equals("java.sql.Timestamp"))
                lst.add("import java.sql.Timestamp;\n");
            else if(set.getValue().equals("java.sql.Time"))
                lst.add("import java.sql.Time;\n");
            else if(set.getValue().equals("java.math.BigDecimal"))
                lst.add("import java.math.BigDecimal;\n");
            else if(set.getValue().contains("java.util.List"))
                lst.add("import java.util.List;\n");
//            else if(set.getValue().equals("org.postgresql.geometric.PGpoint"))
//                lst.add("import org.postgresql.geometric.PGpoint;\n");
        }
        return lst;
    }

    public static String getImports(HashMap<String, String> columns){
        List<String> lst = getAllImports(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        res += "import com.fasterxml.jackson.annotation.JsonIgnore;\n";
        return res;
    }

    public static String splitByPoint(String str){
        if(str.equals("[B"))
            return "Byte[]";
        else if(str.equals("org.postgresql.geometric.PGpoint"))
            return "String";
        return  str.split("\\.")[str.split("\\.").length - 1];
    }

    public static String getClassName(String table){
        return ObjectUtility.capitalize(DbService.formatString(table));
    }

    public static String getClass(String table){
        String res = "@Entity\n";
        res += "@Table(name = " + "\"" + table + "\")\n";
        res += "public class " + getClassName(table) + "";
        return res;
    }

    public static List<String> getAllGettersAndSetters(HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();

        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = set.getKey().split("_pk")[0];//signe de primary key le pk
            field = DbService.formatString(field);
            String type = splitByPoint(set.getValue());
            type = JavaGenerationService.doThisIfClass(type);//POUR LES TYPES CLASS
            type = updateTypeForList(type, field);
            field = updateVariableForList(type, field);
            String classNameRef = set.getValue().split("_CLASS")[0];//Pour les classes foreign key
            String temp = "";
            if (set.getValue().contains("_CLASS")) {
                temp = "\tpublic " + type + " get" + ObjectUtility.capitalize(classNameRef) + "(){\n";
                temp += "\t\treturn this." + classNameRef + ";\n";
                temp += "\t}\n";
                temp += "\tpublic void set" + ObjectUtility.capitalize(classNameRef) + "(" + type + " " + classNameRef+"){\n";
                temp += "\t\tthis." + classNameRef+" = " + classNameRef + ";\n";
            }
            else {
                temp = "\tpublic " + type + " get" + ObjectUtility.capitalize(field) + "(){\n";
                temp += "\t\treturn this." + field + ";\n";
                temp += "\t}\n";
                temp += "\tpublic void set" + ObjectUtility.capitalize(field) + "(" + type + " " + field+"){\n";
                temp += "\t\tthis." + field+" = " + field + ";\n";
            }

            temp += "\t}\n";
            lst.add(temp);
        }

        return lst;
    }

    public static String updateTypeForList(String type, String field) {
        if (type != null && type.contains("List")) {
            type = type+"<"+ObjectUtility.capitalize(field)+">";
        }
        return type;
    }

    public static String updateVariableForList(String type, String field) {
        if (type != null && type.contains("List")) {
            field = field+"s";
        }
        return field;
    }

    static String  doThisIfClass(String type) {
        String tString =type;
        if(type != null && type.endsWith("_CLASS")) {
            String[] typeStrings = type.split("_");
             tString = ObjectUtility.capitalize(typeStrings[0]);
        }
        return tString;
    }

    public static String getGettersAndSetters(HashMap<String, String> columns){
        List<String> lst = getAllGettersAndSetters(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }

    public static String doPrimaryKeyAnnotationForJpa() {
        String s = "\t@Id\n";
        s += "\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n";
        return s;
    }

    public static String manyToOne(String columnName) {
        String s = "\t@ManyToOne\n";
        s += "\t@JoinColumn(name = \""+columnName.split("_CLASS")[0]+"\", insertable = false, updatable = false)\n";
        return s;
    }
    public static String oneToMany(String columnName) {
        String s = "\t@JsonIgnore\n\t@OneToMany(mappedBy = \""+columnName.split("_CLASS")[0]+"\", cascade = CascadeType.ALL)\n";
        return s;
    }

    public static String getAnnotationsPrimaryKey(String columnName) {
        if (columnName.contains("_pk")) {
            return doPrimaryKeyAnnotationForJpa();
        }
        return "";
    }

    public static String getAnnotationsManyOrOneTo(String columnName, String table) {
        if (table.contains("_CLASS")) {
            return manyToOne(columnName);
        }
        if (table.contains("List")) {
            String tableReference = table.split(":")[0];
            return oneToMany(tableReference);
        }
        return "";
    }

    public static List<String> getAllFields(HashMap<String, String> columns){

        List<String> lst = new ArrayList<>();

        for (Map.Entry<String, String> set : columns.entrySet()) {
            String key = set.getKey().split("_pk")[0];
            String field = DbService.formatString(key);
            String type = splitByPoint(set.getValue());
            type = updateTypeForList(type, field);
            field = updateVariableForList(type, field);
            type = JavaGenerationService.doThisIfClass(type); //POUR LES TYPES CLASS
            String temp = getAnnotationsPrimaryKey(set.getKey());//POUR LES PRIMARY KEYS
            temp += "\t@Column(name = \""+key+"\")\n";
            if (set.getValue().contains("_CLASS")) {
                temp = getAnnotationsManyOrOneTo(set.getKey(), set.getValue()); //ON MET PAS L'ANNOTATION SI C'EST UN CLASS Et ON MET JPA
                temp += "\t" + type + " " + set.getValue().split("_CLASS")[0] + ";\n";
            }
            else if (set.getValue().contains("List")) {
                temp = getAnnotationsManyOrOneTo(set.getKey(), set.getValue()); //ON MET PAS L'ANNOTATION SI C'EST UN CLASS Et ON MET JPA
                temp += "\t" + type + " " + field + ";\n";
            }
            else temp += "\t" + type + " " + field + ";\n";
            lst.add(temp);
        }

        return lst;
    }
    public static String getFields(HashMap<String, String> columns){
        List<String> lst = getAllFields(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }

    public static List<String> getAllConstructors(String table, HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();

        lst.add("\tpublic " + getClassName(table) + "(){}\n");
        String temp = "\tpublic " + getClassName(table) + "(";
        String args = "";
        String  setters = "";
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = set.getKey().split("_pk")[0];
            field = DbService.formatString(field);
            String type = splitByPoint(set.getValue());
            type = updateTypeForList(type, field);
            field = updateVariableForList(type, field);
            type = JavaGenerationService.doThisIfClass(type);//POUR LES TYPES CLASS
            if (set.getValue().contains("_CLASS")) {
                args += type + " "+ set.getValue().split("_CLASS")[0] +", ";
                setters += "\t\tset" + ObjectUtility.capitalize(set.getValue().split("_CLASS")[0]) +"("+set.getValue().split("_CLASS")[0]+");\n";
            }
            else {
                args += type + " " + field + ", ";
                setters += "\t\tset" + ObjectUtility.capitalize(field) +"("+field+");\n";
            }
        }
        args = args.substring(0, args.length() - 2);
        temp += args + "){\n";
        temp += setters + "\t}\n";
        lst.add(temp);

        return lst;
    }

    public static String getConstructors(String table, HashMap<String, String> columns){
        List<String> lst = getAllConstructors(table, columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }

    public static String generate(String template, String packageName, HashMap<String, String> mapp, String table){
        String temp = template;
        temp = temp.replace("%package%", JavaGenerationService.getPackage(packageName));
        temp = temp.replace("%imports%", JavaGenerationService.getImports(mapp));
        temp = temp.replace("%class%", JavaGenerationService.getClass(table));
        temp = temp.replace("%fields%", JavaGenerationService.getFields(mapp));
        temp = temp.replace("%encapsulation%", JavaGenerationService.getGettersAndSetters(mapp));
        temp = temp.replace("%constructors%", JavaGenerationService.getConstructors(table, mapp));
        return temp;
    }


}

