package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import models.GThEdge;
import models.GThNode;
import models.Game;

public class GThNetwork extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtServers;
	private JTextField txtClients;
	private JTable table_1;
	private JTextField txtMaxsc;
	
	private ArrayList<GThEdge> edges;
	private ArrayList <GThNode> nodes;
	
	private Vector startNodes = null;
	private Vector endtNodes = null;
	private Vector connections = null;
	private Vector nodesV = null;
	
	private Game g;
	
	DefaultTableModel dmEdge;
	JTable edgeTable;
	JScrollPane scrollPane_1;
	
	JTable nodesTable;
	DefaultTableModel nodeDm;
	JScrollPane scrollPane;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GThNetwork frame = new GThNetwork();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GThNetwork() {
		edges = new ArrayList<>();
		nodes = new ArrayList<>();
		
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
		innerpanel1.add(lblNewLabel, "2, 2, left, default");
		
		txtServers = new JTextField();
		innerpanel1.add(txtServers, "4, 2, fill, default");
		txtServers.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Clients");
		innerpanel1.add(lblNewLabel_1, "2, 4, left, default");
		
		txtClients = new JTextField();
		innerpanel1.add(txtClients, "4, 4, fill, default");
		txtClients.setColumns(10);
		
		String twoLines = "Max Shared\n Connections" ; 
		JLabel lblMaxSharedConnections = new JLabel( "<html>" + twoLines.replaceAll("\\n", "<br>") + "</html>");
		innerpanel1.add(lblMaxSharedConnections, "2, 6, right, default");
		
		txtMaxsc = new JTextField();
		innerpanel1.add(txtMaxsc, "4, 6, fill, default");
		txtMaxsc.setColumns(10);
		panel.add(innerpanel2);
		innerpanel2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,
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
		
		JButton button = new JButton("Initialzed Edges");
		button.addActionListener(this);
		innerpanel2.add(button, "6, 6");
		
		JButton button_1 = new JButton("Create Graph");
		innerpanel2.add(button_1, "6, 8");
		
		panel.setLayout(new GridLayout(1,2));
		
		contentPane.add(panel);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		
		
		
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,2));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Node config", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
		
//		String[] data = { "a", "b", "c", "d", "e", "f", "g" };

//	    JList list = new JList(data);
//	    JScrollPane scrollList = new JScrollPane(list);
//	    scrollList.setMinimumSize(new Dimension(100, 80));
//	    Box listBox = new Box(BoxLayout.Y_AXIS);
//	    listBox.add(scrollList);
//	    listBox.add(new JLabel("JList"));

	    
	    
	    
		
		
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
//		table_1 = new JTable();
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Edges", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		
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
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int clients = Integer.valueOf(txtClients.getText());
		int servers = Integer.valueOf(txtServers.getText());
		int maxsc = Integer.valueOf(txtMaxsc.getText());
		g = new Game(maxsc, servers, clients);
		nodes = g.getNodes();
		edges = g.getEdges();
		
		 endtNodes = new Vector<>();
		 startNodes = new Vector<>();
		 connections = new Vector<>();
		 nodesV = new Vector<>();
		 for (Iterator iterator = edges.iterator(); iterator.hasNext();) {
			GThEdge gThEdge = (GThEdge) iterator.next();
			GThNode nodE = gThEdge.getStartNode();
			startNodes.addElement(nodE.getId());
			nodE = gThEdge.getEndNode();
			endtNodes.addElement(nodE.getId());
			connections.addElement(gThEdge.getSharedConns());
			if(!nodesV.contains(nodE.getId()))
				nodesV.addElement(nodE.getId());
			
		}
		
		
//		dmEdge.fireTableDataChanged();
//		edgeTable.
		 
		 	dmEdge = new DefaultTableModel();
//		    Vector dummyHeader = new Vector();
//		    dummyHeader.addElement(" test");
//		    dm.setDataVector(strArray2Vector(data), dummyHeader);
		    
			dmEdge.addColumn("Start Node",startNodes);
			dmEdge.addColumn("End Node",endtNodes);
			dmEdge.addColumn("Connections",connections);
			
			
			edgeTable = new JTable(dmEdge);
		    edgeTable.setShowGrid(true);
		    edgeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    edgeTable.setFillsViewportHeight(true);
		    scrollPane_1.setViewportView(edgeTable);
		
//		edgeTable.getTopLevelAncestor().revalidate();
//		edgeTable.getTopLevelAncestor().repaint();
		
		    
		    
		    
		    
		    nodeDm = new DefaultTableModel();
//		    Vector dummyHeader = new Vector();
//		    dummyHeader.addElement(" test");
//		    dm.setDataVector(strArray2Vector(data), dummyHeader);
		    
		    nodeDm.addColumn("Node ID",nodesV);
//		    nodeDm.addColumn("Node Type",data);
		    nodeDm.addColumn("Connections",nodesV);
		    
		    
		    
		    
		    
		    nodesTable = new JTable(nodeDm);
		    nodesTable.setShowGrid(true);
		    nodesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    nodesTable.setFillsViewportHeight(true);
			
		    scrollPane.setViewportView(nodesTable);
		
	}

}
