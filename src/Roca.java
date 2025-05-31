import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

//classe Roca que és filla de Sprite
public class Roca extends PhysicBody {

	// la variable no sera estatica, ja que no és una classe amb main
	private int accionsDisponibles;
	//Contador
	public static int comptador = 0;
	public String id = "Roca " + (comptador+1);

	// constructor generat automàticament
	public Roca(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f) {
		// crida al constructor del pare, com a primera linea.
		// Per tant cridara a new Sprite() amb els paràmetres passats
		super(name, x1, y1, x2, y2, angle, path, f);
		this.name = id;
		this.setAccionsDisponibles(50);
		// aqui faras altres coses que vulguis fer al crear una nova Roca.
		comptador++;
	}

	//Comprobar si la moneda spownea en una plataforma o el suelo
	@Override
	public void onCollisionEnter(Sprite sprite) {
		if(sprite instanceof Coin){
			((Coin) sprite).moverMoneda();
		}
	}

	@Override
	public void onCollisionExit(Sprite sprite) {

	}

	//constructor amb accions disponibles. Aquest es un constructor diferent al primer i has de tenir els dos!
    public Roca(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, int accions) {
        //crida al constructor del pare, com a primera linea. 
        //Per tant cridara a new Sprite() amb els paràmetres passats
        this(name, x1, y1, x2, y2, angle, path, f);
        this.name = id;
        //aqui faras altres coses que vulguis fer al crear una nova Roca.
        //this. fa una referencia al propi objecte. this.accionsDisponibles es refereix a la variable accionsDisponibles global a l'objecte     
        this.setAccionsDisponibles(accions);
    }

    public Roca(int x1, int y1, int x2, int y2, Field f, int accions) {
    	
    	this("Roca", x1, y1, x2, y2, 0, "resources/rock1.png", f);
    	this.name = id;
    	this.setAccionsDisponibles(accions);
    }
    
    public Roca(int x1, int y1, int mida, Field f) {
    	this("Roca quadrada", x1, y1, (x1+mida), (y1+mida), 0, "resources/rock1.png", f);
    	this.name = id;
    	this.setAccionsDisponibles(50);
    }
    
    public Roca() {
    	this("Roca buida", 0, 0, 50, 50, 0, "resources/rock1.png", Joc.f);
    	this.name = id;
    	this.setAccionsDisponibles(50);
    }
    
    //Getters i Setters

	public int getAccionsDisponibles() {
		return accionsDisponibles;
	}

	public void setAccionsDisponibles(int accionsDisponibles) {
		this.accionsDisponibles = accionsDisponibles;
	}
}
