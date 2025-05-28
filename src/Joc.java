import java.util.ArrayList;
import java.util.Random;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class Joc {

	static int segundos;
	static int puntos = 0;
	static Field f = new Field();
	static Window w = new Window(f);
	static Crono crono = new Crono();
	// hola
	static ArrayList<Coin> coins = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {



		// Personaje más pequeño (ej. 50x75)
		//Con este ratio en mi Pantalla se ve bien. Probadlo vosotros y me decís. -Xavi
		Personatge pers = new Personatge("Adri", 800, w.getHeight()-230, 950, w.getHeight()-50, 0, "resources/Juego/mrPopuu.png", f);

		// Suelo
		Roca terra = new Roca("terra", 0, w.getHeight()-50, w.getWidth(), w.getHeight(), 0, "resources/Juego/Suelo.png", f);

		// Techo
		Roca sostre = new Roca("sostre", 0, 0, w.getWidth(), 10, 0, "resources/Juego/Suelo.png", f);

		// 12 plataformas pequeñas (128x32), distribuidas en distintos puntos
		Roca[] plataformas = new Roca[12];

		plataformas[0] = new Roca("plat1", 50, w.getHeight()-300, 242, w.getHeight()-234, 0, "resources/Juego/plataforma192x64.png", f);
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

		//Srite vacío para el timer
		//He tenido que poner la clase Sprite en público, para no hacer una clase nueva.
		//Si se necesita hacer otro, hacemos la clase y volvemos a poner el sprite en private o package.
		Sprite temporizador = new Sprite("Temporizador", w.getWidth() - 200, 50, w.getWidth() - 20, 70, 0 ,f);
		temporizador.text = true;

		//Sprite.text se pone a true para que enseñe el Path como texto
		Sprite marcador = new Sprite("Marcador",w.getWidth() - 200, 90, w.getWidth() - 20, 110, 0 ,f);
		marcador.text = true;

		crearCoins(pers);

		boolean sortir = false;
		while (!sortir) {
			temporizador.path = "Temps: "+Integer.toString(segundos );
			marcador.path = "Punts: "+Integer.toString(pers.getPuntos());
			input(pers);
			f.draw();
			//TODO: Hay que mirar esto, tiene que haber otra forma.
			Thread.sleep(45);
			if(segundos == 0){
				sortir = true;
				//Esto de abajo no funciona
				Sprite gameOver = new Sprite("gameOver", (w.getWidth()/2)-200, (w.getHeight()/2) - 200,
						(w.getWidth()/2)+200,(w.getHeight()/2) + 200,0,f);
				gameOver.text = true;
			}
		}
	}


	public static void pillarSegundos(int i) {
		segundos = i;
	}
	
	public static void PillarPuntos(int i){
		puntos = i;
	}

	//TODO: Que las Coins no spawneen en el timer pls -Xavi <3 os lamo el siempresucio
	//Esto lo ha hecho el Sam
	//Comenta el codigo, puerco.
	public static void crearCoins(Personatge pers) {
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();
			int x1 = Math.abs(rand.nextInt(0, w.getWidth()));
			int y1 = Math.abs(rand.nextInt(20, w.getHeight()-100));
			Coin coin = new Coin("coin" + i, x1, y1, x1 + 50, y1 + 50, 0, "resources/Juego/coin.png", f,crono);
			coin.addObserver(pers);
			coins.add(coin);
		}
	}

	private static void input(Personatge pers) {

		//Por alguna razón, esto funciona mejor que lo otro. No se, si veis otra forma mejor provadla.
		boolean dreta = w.getPressedKeys().contains('d');
		boolean esquerra = w.getPressedKeys().contains('a');
		boolean salto = w.getPressedKeys().contains('w');

		if (dreta) {
			pers.moviment(Input.DRETA);
		} else if (esquerra) {
			pers.moviment(Input.ESQUERRA);
		} else if (salto){
			pers.moviment(Input.SALT);
		} else{
			pers.moviment(Input.RES);
		}

	}
}
