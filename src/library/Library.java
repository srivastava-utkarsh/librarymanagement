package library;

import book.Book;
import java.util.LinkedList;

public class Library {
    private String name;
    private String ownerName;
    private String address;
    private int libId;
    
    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAddress() {
        return address;
    }
    
    public int getLibraryId(){
        return libId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLibId(int libId) {
        this.libId = libId;
    }
   
    @Override
    public String toString(){
        return String.format("%n%-30s%-30s%-30s%n", this.name , this.ownerName , this.address);
    }
}
