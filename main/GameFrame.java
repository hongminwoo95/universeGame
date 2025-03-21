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

import Player.Player;
import Player.PlayerCenter;
import missile.Missile;
import missile.MissileCenter;
import missile.MissileOneLeft;

public class GameFrame extends JFrame implements KeyListener, Runnable {

	
	private int f_width = 800; // 프레임 너비설정
	private int f_height = 800; // 프레임 높이 설정
	private ArrayList<Image> missileList = new ArrayList(); // 미사일 이미지 리스트
	private Vector<Image> playerList = new Vector(); // 플레이어 이미지 리스트
	
	// 키보드셋팅
	boolean keyUp = false; // 윗키
	boolean keyDown = false; // 아래키
	boolean keyLeft = false; // 왼쪽
	boolean keyRight = false; // 오른쪽
	boolean keySpace = false; // 스페이스 (총탄발사)
	
	// 참조형
	private Missile missile; // 미사일 인터페이스 참조변수
	
	private Image player; //캐릭터 이미지
	private Image missileImage; // 미사일 이미지
	
	// 배경 이미지
	private Image background1; // 화염행성 배경
	private Image background2; // 얼음행성 배경
	
	private int x = 100; // 게임시작시 player 나타날 x좌표
	private int y = 100; // 게임시작시 player 나타날 y좌표
	
	private Image buffImage; // 더블 버퍼링용 이미지
	private Graphics buffg; // 더블 버퍼링용 그리기

	Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지를 불러오기 위한 톨킷
	Thread th; // 스레드
	Thread py; // 플레이어 스레드
//	Thread keyUpTh; // keyUp 스레드
//	Thread keyDownTh; // keyDown 스레드
//	Thread keyLeftTh; // key Left 스레드
	
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
	}
	public void init() { // 각종 이미지 객체 생성
		background1 = new ImageIcon("images/얼음행성.png").getImage(); // 맵 이미지 생성
	}
	public void start() { // 시작 메서드
		th = new Thread(this); // 스레드 생성
		th.start(); // 스레드 실행
		addKeyListener(this); // 키보드 이벤트 실행
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임을 닫았을시 프로그램 실행종료
	}
	public void paint(Graphics g) { // 
		buffImage = createImage(f_width, f_height);
		// ↑ createImage <- 지정된 바이트 배열에 저장된 이미지를 지정된 오프셋과 길이로 디코딩하는 이미지 생성 (버퍼)
		// 더블버퍼링 버퍼 크기를 화면 크기와 같게 설정
		buffg = buffImage.getGraphics(); // 버퍼의 그래픽 객체를 얻기
		buffg.clearRect(0, 0, f_width, f_height); // 버퍼를 지운다
		//buffg.drawImage(player, x, y, this); // 첫 시작 위치
		update(g);

	}
	
	public void update(Graphics g) { // 이미지 생성
		Draw_background();// 배경그림 가져옴
		Draw_Player(); // 플레이어 그림 가져옴
		Draw_missile(); // 미사일 그림 가져옴
		g.drawImage(buffImage, 0, 0, this); // 화면 버퍼에 그린 그림을 버퍼에 덮어씌우기 (아래에서 그린그림 찍어내기)
	}
	
	public void Draw_background() { // 이미지 그릴부분
		buffg.drawImage(background1, 0, 0, f_width, f_height, this); // 프레임에

	}
	
	public void Draw_Player() {
		for (int i=0; i<playerList.size(); i++) {
			player = playerList.get(i);
			buffg.drawImage(player, x, y, 100, 100, this);
			if(i < playerList.size()-1)
				playerList.remove(i);
		}
		//buffg.drawImage(player, x, y, 110, 110, this);
		
		// 프레임에 player에 저장된 변환되는 이미지를 x=100, y=100 좌표에 110x110해상도로 그려넣습니다.

	}
	
	
//	private Missile missile; // 미사일 인터페이스 참조변수
//	private Image missileImage; // 미사일 이미지
	
	public void Draw_missile() { // 미사일 이미지 출력
		for (int i=0; i<missileList.size(); ++i) {
			Image image = missileList.get(i);
			buffg.drawImage(image, PlayerCenter.getPlayerCenter().getPlayer().getPlayerX()
					, PlayerCenter.getPlayerCenter().getPlayer().getPlayerY(), 30, 30, this);
			missile.move();
			if (missile.getBulletX()>=710 && missile.getBulletX()<=-20 && 
					missile.getBulletY()>=710 && missile.getBulletY()<=10) {
				missileList.remove(i);
			}
		}

	}
	@Override
	public void run() { // 스레드 무한으로 실행시킬 메서드
		try { // 스레드 예외처리
			while (true) {
				KeyProcess(); //키보드 이벤트
				SpaceProcess(); // 미사일 키보드 이벤트
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				th.sleep(20); // CPU 사용량을 줄이기 위해 잠시 대기
			}
		} catch (Exception e) {
		}
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
		case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가(미사일 발사)
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
//		case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가(미사일 발사)
//			keySpace = false;
//			break;
		}
	}
	public void SpaceProcess() { // 미사일 이미지 가져오기
		if(keySpace) { // 스페이스키를 눌렀을때
			if(PlayerCenter.getPlayerCenter().getType().equals("PlayerL")) { //플레이어 이미지가 L일때
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile ,xx, yy);
				missileList.add((Image) missile);
			}else if(PlayerCenter.getPlayerCenter().getType().equals("PlayerR")) { 
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile,xx, yy);
				missileList.add((Image) missile);
			}else if(PlayerCenter.getPlayerCenter().getType().equals("PlayerU")) { 
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile,xx, yy);
				missileList.add((Image) missile);
			}else if(PlayerCenter.getPlayerCenter().getType().equals("PlayerD")) { 
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile,xx, yy);
				missileList.add((Image) missile);
			}else if(PlayerCenter.getPlayerCenter().getType().equals("PlayerLD")) { 
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile,xx, yy);
				missileList.add((Image) missile);
			}else if(PlayerCenter.getPlayerCenter().getType().equals("PlayerLU")) { 
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile,xx, yy);
				missileList.add((Image) missile);
			}else if(PlayerCenter.getPlayerCenter().getType().equals("PlayerRU")) { 
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile,xx, yy);
				missileList.add((Image) missile);
			}else if(PlayerCenter.getPlayerCenter().getType().equals("PlayerRD")) { 
				missileImage = MissileCenter.getMissileCenter().getMissile().getImage(); // MissileCenter클래스 String타입 type변수에 PlayerL에 맞는 미사일 이름 대입
				int xx = x + PlayerCenter.getPlayerCenter().getPlayer().getPlayerWidth() / 2 - MissileCenter.getMissileCenter().getMissile().getWidth() / 2;
				int yy = y + PlayerCenter.getPlayerCenter().getPlayer().getPlayerHeight() / 2 - MissileCenter.getMissileCenter().getMissile().getHeight() / 2; 
				missile = MissileCenter.getMissileCenter().getMissileXY(missile,xx, yy);
				missileList.add((Image) missile);
			}
		}
	}
	public void KeyProcess() { // player이미지가 JFrame너비,높이를 넘어가지 못하게 이벤트 발생 
		if (keyLeft&&!keyRight&&!keyUp&&!keyDown) { // ← 좌측키
			if(x<=-20) {
				PlayerCenter.getPlayerCenter().setType("PlayerL"); // 방향키를 누르면 PlayerCenter Type변경
				MissileCenter.getMissileCenter().setPlayertype("PlayerL"); // 방향키를 누르면 MissileCenter playertype에 문자열대입
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer(); // Image Player변수에 방향키에 맞는 이미지 대입
				playerList.add(player); // 해당 이미지를 PlayerList에 add.
				x = -20;
				x += 5;
			}else {
				PlayerCenter.getPlayerCenter().setType("PlayerL");
				MissileCenter.getMissileCenter().setPlayertype("PlayerL");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				x -= 5;
			}
		}
		if (keyRight&&!keyLeft&&!keyUp&&!keyDown) { // → 우측키
			if(x>=710) {
				PlayerCenter.getPlayerCenter().setType("PlayerR");
				MissileCenter.getMissileCenter().setPlayertype("PlayerR");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				x = 710;
				x += 5;
			}else {
				PlayerCenter.getPlayerCenter().setType("PlayerR");
				MissileCenter.getMissileCenter().setPlayertype("PlayerR");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				x += 5;
			}
		}
		if (keyUp&&!keyDown&&!keyLeft&&!keyRight) { // ↑ 윗키
			if(y<=10) { // y가 좌표 10보다 같거나 작을때
				PlayerCenter.getPlayerCenter().setType("PlayerU");
				MissileCenter.getMissileCenter().setPlayertype("PlayerU");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				y = 10; // y좌표 10에서 멈추게하기
				y -=5;
			}else { // 아닐시
				PlayerCenter.getPlayerCenter().setType("PlayerU");
				MissileCenter.getMissileCenter().setPlayertype("PlayerU");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				y -= 5; // y좌표에서 이동
			}
		}
		if (keyDown && !keyUp && !keyLeft && !keyRight) { // ↓ 아랫키
			if (y >= 710) { // JFrame y+710를 넘어갈시
				PlayerCenter.getPlayerCenter().setType("PlayerD");
				MissileCenter.getMissileCenter().setPlayertype("PlayerD");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				y = 710; // JFrame y+710를 넘어갈시 멈춤
				y += 5;
			} else {
				PlayerCenter.getPlayerCenter().setType("PlayerD");
				MissileCenter.getMissileCenter().setPlayertype("PlayerD");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				y += 5;
			}
		}
		if (keyLeft&&keyUp&&!keyDown&&!keyRight){ // 좌측상단 ↖키
			if (x<=-20) {
				x = -20;
				if(y<=-10) {
					y = -10;
					PlayerCenter.getPlayerCenter().setType("PlayerLU");
					MissileCenter.getMissileCenter().setPlayertype("PlayerLU");
					player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
					playerList.add(player);
				}
				x -= 5;
				y -= 5;
			}else {
				PlayerCenter.getPlayerCenter().setType("PlayerLU");
				MissileCenter.getMissileCenter().setPlayertype("PlayerLU");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				x -= 5;
				y -= 5;
			}
		
		}
		if (keyLeft&&keyDown&&!keyUp&&!keyRight){ // ↙ 우측하단키
			if (x <= -20) {
				x = -20;
				if(y >= 710) {
					y = 710;
					PlayerCenter.getPlayerCenter().setType("PlayerLD");
					MissileCenter.getMissileCenter().setPlayertype("PlayerLD");
					player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
					playerList.add(player);
				}
				x -= 5;
				y += 5;
			}else {
				PlayerCenter.getPlayerCenter().setType("PlayerLD");
				MissileCenter.getMissileCenter().setPlayertype("PlayerLD");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
				playerList.add(player);
				x -= 5;
				y += 5;
			}
		
		}
		if (keyRight&&keyUp&&!keyDown&&!keyLeft){ // ↗ 우측상단키
			if (x >= 710) {
				x = 710;
				if(y <= -10) {
					y = -10;
					PlayerCenter.getPlayerCenter().setType("PlayerRU");
					MissileCenter.getMissileCenter().setPlayertype("PlayerRU");
					player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
					playerList.add(player);
				}
				x += 5;
				y -= 5;
			}else {
				PlayerCenter.getPlayerCenter().setType("PlayerRU");
				MissileCenter.getMissileCenter().setPlayertype("PlayerRU");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
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
					PlayerCenter.getPlayerCenter().setType("PlayerRD");
					MissileCenter.getMissileCenter().setPlayertype("PlayerRD");
					player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
					playerList.add(player);
				}
				x += 5;
				y += 5;
			}else {
				PlayerCenter.getPlayerCenter().setType("PlayerRD");
				MissileCenter.getMissileCenter().setPlayertype("PlayerRD");
				player = PlayerCenter.getPlayerCenter().getPlayer().getPlayer();
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
