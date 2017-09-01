package PokerGame2;

import java.io.IOException;
//������ �ִ� 7���̼� ������ �� �ִ�. -- ���߿� 
//������ �и� ���常 �����ϰ� ���� ���� ������ ���������� 
//�÷��̾�� ī�带 �� ������(hit) �ƴ��� ���Ѵ�(stay).
//�Ѵ� 21�� ������ ������ ����������
//������ ���� ������ �������� ���� �̱�� ������ 2�踦 �޴´�. 
import java.util.*;

public class BlackJack {

	Diller diller = new Diller();
	Player player = new Player();
	int gameMoney = 0; 
	
	Scanner sc = new Scanner(System.in);

	//����
	public static void main(String[] args) {
		BlackJack blackjack = new BlackJack();
		blackjack.gameStart();
	}
	
	// �÷��̾� ���� �̸��� �����ϴ� �Լ�
	void setPlayer() {
		//1. ȸ���� �̸��� �Է� �޴´�.
		System.out.println("        ȸ������ �̸��� �������ּ���.");
		String name = sc.next();
		//2. ȸ���� ���� �Է¹޴´�.
		System.out.println("              �󸶰��� ���̾��?");
		int money = sc.nextInt();
		//3. �Է¹��� �̸��� ���� �Ű������� �÷��̾� ����
		player = new Player(name, money);
	}

	// �÷��̾��� ī���� ������ ����ϴ� �Լ�
	void pointCalc() {
		
		//1. �÷��̾� ī���� ������ ���ؿ´�.
		int size = player.cardList.size();
		int flag = 0;

		//2. �÷��̾ �����ִ� ī���� ���ڸ� ��
		for (int i = 0; i < size; i++) {
			int num = ((Card) player.cardList.get(i)).num;
		//2.1 ���� ī���� ���ڰ� A�� 11���������� 1������ ���� ������ �� �ִ�.
			if (num == 1) {
				System.out.println("A�� 1������ ����Ͻðڽ��ϱ�? �� : 1 �ƴϿ� : 0");
				flag = sc.nextInt();
		//2.1.1 �̿��ڰ� 1�������ϱ� ���ϸ� �÷��̾� �������� 10�� �����Ѵ�.
				if (flag == 1) {
					player.point -= 10;
				}
			}
		//3. ī���� ���ڸ� ����ǥ�� ������ ������ ���Ѵ�.
			player.point += (int) diller.deck.pointMap.get(num);
		}

		// ������ �̺κ��� iterator�� �ϴϱ� �ѹ��ۿ� �ȵ��ϴ�. �ٸ� �÷����� �ҷ����� ���ͷ����Ͱ� �����°ǰ���?
		// ���� �� ����
		// Iterator it = player.cardList.iterator();
		//
		// while(it.hasNext()) {
		//
		// int flag = 0;
		// System.out.println("ī��ѹ�");
		// System.out.println(((Card)it.next()).num);
		// System.out.println("��Ī�Ǵ� ���� ");
		// System.out.println((int)diller.deck.pointMap.get(((Card)it.next()).num));
		// //A�� 1������ ������� 11������ ������� �����.
		// if(((Card)it.next()).num == 1) {
		//
		// System.out.println("A�� 1������ ����Ͻðڽ��ϱ�? �� : 1 �ƴϿ� : 0");
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

	// ���� �̰���� �Ǻ��ϴ� �Լ�
	void whoWin() {
		
		pointCalc();
		// 1. �÷��̾�,���� �Ѵ� 21�� ������ ��ο�
		if (diller.point > 21 && player.point > 21)
			draw();
		// 2. ������ 21�� �Ѱ� �÷��̾�� 21���ϸ� �÷��̾��
		if (diller.point > 21 && player.point <= 21)
			playerWin();
		// 3. �÷��̾�� 21�̳Ѱ� ������ 21���ϸ� ���� ��
		if (diller.point <= 21 && player.point > 21)
			dillerWin();
		// 4. �÷��̾�, ���� �Ѵ� 21���ϸ� ���� �� �������� ��
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

	// ������ �̰��� ��(�÷��̾� ��) �Լ�
	private void dillerWin() {
		//1. ������ �̰�ٴ°� ǥ��
		System.out.println("Diller Win!!");
		//2. ������ �÷��̾� �ӴϿ��� �����Ѵ�. �׸��� ���
		player.money -= gameMoney;
		System.out.println("���ӸӴϸ� �����̽��ϴ�.");
		System.out.println("���� " + player.name + "�� ���ӸӴ�" + player.money);
		System.out.println("===============================");
		System.out.println();
	}

	// �÷��̾ �̰��� ��  �Լ�()
	private void playerWin() {
		//1. �÷��̾ �̰�ٴ°� ǥ��
		System.out.println(player.name + " Win!!");

		//2. �����̸� ���� 2��ι�
		//2.1 ������ 21�̰� ī�尡 2���̸� ����
		//2.1.1 �����̸� �� �ι� ȹ��
		//2.2 ������ �ƴϸ� �׳� ���� ȹ��
		if (player.point == 21 && player.cardList.size() == 2) {
			System.out.println("����!!!!!!!!!!!!!");
			System.out.println("������ 2�踦 ȹ���մϴ�.");
			player.money += gameMoney * 2;
		} else {
			System.out.println("���ӸӴϸ� ȹ���ϼ̽��ϴ�.");
			player.money += gameMoney;
		}
		
		//3.�÷��̾� ���ӸӴ� ǥ��
		System.out.println("���� " + player.name + "�� ���ӸӴ�" + player.money);
		System.out.println("===============================");
		System.out.println();
	}

	// ���º� �Լ�
	private void draw() {
		//1. ���º� ǥ���Ѵ�.
		//2. �÷��̾� �� ǥ��
		System.out.println("���º�");
		System.out.println("���� " + player.name + "�� ���ӸӴ�" + player.money);
		System.out.println("===============================");
		System.out.println();
	}

	// �� �� �������� ���ϴ� �Լ�
	void batting() {
		boolean flag = true;

		
		do {
			//1.�󸶸� �������� ����ڿ��� �Է¹޴´�.
			System.out.println("         �󸶸� �����Ͻðڽ��ϱ�?");
			gameMoney = sc.nextInt();
			//1.1 �÷��̾ �����ִ� ������ ���ñݾ��� ������ �����
			if (gameMoney > player.money) {
				System.out.println("===============================");
				System.out.println("  �������ִ� ������ ū���� ������ �� �����");
				System.out.println("===============================");
			} else
				flag = false;

		} while (flag);

		System.out.println();
	}

	// ���� ���� �Լ�
	void gameStart() {
		int battingFlag = 0;
		
		System.out.println("===============================");
		System.out.println("        ��Ʈ���忡 ���Ű��� ȯ���մϴ�.");
		System.out.println("===============================");
		System.out.println();
		
		//1. �÷��̾ �����Ѵ�.
		setPlayer();
		
		while (true) {
			//���� ī�� �ʱ�ȭ
			diller.cardInit(diller);

			//�÷��̾� ī�� �ʱ�ȭ
			diller.playerCardInit(player);
			
			System.out.println("      ������ ī�带 �����ִ� ���Դϴ�. ");
			sleep();
			
			//���� ��Ʈ�� ȭ��
			System.out.println();
			System.out.println("          ī�� ������ �޾ҽ��ϴ�. ");
			
			//ī�� ������ ����� �÷��̾ �������� ������ �����ϰ� �Ѵ�.
			System.out.println("[" + player.cardList.get(0) + "]");
			System.out.println("���� �Ͻðڽ��ϱ�? ���� : 1, DIE : 0");
			battingFlag = sc.nextInt();
			
			//�����Ѵٰ��ϸ� �����Լ��� �ƴϸ� ������ ���ܽ�Ű�� ���
			if(battingFlag == 1) {
				batting();
				battingFlag = 0;
			}else {
				System.out.println("�׾����ϴ�. ������ 200���� ���ܵ˴ϴ�");
				player.money -= 200;
				continue;
			}
			
			//���� ī��� �÷��̾�ī�� ����
			System.out.println("    ������ ī�� ������ �޾ҽ��ϴ�. ");
			System.out.println("===============================");
			System.out.println("���� ī�� ���� ���� ");
			System.out.println("[" + diller.cardList.get(0) + "]" + " ī�� ���� : " + diller.cardList.size());
			System.out.println("===============================");
			System.out.println(player.name + "���� ī��");
			System.out.println(player.cardList);
			System.out.println("===============================");
			
		
			int flag = 0;

			do {
				//�÷��̾ ī�带 �� ������ �ƴϸ� �״�� ������ ���Ѵ�.
				System.out.println("Hit : 1 or Stay : 2");

				flag = sc.nextInt();
				
				//ī�带 �� �޴´ٰ��ϸ� ���� �߰� �� ī�� ����� �����ش�. �����̸� �����ϸ� �������
				if (flag == 1) {
					System.out.println("ī�带 ���� �� �޽��ϴ�.");
					diller.giveCardToPlayer(player);
					System.out.println("===============================");
					System.out.println(player.name + "���� ī��");
					System.out.println(player.cardList);
					System.out.println("===============================");
				}
			} while (flag != 2);
			
			//��� ���
			printResult();
			
			//�������� ���ӿ���
			if (player.money <= 0) {
				player.backRub();
				break;
			}
		}
	}
	
	//����� ����ϴ� �Լ�
	private void printResult() {
		//1. ����Ʈ�� ����ϰ�
		//2. ������ ī��� �÷��̾��� ī�带 ����
		System.out.println("===============================");
		System.out.println("            ��� ����");
		System.out.println("===============================");
		System.out.println("            ���� ī��");
		System.out.println(diller.cardList);
		System.out.println(player.name + "�� ī��");
		System.out.println(player.cardList);
		System.out.println("===============================");
		//3. �����̰���� �Ǻ�
		whoWin();
	}

	private void sleep() {
		// �׳� �ð�������
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
