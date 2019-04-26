package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Tree t = new Tree();

        Scanner input = new Scanner(System.in);
        int x;
        File file = new File("English_Dictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {

            t.insert(new Node(st));

        }

        br.close();

        System.out.println("Choose an option:" + "\n" + "1) Print dictionary size." + "\n" + "2) Insert a word." + "\n" + "3) Look-up for a word." + "\n" + "4) Delete a word." + "\n" + "5) Exit");
        x = input.nextInt();
        while(true){

            switch(x){
                case 1:
                System.out.println("The dictionary contains " + t.getNodeCount() + " words" + "\n" + "Tree height = " + " " + t.getTreeHeight(t.getRoot()) + "\n" + "The root of the dictionary is " + t.getRoot().getKey());
                break;
                case 2:
                System.out.println("Enter the word to be inserted.");
                st = input.next();
                t.insert(new Node(st));
                break;
                case 3:
                System.out.println("Enter the word to be looked-up for.");
                st = input.next();
                t.search(st);
                break;
                case 4:
                System.out.println("Enter the word to be deleted.");
                st = input.next();
                t.rbDelete(t.search(st));
                break;
                case 5:
                input.close();
                return;
                default:
                System.out.println("Error");
                break;
            }

            System.out.println("Choose an option:");
            x = input.nextInt();

        }

    }
}