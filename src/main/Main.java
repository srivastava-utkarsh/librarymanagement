package main;

import java.util.Map.Entry;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;
import library.*;
import book.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.io.IOException;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Integer incrementer = 1;
    private static Map<Integer , Library> libraries = new TreeMap<Integer , Library>();
    private static Map<Integer , Book> books = new TreeMap<Integer , Book>();
    private static LibraryOperations libImpl = new LibraryOperations();
    private static BookOperations bookImpl = new BookOperations();
    private static Map<Integer , Set<Integer>> libBookAssoc = new TreeMap<Integer , Set<Integer>>();
    
    public static void main(String[] args) {
        mainMenu();
        boolean exit = false;
        while (!exit) {
            System.out.println("***** Please select your option *******");
            try {
                int selectedOption = sc.nextInt();
                sc.nextLine();
                action(selectedOption);
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Error occured in main->while loop");
            } catch (IOException e){
                System.out.println("Error in action");
            }
        }
    }
    
    private static void action(int i) throws IOException {
        switch (i) {
            case 0:
                System.out.println("***** Library Details *****");
                displayAllLibraries(libraries);
                mainMenu();
                break;
            case 1:
                System.out.println("***** Library and book association *****");
                displayLibBookassoc(libBookAssoc , true);
                mainMenu();
                break;
            case 2:
                System.out.println("***** Display Existing Available Books *****");
                bookImpl.displayNotIssuedBooks(books);
                mainMenu();
                break;
            case 3:
                System.out.println("***** Display Existing Issued Books *****");
                bookImpl.displayOnlyIssuedBooks(books);
                mainMenu();
                break;
            case 4:
                System.out.println("*****  Add new library *****");
                addLibrary();
                mainMenu();
                break;
            case 5:
                System.out.println("***** Add Book *****");
                addBook();
                mainMenu();
                break;
            case 6:
                System.out.println("***** Issue a Book *****");
                issueBook();
                mainMenu();
                break;
            case 7:
                System.out.println("***** Delete a Book *****");
                deleteBook();
                mainMenu();
                break;
            case 8:
                
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private static void deleteBook(){
        try {
            if (books.isEmpty()) {
                System.out.println("No book added yet!");
            }else{
                displayLibBookassoc(libBookAssoc, false);
                System.out.println("enter bookid of book which you want to delete :-");
                int bookId =  sc.nextInt();
                sc.nextLine();
                if (books.containsKey(bookId)) {
                    
                    if (bookImpl.deleteBook(books , libBookAssoc, bookId)) {
                        System.out.println("Deleted Successfully");
                    }else{
                        System.out.println("Could not delete book");
                    }
                    
                }else{
                    System.out.println("Entered BookId does not match. Please try again.");
                    issueBook();
                }
            }
        } catch (Exception e) {
        }
    }
    
    private static void issueBook(){
        try {
            if (books.isEmpty()) {
                System.out.println("No book added yet!");
            }else{
                System.out.println("List of available books are :-");
                displayLibBookassoc(libBookAssoc , false);
                int bookId =  sc.nextInt();
                sc.nextLine();
                if (books.containsKey(bookId)) {
                    books.get(bookId).setIsAllocated(true);
                }else{
                    System.out.println("Entered BookId does not match. Please try again.");
                    issueBook();
                }
            }
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }
    
    private static void addBook() throws IOException {
        Book book = new Book();
        book.setBookId(incrementer++);
        System.out.println("Enter book title :-");
        book.setTitle(sc.nextLine());
        System.out.println("Enter book author :-");
        book.setAuthor(sc.nextLine());
        System.out.println("Enter Associated LibraryID :-");
        book.setLibraryId(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter Publisher :-");
        book.setPublisher(sc.nextLine());
        validateLibraryID(book);
        bookImpl.addBook(book, books);
    }
    
    
    private static void validateLibraryID(Book bk){
        if (libraries.containsKey(bk.getLibraryId()) == false) {
            System.out.println("Entered LibraryId not available");
            if (displayAllLibraries(libraries)) {
                System.out.println("Enter Associated Library :-");
                bk.setLibraryId(sc.nextInt());
                sc.nextLine();
                validateLibraryID(bk);
            }
        }else{
            Set<Integer> bookIdSet = libBookAssoc.get(bk.getLibraryId());
            if (bookIdSet == null) {
                bookIdSet = new HashSet<Integer>();
            }
            bookIdSet.add(bk.getBookId());
            libBookAssoc.put(bk.getLibraryId(), bookIdSet);
        }
    }
    
    private static void addLibrary() throws IOException {
        Library libD = new Library();
        libD.setLibId(incrementer++);
        libImpl.addLibrary(libD, libraries);
        
        System.out.println("Enter Library Name :-");
        String name = sc.nextLine();
        libD.setName(name);
        System.out.println("Enter Library Address :-");
        String address = sc.nextLine();
        libD.setAddress(address);
        System.out.println("Enter Library Owner Name :-");
        String owner = sc.nextLine();
        libD.setOwnerName(owner);
    }
    
    private static boolean displayAllLibraries(Map<Integer , Library> lib){
        if (lib.isEmpty()) {
            System.out.println("No libraries added yet!");
            return false;
        }else{
            Iterator<Entry<Integer , Library>> itr = lib.entrySet().iterator();
            while (itr.hasNext()) {
                Entry<Integer , Library> entry = itr.next();
                Library libD = entry.getValue();
                System.out.printf("%n%n%-10d%-20s%n" , libD.getLibraryId() , libD.getName());
            }
            return true;
        }
    }
    
    private static void displayLibBookassoc(Map<Integer , Set<Integer>> libBkAssoc , boolean showAll){
        if (libBkAssoc.isEmpty()) {
            System.out.println("No book assigned to library yet");
        }else{
            Iterator<Entry<Integer ,  Set<Integer>>> itr = libBkAssoc.entrySet().iterator();
            while (itr.hasNext()) {
                Entry<Integer , Set<Integer>> entry = itr.next();
                Integer key = entry.getKey();
                Iterator itrSet = entry.getValue().iterator();
                System.out.printf("%n%n%-40s%-40s%-40s%-40s%n" , "Library Name" , "BookId" ,"Book Title" , "Author");
                show : while (itrSet.hasNext()) {
                    Integer bookId = (Integer)itrSet.next();
                    if (books.containsKey(bookId)) {
                        Book book = books.get(bookId);
                        if (showAll == false && book.isAllocated()) {
                            continue show;
                        }
                        System.out.printf("%-40s%-40d%-40s%-40s%n" , libraries.get(key).getName() , book.getBookId() ,  book.getTitle() , book.getAuthor());
                    }
                }
            }
        }
    }
    
    private static void mainMenu(){
        System.out.printf("%n%n");
        System.out.println("***** Main Menu *******");
        System.out.println("0: Display Existing Libraries");
        System.out.println("1: Display Existing Libraries and Books Details");
        System.out.println("2: Display Existing Available Books");
        System.out.println("3: Display Existing Issues Books");
        System.out.println("4: Add New Libray");
        System.out.println("5: Add New Book");
        System.out.println("6: Issue a Book");
        System.out.println("7: Delete a Book");
        System.out.println("8: To Stop KnowledgeWala Application");
        System.out.printf("%n%n");
    }
}
