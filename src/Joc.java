import Core.Field;
import Core.Window;

import java.util.ArrayList;
import java.util.Random;

public class Joc {

	static Field f = new Field();
	static Window w = new Window(f);
	//hola
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

		crearCoins();
		int i = 0;
        boolean sortir = false;
        while (!sortir) {

            f.draw();
            input(pers);
            Thread.sleep(30);
        }
    }

	public static void crearCoins(){
		for(int i = 0; i < 5; i++){
			Random rand = new Random();
			int x1 = Math.abs(rand.nextInt(0,w.getWidth()));
			int y1 = Math.abs(rand.nextInt(0,w.getHeight()));
			Coin coin = new Coin("coin" + i, x1, y1, x1 + 50, y1 + 50,0, "resources/Juego/coin.png", f);
			coins.add(coin);
		}
	}

	private static void input(Personatge pers) {
		if (w.getPressedKeys().contains('d')) {
			pers.moviment(Input.DRETA);
		} else if (w.getPressedKeys().contains('a')) {
			pers.moviment(Input.ESQUERRA);
		} else if (w.getPressedKeys().contains('w')) {
			pers.moviment(Input.SALT);
		} else {
			pers.moviment(Input.RES);
		}
	}
}
