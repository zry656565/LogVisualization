package com.LogVisualization.Servlet;
import org.json.*;
import java.io.*;


public class Prediction {
	public double Predict(String inputString) throws JSONException  {
		JSONArray input=new JSONArray(inputString);
		int size_x=input.length();
		int size_y=input.length();
		double[] x_ax=new double[size_x];
		double[] y_ax=new double[size_y];
		for(int i=0;i<size_x;i++) {
			x_ax[i]=input.getJSONObject(i).getDouble("x");
			y_ax[i]=input.getJSONObject(i).getDouble("y");
		}
		double x_avg=0.0;
		double y_avg=0.0;
		for(int i=0;i<size_x;i++)
			x_avg+=x_ax[i];
		x_avg/=(double)size_x;
		for(int i=0;i<size_y;i++)
			y_avg+=y_ax[i];
		y_avg/=(double)size_y;
		double a,b;
		double temp1=0.0;
		double temp2=0.0;
		for(int i=0;i<size_x;i++) {
			temp1+=(double)(x_ax[i]-x_avg)*(double)(y_ax[i]-y_avg);
			temp2+=(double)((x_ax[i]-x_avg)*(x_ax[i]-x_avg));
		}
		a=temp1/temp2;
		b=y_avg-a*(double)x_avg;
		return a*(x_ax[size_x-1]+1)+b;
	}
}

