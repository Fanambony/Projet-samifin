package generator.service;

import java.util.HashMap;

import generator.utils.ObjectUtility;
import generator.utils.ExtractSubstring;


public class JavaGenerationServiceForService {

    public static String getPackage(String packageName) {
        packageName = packageName.replace(ExtractSubstring.getSeparatorAfter(), ".");
        return "package " + packageName+".Model.Service;";
    }

    public static String getImports(String packageName, String table) {
        packageName = packageName.replace(ExtractSubstring.getSeparatorAfter(), ".");
        String content = "import "+packageName+".Context."+ObjectUtility.capitalize(DbService.formatString(table))+"Repository;\n";
        content += "import "+packageName+".Model.Entity."+ObjectUtility.capitalize(DbService.formatString(table))+";\n";
        content += "import org.springframework.stereotype.Service;\n";
        content += "import java.util.List;\n";
        content += "import java.util.Optional;\n\n";

        return content;
    }

    public static String getClass(String table) {
        String content = "@Service\n";
        content += "public class "+ObjectUtility.capitalize(DbService.formatString(table))+"Service";
        return content;
    }

    public static String getFields(String table) {
        String content = "\tprivate final "+ObjectUtility.capitalize(DbService.formatString(table))+"Repository "+ DbService.formatString(table)+"Repository;";
        return content;
    }

    public static String getConstructor(String table, HashMap<String, String> classes) {
        String className = ObjectUtility.capitalize(DbService.formatString(table))+"Service";
        String jpaRepository = ObjectUtility.capitalize(DbService.formatString(table))+"Repository";
        String content = "\tpublic "+className+"("+jpaRepository+" "+ DbService.formatString(table)+"Repository) {"+
            "this."+DbService.formatString(table)+"Repository = "+DbService.formatString(table)+"Repository;}";
        return content;
    }

    public static String getReadOne(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        String attrJpa = DbService.formatString(table)+"Repository";
        String content = "\t/* -- READ ONE -- */\n";
        content += "\tpublic Optional<"+className+"> getOne(Object id) { return "+attrJpa+".findById(id); }";
        return content;
    }
    public static String getRead(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        String attrJpa = DbService.formatString(table)+"Repository";
        String content = "\t/* -- READ -- */\n";
        content += "\tpublic List<"+className+"> getAll() { return "+attrJpa+".findAll(); }";
        return content;
    }

    public static String getCreate(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        String classNameAttr = ObjectUtility.capitalize(DbService.formatString(table));

        String attrJpa = DbService.formatString(table)+"Repository";
        String content = "\t/* -- CREATE ET UPDATE -- */\n";
        content += "\tpublic void create("+className+" "+classNameAttr+") {  "+attrJpa+".save("+classNameAttr+"); }";
        return content;
    }
    
    public static String getDelete(String table, HashMap<String, String> classes) {
        String content = "\t/* -- DELETE -- */\n";
        String attrJpa = DbService.formatString(table)+"Repository";
        content += "\tpublic void delete(Object id) {  "+attrJpa+".deleteById(id); }";
        return content;
    }

    public static String login(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        if ("Users".compareTo(className) != 0)
            return "";
        String content = "\tpublic Users getUserByEmailAndPassword(String email, String password) throws Exception{\n";
        content += "\t\tUsers user = usersRepository.getUserByEmailAndPassword(email, password);\n";
        content += "\t\tif (user != null) return user;\n";
        content += "\t\tthrow new Exception(\"Identifiant invalide\");\n";
        content += "\t}";
        return content;
    }

    public static String generateService(String template, String packageName, HashMap<String, String> mapp, String table){
        String temp = template;
        temp = temp.replace("%package%", JavaGenerationServiceForService.getPackage(packageName));
        temp = temp.replace("%imports%", JavaGenerationServiceForService.getImports(packageName, table));
        temp = temp.replace("%class%", JavaGenerationServiceForService.getClass(table));
        temp = temp.replace("%field%", JavaGenerationServiceForService.getFields(table));
        temp = temp.replace("%constructor%", JavaGenerationServiceForService.getConstructor(table, mapp));
        temp = temp.replace("%read_one%", JavaGenerationServiceForService.getReadOne(table));
        temp = temp.replace("%read%", JavaGenerationServiceForService.getRead(table));
        temp = temp.replace("%create%", JavaGenerationServiceForService.getCreate(table));
        temp = temp.replace("%delete%", JavaGenerationServiceForService.getDelete(table, mapp));
        temp = temp.replace("%login%", JavaGenerationServiceForService.login(table));

        return temp;
    }
}
