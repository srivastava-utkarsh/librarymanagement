package book;

public class Book {
    private String name;
    private String author;
    private String publisher;
    private boolean isAllocated;
    
    public Book(String name , String author , String publisher){
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.isAllocated = isAllocated;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public boolean isIsAllocated() {
        return isAllocated;
    }
    
    @Override
    public String toString(){
        return String.format("%n%-20s%-20s%-20s%-20s%n", this.name , this.author , this.publisher , (this.isAllocated == true) ? "Allocated" : "Not allocated" );
    }
}
