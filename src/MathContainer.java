public class MathContainer {
    public static double closestToZero(double... list) {
        int closestIndex = 0;
        for (int i = 0; i < list.length; ++i) {
            if (Math.abs(list[i]) < Math.abs(list[closestIndex])) {
                closestIndex = i;
            }
        }
        return list[closestIndex];
    }
}
