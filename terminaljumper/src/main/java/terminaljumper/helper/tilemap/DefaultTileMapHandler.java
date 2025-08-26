package terminaljumper.helper.tilemap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;
import terminaljumper.helper.*;
import terminaljumper.objects.*;
import terminaljumper.view.GameScreen;

import java.util.ArrayList;
import java.util.List;

import static terminaljumper.helper.Constants.PPM;

/**
 * <h1>DefaultTileMapHandler</h1>
 * This class implments TileMapHandler
 */
public class DefaultTileMapHandler implements TileMapHandler {

    private TiledMap tiledMap;
    private GameScreen gameScreen;

    /** Current spawned powerups */
    private List<Bomb> bombs;
    private List<Star> stars;
    private List<DoubleJump> doubleJumps;

    public DefaultTileMapHandler(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public OrthogonalTiledMapRenderer setupMap(String filepath, String objectLayerName) {
        bombs = new ArrayList<>();
        stars = new ArrayList<>();
        doubleJumps = new ArrayList<>();
        this.tiledMap = new TmxMapLoader().load(filepath);
        parseMapObject(tiledMap.getLayers().get(objectLayerName).getObjects());
        return new OrthogonalTiledMapRenderer(this.tiledMap);
    }

    @Override
    public Integer tiledMapHeight() {
        return tiledMap.getProperties().get("height", Integer.class)
                * tiledMap.getProperties().get("tileheight", Integer.class);
    }

    @Override
    public TiledMap randomMap() {
        this.tiledMap = new TmxMapLoader().load(Map.randomMap());
        return this.tiledMap;
    }

    @Override
    public boolean generateTileMapIfNeeded(OrthogonalTiledMapRenderer orthogonalTiledMapRenderer,
            Camera camera, Player player, Mouse mouse) {
        if (shouldGenerateMap(camera)) {
            generateMap(orthogonalTiledMapRenderer, camera, player, mouse);
            return true;
        }
        return false;
    }

    /**
     * Iterates through the MapObjects from the Tiled file, and creates a "Body" for
     * each, with a name specified in the Tiled file.
     */
    private void parseMapObject(MapObjects objects) {
        for (MapObject mapObject : objects) {
            if (mapObject instanceof PolygonMapObject) {
                createStaticBody((PolygonMapObject) mapObject, mapObject.getName());
            }

            if (mapObject instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String name = mapObject.getName();
                Body body = null;

                if (name.equals(Constants.CollisionObject.PLAYER)) {
                    body = BodyHelperService.createBody(
                            rectangle.getX() + rectangle.getWidth() / 2,
                            rectangle.getY() + rectangle.getHeight() / 2,
                            rectangle.getWidth(),
                            rectangle.getHeight(),
                            false,
                            GameScreen.getWorld());
                    body.setUserData(Constants.CollisionObject.PLAYER);
                    gameScreen.setPlayer(new Player(body));
                }

            }
        }
    }

    private void createStaticBody(PolygonMapObject polygonObject, String name) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        Body body = GameScreen.getWorld().createBody(bodyDef);
        body.setUserData(name);
        Shape shape = createPolygonShape(polygonObject);
        body.createFixture(shape, 1000);
        shape.dispose();
        if (name.equals(Constants.CollisionObject.PLATFORM)) {
            if (shouldSpawnBomb()) {
                Bomb bomb = PowerUpFactory.createPowerUp(polygonObject, Bomb.class);
                if (bomb != null)
                    bombs.add(bomb);
            } else if (shouldSpawnStar()) {
                Star star = PowerUpFactory.createPowerUp(polygonObject, Star.class);
                if (star != null)
                    stars.add(star);
            } else if (shouldSpawnDoubleJump()) {
                DoubleJump doubleJump = PowerUpFactory.createPowerUp(polygonObject, DoubleJump.class);
                if (doubleJump != null)
                    doubleJumps.add(doubleJump);
            }
        }
    }

    /** Calculates if a bomb should spawn given the bomb spawn probability */
    private boolean shouldSpawnBomb() {
        return PowerUpHandler.bombProbability > Math.random();
    }

    /** Calculates if a Star should spawn given the star spawn probability */
    private boolean shouldSpawnStar() {
        return PowerUpHandler.starProbability > Math.random();
    }

    /**
     * Calculates if a DoubleJump should spawn given the DoubleJump spawn
     * probability
     */
    private boolean shouldSpawnDoubleJump() {
        return PowerUpHandler.doubleJumpProbability > Math.random();
    }

    private Shape createPolygonShape(PolygonMapObject mapObject) {
        float[] vertices = mapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 current = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    /**
     * Uses the camera position and the tilemap to calculate if the camera has
     * reached the top or not. If it has, the game should call generateMap()
     */
    private boolean shouldGenerateMap(Camera camera) {
        float tileMapHeight = this.tiledMapHeight();
        float camTopPosition = camera.position_TOPLEFT().y;
        return tileMapHeight <= camTopPosition;
    }

    @Override
    public float getMapLoopDistance(Camera camera) {
        return this.tiledMapHeight() - camera.viewportHeight;
    }

    /**
     * This generates more map, by moving the player, mouse and the camera to the
     * bottom, and loads a new random tilemap. This method also destroys all bodies
     * in the game, so all bodies that should exist after this has to be created
     * again (for instance Player/Mouse)
     */
    private void generateMap(OrthogonalTiledMapRenderer orthogonalTiledMapRenderer, Camera camera,
            Player player, Mouse mouse) {
        System.out.println("Generating map..");
        float distance = getMapLoopDistance(camera);

        // Move camera down
        Vector3 positon = camera.position;
        positon.y -= distance;
        camera.position.set(positon);

        // Store the old player and mouse positions/velocities/PowerUps
        float playerXPos = player.getX();
        float playerYPos = player.getY();
        float playerYVel = player.getBody().getLinearVelocity().y;
        boolean affectedByBomb = player.getIsAffectedByBomb();
        boolean affectedByDoubleJump = player.getIsAffectedByDoubleJump();

        // Setup random map
        TiledMap randomMap = this.randomMap();

        // Destory all powerups, sprites and bodies
        bombs = new ArrayList<>();
        stars = new ArrayList<>();
        doubleJumps = new ArrayList<>();
        mouse = null;
        Array<Body> bodies = new Array<>();
        GameScreen.getWorld().getBodies(bodies);
        for (Body body : bodies) {
            GameScreen.getWorld().destroyBody(body);
        }

        // Init the new TileMap
        parseMapObject(tiledMap.getLayers().get(OBJECT_LAYER).getObjects());
        orthogonalTiledMapRenderer.setMap(randomMap);

        // Move Player down
        gameScreen.getPlayer().setPosition(playerXPos, playerYPos - distance);
        // Make the new Player have the same velocity
        gameScreen.getPlayer().getBody().setLinearVelocity(0, playerYVel);
        // Make player have the same PowerUp effects
        if (affectedByBomb)
            gameScreen.getPlayer().onHitBomb();
        if (affectedByDoubleJump)
            gameScreen.getPlayer().onHitDoubleJump();
    }

    @Override
    public List<Bomb> getBombs() {
        return bombs;
    }

    @Override
    public List<Star> getStars() {
        return stars;
    }

    @Override
    public List<DoubleJump> getDoubleJumps() {
        return doubleJumps;
    }

    /** ONLY FOR TESTING */
    public void setBombs(List<Bomb> bombs) {
        this.bombs = bombs;
    }

    /** ONLY FOR TESTING */
    public void setStars(List<Star> stars) {
        this.stars = stars;
    }
}
