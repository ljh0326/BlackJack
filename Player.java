package PokerGame2;

import java.util.*;

//�÷��̾�� �������� ī�带 ���� �� �ְ� �� �������� �׸��������� �ִ�.
//�÷��̾�� ���� ������ ���� �� �������� �Ļ��� �� �ִ�.

public class Player {
	ArrayList cardList = null;
	String name;
	int money;
	int point = 0;
	
	Player(){
		this("���ڲ�", 5000);
	}
	
	Player(String name, int money){
		this.name = name;
		this.money = money;
		point = 0;
	}
	
	//�÷��� �Ļ����
	void backRub() {
		System.out.println("�Ļ��ϼ̽��ϴ�. ���̳� ���ʼ�");
	}
}
