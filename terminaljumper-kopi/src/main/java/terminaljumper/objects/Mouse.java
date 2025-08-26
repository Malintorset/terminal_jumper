package terminaljumper.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import terminaljumper.helper.Camera;
import terminaljumper.helper.Constants;
import terminaljumper.helper.GameMode;

import static terminaljumper.helper.Constants.PPM;

/**
 * <h1>Mouse</h1>
 * This class is a game enemy, that float on screen does not collide in
 * anything, and moves towards the Player. If the Player get hit, it is "Game
 * Over"
 */
public class Mouse extends GameEntity {

    private Player player;
    private Camera camera;
    private float mouseRotation = -120f;
    private GameMode gameMode = GameMode.CLASSIC;

    private float EXPERT_MOUSE_SPEED = 3;
    private float CLASSIC_MOUSE_SPEED = 1.7f;

    public Mouse(Body body) {
        super(body);
        speed = CLASSIC_MOUSE_SPEED;
        loadSprite(Constants.Images.MOUSE, 0.6f);
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        velY = 0;
        velX = 0;

        // Move the Mouse towards the Player and rotate the body according to the
        // direction
        Vector2 dir = playerDirection();
        velX += dir.x;
        velY += dir.y;

        body.setTransform(body.getPosition().x, body.getPosition().y + camera.getCameraSpeed() / PPM, 0);
        body.setLinearVelocity(velX * speed, velY * speed);

        // Teleport the mouse back up if too far away (if camera speed is too fast)
        if (body.getPosition().y < player.getBody().getPosition().y - 24)
            body.setTransform(body.getPosition().x, body.getPosition().y + 50, 0);
    }

    @Override
    public void render(SpriteBatch batch) {
        // Calculate sprite position, and rotate the sprite so that the Mouse point
        // towards the Player
        float offsetX = -55;
        float offsetY = -55;
        sprite.setPosition(body.getPosition().x * PPM + offsetX,
                body.getPosition().y * PPM + offsetY);
        sprite.setRotation(playerDirection().angleDeg() + mouseRotation);
        sprite.draw(batch);
    }

    /** The Mouse need to know where the player is in order to follow him */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * The Mouse need to know the camera speed to move in sync with this speed
     * (float on the screen)
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * The Mouse need to know what game mode is playing (to know how fast the Mouse
     * should move)
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        speed = gameMode == GameMode.CLASSIC ? CLASSIC_MOUSE_SPEED : EXPERT_MOUSE_SPEED;
    }

    /** Calculates the direction from the Mouse to the Player */
    private Vector2 playerDirection() {
        if (player == null)
            return new Vector2();
        Vector2 playerPos = player.getBody().getPosition();
        Vector2 dir = new Vector2(playerPos.x * PPM - x, playerPos.y * PPM - y);
        dir.nor();
        return dir;
    }
}
