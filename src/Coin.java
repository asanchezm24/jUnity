import Core.Field;
import Core.Sprite;

import java.util.ArrayList;

public class Coin extends Sprite implements Recogible, CoinSubject {
	ArrayList<CoinObserver> observers;

	public Coin(String name, int x1, int y1, int x2, int y2, double angle, String path,Field f) {
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
			o.update(10, 10);
		}
	}

	//Se llama si la moneda spownea en el suelo o en las plataformas
	public void moverMoneda(){
		this.y1 -= 64;
		this.y2 -= 64;

	}

	@Override
	public void recoger() {
		notifyObservers();
		Joc.tiempoUltimaCoin = System.currentTimeMillis();
		Joc.coinActual = Joc.crearCoins();
		this.delete();
	}
}
