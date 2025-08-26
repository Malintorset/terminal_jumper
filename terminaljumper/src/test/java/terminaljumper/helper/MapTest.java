
package terminaljumper.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * <h1>MapTest</h1>
 * The MapTest class tests map generation, including loading tilesets and random maps.
 */
public class MapTest {

    @Test
    public void testRandomMap() {
        // Generate a random map 100 times and make sure each one is valid
        for (int i = 0; i < 100; i++) {
            String randomMap = Map.randomMap();
            assertNotNull(randomMap);
            assertTrue(randomMap.startsWith("tilemaps/tileMap_"));
            assertTrue(randomMap.endsWith(".tmx"));
        }
    }

    @Test
    public void testFilePath() {
        assertEquals("tilemaps/tileMap_START.tmx", Map.MAP_START.filPath());
        assertEquals("tilemaps/tileMap_1.tmx", Map.MAP1.filPath());
        assertEquals("tilemaps/tileMap_2.tmx", Map.MAP2.filPath());
        assertEquals("tilemaps/tileMap_3.tmx", Map.MAP3.filPath());
        assertEquals("tilemaps/tileMap_4.tmx", Map.MAP4.filPath());
        assertEquals("tilemaps/tileMap_5.tmx", Map.MAP5.filPath());
    }
}
