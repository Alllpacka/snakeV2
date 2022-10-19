package at.htlhl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testEquals() {
        Point firstPoint = new Point(8, 8);
        Point secondPoint = new Point(8, 8);
        assertEquals(true, firstPoint.equals(secondPoint));

        firstPoint = new Point(8, 8);
        secondPoint = firstPoint;
        assertEquals(true, firstPoint.equals(secondPoint));

        firstPoint = new Point(8, 8);
        secondPoint = new Point(8, 7);
        assertEquals(false, firstPoint.equals(secondPoint));

        firstPoint = new Point(8, 8);
        java.awt.Point awtPoint = new java.awt.Point(8, 8);
        assertEquals(false, firstPoint.equals(awtPoint));
    }
}