package terminaljumper.midi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultAudioManagerTest {

    private static DefaultAudioManager audioManager;

    @Mock
    private Music music;

    @Mock
    private FileHandle fileHandle;

    @Mock
    private Music soundEffect;
    
    
    @BeforeEach
    public void setUp() {
        Gdx.audio = Mockito.mock(com.badlogic.gdx.Audio.class);
        Gdx.files = Mockito.mock(com.badlogic.gdx.Files.class);

        audioManager = new DefaultAudioManager();
    }

    @Test
    public void testGetCurrentVolume() {
        float volume = audioManager.getCurrentVolume();
        assertEquals(0.5f, volume);
    }

    @Test
    public void testGetCurrentSfxVolume() {
        float sfxVolume = audioManager.getCurrentSfxVolume();
        assertEquals(0.5f, sfxVolume);
    }

    @Test
    public void testPlayLooping() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);
    
        float volume = 0.5f;
        float currentVolume = audioManager.getCurrentVolume();
    
        audioManager.playLooping("test_sound", volume);
        verify(music).setLooping(true);
        verify(music).setVolume(currentVolume * volume);
        verify(music).play();
    }

    @Test
    public void testPlayOnce() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(soundEffect);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);
    
        float volume = 0.5f;
        float currentSfxVolume = audioManager.getCurrentSfxVolume();
    
        audioManager.playOnce("test_sound", volume);
        verify(soundEffect).setLooping(false);
        verify(soundEffect).setVolume(currentSfxVolume * volume);
        verify(soundEffect).play();
    }

    @Test
    public void testStopMusic() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);

        audioManager.playLooping("test_sound", 0.5f);
        audioManager.stopMusic();
        verify(music).stop();
    }

    @Test
    public void testPauseMusic() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);

        audioManager.playLooping("test_sound", 0.5f);
        audioManager.pauseMusic();
        verify(music).pause();
    }

    @Test
    public void testDisposeMusic() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);

        audioManager.playLooping("test_sound", 0.5f);
        audioManager.disposeMusic();
        verify(music).dispose();
    }

    @Test
    public void testChangeMusic_NoMusicPlaying() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);

        audioManager.changeMusic("test_sound", 0.5f);

        verify(music).play();
    }

    @Test
    public void testChangeMusic_SameMusic() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);
    
        audioManager.changeMusic("test_sound", 0.5f);
        audioManager.changeMusic("test_sound", 0.5f);
    
        verify(music, times(2)).play();
    }
    
    @Test
    public void testChangeMusic_DifferentMusic() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal(anyString())).thenReturn(fileHandle);
        when(music.isPlaying()).thenReturn(true);
    
        audioManager.changeMusic("test_sound1", 0.5f);
        audioManager.changeMusic("test_sound2", 0.5f);
    
        verify(music).stop();
        verify(music, times(2)).play();
    }

    @Test
    public void testSetVolume() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(music);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);

        audioManager.playLooping("test_sound", 0.5f);
        audioManager.setVolume(0.7f);

        verify(music).setVolume(0.7f);
        assertEquals(0.7f, audioManager.getCurrentVolume());
    }

    @Test
    public void testSetSoundEffectsVolume() {
        when(Gdx.audio.newMusic(fileHandle)).thenReturn(soundEffect);
        when(Gdx.files.internal("test_sound")).thenReturn(fileHandle);

        audioManager.playOnce("test_sound", 0.5f); 
        audioManager.setSoundEffectsVolume(0.7f);

        verify(soundEffect).setVolume(0.7f);
        assertEquals(0.7f, audioManager.getCurrentSfxVolume());
    }

}
