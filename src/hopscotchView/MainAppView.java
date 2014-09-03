package hopscotchView;

import hopscotch.Hopscotch;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

public class MainAppView {

	private JFrame frame;
	private static HopscotchUI hopscotch = new HopscotchUI();
	//private static Hopscotch<Integer,String> hmap ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppView window = new MainAppView();
					window.frame.setVisible(true);
					
					window.frame.getContentPane().add(hopscotch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainAppView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setSize(new Dimension(600,430));
		//frame.setResizable(false);
		//frame.getContentPane().setSize(new Dimension(600, 430));
		frame.setSize(900, 650);
		//frame.getContentPane().setPreferredSize(new Dimension(600, 430));
		//frame.getContentPane().setMinimumSize(new Dimension(600, 430));
		//frame.getContentPane().setMaximumSize(new Dimension(600, 430));
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//frame.setBounds(0, 0, 600, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	hmap = new Hopscotch<Integer, String>();
		//hopscotch.setLocation(new Point(0,0));
		//hopscotch.setSize(new Dimension(600,400));
	}

}
