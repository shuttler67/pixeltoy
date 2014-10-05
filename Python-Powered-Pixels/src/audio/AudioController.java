package audio;

import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import static org.lwjgl.openal.AL10.*;
import org.lwjgl.openal.OpenALException;
import org.lwjgl.util.WaveData;
import org.newdawn.slick.openal.OggData;
import org.newdawn.slick.openal.OggDecoder;
import org.newdawn.slick.openal.OggInputStream;

public class AudioController {

	private static HashMap<String, Integer> loadedFiles = new HashMap<String, Integer>();
	private static ArrayList<Source> sources = new ArrayList<Source>(32);
	private static OggDecoder oggDecoder = new OggDecoder();
    private static FloatBuffer position = BufferUtils.createFloatBuffer(3);
    private static FloatBuffer velocity = BufferUtils.createFloatBuffer(3);
    private static FloatBuffer orientation = BufferUtils.createFloatBuffer(6);

    public static void init() {
        try {
            AL.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

	public static String getALErrorString(int err) {
		switch (err) 
		{
		case AL_NO_ERROR:
			return "AL_NO_ERROR";
		case AL_INVALID_NAME:
			return "AL_INVALID_NAME";
		case AL_INVALID_ENUM:
			return "AL_INVALID_ENUM";
		case AL_INVALID_VALUE:
			return "AL_INVALID_VALUE";
		case AL_INVALID_OPERATION:
			return "AL_INVALID_OPERATION";
		case AL_OUT_OF_MEMORY:
			return "AL_OUT_OF_MEMORY";
		default:
			return "No such error code";
		}
	}
	
	public static void checkForALError() {
		int result;
		if ((result = alGetError()) != AL_NO_ERROR) {
			throw new OpenALException(getALErrorString(result));
		}
    }
	
	private static int loadALBuffer(String path) {
		int buffer = alGenBuffers();
		
		checkForALError();

		if (path.toLowerCase().endsWith(".wav")) {
			try {
                BufferedInputStream file = new BufferedInputStream(new FileInputStream(path));
				WaveData waveFile = WaveData.create(file);
				alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
				waveFile.dispose();
			} catch (FileNotFoundException e) {
				throw new RuntimeException("No such file: " + path);
			}
		} else if (path.toLowerCase().endsWith(".ogg")) {
			try {
				OggData oggFile = oggDecoder.getData(new FileInputStream(path));
				
				alBufferData(buffer, oggFile.channels == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, oggFile.data, oggFile.rate);
				
			} catch (IOException e) {
				throw new RuntimeException("No such file: " + path);
			}
		}
		
		checkForALError();
		
		return buffer;
	}

    public static void killALData() {
        IntBuffer scratch = BufferUtils.createIntBuffer(loadedFiles.size());
        for ( Integer i :loadedFiles.values()) {
            scratch.put(i);
        }
        alDeleteBuffers(scratch);
        AL.destroy();
    }

	public static int getLoadedALBuffer(String path) {

		if (loadedFiles.containsKey(path))
			return loadedFiles.get(path);
		
		int buffer = loadALBuffer(path);
		
		loadedFiles.put(path, buffer);
		return buffer;
	}

    public static OggInputStream getOggStream(String path) {
        try {
            return new OggInputStream( new BufferedInputStream(new FileInputStream(new File(path))) );
        } catch (Exception e) {
            throw new RuntimeException("No such file: " + path);
        }
    }

    public static void setListenerPosition(float x, float y, float z) {
        position.put(new float[]{x, y, z});
        position.flip();
        alListener(AL_POSITION, position);
    }

    public static void setListenerVelocity(float x, float y, float z) {
        velocity.put(new float[]{x, y, z});
        velocity.flip();
        alListener(AL_VELOCITY, velocity);
    }

    public static void setListenerOrientation( float fx, float fy, float fz, char upaxis) {
        float ux = 0, uy = 0, uz = 0;

        if (upaxis == 'x')
            ux = 1;
        else if (upaxis == 'y')
            uy = 1;
        else if (upaxis == 'z')
            uz = 1;

        orientation.put(new float[]{fx, fy, fz, ux, uy, uz});
        orientation.flip();
        alListener(AL_ORIENTATION, orientation);
    }

    public static float[] getListenerPosition() {
        return position.array();
    }

    public static float[] getListenerVelocity() {
        return velocity.array();
    }

    public static float[] getListenerOrientation() {
        return orientation.array();
    }

    public static float getVolume() {
        return alGetListenerf(AL_GAIN);
    }

    public static void setVolume( float volume) {
        alListenerf(AL_GAIN, volume);
    }

    public static int getSourceCount() {
        return sources.size();
    }

    public static void pauseAll() {
        IntBuffer allsources = BufferUtils.createIntBuffer(sources.size());
        for (Source source : sources) {
            allsources.put(source.getId());
        }
        alSourcePause(allsources);
    }

    public static void stopAll() {
        IntBuffer allsources = BufferUtils.createIntBuffer(sources.size());
        for (Source source : sources) {
            if (source.type == Source.Type.STATIC)
                allsources.put(source.getId());
            else
                source.stop();
        }
        alSourceStop(allsources);
    }

    public static void rewindAll() {
        IntBuffer allsources = BufferUtils.createIntBuffer(sources.size());
        for (Source source : sources) {
            if (source.type == Source.Type.STATIC)
                allsources.put(source.getId());
            else
                source.rewind();
        }
        alSourceRewind(allsources);
    }

    //To be used only by audio.Source
    public static void addSource(Source source) {
        sources.add(source);
    }

    public static void update() {
        for (Source s: sources) {
            s.update();
        }
    }
}
