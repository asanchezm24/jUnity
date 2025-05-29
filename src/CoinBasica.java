import java.util.ArrayList;

import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class CoinBasica extends PhysicBody implements Coin {

	protected ArrayList<CoinObserver> observers = new ArrayList<>();
	protected int punts = 1;
	protected int temps = 10;

	public CoinBasica(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f,
			CoinObserver observer) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.addObserver(observer);
	}

	@Override
	public void recoger() {
		notifyObservers();
		deleteObserver(Joc.pers);
		this.delete();
		Joc.tiempoUltimaCoin = System.currentTimeMillis();
		Joc.coinActual = Joc.crearCoins();
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
	}

	@Override
	public void addObserver(CoinObserver observer) {
		observers.add(observer);
	}

	@Override
	public void deleteObserver(CoinObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (CoinObserver o : observers) {
			o.update(punts);
		}
	}
}
