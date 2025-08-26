package terminaljumper.helper;

import com.badlogic.gdx.physics.box2d.Body;
import terminaljumper.helper.tilemap.TileMapHandler;
import terminaljumper.midi.DefaultAudioManager;
import terminaljumper.objects.Bomb;
import terminaljumper.objects.DoubleJump;
import terminaljumper.objects.PowerUp;
import terminaljumper.objects.Star;
import terminaljumper.view.GameScreen;

import java.util.List;

/**
 * <h1>PowerUpHandler</h1>
 * Helper class for handling powerup logic
 */
public class PowerUpHandler {

    /**
     * The bomb the player just hit. Null if, player did not hit a bomb. If a player
     * hits a bomb it should be put here, so that it can be destroyed in the
     * update() function.
     */
    private static Bomb hitBomb;

    /** The star the player just hit */
    private static Star hitStar;

    /** The star the player just hit */
    private static DoubleJump hitDoubleJump;

    /** Probaility of a powerups spawning on a platform */
    public final static float bombProbability = 0.2f;
    public final static float starProbability = 0.3f;
    public final static float doubleJumpProbability = 0.3f;

    /**
     * Timers for keeping track of how long player has been affected by powerup. If
     * timer reaches zero, the powerup is removed
     */
    static private float bombTimer = 0f;
    static private float doubleJumpTimer = 0f;

    /** How long a bomb will slow the player */
    static final private float TIME_SLOWED_BY_BOMB = 5f;

    /** How long a double jump will affect the player */
    static final private float TIME_AFFECTED_BY_DOUBLEJUMP = 10f;

    /** How much the player is slowed if affected by bomb */
    public static final float BOMB_SLOWING_PRECENTAGE = 0.5f;

    public static void didHitBomb(Bomb hitBomb) {
        PowerUpHandler.hitBomb = hitBomb;
        bombTimer = TIME_SLOWED_BY_BOMB;
    }

    public static void didHitStar(Star hitStar) {
        PowerUpHandler.hitStar = hitStar;
    }

    public static void didHitDoubleJump(DoubleJump hitDoubleJump) {
        PowerUpHandler.hitDoubleJump = hitDoubleJump;
        doubleJumpTimer = TIME_AFFECTED_BY_DOUBLEJUMP;
    }

    /** Tells the time until DoubleJump powerup is removed */
    public static float getDoubleJumpTimer() {
        return doubleJumpTimer;
    }

    /** Tells the time until Bomb powerdown is removed */
    public static float getBombTimer() {
        return bombTimer;
    }

    /** Checks if the player just hit a powerup, and updates the game accordingly */
    public static void updateIfNeeded(GameScreen gameScreen, TileMapHandler tileMapHandler) {
        if (didHitBomb()) {
            GameScreen.getWorld().destroyBody(hitBomb.getBody());
            removePowerup(hitBomb, tileMapHandler);
            gameScreen.getPlayer().onHitBomb();
            DefaultAudioManager.instance.playOnce(Constants.Audio.POWER_DOWN, 0.4f);
            hitBomb = null;
        }
        if (didHitStar()) {
            GameScreen.getWorld().destroyBody(hitStar.getBody());
            removePowerup(hitStar, tileMapHandler);
            gameScreen.increaseScore(10);
            DefaultAudioManager.instance.playOnce(Constants.Audio.POWER_UP, 0.43f);
            hitStar = null;
        }
        if (didHitDoubleJump()) {
            GameScreen.getWorld().destroyBody(hitDoubleJump.getBody());
            removePowerup(hitDoubleJump, tileMapHandler);
            gameScreen.getPlayer().onHitDoubleJump();
            DefaultAudioManager.instance.playOnce(Constants.Audio.DOUBLE_JUMP_UPGRADE, 0.37f);
            hitDoubleJump = null;
        }

        // Update timers
        if (bombTimer > 0f) {
            bombTimer = bombTimer - (1.0f / 60);
        } else if (gameScreen.getPlayer().getIsAffectedByBomb()) {
            gameScreen.getPlayer().onBombRemoved();
        }
        if (doubleJumpTimer > 0f) {
            doubleJumpTimer = doubleJumpTimer - (1.0f / 60);
        } else if (gameScreen.getPlayer().getIsAffectedByDoubleJump()) {
            gameScreen.getPlayer().onDoubleJumpRemoved();
        }
    }

    public static <T extends PowerUp> List<T> getPowerUpListFor(String name, TileMapHandler tileMapHandler) {
        if (name.equals(Constants.CollisionObject.BOMB)) {
            return (List<T>) tileMapHandler.getBombs();
        }
        if (name.equals(Constants.CollisionObject.STAR)) {
            return (List<T>) tileMapHandler.getStars();
        }
        if (name.equals(Constants.CollisionObject.DOUBLE_JUMP)) {
            return (List<T>) tileMapHandler.getDoubleJumps();
        }
        System.out.println("ERROR: PowerUp class not handled (getPowerUpListFor).");
        return null;
    }

    /**
     * Function for removing a powerup (tilemaphandler keeps track of current
     * spawned
     * bombs in a field variable)
     */
    public static <T extends PowerUp> void removePowerup(T powerup, TileMapHandler tileMapHandler) {
        if (powerup instanceof Bomb) {
            tileMapHandler.getBombs().remove(powerup);
        } else if (powerup instanceof Star) {
            tileMapHandler.getStars().remove(powerup);
        } else if (powerup instanceof DoubleJump) {
            tileMapHandler.getDoubleJumps().remove(powerup);
        } else {
            System.out.println("ERROR: PowerUp class not handled (removePowerup).");
        }
    }

    /** Returns the powerup this body belongs to */
    public static <T extends PowerUp> T getPowerupFromBody(Body body, TileMapHandler tileMapHandler) {
        for (Star star : tileMapHandler.getStars()) {
            if (star.getBody() == body) {
                return (T) star;
            }
        }
        for (Bomb bomb : tileMapHandler.getBombs()) {
            if (bomb.getBody() == body) {
                return (T) bomb;
            }
        }
        for (DoubleJump doubleJump : tileMapHandler.getDoubleJumps()) {
            if (doubleJump.getBody() == body) {
                return (T) doubleJump;
            }
        }
        System.out.println("ERROR: PowerUp class not handled (getPowerupFromBody).");
        return null;
    }

    private static boolean didHitBomb() {
        return hitBomb != null;
    }

    private static boolean didHitStar() {
        return hitStar != null;
    }

    private static boolean didHitDoubleJump() {
        return hitDoubleJump != null;
    }
}
