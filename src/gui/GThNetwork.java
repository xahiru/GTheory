package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;

public class GThNetwork extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GThNetwork frame = new GThNetwork();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GThNetwork() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2,1));
		
		
		
		JPanel panel = new JPanel();
//		panel.setPreferredSize(new Dimension(contentPane.getWidth(), contentPane.getWidth()/3));
		
		
//		panel.setPreferredSize(new Dimension(contentPane.getSize().width, contentPane.getSize().height/3));
		JPanel innerpanel1 = new JPanel();
		innerpanel1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "initilization", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		JPanel innerpanel2 = new JPanel();
		innerpanel2.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(innerpanel1);
		innerpanel1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Servers");
		innerpanel1.add(lblNewLabel, "2, 2, right, default");
		
		textField = new JTextField();
		innerpanel1.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Clients");
		innerpanel1.add(lblNewLabel_1, "2, 4, right, default");
		
		textField_1 = new JTextField();
		innerpanel1.add(textField_1, "4, 4, fill, default");
		textField_1.setColumns(10);
		
		JButton btnInitialzedEdges = new JButton("Initialzed Edges");
		innerpanel1.add(btnInitialzedEdges, "4, 6");
		
		JButton btnCreateGraph = new JButton("Create Graph");
		innerpanel1.add(btnCreateGraph, "4, 8");
		panel.add(innerpanel2);
		innerpanel2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_2 = new JLabel("Nodes");
		innerpanel2.add(lblNewLabel_2, "2, 2");
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		innerpanel2.add(lblNewLabel_4, "6, 2");
		
		JLabel lblNewLabel_3 = new JLabel("Edges");
		innerpanel2.add(lblNewLabel_3, "2, 4");
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		innerpanel2.add(lblNewLabel_5, "6, 4");
		
		panel.setLayout(new GridLayout(1,2));
		
		contentPane.add(panel);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		
		
		
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,2));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Node config", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
		
		String[] data = { "a", "b", "c", "d", "e", "f", "g" };

//	    JList list = new JList(data);
//	    JScrollPane scrollList = new JScrollPane(list);
//	    scrollList.setMinimumSize(new Dimension(100, 80));
//	    Box listBox = new Box(BoxLayout.Y_AXIS);
//	    listBox.add(scrollList);
//	    listBox.add(new JLabel("JList"));

	    DefaultTableModel dm = new DefaultTableModel();
//	    Vector dummyHeader = new Vector();
//	    dummyHeader.addElement(" test");
//	    dm.setDataVector(strArray2Vector(data), dummyHeader);
	    
	    dm.addColumn("Node ID",data);
	    dm.addColumn("Node Type",data);
	    dm.addColumn("Connections",data);
	    
	    
	    JTable table = new JTable(dm);
	    table.setShowGrid(true);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setFillsViewportHeight(true);
		
		
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
//		table_1 = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Edges", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JList list = new JList();
		panel_3.add(list, BorderLayout.CENTER);;
	}
	
	private Vector strArray2Vector(String[] str) {
	    Vector vector = new Vector();
	    for (int i = 0; i < str.length; i++) {
	      Vector v = new Vector();
	      v.addElement(str[i]);
	      vector.addElement(v);
	    }
	    return vector;
	  }

}
