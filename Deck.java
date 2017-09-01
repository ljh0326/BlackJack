package PokerGame2;

import java.util.*;

//ī��� 52�� 
//�����ؾ��ϰ�, �������� �����ϰ�, �а� �ٶ������� �ٽ� �����ؾ��Ѵ�.
public class Deck {
	static final int CARD_NUM = 52;
	int remindCard = CARD_NUM; //������ ���� ī�� ����
	
	ArrayList cardList = new ArrayList(CARD_NUM);
	HashMap pointMap = new HashMap();
	
	
	Deck() {
		//52���� ī�� ����Ʈ�� �߰�
		for(int i = 0; i < Card.KIND_MAX; i++) {
			for(int j = 0; j < Card.NUM_MAX -1; j++) {
				cardList.add(new Card(i,j+1));
			}
		}
		pointInit();
	}
	
	
	private void pointInit() {
		//�ؽ��� �ʱ�ȭ
		//������ A�� 1 or 10 �����ϰ� 10�̻��� �� 10���� �����Ѵ�.
		pointMap.put(1, 11);
		
		for(int i = 2 ; i<=10; i++) {
			pointMap.put(i,i);
		
		}
		pointMap.put(11, 10);
		pointMap.put(12, 10);
		pointMap.put(13, 10);
	}
	
	//ī�带 ���� ��
	void shuffle() {
		Collections.shuffle(cardList);
	}
	
	//ī�� ����
	Card pick() {
		//1. �����ִ� ī�� �� �߿��� �������� ���ڸ� �����ϰ�
		int num = (int)(Math.random()*remindCard);
		//2. ���� �ִ� ī�带 ��ȯ
		Card cd =  (Card) cardList.get(num);
		//3. �ش� ī�� ����
		cardList.remove(num);
		//4. �����ִ� ī�� ���ڸ� �÷��ش�.
		remindCard--;
		
		return cd;
	}
	
	//��� ī�尡 �������� ���ο� ���� �� 
	void reRoad() {
		//1. Ȥ�ø𸣴� �ٽ� �����ϰ�
		cardList.removeAll(cardList);
		
		//2. ���� ������Ѵ�.
		for(int i = 0; i < Card.KIND_MAX; i++) {
			for(int j = 0; j < Card.NUM_MAX -1; j++) {
				cardList.add(new Card(i,j+1));
			}
		}
		//3. �� �� ���´�.
		shuffle();
		//4. ���� ī�� ���� �ʱ�ȭ���ش�.
		remindCard = CARD_NUM;
	}
}
