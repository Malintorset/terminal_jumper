package terminaljumper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static terminaljumper.helper.Constants.PPM;

/**
 * <h1>GameEntity</h1>
 * Abtract class for storing general variables and function all game entities
 * have (players, enemies).
 */
public abstract class GameEntity {
    protected float x, y, velX, velY, speed;
    protected Body body;
    protected Sprite sprite;

    public GameEntity(Body body) {
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.body = body;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
    }

    /**
     * This method loads the sprite for the GameEntity object from a file, scales it
     * and sets it to the "sprite" attribute of the player.
     * The file used is located at "src/main/resources/sprites/standing.png".
     */
    protected void loadSprite(String image, float scale) {
        sprite = new Sprite(new Texture(Gdx.files.internal(image)));
        sprite.setScale(scale);
    }

    /** Same as loadSprite() but with a color that overrides the sprite colors */
    protected void loadSpriteWithColor(String image, Color color, float scale) {
        sprite = new Sprite(new Texture(Gdx.files.internal(image)));
        sprite.setScale(scale);
        if (color != null)
            sprite.setColor(color);
    }

    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public Body getBody() {
        return body;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        Vector2 position = body.getPosition();
        position.y = y / PPM;
        this.body.setTransform(position, 0f);
    }

    public void setX(float y) {
        Vector2 position = body.getPosition();
        position.x = x / PPM;
        this.body.setTransform(position, 0f);
    }

    public void setPosition(float x, float y) {
        this.body.setTransform(new Vector2(x / PPM, y / PPM), 0f);
    }
}
