import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import youxian.ncumap.ActivityData;

@SuppressWarnings("serial")
public class EditPanel extends JPanel{
	ActivityData myData;
	private JTextField username_text;
	private List<JTextField> starttime_texts;
	private List<JTextField> endtime_texts;
	private JTextField address_text;
	private JTextField website_text;
	private List<JCheckBox> type_check;
	private JTextField title_text;
	private JTextArea context_text;
	private JLabel ActivityId_lab;
	private JLabel UserName_lab;
	private JLabel StartTime_lab;
	//private List<JLabel> starttime_labs;
	private JLabel EndTime_lab;
	//private List<JLabel> endtime_labs;
	private JLabel Address_lab;
	private JLabel website_lab;
	private JLabel Title_lab;
	private JLabel Context_lab;
	private JButton image_btn;
	private JButton upload_btn;
	private JButton delete_btn;
	private JTextField imagepath_text;
	private File openfile;
	private BufferedImage img = null;
	private byte[] imgbytes;
	//private JLabel tmp=new JLabel("tmp");
	public EditPanel(ActivityData a){
		myData=a;
		initPanel();
		initData();
	}
	private void initData() {
		// TODO Auto-generated method stub
		username_text.setText(myData.getUserName());
		initTime();
		initType();
		address_text.setText(myData.getAddress());
		website_text.setText(myData.getWebsite());
		title_text.setText(myData.getTitle());
		context_text.setText(myData.getContext());
		initImage();
	}
	private void initImage() {
		// TODO Auto-generated method stub
		if(myData.getFileBytes()!=null){
			imagepath_text.setText("Image exists");
		}
		else if(myData.getFileBytes()==null){
			imagepath_text.setText("No Image");
		}
	}
	private void initType() {
		// TODO Auto-generated method stub
		if(myData.getType().charAt(0)=='1')
			type_check.get(0).setSelected(true);
		else
			type_check.get(0).setSelected(false);
		if(myData.getType().charAt(1)=='1')
			type_check.get(1).setSelected(true);
		else
			type_check.get(1).setSelected(false);
		if(myData.getType().charAt(2)=='1')
			type_check.get(2).setSelected(true);
		else
			type_check.get(2).setSelected(false);
	}
	private void initTime() {
		// TODO Auto-generated method stub
		String s[]=myData.getStartTime().split(" ");
		String d[]=s[0].split("-");
		String t[]=s[1].split(":");
		starttime_texts.get(0).setText(d[0]);
		starttime_texts.get(1).setText(d[1]);
		starttime_texts.get(2).setText(d[2]);
		starttime_texts.get(3).setText(t[0]);
		starttime_texts.get(4).setText(t[1]);
		String se[]=myData.getEndTime().split(" ");
		String de[]=se[0].split("-");
		String te[]=se[1].split(":");
		endtime_texts.get(0).setText(de[0]);
		endtime_texts.get(1).setText(de[1]);
		endtime_texts.get(2).setText(de[2]);
		endtime_texts.get(3).setText(te[0]);
		endtime_texts.get(4).setText(te[1]);
	}
	private void initPanel() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		ActivityId_lab=new JLabel("ActivityId: "+myData.getActivityId());
		this.add(ActivityId_lab);
		ActivityId_lab.setBounds(20, 20, 80, 30);
		username_text=new JTextField("");
		this.add(username_text);
		username_text.setBounds(190, 20, 100, 30);
		UserName_lab=new JLabel("UserName");
		this.add(UserName_lab);
		UserName_lab.setBounds(120, 20, 60, 30);
		startTimerow();
		endTimerow();
		initCheckbox();
		address_text=new JTextField("");
		this.add(address_text);
		address_text.setBounds(90, 180, 150, 30);
		Address_lab=new JLabel("Address");
		this.add(Address_lab);
		Address_lab.setBounds(20, 180, 60, 30);
		website_text=new JTextField("");
		this.add(website_text);
		website_text.setBounds(280, 180, 200, 30);
		website_lab=new JLabel("Website");
		this.add(website_lab);
		website_lab.setBounds(220, 180, 60, 30);
		title_text=new JTextField("");
		this.add(title_text);
		title_text.setBounds(90, 220, 350, 30);
		Title_lab=new JLabel("Title");
		this.add(Title_lab);
		Title_lab.setBounds(20, 220, 60, 30);
		context_text=new JTextArea("");
		this.add(context_text);
		context_text.setBounds(90, 260, 350, 300);
		Context_lab=new JLabel("Context");
		this.add(Context_lab);
		Context_lab.setBounds(20, 260, 60, 30);
		//this.add(tmp);
		//tmp.setBounds(20, 510, 100, 100);
		initButton();
	}
	private void initCheckbox() {
		// TODO Auto-generated method stub
		JCheckBox js =new JCheckBox("For Students");
		this.add(js);
		js.setBounds(20, 140, 100, 30);
		JCheckBox jt =new JCheckBox("For Teachers");
		this.add(jt);
		jt.setBounds(130, 140, 100, 30);
		JCheckBox jp =new JCheckBox("For People");
		this.add(jp);
		jp.setBounds(240, 140, 100, 30);
		type_check=new ArrayList<JCheckBox>();
		type_check.add(js);
		type_check.add(jt);
		type_check.add(jp);
	}
	private void initButton() {
		imagepath_text=new JTextField();
		this.add(imagepath_text);
		imagepath_text.setBounds(90, 570, 230, 30);
		imagepath_text.setEditable(false);
		String pathToImageSortBy1 = "resources/images/uploadimage.png";
		ImageIcon c1=new ImageIcon(getClass().getClassLoader().getResource(pathToImageSortBy1));
		image_btn=new JButton(c1);
		this.add(image_btn);
		image_btn.setBounds(350, 570, 30, 30);
		image_btnclick();
		String pathToImageSortBy = "resources/images/ic_cross.png";
		ImageIcon c=new ImageIcon(getClass().getClassLoader().getResource(pathToImageSortBy));
		delete_btn=new JButton(c);
		this.add(delete_btn);
		delete_btn.setBounds(320, 570, 30, 30);
		delete_btnclick();
		upload_btn=new JButton("上傳");
		this.add(upload_btn);
		upload_btn.setBounds(380, 570, 60, 30);
		upload_btnclick();
	}
	private void showImage(){
		if(openfile!=null){
			try {
			    img = ImageIO.read(openfile);
			} catch (IOException e) {
			}
		}
		//ImageIcon i=new ImageIcon(img);
		//tmp.setIcon(i);
		//tmp.setText("");
	}
	private void upload_btnclick() {
		// TODO Auto-generated method stub
		upload_btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(openfile!=null){
					try {
					    img = ImageIO.read(openfile);
					    String type=Files.probeContentType(openfile.toPath());
					    String t[]=type.split("/");
					    ByteArrayOutputStream baos = new ByteArrayOutputStream();
					    ImageIO.write(img, t[1], baos);
					    baos.flush();
					    imgbytes = baos.toByteArray();
					    baos.close();
					    //address_text.setText(type);
					    //title_text.setText(""+imgbytes.length);
					} catch (IOException e) {}					
				}
				if(!username_text.getText().isEmpty() && !startEmpty() && !endEmpty() 
						&& !address_text.getText().isEmpty() && !title_text.getText().isEmpty()
						&& !context_text.getText().isEmpty()){
					int n = JOptionPane.showConfirmDialog(
						    EditPanel.this,
						    "Would you like to upload?",
						    "Upload",
						    JOptionPane.YES_NO_OPTION);
					if(n==JOptionPane.YES_OPTION){
						String type=checkboxValue();
						ActivityData a=new ActivityData(myData.getActivityId(),username_text.getText(),getcurrentTimeString(),getstartTimeString(),getendTimeString(),
								title_text.getText(),address_text.getText(),website_text.getText(),"","",context_text.getText(),type,imgbytes);
						connectToServer(a);
					}
				}
				else{
					if(username_text.getText().isEmpty())
						UserName_lab.setForeground(Color.RED);
					if(startEmpty())
						StartTime_lab.setForeground(Color.RED);
					if(endEmpty())
						EndTime_lab.setForeground(Color.RED);
					if(address_text.getText().isEmpty())
						Address_lab.setForeground(Color.RED);
					if(title_text.getText().isEmpty())
						Title_lab.setForeground(Color.RED);
					if(context_text.getText().isEmpty())
						Context_lab.setForeground(Color.RED);
					JOptionPane.showMessageDialog(EditPanel.this, "These can’t be empty!");
				}
				
			}

			
			
		});
	}
	private String checkboxValue() {
		// TODO Auto-generated method stub
		String s="";
		for(JCheckBox j:type_check){
			if(j.isSelected()){
				s=s+"1";
			}
			else{
				s=s+"0";
			}
		}
		return s;
	}
	private void connectToServer(ActivityData a){
		String s=Connector.SendEditDataCommand(a);
		JOptionPane.showMessageDialog(EditPanel.this, s);
		if(s.contains("success")){
			clearMyPanel();
		}
	}
	private void clearMyPanel() {
		// TODO Auto-generated method stub
		username_text.setText("");
		for(JTextField st:starttime_texts){
			st.setText("");
		}
		for(JTextField et:endtime_texts){
			et.setText("");
		}
		address_text.setText("");
		website_text.setText("");
		title_text.setText("");
		context_text.setText("");
		openfile=null;
		imagepath_text.setText("");
		img = null;
		imgbytes=null;
		//tmp.setIcon(null);
		//tmp.setText("tmp");
		UserName_lab.setForeground(Color.BLACK);
		StartTime_lab.setForeground(Color.BLACK);
		EndTime_lab.setForeground(Color.BLACK);
		Address_lab.setForeground(Color.BLACK);
		Title_lab.setForeground(Color.BLACK);
		Context_lab.setForeground(Color.BLACK);
		for(JCheckBox j:type_check){
			j.setSelected(false);
		}
	}
	private String getcurrentTimeString() {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	private String getstartTimeString() {
		// TODO Auto-generated method stub
		String s=starttime_texts.get(0).getText()+"-"+starttime_texts.get(1).getText()+"-"+starttime_texts.get(2).getText()
				+" "+starttime_texts.get(3).getText()+":"+starttime_texts.get(4).getText();
		return s;
	}
	private String getendTimeString() {
		// TODO Auto-generated method stub
		String s=endtime_texts.get(0).getText()+"-"+endtime_texts.get(1).getText()+"-"+endtime_texts.get(2).getText()
				+" "+endtime_texts.get(3).getText()+":"+endtime_texts.get(4).getText();
		return s;
	}
	private boolean startEmpty() {
		// TODO Auto-generated method stub
		for(JTextField t:starttime_texts){
			if(t.getText().isEmpty())
				return true;
		}
		return false;
	}
	private boolean endEmpty() {
		// TODO Auto-generated method stub
		for(JTextField t:endtime_texts){
			if(t.getText().isEmpty())
				return true;
		}
		return false;
	}
	private void delete_btnclick() {
		// TODO Auto-generated method stub
		delete_btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(openfile!=null)
					openfile.delete();
				imagepath_text.setText("");
				myData.setFileBytes(null);
			}
			
		});
	}
	private void image_btnclick() {
		// TODO Auto-generated method stub
		
		image_btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FileFilter imageFilter = new FileNameExtensionFilter(
					    "Image files", ImageIO.getReaderFileSuffixes());
				final JFileChooser fc = new JFileChooser();
				fc.setAcceptAllFileFilterUsed(false);
				fc.addChoosableFileFilter(imageFilter);
				int returnVal = fc.showOpenDialog(EditPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if(openfile!=null)
						openfile.delete();
					openfile = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            imagepath_text.setText(""+openfile.getPath());
		            showImage();
		        } else {
		            //log.append("Open command cancelled by user." + newline);
		        }
			}
			
		});
	}
	private void startTimerow() {
		// TODO Auto-generated method stub
		StartTime_lab=new JLabel("StartTime");
		this.add(StartTime_lab);
		StartTime_lab.setBounds(20, 60, 60, 30);
		JTextField y_s =new JTextField("");
		this.add(y_s);
		y_s.setBounds(90, 65, 50, 25);
		JLabel y_sl=new JLabel("年");
		this.add(y_sl);
		y_sl.setBounds(140, 65, 25, 25);
		JTextField m_s =new JTextField("");
		this.add(m_s);
		m_s.setBounds(160, 65, 50, 25);
		JLabel m_sl=new JLabel("月");
		this.add(m_sl);
		m_sl.setBounds(210, 65, 25, 25);
		JTextField d_s =new JTextField("");
		this.add(d_s);
		d_s.setBounds(230, 65, 50, 25);
		JLabel d_sl=new JLabel("日");
		this.add(d_sl);
		d_sl.setBounds(280, 65, 25, 25);
		JTextField h_s =new JTextField("");
		this.add(h_s);
		h_s.setBounds(300, 65, 50, 25);
		JLabel h_sl=new JLabel("時");
		this.add(h_sl);
		h_sl.setBounds(350, 65, 25, 25);
		JTextField mm_s =new JTextField("");
		this.add(mm_s);
		mm_s.setBounds(370, 65, 50, 25);
		JLabel mm_sl=new JLabel("分");
		this.add(mm_sl);
		mm_sl.setBounds(420, 65, 25, 25);
		starttime_texts=new ArrayList<JTextField>();
		starttime_texts.add(y_s);
		starttime_texts.add(m_s);
		starttime_texts.add(d_s);
		starttime_texts.add(h_s);
		starttime_texts.add(mm_s);
	}
	private void endTimerow() {
		// TODO Auto-generated method stub
		EndTime_lab=new JLabel("EndTime");
		this.add(EndTime_lab);
		EndTime_lab.setBounds(20, 100, 60, 30);
		JTextField y_e =new JTextField("");
		this.add(y_e);
		y_e.setBounds(90, 105, 50, 25);
		JLabel y_el=new JLabel("年");
		this.add(y_el);
		y_el.setBounds(140, 105, 25, 25);
		JTextField m_e =new JTextField("");
		this.add(m_e);
		m_e.setBounds(160, 105, 50, 25);
		JLabel m_el=new JLabel("月");
		this.add(m_el);
		m_el.setBounds(210, 105, 25, 25);
		JTextField d_e =new JTextField("");
		this.add(d_e);
		d_e.setBounds(230, 105, 50, 25);
		JLabel d_el=new JLabel("日");
		this.add(d_el);
		d_el.setBounds(280, 105, 25, 25);
		JTextField h_e =new JTextField("");
		this.add(h_e);
		h_e.setBounds(300, 105, 50, 25);
		JLabel h_el=new JLabel("時");
		this.add(h_el);
		h_el.setBounds(350, 105, 25, 25);
		JTextField mm_e =new JTextField("");
		this.add(mm_e);
		mm_e.setBounds(370, 105, 50, 25);
		JLabel mm_el=new JLabel("分");
		this.add(mm_el);
		mm_el.setBounds(420, 105, 25, 25);
		endtime_texts=new ArrayList<JTextField>();
		endtime_texts.add(y_e);
		endtime_texts.add(m_e);
		endtime_texts.add(d_e);
		endtime_texts.add(h_e);
		endtime_texts.add(mm_e);
	}
}
