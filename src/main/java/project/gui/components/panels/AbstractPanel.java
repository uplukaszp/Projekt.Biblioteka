package project.gui.components.panels;

import javax.swing.JPanel;

public abstract class AbstractPanel<ModelItem> extends JPanel {

	protected ModelItem item;

	public AbstractPanel() {
		init();
	}

	public void setData(ModelItem item) {
		try {
			throw new Exception("Unimplemented");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected abstract void readFromFields();

	public ModelItem getData() {
		readFromFields();
		return item;
	}

	protected abstract void init();

}
