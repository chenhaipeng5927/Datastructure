package com.chenhp.linkedlist;

/**
 * 单向列表
 *
 * @author chenhp
 * @Date: 2020/11/25/15:25
 */
public class SingleLinkedListDemo {
	public static void main(String[] args) {
		//进行测试
		//创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "豹子头", "林冲");
		HeroNode hero5 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero6 = new HeroNode(2, "小陈", "玉麒麟");

		//创建要给链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		//加入
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3);
//		singleLinkedList.add(hero4);
//		singleLinkedList.list();

		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero1);

		singleLinkedList.addByOrder(hero5);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.list();
		singleLinkedList.update(hero6);
		singleLinkedList.list();
	}
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
	//先初始化一个头结点  头结点不要动
	private HeroNode head = new HeroNode(0, "", "");

	//添加节点到单向链表
	//思路:当不考虑编号顺序时
	//1.找到当前链表的最后节点
	//2，将最后这个节点的next指向新的节点
	public void add(HeroNode node) {
		//因为head节点不能动，因此我们需要一个新的辅助变量temp遍历
		HeroNode temp = head;
		//遍历链表
		while (temp.next != null) {
			//如果没有找到 就将temp后移
			temp = temp.next;
		}
		//当退出while循环时 temp就指向了列表的最后
		temp.next = node;
	}

	//显示链表【遍历】
	public void list() {
		//判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头结点不能动，因为需要一个辅助变量来遍历
		HeroNode temp = head.next;
		while (temp != null) {
			//输出节点信息
			System.out.println(temp);
			temp = temp.next;
		}
	}

	//按顺序添加
	public void addByOrder(HeroNode node) {
		//因为head节点不能动，因此我们需要一个新的辅助变量temp遍历
		HeroNode temp = head;
		//因为单链表 因此找的temp是位于添加位置之前
		//遍历链表
		while (temp.next != null) {
			//如果没有找到 就将temp后移
			if (temp.next.no < node.no) {
				temp = temp.next;
				continue;
			}
			break;
		}
		//当退出while循环时 temp就指向了列表的最后
		node.next = temp.next;
		temp.next = node;
	}

	//修改节点的信息，根据no编号来修改 即no编号不能改（修改所有no相同的）
	public void update(HeroNode newHeroNode) {
		//判断是否空
		if (head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		//找到需要修改的节点 根据no编号
		//定义辅助节点
		HeroNode temp = head.next;
		do {
			if (temp.no == newHeroNode.no) {
				temp.name = newHeroNode.name;
				temp.nickName = newHeroNode.nickName;
			}
			temp = temp.next;
		} while (temp != null);
	}

	//删除节点
	
}

//定义HeroNode 每个HeroNode 对象就是一个节点
class HeroNode {

	public int no;

	public String name;

	public String nickName;

	public HeroNode next;//指向下一个节点

	//构造器
	public HeroNode(int hNo, String hName, String hNickName) {
		this.no = hNo;
		this.name = hName;
		this.nickName = hNickName;
	}


	@Override
	public String toString() {
		return "HeroNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				", nickName='" + nickName + '\'' +

				'}';
	}
}