/**
 * 
 */
package com.tomkaX.swing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Class for working with traffic lights and icons for fit quality results
 * @author tkol
 */
public class ColorIconComponent {

	private final ColorIcon[] icons;

	/**
	 * Get method for array with color icons
	 * @return
	 */
	public ColorIcon[] getIcons() {
		return icons;
	}

	/**
	 * Default icon for not defined states
	 */
	final Icon defaultIcon = new ColorIcon(Color.LIGHT_GRAY);


	/**
	 * Constructor for ColorIconComponent have special behavior with 18 steps and N-number of steps.
	 * For 18-color steps used defined color array with web safe RYG color.
	 * For any other number of steps used function for generate N -color array 
	 * @param nOfSteps - number of color steps for quality assessment
	 */
	protected ColorIconComponent(int nOfSteps) {
		icons = new ColorIcon[nOfSteps];
		Color[] colArr;
		if (nOfSteps == 18)
			colArr = make18RGBColorArray();
		else
			colArr = makeRedYellowGreenColorArray(nOfSteps);
		int i = -1;
		for (Color c : colArr) {
			icons[++i] = generateIcon(c);
		}
	}
	
	/**
	 * Wrap function for creating colored icons
	 * @param color - define color for icon
	 * @return ColorIcon 
	 */
	public final  ColorIcon generateIcon( Color color) {
		ColorIcon icon = new ColorIcon(color);
		return icon;
	}

	/**
	 * Generate ColorIconLabel for creating custom label with colored icon for fit quality indicator
	 * @param color -   color for creating circle icon with this color
	 * @return JLabel component with defined color icon
	 */
	public static  JLabel generateColorIconLabel(Color color) {
		ColorIcon icon = new ColorIcon(color);
		JLabel label = new JLabel(icon, JLabel.CENTER);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(20, 20));
		return label;
	}

	/**
	 * Public method that takes a number and returns  color icon 
	 * @param value - parameter for return fit quality icon if between 0-1, a "failed" icon if value is "Infinity",
	 * "Not ready" icon for NaN, or a "misplaced baseline reference cursor" icon if called with magic number = (TBD)
	 * @return ColorIcon which represent fit quality result
	 */
	public  Icon getFitResultIcon( double value) {
		if (value >= 0 && value <= 1) {
			int index = (int) (value * icons.length);
			if ((index >= 0) && (index < icons.length))
				return icons[index];
		} 
		return defaultIcon;
	}
	
	/**
	 * Function makeRedYellowGreenColorArray is using for creating array of N - colors (Red-Yellow-Green)
	 * This pallete of colors isn't permanent and will change in accordance of number of quality steps
	 * @param n - number of quality step colors
	 * @return array of fir quality colors
	 */	
	public static  Color[] makeRedYellowGreenColorArray(int n) {
		Color[] colArr = new Color[n];	
		colArr[0] = new Color(0xFF, 0x00, 0x00);
		colArr[n / 2] = new Color(0xFC, 0xC3, 0x3C);
		colArr[n - 1] = new Color(0x00, 0xFF, 0x00);
		int incB = (0xE0 * 2 / n);
		int incS = (0x20 * 2 / n);
		for (int i = 1; i < n / 2; i++)
			colArr[i] = new Color(((0x10000 * (0xFF - incS * i)) & 0xFF0000) + ((0x100 * (incB * i)) & 0x00FF00));
		for (int i = 0; i < n / 2; i++)
			colArr[i + n / 2] = new Color(((0x10000 * (0xE0 - incB * i)) & 0xFF0000) + ((0x100 * (0xE0 + incS * i)) & 0x00FF00));
		return colArr;
	}
	
	/**
     * Made web safe RGB colors defined for 18 fixed color steps for quality assessment.
	 * This pallete of colors is permanent and used only for 18-steps quality assessment.
	 * @return array of Red-Yellow-Green color gradient set
	 */
	public static  Color[] make18RGBColorArray() {
		Color[] colArr = new Color[18];	
		int[]  array = {0xD7, 0x39, 0x36,0xD9, 0x3F, 0x36,0xE1, 0x5A, 0x39, 0xFF, 0x77, 0x3E,
				 0xEF, 0x7C, 0x42,0xF0, 0x82, 0x48,0xF3, 0x91, 0x55,0xF7, 0xA7, 0x41,
				 0xFC, 0xBF, 0x20,0xFC, 0xC3, 0x3C,0xFD, 0xC8, 0x58,0xDE, 0xC1, 0x57,
				 0xC4, 0xBB, 0x56,0xA5, 0xB4, 0x56,0x8F, 0xB0, 0x55,0x88, 0xAE, 0x55,0x5E, 0x9A, 0x4D,0x25, 0x85, 0x43};
		for (int i = 0; i+2 <= array.length-1 ; i=i+3)
			colArr[i/3] = new Color(array[i],array[i+1],array[i+2]);
		return colArr;
	}
	
	 
	 
	
	 
	 
	
	
	
	
	

	 
	 
	
	
	 
	
	 
	 
	 
	
	

}



