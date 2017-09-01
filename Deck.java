package PokerGame2;

import java.util.*;

//카드는 52장 
//셔플해야하고, 랜덤으로 선택하고, 패가 다떨어지면 다시 보충해야한다.
public class Deck {
	static final int CARD_NUM = 52;
	int remindCard = CARD_NUM; //덱에서 남은 카드 숫자
	
	ArrayList cardList = new ArrayList(CARD_NUM);
	HashMap pointMap = new HashMap();
	
	
	Deck() {
		//52장을 카드 리스트에 추가
		for(int i = 0; i < Card.KIND_MAX; i++) {
			for(int j = 0; j < Card.NUM_MAX -1; j++) {
				cardList.add(new Card(i,j+1));
			}
		}
		pointInit();
	}
	
	
	private void pointInit() {
		//해쉬맵 초기화
		//블랙잭은 A는 1 or 10 선택하고 10이상은 다 10으로 간주한다.
		pointMap.put(1, 11);
		
		for(int i = 2 ; i<=10; i++) {
			pointMap.put(i,i);
		
		}
		pointMap.put(11, 10);
		pointMap.put(12, 10);
		pointMap.put(13, 10);
	}
	
	//카드를 섞는 것
	void shuffle() {
		Collections.shuffle(cardList);
	}
	
	//카드 선택
	Card pick() {
		//1. 남아있는 카드 수 중에서 랜덤으로 숫자를 선택하고
		int num = (int)(Math.random()*remindCard);
		//2. 덱에 있는 카드를 반환
		Card cd =  (Card) cardList.get(num);
		//3. 해당 카드 삭제
		cardList.remove(num);
		//4. 남아있는 카드 숫자를 늘려준다.
		remindCard--;
		
		return cd;
	}
	
	//모든 카드가 떨어지면 새로운 덱을 씀 
	void reRoad() {
		//1. 혹시모르니 다시 삭제하고
		cardList.removeAll(cardList);
		
		//2. 덱을 재생성한다.
		for(int i = 0; i < Card.KIND_MAX; i++) {
			for(int j = 0; j < Card.NUM_MAX -1; j++) {
				cardList.add(new Card(i,j+1));
			}
		}
		//3. 그 후 섞는다.
		shuffle();
		//4. 남은 카드 수를 초기화해준다.
		remindCard = CARD_NUM;
	}
}
