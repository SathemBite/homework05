package task2;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * Created by anton on 03.11.17.
 */
public class UniversalProp {
    private HashMap<String, String> data;
    private String[] props;

    public UniversalProp(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader("resources/" + fileName + ".properties"))){
            data = new HashMap<>();
            br.lines().
                    forEach(str ->
                            data.put(
                                    str.substring(0, str.indexOf('=')),
                                    str.substring(str.indexOf('=') + 1, str.length())
                            )
                    );
        } catch (NullPointerException ex){
            System.out.println("Error! Properties file name or locale is null!");
        } catch (MissingResourceException ex){
            System.out.println("Error! The specified properties file not exist!");
        } catch (IOException ex){
            System.out.println("Error! The specified file is opened by another process!");
        }
    }

    public String get(String key){
        try {
            return data.get(key);
        } catch (NullPointerException ex){
            System.out.println("Property not found!");
            return "";
        }

    }
}

