package interfaces;

public interface Observer<T> {
    void notifyUpdate(T item);
}
