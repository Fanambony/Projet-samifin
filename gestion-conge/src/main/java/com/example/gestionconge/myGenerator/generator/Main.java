
package generator;

import generator.dao.DbConnection;
import generator.service.DbService;
import generator.utils.ExtractSubstring;

import java.io.File;
import java.sql.Connection;
import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Connection con = null;
        try{
            String pathProject = new File(".").getAbsolutePath().split(ExtractSubstring.getSeparatorBefore()+"myGenerator")[0];
            System.out.println(pathProject);
            String package_name = " -p "+ ExtractSubstring.getBetweenString(pathProject, "java"+ExtractSubstring.getSeparatorAfter());
            pathProject = " -path "+pathProject;
            System.out.println(pathProject);

            con = new DbConnection().connect();
           while(1 != 2){
               Scanner scan = new Scanner(System.in);
               System.out.print("Scaffold >> ");
               String cli = scan.nextLine();
               cli += pathProject;
               cli += package_name;
               new CLIReader().read(con, cli);
           }
            // DbService.getTableConstraints(con, "photo");
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            con.close();
        }

    }

}
