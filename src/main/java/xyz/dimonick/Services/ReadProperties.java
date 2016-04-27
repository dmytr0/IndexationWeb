package xyz.dimonick.Services;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    private static Properties properties;

    static{
        init();
    }

    private static void init() {
        properties = new Properties();
        FileInputStream fis=null;
        String filepath1 = "src/main/resources/app.properties";
        String filepath2 = "C:\\Users\\admin\\Documents\\Java all\\IndexCalculator\\src\\main\\resources\\app.properties";
        String src = "";
        File file = new File(filepath1);
        if (file.exists() && file.isFile()) {
            src = filepath1;
        } else {
            file = new File(filepath2);
            if (file.exists() && file.isFile()) {
                src = filepath2;
            }
        }

        try{
            fis = new FileInputStream(src);
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found!");
            e.printStackTrace();
        }
        if(fis!=null) {
            try {
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }

    public static String getProperty(String property){

        return properties.getProperty(property);
    }

}
