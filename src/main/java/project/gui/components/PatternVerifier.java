package project.gui.components;

import java.awt.Component;
import java.awt.Container;

public class PatternVerifier {
	Component components[];

	public PatternVerifier(Component[] components) {
		super();
		this.components = components;
	}

	public boolean areFieldsMatched() {
		boolean match = true;
		for (Component jComponent : components) {

			match = isFieldMatched(jComponent, match);
		}
		System.out.println(match);
		return match;
	}

	private boolean isFieldMatched(Component jComponent, boolean match) {

		if (jComponent instanceof Container) {
			Container c = (Container) jComponent;
			for (Component component : c.getComponents()) {
				match = isFieldMatched(component, match);
			}
		}
		if (jComponent instanceof PatternTextField) {
			PatternTextField field = (PatternTextField) jComponent;
			if (!field.isPropriety()) {
				System.out.println(false);
				match = false;
			}
		}
		return match;
	}

	public void reset() {
		for (Component component : components) {
			clearTextFields(component);
		}
	}

	void clearTextFields(Component component) {
		if (component instanceof Container) {
			Container container = (Container) component;
			for (Component c : container.getComponents()) {
				if (c instanceof Container) {
					clearTextFields(c);
				}
			}
		}
		if(component instanceof PatternTextField)
		{
			PatternTextField field = (PatternTextField) component;
			field.reset();
		}
	}

	
}
