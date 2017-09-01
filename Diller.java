package PokerGame2;


import java.util.*;

//������ ���� �����ְ�
//�ڽ��� ī�� 2���� ���´�. ī�� 2���� 16�� ���ϸ� ī�带 �ް� 17�̻��̸� ���̻� �и� ���� �� ����.
//������ A�������� ���ǻ� 11�� ����Ѵ�.

//������ �÷��̾�� ī�带 �����ָ�
//ī�带 ���Ѵ�. 

public class Diller {
	Deck deck = new Deck();
	ArrayList cardList = null;
	int point = 0; //����
	
	Diller(){
		cardList = new ArrayList();
	}
	
	
	//���� ī�� �ʱ�ȭ 
	void cardInit(Diller diller) {
		//1 ī�尡 ������ ���ο� ��̸���Ʈ�� �����
		diller.cardList = new ArrayList();
		//1.2 ����Ʈ�� �ʱ�ȭ�Ѵ�.
		diller.point = 0;
		cardRelode();
		
		//2. ī�� ������ 16�� �̻��� �ɶ����� ī�带 �̴´�.
		while(point < 16) {
			Card card = deck.pick();
			diller.cardList.add(card);
			diller.point += (int)deck.pointMap.get(card.num);
		}	
		//3. ���� �����ִ� ī�� ���� �����ش�(�Ӹ��ο��)(�ɼ����� �����ұ���)
		//System.out.println("���� �����ִ� ī�� �� " + deck.cardList.size());
	}
	
	//�÷��̾�� ī�带 2�� �����ش�. 
	void playerCardInit(Player player) {
		//1.���� ī�尡 ������� ���캸��
		cardRelode();
		//2.�÷��̾� ���� �ʱ�ȭ
		player.point = 0;
		//3.1 �÷��̾��� �迭 ��ü�� ���� �����
		player.cardList = new ArrayList();
		//3.2 ī�带 2�� �̴´�.
		player.cardList.add(deck.pick());
		player.cardList.add(deck.pick());
	}
	
	//�÷��̾��� ��û�� ī�带 �� �ش�.
	void giveCardToPlayer(Player player){
		//1.���� ī�尡 ������� ���캸��
		cardRelode();
		//2. ī�带 �߰�
		player.cardList.add(deck.pick());
	}
	
	//���� ī�尡 �������� ���ε�
	private void cardRelode() {
		//1 ī������ 1�������� ���캸�� ���ϸ� �� ��ü
		if(deck.cardList.size() <= 1)
			deck.reRoad();
	}

}
