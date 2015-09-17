import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import youxian.ncumap.ActivityData;

@SuppressWarnings("serial")
public class TablePanel extends JPanel{
	private JTable myTable;
	private JScrollPane myscroll;
	private JLabel lab;
	private JButton update_btn;
	private List<ActivityData> alldata;
	private String columnNames[] = { "ActivityId", "Address", "Title" };
	
	public TablePanel(){
		init();
		
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		lab=new JLabel("Search Database");
		this.add(lab);
		lab.setBounds(50, 30, 150, 30);
		update_btn=new JButton("Update");
		this.add(update_btn);
		update_btn.setBounds(250, 30, 80, 30);
		updateclick();
		myTable=new JTable(new DefaultTableModel(columnNames,0));
		myTable.setEnabled(false);
		myscroll = new JScrollPane(myTable);
		initTable();
		this.add(myscroll);
		myscroll.setBounds(25, 80, 450, 550);
	}
	
	private void initTable() {
		// TODO Auto-generated method stub
		//TableColumn column = null;
		myTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		myTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		myTable.getColumnModel().getColumn(2).setPreferredWidth(150);
	    
	}
	private void updateclick() {
		// TODO Auto-generated method stub
		update_btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				alldata=connectToServer();
				if(alldata!=null){
					removeData();
					updateData();
				}
				else
					JOptionPane.showMessageDialog(TablePanel.this, "Update failure");
			}
		});
	}
	private void removeData() {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) myTable.getModel();
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
	}			
	private void updateData() {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) myTable.getModel();
		for(ActivityData a :alldata){
			model.addRow(new Object[]{""+a.getActivityId(),a.getAddress(),a.getTitle()});
		}
	}
	private List<ActivityData> connectToServer() {
		// TODO Auto-generated method stub
		return Connector.SendGetDataCommand();
	}
}
