package view;

import javax.swing.*;
import java.awt.*;
/**
 * 
 * Az alap view osztaly, melybol minden view szarmazik majd.
 * Ez az osztaly a JLabel-bol szarmazik.
 * @author Gabor
 *
 */
public class View extends JLabel implements Drawable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6429772857027251567L;

	/**
	 * A felhasznalt kepeket taroljuk itt, minden view-hoz egy kulon .png kiterjesztesu fajlt.
	 */
	protected ImageIcon icon;

	/**
	 *  X koordin�ta.
	 */
	protected int x;

	/**
	 *  Y koordin�ta.
	 */
	protected int y;

	/**
	 * Visszaadja az X koordin�t�t.
	 */
	public int getPosx()
	{
		return this.x;
	}

	/**
	 * Visszaadja az Y koordin�t�t.
	 */
	public int getPosy()
	{
		return this.y;
	}

	/**
	 * Be�ll�tja a kapott koordin�t�kat.
	 */
	public void setViewPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void scale(int w, int h)
	{
		icon.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT);
	}

	/**
	 * Konstruktor.
	 */
	public View() {
		setOpaque(false);
		setFocusable(false);
	}

	/**
	 * Text�ra kirajzolasa.
	 */
	@Override
	public void draw(Graphics g) { }

	/**
	 * Aszteroida inform�ci�k ki�r�sa.
	 */
	public void drawAsteroidInfos(Graphics g) {	}

}
