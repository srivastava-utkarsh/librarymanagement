package book;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private boolean isAllocated;
    private int libraryId;
    private int bookId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsAllocated(boolean isAllocated) {
        this.isAllocated = isAllocated;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }
    
    

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public boolean isAllocated() {
        return isAllocated;
    }
    
    public int getLibraryId(){
        return libraryId;
    }
    
    @Override
    public String toString(){
        return String.format("%n%-20s%-20s%-20s%-20s%n", this.title , this.author , this.publisher , (this.isAllocated == true) ? "Allocated" : "Not allocated" );
    }
}
