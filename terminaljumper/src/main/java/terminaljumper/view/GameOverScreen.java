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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import terminaljumper.TerminalJumper;
import terminaljumper.helper.Constants;
import terminaljumper.helper.GameMode;

/**
 * <h1>GameOverScreen</h1>
 * The GameOverScreen class represents a "Game Over" screen for the TerminalJumper game, when the player dies.
 * It shows a final score and buttons to play again or exit the game.
 */
public class GameOverScreen implements Screen {

    final TerminalJumper game;
    private GameMode previousGameMode;
    private SpriteBatch batch;
    private Stage stage;
    private Image logo;
    private Sprite background;
    private TextureRegionDrawable myTexture;
    private ImageButton startButton;
    private ImageButton quitButton;
    private ImageButton backButton;
    private ImageButton settingsButton;
    private float score;
    private static final Float WINDOW_SIZE = 0.8f;
    private static final Float LOGO_SIZE = 0.6f;

    public GameOverScreen(final TerminalJumper game, Float score, GameMode gameMode) {
        this.game = game;
        this.score = score;
        this.previousGameMode = gameMode;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        // Background sprite for main menu
        background = new Sprite(new Texture(Gdx.files.internal("ui/desktop.png")));
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Create a table for menu UI
        Table table = new Table();
        table.setBackground(
                new TextureRegionDrawable(new TextureRegion(new Texture("ui/window.png"))));
        // Size is 80% of the window
        table.setSize(Gdx.graphics.getWidth() * WINDOW_SIZE, Gdx.graphics.getHeight() * WINDOW_SIZE);
        //table.setDebug(true);
        stage.addActor(table);

        // Add logo
        logo = new Image(new Texture(Gdx.files.internal("ui/gameover.png")));
        logo.setAlign(Align.center);
        // Size is 60% of the table
        table.add(logo).width(table.getWidth() * LOGO_SIZE).height(table.getHeight() * LOGO_SIZE);
        table.row();

        // Show final score
        Skin skin = new Skin(Gdx.files.internal("ui/terra-mother/skin/terra-mother-ui.json"));
        Label label = new Label("Final Score:  " + Math.round(score), skin);
        label.setScale(1.5f);
        label.setFontScale(1.5f);
        label.setAlignment(Align.center);
        table.add(label).expandX().fillX();
        table.row();

        Table buttonTable = new Table();
        // Set up buttons with textures
        myTexture = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.RETRY))));
        startButton = new ImageButton(myTexture);

        myTexture = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.QUIT))));
        quitButton = new ImageButton(myTexture);

        //Add Settingsbutton
        myTexture = new TextureRegionDrawable(
            new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.SETTINGS)))); 
        settingsButton = new ImageButton(myTexture);

        //Add backButton
        myTexture = new TextureRegionDrawable(
            new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.BACK))));
        backButton = new ImageButton(myTexture);

        // Add to buttonTable, add to root table
        buttonTable.add(backButton);
        buttonTable.add(startButton);
        buttonTable.add(settingsButton);
        table.add(buttonTable);

        // Button listener to start game
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game , previousGameMode));
                //Gdx.input.setInputProcessor(null); // Remove this if GameScreen starts using its own InputProcessor
            }
        });

        //Button listener to settings button. 
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new SettingsScreen(game, GameOverScreen.this));
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        // Button listener to quit app
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Gdx.app.exit();
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
