import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;


public class Main {

	private ArrayList<Point> points;
	double totalD;

	public Main(String file) throws FileNotFoundException {
		loadFile(file);
	}

	public void loadFile(String file) throws FileNotFoundException {
		points = new ArrayList<Point>();
		FileReader reader = new FileReader(file);
		Scanner scan = new Scanner(reader);
		int size, x, y;
		size = Integer.parseInt(scan.next());
		while( scan.hasNext() ) {
			if( scan.hasNextLine() ) {
				x = Integer.parseInt(scan.next());
				y = Integer.parseInt(scan.next());
				points.add(new Point(x,y));
			}
		}
		scan.close();
	}

	public double distance(Point p1, Point p2) {
		double d = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
		return d;
	}

	public void nearestNeighbor(ArrayList<Point> points) {
		totalD = 0;
		Point initialP = points.get(0);
		points.remove(initialP);
		Point currentP = initialP;
		while (!points.isEmpty()) {
			double shortestD = 0;
			Point tempP = null;
			for (Point p: points) {
				double tempD = distance(currentP, p);
				if (shortestD == 0 || tempD < shortestD) {
					shortestD = tempD;
					tempP = p;
				}
			}
			currentP = tempP;
			totalD += shortestD;
			points.remove(currentP);
		}
		totalD =+ distance(currentP, initialP);
		currentP = initialP;
	}

	// Coordinates are randomly generated between -20 and 20.
	public static void generateInputs() throws FileNotFoundException, UnsupportedEncodingException {
		Random random = new Random();
		// n = 200
		PrintWriter writer = new PrintWriter("input1.txt", "UTF-8");
		int n = 200;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
		// n = 250
		writer = new PrintWriter("input2.txt", "UTF-8");
		n = 20;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
		// n = 300
		writer = new PrintWriter("input3.txt", "UTF-8");
		n = 300;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
		// n = 350
		writer = new PrintWriter("input4.txt", "UTF-8");
		n = 350;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Main.generateInputs();
		Main main = new Main("input4.txt");
		long startTime = System.currentTimeMillis(); // start_time
		main.nearestNeighbor(main.points);
		long runtime = System.currentTimeMillis() - startTime; // runtime = start_time - current_time
		System.out.println("Runtime: " + runtime + " milliseconds");
		System.out.println("Total Distance: " + main.totalD);
	}
}
