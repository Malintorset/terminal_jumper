package terminaljumper.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import terminaljumper.TerminalJumper;
import terminaljumper.helper.Constants;
import terminaljumper.midi.DefaultAudioManager;

/**
 * <h1>HowToPlayScreen</h1>
 * The HowToPlayScreen class shows the player an overview of the gameplay,
 * controls and mechanics.
 */
public class HowToPlayScreen implements Screen {

    final TerminalJumper game;
    private SpriteBatch batch;
    private Stage stage;
    private Sprite background;
    private Image explanation;
    private TextureRegionDrawable myTexture;
    private ImageButton backButton;
    private Screen previousScreen;
    private static final Float WINDOW_SIZE = 0.8f;
    private static final Float EXPLANATION_SIZE = 0.6f;

    /**
     * Constructs the how to play screen for the TerminalJumper game.
     *
     * @param game the TerminalJumper instance for this game.
     * @param previousScreen the previous screen that should be displayed when the user clicks the back button.
     */
    public HowToPlayScreen(final TerminalJumper game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
    }
    
    @Override
    public void show() {
        // Background sprite for how to play
        background = new Sprite(new Texture(Gdx.files.internal(Constants.Images.DESKTOP)));
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Create a table for menu UI
        Table table = new Table();
        table.setBackground(
                new TextureRegionDrawable(new TextureRegion(new Texture(Constants.Images.WINDOW))));
        // Size is 80% of the window
        table.setSize(Gdx.graphics.getWidth() * WINDOW_SIZE, Gdx.graphics.getHeight() * WINDOW_SIZE);
        //table.setDebug(true);
        stage.addActor(table);

        // Add explanation
        explanation = new Image(new Texture(Gdx.files.internal(Constants.Images.EXPLANATION)));
        // Size is 60% of the table
        table.add(explanation).width(table.getWidth() * EXPLANATION_SIZE).height(table.getHeight() * EXPLANATION_SIZE);
        table.row();

        // Add music
        DefaultAudioManager.instance.changeMusic(Constants.Audio.SPACE_JAZZ, 0.5f);

        // Set up back button with textures
        Table buttonTable = new Table();
        myTexture = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.BACK))));
        backButton = new ImageButton(myTexture);

        // Add to buttonTable, add to root table
        buttonTable.add(backButton);
        table.add(buttonTable);

        // Button listener to go back to main menu
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        // Center table in window
        table.setPosition(Gdx.graphics.getWidth() / 2 - table.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - table.getHeight() / 2);
        // Start taking input from the ui
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        batch.begin();
        background.draw(batch);
        batch.end();

        // Draw logo, window, buttons
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
