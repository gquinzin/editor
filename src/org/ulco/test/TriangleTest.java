package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.Triangle;
import org.ulco.GraphicsObject;
import org.ulco.Point;

public class TriangleTest extends TestCase {

    public void testType() throws Exception {
        Triangle t = new Triangle(new Point(0, 0),new Point(10, 2),new Point(5, 15));

        assertTrue(t instanceof Triangle);
        assertTrue(t instanceof GraphicsObject);
    }

    public void testJson() throws Exception {
        Triangle t = new Triangle(new Point(0, 0),new Point(10, 2),new Point(5, 15));

        assertEquals(t.toJson(), "{ type: triangle, p1: { type: point, x: 0.0, y: 0.0 }, p2: { type: point, x: 10.0, y: 2.0 }, p3: { type: point, x: 5.0, y: 15.0 } }");
    }

    public void testCopy() throws Exception {
        Triangle t = new Triangle(new Point(0, 0),new Point(10, 2),new Point(5, 15));

        assertEquals(t.toJson(), t.copy().toJson());
    }
}