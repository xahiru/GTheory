package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import models.GThEdge;
import models.GThNode;
import models.Game;

public class GThMain implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtNumOfServers;
	private JTextField txtNumOfEdges;
	JButton btnTestgraph;
	JButton btnEdge;
	private Graph graph;
	JPanel panel_5;
	Game game;
	private JTextField txtNumOfClients;

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

		 game = new Game();
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 422);
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
		
		 btnEdge = new JButton("edge");
		btnEdge.addActionListener(this);
		toolBar.add(btnEdge);
		
		 btnTestgraph = new JButton("Network");
		toolBar.add(btnTestgraph);
		btnTestgraph.addActionListener(this);
		
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
				ColumnSpec.decode("111px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("115px:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("29px"),}));
		
		JLabel lblNewLabel_1 = new JLabel("Servers");
		panel_3.add(lblNewLabel_1, "2, 2, left, default");
		
		txtNumOfServers = new JTextField();
		panel_3.add(txtNumOfServers, "4, 2, fill, default");
		txtNumOfServers.setColumns(10);
		
		JLabel lblClients = new JLabel("Clients");
		panel_3.add(lblClients, "2, 4, left, default");
		
		txtNumOfClients = new JTextField();
		panel_3.add(txtNumOfClients, "4, 4, fill, default");
		txtNumOfClients.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Edges");
		panel_3.add(lblNewLabel_2, "2, 6, left, default");
		
		txtNumOfEdges = new JTextField();
		txtNumOfEdges.setEnabled(false);
		panel_3.add(txtNumOfEdges, "4, 6, fill, default");
		txtNumOfEdges.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Attack Strategy");
		panel_3.add(lblNewLabel_3, "2, 8, left, default");
		
		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox, "4, 8, fill, default");
		
		JLabel lblDefenceStrategy = new JLabel("Defence Strategy");
		panel_3.add(lblDefenceStrategy, "2, 10, left, default");
		
		JComboBox comboBox_1 = new JComboBox();
		panel_3.add(comboBox_1, "4, 10, fill, default");
		
		JLabel lblNewLabel = new JLabel("services");
		panel_3.add(lblNewLabel, "2, 12, left, center");
		
		textField = new JTextField();
		textField.setEnabled(false);
		panel_3.add(textField, "4, 12, fill, default");
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
		
		 panel_5 = new JPanel();
		tabbedPane.addTab("Network", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_6, null);
		

	}
	
	
	 public void actionPerformed(ActionEvent e) {

//		 new NodeWindow().setVisible(true);
//		 System.out.print("hello");
		 
		 if(e.getSource().equals(btnTestgraph)) {
			 
			 int clients = Integer.valueOf(txtNumOfClients.getText());
			 int servers = Integer.valueOf(txtNumOfServers.getText());
//			 game = new Game(2, 5, 1);
			 game.setNumClients(clients);
			 game.setNumServers(servers);
			 game.setNumNodes(game.getNumClients()+game.getNumServers());
			 game.init();
			 graph =	 game.compeletGraph();
				
//				graph =	game.testGraph();
//				game.directedGraph();
//				graph =	game.directedGraph();
		
			displayGraph(graph);
		 }
		 if(e.getSource().equals(btnEdge))
			
			 graph = game.compeletGraph();
		 displayGraph(graph);
			 
	    }
	 
	 public void displayGraph(Graph graph) {
		 
		 
		 Layout<GThNode, GThEdge> layout = new CircleLayout(graph);
		 layout.setSize(new Dimension(300,300)); // sets the initial size of the space
		 // The BasicVisualizationServer<V,E> is parameterized by the edge types
		 BasicVisualizationServer<GThNode,GThEdge> vv =
		 new BasicVisualizationServer<GThNode,GThEdge>(layout);
		 vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size


		 panel_5.add(vv);
		 panel_5.getTopLevelAncestor().repaint();
//		 panel_5.remove(vv);
		
	}
}

