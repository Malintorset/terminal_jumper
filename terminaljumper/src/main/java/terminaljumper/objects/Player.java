package terminaljumper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import terminaljumper.helper.Constants;

import java.util.HashMap;

import static terminaljumper.helper.Constants.PPM;

/**
 * <h1>Player</h1>
 * The Player class represents a player object, controlled by the user and which
 * through the user interacts with the game.
 */
public class Player extends GameEntity {

    private boolean isGrounded = true;
    private boolean doubleJumpEnabled = false; // This is not the DoubleJump powerup
    private boolean playerFacing = true; // Last direction player faced. true = left, false = right
    private final HashMap<String, Sprite> sprites = new HashMap<>();
    private Color currentColor = Color.WHITE;

    /** Tells if the player is currently affected by powerup/down */
    private boolean isAffectedByBomb = false;
    private boolean isAffectedByDoubleJump = false;

    private static final String STAND_LEFT = "stand_left";
    private static final String STAND_RIGHT = "stand_right";
    private static final String RUN_LEFT = "run_left";
    private static final String RUN_RIGHT = "run_right";
    private static final String JUMP_LEFT = "jump_left";
    private static final String JUMP_RIGHT = "jump_right";

    public Player(Body body) {
        super(body);
        speed = 15f;
        addSprites();
        loadSprite(STAND_LEFT);
    }

    /**
    * This method puts an "action" and the corresponding sprite in a hashmap.
    */
    private void addSprites() {
        sprites.put(STAND_LEFT, new Sprite(new Texture(Gdx.files.internal(Constants.Images.STANDING_LEFT))));
        sprites.put(STAND_RIGHT, new Sprite(new Texture(Gdx.files.internal(Constants.Images.STANDING_RIGHT))));
        sprites.put(RUN_LEFT, new Sprite(new Texture(Gdx.files.internal(Constants.Images.RUN_LEFT))));
        sprites.put(RUN_RIGHT, new Sprite(new Texture(Gdx.files.internal(Constants.Images.RUN_RIGHT))));
        sprites.put(JUMP_LEFT, new Sprite(new Texture(Gdx.files.internal(Constants.Images.JUMP_LEFT))));
        sprites.put(JUMP_RIGHT, new Sprite(new Texture(Gdx.files.internal(Constants.Images.JUMP_RIGHT))));
    }

    /**
     * This method loads the sprite for the player object from its hash map, scales
     * it to
     * 0.75f and sets it to the "sprite" attribute of the player.
     */
    private void loadSprite(String spriteName) {
        sprite = sprites.get(spriteName);
        sprite.setScale(0.75f);
    }

    /**
     * The pickSprite method changes the Player's sprite depending on
     * how it's moving and which direction it's facing, by calling loadSprite.
     */
    private void pickSprite() {
        if (playerFacing) {
            if (!isGrounded) {
                loadSprite(JUMP_LEFT);
            } else if (velX < 0) {
                loadSprite(RUN_LEFT);
            } else {
                loadSprite(STAND_LEFT);
            }
        } else {
            if (!isGrounded) {
                loadSprite(JUMP_RIGHT);
            } else if (velX > 0) {
                loadSprite(RUN_RIGHT);
            } else {
                loadSprite(STAND_RIGHT);
            }
        }
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
    }

    @Override
    public void render(SpriteBatch batch) {
        pickSprite();
        currentColor = isAffectedByBomb ? Color.RED : Color.WHITE;
        sprite.setPosition(body.getPosition().x * PPM - sprite.getWidth() / 2,
                (body.getPosition().y - 1) * PPM);
        sprite.setColor(currentColor);
        sprite.draw(batch);
    }

    /**
     * Logic for when the player just hit a bomb. The bomb will be destroyed in the GameScreen.
     */
    public void onHitBomb() {
        isAffectedByBomb = true;
    }

    /** Logic for when the Bomb powerdown timer is finished. */
    public void onBombRemoved() {
        isAffectedByBomb = false;
    }

    /** Logic for when the player just hit a DoubleJump powerup, allows Player to Double Jump. */
    public void onHitDoubleJump() {
        isAffectedByDoubleJump = true;
    }

    /** Logic for when the DoubleJump powerdown timer is finished, no longer allow Player to Double Jump. */
    public void onDoubleJumpRemoved() {
        isAffectedByDoubleJump = false;
    }

    /** Tells whether the player is currently slowed by bomb powerdown. */
    public boolean getIsAffectedByBomb() {
        return isAffectedByBomb;
    }

    /** Tells whether the player is currently slowed by bomb powerdown. */
    public boolean getIsAffectedByDoubleJump() {
        return isAffectedByDoubleJump;
    }

    /** Tells whether the player is currently on the ground. */
    public boolean getIsGrounded() {
        return isGrounded;
    }

    /** Sets whether the player is currently on the ground. */
    public void setIsGrounded(boolean grounded) {
        isGrounded = grounded;
    }

    /** Tells whether the player has (internal) double jump enabled, i.e. hasn't already jumped while in-air. */
    public boolean getDoubleJumpEnabled() {
        return doubleJumpEnabled;
    }

    /** Sets if the player has (internal) double jump enabled, i.e. hasn't already jumped while in-air. */
    public void setDoubleJumpEnabled(boolean jump) {
        doubleJumpEnabled = jump;
    }

    /** Tells which direction the player is facing. true = left, false = right */
    public boolean getPlayerFacing() {
        return playerFacing;
    }

    /** Sets which direction the player is facing. true = left, false = right */
    public void setPlayerFacing(boolean facing) {
        playerFacing = facing;
    }

    /** Tells the current velocity along the X-axis for the player. */
    public float getVelX() {
        return velX;
    }

    /** Sets the current velocity along the X-axis for the player. */
    public void setVelX(float newVel) {
        velX = newVel;
    }

    /** Tells the current speed for the player. */
    public float getSpeed() {
        return speed;
    }

    /** Sets the current speed for the player. */
    public void setSpeed(Float newSpeed) {
        speed = newSpeed;
    }
}
