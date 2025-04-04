package org.example.assessmentdr1.exercicio_5;

import static org.junit.jupiter.api.Assertions.*;

import exercicio_5.Area;
import org.junit.jupiter.api.Test;

public class AreaTest {

    // CUBO
    @Test
    void testSurfaceAreaCube_ValidInput() {
        assertEquals(96.0, Area.surfaceAreaCube(4), 1e-6);
        assertEquals(6 * 2.5 * 2.5, Area.surfaceAreaCube(2.5), 1e-6);
    }

    @Test
    void testSurfaceAreaCube_InvalidInput() {
        assertEquals("Lado inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCube(0)).getMessage());
        assertEquals("Lado inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCube(-5)).getMessage());
        assertEquals("Lado inválido: não é um número válido.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCube(Double.NaN)).getMessage());
        assertEquals("Lado inválido: valor excede o limite suportado.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCube(Double.MAX_VALUE)).getMessage());
    }

    // ESFERA
    @Test
    void testSurfaceAreaSphere_ValidInput() {
        assertEquals(4 * Math.PI * 2.5 * 2.5, Area.surfaceAreaSphere(2.5), 1e-6);
    }

    @Test
    void testSurfaceAreaSphere_InvalidInput() {
        assertEquals("Raio inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSphere(0)).getMessage());
        assertEquals("Raio inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSphere(-1)).getMessage());
        assertEquals("Raio inválido: não é um número válido.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSphere(Double.NaN)).getMessage());
        assertEquals("Raio inválido: valor excede o limite suportado.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSphere(Double.MAX_VALUE)).getMessage());
    }

    // QUADRADO
    @Test
    void testSurfaceAreaSquare_ValidInput() {
        assertEquals(6.25, Area.surfaceAreaSquare(2.5), 1e-6);
    }

    @Test
    void testSurfaceAreaSquare_InvalidInput() {
        assertEquals("Lado inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSquare(0)).getMessage());
        assertEquals("Lado inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSquare(-5)).getMessage());
        assertEquals("Lado inválido: não é um número válido.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSquare(Double.NaN)).getMessage());
        assertEquals("Lado inválido: valor excede o limite suportado.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaSquare(Double.MAX_VALUE)).getMessage());
    }

    // CÍRCULO
    @Test
    void testSurfaceAreaCircle_ValidInput() {
        assertEquals(Math.PI * 2.5 * 2.5, Area.surfaceAreaCircle(2.5), 1e-6);
    }

    @Test
    void testSurfaceAreaCircle_InvalidInput() {
        assertEquals("Raio inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCircle(0)).getMessage());
        assertEquals("Raio inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCircle(-3)).getMessage());
        assertEquals("Raio inválido: não é um número válido.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCircle(Double.NaN)).getMessage());
        assertEquals("Raio inválido: valor excede o limite suportado.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCircle(Double.MAX_VALUE)).getMessage());
    }

    // HEMISFÉRIO
    @Test
    void testSurfaceAreaHemisphere_ValidInput() {
        assertEquals(3 * Math.PI * 2.5 * 2.5, Area.surfaceAreaHemisphere(2.5), 1e-6);
    }

    @Test
    void testSurfaceAreaHemisphere_InvalidInput() {
        assertEquals("Raio inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaHemisphere(0)).getMessage());
        assertEquals("Raio inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaHemisphere(-2)).getMessage());
        assertEquals("Raio inválido: não é um número válido.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaHemisphere(Double.NaN)).getMessage());
        assertEquals("Raio inválido: valor excede o limite suportado.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaHemisphere(Double.MAX_VALUE)).getMessage());
    }

    // RETÂNGULO
    @Test
    void testSurfaceAreaRectangle_ValidInput() {
        assertEquals(12.5, Area.surfaceAreaRectangle(2.5, 5), 1e-6);
    }

    @Test
    void testSurfaceAreaRectangle_InvalidInput() {
        assertEquals("Comprimento inválido: deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaRectangle(0, 5)).getMessage());
        assertEquals("Largura inválida: deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaRectangle(5, -2)).getMessage());
    }

    // TRIÂNGULO
    @Test
    void testSurfaceAreaTriangle_ValidInput() {
        assertEquals(0.5 * 4 * 2.5, Area.surfaceAreaTriangle(4, 2.5), 1e-6);
    }

    @Test
    void testSurfaceAreaTriangle_InvalidInput() {
        assertEquals("Base inválida: deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTriangle(0, 6)).getMessage());
        assertEquals("Altura inválida: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTriangle(5, 0)).getMessage());
    }

    // PARALELOGRAMO
    @Test
    void testSurfaceAreaParallelogram_ValidInput() {
        assertEquals(2.5 * 5, Area.surfaceAreaParallelogram(2.5, 5), 1e-6);
    }

    @Test
    void testSurfaceAreaParallelogram_InvalidInput() {
        assertEquals("Base inválida: deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaParallelogram(0, 5)).getMessage());
        assertEquals("Altura inválida: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaParallelogram(3, -2)).getMessage());
    }

    // TRAPÉZIO
    @Test
    void testSurfaceAreaTrapezium_ValidInput() {
        double expected = (2.5 + 3.5) * 4 / 2.0;
        assertEquals(expected, Area.surfaceAreaTrapezium(2.5, 3.5, 4), 1e-6);
    }

    @Test
    void testSurfaceAreaTrapezium_InvalidInput() {
        assertEquals("Base 1 inválida: deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(0, 6, 5)).getMessage());
        assertEquals("Base 2 inválida: deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(4, -2, 5)).getMessage());
        assertEquals("Altura inválida: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaTrapezium(4, 6, 0)).getMessage());
    }

    // CONE
    @Test
    void testSurfaceAreaCone_ValidInput() {
        double expected = Math.PI * 3 * (3 + Math.sqrt(4 * 4 + 3 * 3));
        assertEquals(expected, Area.surfaceAreaCone(3, 4), 1e-6);
    }

    @Test
    void testSurfaceAreaCone_InvalidInput() {
        assertEquals("Raio inválido: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCone(0, 4)).getMessage());
        assertEquals("Altura inválida: o valor deve ser maior que zero.",
                assertThrows(IllegalArgumentException.class, () -> Area.surfaceAreaCone(3, 0)).getMessage());
    }
}
