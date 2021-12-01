package VonKoch;

import graphic.Window;
import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VonKochMono {
	private final static double LG_MIN = 8.0;
	private final static int NB_THREADS = 100;
	private final ExecutorService exec = Executors.newFixedThreadPool(NB_THREADS);
	Window f;

	public void add(Runnable r) {
		exec.execute(r);
	}

	public VonKochMono(Window f, Point a, Point b, Point c) {
		this.f = f;
		exec.execute(new Cote(f, b, a, this));
		exec.execute(new Cote(f, a, c, this));
		exec.execute(new Cote(f, c, b, this));
	}
}