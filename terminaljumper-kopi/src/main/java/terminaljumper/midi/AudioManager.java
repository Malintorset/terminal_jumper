package terminaljumper.midi;

/**
 * <h1>AudioManager</h1>
 * The AudioManager interface provides a contract for managing audio playback,
 * including background music and sound effects, in a game or application.
 * Implementations of this interface should provide methods to play, pause,
 * stop, and change the volume of audio tracks, as well as manage resources
 * associated with audio playback.
 */
public interface AudioManager {

    /**
     * Plays a music track on a loop with the specified volume.
     *
     * @param sound  the path to the audio file
     * @param volume the volume of the audio, between 0 and 1
     */
    public void playLooping(String sound, float volume);

    /**
     * Plays a sound effect once with the specified volume.
     *
     * @param sound  the path to the audio file
     * @param volume the volume of the audio, between 0 and 1
     */
    public void playOnce(String sound, float volume);

    /** Stops the currently playing background music, if any. */
    public void stopMusic();

    /** Pauses the currently playing background music, if any. */
    public void pauseMusic();

    /** Disposes of the currently playing background music, freeing up resources. */
    public void disposeMusic();

    /**
     * Changes the background music to the specified track, if it's not already
     * playing.
     *
     * @param sound  the path to the new audio file
     * @param volume the volume of the new audio, between 0 and 1
     */
    public void changeMusic(String sound, float volume);

    /**
     * Sets the volume of the background music to the specified value.
     *
     * @param volume the new volume, between 0 and 1
     */
    public void setVolume(float volume);

    /**
     * Sets the volume of the sound effects to the specified value.
     *
     * @param volume the new volume, between 0 and 1
     */
    public void setSoundEffectsVolume(float volume);

    /** Returns the current volume of the background music. */
    public float getCurrentVolume();

    /** Returns the current volume of the sound effects. */
    public float getCurrentSfxVolume();
}
