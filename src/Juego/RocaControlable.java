package Juego;

import Core.Field;

public class RocaControlable extends Roca {

	public RocaControlable(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		// crida al constructor del pare, com a primera linea.
		// Per tant cridara a new Sprite() amb els paràmetres passats
		super(name, x1, y1, x2, y2, angle, path, f);
	}

	public void moviment(Input in) {
		if(this.getAccionsDisponibles() > 0) {
			if(in == Input.DRETA){
				this.x1 += 1;
				this.x2 += 1;
				this.setAccionsDisponibles(getAccionsDisponibles()-1);
			}
			if(in == Input.ESQUERRA){
				this.x1 -= 1;
				this.x2 -= 1;
				this.setAccionsDisponibles(getAccionsDisponibles()-1);
			}
			if(in == Input.AMUNT){
				this.y1 -= 1;
				this.y2 -= 1;
				this.setAccionsDisponibles(getAccionsDisponibles()-1);
			}
			if(in == Input.AVALL){
				this.y1 += 1;
				this.y2 += 1;
				this.setAccionsDisponibles(getAccionsDisponibles()-1);
			}
		} else {
			delete();
		}


	}
}
