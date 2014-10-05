package core;

public class Timer {
    public static double lastFrame;

    public static double getTime() {
        return (((double)System.nanoTime()) / 1000000000d);
    }

    public static double getDelta() {
        double currentTime = getTime();
        double delta = currentTime - lastFrame;
        lastFrame = getTime();
        return delta;
    }
}
