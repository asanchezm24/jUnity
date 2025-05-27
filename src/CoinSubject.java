public interface CoinSubject {
    void addObserver(CoinObserver observer);
    void deleteObserver(CoinObserver observer);
    void notifyObservers();
}
