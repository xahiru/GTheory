package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class NodeWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NodeWindow frame = new NodeWindow();
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
	public NodeWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		
//		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        this.setSize(100, 75);
//	        this.setLayout(new BorderLayout());
	        this.setVisible(true);

//	        JPanel container = new JPanel();
	        JPanel panelOne = new JPanel();
	        JPanel panelTwo = new JPanel();
//	        JPanel panelThree = new JPanel();

	        contentPane.setLayout(new GridLayout(1,3));
	        contentPane.add(panelOne);
	        panelOne.setLayout(new GridLayout(2,1));
	        
	        JPanel panel = new JPanel();
	        panel.setBorder(new TitledBorder(null, "one", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	        panelOne.add(panel);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBorder(new TitledBorder(null, "leftbottom", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	        panelOne.add(panel_1);
	        
	        
	        contentPane.add(panelTwo);
//	        panelTwo.setLayout(new BoxLayout(panelTwo, BoxLayout.X_AXIS));
	        
//	        contentPane.add(panelThree);

//	        this.add(contentPane);
	        setContentPane(contentPane);
			
	}

}
