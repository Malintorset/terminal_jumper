package terminaljumper.helper;

import com.badlogic.gdx.physics.box2d.*;

import static terminaljumper.helper.Constants.PPM;

/**
 * <h1>BodyHelperService</h1>
 * The BodyHelperService class creates a body for objects used in game "world".
 */
public class BodyHelperService {

    /**
     * Creates a Box2D body with the specified properties.
     *
     * @param x         the x-coordinate of the body's initial position
     * @param y         the y-coordinate of the body's initial position
     * @param width     the width of the body
     * @param height    the height of the body
     * @param isStatic  true if the body should be static, false if it should be dynamic
     * @param world     the Box2D world in which the body will be created
     * @return the created Box2D body
     */
    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PPM, y / PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
}
