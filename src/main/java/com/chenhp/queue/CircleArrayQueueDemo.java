package com.chenhp.queue;

import java.util.Scanner;

/**
 * @author chenhp
 * @Date: 2020/11/25/14:10
 */
public class CircleArrayQueueDemo {

	public static void main(String[] args) {
		CircleArray circleArray = new CircleArray(4);

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
					circleArray.showQueue();
					break;
				case 'a':
					System.out.println("输入一个数字");
					int value = scanner.nextInt();
					circleArray.addQueue(value);
					break;
				case 'g':
					try {
						int res = circleArray.getQueue();
						System.out.printf("取出的数据是%d\n",res);
					}catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'h':
					try {
						int peek = circleArray.peek();
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

class CircleArray {

	private int maxSize;//表示数组的最大容量

	private int front; //队列头

	private int rear; //队列尾下一个

	private int[] arr;//存放数据的数组


	public CircleArray(int arrMaxSize) {
		this.maxSize = arrMaxSize;

		this.front = 0;

		this.rear = 0;

		arr = new int[arrMaxSize];

	}


	//是否满
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
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
		//将数据直接加入
		arr[rear] = n;
		//将rear后移  因为是环形  所以要取余
		rear = (rear + 1) % this.maxSize;
	}

	//获取数据出队列
	public int getQueue() {
		//判断队列是否为空
		if (isEmpty()) {
			//通过抛出异常
			throw new RuntimeException("队列空  不能取数据");
		}

		//这里需要分析出front是指向队列的第一个元素
		//1.先把front 对应的值保留在一个临时变量
		//2.将front后移
		//3.将临时保存的变量返回
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}


	//显示队列的所有数据
	public void showQueue() {
		//遍历数组
		if (isEmpty()) {
			System.out.println("队列为空 没有数据");
			return;
		}
		//思路：从front开始遍历 遍历多少个元素
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]= %d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	public int size() {
		return (rear - front + maxSize) % maxSize;
	}


	//显示队列的头数据，注意 不是取数据
	public int peek() {
		//判断
		if (isEmpty()) {
			//通过抛出异常
			throw new RuntimeException("队列空  无数据");
		}
		return arr[front];
	}
}