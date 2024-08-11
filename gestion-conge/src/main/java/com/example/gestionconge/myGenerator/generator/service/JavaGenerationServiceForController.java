package generator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import generator.utils.ObjectUtility;
import generator.CodeGenerator;
import generator.utils.ExtractSubstring;


public class JavaGenerationServiceForController {

    public static String getPackage(String packageName) {
        packageName = packageName.replace(ExtractSubstring.getSeparatorAfter(), ".");
        return "package " + packageName+".Controller;";
    }

    public static String getImports(String packageName, String table, HashMap<String, String> columns) {
        packageName = packageName.replace(ExtractSubstring.getSeparatorAfter(), ".");
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        String content = "import "+packageName+".Model.Entity."+className+";\n";
        content += "import "+packageName+".Model.Service."+className+"Service;\n";
        content += "import org.springframework.stereotype.Controller;\n";
        content += "import org.springframework.ui.Model;\n";
        content += "import org.springframework.web.bind.annotation.*;\n";
        content += "import java.util.List;\n";
        content += "import java.util.Optional;\n";
        content += "import jakarta.servlet.http.HttpSession;\n";
        return content + JavaGenerationService.getImports(columns);
    }

    public static String getClass(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        String content = "@Controller\n";
        content += "@RequestMapping(\""+table+"\")\n";
        content += "public class "+className+"Controller";
        return content;
    }

    public static String getFields(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table))+"Service";
        String classNameAttr = DbService.formatString(table)+"Service";
        String content = "\tprivate final "+className+" " +classNameAttr+";";
        return content;
    }

    public static String getConstructor(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table))+"Controller";
        String classNameService = ObjectUtility.capitalize(DbService.formatString(table))+"Service";
        String classNameServiceAttr = DbService.formatString(table)+"Service";

        String classNameAttr = DbService.formatString(table);
        String content = "\tpublic " + className + "(" + classNameService + " "+classNameServiceAttr+")"+
        "{this."+classNameServiceAttr+" = " + classNameServiceAttr+";}";
        return content;
    }
    
     public static List<String> getAllParameters(String table, HashMap<String, String> columns, String fonctionName){
        List<String> lst = new ArrayList<>();

        String className = JavaGenerationService.getClassName(table);
        String attrName = DbService.formatString(table);

        String temp = "";
        String  attrs = "\t\t"+className+" "+ attrName +" = new " +className +"();\n";
        
        temp = "\tpublic void "+ fonctionName +"(@RequestBody "+ className +" formData) {\n";

        attrs += "\t\t" + attrName + "Service.create(formData);\n";//appelation de methode de service

        temp += attrs + "\t}\n";
        lst.add(temp);

        return lst;
    }
    public static String getParametters(String table, HashMap<String, String> columns, String fonctionName){
        List<String> lst = getAllParameters(table, columns, fonctionName);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
    public static String getReadOne(String table, HashMap<String, String> columns) {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        String classNameServiceAttr = DbService.formatString(table)+"Service";

        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = set.getKey().split("_pk")[0];
            field = DbService.formatString(field);
            String type = JavaGenerationService.splitByPoint(set.getValue());
            if (set.getKey().contains("_pk")) {

                String content = "\t/* -- READ ONE -- */\n";
                content += "\t@GetMapping(\"/{"+field+"}\")\n";
                content += "\tpublic Optional<"+className+"> getOne(@PathVariable "+type+" "+field+") { \n";
                content += "\t\t Optional<"+className+"> "+DbService.formatString(table)+" = this."+classNameServiceAttr+".getOne("+field+");\n";
                content += "\t\treturn "+DbService.formatString(table)+";\n"  ;
                content += "\t}";

                return content;
            }

        }
        return "";
    }
    public static String getRead(String table) {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        String classNameServiceAttr = DbService.formatString(table)+"Service";

        String content = "\t/* -- READ -- */\n";
        content += "\t@GetMapping(\"lists\")\n";
        content += "\tpublic List<"+className+"> getAll() { \n";
        content += "\t\tList<"+className+"> list"+className+" = this."+classNameServiceAttr+".getAll();\n";
        content += "\t\treturn list"+className+";\n"  ;
        content += "\t}";
        return content;
    }

    public static String getCreate(String table, HashMap<String, String> columns) {
        String content = "\t/* -- CREATE -- */\n";
        content += "\t@PostMapping(\"create\")\n";
        return content + getParametters(table, columns, "create");
    }
    public static String getUpdate(String table, HashMap<String, String> columns) {
        String content = "\t/* -- UPDATE -- */\n";
        content += "\t@PutMapping(\"update\")\n";
        return content + getParametters(table, columns, "update");
    }
    public static String getDelete(String table, HashMap<String, String> columns) {
    
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = set.getKey().split("_pk")[0];
            field = DbService.formatString(field);
            String type = JavaGenerationService.splitByPoint(set.getValue());
            if (set.getKey().contains("_pk")) {
                String content = "\t/* -- DELETE -- */\n";
                content += "\t@DeleteMapping(\"delete/{"+field+"}\")\n";
                content += "\tpublic void delete(@PathVariable ";
                content += type +" "+field+") {\n";
                content += "\t\t"+ DbService.formatString(table)+"Service.delete("+field+");\n";
                content += "\t}";
                return content;
            }

        }
        return "";
    }

    public static String getLogin(String table) throws Exception {
        String className = ObjectUtility.capitalize(DbService.formatString(table));
        if ("Users".compareTo(className) != 0)
            return "";
        String template = CodeGenerator.getTemplate(CodeGenerator.class.getResourceAsStream("/Login.code"));
        return template;
        
    }

    public static String generateController(String template, String packageName, HashMap<String, String> mapp, String table) throws Exception{
        String temp = template;
        temp = temp.replace("%package%", JavaGenerationServiceForController.getPackage(packageName));
        temp = temp.replace("%imports%", JavaGenerationServiceForController.getImports(packageName, table, mapp));
        temp = temp.replace("%class%", JavaGenerationServiceForController.getClass(table));
        temp = temp.replace("%field%", JavaGenerationServiceForController.getFields(table));
        temp = temp.replace("%constructor%", JavaGenerationServiceForController.getConstructor(table));
        temp = temp.replace("%read_one%", JavaGenerationServiceForController.getReadOne(table, mapp));
        temp = temp.replace("%read%", JavaGenerationServiceForController.getRead(table));
        temp = temp.replace("%create%", JavaGenerationServiceForController.getCreate(table, mapp));
        temp = temp.replace("%update%", JavaGenerationServiceForController.getUpdate(table, mapp));
        temp = temp.replace("%delete%", JavaGenerationServiceForController.getDelete(table, mapp));
        temp = temp.replace("%login%", JavaGenerationServiceForController.getLogin(table));


        return temp;
    }

}
