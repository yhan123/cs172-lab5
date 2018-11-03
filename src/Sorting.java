/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class Sorting {// all sorting method

	public static int[] Arraysort(int [] arr) {//array sort 0
		Arrays.sort(arr);//use arrays.sort method
		return arr;
	}
	
	public static int[] Bubblesort(int [] arr) {//bubble sort 1
		int len=arr.length;
		for(int i=0; i<len-1; i++) {//get pos of first num
			for(int j=0; j<len-i-1; j++) {//get pos of second num
				if(arr[j]>arr[j+1]) {//compare and change position
					int n=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=n;
				}
			}
		}
		return arr;
	}
	
	public static int[] Selectionsort(int [] arr) {//selection sort 2
		int len=arr.length;
		for(int i=0; i<len-1;i++) {//get pos of first  number
			int minValue = i;//position of min number
			for(int j=i+1;j<len;j++){//get pos of second number (always behind first one)
		    	if(arr[j] < arr[minValue]){//compare
		    		minValue = j;
		    	}
		    	int n = arr[minValue]; //change position
		    arr[minValue] = arr[i]; 
		    arr[i] = n;
		    }
		}
		return arr; 
		
	}
	
	public static int[] InsertionSort(int []arr) {//insertion sort 3
		int len=arr.length;
		for(int i=0; i<len; i++) {//get pos of first num
			int n=arr[i];//get num at this position
			int j=i-1;//the pos of num before first one
			while(j>=0 && arr[j]>n) {//compare and change pos
				 arr[j+1]=arr[j]; 
	                j=j-1;
			}
			arr[j+1]=n;
		}
		return arr;
	}
	
	public static int[] MergeSort(int [] arr) {// merge sort 4
		if(arr.length<=1) {//length=1
			return arr;
		}
		int mid=arr.length/2;//divide array by 2
		int [] left=new int[mid];//left array
		int [] right;//right array
		if(arr.length%2==0) {//length is even
			right=new int[mid];
		}
		else {//length is odd
			right=new int[mid+1];
		}
		for(int i=0; i<mid; i++) {//set num to left array
			left[i]=arr[i];
		}
		for(int j=0; j<right.length; j++) {//set num to right array
			right[j]=arr[j+mid];
		}
		left=MergeSort(left);//repeat on left array
		right=MergeSort(right);//repeat on right array
		
		int [] fin= new int[arr.length];//set length for array fin
		fin=Merge(left, right);//combine
		
		return fin;
	}
	
	
	public static int[] Merge(int [] left, int[] right) {
		//compare all numbers and set to correct pos
		int l=left.length;
		int r=right.length;
		int [] fin=new int[l+r];
		int a = 0, b = 0, c = 0;
		while(a<l || b<r) {
			if(a<l && b<r) {
				if(left[a]<right[b]) {
					fin[c++]=left[a++];
				}else {
					fin[c++]=right[b++];
				}
			}
			else if(a<l) {
				fin[c++]=left[a++];
			}else if(b<r) {
				fin[c++]=right[b++];
			}
		}
		
		
		return fin;
	}
	

		public static int partition(int arr[], int first, int last) {
			int pivot=arr[last];//set the pivot be the last number of array
			int low=first-1;
			for(int j=first; j<last;j++) {//compare number
				if(arr[j]<=pivot) {
					low++;
				int n=arr[low];
				arr[low]=arr[j];
				arr[j]=n;
				}
			}
			int n=arr[low+1]; //change pos of numbers
	        arr[low+1]=arr[last]; 
	        arr[last]=n; 
	  
	        return low+1;
		}
		
		public static int[] QuickSort(int [] arr, int left, int right) {// quick sort 5
			if(left<right) {// check if in order
				int in=partition(arr,left, right);
				QuickSort(arr,left, in-1);//sort again on left
				QuickSort(arr,in+1, right);//sort again on right
			}
		return arr;
			
		}
	
 /**
     * 
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort 
     * 3 = Insertion Sort 
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
 * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException  { 
        In in = new In(args[0]); 
        int read=Integer.parseInt(args[1]); 
        String[] Sort = {"Array Sort", "Bubble Sort", "Selection Sort", "Insertion Sort", "Mergesort", "Quicksort"};
		// Storing file input in an array
        int[] a = in.readAllInts();//Initialize array a
        int[] b=Arraysort(a);//Initializearray b, b is sorted 
        int blen=b.length;
        int[] c=new int[blen];//Initializearray c
        int w=0;
        for (int i=blen-1; i>=0;i--) {//reverse b
          c[i]=b[w];
          w++;
        }
//        int d[]=b;//Initialize array d
//        Random rand = new Random();
//        for (int i = 0; i < b.length*0.1; i++) {// 0.1*length of sort
//            int ran1 = rand.nextInt(b.length);
//            int ran2 = rand.nextInt(b.length);
//            int temp = d[ran1];
//            d[ran1] = d[ran2];//change pos 
//            d[ran2] = temp;
//        }
        	PrintWriter wri = null;
     
        
        	
        int[][] arr= {a,b,c};
        int cou=0;//move through a to d
        
        // TODO: Generate 3 other arrays, b, c, d where
        // b contains sorted integers from a (You can use Java Arrays.sort() method)
        // c contains all integers stored in reverse order 
        // (you can have your own O(n) solution to get c from b
        // d contains almost sorted array 
        //(You can copy b to a and then perform (0.1 * d.length)  many swaps to acheive this.   
       
        //TODO: 
        // Read the second argument and based on input select the sorting algorithm
        //  * 0 = Arrays.sort() (Java Default)
        //  * 1 = Bubble Sort
        //  * 2 = Selection Sort 
        //  * 3 = Insertion Sort 
        //  * 4 = Mergesort
        //  * 5 = Quicksort
        //  Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp and record those. 
        // For runtime and priting, you can use the same code from Lab 4 as follows:
        
         // TODO: For each array, a, b, c, d: 
        
        for(int[] run : arr) {
        	Stopwatch timer = new Stopwatch();
     // TODO: Perform Sorting and store the result in an  array
        switch(read) {
        case 0:
        Arraysort(run);
        break;
        case 1:
        	Bubblesort(run);
        	break;
        case 2:
        	Selectionsort(run);
        	break;
        case 3:
        	InsertionSort(run);
        	break;
        case 4:
        MergeSort(run);
        	break;
        case 5:
        	QuickSort(run, 0, run.length-1);
        	break;
        }
        double time = timer.elapsedTimeMillis();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String netID = "xwang151 && ychen218";
        String algorithmUsed = Sort[read];
        //TODO: Replace with the array used 
        String arrayUsed = "";
        //String arrayUsed = " ";
        
        if (cou==0) {
            arrayUsed = "a";//set name
            try{// print number sorted to a.txt
                File file = new File("a.txt");
                wri=new PrintWriter(file);
                for(int i=0;i<a.length;i++){
                    wri.println(a[i]);//print sorted a
                }
                wri.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        if (cou == 1) {//same as above
            arrayUsed = "b";
            try{
                File file = new File("b.txt");
                wri = new PrintWriter(file);
                for(int i=0;i<b.length;i++){
                    wri.println(b[i]);
                }
                wri.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        if (cou == 2) {//same as above
            arrayUsed = "c";
            try{
                File file = new File("c.txt");
                wri = new PrintWriter(file);
                for(int i=0;i<c.length;i++){
                    wri.println(c[i]);
                }
                wri.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
//        if (cou == 3) {//same as above
//            arrayUsed = "d";
//            try{
//                File file = new File("d.txt");
//                wri = new PrintWriter(file);
//                for(int i=0;i<d.length;i++){
//                    wri.println(d[i]);
//                }
//                wri.close();
//            }
//            catch(IOException e) {
//                e.printStackTrace();
//            }
//        }
        
        
        StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
        cou++;//move to next array
        }
          // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
		
  } 
}