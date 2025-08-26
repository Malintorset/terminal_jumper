package terminaljumper.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * <h1>Bomb</h1>
 * Power down - slows down player
 */
public class Bomb extends PowerUp {

    public Bomb(Body body, Vector2 position) {
        super(body, position);
    }
}
