package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import models.GThEdge;
import models.GThNode;
import models.Game;

public class GThNetwork extends JFrame implements ActionListener, TableModelListener{

	private JPanel contentPane;
	private JTextField txtServers;
	private JTextField txtClients;
	private JTable table_1;
	private JTextField txtMaxsc;
	JButton btnInit;
	JButton btnUpdate;
	
	private ArrayList<GThEdge> edges;
	private ArrayList <GThNode> nodes;
	
	private Vector startNodes = null;
	private Vector endtNodes = null;
	private Vector edgeConnections = null;
	private Vector connections = null;
	private Vector nodesV = null;
	private Vector nodesVulnerability = null;
	
	private Game g;
	
	DefaultTableModel dmEdge;
	JTable edgeTable;
	JScrollPane scrollPane_1;
	
	JTable nodesTable;
	DefaultTableModel nodeDm;
	JScrollPane scrollPane;
	JLabel lblNodes;
	JLabel lblEdge;
	
	int count = 0;
	
//	GThMain mainWindow;

	
//	public static void main(String[] args) {
//	    EventQueue.invokeLater(new Runnable() {
//	        public void run() {
//	            try {
//	                Test_JIF frame = new Test_JIF();
//	                frame.setVisible(true);
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    });
//	}

	/**
	 * Create the frame.
	 */
	public GThNetwork() {
		
//		
		
		edges = new ArrayList<>();
		nodes = new ArrayList<>();
		
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		
		 lblNodes = new JLabel("0");
		innerpanel2.add(lblNodes, "6, 2");
		
		JLabel lblNewLabel_3 = new JLabel("Edges");
		innerpanel2.add(lblNewLabel_3, "2, 4");
		
		 lblEdge = new JLabel("0");
		innerpanel2.add(lblEdge, "6, 4");
		
		btnInit = new JButton("Initialzed Edges");
		btnInit.addActionListener(this);
		innerpanel2.add(btnInit, "6, 6");
		
		btnUpdate = new JButton("Create Graph");
		btnUpdate.addActionListener(this);
		innerpanel2.add(btnUpdate, "6, 8");
		
		panel.setLayout(new GridLayout(1,2));
		
		contentPane.add(panel);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,2));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Node config", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
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
		
		
		if(e.getSource().equals(btnUpdate)) {
			
			setVisible(false);
			g.setGraph(g.createCompeletGraph());
			
			GThMain.window.game = g;
			GThMain.window.txtNumOfClients.setText(Integer.toString(g.getNumClients()));
			GThMain.window.txtNumOfServers.setText(Integer.toString(g.getNumServers()));
			GThMain.window.txtNumOfEdges.setText(Integer.toString(g.getNumEdges()));
			GThMain.window.textTotalConnections.setText(Integer.toString(g.getTotalNumConnections()));
//			GThMain.window.btnRun.
			GThMain.window.displayGraph(g.getMainGraph());
			
			
			
			
		}
		
		if(e.getSource().equals(btnInit)) {
			

			int clients = Integer.valueOf(txtClients.getText());
			int servers = Integer.valueOf(txtServers.getText());
			int maxsc = Integer.valueOf(txtMaxsc.getText());
			g = new Game(maxsc, servers, clients);
			
			nodes = g.getNodes();
			edges = g.getEdges();
			
			lblEdge.setText(Integer.toString(edges.size()));
			lblNodes.setText(Integer.toString(nodes.size()));
			
			
			updateNodesTable();
			
			
			    nodeDm = new DefaultTableModel();
			    nodeDm.addTableModelListener(this);
			    
			    nodeDm.addColumn("Node ID",nodesV);
//			    nodeDm.addColumn("Node Type",data);
//			    nodeDm.addColumn("Vulnerability",nodesVulnerability);
			    nodeDm.addColumn("Connections",connections);
			    
			    nodesTable = new JTable(nodeDm);
			    nodesTable.setShowGrid(true);
			    nodesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    nodesTable.setFillsViewportHeight(true);
				
			    scrollPane.setViewportView(nodesTable);
		
		}
		
		
		
	}

	
	void updateNodesTable() {
		
		
		endtNodes = new Vector<>();
		startNodes = new Vector<>();
		nodesV = new Vector<>();
		edgeConnections = new Vector<>();
		connections = new Vector<>();
		
		nodesVulnerability = new Vector<>();
		
		for (int i = 0; i < edges.size(); i++) {
			 GThEdge gThEdge = edges.get(i);
			GThNode nodE = gThEdge.getStartNode();
			startNodes.addElement(nodE.getId());
			
			if(!nodesV.contains(nodE.getId())) {
				nodesV.addElement(nodE.getId());
				connections.addElement(nodE.getAvaialableConnections());
//				nodesVulnerability.addElement(nodE.isValnerable());
			}
			
			nodE = gThEdge.getEndNode();	
			endtNodes.addElement(nodE.getId());
			edgeConnections.addElement(gThEdge.getSharedConns());
			
			if(!nodesV.contains(nodE.getId())) {
				nodesV.addElement(nodE.getId());
				connections.addElement(nodE.getAvaialableConnections());
//				nodesVulnerability.addElement(nodE.isValnerable());
			}
			
		}
		
		dmEdge = new DefaultTableModel();
//	    Vector dummyHeader = new Vector();
//	    dummyHeader.addElement(" test");
//	    dm.setDataVector(strArray2Vector(data), dummyHeader);
	    
		dmEdge.addColumn("Start Node",startNodes);
		dmEdge.addColumn("End Node",endtNodes);
		dmEdge.addColumn("Connections",edgeConnections);
		
		dmEdge.addTableModelListener(this);
		
		
		edgeTable = new JTable(dmEdge);
		edgeTable.setEnabled(false);
	    edgeTable.setShowGrid(true);
	    edgeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    edgeTable.setFillsViewportHeight(true);
	    
	    scrollPane_1.setViewportView(edgeTable);
	    
	}
	
	
	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getSource().equals(nodeDm)) {
			
			System.out.println("nodeDM first Element=====>"+nodeDm.getDataVector());
			int conn =0;
			for (Iterator iterator = nodeDm.getDataVector().iterator(); iterator.hasNext();) {
				Vector object = (Vector) iterator.next();
				
				if(object.size()>1)
				 conn = Integer.valueOf(object.get(1).toString());
				
				int index = (int) object.get(0);
				
//				System.out.println(nodes.get(index-1).getId());
				if(conn<= Game.MAX_SC) {
				nodes.get(index-1).setAvaialableConnections(conn);
				}else
				{
					JOptionPane.showMessageDialog(null, "node :"+nodes.get(index-1).getId()+ "connection has "+conn+ ", which is higher than the network max: "+Game.MAX_SC, "InfoBox: " + "Network Max SC reached", JOptionPane.INFORMATION_MESSAGE);
				    
				}
				
				
//				
				//update graph according to changes
				g.setNodes(nodes);
				g.updateEdges();
				
//			scrollPane_1.setViewportView(edgeTable);
				
				
				updateNodesTable();
				
			}
			
		}
		
		if(e.getSource().equals(dmEdge)) {
			dmEdge.getDataVector();
			
		}
		
	}
	
//	public Game getGame() {
//		return this.g;
//	}

}
