import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class Personatge extends PhysicBody implements CoinObserver {

	private int puntos;
	boolean aterra = true;
	boolean dobleSalto = false;
	// TODO: Mejorar movimiento y saltos.

	public Personatge(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.puntos = 0;
		this.setConstantForce(0, 0.2);
	}

	// Getters y Setters

	public int getPuntos() {
		return puntos;
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		if (sprite instanceof Roca && sprite.y1 >= this.y2) {
			// Cuando vuelve al suelo todos los booleanos a true y se resetea el sprite
			aterra = true;
			dobleSalto = false;
			this.changeImage("resources/Juego/mrPopuu.png");
			System.out.println("Ha tocado suelo");
		} else if (sprite instanceof Recogible) {
			Recogible rec = (Recogible) sprite;
			rec.recoger();
		}

		System.out.println(sprite.name);
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		if (sprite instanceof Roca) {
			// Cuando salte del colider del suelo Aterra se pone a false y se cambia el
			// sprite por el de salto. No notarán la diferencia. Es negro igual.
			aterra = false;
			System.out.println("Salio de la colision Roca");
			// Esta función está en la clase Sprite del Core.
			this.changeImage("resources/Juego/Salto.png");
		}
	}

	// Lo he retocado porque no me dejaba saltar mientras avanzaba.
	public void moviment(Input in) {
		//this.velocity[1] en la Y te permite mantener la fuerza de altura y poder
		//seguir moviendote hacia los lados.

		System.out.println(in);
		if (in == Input.DRETA) {
			this.setVelocity(+5, this.velocity[1]);
		}
		if (in == Input.ESQUERRA) {
			this.setVelocity(-5, this.velocity[1]);
		}
		if (in == Input.SALT) {
			// No he borrado el codigo del Adri, está más abajo comentado.
			if (aterra) {
				System.out.println("Aqui entra");
				this.setVelocity(0, 0);
				this.addForce(0, -2.3);
				aterra = false;
				dobleSalto = true;
			} else if (dobleSalto) {
				System.out.println("Doble salt");
				this.setVelocity(0, 0);
				this.addForce(0, -2.3);
				dobleSalto = false;
			}
		}
		if (in == Input.RES) {
			this.setVelocity(0, this.velocity[1]);
		}

	}

	@Override
	public void update(int num) {
		this.puntos += num;
	}
}
