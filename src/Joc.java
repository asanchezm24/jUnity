import java.util.ArrayList;
import java.util.Random;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class Joc {

	static int segundos;
	static Field f = new Field();
	static Window w = new Window(f);
	static Crono crono = new Crono();
	// hola
	static ArrayList<Coin> coins = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {

		// Personaje más pequeño (ej. 50x75)
		Personatge pers = new Personatge("Adri", 100, 745, 150, 820, 0, "resources/Juego/mrPopuu.png", f);

		// Suelo
		Roca terra = new Roca("terra", 0, 820, w.getWidth(), 850, 0, "resources/Juego/Suelo.png", f);

		// Techo
		Roca sostre = new Roca("sostre", 0, 0, w.getWidth(), 10, 0, "resources/Juego/Suelo.png", f);

		// 12 plataformas pequeñas (128x32), distribuidas en distintos puntos
		Roca[] plataformas = new Roca[12];

		plataformas[0] = new Roca("plat1", 50, 700, 178, 732, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[1] = new Roca("plat2", 250, 650, 378, 682, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[2] = new Roca("plat3", 450, 600, 578, 632, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[3] = new Roca("plat4", 650, 550, 778, 582, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[4] = new Roca("plat5", 850, 500, 978, 532, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[5] = new Roca("plat6", 150, 450, 278, 482, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[6] = new Roca("plat7", 350, 400, 478, 432, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[7] = new Roca("plat8", 550, 350, 678, 382, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[8] = new Roca("plat9", 750, 300, 878, 332, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[9] = new Roca("plat10", 950, 250, 1078, 282, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[10] = new Roca("plat11", 200, 200, 328, 232, 0, "resources/Juego/plataforma192x64.png", f);
		plataformas[11] = new Roca("plat12", 400, 150, 528, 182, 0, "resources/Juego/plataforma192x64.png", f);


		Sprite temporizador = new Sprite("Temporizador", w.getWidth() - 80, 50, w.getWidth() - 20, 70, 0, f);
		temporizador.text = true;
		crearCoins();
		int i = 0;
		boolean sortir = false;
		while (!sortir) {
			temporizador.path = Integer.toString(segundos);
			input(pers);
			f.draw();
			Thread.sleep(30);
			if(segundos == 0){
				sortir = true;
				//Esto de abajo no funciona
				Sprite gameOver = new Sprite("gameOver", (w.getWidth()/2)-200, (w.getHeight()/2) - 200,
						(w.getWidth()/2)+200,(w.getHeight()/2) + 200,0,f);
				gameOver.text = true;
			}
		}
	}

	public static void PillarSegundos(int i) {
		segundos = i;
	}

	public static void crearCoins() {
		for (int i = 0; i < 5; i++) {
			Random rand = new Random();
			int x1 = Math.abs(rand.nextInt(0, w.getWidth()));
			int y1 = Math.abs(rand.nextInt(0, w.getHeight()));
			Coin coin = new Coin("coin" + i, x1, y1, x1 + 50, y1 + 50, 0, "resources/Juego/coin.png", f,crono);
			coins.add(coin);
		}
	}

	private static void input(Personatge pers) {
		if (w.getPressedKeys().contains('d')) {
			pers.moviment(Input.DRETA);
		} else if (w.getPressedKeys().contains('a')) {
			pers.moviment(Input.ESQUERRA);
		} else {
			pers.moviment(Input.RES);
		}

		if (w.getPressedKeys().contains('w') && pers.aterra) {
			pers.moviment(Input.SALT);
		} else if (w.getPressedKeys().contains('w') && pers.dobleSalto){
			pers.moviment(Input.SALT);
		}
	}
}
