package project.gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class PatternTextField extends JTextField {

	JTextField myfield;
	Color c;

	public PatternTextField(final String regex) {
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
	}

	public PatternTextField() {
		this("");

	}

	public void clear() {
		myfield.setBackground(c);
	}

}
