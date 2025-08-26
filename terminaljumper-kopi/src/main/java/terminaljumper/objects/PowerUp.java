package terminaljumper.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * <h1>PowerUp</h1>
 * Abstract class any power up should extend.
 */
public abstract class PowerUp {

    private Body body;
    private Sprite sprite;
    private Vector2 position;

    public PowerUp(Body body, Vector2 position) {
        this.body = body;
        this.position = position;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void drawSprite(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Body getBody() {
        return body;
    }
}
