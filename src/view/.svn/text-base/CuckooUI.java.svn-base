package view;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import cuckoo.Cuckoo;

import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dimension;


public class CuckooUI extends JPanel {
	private JTextField txtKey;
	private JTextField txtValue;

	JComboBox comboBox;
	JLabel valueLabel;


	private JTable table;


	/**
	 * Create the panel.
	 */
	
	Cuckoo<Integer,String> cuckoo;
	public CuckooUI() {

		cuckoo = new Cuckoo<Integer,String>();

		setPreferredSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 400));
		setMaximumSize(new Dimension(600, 400));

		setForeground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JButton btnEnter = new JButton("ENTER");
		valueLabel = new JLabel(" ");
		valueLabel.setBounds(229, 143, 152, 40);
		add(valueLabel);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(comboBox.getSelectedIndex() == 0){ //PUT Method
					cuckoo.put(Integer.parseInt(txtKey.getText()), txtValue.getText());			
					valueLabel.setText("Added Successfully!");
				}
				if(comboBox.getSelectedIndex() == 1){ //GET Method
					if(cuckoo.get(Integer.parseInt(txtKey.getText()))==null)
						valueLabel.setText("Key doesn't exist!");
					else
						valueLabel.setText(cuckoo.get(Integer.parseInt(txtKey.getText())));
				}
				if(comboBox.getSelectedIndex() == 2){ //REMOVE Method
					if(cuckoo.get(Integer.parseInt(txtKey.getText())).equals(null))
						valueLabel.setText("Nothing to remove!");
					else{
						String value = cuckoo.get(Integer.parseInt(txtKey.getText()));
						cuckoo.remove(Integer.parseInt(txtKey.getText()));
						valueLabel.setText(value);
					}
				}
				if(comboBox.getSelectedIndex() == 3){ //CONTAINS Method
					if(cuckoo.contains(Integer.parseInt(txtKey.getText())))
						valueLabel.setText("true");
					else
						valueLabel.setText("false");
				}
				if(comboBox.getSelectedIndex() == 4){//CLEAR Method
					cuckoo.clear();
					valueLabel.setText("Cleared all values!");
				}
			}
		});
		btnEnter.setBounds(414, 316, 139, 53);
		add(btnEnter);
		
		txtKey = new JTextField();
		txtKey.setText("Key (Integer)");
		txtKey.setBounds(73, 319, 114, 19);
		add(txtKey);
		txtKey.setColumns(10);
		
		txtValue = new JTextField();
		txtValue.setText("Value (String)");
		txtValue.setColumns(10);
		txtValue.setBounds(73, 350, 114, 19);
		add(txtValue);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"PUT", "GET", "REMOVE", "CONTAINS", "CLEAR"}));
		comboBox.setBounds(229, 316, 139, 53);
		add(comboBox);

		
		table = new JTable();
		table.setBounds(70, 100, 16, 1);
		add(table);


	}
}
