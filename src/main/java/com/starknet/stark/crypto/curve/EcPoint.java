package com.starknet.stark.crypto.curve;


public class EcPoint {
    private Felt x;
    private Felt y;
    private boolean infinity;
    public EcPoint(Felt x,Felt y){
        this.x = x;
        this.y = y;
    }

    public EcPoint sub(EcPoint rhs){
        assert this.x != rhs.x: "x values should be different for arbitrary points";
        // To sum two points, one should draw a straight line containing these points, find the
        // third point in the intersection of the line and the curve, and then negate the y coordinate.
        // Notice that if x_1 = x_2 then either they are the same point or their sum is infinity. This
        // function doesn't deal with these cases. The straight line is given by the equation:
        // y = slope * x + y_intercept. The x coordinate of the third point is found by solving the system
        // of equations:

        // y = slope * x + y_intercept
        // y^2 = x^3 + alpha * x + beta

        // These equations yield:
        // (slope * x + y_intercept)^2 = x^3 + alpha * x + beta
        // ==> x^3 - slope^2 * x^2 + (alpha  - 2 * slope * y_intercept) * x + (beta - y_intercept^2) = 0

        // This is a monic polynomial in x whose roots are exactly the x coordinates of the three
        // intersection points of the line with the curve. Thus it is equal to the polynomial:
        // (x - x_1) * (x - x_2) * (x - x_3)
        // where x1, x2, x3 are the x coordinates of those points.
        // Notice that the equality of the coefficient of the x^2 term yields:
        // slope^2 = x_1 + x_2 + x_3.
        Felt slope = this.y.sub(rhs.y)  .divide (this.x.sub(rhs.x) );
        Felt x3 = slope .mul(slope) .sub(this.x) .sub(rhs.x) ;
        Felt y3 = slope .mul (this.x .sub(x3) ) .sub(this.y) ;
        return new EcPoint(x3,y3);
    }
    public EcPoint GetPointFromX(Felt x,Felt alpha,Felt beta){
        Felt y_squared = x.mul(x).mul(x) .add(alpha.mul(x))  .add(beta) ;
        return new EcPoint(x,y_squared);
    }
}
