package library;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class LibraryOperations implements AbstractLibrary{

    @Override
    public void addLibrary(Library lib, Map<Integer, Library> map) {
        map.put(lib.getLibraryId(), lib);
    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayLibrariresInfo(Map<Integer, Library> map) {
        Iterator<Entry<Integer , Library>> itr = map.entrySet().iterator();
        System.out.println("***********  Library Information ************");
        while (itr.hasNext()) {
            Entry<Integer , Library> entry = itr.next();
            Library libD = entry.getValue();
            System.out.printf("%n%-10d%-20s%n" , libD.getLibraryId() , libD.getName());
        }
    }
}
