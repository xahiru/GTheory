package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
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

	        contentPane.setLayout(new GridLayout(1,2));
	        contentPane.add(panelOne);
	        panelOne.setLayout(new GridLayout(2,1));
	        
	        JPanel panel = new JPanel();
	        panel.setBorder(new TitledBorder(null, "Attack Strategy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	        panelOne.add(panel);
	        panel.setLayout(new BorderLayout(0, 0));
	        
//	        JList list_1 = new JList();
	        
	        
	        JList list = new JList(new CheckListItem[] { new CheckListItem("apple"),
	                new CheckListItem("orange"), new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                new CheckListItem("mango"),
	                
	                new CheckListItem("paw paw"), new CheckListItem("banana") });
	            list.setCellRenderer(new CheckListRenderer());
	            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	            list.addMouseListener(new MouseAdapter() {
	              @Override
	              public void mouseClicked(MouseEvent event) {
	                JList list = (JList) event.getSource();
	                int index = list.locationToIndex(event.getPoint());// Get index of item
	                                                                   // clicked
	                CheckListItem item = (CheckListItem) list.getModel()
	                    .getElementAt(index);
	                item.setSelected(!item.isSelected()); // Toggle selected state
	                list.repaint(list.getCellBounds(index, index));// Repaint cell
	              }
	            });
	        
	        
	        
	        
	        panel.add(new JScrollPane(list), BorderLayout.CENTER);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBorder(new TitledBorder(null, "Diffence Stragty", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	        panelOne.add(panel_1);
	        
	        
	        contentPane.add(panelTwo);
	        DefaultListModel model = new DefaultListModel();
	        JList list2 = new JList(model);
	        model.addElement("one");
	        JScrollPane pane = new JScrollPane(list2);
	        
	        panelTwo.add(pane);
	        
//	        panelTwo.setLayout(new BoxLayout(panelTwo, BoxLayout.X_AXIS));
	        
//	        contentPane.add(panelThree);

//	        this.add(contentPane);
	        setContentPane(contentPane);
			
	}
	
	
	class CheckListItem {

		  private String label;
		  private boolean isSelected = false;

		  public CheckListItem(String label) {
		    this.label = label;
		  }

		  public boolean isSelected() {
		    return isSelected;
		  }

		  public void setSelected(boolean isSelected) {
		    this.isSelected = isSelected;
		  }

		  @Override
		  public String toString() {
		    return label;
		  }
		}
	class CheckListRenderer extends JCheckBox implements ListCellRenderer {
		  public Component getListCellRendererComponent(JList list, Object value,
		      int index, boolean isSelected, boolean hasFocus) {
		    setEnabled(list.isEnabled());
		    setSelected(((CheckListItem) value).isSelected());
		    setFont(list.getFont());
		    setBackground(list.getBackground());
		    setForeground(list.getForeground());
		    setText(value.toString());
		    return this;
		  }
	}

}
