package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import missile.Missile;
import missile.MissileCenter;
import player.Player;

public class GameFrame extends JFrame implements KeyListener, Runnable {

	
	private int f_width = 800; // 프레임 너비설정
	private int f_height = 800; // 프레임 높이 설정
	private Player playerClass = new Player(); // Player클래스 의존
	ArrayList<Missile> missileList = new ArrayList();
	// private ArrayList
	
	// 키보드셋팅
	boolean keyUp = false; // 윗키
	boolean keyDown = false; // 아래키
	boolean keyLeft = false; // 왼쪽
	boolean keyRight = false; // 오른쪽
	boolean keySpace = false; // 스페이스 (총탄발사)
	
	// 참조형
	private Missile missile; // 미사일 참조변수
	private MissileCenter missilecenter; // 미사일컨트롤 참조변수
	
	//캐릭터 이미지
	private Image player; // 현재 플레이어 이미지
	private Image playerL; // 좌측면 캐릭터 이미지
	public boolean isKeyUp() {
		return keyUp;
	}

	public boolean isKeyDown() {
		return keyDown;
	}

	public void setKeyDown(boolean keyDown) {
		this.keyDown = keyDown;
	}

	public boolean isKeyLeft() {
		return keyLeft;
	}

	public void setKeyLeft(boolean keyLeft) {
		this.keyLeft = keyLeft;
	}

	public boolean isKeyRight() {
		return keyRight;
	}

	public void setKeyRight(boolean keyRight) {
		this.keyRight = keyRight;
	}

	public void setKeyUp(boolean keyUp) {
		this.keyUp = keyUp;
	}

	private Image playerR; // 우측면 캐릭터 이미지
	private Image playerU; // 상단면 캐릭터 이미지
	private Image playerD; // 하단면 캐릭터 이미지
	
	// 배경 이미지
	private Image background1; // 화염행성 배경
	private Image background2; // 얼음행성 배경
	
	private Image missileImage; // 미사일 이미지
	
	private int x = 100; // player  x좌표
	private int y = 100; // player  y좌표

	private Image buffImage; // 더블 버퍼링용 이미지
	private Graphics buffg; // 더블 버퍼링용 그리기

	Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지를 불러오기 위한 톨킷
	Thread th; // 스레드
	Thread py; // 플레이어 스레드
	
//	private static GameFrame gameframe = new GameFrame(); //싱글톤
//	public GameFrame getGameFrame() {
//		return gameframe;
//	}
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
		player = playerClass.getPlayer(); // 플레이어 정면이미지 생성
		playerL = playerClass.getPlayerL(); // 플레이어 좌측이미지 생성
		playerR = playerClass.getPlayerR(); // 플레이어 우측이미지 생성
		playerU = playerClass.getPlayerU(); // 플레이어 상단이미지 생성
		playerD = playerClass.getPlayerD(); // 플레이어 하단이미지 생성
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
		g.drawImage(buffImage, 0, 0, this); // 화면 버퍼에 그린 그림을 버퍼에 덮어씌우기
	}
	
	public void Draw_background() { // 이미지 그릴부분
		buffg.drawImage(background1, 0, 0, f_width, f_height, this); // 프레임에
	}
	
	public void Draw_Player() {
		buffg.drawImage(player, x, y, 110, 110, this);
		// 프레임에 player에 저장된 변환되는 이미지를 x=100, y=100 좌표에 110x110해상도로 그려넣습니다.
	}
	public void Draw_missile() {
		for (int i=0; i<missileList.size(); ++i) {
			missile = missileList.get(i);
			buffg.drawImage(missileImage, missile.getBulletX(), missile.getBulletY(), this);
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
				KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
			//	directionProcess(); // 이미지
				missileOneProcess(); // 미사일 가져오기
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
		case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가(미사일 발사)
			keySpace = false;
			break;
		}
	}

	public void KeyProcess() { // player이미지가 JFame너비,높이를 넘어가지 못하게 이벤트 발생 
		if (keyUp) {
 			if(y<=10) { // y가 좌표 10보다 같거나 작을때
 				player = playerU; // 이미지 변환
 				playerClass.setMy(player);
 				y = 10; // y좌표 10에서 멈추게하기
 			}else { // 아닐시
 				player = playerU; // 이미지변환
 				playerClass.setMy(player);
 				y -= 5; // y좌표에서 이동
 			}
 		}
 		if (keyDown) {
 			if(y>=710) { 
 				player = playerD;
 				playerClass.setMy(player);
 				y = 710;	
 			}else {
 				player = playerD;
 				playerClass.setMy(player);
 				y += 5;
 			}
 		}
 		if (keyLeft) {
 			if(x<=-20) {
 				player = playerL;
 				playerClass.setMy(player);
 				x = -20;
 			}else {
 				player = playerL;
 				playerClass.setMy(player);
 				x -= 5;
 			}
 		}
 		if (keyRight) {
 			if(x>=710) {
 				player = playerR;
 				playerClass.setMy(player);
 				x = 710;
 			}else {
 				player = playerR;
 				playerClass.setMy(player);
 				x += 5;
 			}
 		}
 	}
	public void missileOneProcess() { // 미사일 이미지 가져오기
		if(keySpace==true) { // 스페이스가 missile = (Missile)PlayerClass
			missile = (Missile) playerClass.getMissile(); // player type에 따라 해당 미사일 이미지 객체 주소를 missile에 대입
			missileImage = missile.getImage(); // missile 객체의 이미지를 missileImage에 대입
			int xx = x + player.getWidth(null) / 2 - missileImage.getWidth(null) / 2; // player,MissileImage 중앙좌표
			// player x좌표 + player이미지 너비 중앙 - missile이미지 너비중앙을 xx에 대입한다
			// https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html#getWidth-java.awt.image.ImageObserver- 너비가 null일때 초기값 api에 -1로 표기되어있음
			missile = playerClass.getMissileCenter().makeMissile(missile, xx, y);
			missileList.add(missile); // missileList에 미사일 이미지 add
			keySpace = false; 
		}
	}
//	public MissileCenter directionProcess() { // GameFrame클래스missileProcess메서드, Player클래스setDirection메서드 참조 
//		if (keySpace==true) { // keySpace가 ture 일때
//			if (player==playerL) { // Player 이미지가 playerL과 같다면
//				missilecenter.setA(1); // playerClass Direction 메서드에 변수a값 set해주기
//			}else if (player==playerR) {
//				missilecenter.setA(2);
//			}else if (player==playerU) {
//				missilecenter.setA(3);
//			}else if (player==playerD) {
//				missilecenter.setA(4);
//			}
//		}
//		return null;
//	}
	
	@Override
	public void keyTyped(KeyEvent e) { // 문자이벤트를 처리하는 타입

	}

}
