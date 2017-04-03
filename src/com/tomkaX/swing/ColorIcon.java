package com.tomkaX.swing;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

public class ColorIcon implements Icon {
	
	private Color color = Color.WHITE;

    /**
     * Constructor for implement custom colored icon 
     * @param color - custom parameter for creating colored icon
     */
	public ColorIcon( Color color) {
		this.color = color;
	}
    
	/**
	 * Default constructor for implement default icon 
	 */
	public ColorIcon() {
	}

	@Override
	public void paintIcon( Component c,  Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke dashed =new BasicStroke(3.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f);
		Ellipse2D.Double circle = new Ellipse2D.Double(x+1, y+1, 14, 14);
		Ellipse2D.Double circleBorder = new Ellipse2D.Double(x, y, 15, 15);
		g2.setColor(getColor());
		g2.setRenderingHints(hints);
		g2.fill(circle);
		Composite oldComposite=g2.getComposite();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
		g2.setColor(new Color(1,1,1,1));
		g2.setStroke(dashed);
		g2.draw(circleBorder);
		g2.setComposite(oldComposite);
	}

	@Override
	public int getIconWidth() {
		return 15;
	}

	@Override
	public int getIconHeight() {
		return 15;
	}

	public Color getColor() {
		return color;
	}

	public void setColor( Color color) {
		this.color = color;
	}


}
