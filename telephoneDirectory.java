package telephoneDirectory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class telephoneDirectory {

    public int n = 0;
    public HashMap<String, String> namesAndPhonesMap = new HashMap<String, String>();

    void getDatasFromFile(){

        try {
            n=0;
            File myObj = new File("src/telephoneDirectory/directory.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String datas = myReader.nextLine();
                String[] data = datas.split(" ");
                namesAndPhonesMap.put(data[0]+" "+data[1], data[2]);
                // System.out.println(namesAndPhonesMap.keySet());
                n++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void deleteDataFromFile(String dataNameAndLastname) throws IOException {

         File inputFile = new File("src/telephoneDirectory/directory.txt");
         File tempFile = new File("src/telephoneDirectory/tempFile.txt");

         BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
         String currentLine;

         while((currentLine = reader.readLine()) != null) {

             String[] data = currentLine.split(" ");
             if(null!=currentLine && !(data[0]+" "+data[1]).equalsIgnoreCase(dataNameAndLastname)){
                 writer.write(currentLine + System.getProperty("line.separator"));
             }
         }
         writer.close();
         reader.close();

         namesAndPhonesMap.remove(dataNameAndLastname);
         //change tempFile.txt name to directory.txt
         File OldFile= new File("src/telephoneDirectory/directory.txt");
         OldFile.delete();
         Path oldFile = Paths.get("src/telephoneDirectory/tempFile.txt");

         try {
             Files.move(oldFile, oldFile.resolveSibling("directory.txt"));
            // System.out.println("File Successfully Rename");
             System.out.println("Operation successed...");
         }
         catch (IOException e) {
             System.out.println("operation failed");
         }
    }

    void addNewData(String newDataName,String newDataLastname, String newDataPhone) throws IOException {

        File file = new File("src/telephoneDirectory/directory.txt");
        FileWriter fr = new FileWriter(file, true);
        namesAndPhonesMap.put(newDataName+" "+newDataLastname,newDataPhone);
        String newData = "\n"+newDataName+" "+newDataLastname+" "+newDataPhone;
        fr.write(newData);
        fr.close();
        System.out.println("Operation successed...");
    }

    void updateData(String oldNameAndLastName, String newName, String newLastName, String newPhone) throws IOException {

        List<String> newLines = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get("src/telephoneDirectory/directory.txt"), StandardCharsets.UTF_8)) {
            if (line.contains(oldNameAndLastName)) {
                newLines.add(line.replace(line, newName+" "+newLastName+" "+newPhone));
            } else {
                newLines.add(line);
            }
        }
        Files.write(Paths.get("src/telephoneDirectory/directory.txt"), newLines, StandardCharsets.UTF_8);

        if(namesAndPhonesMap.containsKey(oldNameAndLastName)){
            namesAndPhonesMap.remove(oldNameAndLastName);
            namesAndPhonesMap.put(newName+" "+newLastName,newPhone);
            System.out.println("Operation successed...");
        }
    }

    void searchData(String nameAndLastName){

        for(String data: namesAndPhonesMap.keySet()){
            if(data.equals(nameAndLastName)){
                System.out.println("Member info:..."+nameAndLastName+" "+ namesAndPhonesMap.get(data));
            }if(!namesAndPhonesMap.containsKey(nameAndLastName)){
                System.out.println("There is no the member.");
            }
        }
    }

    void listAllDatas(){

        for(String data: namesAndPhonesMap.keySet()){
            System.out.println(data + "  " + namesAndPhonesMap.get(data));
        }
    }
}
