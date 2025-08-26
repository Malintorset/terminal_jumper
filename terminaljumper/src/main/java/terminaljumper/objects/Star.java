package terminaljumper.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * <h1>Star</h1>
 * Additional score points - increases the score
 */
public class Star extends PowerUp {

    public Star(Body body, Vector2 position) {
        super(body, position);
    }
}
