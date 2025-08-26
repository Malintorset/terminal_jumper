package terminaljumper.helper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import terminaljumper.objects.Mouse;
import terminaljumper.objects.Player;
import terminaljumper.view.GameScreen;

/**
 * <h1>EnemyFactory</h1>
 * A fcctory clas responsible for producing enemy objects. Notably, the Mouse pointer.
 */
public class EnemyFactory {

    public static Mouse createMouse(Vector2 pos, Player player, Camera camera, GameMode gameMode) {
        Body body = BodyHelperService.createBody(pos.x, pos.y, 38, 38, false,
                GameScreen.getWorld());
        body.setUserData(Constants.CollisionObject.MOUSE);
        body.setGravityScale(0f);
        Mouse mouse = new Mouse(body);
        mouse.setPlayer(player);
        mouse.setCamera(camera);
        mouse.setGameMode(gameMode);
        return mouse;
    }
}
