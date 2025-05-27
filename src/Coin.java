import Core.Field;
import Core.PhysicBody;
import Core.Sprite;

import java.util.ArrayList;

public class Coin extends PhysicBody implements Recogible, CoinSubject {

    ArrayList<CoinObserver> observers = new ArrayList<>();

    public Coin(String name, int x1, int y1, int x2, int y2, double angle, String path, Field f, CoinObserver observer){
        super(name, x1, y1, x2, y2, angle, path, f);
        this.addObserver(observer);
    }

    @Override
    public void onCollisionEnter(Sprite sprite) {
    }

    @Override
    public void onCollisionExit(Sprite sprite) {
    }


    //Al recoger avisa a los observers y se elimina
    //TODO: Que la coin no tenga colision y puedas recojerla sin chocar.
    @Override
    public void recoger() {
        this.notifyObservers();
        this.delete();
    }

    @Override
    public void addObserver(CoinObserver observer){
        this.observers.add(observer);
    }

    @Override
    public void deleteObserver(CoinObserver observer) {

    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < this.observers.size(); i++){
            observers.get(i).update(10);
        }
    }


}
