
package generator;

import generator.service.DbService;
import generator.utils.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class CLIReader {
    String path;
    String packageName;
    String lang;
    String table;
    String view;

    //GETTERS AND SETTERS
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }

    //METHODS
    public void getData(String[] str) throws Exception{
        if(!str[0].equals("scaffold"))
            throw new Exception("Wrong syntax");
        for(int i = 0; i < str.length; i++){
            if(str[i].equals("-p")){
                this.setPackageName(str[i+1]);
            }
            if(str[i].equals("-t")){
                this.setTable(str[i+1]);
            }
            if(str[i].equals("-path")){
                this.setPath(str[i+1]);
            }
            if(str[i].equals("-l")){
                this.setLang(str[i+1].toLowerCase());
            }
            if(str[i].equals("-v")){
                this.setView(str[i+1].toLowerCase());
            }
        }
    }

    public static void showAllTables(Connection con) throws Exception{
        List<String> lst = DbService.getAllTables(con);
        for(String item : lst)
            System.out.println(item);
    }

    public String[] getTables(Connection con) throws Exception{
        if(this.getTable().equals("all"))
            return DbService.getAllTablesArrays(con);
        return this.getTable().split(",");
    }
    public String[] getViews(Connection con) throws Exception{
        if(this.getView().equals("all"))
            return DbService.getAllViewsArrays(con);
        return this.getView().split(",");
    }
    public void read(Connection con, String str) throws Exception{
        if(str.equals("list all tables") || str.equals("show all tables")){
            showAllTables(con);
            return;
        }
        List<String> lst = new ArrayList<>();
        String[] array = str.split(" ");
        getData(array);
        String[] list = getTables(con);
        String[] listViews = getViews(con);

        String separator = ExtractSubstring.getSeparatorAfter();

        CodeGenerator.createPackage("Model"+separator+"Entity", getPath());
        CodeGenerator.createPackage("Context", getPath());
        CodeGenerator.createPackage("Model"+separator+"Service", getPath());
        CodeGenerator.createPackage("Controller", getPath());
        CodeGenerator.createPackage("Configuration", getPath());


        if(getLang().equals("java")){
            for(String item : list)
                CodeGenerator.generateSource(con, getPath(),item, getPackageName(), "java");
            for(String item : listViews)
                CodeGenerator.generateSource(con, getPath(),item, getPackageName(), "java");

        }else if(getLang().equals("dotnet")){
            for(String item : list)
                CodeGenerator.generateSource(con, getPath(),item, getPackageName(), "cs");
        }
    }

        //scaffold -p test -t information -path E:\ITU\TEST -l java
}
