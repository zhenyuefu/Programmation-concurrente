package triangle;

import java.awt.Point;

import graphic.Window;

public class DessineLigneInterface implements Runnable {
    private Point p1, p2;
    private Window window;

    public DessineLigneInterface(Window window, Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.window = window;
    }

    @Override
    public void run() {
        window.plotLine(p1, p2);

    }

}
