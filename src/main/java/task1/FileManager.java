package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;

import static java.util.Arrays.stream;

/**
 * Created by anton on 26.10.17.
 */
public class FileManager {


    public static void main(String[] args) throws IOException{
        Arrays.stream(getDirectoryContent(new File(System.getenv("HOME")))).forEach(System.out::println);
        File currPath = new File(System.getenv("HOME"));
        System.out.println(currPath.getParent());
        System.out.println(instructions());
        int a = 4;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String token = "";
        int command;
        while (!token.equals("0")){
            Arrays.stream(getDirectoryContent(currPath)).forEach(System.out::println);
            System.out.println(instructions());
            System.out.println("Enter command: ");
            token = br.readLine();
            try{
                command = Integer.parseInt(token);
            } catch(NumberFormatException e){
                System.out.println("Command not exist!!! Try again!!!");
                token = br.readLine();
                continue;
            }
            if ((command > 0) && (command < 6)){
                System.out.print("Enter file(directory) name: ");
                String fileName ="/" + br.readLine();
                boolean res = false;
                switch (command){

                    case 1: res = createFile(new File(currPath.getPath() + fileName)); break;
                    case 2: res = createDirectory(new File(currPath.getPath() + fileName)); break;
                    case 3: res = deleteFile(new File(currPath.getPath() + fileName)); break;
                    case 4:
                        System.out.print("Enter text: ");
                        res = writeToFile(new File(currPath.getPath() + fileName), br.readLine());
                        break;
                    case 5:
                        System.out.print("Enter text: ");
                        res = appendToFile(new File(currPath.getPath() + fileName), br.readLine());
                        break;
                }
            }
            if (command == 6){
                System.out.print("Enter directory name: ");
                currPath = new File(currPath.getPath() + "/" +  br.readLine());

            }
            if (command == 7){
                currPath = currPath.getParentFile();
            }

        }
    }

    public static String[] getDirectoryContent(File path){
        return path.list();
    }



    public static boolean createFile(File path){
        try{
            Files.createFile(path.toPath());
        }catch(IOException e){
            System.out.println("The specified file already exists!");
        }
        return false;
    }

    public static boolean createDirectory(File path){
        try{
            path.mkdir();
            return true;

        } catch(Exception e){
            System.out.println("The specified directory already exists");
            return false;
        }
    }


    public static boolean writeToFile(File path, String text){
        try{
            Files.write(path.toPath(), text.getBytes(), StandardOpenOption.WRITE);
        }catch (Exception e){
            System.out.println("Error!!! Try again!");
            return false;
        }
        return true;
    }

    public static boolean appendToFile(File path, String text){
        try{
            Files.write(path.toPath(), text.getBytes(), StandardOpenOption.APPEND);
        }catch (Exception e){
            System.out.println("The specified file does not exist!");
            return false;
        }
        return true;
    }

    public static boolean deleteFile(File path){
        try{
            return Files.deleteIfExists(path.toPath());
        } catch (IOException e){
            System.out.println("The specified file does not exist!");
            return false;
        }
    }

    public static String instructions(){
        StringBuilder sb = new StringBuilder();

        sb.append("1 - create file\n");
        sb.append("2 - create directory\n");
        sb.append("3 - delete file or direcoty\n");
        sb.append("4 - write in file\n");
        sb.append("5 - append in file\n");
        sb.append("6 - open directory\n");
        sb.append("7 - move to parent directory\n\n");
        sb.append("0 - exit\n");

        return sb.toString();
    }


}
