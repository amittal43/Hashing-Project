package hopscotchView;

import java.util.Map;

import hopscotch.Hopscotch;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class HopTable extends JTable{

	public HopTable(Hopscotch hmap){
		update(hmap);
	}

	public void update(Hopscotch hmap){
		TableModel tm = toTableModel(hmap);
	
		setModel(tm);
		//repaint();

	}

	public static TableModel toTableModel(Hopscotch hmap) {
		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "Key", "Value" , "Base" }, 0
				);
		model.addRow(new Object[] { "KEY", "VALUE", "BASE"});

		for(int i=0;i<hmap.getSize();i++){
			if(hmap.table[i]==null){
				model.addRow(new Object[] { " ", "  ", " "});
				continue;
			}
			model.addRow(new Object[] { hmap.table[i].getKey(), hmap.table[i].getValue(), hmap.table[i].getBase()});
		}

		return model;
	}


}
