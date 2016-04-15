import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Run {
	
	
	private static JLabel lblRed;
	private static JLabel lblGreen;
	private static JLabel lblBlue;
	private static JPanel panel;
	private static JLabel lblX;
	private static JLabel lblY;
	private static int screenHeight;
	private static int screenWidth;
	private static int[] globPosArray = new int[2];
	private static Color globColor;
	public static void main(String[] args) {
		
		getScreenSize();
		CalcGlobePosition();
		
		try {
			Robot roboto = new Robot();
			globColor = roboto.getPixelColor(globPosArray[0],globPosArray[1]);
			roboto.mouseMove(globPosArray[1], globPosArray[0]);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Transparent Window");
		
		CalcGlobePosition();
		
		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(true);
        
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        
        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        

        
        
        
        
        Timer timer = programFPS(60);
        JButton btnNewButton = new JButton("Stop Timer");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		stopTimer(timer);
        	}

			
        });
        frame.getContentPane().add(btnNewButton, BorderLayout.NORTH);
        
        JButton btnNewButton_1 = new JButton("Start Timer");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		startTimer(timer);
        	}

			
        });
        frame.getContentPane().add(btnNewButton_1, BorderLayout.EAST);
        
        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(0, 3, 0, 0));
        
        lblRed = new JLabel("lblRed");
        panel.add(lblRed);
        
        lblGreen = new JLabel("lblGreen");
        panel.add(lblGreen);
        
        lblBlue = new JLabel("lblBlue");
        panel.add(lblBlue);
        
        lblY = new JLabel("Y");
        panel.add(lblY);
        
        lblX = new JLabel("X");
        panel.add(lblX);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(250,0,0)));
        frame.setVisible(true);
        
        frame.pack();
		
		timer.setRepeats(true);
		timer.start();
		
	}
	public static void getScreenSize(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		
		
	}
	
	public static Point getMousePosition(){
		return MouseInfo.getPointerInfo().getLocation();
		
	}
	
	public static void getColors(Robot robot) throws AWTException {
		
		Point pos = getMousePosition();
		Color c = robot.getPixelColor(pos.x, pos.y);
		
		lblY.setText(pos.y + "");
		lblX.setText(pos.x + "");
		lblRed.setText(c.getRed()+ "");
		lblGreen.setText(c.getGreen()+ "");
		lblBlue.setText(c.getBlue()+ "");
	}
	
	public static Timer programFPS(double fps){
		Timer timer = new Timer(1000/20,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Robot robot = new Robot();
					getColors(robot);
					if(getGlobeColors(robot)){
						System.out.println("YOur life is low");
					}
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		return timer;
	}
	
	private static void stopTimer(Timer timer) {
		timer.stop();
		
		
	}
	
	private static void startTimer(Timer timer) {
		timer.start();
		
	}
	
	private static void CalcGlobePosition(){
		int globPosH = (int) Math.floor((screenHeight * 0.79) * 1.15);
		int globPosW = (int) Math.floor((screenWidth * 0.11) / 2);
		globPosArray[0] = globPosH;
		globPosArray[1] = globPosW;
		
	}
	
	private static boolean getGlobeColors(Robot robot){
		Color tempColor = robot.getPixelColor(globPosArray[0], globPosArray[1]);
		
		if(tempColor.getRed() !=  globColor.getRed()){
			return true;
		}
		else {
			return false;
		}
		
		
	}
}
