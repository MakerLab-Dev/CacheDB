package edai.CacheDB.io;

import edai.CacheDB.utils.MapEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadWrite {
    public static void writeToFile(String fileName, MapEntry data) {
        try {
            // Create necessary directories if they don't exist.
            String[] dirs = fileName.split("/");
            String path = "";
            for (int i = 0; i < dirs.length - 1; i++) {
                path += dirs[i] + "/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
            // Write to file, overwriting if it exists.
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static MapEntry readFromFile(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            MapEntry data = (MapEntry) ois.readObject();
            ois.close();
            fis.close();
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String[] getAllFiles(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            return dir.list();
        }
        return null;
    }

    public static void deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            // Delete file if it exists.
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
