package terminaljumper.helper;

import com.badlogic.gdx.physics.box2d.*;
import terminaljumper.helper.tilemap.TileMapHandler;
import terminaljumper.objects.Bomb;
import terminaljumper.objects.DoubleJump;
import terminaljumper.objects.Star;
import terminaljumper.view.GameScreen;

/**
 * <h1>CollisionHandler</h1>
 * This is a helper class for handling all collision.
 */
public class CollisionHandler {

    /**
     * Makes player able to jump through platform, but not fall through them.
     * Jumping is reset if player is falling, and collides with a platform or wall
     */
    public static void setWorldContactListener(GameScreen gameScreen, TileMapHandler tileMapHandler) {
        GameScreen.getWorld().setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (getVertivalPlayerVelocity(contact) <= 0 && !contactWasAWallWithPlayer(contact)) {
                    gameScreen.getPlayer().setIsGrounded(true);
                }
                if (contactWasABombWithPlayer(contact)) {
                    contact.setEnabled(false);
                    Body bombBody = getBombBody(contact);
                    Bomb bomb = PowerUpHandler.getPowerupFromBody(bombBody, tileMapHandler);
                    PowerUpHandler.didHitBomb(bomb);
                }
                if (contactWasAStarWithPlayer(contact)) {
                    contact.setEnabled(false);
                    Body starBody = getStarBody(contact);
                    Star star = PowerUpHandler.getPowerupFromBody(starBody, tileMapHandler);
                    PowerUpHandler.didHitStar(star);
                }
                if (contactWasADoubleJumpWithPlayer(contact)) {
                    contact.setEnabled(false);
                    Body doubleJumpBody = getDoubleJumpBody(contact);
                    DoubleJump doubleJump = PowerUpHandler.getPowerupFromBody(doubleJumpBody, tileMapHandler);
                    PowerUpHandler.didHitDoubleJump(doubleJump);
                }
                if (contactWasAMouseWithPlayer(contact)) { // Mouse and Player collision
                    gameScreen.onMouseHitPlayer();
                }
            }

            @Override
            public void endContact(Contact contact) {
                if (playerCollidedWithPlatform(contact))
                    gameScreen.getPlayer().setIsGrounded(false);
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                if (playerCollidedWithPlatform(contact) && getVertivalPlayerVelocity(contact) > 0) {
                    contact.setEnabled(false);
                }
                if (getVertivalPlayerVelocity(contact) == 0) {
                    gameScreen.getPlayer().setIsGrounded(true);
                }
                if (contactWasAMouseAndNotPlayer(contact)) {
                    contact.setEnabled(false);
                }
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });
    }

    /** is public static for testing */
    public static float getVertivalPlayerVelocity(Contact contact) {
        Body body1 = contact.getFixtureA().getBody();
        Body body2 = contact.getFixtureB().getBody();
        if (body1.getUserData().equals(Constants.CollisionObject.PLAYER))
            return body1.getLinearVelocity().y;
        return body2.getLinearVelocity().y;
    }

    /** is public static for testing */
    public static boolean playerCollidedWithPlatform(Contact contact) {
        Object collider1 = contact.getFixtureA().getBody().getUserData();
        Object collider2 = contact.getFixtureB().getBody().getUserData();
        return (collider1.equals(Constants.CollisionObject.PLAYER)
                && collider2.equals(Constants.CollisionObject.PLATFORM)
                || collider2.equals(Constants.CollisionObject.PLAYER)
                        && collider1.equals(Constants.CollisionObject.PLATFORM));
    }

    /** is public static for testing */
    public static boolean contactWasAWallWithPlayer(Contact contact) {
        boolean case1 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.WALL)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.PLAYER);
        boolean case2 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.PLAYER)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.WALL);
        return case1 || case2;
    }

    /** is public static for testing */
    public static boolean contactWasABombWithPlayer(Contact contact) {
        boolean case1 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.BOMB)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.PLAYER);
        boolean case2 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.PLAYER)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.BOMB);
        return case1 || case2;
    }

    /** is public static for testing */
    public static boolean contactWasAStarWithPlayer(Contact contact) {
        boolean case1 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.STAR)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.PLAYER);
        boolean case2 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.PLAYER)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.STAR);
        return case1 || case2;
    }

    public static boolean contactWasADoubleJumpWithPlayer(Contact contact) {
        boolean case1 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.DOUBLE_JUMP)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.PLAYER);
        boolean case2 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.PLAYER)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.DOUBLE_JUMP);
        return case1 || case2;
    }

    public static boolean contactWasAMouseWithPlayer(Contact contact) {
        boolean case1 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.MOUSE)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.PLAYER);
        boolean case2 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.PLAYER)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.MOUSE);
        return case1 || case2;
    }

    public static boolean contactWasAMouseAndNotPlayer(Contact contact) {
        boolean case1 = contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.MOUSE)
                && !contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.PLAYER);
        boolean case2 = !contact.getFixtureA().getBody().getUserData().equals(Constants.CollisionObject.PLAYER)
                && contact.getFixtureB().getBody().getUserData().equals(Constants.CollisionObject.MOUSE);
        return case1 || case2;
    }

    /** is public static for testing */
    public static Body getBombBody(Contact contact) {
        Body body1 = contact.getFixtureA().getBody();
        Body body2 = contact.getFixtureB().getBody();
        if (body1.getUserData().equals(Constants.CollisionObject.BOMB)) {
            return body1;
        } else if (body2.getUserData().equals(Constants.CollisionObject.BOMB)) {
            return body2;
        }
        return null;
    }

    /** is public static for testing */
    public static Body getStarBody(Contact contact) {
        Body body1 = contact.getFixtureA().getBody();
        Body body2 = contact.getFixtureB().getBody();
        if (body1.getUserData().equals(Constants.CollisionObject.STAR)) {
            return body1;
        } else if (body2.getUserData().equals(Constants.CollisionObject.STAR)) {
            return body2;
        }
        return null;
    }

    public static Body getDoubleJumpBody(Contact contact) {
        Body body1 = contact.getFixtureA().getBody();
        Body body2 = contact.getFixtureB().getBody();
        if (body1.getUserData().equals(Constants.CollisionObject.DOUBLE_JUMP)) {
            return body1;
        } else if (body2.getUserData().equals(Constants.CollisionObject.DOUBLE_JUMP)) {
            return body2;
        }
        return null;
    }
}
