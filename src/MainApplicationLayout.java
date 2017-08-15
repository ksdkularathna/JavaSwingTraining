import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.swing.training.jpanels.PatientRegestrationPanel;
import com.swing.training.jpanels.PatientSearchPanel;
import com.swing.training.jpanels.TablePanel;
import com.swing.training.tablemodels.PatientTableModel;

public class MainApplicationLayout {

	public static final String PROJECT_NAME = "Patient Registration";

	private static TablePanel tablePanel;
	private static PatientRegestrationPanel patientRegestration;
	private static PatientSearchPanel searchPanel;
	private static JFrame jFrame;
	private static JPanel mainPanel;

	public static void main(String[] args) {

		tablePanel = new TablePanel(new PatientTableModel());
		patientRegestration = new PatientRegestrationPanel(tablePanel);
		searchPanel = new PatientSearchPanel(tablePanel);
		jFrame = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		patientRegestration.setMaximumSize(patientRegestration
				.getPreferredSize());
		mainPanel.add(patientRegestration);
		mainPanel.add(searchPanel);
		mainPanel.add(tablePanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		GridBagConstraints gbConstraintsCloseBtn = new GridBagConstraints();
		gbConstraintsCloseBtn.gridx = 0;
		gbConstraintsCloseBtn.gridy = 0;
		gbConstraintsCloseBtn.weightx = 1;
		gbConstraintsCloseBtn.weighty = 1;
		gbConstraintsCloseBtn.insets = new Insets(5, 5, 5, 12);
		gbConstraintsCloseBtn.anchor = GridBagConstraints.NORTHEAST;
		buttonPanel.add(closeBtn, gbConstraintsCloseBtn);
		mainPanel.add(buttonPanel);

		jFrame.setTitle(PROJECT_NAME);
		jFrame.setResizable(Boolean.FALSE);
		jFrame.setSize(550, 550);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(mainPanel);
	}
}
