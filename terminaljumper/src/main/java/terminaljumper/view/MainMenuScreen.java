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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import terminaljumper.TerminalJumper;
import terminaljumper.helper.Constants;
import terminaljumper.midi.DefaultAudioManager;

/**
 * <h1>MainMenuScreen</h1>
 * The MainMenuScreen class is responsible for showing the user an initial UI to start the game, access settings etc.
 */
public class MainMenuScreen implements Screen {

    final TerminalJumper game;
    private SpriteBatch batch;
    private Stage stage;
    private Image logo;
    private Sprite background;
    private TextureRegionDrawable myTexture;
    private ImageButton startButton;
    private ImageButton quitButton;
    private ImageButton howtoplayButton;
    private ImageButton settingsButton;
    private static final Float WINDOW_SIZE = 0.8f;
    private static final Float LOGO_SIZE = 0.6f;

    /**
     * Constructs the main menu screen for the TerminalJumper game.
     *
     * @param game the TerminalJumper instance for this game.
     */
    public MainMenuScreen(final TerminalJumper game) {
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        // Background sprite for main menu
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

        // Add music to Main Menu
        DefaultAudioManager.instance.changeMusic(Constants.Audio.SPACE_JAZZ, 1f);
        
        // Add logo
        logo = new Image(new Texture(Gdx.files.internal(Constants.Images.TERMINALJUMPER)));
        logo.setAlign(Align.center);
        // Size is 60% of the table
        table.add(logo).width(table.getWidth() * LOGO_SIZE).height(table.getHeight() * LOGO_SIZE);
        table.row();

        // Set up buttons with textures
        Table buttonTable = new Table();
        Table settingTable = new Table();

        myTexture = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.START))));
        startButton = new ImageButton(myTexture);

        myTexture = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.QUIT))));
        quitButton = new ImageButton(myTexture);

        //Add how to play button
        myTexture = new TextureRegionDrawable(
            new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.HOWTOPLAY))));
        howtoplayButton = new ImageButton(myTexture);

        // Add settings button
        myTexture = new TextureRegionDrawable(
            new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.SETTINGS))));
        settingsButton = new ImageButton(myTexture);

        // Add to buttonTable, add to root table
        buttonTable.add(quitButton);
        buttonTable.add(startButton);
        buttonTable.add(howtoplayButton);
        settingTable.add(settingsButton).right().bottom();
        
        table.add(buttonTable);
        table.add(settingTable);

        // Button listener to start game
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                //DefaultAudioManager.instance.stopMusic();
                game.setScreen(new ModeScreen(game, MainMenuScreen.this));
                //Gdx.input.setInputProcessor(null); // Remove this if GameScreen starts using its own InputProcessor
            }
        });

        //Button listener to settings button. 
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new SettingsScreen(game, MainMenuScreen.this));
            }
        });

        // Button listener to quit app
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Gdx.app.exit();
            }
        });

        //Button listener to how to play
        howtoplayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new HowToPlayScreen(game, MainMenuScreen.this));
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
