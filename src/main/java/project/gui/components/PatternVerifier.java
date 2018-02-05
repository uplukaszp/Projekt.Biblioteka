package project.gui.components;

import java.awt.Component;

public class PatternVerifier {
	Component components[];

	public PatternVerifier(Component[] components) {
		super();
		this.components = components;
	}
	


	public boolean areFieldsMatched()
	{
		boolean match=true;
		for (Component jComponent : components) {
			if(jComponent instanceof PatternTextField)
			{
				PatternTextField field=(PatternTextField) jComponent;
				if(!field.isPropriety())
				{
					match=false;
				}
			}
		}
		return match;
	}
}
