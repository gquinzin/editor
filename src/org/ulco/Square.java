package org.ulco;

public class Square extends Rectangle {
    public Square(Point center, double length) {
        this.m_origin = center;
        this.m_length = length;
    }

    public Square() {
        this.m_origin = new Point(0,0);
        this.m_length = 0;
    }

    public void initObject(String json) {
        String str = json.replaceAll("\\s+","");
        int centerIndex = str.indexOf("center");
        int lengthIndex = str.indexOf("length");
        int endIndex = str.lastIndexOf("}");

        this.m_origin = new Point(str.substring(centerIndex + 7, lengthIndex - 1));
        this.m_length = Double.parseDouble(str.substring(lengthIndex + 7, endIndex));
    }

    public GraphicsObject copy() {
        return new Square(m_origin.copy(), m_length);
    }

    public Point getOrigin() { return m_origin; }

    void move(Point delta) { m_origin.move(delta); }

    public String toJson() {
        return "{ type: square, center: " + m_origin.toJson() + ", length: " + this.m_length + " }";
    }

    public String toString() {
        return "square[" + m_origin.toString() + "," + m_length + "]";
    }

    private Point m_origin;
    private double m_length;
}
