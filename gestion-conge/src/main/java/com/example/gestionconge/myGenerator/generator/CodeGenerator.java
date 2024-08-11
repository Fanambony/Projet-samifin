
package generator;

import generator.dao.DbConnection;
import java.sql.Connection;
import generator.service.DbService;
//import generator.service.DotnetGenerationService;
import generator.service.JavaGenerationService;
import generator.service.JavaGenerationServiceForController;
import generator.service.JavaGenerationServiceForService;
import generator.utils.ObjectUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class CodeGenerator {

    public static void createPackage(String packageName, String path) throws Exception{
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        Path directoryPath = Paths.get(path + separator + packageName);
        Files.createDirectories(directoryPath);
        System.out.println(directoryPath.toString() + " created");
    }

    public static String getTemplate(InputStream path) throws Exception{
        StringBuilder builder = new StringBuilder();
        InputStreamReader fis = new InputStreamReader(path);
        BufferedReader reader = new BufferedReader(fis);
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line).append("\n");
        }
        return builder.toString();
    }

    public static String getTemplate(String path) throws Exception{
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line).append("\n");
        }
        return builder.toString();
    }

    public static void createFile(String packageName, String path, String fileName, String extension) throws Exception{
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator +"Model" + separator + "Entity"+ separator  + separator + fileName + "." + extension;
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }

    /*POUR JpaRepository */
    public static void createFileJpaRepository(String packageName, String path, String fileName, String extension) throws Exception{
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator + packageName + separator + fileName+ "." + extension;
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }

    public static void createFileController(String packageName, String path, String fileName, String extension) throws Exception{

        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator + "Controller"+ separator  + fileName + "." + extension;
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }

    public static void createFileService(String packageName, String path, String fileName, String extension) throws Exception{
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator +"Model" + separator + "Service" + separator + fileName+ "." + extension;
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }

    public static void createFileWebConfig(String packageName, String path, String fileName, String extension) throws Exception{
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator +"Configuration" +  separator + fileName+ "." + extension;
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }


    public static void writeFile(Connection con, String table, String path, String packageName, String fileName, String extension) throws Exception{
        String separator = File.separator;
        path = path + separator + "Model"+ separator +"Entity" + separator + fileName + "." + extension;
        FileWriter writer = new FileWriter(path);
        HashMap<String, String> mapp = DbService.getColumnNameAndTypeWithForeignKeyObject(con, table);

        String template = getTemplate(CodeGenerator.class.getResourceAsStream("/Template.code"));
//        String template = getTemplate("Template.code");
        if(extension.equals("java")){
            template = JavaGenerationService.generate(template, packageName, mapp, table);
        }else if(extension.equals("cs")){
            //template = DotnetGenerationService.generate(template, packageName, mapp, table);
        }

        writer.write(template);
        writer.close();
    }

    public static void writeFileServices(Connection con, String table, String path, String packageName, String fileName, String extension) throws Exception{
        String separator = File.separator;
        path = path + separator + "Model"+ separator +"Service" + separator + fileName + "." + extension;
        FileWriter writer = new FileWriter(path);
        HashMap<String, String> mapp = DbService.getColumnNameAndTypeWithForeignKeyObject(con, table);

        String template = getTemplate(CodeGenerator.class.getResourceAsStream("/Service.code"));
//        String template = getTemplate("Template.code");
        if(extension.equals("java")){
            template = JavaGenerationServiceForService.generateService(template, packageName, mapp, table);
        }else if(extension.equals("cs")){
            //template = DotnetGenerationService.generate(template, packageName, mapp, table);
        }

        writer.write(template);
        writer.close();
    }

    public static void writeFileControllers(Connection con, String table, String path, String packageName, String fileName, String extension) throws Exception{
        String separator = File.separator;
        path = path + separator +"Controller" + separator + fileName + "." + extension;
        FileWriter writer = new FileWriter(path);
        HashMap<String, String> mapp = DbService.getColumnNameAndTypeWithForeignKeyObject(con, table);

        String template = getTemplate(CodeGenerator.class.getResourceAsStream("/Controller.code"));
//        String template = getTemplate("Template.code");
        if(extension.equals("java")){
            template = JavaGenerationServiceForController.generateController(template, packageName, mapp, table);
        }else if(extension.equals("cs")){
           // template = DotnetGenerationService.generate(template, packageName, mapp, table);
        }

        writer.write(template);
        writer.close();
    }


    public static void writeFileJpaRepository(Connection con, String table, String path, String packageName, String fileName, String extension) throws Exception{
        String separator = File.separator;
        path = path + separator + "Context" + separator + fileName + "." + extension;
        FileWriter writer = new FileWriter(path);
        String className = ObjectUtility.capitalize(DbService.formatString(table));

        packageName = packageName.replace(generator.utils.ExtractSubstring.getSeparatorAfter(), ".");

        String content = "package "+packageName+".Context;\n\n";
        content += "import org.springframework.data.jpa.repository.JpaRepository;\n";
        content += "import "+packageName+".Model.Entity."+className+";\n\n";
        content += "public interface " +fileName+" extends JpaRepository<"+className+", Object> {\n\n";
        if (className.equals("Users"))
            content += "\tUsers getUserByEmailAndPassword(String email, String password);\n";
        content += "}";

        writer.write(content);
        writer.close();
    }

    public static void writeFileForApplicationProprieties(String path) throws Exception{
        String separator = File.separator;
        String pathMain = path.split("java")[0];
        String pathAp = pathMain + "resources\\application.properties";

        String content = generator.service.JavaGenerationConnection.write();
        FileWriter writer = new FileWriter(pathAp);
        writer.write(content);
        writer.close();
    }

    public static void writeFileForWebConfig(Connection con, String table, String path, String packageName, String fileName, String extension) throws Exception{
        String separator = File.separator;
        path = path + separator + "Configuration" + separator + fileName + "." + extension;
        FileWriter writer = new FileWriter(path);

        String template = getTemplate(CodeGenerator.class.getResourceAsStream("/Cors.code"));

        System.out.println(template);
        String content = template;

        writer.write(content);
        writer.close();
    }

    public static void generateSource(Connection con, String path, String table, String packageName, String extension) throws Exception{
        boolean state = false;
        if(con == null){
            con = new DbConnection().connect();
            state = true;
        }
        String fileName = JavaGenerationService.getClassName(table);
        createFile(packageName, path, fileName, extension);
        writeFile(con, table, path, packageName, fileName, extension);

        createFileJpaRepository(packageName, path, fileName+"Repository", extension);
        writeFileJpaRepository(con, table, path, packageName, fileName+"Repository", extension);

        createFileController(packageName, path, fileName+"Controller", extension);
        writeFileControllers(con, table, path, packageName, fileName+"Controller", extension);

        createFileService(packageName, path, fileName+"Service", extension);
        writeFileServices(con, table, path, packageName, fileName+"Service", extension);

        writeFileForApplicationProprieties(path);

        createFileWebConfig(packageName, path, "WebConfig", extension);
        writeFileForWebConfig(con, table, path, packageName, "WebConfig", extension);

        if( state == true) con.close();
    }
}
