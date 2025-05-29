import java.util.Random;

import Core.Field;
import Core.Sprite;
import Core.Window;

public class Joc {

	public static int segundos;
	public static Field f = new Field();
	public static Window w = new Window(f);
	public static Crono crono = new Crono();
	public static Coin coinActual = null;
	public static long tiempoUltimaCoin = System.currentTimeMillis();
	public static Personatge pers = new Personatge("Adri", 800, w.getHeight() - 230, 950, w.getHeight() - 50, 0,
			"resources/Juego/mrPopuu.png", f);




	public static void main(String[] args) throws InterruptedException {

		// Con este ratio en mi Pantalla se ve bien. Probadlo vosotros y me decís. -Xavi

		// Suelo
		Roca terra = new Roca("terra", 0, w.getHeight() - 50, w.getWidth(), w.getHeight(), 0,
				"resources/Juego/Suelo.png", f);

		// Techo
		Roca sostre = new Roca("sostre", 0, 0, w.getWidth(), 10, 0, "resources/Juego/Suelo.png", f);

		//Calculos de dimensiones
		Roca[] plataformas = calcularPlataformas(terra);
		calcularTamañoPj(plataformas[0]);


	/*	plataformas[1] = new Roca("plat2", (int)plataformas[0].x2+200, w.getHeight() - 300, (int) plataformas[0].x2+392, w.getHeight() - 234,
				0, "resources/Juego/plataforma192x64.png", f);
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
*/
		// Srite vacío para el timer
		// He tenido que poner la clase Sprite en público, para no hacer una clase nueva.
		// Si se necesita hacer otro, hacemos la clase y volvemos a poner el sprite en
		// private o package.
		Sprite temporizador = new Sprite("Temporizador", w.getWidth() - 200, 50, w.getWidth() - 20, 70, 0, f);
		temporizador.text = true;

		// Sprite.text se pone a true para que enseñe el Path como texto
		Sprite marcador = new Sprite("Marcador", w.getWidth() - 200, 90, w.getWidth() - 20, 110, 0, f);
		marcador.text = true;

		//Texto de final de partida
		Sprite gameOver = new Sprite("gameOver", (w.getWidth() / 2) - 200, (w.getHeight() / 2) - 200,
				(w.getWidth() / 2) + 200, (w.getHeight() / 2) + 200, 0, f);

		gameOver.solid = false;
		gameOver.path = "GAME OVER";

		coinActual = crearCoins();

		boolean sortir = false;
		while (!sortir) {
			temporizador.path = "Temps: " + Integer.toString(segundos);
			marcador.path = "Punts: " + Integer.toString(pers.getPuntos());
			input(pers);
			f.draw();
			long tiempoActual = System.currentTimeMillis();
			if (tiempoActual - tiempoUltimaCoin >= 10000) { // 10 segundos en milisegundos
				if (coinActual != null) {
					//coinActual.delete(); // elimina del campo
				}
				coinActual = crearCoins();
				tiempoUltimaCoin = tiempoActual;
			}

			// TODO: Hay que mirar esto, tiene que haber otra forma.
			Thread.sleep(30);
			if (segundos == 0) {
				gameOver.solid = true;
				gameOver.text = true;
				f.draw();
				sortir = true;
			}
		}
	}

	private static void calcularTamañoPj(Roca plataforma) {
		//static Personatge pers = new Personatge("Adri", 800, w.getHeight() - 230, 950, w.getHeight() - 50, 0,
		//			"resources/Juego/mrPopuu.png", f);
		int anchoPj = w.getWidth() / 14;
		int altoPj = (w.getHeight() / 16) * 3;

		pers.x1 = plataforma.x2 + 50;
		pers.x2 = pers.x1 + anchoPj;
		pers.y1 = plataforma.y2;
		pers.y2 = pers.y1 + altoPj;
	}


	// PROPORCIONES PORQUE LAS PANTALLAS DEL COLE SON BASURA:
	/*
	 *  Plataformas: Cada plataforma es de x = 192 e y = 64 idílicamente
	 *               - Lo que pasa:
	 * 					En la proporcion correcta caben 15 de altura (16 muy apurados porue sobresalen del techo )
	 *  					Utilizad 16 si os va mejor para los calculos.
	 * 					Y 8 o 9 de ancho. (De nuevo igual que la altura. Lo que vaya mejor)
	 * 					ALTURA DE LA PLATAFORMA: AlturaVentana/15
	 * 					ANCHO PLATAFORMA: AnchoVentana/8
	 *
	 *  PJ: y = Plataforma.Y * 3 x = Plataforma.X (Mas o menos, hay que ajustarlo porque creo que es un poco menos, pero no mucho)
	 *          EJ: y = 64 * 3 ->  192 x = 150  ESTA PROPORCION EN MI PANTALLA VA BIEN.
	 *  TODO: COMPROBADLO PORFA
	 *  Distancia/Hileras de plataformas: Plataforma.Y * 4 Para dejar que el player pueda saltar horizontalmente.
	 *  Distancia/Plataformas misma Hilera: PJ.X * 2
	 */
	private static Roca[] calcularPlataformas(Roca terra) {
		Roca[] plataformas = new Roca[15];

		int plataformaHeight = w.getHeight()/16;
		int plataformaWidth = w.getWidth()/8;

		//Primera Hilera:
		plataformas[0] = new Roca("plat0",
				plataformaWidth,
				w.getHeight() - 300,
				plataformaWidth + plataformaWidth,
				w.getHeight() - 236,
				0, "resources/Juego/plataforma192x64.png", f);
		plataformas[1] = new Roca("plat1",
				(int) plataformas[0].x2 + plataformaWidth + (plataformaWidth/2),
				w.getHeight() - 300,
				(int) plataformas[0].x2 + plataformaWidth + plataformaWidth + (plataformaWidth/2),
				w.getHeight() - 236,
				0, "resources/Juego/plataforma192x64.png", f);
		plataformas[2] = new Roca("plat2",
				(int) plataformas[1].x2 + plataformaWidth + (plataformaWidth/2),
				w.getHeight() - 300,
				(int) plataformas[1].x2 + plataformaWidth + plataformaWidth + (plataformaWidth/2),
				w.getHeight() - 236,
				0, "resources/Juego/plataforma192x64.png", f);

	   	//Segunda Hilera

		plataformas[3] = new Roca("plat2",
				plataformaWidth/2,
				(int)plataformas[0].y1 - (plataformaHeight* 4)- 64,
				plataformaWidth/2 + plataformaWidth,
				(int)plataformas[0].y1 - (plataformaHeight* 4) ,
				0, "resources/Juego/plataforma192x64.png", f);

		plataformas[4] = new Roca("plat2",
				(int)plataformas[3].x2 + plataformaWidth,
				(int)plataformas[0].y1 - (plataformaHeight* 4)- 64,
				(int)plataformas[3].x2 + (plataformaWidth *2),
				(int)plataformas[0].y1 - (plataformaHeight* 4) ,
				0, "resources/Juego/plataforma192x64.png", f);
		plataformas[5] = new Roca("plat2",
				(int)plataformas[4].x2 + plataformaWidth,
				(int)plataformas[0].y1 - (plataformaHeight* 4)- 64,
				(int)plataformas[4].x2 + (plataformaWidth *2),
				(int)plataformas[0].y1 - (plataformaHeight* 4) ,
				0, "resources/Juego/plataforma192x64.png", f);
		plataformas[6] = new Roca("plat2",
				(int)plataformas[5].x2 + plataformaWidth,
				(int)plataformas[0].y1 - (plataformaHeight* 4)- 64,
				(int)plataformas[5].x2 + (plataformaWidth *2),
				(int)plataformas[0].y1 - (plataformaHeight* 4) ,
				0, "resources/Juego/plataforma192x64.png", f);



		calcularTamañoPj(plataformas[0]);
		return plataformas;
	}

	public static void pillarSegundos(int i) {
		segundos = i;
	}

	// TODO: Que las Coins no spawneen en el timer pls -Xavi <3 os lamo el
	// siempresucio
	// Esto lo ha hecho el Sam
	// Comenta el codigo, puerco.
	public static Coin crearCoins() {
		Random rand = new Random();
		int x1 = Math.abs(rand.nextInt(0, w.getWidth()));
		int y1 = Math.abs(rand.nextInt(20, w.getHeight() - 100));
		Coin coin = new CoinBasica("coin", x1, y1, x1 + 50, y1 + 50, 0, "resources/Juego/coin.png", f, crono);
		
		int r = rand.nextInt(1,11);
		
		if(r == 7) {
			CoinRoja cr = new CoinRoja(coin);
		}
		coin.addObserver(pers);

		return coin;
	}

	// Algo mas limpio que lo otro.
	private static void input(Personatge pers) {
		boolean keyPressed = false;

		if (w.getPressedKeys().contains('d')) {
			pers.moviment(Input.DRETA);
			keyPressed = true;
		}
		if (w.getPressedKeys().contains('a')) {
			pers.moviment(Input.ESQUERRA);
			keyPressed = true;
		}
		if (w.getKeysDown().contains('w')) {
			pers.moviment(Input.SALT);
			keyPressed = true;
		}

		if (!keyPressed) {
			pers.moviment(Input.RES);
		}
	}
}
