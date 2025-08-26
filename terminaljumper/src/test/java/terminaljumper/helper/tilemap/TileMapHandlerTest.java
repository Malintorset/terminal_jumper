package terminaljumper.helper.tilemap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import terminaljumper.helper.Constants;
import terminaljumper.helper.PowerUpHandler;
import terminaljumper.objects.Bomb;
import terminaljumper.objects.Star;

/**
 * <h1>TileMapHandlerTest</h1>
 * The TileMapHandlerTest class tests the TileMapHandler implementation.
 */
public class TileMapHandlerTest {

    private DefaultTileMapHandler tileMapHandler;
    private Body bodyA;
    private Body bodyB;

    @BeforeEach
    public void setUp() {
        // Init a test world, with bodies: body A and B:
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

        tileMapHandler = new DefaultTileMapHandler(null);
    }

    @Test
    public void testGetPowerUpListFor() {
        Bomb bomb = new Bomb(bodyA, bodyA.getPosition());
        Star star = new Star(bodyB, bodyB.getPosition());
        List<Bomb> bombs = Collections.singletonList(bomb);
        List<Star> stars = Collections.singletonList(star);
        tileMapHandler.setBombs(bombs);
        tileMapHandler.setStars(stars);

        List<Bomb> bombList = PowerUpHandler.getPowerUpListFor(Constants.CollisionObject.BOMB, tileMapHandler);
        List<Star> starList = PowerUpHandler.getPowerUpListFor(Constants.CollisionObject.STAR, tileMapHandler);

        assertEquals(bombs, bombList);
        assertEquals(stars, starList);
    }

    @Test
    public void testGetPowerupFromBody() {
        Bomb bomb = new Bomb(bodyA, bodyA.getPosition());
        Star star = new Star(bodyB, bodyB.getPosition());
        List<Bomb> bombs = Collections.singletonList(bomb);
        List<Star> stars = Collections.singletonList(star);
        tileMapHandler.setBombs(bombs);
        tileMapHandler.setStars(stars);

        Bomb retrievedBomb = PowerUpHandler.getPowerupFromBody(bodyA, tileMapHandler);
        Star retrievedStar = PowerUpHandler.getPowerupFromBody(bodyB, tileMapHandler);

        assertNotNull(retrievedBomb);
        assertNotNull(retrievedStar);

        assertEquals(bomb, retrievedBomb);
        assertEquals(star, retrievedStar);
    }

    @Test
    public void testRemovePowerup() {
        Bomb bomb = new Bomb(bodyA, bodyA.getPosition());
        Star star = new Star(bodyB, bodyB.getPosition());
        List<Bomb> bombs = new ArrayList<>();
        List<Star> stars = new ArrayList<>();
        bombs.add(bomb);
        stars.add(star);
        tileMapHandler.setBombs(bombs);
        tileMapHandler.setStars(stars);

        PowerUpHandler.removePowerup(bomb, tileMapHandler);
        PowerUpHandler.removePowerup(star, tileMapHandler);

        assertTrue(tileMapHandler.getBombs().isEmpty());
        assertTrue(tileMapHandler.getStars().isEmpty());
    }
}
