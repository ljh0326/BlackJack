package PokerGame2;


import java.util.*;

//딜러는 덱을 갖고있고
//자신이 카드 2장을 갖는다. 카드 2장이 16점 이하면 카드를 받고 17이상이면 더이상 패를 받을 수 없다.
//딜러는 A가나오면 편의상 11로 계산한다.

//딜러는 플레이어에게 카드를 나눠주며
//카드를 픽한다. 

public class Diller {
	Deck deck = new Deck();
	ArrayList cardList = null;
	int point = 0; //점수
	
	Diller(){
		cardList = new ArrayList();
	}
	
	
	//딜러 카드 초기화 
	void cardInit(Diller diller) {
		//1 카드가 들어오면 새로운 어레이리스트를 만들고
		diller.cardList = new ArrayList();
		//1.2 포인트를 초기화한다.
		diller.point = 0;
		cardRelode();
		
		//2. 카드 점수가 16점 이상이 될때까지 카드를 뽑는다.
		while(point < 16) {
			Card card = deck.pick();
			diller.cardList.add(card);
			diller.point += (int)deck.pointMap.get(card.num);
		}	
		//3. 덱에 남아있는 카드 수를 보여준다(머리싸움용)(옵션으로 변경할까함)
		//System.out.println("덱에 남아있는 카드 수 " + deck.cardList.size());
	}
	
	//플레이어에게 카드를 2장 나눠준다. 
	void playerCardInit(Player player) {
		//1.덱에 카드가 충분한지 살펴보고
		cardRelode();
		//2.플레이어 점수 초기화
		player.point = 0;
		//3.1 플레이어의 배열 객체를 새로 만들고
		player.cardList = new ArrayList();
		//3.2 카드를 2장 뽑는다.
		player.cardList.add(deck.pick());
		player.cardList.add(deck.pick());
	}
	
	//플레이어의 요청시 카드를 더 준다.
	void giveCardToPlayer(Player player){
		//1.덱에 카드가 충분한지 살펴보고
		cardRelode();
		//2. 카드를 추가
		player.cardList.add(deck.pick());
	}
	
	//덱에 카드가 떨어지면 리로드
	private void cardRelode() {
		//1 카드사이즈가 1이하인지 살펴보고 이하면 덱 교체
		if(deck.cardList.size() <= 1)
			deck.reRoad();
	}

}
