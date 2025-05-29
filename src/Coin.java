import Core.Sprite;

public interface Coin extends Recogible, CoinSubject {
	void recoger();

	void onCollisionEnter(Sprite sprite);

	void onCollisionExit(Sprite sprite);
}
