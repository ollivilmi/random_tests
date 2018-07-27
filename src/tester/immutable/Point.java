package tester.immutable;

public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point translate(double x, double y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point scale(double a) {
        return new Point(x*a, y*a);
    }

    @Override
    public String toString() {
        return "["+x+","+y+"]";
    }

    public static void main(String[] args) {
        System.out.println(new Point(3, 4).translate(1, 3).scale(0.5));
    }
}
