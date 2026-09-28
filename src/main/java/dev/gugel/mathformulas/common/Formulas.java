package dev.gugel.mathformulas.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public final class Formulas {

    private Formulas() {
        // This class should not be instantiated
    }

    // --- Rectangle ---
    /**
     *
     * @param a length
     * @param b width
     * @return area of a rectangle
     */
    public static Double calculateRectangleArea( Double a, Double b ) {
        if( a == null ) return null;
        if( b == null ) return null;

        return a * b;
    }

    /**
     *
     * @param a length
     * @param b width
     * @return perimeter of a rectangle
     */
    public static Double calculateRectanglePerimeter( Double a, Double b ) {
        if( a == null ) return null;
        if( b == null ) return null;

        return 2 * a + 2 * b;
    }

    // --- Triangle ---
    /**
     * Heron's formula in the numerically stable form by W. Kahan: the sides are sorted so that
     * x >= y >= z, and the brackets must stay as written. The sides are first scaled by a power of two,
     * which is exact, so the result is unchanged but the product under the root cannot overflow.
     * The sides must form a triangle (see isTriangle, checked by TriangleInputValidation),
     * otherwise the result is NaN.
     *
     * @param a side a
     * @param b side b
     * @param c side c
     * @return area of a triangle
     */
    public static Double calculateTriangleArea( Double a, Double b, Double c ) {
        if( a == null ) return null;
        if( b == null ) return null;
        if( c == null ) return null;

        double[] sides = { a, b, c };
        Arrays.sort( sides );
        int exponent = Math.getExponent( sides[2] );
        double x = Math.scalb( sides[2], -exponent );
        double y = Math.scalb( sides[1], -exponent );
        double z = Math.scalb( sides[0], -exponent );

        double area = Math.sqrt( ( x + ( y + z ) ) * ( z - ( x - y ) ) * ( z + ( x - y ) ) * ( x + ( y - z ) ) ) / 4;
        return Math.scalb( area, 2 * exponent );
    }

    /**
     * Triangle inequality in Kahan's form: with the sides sorted so that x >= y >= z, they form a
     * triangle exactly when z - (x - y) > 0. Unlike a + b > c on rounded sums, this also accepts
     * thin triangles such as (1e16, 1, 1e16), and it is the factor calculateTriangleArea takes the root of.
     *
     * @param a side a
     * @param b side b
     * @param c side c
     * @return true if the sides form a (non-degenerate) triangle
     */
    public static boolean isTriangle( double a, double b, double c ) {
        double[] sides = { a, b, c };
        Arrays.sort( sides );

        return sides[0] - ( sides[2] - sides[1] ) > 0;
    }

    /**
     *
     * @param a side a
     * @param b side b
     * @param c side c
     * @return perimeter of a triangle
     */
    public static Double calculateTrianglePerimeter( Double a, Double b, Double c ) {
        if( a == null ) return null;
        if( b == null ) return null;
        if( c == null ) return null;

        return a + b + c;
    }
}
