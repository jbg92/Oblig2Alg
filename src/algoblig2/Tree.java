/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoblig2;

import java.util.Collection;

/**
 *
 * @author John-Berge
 * @param <E>
 */
public interface Tree<E> extends Collection<E> {
    public boolean search(E e); // ret. true dersom finst
    public boolean insert(E e); // ret. true dersom OK
    public boolean delete(E e); // ret. True dersom OK
    public int getSize();
    // traverseringsmetoder skriv berre ut – lite nyttige.
    // Vi såg på desse forrige veke
    @Override
    public default boolean isEmpty() {
        return size() == 0;
    }
    @Override
    public default boolean contains(Object e){
        return search((E)e);
    }
    @Override
    public default boolean add(E e){
        return insert(e);
    }
    @Override
    public default boolean remove(Object e){
        return delete((E)e);
    }
    @Override
    public default int size(){
        return getSize();
    }
    @Override
    public default boolean containsAll(Collection<?> c){
        return false;
    //Oppgåve!
    
    }
    @Override
    public default boolean addAll(Collection<? extends E> c){
        return false;
    // Oppgåve! 
    }
    @Override
    public default boolean removeAll(Collection<?> c){
        return false;
    // Oppgåve!
    }
    @Override
    public default boolean retainAll (Collection<?> c){
        return false;
    // Oppgåve!
    }
    @Override
    public default Object[] toArray() {
        return null;
    // Oppgåve!
    }
    @Override
    public default <T> T[] toArray(T[] array){
        return null;
    // Oppgåve!
    }
} // Slutt interface Tree