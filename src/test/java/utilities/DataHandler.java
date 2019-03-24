package utilities;

import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {

    public static Map<String, String> data = new HashMap<String, String>();

    public enum DataType {
        DATA
    }

    public static void dataRead(String path, DataType option) {
        File file = new File(path);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while (true) {
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] parts = line.split("~");
            if (parts.length != 0) {
                if (option == DataType.DATA) {
                    data.put(parts[0], parts[1]);
                }
            }
        }
    }

    public static String[] getData(String id) {
        String[] data = new String[7];
        for (int i = 0; i < 7; i++) {
            data[i] = DataHandler.data.get(id + (i + 1));
        }
        return data;
    }

    public static void putData(List<WebElement> elements, String[] data) {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).sendKeys(data[i]);
        }
    }
}

