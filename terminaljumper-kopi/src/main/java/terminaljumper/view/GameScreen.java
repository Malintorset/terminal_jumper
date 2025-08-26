package terminaljumper.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import terminaljumper.TerminalJumper;
import terminaljumper.controller.DefaultInputHandler;
import terminaljumper.helper.*;
import terminaljumper.helper.tilemap.DefaultTileMapHandler;
import terminaljumper.helper.tilemap.TileMapHandler;
import terminaljumper.midi.DefaultAudioManager;
import terminaljumper.objects.Mouse;
import terminaljumper.objects.Player;

import static terminaljumper.helper.Constants.PPM;

/**
 * <h1>GameScreen</h1>
 * The GameScreen class is responsible for presenting the game view to the user
 * while playing.
 */
public class GameScreen implements Screen {

    private static World world;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHandler tileMapHandler;
    private Camera camera;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;
    private Player player;
    private Mouse mouse;
    /** For displaying score */
    private TextureRegion[] numbers; // For displaying score
    private TextureRegion[] redNumbers; // For displaying bomb timer countdown
    private TextureRegion[] greenNumbers; // For displaying doublejump timer countdown
    final TerminalJumper game;
    private float score;
    private GameMode gameMode;
    private DefaultInputHandler defaultInputHandler;

    public GameScreen(TerminalJumper game, GameMode gameMode) {
        this.gameMode = gameMode;
        GameScreen.world = new World(new Vector2(0, -9.81f * 6), false);
        this.camera = new Camera(gameMode);
        this.batch = new SpriteBatch();
        this.debugRenderer = new Box2DDebugRenderer();
        this.tileMapHandler = new DefaultTileMapHandler(this);
        this.orthogonalTiledMapRenderer = tileMapHandler.setupMap(Map.MAP_START.filPath(), TileMapHandler.OBJECT_LAYER);
        this.numbers = RenderUtils.loadTextureSprite(Constants.Images.NUMBERS, 10);
        this.redNumbers = RenderUtils.loadTextureSprite(Constants.Images.NUMBERS_RED, 10);
        this.greenNumbers = RenderUtils.loadTextureSprite(Constants.Images.NUMBERS_GREEN, 10);
        this.score = 0;
        this.game = game;
        this.mouse = EnemyFactory.createMouse(new Vector2(1000, 200), player, camera, gameMode);
        this.defaultInputHandler = new DefaultInputHandler(player);
        CollisionHandler.setWorldContactListener(this, tileMapHandler);
        RenderUtils.initBombSprites(tileMapHandler.getBombs());
        RenderUtils.initStarSprites(tileMapHandler.getStars());
        RenderUtils.initDoubleJumpSprites(tileMapHandler.getDoubleJumps());
    }

    public void update() {
        world.step(1 / 60f, 6, 2);
        deathDetection();
        increaseScore();
        camera.moveUp();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
        mouse.update();
        Vector2 oldMousePos = mouse.getBody().getPosition();
        defaultInputHandler.checkInput();
        if (tileMapHandler.generateTileMapIfNeeded(orthogonalTiledMapRenderer, camera, player, mouse)) {
            RenderUtils.initBombSprites(tileMapHandler.getBombs());
            RenderUtils.initStarSprites(tileMapHandler.getStars());
            RenderUtils.initDoubleJumpSprites(tileMapHandler.getDoubleJumps());
            Vector2 newMousePos = new Vector2(oldMousePos.x * PPM,
                    oldMousePos.y * PPM - tileMapHandler.getMapLoopDistance(camera));
            this.mouse = EnemyFactory.createMouse(newMousePos, player, camera, gameMode); // Create a new mouse
            this.defaultInputHandler.setPlayer(player);
            this.defaultInputHandler = new DefaultInputHandler(player);
        }
        PowerUpHandler.updateIfNeeded(this, tileMapHandler);
    }

    private void deathDetection() {
        // Check if player oustide of camera
        if ((player.getBody().getPosition().y * PPM + 100) < (camera.position.y - camera.viewportHeight / 2)) {
            goToGameOverScreen();
            DefaultAudioManager.instance.playOnce(Constants.Audio.GAME_OVER, 0.5f);
        }
    }

    private void goToGameOverScreen() {
        DefaultAudioManager.instance.stopMusic();
        game.setScreen(new GameOverScreen(game, score, gameMode));
    }

    @Override
    public void dispose() {
        batch.dispose();
        DefaultAudioManager.instance.disposeMusic();
    }

    @Override
    public void render(float v) {
        this.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthogonalTiledMapRenderer.render();
        //debugRenderer.render(world, camera.combined.scl(PPM));
        batch.begin();
        player.render(batch);
        mouse.render(batch);

        // Draw score numbers
        RenderUtils.drawNumbers(batch, Math.round(score), camera.position_BOTTOMLEFT(), 60, 80, numbers);
        if (player.getIsAffectedByBomb()) // Draw bomb timer, if player is slowed
            RenderUtils.drawNumbers(batch, Math.round(PowerUpHandler.getBombTimer()),
                    camera.position_TOPLEFT_withOffset(40, -120), 60, 80,
                    redNumbers);
        if (player.getIsAffectedByDoubleJump()) { // Draw doublejump timer, if player has doublejump
            RenderUtils.drawNumbers(batch, Math.round(PowerUpHandler.getDoubleJumpTimer()),
                    camera.position_TOPLEFT_withOffset(40, -220), 60, 80,
                    greenNumbers);
            RenderUtils.drawImage(batch, Constants.Images.DOUBLE_JUMP_TEXT,
                    camera.position_TOPLEFT_withOffset(30, -290), 120, 70);
        }

        RenderUtils.drawBombs(batch, tileMapHandler.getBombs());
        RenderUtils.drawStars(batch, tileMapHandler.getStars());
        RenderUtils.drawDoubleJumps(batch, tileMapHandler.getDoubleJumps());
        batch.end();
    }

    @Override
    public void show() {
        // Add music to GameScreen
        DefaultAudioManager.instance.changeMusic(Constants.Audio.GAMESCREEN_BACKGROUND, 0.8f);
    }

    public static World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void increaseScore() {
        score += 1 / 60f;
    }

    public Player getPlayer() {
        return player;
    }

    public void increaseScore(float addition) {
        this.score += addition;
    }

    @Override
    public void resize(int width, int height) {
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

    public void onMouseHitPlayer() {
        DefaultAudioManager.instance.playOnce(Constants.Audio.GAME_OVER, 0.5f);
        goToGameOverScreen();
    }
}
