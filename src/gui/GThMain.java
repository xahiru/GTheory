package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.collections15.Transformer;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import models.DefenceStrategy;
//import edu.uci.ics.jung.visualization
import models.GThEdge;
import models.GThNode;
import models.Game;

public class GThMain implements ActionListener {

	private JFrame frame;
	JTextField textTotalConnections;
	JTextField txtNumOfServers;
	JTextField txtNumOfEdges;
	JButton btnStrategy;
	JButton btnEdge;
	JButton btnRun;
	private Graph graph;
	JPanel panel_5;
	JMenuBar menuBar;
	JMenu modeMenu;
	Game game;
//	boolean menuadded =false;
	JTextField txtNumOfClients;
	JLabel lblSC;
	JLabel lblStId ;
	JLabel lbldefStrCost;
	
	JComboBox<String> comboBoxDefence;
	JComboBox<String> comboBoxOffence;
	
	static GThMain window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GThMain();
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

		 game = new Game(200,5,3,2,1);
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
		
		 btnStrategy = new JButton("Strategy");
		toolBar.add(btnStrategy);
		
		JButton btnNetwork = new JButton("+ Network");
		btnNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				GThNetwork graphNet = new GThNetwork();
				graphNet.setVisible(true);
//				game = graphNet.getGame();
				
			}
		});
		toolBar.add(btnNetwork);
		btnStrategy.addActionListener(this);
		
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
		txtNumOfServers.setEnabled(false);
		txtNumOfServers.setText("1");
		panel_3.add(txtNumOfServers, "4, 2, fill, default");
		txtNumOfServers.setColumns(10);
		
		JLabel lblClients = new JLabel("Clients");
		panel_3.add(lblClients, "2, 4, left, default");
		
		txtNumOfClients = new JTextField();
		txtNumOfClients.setEnabled(false);
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
		
		 comboBoxOffence = new JComboBox();
		panel_3.add(comboBoxOffence, "4, 8, fill, default");
		
		JLabel lblDefenceStrategy = new JLabel("Defence Strategy");
		panel_3.add(lblDefenceStrategy, "2, 10, left, default");
		
		comboBoxDefence = new JComboBox();
//		comboBox_1.addItemListener(this);
		panel_3.add(comboBoxDefence, "4, 10, fill, default");
		
		JLabel lblNewLabel = new JLabel("Connections");
		panel_3.add(lblNewLabel, "2, 12, left, center");
		
		textTotalConnections = new JTextField();
		textTotalConnections.setEnabled(false);
		panel_3.add(textTotalConnections, "4, 12, fill, default");
		textTotalConnections.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Results", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblShareconnections = new JLabel("ShareConnections");
		panel_7.add(lblShareconnections, "2, 2");
		
		 lblSC = new JLabel("0");
		panel_7.add(lblSC, "6, 2");
		
		JLabel lblBestDefStrategy = new JLabel("Best Def Strategy");
		panel_7.add(lblBestDefStrategy, "2, 4");
		
		 lblStId = new JLabel("St ID");
		panel_7.add(lblStId, "6, 4");
		
		JLabel lblCost = new JLabel("Cost");
		panel_7.add(lblCost, "2, 6");
		
		 lbldefStrCost = new JLabel("0");
		panel_7.add(lbldefStrCost, "6, 6");
		
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
		
		
		game.addRandDS(5,3);

		
		game.addRandOS(2, 3);
		
		createComboModel();
		
		createComboModel2();
		
		
		
		displayGraph(game.getMainGraph());
		
	

	}
	
	
	 public void actionPerformed(ActionEvent e) {

//		 new NodeWindow().setVisible(true);
//		 System.out.print("hello");
		 
		 if(e.getSource().equals(btnRun)) {
			 
//			 System.out.print("btnRun");
			 
//			 comboBoxOffence.getSelectedIndex();
//			 comboBoxDefence.getSelectedIndex();
			 
//			 System.out.println(game.offence.get(comboBoxOffence.getSelectedIndex()).size());
			 game.play( comboBoxOffence.getSelectedIndex(), comboBoxDefence.getSelectedIndex());
			 
			 
       
			 
	
			 displayGraph(game.getMainGraph());
			 
			 lblSC.setText(Integer.toString(game.SC_D));
			 lblStId.setText(Integer.toString(game.bestDefenceIndex));
			 lbldefStrCost.setText(Double.toString(game.bestDefence.calculateCost()));
			 
			 

		 }
		 
//		 if(e.getSource().equals(btnStrategy)) {
//
//			 new NodeWindow().setVisible(true);
//			 
//			
//			 
//		 }
//		 
//		 if(e.getSource().equals(btnEdge)) {
//			
//			 graph = game.getGraph();
//			 displayGraph(graph);
//		 }
		
			 
			 
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
	 
	 public void createComboModel(){
		 
		 
		 Vector<String> dfsIndext = new Vector<String>();
		 
		 for (int i = 0; i < game.defence.size(); i++) {
			 
			 dfsIndext.addElement("StrategyList"+Integer.toString(i) +": "+ game.defence.get(i).size());
		}
		 

		 comboBoxDefence.setModel(new DefaultComboBoxModel(dfsIndext));

			 }
	 
	 
	 public void createComboModel2(){
		 
		 
		 Vector<String> ofsIndext = new Vector<String>();
		 
		 for (int i = 0; i < game.offence.size(); i++) {
			 
			 ofsIndext.addElement("Offense"+Integer.toString(i) +": "+ game.offence.get(i).size());
		}
		 
		 comboBoxOffence.setModel(new DefaultComboBoxModel(ofsIndext));

			 }
	

	 
}
