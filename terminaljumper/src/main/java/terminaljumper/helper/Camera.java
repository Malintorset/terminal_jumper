package terminaljumper.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * <h1>Camera</h1>
 * The Camera class manages camera logic for the in-game view.
 */
public class Camera extends OrthographicCamera {

    /** The number of pixels the camera should move up when moveUp() is called */
    private float cameraSpeed;
    private int timeCounter;

    public Camera(GameMode gameMode) {
        int widthScreen = Gdx.graphics.getWidth();
        int heightScreen = Gdx.graphics.getHeight();
        this.setToOrtho(false, widthScreen, heightScreen);
        initCamera(gameMode);
    }

    private void initCamera(GameMode gameMode) {
        if (gameMode == GameMode.CLASSIC) {
            this.cameraSpeed = 1.2f;
        }
        if (gameMode == GameMode.EXPERT) {
            this.cameraSpeed = 2.2f;
        }
    }

    /** Moves the camera upwards by a given speed. */
    public void moveUp() {
        Vector3 positon = this.position;
        positon.y += cameraSpeed;
        incrementTimeCounter();
        speedIncreaseLogic();
        this.position.set(positon);
        this.update();
    }

    private void incrementTimeCounter() {
        this.timeCounter += 1;
    }

    private void speedIncreaseLogic() {
        if (this.timeCounter % 360 == 0 && this.cameraSpeed <= 4) {
            this.cameraSpeed += 0.1f;
        }
    }

    /** Returns the position of the top-left corner of the camera */
    public Vector3 position_TOPLEFT() {
        return position_TOPLEFT_withOffset(0, 0);
    }

    /** Returns the position of the bottom-left corner of the camera */
    public Vector3 position_BOTTOMLEFT() {
        return position_BOTTOMLEFT_withOffset(0, 0);
    }

    /**
     * Returns the position of the top-left corner of the camera with offset
     * 
     * @param dx offset in x (positive number moves to the right)
     * @param dy offset in y (positive number moves up)
     */
    public Vector3 position_TOPLEFT_withOffset(float dx, float dy) {
        Vector2 corner = new Vector2(this.position.x - this.viewportWidth / 2 + dx,
                this.position.y + this.viewportHeight / 2 + dy);
        return new Vector3(corner, 0);
    }

    /**
     * Returns the position of the bottom-left corner of the camera with offset
     * 
     * @param dx offset in x (positive number moves to the right)
     * @param dy offset in y (positive number moves up)
     */
    public Vector3 position_BOTTOMLEFT_withOffset(float dx, float dy) {
        Vector2 corner = new Vector2(this.position.x - this.viewportWidth / 2 + dx,
                this.position.y - this.viewportHeight / 2 + dy);
        return new Vector3(corner, 0);
    }

    public float getCameraSpeed() {
        return cameraSpeed;
    }
}
