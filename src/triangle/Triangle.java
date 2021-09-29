package triangle;

import java.awt.Point;

import graphic.*;

public class Triangle {

    public static void main(String[] args) {
        Window window = new Window(500, 500, "Triangle");
        Point p1 = new Point(250, 100);
        Point p2 = new Point(400, 400);
        Point p3 = new Point(100, 400);
        window.plotLine(p1, p2);
        window.plotLine(p2, p3);
        window.plotLine(p1, p3);
    }
}
