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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import terminaljumper.TerminalJumper;
import terminaljumper.helper.Constants;
import terminaljumper.midi.DefaultAudioManager;

/**
 * <h1>SettingsScreen</h1>
 * The SettingsScreen class represents the settings menu screen for the TerminalJumper game.
 * It allows users to adjust audio settings, such as volume and sound effects, and navigate back to the previous screen.
 */
public class SettingsScreen implements Screen {

    private Skin skin;
    private Sprite background;
    private SpriteBatch batch;
    private Screen previousScreen;
    private TextureRegionDrawable myTexture;
    private ImageButton backButton;
    final TerminalJumper game;
    private Stage stage;

    /**
     * Creates a new SettingsScreen instance.
     *
     * @param game the TerminalJumper game instance
     * @param previousScreen the previous screen that should be displayed when the user exits the settings menu
     */
    public SettingsScreen(final TerminalJumper game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;

        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
    }

    /**
     * Called when this screen becomes the current screen for the game. Sets up UI elements for the settings screen.
     */
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
        table.setSize(Gdx.graphics.getWidth() * (80.0f / 100.0f), Gdx.graphics.getHeight() * (80.0f / 100.0f));
        //table.setDebug(true);
        stage.addActor(table);

        // Center table in window
        table.setPosition(Gdx.graphics.getWidth() / 2 - table.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - table.getHeight() / 2);
        // Start taking input from the ui
        Gdx.input.setInputProcessor(stage);
    
        // Loads the existing skin
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
    
        // Changes the label style
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default-font");
    
        // Changes the slider style
        Slider.SliderStyle sliderStyle = skin.get("default-horizontal", Slider.SliderStyle.class);
    
        // Changes button style
        TextButton.TextButtonStyle buttonStyle = skin.get("default", TextButton.TextButtonStyle.class);
    
        //Adds slider for volume
        Label volumeLabel = new Label("Music", labelStyle);
        Slider volumeSlider = new Slider(0, 100, 1, false, sliderStyle);
        volumeSlider.setValue(DefaultAudioManager.instance.getCurrentVolume() * 100);

        //Adds slider for sounds effects
        Label sfxLabel = new Label("Sound Effects", labelStyle);
        Slider sfxSlider = new Slider(0, 100, 1, false, sliderStyle);
        sfxSlider.setValue(DefaultAudioManager.instance.getCurrentSfxVolume() * 100);

        // Set up back button with textures
        myTexture = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal(Constants.Images.BACK))));
        backButton = new ImageButton(myTexture);

        table.add(volumeLabel).pad(10);
        table.add(volumeSlider).pad(10);
        table.row();
        table.add(sfxLabel).pad(10);
        table.add(sfxSlider).pad(10);
        table.row();
        table.add(backButton).colspan(2).pad(10);

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue() / 100f; // Converts the slider value (0 to 100) to a range of 0 to 1
                DefaultAudioManager.instance.setVolume(volume);
            }
        });

        sfxSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float sfxVolume = sfxSlider.getValue() / 100f;
                DefaultAudioManager.instance.setSoundEffectsVolume(sfxVolume);
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(previousScreen);
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the settings menu screen.
     *
     * @param delta time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        batch.begin();
        background.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        //stage.act(delta);
        stage.draw();
    }

    /**
     * Called when the screen is resized.
     *
     * @param width the new screen width
     * @param height the new screen height
     */
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

    /**
     * Disposes of all resources used by this screen, such as textures and stages.
     */
    @Override
    public void dispose() {
        stage.dispose();
        //spriteBatch.dispose();
        background.getTexture().dispose();
        skin.dispose(); 
    }
}
