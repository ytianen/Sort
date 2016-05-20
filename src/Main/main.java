package Main;

import java.sql.Date;
import java.util.Random;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.emitter.EmitterException;

import functions.Functions;

public class main {
	public int ArraySize = 10;
	public int range = 100;
	
	public Functions function;
	public long starttime=0;
	public long endtime=0;
	public Random rand =new Random();
	public int[] data= new int[ArraySize];
	public int[] dataCopy;
	public int count;
	@BeforeTest
	public void main() {
		System.out.println("Raw Data generating " +ArraySize+" numbers.");
		for(int i=0;i<ArraySize;i++){
			data[i]=rand.nextInt(range);
			System.out.print(data[i]+",");
		}
		System.out.println("\nRaw Data generated.\n");
	}

	@BeforeMethod
	public void setup(){
		dataCopy= data;
		starttime= System.currentTimeMillis();
	}
	@AfterMethod
	public void finish(){
		endtime=System.currentTimeMillis();
		try{
			for(int i=0;i<ArraySize-1;i++){
				//System.out.print(dataCopy[i]+",");
				Assert.assertTrue(dataCopy[i]<=dataCopy[i+1]);
			}
			//System.out.println("This array is sorted");
		}
		catch(Exception e){
			System.out.println("The array is not sorted");
		}
		
		System.out.println("Time used: "+ (endtime-starttime)+" ms\n" );

	}

	@Test
	public void BubbleSorting(){
		System.out.println("Bubble sort");
		BubbleSort(dataCopy);
	}
	@Test
	public void InsertionSorting(){
		System.out.println("Insertion sort");
		InsertionSort(dataCopy);
	}
	@Test
	public void SelectionSorting(){
		System.out.println("Selection sort");
		SelectionSort(dataCopy);
	}
	
	@Test
	public void RadixSorting(){
		System.out.println("RadixSort");
		RadixSort(dataCopy);
	}
	
	@Test
	public void BothwaySorting(){
		System.out.println("BothwaySort");
		BothwaySort(dataCopy);
	}
	@Test
	public void QuickSorting(){
		System.out.println("Quick sort");
		QuickSort(dataCopy,0,dataCopy.length-1);
	}

	public void swap(int[] data, int index1, int index2){
		//System.out.println("data["+index1+"] and ["+index2+"] will swapped");
		int swaptemp=data[index1];
		data[index1]=data[index2];
		data[index2]=swaptemp;
		//System.out.println("data["+index1+"] and ["+index2+"] has swapped");
	}

	public void BubbleSort(int[] data){
		//System.out.println("Data length is:"+data.length);
		for(int i=0; i<data.length;i++){
			for(int j=0; j<data.length-1;j++){
				if(data[j]>data[j+1]){
					swap(data,j,j+1);
				}
			}
		}
	}

	public void InsertionSort(int []data){
		int j;
		for(int i=1;i<data.length;i++){
			j=i;
			while(j<0 && data[j-1]>data[j]){
				swap(data,j,j-1);
				j--;
			}
		}

	}

	public void QuickSort(int[]data, int low, int high){
		if(low<high){
			int p= partition(data,low,high);
			QuickSort(data,p+1,high);
			QuickSort(data,low,p-1);
		}
	}
	public int partition(int[]data,int low,int high){
		int pivot = data[high];
		count=low;
		for(int j=low;j<high-1;j++){
			if(data[j]<=pivot){
				swap(data,j,count);
				count++;
			}	
		}	
		//System.out.println("High:"+ high+" Low:"+low+" Data.length="+data.length+" pivot:"+pivot+" count:"+count);
		return count;
	}

	public void SelectionSort(int []data){
		int localMin=0;
		for(int i=0;i<data.length;i++){
			localMin=i;
			for(int j=i;j<data.length;j++){				
				if(data[j]<data[i]){
					localMin=j;
				}
			}
			swap(data,i,localMin);
		}
	}
	
	public void RadixSort(int []data){
		int max=data[0], exp=1, n=data.length,i;
		int [] Bucket= new int[n];
		//find max in the data
		for(i=0;i<n;i++){
			if(data[i]>max){
				max=data[i];
			}
		}	
		while(max/exp>0){
			int [] BucketTemp = new int[10];
			for(i=0;i<n;i++){
				BucketTemp[(data[i]/exp)%10]++;
			}
			for(i=1;i<10;i++){
				BucketTemp[i]+=BucketTemp[i-1];
			}
			for (i = n - 1; i > 0; i--)
				 Bucket[--BucketTemp[(data[i] / exp) % 10]] = data[i];
	            for (i = 0; i < n; i++)
	                data[i] = Bucket[i];
	         
	    	
			exp =exp* 10;
		}
	}

	public void BothwaySort(int []data){
		int n=data.length;
		int max=0,min=0;
		for(int i=0;i<n;i++){
			max=n-1;
			min=i+1;
			for(int j=i;j<n;j++){
				if(data[max]<data[j]){
					max=j;
				}
				if(data[min]>data[j]){
					min=j;
				}
				//System.out.println("Max index:"+max + " Min index:"+min);
			}
		swap(data,i,min);
		swap(data,n-1,max);
		n--;
		
		}
		for(int i=0;i<ArraySize-1;i++)
			System.out.print(dataCopy[i]+",");
	}
}
