package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bullets.Bullet;
import towers.Tower;
import monsters.Monster;

public class GamePanel extends JPanel implements MouseListener{
	
	private GameObject[] gameObjects = new GameObject[10000] ;
	private int gameObjectsCount;
	
	
	private Map map = new Map();
	
	public GamePanel(){
		addMouseListener(this);
	}
	
	public void addGameObject(GameObject go){
		gameObjects[gameObjectsCount++] = go;
	
	}
	
	public void deleteGameObject(GameObject go){
		
		int index=0;
		for (int i = 0; i < gameObjectsCount; i++) {
			if(gameObjects[i].equals(go)){
				index =i;
				break;
			}
		}
		for (int i = index; i < gameObjectsCount; i++) {
			gameObjects[i]=gameObjects[i+1];	
		}
		gameObjectsCount--;
		
		
	}
	
	public int getGameObjectArrayLength(){
		return gameObjectsCount;
	}
	
	public ArrayList<Monster> getMonsters(){
		ArrayList<Monster> m = new ArrayList<Monster>();
		
		
		for (int i=0; i<gameObjectsCount;i++) {
			if(gameObjects[i] instanceof Monster){
				m.add((Monster)gameObjects[i]);
			}
		}
		
		
		return m;
	}
	
	public void paint(Graphics g){
		map.paint(g);
		for (int i=0; i<gameObjectsCount;i++) {
			gameObjects[i].paint(g);
		}
		
		
	}
	
	public void update(int deltaTime){
		
		for (int i=0; i < gameObjectsCount; i++) {
				gameObjects[i].update(deltaTime);
			}
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			int tempRow = e.getY() / Block.getHeight();
			int tempCol = e.getX() / Block.getWidth();
			double posX = tempCol * Block.getWidth() + Block.getWidth() / 2;
			double posY =  tempRow * Block.getHeight() + Block.getHeight() / 2;
			

			Tower t =new Tower(posX, posY,20.0,200.0,10.0,5.0,Toolkit.getDefaultToolkit().getImage("images/redTower.png"),this);
			
			if (map.placeTower(tempRow, tempCol, t)) {
				addGameObject(t);
			}
			
		}
		
		if (e.getButton() == MouseEvent.BUTTON3) {
			addGameObject(new Monster(15, map.getPath(), 50,100,this));
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
