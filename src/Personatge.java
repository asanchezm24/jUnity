import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class Personatge extends PhysicBody {

	private Input input;
	boolean aterra = true;
	int salts = 0;

	public Personatge(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setConstantForce(0, 0.2);
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		if (sprite instanceof Roca) {
			aterra = true;
			salts = 0;
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
			this.setVelocity(+4, 0);
		}
		if (in == Input.ESQUERRA) {
			this.setVelocity(-2, 0);
		}
		if (in == Input.SALT && salts < 2) {
			this.addForce(0, -1);
			this.salts++;
		}

	}

}
