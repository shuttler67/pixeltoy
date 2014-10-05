package audio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.openal.AL10.*;

public class Source {

	protected int id;

    protected Cone cone;
    public Type type = Type.STATIC;
    public Streamer streamer;

    protected boolean isRelative;
    protected boolean loop;

    protected FloatBuffer position;
    protected FloatBuffer velocity;
    protected FloatBuffer direction;

    protected class Cone {
        public float innerAngle;
        public float outerAngle;
        public float outerVolume;
        public void set( float innerAngle, float outerAngle, float outerVolume) {
            this.innerAngle = innerAngle;
            this.outerAngle = outerAngle;
            this.outerVolume = outerVolume;
        }
        public float[] get() {
            return new float[]{innerAngle, outerAngle, outerVolume};
        }
    }
    protected enum Type {
        STATIC, STREAM
    }

	public Source(String path, String type, boolean loop) {

        if (type.equals("static"))
            this.type = Type.STATIC;
        else if (type.equals("stream"))
            this.type = Type.STREAM;

        if (path.toLowerCase().endsWith(".wav"))
            this.type = Type.STATIC;

        id = alGenSources();

        AudioController.checkForALError();
        AudioController.addSource(this);

        if (this.type == Type.STATIC) {
            int buffer = AudioController.getLoadedALBuffer(path);
            alSourcei(id, AL_BUFFER, buffer);

        } else if (this.type == Type.STREAM) {
            streamer = new Streamer(id, path);
        }

        position = BufferUtils.createFloatBuffer(3);
        velocity = BufferUtils.createFloatBuffer(3);
        direction = BufferUtils.createFloatBuffer(3);

        setPitch(1.0f);
        setVolume(1.0f);
        setPosition(0.0f, 0.0f, 0.0f);
        setVelocity(0.0f, 0.0f, 0.0f);
        setLooping(loop);
        AudioController.checkForALError();
	}

    public Source(String path) throws MalformedURLException {
        this(path, false);
    }
    public Source(String path, String type) throws MalformedURLException {
        this(path, type, false);
    }
    public Source(String path, boolean loop) throws MalformedURLException {
        this(path, "static", loop);
    }

    public void update() {
        if (type == Type.STREAM && !isPaused()) {
            streamer.update();
        }
    }

	public void setPitch(float pitch) {
		alSourcef(id, AL_PITCH, pitch);
	}
	
	public void setVolume(float volume) {
		alSourcef(id, AL_GAIN, volume);
	}
	
	public void setPosition(float x, float y, float z) {
        setFloatBuffer(position, x, y, z);
        alSource(id, AL_POSITION, position);
	}
	
	public void setVelocity(float x, float y, float z) {
        setFloatBuffer(velocity, x, y, z);
        alSource(id, AL_POSITION, velocity);
	}

    public void setDirection(float x, float y, float z) {
        setFloatBuffer(direction, x, y, z);
        alSource(id, AL_DIRECTION, direction);
    }

    private void setFloatBuffer(FloatBuffer buffer, float x, float y, float z) {
        buffer.clear();
        buffer.put(new float[] { x, y, z });
        buffer.flip();
    }

    public void setLooping(boolean enabled) {
        if (type == Type.STATIC)
		    alSourcei(id, AL_LOOPING, enabled ? AL_TRUE : AL_FALSE);
        loop = enabled;
	}

    public void setRelative(boolean enabled) {
        isRelative = enabled;
        alSourcei(id, AL_SOURCE_RELATIVE, enabled ? AL_TRUE : AL_FALSE);
    }
    public void play() {
        if (type == Type.STATIC)
            alSourcePlay(id);
        else if (type == Type.STREAM) {
            try {
                streamer.play(loop);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        alSourcePause(id);
    }

    public void stop() {
        if (type == Type.STREAM) {
            try {
                streamer.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alSourceStop(id);
        }
    }

    public void rewind() {
        if (type == Type.STATIC)
            alSourceRewind(id);
        else if (type == Type.STREAM)
            streamer.setPosition(0);
    }



    public void setCone( float innerAngle, float outerAngle, float outerVolume) {
        if (cone == null)
            cone = new Cone();
        cone.set(innerAngle, outerAngle, outerVolume);
        alSourcef(id, AL_CONE_INNER_ANGLE, innerAngle);
        alSourcef(id, AL_CONE_OUTER_ANGLE, outerAngle);
        alSourcef(id, AL_CONE_OUTER_GAIN, outerVolume);
    }

    public float[] getCone() {
        return cone.get();
    }

    public boolean isLooping() {
        return alGetSourcei(id, AL_LOOPING) == AL_TRUE;
    }

    public boolean isRelative() {
        return isRelative;
    }

    public boolean isPlaying() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_PLAYING;
    }
    public boolean isPaused() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_PAUSED;
    }
    public boolean isStopped() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_STOPPED;
    }

    public float getVolume() {
        return alGetSourcef(id, AL_GAIN);
    }

    public float getPitch() {
        return alGetSourcei(id, AL_PITCH);
    }

    public float[] getPosition() {
        return position.array();
    }
    public float[] getVelocity() {
        return velocity.array();
    }
    public float[] getDirection() {
        return direction.array();
    }

    public int getId() { return id; }

}
