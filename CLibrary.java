package cis156;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
public class CLibrary 
{
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        HashMap books = new HashMap();
        CBook currentBook=null;
        String askUser="", bookId="";
        byte menuChoice=0;
        boolean goodInput1=false, goodInput2=false, 
                goodInput3=false, goodInput4=false;
        
        System.out.println("-Book Collection-");
        
        do {
            do {
                goodInput1=false;
                try {
                    System.out.println("\nSelect one of the following options:" +
                            "\n1. List all books in your collection." + 
                            "\n2. Add a book or edit a book in your collection." +
                            "\n3. Remove a book from your collection." +
                            "\n4. Quit the application.");
                    System.out.print("\nYour selection: ");
                    menuChoice = userInput.nextByte();
                    goodInput1=true;
                }
                catch (Exception e) {
                    System.out.println("\nPlease input 1, 2, 3, or 4.");
                    userInput.nextLine();
                }
                if ((goodInput1==true) && (menuChoice<1 || menuChoice>=5)) {
                    System.out.println("\nPlease input 1, 2, 3, or 4.");
                    userInput.nextLine();
                    goodInput1=false;
                }
            }
            while ((goodInput1==false)&&(menuChoice!=1)&&(menuChoice!=2)&&(menuChoice!=3)&&(menuChoice!=4));
            
            if ((goodInput1==true)&&(menuChoice==1)||(menuChoice==2)||(menuChoice==3)||(menuChoice==4)) {
                switch (menuChoice) {
                    case 1:
                        if (books.isEmpty()) {
                            System.out.println("\nNo data exists.");
                        }
                        else {
                            Set bookIds = books.keySet();
                            Iterator it = bookIds.iterator();
                            String id;
                            while (it.hasNext()) {
                                id=(String)it.next();
                                System.out.println("\nBook ID: " + id);
                                currentBook=(CBook)books.get(id);
                                System.out.println("Book Author: " + currentBook.bookAuthor +
                                        "\nBook Title: " + currentBook.bookName);
                            }
                        }
                        break;
                    case 2:
                        userInput.nextLine();
                        System.out.print("Enter a new or existing book ID: ");
                        bookId=userInput.nextLine();
                        if (books.containsKey(bookId)) {
                            System.out.println("\nThis book already exists.");
                            currentBook=(CBook)books.get(bookId);
                            System.out.println("\nCurrent Author: " + currentBook.bookAuthor +
                                    "\nCurrent Title: " + currentBook.bookName + "\n");
                            do {
                                try {
                                    System.out.print("Would you like to edit this book? y/n ");
                                    askUser=userInput.nextLine();
                                    goodInput3=true;
                                }
                                catch (Exception e) {
                                    System.out.println("\nPlease enter y for yes or n for no.");
                                    goodInput3=false;
                                }
                                if (askUser.equalsIgnoreCase("y")) {
                                    currentBook=(CBook)books.get(bookId);
                                    System.out.print("Input the new book author: ");
                                    currentBook.bookAuthor=userInput.nextLine();
                                    System.out.print("Input the new book title: ");
                                    currentBook.bookName=userInput.nextLine();
                                    books.replace(bookId, currentBook);
                                }
                                else if (askUser.equalsIgnoreCase("n")) {
                                    goodInput3=true;
                                }
                                else {
                                    System.out.println("\nPlease enter y for yes or n for no.");
                                    goodInput3=false;
                                }
                            }
                            while (goodInput3==false);
                        }
                        else {
                            System.out.println("\nThis is a new book.");
                            currentBook = new CBook();
                            System.out.print("Input the book author: ");
                            currentBook.bookAuthor=userInput.nextLine();
                            System.out.print("Input the book title: ");
                            currentBook.bookName=userInput.nextLine();
                            books.put(bookId, currentBook);    
                        }    
                        break;
                    case 3:
                        userInput.nextLine();
                        System.out.print("Enter the ID of the book to remove: ");
                        bookId=userInput.nextLine();
                        if (books.containsKey(bookId)) {
                            currentBook=(CBook)books.get(bookId);
                            System.out.println("\nCurrent Author: " + currentBook.bookAuthor +
                                    "\nCurrent Title: " + currentBook.bookName + "\n");
                            do {
                                try {
                                    System.out.print("Are you sure you want to remove this book? y/n: ");
                                    askUser=userInput.nextLine();
                                    goodInput4=true;
                                }
                                catch (Exception e) {
                                    System.out.println("\nPlease enter y for yes or n for no.");
                                    goodInput4=false;
                                }
                                if (askUser.equalsIgnoreCase("y")) {
                                    books.remove(bookId, currentBook);
                                    System.out.println("\nThis book has been removed.");
                                }
                                else if (askUser.equalsIgnoreCase("n")) {
                                    goodInput4=true;
                                }
                                else {
                                    System.out.println("\nPlease enter y for yes or n for no.");
                                    goodInput4=false;
                                }
                            }
                            while (goodInput4==false);
                        }
                        else {
                            System.out.println("\nThis book does not exist.");
                        }
                        break;
                    case 4:
                        goodInput2=true;
                        break;
                }
            }
        }
        while (goodInput2==false);
    }
}