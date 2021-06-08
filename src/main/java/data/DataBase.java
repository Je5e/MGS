package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataBase {

    // Load file, if does not exist then it will be created.
    public File loadFile(String fileName) {
        File loadedFile = null;
        try {
            File newFile = new File(fileName+".txt");
            if(!newFile.createNewFile()){
                loadedFile=newFile;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedFile;
    }
    public static void  writeToFile(String content,String fileName){
        try {
            FileWriter fileWriter =new FileWriter(fileName);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
