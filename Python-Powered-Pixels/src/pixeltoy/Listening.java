package pixeltoy;

import audio.AudioController;

public class Listening {

    public void setListenerPosition( double x, double y, double z ) {
        AudioController.setListenerPosition((float) x, (float) y, (float) z);
    }

    public void setListenerVelocity( double x, double y, double z ) {
        AudioController.setListenerVelocity((float) x, (float) y, (float) z);
    }

    public int getSourceCount() {
        return AudioController.getSourceCount();
    }

    public void setListenerOrientation(float fx, float fy, float fz, char upaxis) {
        AudioController.setListenerOrientation(fx, fy, fz, upaxis);
    }

    public void setListenerOrientation(float fx, float fy, float fz) {
        AudioController.setListenerOrientation(fx, fy, fz, 'z');
    }

    public float[] getListenerPosition() {
        return AudioController.getListenerPosition();
    }

    public float[] getListenerVelocity() {
        return AudioController.getListenerVelocity();
    }

    public float[] getListenerOrientation() {
        return AudioController.getListenerOrientation();
    }

    public float getVolume() {
        return AudioController.getVolume();
    }

    public void setVolume(double volume) {
        AudioController.setVolume((float) volume);
    }

    public void rewindAll() {
        AudioController.rewindAll();
    }

    public void pauseAll() {
        AudioController.pauseAll();
    }

    public void stopAll() {
        AudioController.stopAll();
    }
}
