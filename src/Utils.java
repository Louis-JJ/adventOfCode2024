package src;

import java.io.*;

public class Utils {

    public static String readInputFile(String inputPath){
        try(InputStream is = new FileInputStream(inputPath)){
            return new String(is.readAllBytes());
        } catch(IOException e){
            System.out.println("fuck");
            return null;
        }
    }
}
