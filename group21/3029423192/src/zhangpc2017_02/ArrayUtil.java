package zhangpc2017_02;

import java.util.Arrays;


public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param pengchengzhang
	 * @return
	 */
	public ArrayUtil(){
		
	}
	public void reverseArray(int[] origin){
		int temp;
		for(int start = 0,end = origin.length-1;start<end;start++,end--){
			temp = origin[start];
			origin[start] = origin[end];
			origin[end] = temp;
		}
		//遍历输出
		for(int x = 0;x<origin.length;x++){
			System.out.print(" "+origin[x]);
		}
		
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param pengchengzhang
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int x;
		int z = 0; 
		int num = 0;
		for(x = 0;x<oldArray.length;x++){
			if(oldArray[x]/1!=0){
				num++;
			}
		}
		int[] newArray = new int[num];
		for(x=0;x<oldArray.length;x++){
				if(oldArray[x]!=0){
				newArray[z] = oldArray[x];
				z++;
			}
		}
		//System.out.println(Arrays.toString(newArray));
		return newArray;	
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int z = 0;
		int[] array3 = new int[array1.length+array2.length];
		//将两个数组放入一个大数组中
		for(int x = 0;x<array1.length;x++){
			array3[z]=array1[x];
			z++;
		}
		for(int y = 0;y<array2.length;y++){
			array3[z]=array2[y];
			z++;
		}
		//将两个元素进行比较
		for(int x = 0;x<array3.length;x++){
			for(int y = x+1;y<array3.length;y++){
				if(array3[x]-array3[y]==0){
					array3[y]=0;
				}
			}
		}
	
		//快排
		removeZero(array3);
		Arrays.sort(array3);
		System.out.print(Arrays.toString(array3));

		return array3;
	}
	
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		int num = 0;
		int[] newArray = new int[oldArray.length+size];
		for(int x=0;x<oldArray.length;x++){
			newArray[x] = oldArray[x];
			num++;	
		}
		while(num++<=size){
			newArray[num] = 0;
		}
		System.out.println(Arrays.toString(newArray));
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		int[] fibonacci = new int[1000];
		fibonacci[0] = 1;
		fibonacci[1] = 1;
		fibonacci[2] = 2;
		int x;
		if(max == 1){
			return null;
		}
		if(max != 2){
			for(x =2;fibonacci[x-1]<max;x++){
				fibonacci[x] = fibonacci[x-1]+fibonacci[x-2];
			}
			//否则多一个
			fibonacci[x-1] = 0;
	}
		System.out.println(Arrays.toString(removeZero(fibonacci)));
		return fibonacci;
}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int z = 0;
		int[] getPrimes = new int[1000];
		if(max < 2){
			return null;
		}
		
		for(int i = 2; i<max; i++){
			for(int y = 2;y<=Math.sqrt(i)+1;y++){
				if(i%y==0){
					break;
				}
				if(y>=Math.sqrt(i)){
					getPrimes[z] = i;
					z++;
				}
			}
			
		}
		System.out.println(Arrays.toString(removeZero(getPrimes)));
		return getPrimes;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int sum = 0;
		int z = 0;
		int x = 1;
		int[] getPerfectNumbers = new int[1000];
		while(x<max){
			for(int y =1;y<x/2+1;y++){
				if(x%y==0){
					sum += y;
				}
			}
			if(sum == x){
				getPerfectNumbers[z]=x;
				z++;
			}
			//sum要置0
			sum=0;
			x++;
		}
		System.out.println(Arrays.toString(removeZero(getPerfectNumbers)));
		return getPerfectNumbers;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		String s1 = null;
		for(int x = 0;x<array.length;x++){
			if(x==0){
				s1=Integer.toString(array[x])+seperator;
			}
			if(x<array.length-1 && x!=0){
				s1 =s1+ Integer.toString(array[x])+seperator;
			}else if(x == array.length-1){
				s1 = s1+Integer.toString(array[x]);
			}
		}
		System.out.println(s1);
		return s1;
	}
}