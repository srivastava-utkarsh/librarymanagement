package library;

import java.util.Map;

public interface AbstractLibrary {
    public void addLibrary(Library lib , Map<Integer , Library> map);
    
    public void search();
    
    public void displayLibrariresInfo(Map<Integer , Library> map);
}
