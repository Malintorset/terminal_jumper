package terminaljumper.helper;

import java.util.Random;

/**
 * <h1>Map</h1>
 * Loads and generates maps for the game. Can also generate random maps.
 */
public enum Map {
    MAP_START,
    MAP1,
    MAP2,
    MAP3,
    MAP4,
    MAP5;

    private static final Random random = new Random();

    Map() {
    }

    /**
     * Random map between all maps in this enum, except the first one (MAP_START)
     */
    public static String randomMap() {
        Map[] maps = values();
        Map randomMap = maps[random.nextInt(maps.length - 1) + 1];
        return randomMap.filPath();
    }

    /**
    * This method updates the path to a given tileMap
    * returns a String with the whole path + .tmx
    * */
    public String filPath() {
        String path = "tilemaps/";
        switch (this) {
            case MAP_START:
                path += "tileMap_START";
                break;
            case MAP1:
                path += "tileMap_1";
                break;
            case MAP2:
                path += "tileMap_2";
                break;
            case MAP3:
                path += "tileMap_3";
                break;
            case MAP4:
                path += "tileMap_4";
                break;
            case MAP5:
                path += "tileMap_5";
                break;
            default:
                break;
        }
        return path + ".tmx";
    }
}
