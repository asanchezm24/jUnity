package Juego;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class Joc1 {

	static Field f = new Field();
	static Window w = new Window(f);

	public static void main(String[] args) throws InterruptedException {


		RocaControlable r6 = new RocaControlable("Roca", 50, 700, 500, 1000, 800, "resources/rock1.png", f);


		boolean sortir = false;
		while (!sortir) {
			f.draw();
			input(r6);
			Thread.sleep(30);
		}

	}

	private static void input(RocaControlable r) {
		if (w.getPressedKeys().contains('d')) {
			r.moviment(Input.DRETA);
		}
		if (w.getPressedKeys().contains('a')) {
			r.moviment(Input.ESQUERRA);
		}
		if (w.getPressedKeys().contains('w')) {
			r.moviment(Input.AMUNT);
		}
		if (w.getPressedKeys().contains('s')) {
			r.moviment(Input.AVALL);
		}

	}

}
