package com.chenhp.sparsearray;

import java.io.*;

/**
 * @author chenhp
 * @Date: 2020/11/23/15:32
 */
public class SparseArray {

	public static void main(String[] args) {
		//创建一个原始的二维数组 11*11
		//0: 表示没有棋子  1表示黑子  2表示篮子
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		//输出原始的二维数组
		System.out.println("原始的二维数组");
		for (int[] row : chessArr1) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}


		//将二维数组转换成稀疏数组
		//1.先遍历二维数组  得到非0数据的个数
		int sum = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr1[i][j] != 0) {
					sum++;
				}
			}
		}

		//2.创建对应的稀疏数组
		int sparseArr[][] = new int[sum + 1][3];
		//给稀疏数组赋值
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;

		//遍历二维数组  将非0值存放到稀疏数组
		int count = 0;//count记录第几个非0数据
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr1[i][j] != 0) {
					++count;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr1[i][j];
				}
			}
		}

		System.out.println("得到的稀疏数组");
		for (int[] row : sparseArr) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}

		//稀疏数组恢复二维原始数组
		int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}

		System.out.println("恢复的二维数组");
		for (int[] row : chessArr2) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}


		File file = new File("D://a.txt");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
			for (int[] row : sparseArr) {
				for (int data : row) {
					bw.write(data + "\t");
				}
				bw.write("\n");
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
