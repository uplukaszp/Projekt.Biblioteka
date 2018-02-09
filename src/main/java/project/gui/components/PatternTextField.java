package project.gui.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class PatternTextField extends JTextField {

	JTextField myfield;
	Color c;
	String regex;
	public PatternTextField(final String regex,String toolTip) {
		setBackground(Color.WHITE);
		myfield = this;
		this.regex=regex;
		c = myfield.getBackground();
		this.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				isPropriety();
			}
			public void focusGained(FocusEvent e) {

			}
		});
		this.setToolTipText(toolTip);
	}
	public PatternTextField(String regex)
	{
		this(regex,"");
	}
	public PatternTextField() {
		this("","");
	}

	public void clear() {
		myfield.setBackground(c);
	}
	public boolean isPropriety() {
		
		if (!myfield.getText().matches(regex)) {
			myfield.setBackground(new Color(255, 150, 150));
			return false;
		} else
			myfield.setBackground(c);
		return true;
	
	}
	public void reset() {
		myfield.setBackground(c);
	}
	
}
