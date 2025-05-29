public class CoinRoja extends CoinDecorator {

	private int extraPoints = 9;

	public CoinRoja(Coin decoratedCoin) {
		super(decoratedCoin);
	}

	@Override
	public void recoger() {
		System.out.println("Moneda Roja recogida!");
		decoratedCoin.notifyObservers();
		for (CoinObserver o : ((CoinBasica) decoratedCoin).observers) {
			o.update(extraPoints); // Le sumamos 9 más
		}
		decoratedCoin.recoger();
	}
}
