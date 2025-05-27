import java.util.Timer;
import java.util.TimerTask;

public class Crono implements CoinObserver {

    Timer timer;
    TimerTask task;
    //Segundos con los que empieza el juego. Retocar si necesario.
    int i = 30;

    //Cuando se llama al constructor se inicializa el timer y se setea la Task.
    public Crono(){
        this.timer = new Timer();
        //Se inicializa la variable con una función anónima que hará cada vez
        //que se le llame
        this.task = new TimerTask() {
            @Override
            public void run() {
                if(i >= 0){
                    Joc.pillarSegundos(i);
                    i--;
                }
            }
        };
        //Una vez inicializado se utiliza esta funcion para que realice la task
        //cada 1000 ms. el 0 es el delay asi que mejor no tocarlo.
        timer.scheduleAtFixedRate(task,0,1000);
    }

    //Actualiza el tiempo cuando una moneda es recogida por el player.
    @Override
    public void update(int segundos) {
        this.i += segundos;
    }
}
