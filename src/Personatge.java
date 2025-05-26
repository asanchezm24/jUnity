import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

public class Personatge extends PhysicBody {

	private Input input;
	boolean aterra = true;
	boolean dobleSalto = true;
	//TODO: Mejorar movimiento y saltos.


	public Personatge(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		super(name, x1, y1, x2, y2, angle, path, f);
		this.setConstantForce(0, 0.2);
	}

	@Override
	public void onCollisionEnter(Sprite sprite) {
		if (sprite instanceof Roca && sprite.y1 >= this.y2) {
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
		}
		if (in == Input.ESQUERRA) {
			this.setVelocity(-5, this.velocity[1]);
		}
		if (in == Input.SALT) {
			if(aterra) {
				this.addForce(0, -1);
			} else if (dobleSalto){
				this.addForce(0,-1);
				dobleSalto = false;
			}
		}
		if(in.equals(Input.RES)){
			this.setVelocity(0, this.velocity[1]);
		}

	}

}
