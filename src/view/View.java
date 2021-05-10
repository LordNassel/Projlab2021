package view;

import game_logic.Game;

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
	 * A felhasznalt kepeket taroljuk itt, minden view-hoz egy kulon .png kiterjesztesu fajlt.
	 */
	protected ImageIcon icon;

	/**
	 *  Az MVC felosztasban segit nekunk kesobbb a GameController eltarolasa.
	 */
	protected Game baseGameController;

	/**
	 *  X koordin�ta.
	 */
	protected int x;

	/**
	 *  Y koordin�ta.
	 */
	protected int y;

	/**
	 *  Sz�less�g.
	 */
	protected int w;

	/**
	 *  Magass�g.
	 */
	protected int h;

	/**
	 * Megjelenitesnel az egyes elemek poziciojat allitja be ezzel a metodus, mivel az ososztalyban van definialva,
	 * igy az egyes szarmaztatott osztalyok
	 * szelessegt es magassagat erjuk el.
	 * 
	 * @param x 
	 * @param y 
	 */
	public void setPos(int x, int y) {
		super.setBounds(x, y, w, h);
	}

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
		w = 500;
		h = 500;
		setOpaque(false);
		setFocusable(false);
	}

	/**
	 * Kirajzolasert felelos fuggveny, mely eloszor validalja, majd ujrarajzolja a grafikus komponenst.
	 */
	/*public void Draw() {
		this.getParent().validate();
		this.getParent().repaint();
	}*/

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
