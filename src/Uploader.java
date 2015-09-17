
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import youxian.ncumap.ActivityData;

@SuppressWarnings("serial")
public class Uploader extends JFrame{
	private Uploader myframe;
	private TablePanel tablePanel;
	private MyPanel myPanel;
	private EditPanel editPanel;
	private JMenuBar jmb;
    private JMenu Mode = new JMenu("Mode"),Edit = new JMenu("Edit");;
    private JMenuItem [] mode_item = new JMenuItem[2];//選單1
    private JMenuItem [] edit_item = new JMenuItem[2];//選單2
	public Uploader(){
		myframe=this;
		//myPanel=new MyPanel();
		//tablePanel=new TablePanel();
		init();
		initMenu();
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		//this.add(myPanel);
		//myPanel.setBounds(0, 0, 500, 650);
		//this.add(tablePanel);
		//tablePanel.setBounds(0, 0, 500, 650);
		this.setSize(500,700);
        this.setLocation(50,10); 
        this.setResizable(false);//視窗放大按鈕無效 
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initMenu() {
		// TODO Auto-generated method stub
		jmb = new JMenuBar();
        this.setJMenuBar(jmb); //加入工具列
        //遊戲的選擇項目
        jmb.add(Mode);
        mode_item[0] = new JMenuItem("Search");
        mode_item[1] = new JMenuItem("Upload");
        mode_item[0].addActionListener(searchMode);
        mode_item[1].addActionListener(uploadMode);
        Mode.add(mode_item[0]);
        Mode.add(mode_item[1]);
        //關於的選擇項目
        jmb.add(Edit);
        edit_item[0] = new JMenuItem("Delete Activity");
        edit_item[1] = new JMenuItem("Edit Activity");
        edit_item[0].addActionListener(deleteActivity);
        edit_item[1].addActionListener(editActivity);
        Edit.add(edit_item[0]);
        Edit.add(edit_item[1]);

	}
	ActionListener searchMode = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			//myframe.removeAll();
			if(myPanel!=null)
				if(myPanel.isShowing())
					myframe.remove(myPanel);
			if(editPanel!=null)
				if(editPanel.isShowing())
					myframe.remove(editPanel);
			if(tablePanel!=null && tablePanel.isShowing()){
				
			}
			else{
				tablePanel = new TablePanel();
				myframe.add(tablePanel);
				tablePanel.setBounds(0, 0, 500, 650);
			}
			
		}	

	};
	ActionListener uploadMode = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//myframe.removeAll();
			if(tablePanel!=null)
				if(tablePanel.isShowing())
					myframe.remove(tablePanel);
			if(editPanel!=null)
				if(editPanel.isShowing())
					myframe.remove(editPanel);
			if(myPanel!=null && myPanel.isShowing()){
				
			}
			else{
				myPanel=new MyPanel();
				myframe.add(myPanel);
				myPanel.setBounds(0, 0, 500, 650);
			}
			
		}

	};	
	ActionListener deleteActivity = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				String id = JOptionPane.showInputDialog(null
						,"Enter ID of the Activity","Delete Activity",1);
				if(id!=null){
					String s=Connector.SendDeleteDataCommand(Integer.parseInt(id));
					JOptionPane.showMessageDialog(Uploader.this, "Delete "+s);
				}
				
			}

	};
	ActionListener editActivity = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(editPanel!=null && editPanel.isShowing()){
				//JOptionPane.showMessageDialog(Uploader.this, "Not finishing your edittiing");
				if (JOptionPane.showConfirmDialog(Uploader.this, "Are you sure to quit editting?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    // yes option
					EditData();
				} else {
				    // no option
				}
			}
			else if (editPanel==null || !editPanel.isShowing()){
				EditData();
			}
			
			}

	};	
	private void EditData(){
		String id = JOptionPane.showInputDialog(null
				,"Enter ID of the Activity","Edit Activity",1);
		if(id!=null){
			ActivityData a =Connector.SendGetDataByIdCommand(Integer.parseInt(id));
			if(a==null)
				JOptionPane.showMessageDialog(Uploader.this, "Get Data Failure");
			else{
				if(tablePanel!=null && tablePanel.isShowing()){
					myframe.remove(tablePanel);
					
				}
				else if(myPanel!=null && myPanel.isShowing()){
					myframe.remove(myPanel);

				}
				else if(editPanel!=null && editPanel.isShowing()){
					myframe.remove(editPanel);

				}
				editPanel=new EditPanel(a);
				myframe.add(editPanel);
				editPanel.setBounds(0, 0, 500, 650);
			}
		}
	}
	public static void main(String[] args) { 
		new Uploader();
		
	}
}
