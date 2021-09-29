package triangle;

import java.awt.Point;

import graphic.Window;

public class TestDessineLigne {
    public static void main(String[] args) {
        Window window = new Window(500, 500, "Triangle");
        Point p1 = new Point(250, 100);
        Point p2 = new Point(400, 400);
        Point p3 = new Point(100, 400);

//        new DessineLigne(window, p2, p1).start();
//        new DessineLigne(window, p1, p3).start();
//        new DessineLigne(window, p3, p2).start();

        DessineLigneInterface dl = new DessineLigneInterface(window, p2, p1);
        new Thread(dl).start();
        dl = new DessineLigneInterface(window, p1, p3);
        new Thread(dl).start();
        dl = new DessineLigneInterface(window, p3, p2);
        new Thread(dl).start();
    }
}
