package hopscotchView;

import hopscotch.Hopscotch;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HopscotchUI extends JPanel {
	private JTextField txtKey;
	private JTextField txtValue;

	/**
	 * Create the panel.
	 */

	Hopscotch<Integer,String> hmap;
	public HopscotchUI() {
		setForeground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());

		hmap = new Hopscotch();
		JLabel title = new JLabel("                            HOPSCOTCH MAP \n \n \n");
		title.setFont(new Font("Serif",Font.BOLD,20));
		add(title,BorderLayout.NORTH);

		final HopTable htable =  new HopTable(hmap);
		add(htable,BorderLayout.CENTER);

		JPanel newpanel = new JPanel();
		txtKey = new JTextField();
		txtKey.setText("Key (Integer)");
		txtKey.setBounds(73, 319, 114, 19);
		newpanel.add(txtKey);
		txtKey.setColumns(10);


		txtValue = new JTextField();
		txtValue.setText("Value (String)");
		txtValue.setColumns(10);
		txtValue.setBounds(73, 350, 114, 19);
		newpanel.add(txtValue);

		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"PUT", "REMOVE", "CONTAINS", "CLEAR"}));
		comboBox.setBounds(229, 316, 139, 53);

		comboBox.getSelectedIndex();
		newpanel.add(comboBox);

		JButton btnEnter = new JButton("ENTER");
		btnEnter.setBounds(414, 316, 139, 53);

		btnEnter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int key= Integer.parseInt(txtKey.getText());
				String value = txtValue.getText();
				int selection = comboBox.getSelectedIndex();


				switch (selection){
				case 0: 
					if(hmap.put(key, value)!=null)
						JOptionPane.showMessageDialog(HopscotchUI.this,"Key already exists: The value of the entry has been updated.","Put Entry",JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					if(hmap.remove(key)==null)
						JOptionPane.showMessageDialog(HopscotchUI.this,"Unable to Remove Entry: Key does not exist.","Remove Key",JOptionPane.INFORMATION_MESSAGE);
					break;
				case 2: 
					if(hmap.contains(key))
						JOptionPane.showMessageDialog(HopscotchUI.this,"This Key IS Present in the Map","Contains Key",JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(HopscotchUI.this,"This Key is NOT present in the Map","Contains Key",JOptionPane.INFORMATION_MESSAGE);

					break;
				case 3: 
					hmap.clear();
					break;

				}
				htable.update(hmap);
				System.out.println("Hi");
			}



		});
		newpanel.add(btnEnter);
		add(newpanel,BorderLayout.SOUTH);

	}
}
