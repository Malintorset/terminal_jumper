package terminaljumper.helper;

import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import terminaljumper.objects.Bomb;
import terminaljumper.objects.DoubleJump;
import terminaljumper.objects.PowerUp;
import terminaljumper.objects.Star;
import terminaljumper.view.GameScreen;

/**
 * <h1>PowerUpFactory</h1>
 * This class will function as the factory for power-ups and power-downs.
 */
public class PowerUpFactory {

    /**
     * Create a powerup object on the platform (the polygonObject), by calculating
     * the bottom-left position of the bomb (similar to how platform are created)
     */
    public static <T extends PowerUp> T createPowerUp(PolygonMapObject polygonObject, Class<T> clazz) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = GameScreen.getWorld().createBody(bodyDef);

        if (clazz == Bomb.class) {
            body.setUserData(Constants.CollisionObject.BOMB);
        } else if (clazz == Star.class) {
            body.setUserData(Constants.CollisionObject.STAR);
        } else if (clazz == DoubleJump.class) {
            body.setUserData(Constants.CollisionObject.DOUBLE_JUMP);
        } else {
            System.out.println("ERROR: Unhandled PowerUp Class");
        }

        float[] vertices = polygonObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 current = new Vector2(vertices[i * 2] / Constants.PPM, vertices[i * 2 + 1] / Constants.PPM);
            worldVertices[i] = current;
        }
        if (worldVertices[0].y < 24 || worldVertices[0].y > 200 - 24 - 5)
            // Should not spawn in the begining or the end (on the "identical"-screens location)
            return null;

        float xDiff = Math.max(worldVertices[1].x - worldVertices[0].x - 1.5f, 0);
        float bombPosX = worldVertices[0].x + (float) Math.random() * xDiff;
        worldVertices[0] = new Vector2(bombPosX, worldVertices[0].y + 1.5f);
        worldVertices[1] = new Vector2(bombPosX, worldVertices[1].y + 0f);
        worldVertices[2] = new Vector2(bombPosX + 1.5f, worldVertices[2].y + 0f);
        worldVertices[3] = new Vector2(bombPosX + 1.5f, worldVertices[3].y + 1.5f);

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        body.createFixture(shape, 1000);
        shape.dispose();
        if (clazz == Bomb.class)
            return (T) new Bomb(body, worldVertices[1]);
        else if (clazz == Star.class)
            return (T) new Star(body, worldVertices[1]);
        else if (clazz == DoubleJump.class)
            return (T) new DoubleJump(body, worldVertices[1]);
        else
            System.out.println("Error: Unhandled PowerUp class. Returning null");
        return null;
    }
}
