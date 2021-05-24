package interfaces;

public interface Observable<T> {
    void addObserver(Observer<T> observer);
}
