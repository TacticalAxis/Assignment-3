package comp611.assignment3.structure.iface;

public interface SearchTree<E> {

    boolean add(E value);

    boolean remove(E value);

    boolean contains(E value);

    int size();
}