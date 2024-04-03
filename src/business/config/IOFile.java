package business.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    // đọc dữ liệu trong file
    public static <T> List<T> getFile(File file){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<T> list =new ArrayList<>();
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fis!=null){
                    fis.close();
                }
                if (ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
    //ghi lại dữ liệu mới vào file
    public static <T> void updateFile(File file, List<T> list){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fos!=null){
                    fos.close();
                }
                if (oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
