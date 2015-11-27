package org.ulco;

public class Triangle extends GraphicsObject {
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(){
        this.p1 = new Point(0,0);
        this.p2 = new Point(0,0);
        this.p3 = new Point(0,0);
    }

    public void initObject(String json) {
        String str = json.replaceAll("\\s+","");
        int p1Index = str.indexOf("p1");
        int p2Index = str.indexOf("p2");
        int p3Index = str.indexOf("p3");
        int endIndex = str.lastIndexOf("}");

        this.p1 = new Point(str.substring(p1Index + 7, p2Index - 1));
        this.p2 = new Point(str.substring(p2Index + 7, p3Index - 1));
        this.p3 = new Point(str.substring(p3Index + 6, endIndex));
    }

    public GraphicsObject copy() {
        return new Triangle(p1.copy(), p2.copy(), p3.copy());
    }

    public boolean isClosed(Point pt, double distance) {
        Point center = new Point(p1.getX() + p2.getX() + p3.getX()/ 3,
                p1.getY() + p2.getY() + p3.getY()/ 3);

        return Helpers.isClosed(center, pt, distance);
    }

    void move(Point delta) { p1.move(delta);p2.move(delta);p3.move(delta); }

    public String toJson() {
        return "{ type: triangle, p1: " + p1.toJson() + ", p2: " + p2.toJson() + ", p3: " + p3.toJson() + " }";
    }

    public String toString() {
        return "triangle[" + p1.toString() + "," + p2.toString() + "," + p3.toString() + "]";
    }

    private Point p1;
    private Point p2;
    private Point p3;

}
