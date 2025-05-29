import Core.Field;
import Core.Sprite;

import java.util.ArrayList;

public class CoinRoja extends Coin implements Recogible, CoinSubject{

	ArrayList<CoinObserver> observers;

	public CoinRoja(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.observers = new ArrayList<>();
	}

	@Override
	public void addObserver(CoinObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void deleteObserver(CoinObserver observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for(CoinObserver o: observers){
			o.update(20, 8);
		}
	}

	@Override
	public void recoger() {
		notifyObservers();
		Joc.tiempoUltimaCoin = System.currentTimeMillis();
		Joc.coinActual = Joc.crearCoins();
		this.delete();
	}
}
