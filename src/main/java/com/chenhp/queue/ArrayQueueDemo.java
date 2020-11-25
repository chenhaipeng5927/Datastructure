package com.chenhp.queue;

import java.util.Scanner;

/**
 * @author chenhp
 * @Date: 2020/11/23/16:38
 */
public class ArrayQueueDemo {

	public static void main(String[] args) {
		ArrayQueue arrayQueue = new ArrayQueue(4);

		char key = ' ';//接收用户输入
		Scanner scanner = new Scanner(System.in);//
		boolean loop = true;
		//输出一个菜单
		while (loop){
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取出数据");
			System.out.println("h(head):查看队列头的数据");
			key = scanner.next().charAt(0);//接收一个字符
			switch (key){
				case 's':
					arrayQueue.showQueue();
					break;
				case 'a':
					System.out.println("输入一个数字");
					int value = scanner.nextInt();
					arrayQueue.addQueue(value);
					break;
				case 'g':
					try {
						int res = arrayQueue.getQueue();
						System.out.printf("取出的数据是%d\n",res);
					}catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'h':
					try {
						int peek = arrayQueue.peek();
						System.out.printf("队列头是是%d\n",peek);
					}catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'e':
					scanner.close();
					loop = false;
					break;
				default:
					break;
			}
		}
		System.out.println("程序已退出");
	}
}

//使用数组模拟队列 -编写一个arrayQueue类
class ArrayQueue {

	private int maxSize;//表示数组的最大容量

	private int front; //队列头

	private int rear; //队列尾

	private int[] arr;//存放数据的数组


	//创建队列的构造器
	public ArrayQueue(int arrMaxSize) {

		this.maxSize = arrMaxSize;

		this.front = -1;//指向队列头部 font是指向队列的前一个位置

		this.rear = -1; //指向队列尾部

		arr = new int[arrMaxSize];

	}


	//判断队列是否满
	public boolean isFull() {
		return rear == maxSize - 1;
	}

	//判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}

	//添加数据到队列
	public void addQueue(int n) {
		//判断队列是否满
		if (isFull()) {
			System.out.println("队列满， 不能加入数据");
			return;
		}
		//让rear 后移
		arr[++rear] = n;

	}

	//获取数据出队列
	public int getQueue() {
		//判断队列是否为空
		if (isEmpty()) {
			//通过抛出异常
			throw new RuntimeException("队列空  不能取数据");
		}

		front++;
		return arr[front];
	}

	//显示队列的所有数据
	public void showQueue() {
		//遍历数组
		if (isEmpty()) {
			System.out.println("队列为空 没有数据");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]= %d\n", i, arr[i]);
		}


	}

	//显示队列的头数据，注意 不是取数据
	public int peek() {
		//判断
		if (isEmpty()) {
			//通过抛出异常
			throw new RuntimeException("队列空  无数据");
		}
		return arr[front + 1];
	}


}