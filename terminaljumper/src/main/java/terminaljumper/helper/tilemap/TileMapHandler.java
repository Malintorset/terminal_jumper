package terminaljumper.helper.tilemap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import terminaljumper.helper.Camera;
import terminaljumper.objects.*;

import java.util.List;

/**
 * <h1>TiledMapHandler</h1>
 * This class sets up the sprites and collision boxes from the Tiled file.
 * <P>
 * 
 * Current setup (11.03.21):
 * <P>
 * - Object layer name is "Objektlag 1",
 * <p>
 * - The Player body should be named "player",
 * <p>
 * - The platform bodies should be named "platform",
 * <p>
 * - The wall bodies should be named "wall".
 * <p>
 * The names are used for collision detection.
 */
public interface TileMapHandler {

    /** The name of the layer where the objects are stored */
    public final static String OBJECT_LAYER = "Objektlag 1";

    /**
     * Initiates the OrthogonalTiledMapRenderer.
     * 
     * <P>
     * Should be called once in the initializer of the screen.
     * 
     * @param filepath        Path to the initial map to render
     * @param objectLayerName The name of the layer where the objects are stored
     */
    public OrthogonalTiledMapRenderer setupMap(String filepath, String objectLayerName);

    /**
     * Get the height of the TileMap
     * 
     * @return height in pixels
     */
    public Integer tiledMapHeight();

    /**
     * Chooses a random map from the maps in {@link terminaljumper.helper.Map}
     * 
     * @return a random map
     */
    public TiledMap randomMap();

    /**
     * Calculates if the camera has reached the top of the tile map. If so, we set
     * the map of orthogonalTiledMapRenderer to a random map, and move the player
     * and camera down to the start.
     * 
     * <p>
     * NB: The start and end of every map has to be identical.
     * 
     * @param orthogonalTiledMapRenderer
     * @param camera
     * @param player
     */
    public boolean generateTileMapIfNeeded(OrthogonalTiledMapRenderer orthogonalTiledMapRenderer,
            Camera camera, Player player, Mouse mouse);

    /**
     * Gets a list of the current bombs in the tilemap
     * 
     * @return list of bombs
     */
    public List<Bomb> getBombs();

    /**
     * Gets a list of the current Stars in the tilemap
     * 
     * @return list of stars
     */
    public List<Star> getStars();

    /**
     * Gets a list of the current DoubleJumps in the tilemap
     * 
     * @return list of DoubleJumps
     */
    public List<DoubleJump> getDoubleJumps();

    /**
     * Retuns how many pixel TileMap heigt minus the camera view height (how many
     * pixels object should move down to loop perfectly with map generation)
     */
    public float getMapLoopDistance(Camera camera);
}
