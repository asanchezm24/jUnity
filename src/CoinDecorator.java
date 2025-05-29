import Core.Sprite;

public abstract class CoinDecorator implements Coin {

    protected Coin decoratedCoin;

    public CoinDecorator(Coin decoratedCoin) {
        this.decoratedCoin = decoratedCoin;
    }

    @Override
    public void recoger() {
        decoratedCoin.recoger();
    }

    @Override
    public void onCollisionEnter(Sprite sprite) {
        decoratedCoin.onCollisionEnter(sprite);
    }

    @Override
    public void onCollisionExit(Sprite sprite) {
        decoratedCoin.onCollisionExit(sprite);
    }

    @Override
    public void addObserver(CoinObserver observer) {
        decoratedCoin.addObserver(observer);
    }

    @Override
    public void deleteObserver(CoinObserver observer) {
        decoratedCoin.deleteObserver(observer);
    }

    @Override
    public void notifyObservers() {
        decoratedCoin.notifyObservers();
    }
}

