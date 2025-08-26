package terminaljumper.midi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * <h1>DefaultAudioManager</h1>
 * Default implementation of the AudioManager interface, using libGDX for audio playback.
 */
public class DefaultAudioManager implements AudioManager {

    public static AudioManager instance = new DefaultAudioManager();
    private float currentVolume = 0.5f;
    private float currentSfxVolume = 0.5f;
    private Music music;
    private Music soundEffect;
    private String currentMusic;

    @Override
    public float getCurrentVolume() {
        return currentVolume;
    }

    @Override
    public float getCurrentSfxVolume() {
        return currentSfxVolume;
    }

    @Override
    public void playLooping(String sound, float volume) {
        music = Gdx.audio.newMusic(Gdx.files.internal(sound));
        music.setLooping(true);
        music.setVolume(currentVolume * volume);
        music.play();

        currentMusic = sound;
    }

    @Override
    public void playOnce(String sound, float volume) {
        soundEffect = Gdx.audio.newMusic(Gdx.files.internal(sound));
        soundEffect.setLooping(false);
        soundEffect.setVolume(currentSfxVolume * volume);
        soundEffect.play();
    }

    @Override
    public void stopMusic() {
        if (music != null) {
            music.stop();
        }
    }

    @Override
    public void pauseMusic() {
        if (music != null) {
            music.pause();
        }
    }

    @Override
    public void disposeMusic() {
        music.dispose();
    }

    @Override
    public void changeMusic(String sound, float volume) {
        if (music != null && music.isPlaying()) {
            if (!sound.equals(currentMusic)) {
                music.stop();
            }   else {
                // If the same music is requested, the method returns without doing anything
                return;
            }
        }
        playLooping(sound, volume);
    }

    @Override
    public void setVolume(float volume) {
        if (music != null) {
            music.setVolume(volume);
        }
        currentVolume = volume;
    }

    @Override
    public void setSoundEffectsVolume(float volume) {
        if (soundEffect != null) {
            soundEffect.setVolume(volume);
        }
        currentSfxVolume = volume;
    }
}
