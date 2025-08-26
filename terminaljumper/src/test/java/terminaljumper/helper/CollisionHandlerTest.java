
package terminaljumper.helper;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import org.junit.jupiter.api.BeforeEach;

/**
 * <h1>CollisionHandlerTest</h1>
 * The ColissionHandlerTest class tests the CollisionHandler and collision within the game.
 */
class CollisionHandlerTest {

    private Contact contact;
    private Body bodyA;
    private Body bodyB;

    @BeforeEach
    public void setUp() {
        // Init a test world, where body A and B have contact. Set in test functions
        // names for these bodies (like PLAYER, BOMB, ...) and test contact functions
        World world = new World(new Vector2(0, -9.81f), true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyA = world.createBody(bodyDef);
        CircleShape shape1 = new CircleShape();
        shape1.setRadius(1);
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape1;
        fixtureDef1.density = 1;
        fixtureDef1.friction = 0.3f;
        bodyA.createFixture(fixtureDef1);

        bodyB = world.createBody(bodyDef);
        CircleShape shape2 = new CircleShape();
        shape2.setRadius(1);
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape2;
        fixtureDef2.density = 1;
        fixtureDef2.friction = 0.3f;
        bodyB.createFixture(fixtureDef2);

        world.step(1 / 60f, 6, 2);

        this.contact = world.getContactList().first();
    }

    @Test
    public void testPlayerCollidedWithPlatform() {
        bodyA.setUserData(Constants.CollisionObject.PLAYER);
        bodyB.setUserData(Constants.CollisionObject.PLATFORM);

        assertTrue(CollisionHandler.playerCollidedWithPlatform(contact));
        assertFalse(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAStarWithPlayer(contact));

        // Swap user data and test again
        bodyA.setUserData(Constants.CollisionObject.PLATFORM);
        bodyB.setUserData(Constants.CollisionObject.PLAYER);

        assertTrue(CollisionHandler.playerCollidedWithPlatform(contact));
        assertFalse(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAStarWithPlayer(contact));
    }

    @Test
    public void testContactWasAWall() {
        bodyA.setUserData(Constants.CollisionObject.PLAYER);
        bodyB.setUserData(Constants.CollisionObject.WALL);

        assertTrue(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAStarWithPlayer(contact));
        assertFalse(CollisionHandler.playerCollidedWithPlatform(contact));

        // Swap user data and test again
        bodyA.setUserData(Constants.CollisionObject.WALL);
        bodyB.setUserData(Constants.CollisionObject.PLAYER);

        assertTrue(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAStarWithPlayer(contact));
        assertFalse(CollisionHandler.playerCollidedWithPlatform(contact));
    }

    @Test
    public void testContactWasABomb() {
        bodyA.setUserData(Constants.CollisionObject.PLAYER);
        bodyB.setUserData(Constants.CollisionObject.BOMB);

        assertTrue(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAStarWithPlayer(contact));
        assertFalse(CollisionHandler.playerCollidedWithPlatform(contact));

        // Swap user data and test again
        bodyA.setUserData(Constants.CollisionObject.BOMB);
        bodyB.setUserData(Constants.CollisionObject.PLAYER);

        assertTrue(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAStarWithPlayer(contact));
        assertFalse(CollisionHandler.playerCollidedWithPlatform(contact));
    }

    @Test
    public void testContactWasAStar() {
        bodyA.setUserData(Constants.CollisionObject.PLAYER);
        bodyB.setUserData(Constants.CollisionObject.STAR);

        assertTrue(CollisionHandler.contactWasAStarWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.playerCollidedWithPlatform(contact));

        // Swap user data and test again
        bodyA.setUserData(Constants.CollisionObject.STAR);
        bodyB.setUserData(Constants.CollisionObject.PLAYER);

        assertTrue(CollisionHandler.contactWasAStarWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasAWallWithPlayer(contact));
        assertFalse(CollisionHandler.contactWasABombWithPlayer(contact));
        assertFalse(CollisionHandler.playerCollidedWithPlatform(contact));
    }

    @Test
    public void testGetBombBody() {
        bodyA.setUserData(Constants.CollisionObject.PLAYER);
        bodyB.setUserData(Constants.CollisionObject.BOMB);

        assertNotNull(CollisionHandler.getBombBody(contact));
        assertNull(CollisionHandler.getStarBody(contact));

        // Swap the user data
        bodyA.setUserData(Constants.CollisionObject.BOMB);
        bodyB.setUserData(Constants.CollisionObject.PLAYER);

        assertNotNull(CollisionHandler.getBombBody(contact));
        assertNull(CollisionHandler.getStarBody(contact));
    }

    @Test
    public void testGetStarBody() {
        bodyA.setUserData(Constants.CollisionObject.PLAYER);
        bodyB.setUserData(Constants.CollisionObject.STAR);

        assertNotNull(CollisionHandler.getStarBody(contact));
        assertNull(CollisionHandler.getBombBody(contact));

        // Swap the user data
        bodyA.setUserData(Constants.CollisionObject.STAR);
        bodyB.setUserData(Constants.CollisionObject.PLAYER);

        assertNotNull(CollisionHandler.getStarBody(contact));
        assertNull(CollisionHandler.getBombBody(contact));
    }
}
