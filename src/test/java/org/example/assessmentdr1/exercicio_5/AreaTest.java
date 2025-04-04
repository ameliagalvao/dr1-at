package org.example.assessmentdr1.exercicio_5;

import static org.junit.jupiter.api.Assertions.*;

import exercicio_5.Area;
import org.junit.jupiter.api.Test;

public class AreaTest {

    @Test
    void testSurfaceAreaCube() {
        assertEquals(6 * 4 * 4, Area.surfaceAreaCube(4), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCube(0));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCube(-2));
    }

    @Test
    void testSurfaceAreaSphere() {
        assertEquals(4 * Math.PI * 4 * 4, Area.surfaceAreaSphere(4), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSphere(0));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSphere(-1));
    }

    @Test
    void testSurfaceAreaRectangle() {
        assertEquals(20.0, Area.surfaceAreaRectangle(4, 5), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaRectangle(0, 5));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaRectangle(4, 0));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaRectangle(-1, 2));
    }

    @Test
    void testSurfaceAreaCylinder() {
        double expected = 2 * (Math.PI * 3 * 3 + Math.PI * 3 * 7);
        assertEquals(expected, Area.surfaceAreaCylinder(3, 7), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCylinder(0, 5));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCylinder(3, -1));
    }

    @Test
    void testSurfaceAreaSquare() {
        assertEquals(16.0, Area.surfaceAreaSquare(4), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSquare(0));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSquare(-5));
    }

    @Test
    void testSurfaceAreaTriangle() {
        assertEquals(0.5 * 4 * 6, Area.surfaceAreaTriangle(4, 6), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTriangle(0, 6));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTriangle(5, 0));
    }

    @Test
    void testSurfaceAreaParallelogram() {
        assertEquals(5 * 3, Area.surfaceAreaParallelogram(5, 3), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaParallelogram(0, 3));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaParallelogram(5, -1));
    }

    @Test
    void testSurfaceAreaTrapezium() {
        double expected = (4 + 6) * 5 / 2.0;
        assertEquals(expected, Area.surfaceAreaTrapezium(4, 6, 5), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(0, 6, 5));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(4, -2, 5));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(4, 6, 0));
    }

    @Test
    void testSurfaceAreaCircle() {
        assertEquals(Math.PI * 3 * 3, Area.surfaceAreaCircle(3), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCircle(0));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCircle(-3));
    }

    @Test
    void testSurfaceAreaHemisphere() {
        assertEquals(3 * Math.PI * 2 * 2, Area.surfaceAreaHemisphere(2), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaHemisphere(0));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaHemisphere(-2));
    }

    @Test
    void testSurfaceAreaCone() {
        double expected = Math.PI * 3 * (3 + Math.sqrt(4 * 4 + 3 * 3));
        assertEquals(expected, Area.surfaceAreaCone(3, 4), 1e-6);
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCone(0, 4));
        assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCone(3, -2));
    }
}

