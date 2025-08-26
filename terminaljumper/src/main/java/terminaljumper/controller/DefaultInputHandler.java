package terminaljumper.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import terminaljumper.helper.Constants;
import terminaljumper.helper.PowerUpHandler;
import terminaljumper.midi.DefaultAudioManager;
import terminaljumper.objects.Player;

/**
 * <h1>DefaultInputHandler</h1>
 * The DefaultInputHandler class implements the InputHandler interface,
 * and is responsible for accepting and handling keyboard input.
 */
public class DefaultInputHandler implements InputHandler {

    Player player;
    public DefaultInputHandler(Player player) {
        this.player = player;
    }

    @Override
    public void checkInput() {
        handlePlayerInput();
        handleMiscInput();
    }

    @Override
    public void handleMiscInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void handlePlayerInput() {
        player.setVelX(0);
        float force = player.getBody().getMass() * 200;

        if (player.getIsGrounded() && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.getBody().applyLinearImpulse(new Vector2(0, force), player.getBody().getPosition(), true);
            player.setIsGrounded(false);
            player.setDoubleJumpEnabled(true);
            DefaultAudioManager.instance.playOnce(Constants.Audio.JUMP, 0.8f);
        }
        else if (!player.getIsGrounded() && player.getDoubleJumpEnabled() && player.getIsAffectedByDoubleJump() && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.getBody().applyLinearImpulse(new Vector2(0, force), player.getBody().getPosition(), true);
            player.setDoubleJumpEnabled(false);
            DefaultAudioManager.instance.playOnce(Constants.Audio.JUMP, 0.8f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setPlayerFacing(false);
            player.setVelX(1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setPlayerFacing(true);
            player.setVelX(-1);
        }

        if (player.getIsAffectedByBomb())
            player.setVelX(player.getVelX() * PowerUpHandler.BOMB_SLOWING_PRECENTAGE);
        player.getBody().setLinearVelocity(player.getVelX() * player.getSpeed(), player.getBody().getLinearVelocity().y < 27 ? player.getBody().getLinearVelocity().y : 27);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
