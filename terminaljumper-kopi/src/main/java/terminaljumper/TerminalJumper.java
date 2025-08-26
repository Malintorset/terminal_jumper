package terminaljumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import terminaljumper.view.MainMenuScreen;

/**
 * <h1>TerminalJumper</h1>
 * The TerminalJumper class, extending the libGDX Game class, is the main entry point for the game.
 * It handles the creation, rendering, and disposing of game assets and resources, and sets the initial screen.
 */
public class TerminalJumper extends Game {

    /**
     * A SpriteBatch used to draw 2D images in the game.
     */
    public SpriteBatch batch;

    /**
     * A BitmapFont used for rendering text on the screen.
     */
    public BitmapFont font;

    /**
     * This method is called when the game is created.
     * It initializes the SpriteBatch, the font, and sets the initial screen to MainMenuScreen.
     */
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new MainMenuScreen(this));
    }

    /**
     * This method is called every frame to render the game.
     * It calls the parent class's render method.
     */
    public void render() {
        super.render(); // important!
    }

    /**
     * This method is called when the game is being disposed.
     * It disposes of the resources such as the SpriteBatch and font.
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
