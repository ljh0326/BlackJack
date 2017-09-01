package PokerGame2;

import java.util.*;

//플레이어는 딜러에게 카드를 받을 수 있고 더 받을수도 그만받을수도 있다.
//플레이어는 돈이 있으며 돈이 다 떨어지면 파산할 수 있다.

public class Player {
	ArrayList cardList = null;
	String name;
	int money;
	int point = 0;
	
	Player(){
		this("도박꾼", 5000);
	}
	
	Player(String name, int money){
		this.name = name;
		this.money = money;
		point = 0;
	}
	
	//플레어 파산상태
	void backRub() {
		System.out.println("파산하셨습니다. 집이나 가십쇼");
	}
}
