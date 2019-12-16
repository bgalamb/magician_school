package com.example.myapplication.calculus;

public class Normalizer {

    public static double calc_min(double[] data)	// To calculate the minimum age from dataset
    {
        int i,j = 0;
        double min = 0;
        min = data[0];
        for(i=1;i<data.length;i++)
        {
            if(data[i] < min )
            {
                min = data[i];
            }
        }
        return min;
    }

    public static double calc_max(double[] data)	// To calculate the maximum age from dataset
    {
        int i =0 ;
        double max = 0;
        max = data[0];
        for(i=0;i<data.length;i++)
        {
            if(data[i] > max)
            {
                max = data[i];
            }
        }
        return max;
    }

    public static void multiplyElements(double by, double[] array){
        for(int i = 0;i < array.length; i++){
            array[i] *= by;
        }
    }

    // Function to perform min_max normalization
    public static double[] min_max(double[] data,double min,double max)
    {
        double new_min = 0;
        double new_max = 1;
        double[] retVal =  new double[data.length];

        double v1;

        for(int i=0;i<data.length;i++)
        {
            v1=(((data[i]-min)/(max-min))*(new_max-new_min))+new_min;
            retVal[i]=v1;
        }

        return retVal;

    }


}
