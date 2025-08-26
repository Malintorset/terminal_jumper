package terminaljumper.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import terminaljumper.objects.Bomb;
import terminaljumper.objects.DoubleJump;
import terminaljumper.objects.Star;

import java.util.List;

import static terminaljumper.helper.Constants.PPM;

/**
 * <h1>RenderUtils</h1>
 * Helper class containing static functions for rendering/drawing
 */
public class RenderUtils {

    /** Displays the number at the given position */
    public static void drawNumbers(SpriteBatch batch, int number, Vector3 position, float width, float height,
            TextureRegion[] numbers) {
        if (number < 10) {
            batch.draw(numbers[number], position.x, position.y, width, height);
        } else {
            int length = (int) (Math.log10(number) + 1);
            int spacing = 40; // For adding space between numbers
            for (int i = 0; i < length; i++) {
                batch.draw(numbers[Integer.parseInt(("" + number).substring(i, i + 1))],
                        position.x + spacing * i,
                        position.y, width, height);
            }
        }
    }

    /**
     * This method draw the given image at the given location and dimentions
     * 
     * @param batch
     * @param path     path to the image
     * @param position position to draw at
     * @param width    width of image
     * @param height   height of image
     */
    public static void drawImage(SpriteBatch batch, String path, Vector3 position, float width, float height) {
        Texture texture = new Texture(Gdx.files.internal(path));
        TextureRegion region = new TextureRegion(texture);
        batch.draw(region, position.x, position.y, width, height);
    }

    /**
     * This method draws the bombs
     * 
     * @param batch
     * @param bombs
     */
    public static void drawBombs(SpriteBatch batch, List<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            bomb.drawSprite(batch);
        }
    }

    public static void drawStars(SpriteBatch batch, List<Star> stars) {
        for (Star star : stars) {
            star.drawSprite(batch);
        }
    }

    public static void drawDoubleJumps(SpriteBatch batch, List<DoubleJump> doubleJumps) {
        for (DoubleJump doubleJump : doubleJumps) {
            doubleJump.drawSprite(batch);
        }
    }

    public static TextureRegion[] loadTextureSprite(String filename, int coloumns) {
        Texture texture = new Texture(filename);
        return TextureRegion.split(texture, texture.getWidth() / coloumns, texture.getHeight())[0];
    }

    public static void initBombSprites(List<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal(Constants.Images.BOMB)));
            sprite.setScale(1.9f);
            // Adjust bomb sprite position offset (for placing the bomb inside the box)
            float offsetX = 9;
            float offsetY = 7;
            sprite.setPosition(bomb.getPosition().x * PPM + offsetX, bomb.getPosition().y * PPM + offsetY);
            bomb.setSprite(sprite);
        }
    }

    public static void initStarSprites(List<Star> stars) {
        for (Star star : stars) {
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal(Constants.Images.STAR)));
            sprite.setScale(2f);
            // Adjust bomb sprite position offset (for placing the bomb inside the box)
            float offsetX = -4;
            float offsetY = -7;
            sprite.setPosition(star.getPosition().x * PPM + offsetX, star.getPosition().y * PPM + offsetY);
            star.setSprite(sprite);
        }
    }

    public static void initDoubleJumpSprites(List<DoubleJump> doubleJumps) {
        for (DoubleJump doubleJump : doubleJumps) {
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal(Constants.Images.DOUBLE_JUMP)));
            sprite.setScale(2.5f);
            // Adjust bomb sprite position offset (for placing the DoubleJump inside the box)
            float offsetX = 9f;
            float offsetY = 8f;
            sprite.setPosition(doubleJump.getPosition().x * PPM + offsetX, doubleJump.getPosition().y * PPM + offsetY);
            doubleJump.setSprite(sprite);
        }
    }
}
