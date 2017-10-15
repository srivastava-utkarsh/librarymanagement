package book;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Set;

public class BookOperations implements AbstractBook{

    @Override
    public Boolean addBook(Book book, Map<Integer, Book> bookCollection) {
        bookCollection.put( book.getBookId() , book );
        return Boolean.TRUE;
    }

    @Override
    public void displayNotIssuedBooks(Map<Integer, Book> map) {
        Iterator<Entry<Integer , Book>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<Integer , Book> entry = itr.next();
            Book bookD = entry.getValue();
            if (bookD !=null && !bookD.isAllocated()) {
                System.out.printf("%n%n%-30s%-30s%-30s%n" , "BookId" ,"Book Title" , "Author");
                System.out.printf("%-30d%-30s%-30s%n" , bookD.getBookId() , bookD.getAuthor() , bookD.getAuthor());
            }
        }
    }
    
    @Override
    public void displayOnlyIssuedBooks(Map<Integer , Book> map){
        Iterator<Entry<Integer,Book>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<Integer,Book> entry = itr.next();
            Book bk = entry.getValue();
            if (bk != null && bk.isAllocated()) {
                System.out.printf("%n%n%-30s%-30s%-30s%n" , "BookId" ,"Book Title" , "Author");
                System.out.printf("%n%n%-5d%-30s%-30s%n" , bk.getBookId() , bk.getAuthor() , bk.getAuthor());
            }
        }
    }
    
    @Override
    public Boolean deleteBook(Map<Integer , Book> books , Map<Integer , Set<Integer>> libBookAssoc , int id){
        
        try {
            
            Iterator<Entry<Integer , Set<Integer>>> itr = libBookAssoc.entrySet().iterator();
            while (itr.hasNext()) {
                Entry<Integer , Set<Integer>> entry = itr.next();
                Set<Integer> bookIds = entry.getValue();
                if (bookIds.contains(id)) {
                    bookIds.remove(id);
                    books.remove(id);
                    if (bookIds.size() == 0) {
                        itr.remove();
                    }
                    return Boolean.TRUE;
                }
            }
            
            return Boolean.FALSE;
            
        } catch (IllegalStateException | UnsupportedOperationException e) {
            System.out.printf("%n%n Exception occured in BookOperation.java in deleteBook method. %n%n");
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
