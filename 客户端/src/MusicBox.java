import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;



public class MusicBox extends JPanel{
	private JComboBox musicCombo;
	private AudioClip [] music = new AudioClip[4];
	private JButton stopButton,playButton;
	private AudioClip current;
	URL [] url = new URL[4];
	URL p;
	public static void main(String[]args){
		JFrame frame=new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MusicBox());
		frame.setVisible(true);
	}
	
	public MusicBox(){
		url[0] = url[1] = url[2] = url[3]  = null;
		try {
			url[0] = new URL("file","localhost","music/∏ﬂ…Ω¡˜ÀÆ.wav");
			url[1] = new URL("file","localhost","music/«Ô»’ÀΩ”Ô.wav");
			url[2] = new URL("file","localhost","music/–«ø’.wav");
			url[3] = new URL("file","localhost","music/¡∫◊£.wav");
		} catch (Exception e){}
		
		p = url[0];
		JLabel titleLabel = new JLabel("±≥æ∞“Ù¿÷");
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		String [] musicNames = {"∏ﬂ…Ω¡˜ÀÆ","«Ô»’ÀΩ”Ô","–«ø’","¡∫◊£"};
		
		musicCombo = new JComboBox(musicNames);
		musicCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		playButton = new JButton("<html><font style='font:bold 15pt'>play</font></html>");
		playButton.setBackground(Color.white);
		playButton.setMnemonic('p');
		stopButton = new JButton("<html><font style='font:bold 15pt'>stop</font></html>");
		stopButton.setBackground(Color.white);
		stopButton.setMnemonic('s');
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		buttons.add(playButton);
		buttons.add(stopButton);
		buttons.setBackground(Color.darkGray);
		
		setPreferredSize(new Dimension(160,89));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.lightGray);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0,3)));
		add(titleLabel);
		add(Box.createRigidArea(new Dimension(0,3)));
		add(musicCombo);
		add(Box.createRigidArea(new Dimension(0,3)));
		add(buttons);
		add(Box.createRigidArea(new Dimension(0,3)));
		musicCombo.addActionListener(new ComboListener());
		stopButton.addActionListener(new ButtonListener());
		playButton.addActionListener(new ButtonListener());
		
		current = Applet.newAudioClip(p);
	}	
	
	
	private class ComboListener implements ActionListener{
		public void actionPerformed (ActionEvent event)
		{
			if (current != null) current.stop();
			p = url[musicCombo.getSelectedIndex()];
			current = Applet.newAudioClip(p);
			System.out.println(p);
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			if (current != null)
				current.stop();
			
			if (event.getSource() == playButton)
		        if (current != null) current.loop();
		}
		
	}

}
