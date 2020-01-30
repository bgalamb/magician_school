package com.magic.wand.calculus;

public class Levenstein {

    public static int characterDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

    public static int numbericDistance2(double[] a, double[] b, double epsilon) {

        // i == 0
        int[] costs = new int[b.length + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length; i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length; j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), Math.abs(a[i - 1] - b[j - 1]) <= epsilon ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length];
    }

    public static int numbericDistance3(double[][] a, double[][] b, double epsilon) {
        double[] dimensionx = new double[a.length];
        double[] dimensiony = new double[a.length];
        double[] dimensionz = new double[a.length];

        double[] expdimensionx = new double[b.length];
        double[] expdimensiony = new double[b.length];
        double[] expdimensionz = new double[b.length];

        for (int i = 0; i < Math.max(a.length,b.length); i++) {
            if (i < a.length) {
                dimensionx[i] = a[i][0];
                dimensiony[i] = a[i][1];
                dimensionz[i] = a[i][2];
            }
            if (i < b.length) {
                expdimensionx[i] = b[i][0];
                expdimensiony[i] = b[i][1];
                expdimensionz[i] = b[i][2];
            }
        }

        int distx = numbericDistance2(dimensionx, expdimensionx, epsilon);
        int disty = numbericDistance2(dimensiony, expdimensiony, epsilon);
        int distz = numbericDistance2(dimensionz, expdimensionz, epsilon);

        return (int) Math.ceil((distx + disty + distz) / 3);

    }
}
