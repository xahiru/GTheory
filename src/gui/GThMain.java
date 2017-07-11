package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

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

import org.apache.commons.collections15.Transformer;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import edu.uci.ics.jung.algorithms.layout.BalloonLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.DAGLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
//import edu.uci.ics.jung.visualization
import models.GThEdge;
import models.GThNode;
import models.Game;
import models.Strategy;

public class GThMain implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtNumOfServers;
	private JTextField txtNumOfEdges;
	JButton btnTestgraph;
	JButton btnEdge;
	JButton btnRun;
	private Graph graph;
	JPanel panel_5;
	JMenuBar menuBar;
	JMenu modeMenu;
	Game game;
//	boolean menuadded =false;
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
//		displayGraph(game.createCompeletGraph());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		 game = new Game();
		frame = new JFrame();
		menuBar = new JMenuBar();
		
		frame.setBounds(100, 100, 652, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 menuBar = new JMenuBar();
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
		mntmAdd.addActionListener(this);
		
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
		txtNumOfServers.setText("1");
		panel_3.add(txtNumOfServers, "4, 2, fill, default");
		txtNumOfServers.setColumns(10);
		
		JLabel lblClients = new JLabel("Clients");
		panel_3.add(lblClients, "2, 4, left, default");
		
		txtNumOfClients = new JTextField();
		txtNumOfClients.setText("2");
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
		
		btnRun = new JButton("Run");
		btnRun.addActionListener( this);
		panel_4.add(btnRun);
		
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
		 if(e.getSource().equals(btnRun)) {
			 
//			 System.out.print("btnRun");
			 
			 Strategy st = game.populateRandStrategy();
			 
//			 System.out.print("stlist size:"+st.getParticipants().size());
//			 
//			 for (Iterator iterator = st.getParticipants().iterator(); iterator.hasNext();) {
//				GThNode node = (GThNode) iterator.next();
//				System.out.print("\n"+node.getId());
//				
//			}
			 
			 displayGraph(game.createCompeletGraph());
		 }
		 
		 if(e.getSource().equals(btnTestgraph)) {
			 
			 int clients = Integer.valueOf(txtNumOfClients.getText());
			 int servers = Integer.valueOf(txtNumOfServers.getText());
//			 game = new Game(2, 5, 1);
			 game.setNumClients(clients);
			 game.setNumServers(servers);
			 game.setNumNodes(game.getNumClients()+game.getNumServers());
//			 game.initGraph();
			 graph =	 game.getGraph(); 
			 
				
//				graph =	game.testGraph();
//				game.directedGraph();
//				graph =	game.directedGraph();
		
			displayGraph(graph);
		 }
		 if(e.getSource().equals(btnEdge)) {
			
			 graph = game.getGraph();
			 displayGraph(graph);
		 }
		
			 
			 
	    }
	 
	 public void displayGraph(Graph graph) {
		 
		 
		 Layout<GThNode, GThEdge> layout = new CircleLayout(graph);

//		 Layout<GThNode, GThEdge> layout = new StaticLayout(graph);
		 
		 layout.setSize(panel_5.getSize());
		 
		 VisualizationViewer<GThNode,GThEdge> vv =
				 new VisualizationViewer<GThNode,GThEdge>(layout);
		 
		 vv.setPreferredSize(panel_5.getSize());
		 
		 
		 Transformer<GThNode,Paint> vertexPaint = new Transformer<GThNode,Paint>() {
			 public Paint transform(GThNode i) {
			 return Color.GREEN;
			 }
			 };
			
			 
			 
			 vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
//			 vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);

			 vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
			 vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
			 vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
			 
			 
			EditingModalGraphMouse gm =
					 new EditingModalGraphMouse(vv.getRenderContext(),
					  game.vertexFactory, game.edgeFactory); 
			
			 vv.setGraphMouse(gm);
			 
			
			 modeMenu = gm.getModeMenu(); // Obtain mode menu from the mouse
			 modeMenu.setText("Mouse Mode");
//			 modeMenu.setIcon(null); // I'm using this in a main menu
//			 modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size 
		
			 menuBar.add(modeMenu);
			
			 frame.setJMenuBar(menuBar);
		
//			 gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
			

		 
		 panel_5.removeAll();
		 panel_5.add(vv);
		 panel_5.getTopLevelAncestor().revalidate();
		 panel_5.getTopLevelAncestor().repaint();
		 

		
	}
}

