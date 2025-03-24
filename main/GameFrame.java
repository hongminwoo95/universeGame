package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Player.PlayerCenter;
import enemy.Enemy;
import enemy.EnemyCenter;
import missile.Missile;
import missile.MissileCenter;

public class GameFrame extends JFrame implements KeyListener {
	
	private int f_width = 800; // 프레임 너비설정
	private int f_height = 800; // 프레임 높이 설정
	
	private ArrayList<Missile> missileList = new ArrayList(); // 미사일 이미지 리스트
	private Vector<Image> playerList = new Vector(); // 플레이어 이미지 리스트
	private Vector<Enemy> enemyList = new Vector(); // 적기 이미지 리스트
	
	// 키보드셋팅
	boolean keyUp = false; // 윗키
	boolean keyDown = false; // 아래키
	boolean keyLeft = false; // 왼쪽
	boolean keyRight = false; // 오른쪽
	boolean keySpace = false; // 스페이스 (미사일 발사)
	boolean flagTh = true; // 전체 스레드 종료,시작 설정
	boolean makeenemyTh = true; // 몬스터 생성 스레드 종료,시작 설정
	
	// 참조형
	private Missile missile; // 미사일 인터페이스 참조변수
	private Enemy enemy; // 적기 인터페이스 참조변수
	
	private Image player; // 현재 캐릭터 이미지
	private Image missileImage; // 현재 미사일 이미지
	private Image enemyOneImage; // 적기생성 이미지
	// 배경 이미지
	private Image background1; // 화염행성 배경
	private Image background2; // 얼음행성 배경
	
	private int x = 350; // 게임시작시 최초 player이미지 생성할 x좌표
	private int y = 300; // 게임시작시 최초 player이미지 생성할 y좌표
	private int enemyX = 0; // 게임시작시 최초 enemy이미지 생설할 x좌표
	private int enemyY = 0; // 게임시작시 최초 enemy이미지 생설할 y좌표
	// private int cnt; // 남은생명 
	private int timer; // 1초마다 1개씩 올라감
	
	private Image buffImage; // 더블 버퍼링용 이미지
	private Graphics buffg; // 더블 버퍼링용 그리기

	private Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지를 불러오기 위한 톨킷
	
	// 각 항목별 중앙센터 싱글톤 객체 주소
	private PlayerCenter playercenter = PlayerCenter.getPlayerCenter(); // PlayerCenter 객체주소
	private MissileCenter missilecenter = MissileCenter.getMissileCenter(); // MissileCenter 객체주소
	private EnemyCenter enemycenter = EnemyCenter.getEnemyCenter(); // EnemyCenter 객체주소
	// System.out.println(Thread.activeCount()); <-- 스레드가 몇개 실행되었는지 알수있는 코드
	
	Thread KeySettingTh; // 키셋팅 스레드
	Thread enemyThR; // 우측 적기 생성 스레드
	Thread enemyThL; // 좌측 적기 생성 스레드
	Thread missileOneTh; // Player 미사일 발사용 스레드
			
			
	GameFrame() {
		init(); // 캐릭터 및 몬스터 좌표 설정
		start(); // 시작메서드
		setTitle("우주모험가"); // 프레임 타이틀 설정
		setSize(f_width, f_height); // 프레임 크기 설정 (프레임을 윈도우에 표시 위치셋팅,이미지를 불러오기 위한 툴킷 , 모니터 해상도 값 받아오기)
		Dimension screen = tk.getScreenSize(); // 모니터 해상도 얻어오기
		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2); // 프레임 모니터 해상도 정중앙 셋팅
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2); // 프레임 모니터 해상도 정중앙 셋팅
		setLocation(f_xpos, f_ypos); // 해당좌표 위치를 나타내는 지점
		setResizable(false); // 프레임 창 크기 조절 가능여부 (boolean type)
		setVisible(true); // 프레임 가림 여부 (boolean type)
		addKeyListener(this); // 키보드 이벤트 실행
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임을 닫았을시 프로그램 실행종료
	}
	public void init() { // 각종 이미지 객체 생성
		background1 = new ImageIcon("images/얼음행성.png").getImage(); // 맵 이미지 생성
		player = new ImageIcon("images/중앙.png").getImage(); // player생성후 키보드 이벤트 발생전 이미지 -> 키이벤트 발생시 이미지변경
	}
	public void start() { // 시작 메서드
		KeySettingTh(); // player 키보드 이벤트 발생 관련 스레드
		missileOneTh(); // missile 발사용 스레드 시작
		enemyTh(); // 적기 생성용 스레드
	}
	public void paint(Graphics g) { // 버퍼 준비 과정 작업
		buffImage = createImage(f_width, f_height); 
		// ↑ createImage <- 지정된 바이트 배열에 저장된 이미지를 지정된 오프셋과 길이로 디코딩하는 이미지 생성 (버퍼)
		// 더블버퍼링 버퍼 크기를 화면 크기와 같게 설정
		buffg = buffImage.getGraphics(); // 버퍼의 그래픽 객체를 얻기
		buffg.clearRect(0, 0, f_width, f_height); // 버퍼를 지운다
		//buffg.drawImage(player, x, y, this); // 첫 시작 위치
		update(g);
	}
	public void update(Graphics g) { // 준비된 버퍼에 이미지 입히는 작업
		Draw_background();// 배경그림 가져옴
		Draw_PlayerM(); // 플레이어 시작전 중앙 이미지
		Draw_Player(); // 플레이어 그림 가져옴
		Draw_missile(); // 미사일 그림 가져옴
		Draw_enemyOne(); // 우측적기 그림 가져옴
		g.drawImage(buffImage, 0, 0, this); // 버퍼에 그린 그림을 버퍼에 덮어씌우기 (아래에서 그린그림 찍어내기)
	}
	public void Draw_background() { // 첫 배경 그리기
		buffg.drawImage(background1, 0, 0, f_width, f_height, this); // 프레임에
	}
	public void Draw_PlayerM() { // 캐릭터 초기 중앙값 그리기
		buffg.drawImage(player, x, y, 100, 100, this); // 프레임에
	}
	public void Draw_Player() { // player 그리기
		for (int i=0; i<playerList.size(); i++) {
			player = playerList.get(i);
			buffg.drawImage(player, x, y, 100, 100, this); // 프레임에 player에 저장된 변환되는 이미지를 x=100, y=100 좌표에 100x100해상도로 그려넣습니다.
			if(i < playerList.size()-1)
				playerList.remove(i);
		}
	}
	public void Draw_missile() { // 미사일 이미지 출력
		for (int i=0; i<missileList.size(); i++) {
			missile = missileList.get(i); // SpaceProcess에서 add된 missile을 missile변수에 대입
			buffg.drawImage(missileImage, missile.getBulletX(), missile.getBulletY(), this); // 버퍼에 (이미지,x,y) 이미지 그려넣기
			for(int j=0; j<enemyList.size(); j++) {
				enemy = enemyList.get(j);
				if(missileList!=null) {
					
					hitenemy(i,j,missile,enemy);
				}
			}
			missile.move(); // missile x,y축 기준 각미사일별 +,- 속도 기입한 수치나 방향으로 이동
			if (missile.getBulletX()>=710 && missile.getBulletX()<=-20 &&  // 미사일좌표가 JFram 범위를 넘어갈시
					missile.getBulletY()>=710 && missile.getBulletY()<=10) { // 미사일좌표가 JFram 범위를 넘어갈시
				missileList.remove(i); // 해당 미사일객체 삭제
			}
		}
	}
	public void Draw_enemyOne() {
		for (int i=0; i<enemyList.size(); i++) {
			enemy = enemyList.get(i); // enemy의 객체주소,x,y좌표가 있는 객체를 enemy에 대입
			buffg.drawImage(enemyOneImage, enemy.getenemyX(), enemy.getenemyY(), 45 , 31, this); // 버퍼에에 enemyImage,시작x,y좌표,해상도100x100을 이미지로 그린다
			enemy.getmove(); // 해당 enemy객체 move설정값 만큼 이동 (버퍼에 그린 이미지와 enemy는 같은객체 주소임)
			if (enemy.getenemyX()<=-20 && enemy.getenemyX()>=710) { // enemy객체의 x값이 -20보다 작아 화면을 넘어갈시
				enemyList.remove(i); // 해당객체 삭제
			}
		}
	}
	public void hitenemy(int i , int j , Missile m, Enemy e) {
		if(m.getBulletX() + m.getWidth()  >= e.getenemyX() &&
				(m.getBulletY() + m.getHeight() >= e.getenemyY() &&
				m.getBulletY() <= e.getenemyY() + e.getenemyHeight())) {
			try {
				missileList.remove(i); // missileList는 키를 누를때마다 객체가 리스트에 add되지만
				enemyList.remove(j);	// enemyList는 스레드가 자동으로 객체를 리시트에 올려주기때문에 i값이 비어있을경우	
			}catch(Exception a) {		// j값만 출력됨으로 예외가 발생한다 try catch로 잡음
				new RuntimeException(a);
			}
		}
	}

	public void enemyTh() { // 적기 생성용 스레드
		enemyThR = new Thread(new Runnable() {
			@Override
			public void run() {
				while (flagTh) {
					try {
						MakeEnemyR(); // 적1 우측 생성 메서드
						repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
						Thread.sleep(2000); // 약간의 딜레이를 주어 cpu의 과부하를 줄여줌
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		enemyThR.start();
	}
	public void MakeEnemyR() { // 적기 생성 
		enemycenter.setLocation("MakeEnemyR"); // EnemyCenter에 적기 생성위치 변수 바꿔주기
		for (int i=100; i<=100*6; i+=100) {
			if (makeenemyTh) {
				enemy = enemycenter.getEnemyOne(x, y); // enemy에  Enemy 타입의 EnemyRight객체주소 대입
				enemyOneImage = enemy.getImage(); // enemyOneImage에 EnemyRight를 참조한 EnemyOne이미지 대입
				int xx = 720; // enemy 이미지 너비의 중앙을 xx변수에 대입 (시작시 enemy출현할 x좌표)
				int yy = i; // enemy 이미지 높이의 중앙을 yy변수에 대입 (시작시 enemy출현할 y좌표)
				enemy = enemycenter.getEnemyXY(enemy, xx, yy); // enemy변수에 EnemyRight 객체주소, x좌표, y좌표 대입
				enemyList.add(enemy); 
			}
		}
	}
	public void missileOneTh() { // 미사일 	발사용 스레드
		missileOneTh = new Thread(new Runnable() {
			@Override
			public void run() {
				while (flagTh) {
					try {
						SpaceMissileProcess(); // 미사일 발사 키보드 이벤트
						repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
						Thread.sleep(10); // 약간의 딜레이를 주어 cpu의 과부하를 줄여줌
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		missileOneTh.start(); // 스레드 시작
	}
	public void SpaceMissileProcess() { // 미사일 이미지 가져오기
		if(keySpace) { // 스페이스키 true일때 (눌렀을때)
			missile = missilecenter.getMissile(x, y); // player방향에 맞는 missile 객체주소를 missile에 대입
			missileImage = missile.getImage(); // player방향에맞는 해당방향 미사일 이미지를 missileImage에 대입
			int xx = x + player.getWidth(null) / 2 - missileImage.getWidth(null) / 2; // Player너비 중앙 - Missile너비 중앙을 x좌표에
			int yy = y + player.getHeight(null) / 2 - missileImage.getHeight(null) / 2; // Player높이 중앙 - Missile높이 중앙을 y좌표에
			missile = missilecenter.getMissileXY(missile, xx, yy); // missile에 missile객체주소, x좌표, y좌표를 대입
			missileList.add(missile);// missileList에 좌표,너비,높이 올리기
			keySpace = false; // missileList에 add될때 발사 하지않기 true시 리스트에 add할때마다 발사 
		}
	}
	public void KeySettingTh() { // 키셋팅 스레드
		KeySettingTh = new Thread(new Runnable() { 
			@Override
			public void run() {
				while (flagTh) { // while문을 사용하여 실행순서 관계없이 계속 진행되도록 
					try {
						KeyProcess(); // player 움직임 키보드 이벤트
						repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
						Thread.sleep(20); // 약간의 딜레이를 주어 cpu의 과부하를 줄여줌
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		KeySettingTh.start(); // 스레드 시작kjk
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			keyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = true;
			break;
		case KeyEvent.VK_Q: // 스페이스키 입력 처리 추가(미사일 발사)
			keySpace = true;
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyUp = false;
			break;
		case KeyEvent.VK_DOWN: 
			keyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = false;
			break;
		case KeyEvent.VK_Q: // 스페이스키 입력 처리 추가(미사일 발사)
			keySpace = false;
			break;
		}
	}
	public void KeyProcess() { // player이미지가 JFrame너비,높이를 넘어가지 못하게 이벤트 발생 
		if (keyLeft&&!keyRight&&!keyUp&&!keyDown) { // ← 좌측키
			if(x<=-20) {
				playercenter.setType("PlayerL"); // 방향키를 누르면 PlayerCenter Type변경
				missilecenter.setPlayertype("PlayerL"); // 방향키를 누르면 MissileCenter playertype에 문자열대입
				player = playercenter.getPlayer().getPlayer(); // Image Player변수에 방향키에 맞는 이미지 대입
				playerList.add(player); // 해당 이미지를 PlayerList에 add.
				x = -20;
				x += 5;
			}else {
				playercenter.setType("PlayerL"); 
				missilecenter.setPlayertype("PlayerL"); 
				player = playercenter.getPlayer().getPlayer(); 
				playerList.add(player); 
				x -= 5;
			}
		}
		if (keyRight&&!keyLeft&&!keyUp&&!keyDown) { // → 우측키
			if(x>=710) {
				playercenter.setType("PlayerR");
				missilecenter.setPlayertype("PlayerR");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x = 710;
				x += 5;
			}else {
				playercenter.setType("PlayerR");
				missilecenter.setPlayertype("PlayerR");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x += 5;
			}
		}
		if (keyUp&&!keyDown&&!keyLeft&&!keyRight) { // ↑ 윗키
			if(y<=10) { // y가 좌표 10보다 같거나 작을때
				playercenter.setType("PlayerU");
				missilecenter.setPlayertype("PlayerU");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				y = 10; // y좌표 10에서 멈추게하기
				y -=5;
			}else { // 아닐시
				playercenter.setType("PlayerU");
				missilecenter.setPlayertype("PlayerU");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				y -= 5; // y좌표에서 이동
			}
		}
		if (keyDown && !keyUp && !keyLeft && !keyRight) { // ↓ 아랫키
			if (y >= 710) { // JFrame y+710를 넘어갈시
				playercenter.setType("PlayerD");
				missilecenter.setPlayertype("PlayerD");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				y = 710; // JFrame y+710를 넘어갈시 멈춤
				y += 5;
			} else {
				playercenter.setType("PlayerD");
				missilecenter.setPlayertype("PlayerD");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				y += 5;
			}
		}
		if (keyLeft&&keyUp&&!keyDown&&!keyRight){ // 좌측상단 ↖키
			if (x<=-20) {
				x = -20;
				if(y<=+10) {
					y = +10;
					playercenter.setType("PlayerLU");
					missilecenter.setPlayertype("PlayerLU");
					player = playercenter.getPlayer().getPlayer();
					playerList.add(player);
				}
				x -= 5;
				y -= 5;
			}else if(y<=+10) {
				y=+10;
				playercenter.setType("PlayerLU");
				missilecenter.setPlayertype("PlayerLU");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x -= 5;
				y -= 5;
			}
			else {
				playercenter.setType("PlayerLU");
				missilecenter.setPlayertype("PlayerLU");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x -= 5;
				y -= 5;
			}
		
		}
		if (keyLeft && keyDown && !keyUp && !keyRight) { // ↙ 우측하단키
			if (x <= -20) {
				x = -20;
				if (y >= 710) {
					y = 710;
					playercenter.setType("PlayerLD");
					missilecenter.setPlayertype("PlayerLD");
					player = playercenter.getPlayer().getPlayer();
					playerList.add(player);
				}
				x -= 5;
				y += 5;
			} else if (y >= +710) {
				y = +710;
				playercenter.setType("PlayerLD");
				missilecenter.setPlayertype("PlayerLD");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x -= 5;
				y += 5;
			} else {
				playercenter.setType("PlayerLD");
				missilecenter.setPlayertype("PlayerLD");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x -= 5;
				y += 5;
			}

		}
		if (keyRight&&keyUp&&!keyDown&&!keyLeft){ // ↗ 우측상단키
			if (x >= 710) {
				x = 710;
				if(y <= +10) {
					y = +10;
					playercenter.setType("PlayrRU");
					missilecenter.setPlayertype("PlayerRU");
					player = playercenter.getPlayer().getPlayer();
					playerList.add(player);
				}
				x += 5;
				y -= 5;
			}else if(y <= +10) {
				y = +10;
				playercenter.setType("PlayrRU");
				missilecenter.setPlayertype("PlayerRU");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x += 5;
				y -= 5;
			}else {
				playercenter.setType("PlayerRU");
				missilecenter.setPlayertype("PlayerRU");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x += 5;
				y -= 5;
			}
		}
		if (keyRight&&keyDown&&!keyUp&&!keyLeft){ // ↘ 우측하단키 
			if (x >= 710) {
				x = 710;
				if(y >= 710) {
					y = 710;
					playercenter.setType("PlayerRD");
					missilecenter.setPlayertype("PlayerRD");
					player = playercenter.getPlayer().getPlayer();
					playerList.add(player);
				}
				x += 5;
				y += 5;
			}else if(y >= 710) {
				y = 710;
				playercenter.setType("PlayerRD");
				missilecenter.setPlayertype("PlayerRD");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x += 5;
				y += 5;
			}else {
				playercenter.setType("PlayerRD");
				missilecenter.setPlayertype("PlayerRD");
				player = playercenter.getPlayer().getPlayer();
				playerList.add(player);
				x += 5;
				y += 5;
			}
		}
 	}
	@Override
	public void keyTyped(KeyEvent e) { // 문자이벤트를 처리하는 타입

	}

}
