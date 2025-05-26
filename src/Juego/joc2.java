package Juego;

import Core.Field;
import Core.Window;

public class joc2 {

	static Field f = new Field();
	static Window w = new Window(f);

	public static void main(String[] args) throws InterruptedException {

		Personatge pers = new Personatge("Adri",  250, 250, 300, 350, 0, "resources/ganso.png", f);
		
        // Suelo - largo y fino
        Roca terra = new Roca("terra", 0, 450, 1900, 460, 0, "resources/rock1.png", f);

        // Techo - largo y fino
        Roca sostre = new Roca("sostre", 0, 50, 1900, 60, 0, "resources/rock1.png", f);

        // Plataforma central
        Roca plataforma = new Roca("plat", 300, 300, 700, 310, 0, "resources/rock1.png", f);


        boolean sortir = false;
        while (!sortir) {
            f.draw();
            input(pers);
            Thread.sleep(30);
        }
    }

	private static void input(Personatge pers) {
		if (w.getPressedKeys().contains('d')) {
			pers.moviment(Input2.DRETA);
		}
		if (w.getPressedKeys().contains('a')) {
			pers.moviment(Input2.ESQUERRA);
		}
		if (w.getPressedKeys().contains('w')) {
			pers.moviment(Input2.SALT);
		}

	}
}
