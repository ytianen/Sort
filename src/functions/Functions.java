package functions;

public class Functions {
	public int[] sorted;
	int swaptemp;
	
	public int[] swap(int[] data, int index1, int index2){
		swaptemp=data[index1];
		data[index1]=data[index2];
		data[index2]=swaptemp;
		return data;
	}
	
	
	public int[] BubbleSort(int[] data){
		sorted = new int[data.length];
		int min= data[0];
		System.out.println("Data length is:"+data.length);
		
		for(int j=0; j<data.length;j++){
			for(int i = 0; i<data.length;i++){
				if(min<data[i]){
					min=data[i];}
				System.out.println("Min is:"+min);
			}
			sorted[j]=min;
		}
		return sorted;
	}
	
	public int[] InsertionSort(int[] data){
		sorted =new int[data.length];
		
		for(int i=1; i<data.length; i++){
			for(int j=0;j<i;j++){
				while(data[j]<data[j-1]){
				swap(data,j,j-1);
				}
			}
			
		}
		
		return data;
	}
}
