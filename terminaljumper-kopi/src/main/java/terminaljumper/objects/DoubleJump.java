package terminaljumper.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * <h1>DoubleJump</h1>
 * Power up - makes player able to double jump
 */
public class DoubleJump extends PowerUp {

    public DoubleJump(Body body, Vector2 position) {
        super(body, position);
    }
}
