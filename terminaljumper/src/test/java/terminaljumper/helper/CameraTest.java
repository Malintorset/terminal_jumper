package terminaljumper.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;

/**
 * <h1>CameraTest</h1>
 * The CameraTest class tests the camera, as well as the smaller cameras
 * inside the game used for showing various pieces of the UI.
 */
public class CameraTest extends ApplicationAdapter {

    private Camera cameraClassic;
    private Camera cameraExpert;

    @BeforeEach
    public void setup() {
        // create a headless application for testing
        new HeadlessApplication(this);

        // initialize the camera
        cameraClassic = new Camera(GameMode.CLASSIC);
        cameraExpert = new Camera(GameMode.EXPERT);
    }

    /**
     * Test that both Classic and Expert camera moves up on moveUp()
     */
    @Test
    public void testMoveUp() {
        float originalYClassic = cameraClassic.position.y;
        cameraClassic.moveUp();
        assertEquals(originalYClassic + cameraClassic.getCameraSpeed(), cameraClassic.position.y);

        float originalYExpert = cameraExpert.position.y;
        cameraExpert.moveUp();
        assertEquals(originalYExpert + cameraExpert.getCameraSpeed(), cameraExpert.position.y);
        assertTrue(originalYExpert + cameraExpert.getCameraSpeed() > originalYClassic + cameraClassic.getCameraSpeed(),
                originalYClassic + "::" + originalYExpert);
    }

    @Test
    public void testPosition_BOTTOMLEFT_withOffset() {
        assertEquals(0, cameraClassic.position_BOTTOMLEFT_withOffset(0, 0).x);
        assertEquals(10, cameraClassic.position_BOTTOMLEFT_withOffset(10, 0).x);
        assertEquals(100, cameraClassic.position_BOTTOMLEFT_withOffset(100, 0).x);
        assertEquals(0, cameraClassic.position_BOTTOMLEFT_withOffset(0, 0).y);
        assertEquals(10, cameraClassic.position_BOTTOMLEFT_withOffset(0, 10).y);
        assertEquals(100, cameraClassic.position_BOTTOMLEFT_withOffset(0, 100).y);
    }

    @Test
    public void testPosition_TOPLEFT_withOffset() {
        assertEquals(0, cameraClassic.position_TOPLEFT_withOffset(0, 0).x);
        assertEquals(10, cameraClassic.position_TOPLEFT_withOffset(10, 0).x);
        assertEquals(100, cameraClassic.position_TOPLEFT_withOffset(100, 0).x);
        assertEquals(cameraClassic.viewportHeight, cameraClassic.position_TOPLEFT_withOffset(0, 0).y);
        assertEquals(cameraClassic.viewportHeight - 10, cameraClassic.position_TOPLEFT_withOffset(0, -10).y);
        assertEquals(cameraClassic.viewportHeight - 100, cameraClassic.position_TOPLEFT_withOffset(0, -100).y);
    }

    @Test
    public void testPosition_BOTTOMLEFT() {
        assertEquals(0, cameraClassic.position_BOTTOMLEFT().x);
        assertEquals(10, cameraClassic.position_BOTTOMLEFT().x + 10);
        assertEquals(0, cameraClassic.position_BOTTOMLEFT().y);
        assertEquals(10, cameraClassic.position_BOTTOMLEFT().y + 10);
    }

    @Test
    public void testPosition_TOPLEFT() {
        assertEquals(0, cameraClassic.position_TOPLEFT().x);
        assertEquals(10, cameraClassic.position_TOPLEFT().x + 10);
        assertEquals(cameraClassic.viewportHeight, cameraClassic.position_TOPLEFT().y);
        assertEquals(cameraClassic.viewportHeight - 10, cameraClassic.position_TOPLEFT().y - 10);
    }

    /*
    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    */
}
