/**
 * Created by zifeiyu on 6/10/17.
 */
import java.util.*;
public class Devide {
    public static void main (String[] args) {
        System.out.println("1.算法开始");
        System.out.println("2.初始化数据");
        Devide devide = new Devide();
        System.out.println(devide.num);
        System.out.println("3.将数组按照x坐标升序排列");
        Collections.sort(devide.num, new Comparator<Point>() {
            public int compare(Point arg0, Point arg1) {
                return arg0.x > arg1.x ? 1 : -1;
            }
        });
        System.out.println(devide.num);
        System.out.println("4.寻找最近点对");
        Result result = devide.nearestPair(0, devide.num.size() - 1);
        System.out.println("5.打印结果");
        System.out.println(result);
    }

    ArrayList<Point> num;

    public Devide() {
        this.num = new ArrayList<Point>();
        this.num.add(new Point(0, 1));
        this.num.add(new Point(3, 2));
        this.num.add(new Point(4, 3));
        this.num.add(new Point(5, 1));
        this.num.add(new Point(1, 2));
        this.num.add(new Point(2, 1));
        this.num.add(new Point(6, 2));
        this.num.add(new Point(7, 2));
        this.num.add(new Point(8, 3));
        this.num.add(new Point(4, 5));
        this.num.add(new Point(9, 0));
        this.num.add(new Point(6, 4));
    }

    //声明点类
    class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }

    //求任意两点间的距离，参数为两个点
    public static double dis(Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }

    //声明结果类
    class Result {
        Point a = null;
        Point b = null;
        Double minDist = Double.POSITIVE_INFINITY;//初始为正无穷大

        public String toString() {
            return "最近点对为: " + this.a + ", " + this.b + "。 最近距离为：" + this.minDist;
        }
    }

    //求最近距离,参数为数组下标
    private Result nearestPair(int left, int right) {
        Result result = new Result();
        //算法终止判断
        if (left == right) {
            return result;
        }
        if ((left + 1) == right) {
            Point leftPoint = num.get(left);
            Point rightPoint = num.get(right);
            double finalDist = dis(leftPoint, rightPoint);
            if (finalDist < result.minDist) {
                result.a = leftPoint;
                result.b = rightPoint;
            }
            return result;
        }
        //1.以x坐标为准，取中点
        int mid = (int) Math.floor((left + right) / 2);
        //2.递归找两边距离最小的点对
        Result leftDis = nearestPair(left, mid);
        Result rightDis = nearestPair(mid + 1, right);
        result = leftDis.minDist > rightDis.minDist ? rightDis : leftDis;
        //3.取中间带的点
        ArrayList<Point> leftArray = new ArrayList<Point>();
        ArrayList<Point> rightArray = new ArrayList<Point>();
        for (int i = left; i <= mid; i++) {
            if(Math.abs(num.get(mid).x - num.get(i).x) < result.minDist) {
                leftArray.add(num.get(i));
            }
        }
        for (int i = mid + 1; i <= right; i++) {
            if(Math.abs(num.get(mid).x - num.get(i).x) < result.minDist) {
                rightArray.add(num.get(i));
            }
        }
        //如果左右两个数组中有一个为空，则算法终止
        if (leftArray.isEmpty() || rightArray.isEmpty()) {
            return result;
        }
        //4.将右侧数组按y坐标升序排列
        Collections.sort(rightArray, new Comparator<Point>() {
            public int compare(Point arg0, Point arg1) {
                return arg0.y > arg1.y ? 1 : -1;
            }
        });
        //5.从中间区域的左边找右边与其距离最近的点，根据鸽巢原理，最多找六个
        for (int i = 0; i < leftArray.size(); i++) {
            for (int j = 0; j < rightArray.size() && j < 6; j++) {
                double tempDis = dis(leftArray.get(i), rightArray.get(j));
                if(tempDis < result.minDist) {
                    result.a = leftArray.get(i);
                    result.b = rightArray.get(j);
                    result.minDist = tempDis;
                }
            }
        }
        return result;
    }
}
