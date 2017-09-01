package PokerGame2;

import java.io.IOException;
//블랙잭은 최대 7명이서 게임할 수 있다. -- 나중에 
//딜러는 패를 한장만 공개하고 딜러 패의 개수와 점수를보고 
//플레이어는 카드를 더 받을지(hit) 아닌지 정한다(stay).
//둘다 21이 넘으면 배당금을 돌려받으며
//딜러가 지면 배당금은 딜러에게 내가 이기면 배당금의 2배를 받는다. 
import java.util.*;

public class BlackJack {

	Diller diller = new Diller();
	Player player = new Player();
	int gameMoney = 0; 
	
	Scanner sc = new Scanner(System.in);

	//메인
	public static void main(String[] args) {
		BlackJack blackjack = new BlackJack();
		blackjack.gameStart();
	}
	
	// 플레이어 돈과 이름을 설정하는 함수
	void setPlayer() {
		//1. 회원의 이름을 입력 받는다.
		System.out.println("        회원님의 이름을 말씀해주세요.");
		String name = sc.next();
		//2. 회원의 돈을 입력받는다.
		System.out.println("              얼마가져 오셨어요?");
		int money = sc.nextInt();
		//3. 입력받은 이름과 돈을 매개변수로 플레이어 생성
		player = new Player(name, money);
	}

	// 플레이어의 카드의 점수를 계산하는 함수
	void pointCalc() {
		
		//1. 플레이어 카드의 개수를 구해온다.
		int size = player.cardList.size();
		int flag = 0;

		//2. 플레이어가 갖고있는 카드의 숫자를 얻어서
		for (int i = 0; i < size; i++) {
			int num = ((Card) player.cardList.get(i)).num;
		//2.1 만약 카드의 숫자가 A면 11점으로할지 1점으로 할지 선택할 수 있다.
			if (num == 1) {
				System.out.println("A를 1점으로 계산하시겠습니까? 예 : 1 아니요 : 0");
				flag = sc.nextInt();
		//2.1.1 이용자가 1점으로하길 원하면 플레이어 점수에서 10을 제외한다.
				if (flag == 1) {
					player.point -= 10;
				}
			}
		//3. 카드의 숫자를 점수표에 대입해 점수를 구한다.
			player.point += (int) diller.deck.pointMap.get(num);
		}

		// 선생님 이부분이 iterator로 하니까 한번밖에 안돕니다. 다른 컬렉션을 불러오면 이터레이터가 끝나는건가요?
		// 질문 후 삭제
		// Iterator it = player.cardList.iterator();
		//
		// while(it.hasNext()) {
		//
		// int flag = 0;
		// System.out.println("카드넘버");
		// System.out.println(((Card)it.next()).num);
		// System.out.println("매칭되는 점수 ");
		// System.out.println((int)diller.deck.pointMap.get(((Card)it.next()).num));
		// //A를 1점으로 계산할지 11점으로 계산할지 물어본다.
		// if(((Card)it.next()).num == 1) {
		//
		// System.out.println("A를 1점으로 계산하시겠습니까? 예 : 1 아니요 : 0");
		// flag = sc.nextInt();
		//
		// if(flag == 1) {
		// player.point -= 10;
		// }
		// }
		// System.out.println(((Card)it.next()).num);
		// player.point += (int)Deck.pointMap.get(((Card)it.next()).num);
		// }
	}

	// 누가 이겼는지 판별하는 함수
	void whoWin() {
		
		pointCalc();
		// 1. 플레이어,딜러 둘다 21이 넘으면 드로우
		if (diller.point > 21 && player.point > 21)
			draw();
		// 2. 딜러는 21이 넘고 플레이어는 21이하면 플레이어승
		if (diller.point > 21 && player.point <= 21)
			playerWin();
		// 3. 플레이어는 21이넘고 딜러가 21이하면 딜러 승
		if (diller.point <= 21 && player.point > 21)
			dillerWin();
		// 4. 플레이어, 딜러 둘다 21이하면 점수 더 높은곳이 승
		if (diller.point <= 21 && player.point <= 21) {
			int num = diller.point - player.point;

			if (num > 0)
				dillerWin();
			else if (num < 0)
				playerWin();
			else
				draw();
		}
	}

	// 딜러가 이겼을 때(플레이어 패) 함수
	private void dillerWin() {
		//1. 딜러가 이겼다는걸 표시
		System.out.println("Diller Win!!");
		//2. 배당금을 플레이어 머니에서 제외한다. 그리고 출력
		player.money -= gameMoney;
		System.out.println("게임머니를 잃으셨습니다.");
		System.out.println("현재 " + player.name + "의 게임머니" + player.money);
		System.out.println("===============================");
		System.out.println();
	}

	// 플레이어가 이겼을 때  함수()
	private void playerWin() {
		//1. 플레이어가 이겼다는거 표시
		System.out.println(player.name + " Win!!");

		//2. 블랙잭이면 돈을 2배로범
		//2.1 점수가 21이고 카드가 2장이면 블랙잭
		//2.1.1 블랙잭이면 돈 두배 획득
		//2.2 블랙잭이 아니면 그냥 배당금 획득
		if (player.point == 21 && player.cardList.size() == 2) {
			System.out.println("블랙잭!!!!!!!!!!!!!");
			System.out.println("배당금의 2배를 획득합니다.");
			player.money += gameMoney * 2;
		} else {
			System.out.println("게임머니를 획득하셨습니다.");
			player.money += gameMoney;
		}
		
		//3.플레이어 게임머니 표시
		System.out.println("현재 " + player.name + "의 게임머니" + player.money);
		System.out.println("===============================");
		System.out.println();
	}

	// 무승부 함수
	private void draw() {
		//1. 무승부 표시한다.
		//2. 플레이어 돈 표시
		System.out.println("무승부");
		System.out.println("현재 " + player.name + "의 게임머니" + player.money);
		System.out.println("===============================");
		System.out.println();
	}

	// 돈 얼마 배팅할지 정하는 함수
	void batting() {
		boolean flag = true;

		
		do {
			//1.얼마를 배팅할지 사용자에게 입력받는다.
			System.out.println("         얼마를 배팅하시겠습니까?");
			gameMoney = sc.nextInt();
			//1.1 플레이어가 갖고있는 돈보다 배팅금액이 많으면 재배팅
			if (gameMoney > player.money) {
				System.out.println("===============================");
				System.out.println("  가지고있는 돈보다 큰돈은 배팅할 수 없어요");
				System.out.println("===============================");
			} else
				flag = false;

		} while (flag);

		System.out.println();
	}

	// 게임 진행 함수
	void gameStart() {
		int battingFlag = 0;
		
		System.out.println("===============================");
		System.out.println("        비트랜드에 오신것을 환영합니다.");
		System.out.println("===============================");
		System.out.println();
		
		//1. 플레이어를 설정한다.
		setPlayer();
		
		while (true) {
			//딜러 카드 초기화
			diller.cardInit(diller);

			//플레이어 카드 초기화
			diller.playerCardInit(player);
			
			System.out.println("      딜러가 카드를 나눠주는 중입니다. ");
			sleep();
			
			//배팅 인트로 화면
			System.out.println();
			System.out.println("          카드 한장을 받았습니다. ");
			
			//카드 한장을 출력해 플레이어가 배팅할지 안할지 결정하게 한다.
			System.out.println("[" + player.cardList.get(0) + "]");
			System.out.println("배팅 하시겠습니까? 배팅 : 1, DIE : 0");
			battingFlag = sc.nextInt();
			
			//배팅한다고하면 배팅함수로 아니면 참가비만 제외시키고 계속
			if(battingFlag == 1) {
				batting();
				battingFlag = 0;
			}else {
				System.out.println("죽었습니다. 참가비 200원이 제외됩니다");
				player.money -= 200;
				continue;
			}
			
			//딜러 카드와 플레이어카드 공개
			System.out.println("    나머지 카드 한장을 받았습니다. ");
			System.out.println("===============================");
			System.out.println("딜러 카드 한장 공개 ");
			System.out.println("[" + diller.cardList.get(0) + "]" + " 카드 개수 : " + diller.cardList.size());
			System.out.println("===============================");
			System.out.println(player.name + "님의 카드");
			System.out.println(player.cardList);
			System.out.println("===============================");
			
		
			int flag = 0;

			do {
				//플레이어가 카드를 더 받을지 아니면 그대로 멈출지 정한다.
				System.out.println("Hit : 1 or Stay : 2");

				flag = sc.nextInt();
				
				//카드를 더 받는다고하면 한장 추가 후 카드 목록을 보여준다. 스태이를 선택하면 계속진행
				if (flag == 1) {
					System.out.println("카드를 한장 더 받습니다.");
					diller.giveCardToPlayer(player);
					System.out.println("===============================");
					System.out.println(player.name + "님의 카드");
					System.out.println(player.cardList);
					System.out.println("===============================");
				}
			} while (flag != 2);
			
			//결과 출력
			printResult();
			
			//돈없으면 게임오버
			if (player.money <= 0) {
				player.backRub();
				break;
			}
		}
	}
	
	//결과를 출력하는 함수
	private void printResult() {
		//1. 포인트를 계산하고
		//2. 딜러의 카드와 플레이어의 카드를 공개
		System.out.println("===============================");
		System.out.println("            결과 공개");
		System.out.println("===============================");
		System.out.println("            딜러 카드");
		System.out.println(diller.cardList);
		System.out.println(player.name + "의 카드");
		System.out.println(player.cardList);
		System.out.println("===============================");
		//3. 누가이겼는지 판별
		whoWin();
	}

	private void sleep() {
		// 그냥 시간끌려고
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
