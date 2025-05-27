import java.util.Timer;
import java.util.TimerTask;

public class Crono implements CoinObserver {

    Timer timer;
    TimerTask task;
    int i = 30;

    public Crono(){
        this.timer = new Timer();
        this.task = new TimerTask() {
            @Override
            public void run() {
                if(i >= 0){
                    Joc.PillarSegundos(i);
                    i--;
                }
            }
        };

        timer.scheduleAtFixedRate(task,0,1000);
    }

    @Override
    public void updateTime(int segundos) {
        this.i += segundos;
        System.out.println(segundos);
    }
}
