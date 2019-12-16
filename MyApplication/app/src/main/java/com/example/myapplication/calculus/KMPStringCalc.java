package com.example.myapplication.calculus;
// JAVA program for implementation of KMP pattern
// searching algorithm
public class KMPStringCalc {

    public static int KMPSearchWithNumbers(double[] pat, double[] txt, double epsilon)
    {
        int M = pat.length;
        int N = txt.length;

        int lps[] = computeLPSArrayWithNumbers(pat, M, 0.2);

        int j = 0;
        int i = 0;
        while (i < N) {
            if (Math.abs(pat[j] - txt[i]) < epsilon) {
                j++;
                i++;
            }
            if (j == M) {
                   return (i - j);
            }else if (i < N && (Math.abs(pat[j] - txt[i])) > epsilon ) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return -1;
    }


    private static int[] computeLPSArrayWithNumbers(double[] pat, int M, double epsilon) {
        int lps[] = new int[M];
        lps[0] = 0;

        int len = 0;
        int i = 1;

        while (i < M) {
            if ( Math.abs(pat[i] - pat[len]) < epsilon) {
                len++;
                lps[i] = len;
                i++;
            }else{
                if (len != 0) {
                    len = lps[len - 1];
                }else{
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
