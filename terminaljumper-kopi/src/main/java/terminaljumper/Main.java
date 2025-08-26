package terminaljumper;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

    /**
     * The main method initializes and launches the game using the LWJGL3 backend.
     *
     * @param arg Command line arguments (not used in this case)
     */
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("TERMINALJUMPER");
        config.setWindowedMode(1280, 720);
        config.useVsync(true);
        config.setForegroundFPS(60);
        config.setResizable(false);
        new Lwjgl3Application(new TerminalJumper(), config);
    }
}
