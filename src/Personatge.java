import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class Personatge extends PhysicBody {

	private Input input;
	boolean aterra = true;
	boolean dobleSalto = true;

	public Personatge(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setConstantForce(0, 0.2);
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		if (sprite instanceof Roca) {
			aterra = true;
			dobleSalto = true;
		}
	}

	@Override
	public void onCollisionExit(Sprite sprite) {
		if (sprite instanceof Roca) {
			aterra = false;
		}
	}

	public void moviment(Input in) {

		if (in == Input.DRETA) {
			this.setVelocity(+5, this.velocity[1]);
			this.flippedX = true;
		}
		if (in == Input.ESQUERRA) {
			this.setVelocity(-5, this.velocity[1]);
			this.flippedX = false;
		}
		if (in == Input.SALT) {
			if (aterra) {
				salto();
			} else if (!aterra && dobleSalto) {
				salto();
				dobleSalto = false;
			}
		}
		if (in.equals(Input.RES)) {
			this.setVelocity(0, this.velocity[1]);
		}
	}

	public void salto() {
		this.setVelocity(this.velocity[0], 0);
		this.addForce(0, -1);
	}
}
