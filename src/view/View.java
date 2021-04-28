package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import game_logic.*;
/**
 * 
 * Az alap view osztaly melybol minden view szarmazik majd, amit hasznalunk. Ez az osztaly a JLabel-bol szarmazik.
 * @author Gabor
 *
 */
public class View extends JLabel implements Drawable {

	/**
	 * 
	 * A felhasznalt kepeket taroljuk itt, midnen view-hoz egy kulon .png kiterjesztesu fajlt.
	 */
	protected ImageIcon icon;

	private static final long serialVersionUID = 4819391159119195619L;
	/**
	 * 
	 *  Az MVC felosztasban segit nekunk kesobbb a GameController eltarolasa.
	 */
	protected Game baseGameController;

	protected int x, y, w, h;

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
	
	public int getPosx()
	{
		return x;
	}
	
	public int getPosy()
	{
		return y;
	}
	
	public void scale(int w, int h)
	{
		icon.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT);
	}
	
	public View() {
		w = 500;
		h = 500;
		//this.baseGameController = baseGameController;
		setOpaque(false);
		setFocusable(false);
	}

	/**
	 * 
	 * Kirajzolasert felelos fuggveny, mely eloszor validalja, majd ujrarajzolja a grafikus komponenst.
	 */
	public void Draw() {
		this.getParent().validate();
		this.getParent().repaint();
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
