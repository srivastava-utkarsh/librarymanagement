package book;

import java.util.Map;
import java.util.Set;

public interface AbstractBook {
    public Boolean addBook(Book b , Map<Integer , Book> map);
    
    public void displayNotIssuedBooks(Map<Integer , Book> map);
    
    public void displayOnlyIssuedBooks(Map<Integer , Book> map);
    
    
    public Boolean deleteBook(Map<Integer , Book> books , Map<Integer , Set<Integer>> libBookAssoc , int id);
}
