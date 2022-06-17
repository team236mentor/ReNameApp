import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class RenameApp {
    

    public void listFiles(String startDir) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();
        // option  "mp4" or "mov"
        String searchString = "mov";
        String prefix,file2Name;
        Boolean wasRenamed;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dtf_year = DateTimeFormatter.ofPattern("yyyy");

        // this does not currently allow setting the date 
        LocalDate localDate = LocalDate.now();
    

        if (files != null && files.length > 0) {
            for (File file : files) {
                // Check if the file is a directory
                if (file.isDirectory()) {
                    // We will not print the directory name, just use it as a new
                     System.out.println("Directory found in " + file.getPath() );
                } else if (file.getName().startsWith(dtf_year.format(localDate)) == false) {
                   // test that file type is .mp4 and does not already start with todays date
                    
                    if (file.getName().endsWith(searchString) == true)  {
                        // define the new file name 
                        // ensure file stays in existing directory 
                        // add todays date as file prefix
                        file2Name= file.getParentFile()+"\\"+ dtf.format(localDate)+"_"+ file.getName();
                        File renamed = new File(file2Name);
                        // use the renamed File to redefine the path and name of the actual file
                        wasRenamed = file.renameTo(renamed);
                        
                        // original debug lines
                        // System.out.println("Parent: " + renamed.getParentFile());
                        // System.out.println("Name: " + renamed.getName()); 
                        // System.out.println ("Absolute: " + renamed.getAbsoluteFile());
                    }  else { 
                        System.out.println("was not " + searchString +" extension " + LocalDate.now()); 
                        // System.out.println (file.getPath());
                        // System.out.println(file.getName()); 
                       // System.out.println( "no match" );
                    }
                

                }else {  
                    // file is already starts with year 
                    System.out.println("file started with year was skipped " + file.getName()) ;
                  }
                }
            }
        } 

    public static void main(String[] args) {
        RenameApp test = new RenameApp();
        // starting directory "D:\\DCIM" OR "F:\\DroneVideos_2020-2021"
        String startDir = ("D:\\DCIM");
        test.listFiles(startDir);
    }
}
        
