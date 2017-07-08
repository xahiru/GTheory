package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class GThMain implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GThMain window = new GThMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GThMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 612, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmEdge = new JMenuItem("Copy");
		mnEdit.add(mntmEdge);
		
		JMenu mnNode = new JMenu("Node");
		mnEdit.add(mnNode);
		
		JMenuItem mntmAdd = new JMenuItem("add");
		mnNode.add(mntmAdd);
		mntmAdd.addActionListener(this);//new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				 new NodeWindow().setVisible(true);
//				 System.out.print("hello");
//				
//			}
//		});
		
		JMenuItem mntmEdit = new JMenuItem("edit");
		mnNode.add(mntmEdit);
		
		JMenuItem mntmDelete = new JMenuItem("delete");
		mnNode.add(mntmDelete);
		
		JMenu mnEdge = new JMenu("Edge");
		mnEdit.add(mnEdge);
		
		JMenuItem mntmAdd_1 = new JMenuItem("add");
		mnEdge.add(mntmAdd_1);
		
		JMenuItem mntmDelete_1 = new JMenuItem("delete");
		mnEdge.add(mntmDelete_1);
		
		JMenuItem mntmEdit_1 = new JMenuItem("edit");
		mnEdge.add(mntmEdit_1);
		
		JMenuItem mntmNode = new JMenuItem("Paste");
		mnEdit.add(mntmNode);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmHelp = new JMenuItem("help");
		mnAbout.add(mntmHelp);
		
		JMenuItem mntmVersion = new JMenuItem("version");
		mnAbout.add(mntmVersion);
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnnode = new JButton("Node");
		toolBar.add(btnnode);
		
		JButton btnEdge = new JButton("edge");
		toolBar.add(btnEdge);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "summary", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("61px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("115px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("29px"),}));
		
		JLabel lblNewLabel_1 = new JLabel("Nodes");
		panel_3.add(lblNewLabel_1, "2, 2, left, default");
		
		textField_1 = new JTextField();
		panel_3.add(textField_1, "4, 2, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Edges");
		panel_3.add(lblNewLabel_2, "2, 4, left, default");
		
		textField_2 = new JTextField();
		panel_3.add(textField_2, "4, 4, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Strategy");
		panel_3.add(lblNewLabel_3, "2, 6, left, default");
		
		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox, "4, 6, fill, default");
		
		JLabel lblNewLabel = new JLabel("services");
		panel_3.add(lblNewLabel, "2, 8, left, center");
		
		textField = new JTextField();
		panel_3.add(textField, "4, 8, fill, default");
		textField.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Nodes List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Run");
		panel_4.add(btnNewButton_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Network", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_6, null);
	}
	
	
	 public void actionPerformed(ActionEvent e) {

		 new NodeWindow().setVisible(true);
//		 System.out.print("hello");
	    }
}

