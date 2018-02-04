package project.gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.JToolTip;

public class PatternTextField extends JTextField {

	JTextField myfield;
	Color c;
	
	public PatternTextField(final String regex,String toolTip) {
		myfield = this;
		c = myfield.getBackground();
		this.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				if (!myfield.getText().matches(regex)) {
					myfield.setBackground(new Color(255, 0, 0));
				} else
					myfield.setBackground(c);
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

}
