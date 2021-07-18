package telephoneDirectory;

import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        telephoneDirectory tD = new telephoneDirectory();

        System.out.println("************* Table *************");
        System.out.println("\n1- Search a member");
        System.out.println("\n2- Delete a member");
        System.out.println("\n3- Update a member");
        System.out.println("\n4- Add a member");
        System.out.println("\n5- List all members");
        System.out.println("\n Exit...");
        System.out.println("\n************* Table *************");

        Scanner scanner = new Scanner(System.in);
        int select=0;
        while(select != 5){

            System.out.println("\nPlease enter your choice: (1,2,3,4,5 or for exiting choose any key):...");
            select = scanner.nextInt();

            if(select == 1){
                System.out.println("---- SEARCHING -----");
                System.out.println("Please enter name and last name:..");
                Scanner scan = new Scanner(System.in);
                String nameAndLastName = scan.nextLine();
                tD.getDatasFromFile();
                tD.searchData(nameAndLastName);
            }else if(select == 2){
                System.out.println("---- DELETING -----");
                System.out.println("Please enter name and last name:..");
                Scanner scan = new Scanner(System.in);
                String nameAndLastName = scan.nextLine();
                tD.getDatasFromFile();
                tD.deleteDataFromFile(nameAndLastName);
            }else if(select == 3){
                System.out.println("---- UPDATING -----");
                System.out.println("Please enter name and last name:..");
                Scanner scan = new Scanner(System.in);
                String oldNameAndLastName = scan.nextLine();
                System.out.println("Please enter new name:..");
                String newName = scan.nextLine();
                System.out.println("Please enter new last name:..");
                String newLastName = scan.nextLine();
                System.out.println("Please enter new phone:..");
                String newPhone = scan.nextLine();
                tD.updateData(oldNameAndLastName, newName,newLastName,newPhone);
            }else if(select == 4){
                System.out.println("---- ADDING -----");
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter name:..");
                String name = scan.nextLine();
                System.out.println("Please enter last name:..");
                String lastName = scan.nextLine();
                System.out.println("Please enter phone:..");
                String phone = scan.nextLine();
                tD.getDatasFromFile();
                tD.addNewData(name, lastName, phone);
            }else if(select == 5){
                System.out.println("---- LIST -----");
                tD.getDatasFromFile();
                tD.listAllDatas();
            }else{
                break;
            }
        }

    }
}
