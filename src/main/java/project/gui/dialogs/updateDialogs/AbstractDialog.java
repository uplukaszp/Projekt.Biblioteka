package project.gui.dialogs.updateDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import project.gui.components.PatternVerifier;
import project.gui.components.panels.AbstractPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class AbstractDialog<ModelItem> extends JDialog {

	protected PatternVerifier verifier;
	protected AbstractPanel<ModelItem> contentPane;

	@Autowired
	public AbstractDialog(AbstractPanel<ModelItem> panel) {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 427, 298);
		getContentPane().setLayout(new BorderLayout());

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> okAction());

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> cancelAction());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			contentPane = panel;
			getContentPane().add(contentPane, BorderLayout.CENTER);
		}
		verifier = new PatternVerifier(contentPane.getComponents());
	}

	protected abstract void cancelAction();

	protected abstract void okAction();

	public void setData(ModelItem data) {
		try {
			contentPane.setData(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ModelItem getData() {
		return (ModelItem) contentPane.getData();
	}

	@Override
	public void setVisible(boolean visible) {
		if (!visible)
			verifier.reset();
		super.setVisible(visible);
	}
}
