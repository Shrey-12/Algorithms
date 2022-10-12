
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairOfPoints {
    public static void main(String[] args) {
        Point[] P = new Point[] {
                new Point(1, 1),
                new Point(7, 30),
                new Point(11, 50),
                new Point(5, 19),
                new Point(12, 10),
                new Point(3, 4)

        };
        System.out.println("The smallest distance is " + (Point.closest(P, P.length)));
    }
}


class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static class Result{
        float distance;
        Point p1;
        Point p2;
        public Result(float distance,Point p1,Point p2){
            System.out.println(p1.x+p1.y);
            System.out.println(p2.x+p2.y);
            System.out.println(distance);
        }
    }


    public static float dist(Point p1, Point p2) {
        return (float) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }

    //brute force method to return minimum distance between 2 points
    public static float findMinDist(Point[] P, int n) {
        float min = Float.MAX_VALUE;
        float currMin = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                currMin = dist(P[i], P[j]);
                if (currMin < min) {
                    min = currMin;
                }
            }
        }
        return min;
    }

    //points of strip are sorted according to y coordinates having upper bound of distance d.
    public static float stripClosest(Point[] strip, int size, float d) {
        float min = d;

        //Arrays.sort(strip, 0, size, new PointYComparator());

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                if (dist(strip[i], strip[j]) < min) {
                    min = dist(strip[i], strip[j]);
                }
            }
        }
        return min;
    }

    //here Px and Py are sorted
    public static float findClosest(Point[] Px, Point[] Py, int n) {

        if (n <= 3) {
            return findMinDist(Px, n);
        }

        //divide the points in sorted y about vertical midpoint line
        int mid = n / 2;
        Point midPoint = Px[mid];
        Point[] ySortedLeft = new Point[mid + 2];
        Point[] ySortedRight = new Point[n - mid - 1];
        int left = 0, right = 0;

        for (int i = 0; i < n; i++) {
            if (Py[i].x <= midPoint.x)
                ySortedLeft[left++] = Py[i];
            else
                ySortedRight[right++] = Py[i];
        }

        //distance dl on left and dr on right of middle line
        float dl = findClosest(Px, Py, mid);
        float dr = findClosest(Px, Py, n - mid);
        float d = Math.min(dl, dr);

        //array strip that contains points closer than d
        Point[] strip = new Point[n];

        int j = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(Py[i].x - midPoint.x) < d) {
                strip[j] = Py[i];
                j++;
            }
        }

        float res=Math.min(d, stripClosest(strip, j, d));
        return res;
    }

    public static float closest(Point[] P, int n) {
        Point[] xSorted = new Point[n];
        Point[] ySorted = new Point[n];
        for (int i = 0; i < n; i++) {
            xSorted[i] = P[i];
            ySorted[i] = P[i];
        }
        Arrays.sort(xSorted, 0, n, new PointXComparator());
        Arrays.sort(ySorted, 0, n, new PointYComparator());

        return findClosest(xSorted, ySorted, n);
    }

}

//utility functions needed for Arrays.sort
class PointXComparator implements Comparator<Point> {

    @Override
    public int compare(Point pointA, Point pointB) {
        return Integer.compare(pointA.x, pointB.x);
    }

}

class PointYComparator implements Comparator<Point> {
    @Override
    public int compare(Point pointA, Point pointB) {
        return Integer.compare(pointA.y, pointB.y);
    }
}

/* to do list:
1. points mai result se return kar sakti hu lekin p1 p2 ke x and y kaise aenge, as functions are just returning the distance value
2. ascending order mai k points?? array? calling function multiple times? what about the old points?
 */