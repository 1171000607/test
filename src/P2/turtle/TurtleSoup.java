/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    
    public static void drawSquare(Turtle turtle, int sideLength) {
        int i;
        for (i=1;i<=4;i++) {
            turtle.forward(sideLength);
            turtle.turn(90.00);
        }
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        double angle;
        angle=(double)180.0-(double)360.0/sides;
        return angle;
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        int sides;
        angle=(double)180.0-angle;
        angle=360.0/angle;
        sides=(int)Math.round(angle);
        return sides;
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle;
        int i;
        angle=(double)180.0-calculateRegularPolygonAngle(sides);
        for (i=1;i<=sides;i++) {
            turtle.forward(sideLength);
            turtle.turn(angle);
        }
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,int targetX, int targetY) {
        double tmp,angle;
        int deltaX,deltaY;
        if (currentX==targetX) {
            if (currentY<targetY) angle=0.0;
            else angle=180.0;
        }
        else if (currentY==targetY) {
            if (currentX<targetX) angle=90.0;
            else angle=270.0;
        }
        else {
            deltaX=targetX-currentX;
            deltaY=targetY-currentY;
            tmp=(double)(deltaY/deltaX);
            angle=Math.atan(tmp);
            angle=Math.toDegrees(angle);
            if (tmp>0) {
               if (deltaX<0) angle+=180.0;
            }
            else {
               if (deltaX<0) angle+=180.0;
               else angle+=360.0;
            }
            angle=90.0-angle;
        }
        angle=angle-currentBearing;
        if (angle<0) angle+=360.0;
        return angle;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        int n,i,deltaX,deltaY;
        double tmp,angle,now,ans;
        List<Double> Ans=new ArrayList<>();
        n=xCoords.size();
        angle=90.0;
        for (i=1;i<n;i++) {
            now=angle;
            deltaX=xCoords.get(i)-xCoords.get(i-1);
            deltaY=yCoords.get(i)-yCoords.get(i-1);
            if (deltaX==0) {
                if (deltaY>0) angle=90.0;
                else angle=270.0;
            }
            else {
                tmp=(double)(deltaY/deltaX);
                angle=Math.atan(tmp);
                angle=Math.toDegrees(angle);
                if (tmp>0) {
                    if (deltaX<0) angle+=180.0;
                }
                else {
                    if (deltaX<0) angle+=180.0;
                    else angle+=360.0;
                }
            }
            ans=now-angle;
            if (ans<0) ans+=360.0;
            Ans.add(ans);
        }
        return Ans;
        //throw new RuntimeException("implement me!");
    }
    
    /**
     * Given a set of points, Compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
        Set<Point> ans = new HashSet<Point> ();
        Point now,now1,now2;
        
        int num1,num2;
        double x,y,x1,y1,x2,y2,tmp,dis1,dis2;
        Iterator<Point> p = points.iterator();
        if (points.size()<4) {
            return points;
        }
        Point Beg = new Point(1,1);
        num1=0;
        while(p.hasNext()) {
            now=p.next();
            if (num1==0) {
                Beg=now;
                num1++;
                continue;
            }
            x=now.x();
            y=now.y();
            if (y<Beg.y() || (y==Beg.y() && x>Beg.x())) Beg=now;
        }
        now=Beg;
        now1=Beg;
        while(true) {
            p = points.iterator();
            num2=0;
            while(p.hasNext()) {
                if (num2==0) {
                    now1=p.next();
                    num2++;
                    continue;
                }
                now2=p.next();
                x1=now1.x()-now.x();
                y1=now1.y()-now.y();
                x2=now2.x()-now.x();
                y2=now2.y()-now.y();
                tmp=x1*y2-x2*y1;
                if (tmp<0) now1=now2;
                else if (tmp==0) {
                    dis1=x1*x1+y1*y1;
                    dis2=x2*x2+y2*y2;
                    if (dis1<dis2) now1=now2;
                }
            }
            num2++;
            now=now1;
            ans.add(now);
            if (now==Beg) break;
        }
        return ans;
        //throw new RuntimeException("implement me!");*/
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        int i,j,k,len;
        for (k=1;k<=4;k++) {
            if (k==2) turtle.color(PenColor.PINK);
            if (k==1) turtle.color(PenColor.ORANGE);
            if (k==3) turtle.color(PenColor.CYAN);
            if (k==4) turtle.color(PenColor.GREEN);
            len=5;
            for(i=1;i<=36;i++) {
                for(j=1;j<=4;j++) {
                    turtle.forward(len);
                    turtle.turn(90);
                }
                turtle.turn(10);
                len+=5;
            }
            turtle.turn(90);
        }
        //throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        //drawRegularPolygon(turtle,5,40);
        drawPersonalArt(turtle);
        //draw the window
        turtle.draw();
    }

}
