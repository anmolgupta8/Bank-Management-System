package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Pinchange extends JFrame implements ActionListener{
	
	JPasswordField pin, repin;
	JButton change, back;
	String pinnumber;
	
	Pinchange(String pinnumber){
		
		this.pinnumber = pinnumber;
		
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,900);
		add(image);
		
		JLabel text = new JLabel("Change your pin");
		text.setFont(new Font("System",Font.BOLD,16));
		text.setForeground(Color.WHITE);
		text.setBounds(265,285,500,35);
		image.add(text);
		
		JLabel pintext = new JLabel("New Pin : ");
		pintext.setFont(new Font("System",Font.BOLD,16));
		pintext.setForeground(Color.WHITE);
		pintext.setBounds(165,330,180,25);
		image.add(pintext);
		
		pin = new JPasswordField();
		pin.setFont(new Font("Raleway",Font.BOLD,16));
		pin.setBounds(330,330,180,25);
		image.add(pin);
		
		JLabel repintext = new JLabel("Re-Enter New Pin : ");
		repintext.setFont(new Font("System",Font.BOLD,16));
		repintext.setForeground(Color.WHITE);
		repintext.setBounds(165,370,180,25);
		image.add(repintext);
		
		repin = new JPasswordField();
		repin.setFont(new Font("Raleway",Font.BOLD,16));
		repin.setBounds(330,370,180,25);
		image.add(repin);
		
		change = new JButton("Change");
		change.setBounds(355,485,150,30);
		change.addActionListener(this);
		image.add(change);
		
		back = new JButton("Back");
		back.setBounds(355,520,150,30);
		back.addActionListener(this);
		image.add(back);
		
		setSize(900,900);
		setLocation(300,0);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Pinchange("");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == change) {
			try {
				String npin = pin.getText();
				String rpin = repin.getText();
				
				if(!npin.equals(rpin)) {
					JOptionPane.showMessageDialog(null, "Entered pin does not match");
					return;
				}
				
				if(npin.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the new pin");
					return;
				}
				
				if(rpin.equals("")) {
					JOptionPane.showMessageDialog(null, "Please re-enter the new pin");
					return;
				}
				
				Conn conn = new Conn();
				String query1 = "update bank set pin = '"+rpin+"' where pin = '"+pinnumber+"'";
				String query2 = "update login set pin = '"+rpin+"' where pin = '"+pinnumber+"'";
				String query3 = "update signupthree set pin = '"+rpin+"' where pin = '"+pinnumber+"'";
				
				conn.s.executeUpdate(query1);
				conn.s.executeUpdate(query2);
				conn.s.executeUpdate(query3);
				
				JOptionPane.showMessageDialog(null, "PIN changed successfully");
				
				setVisible(false);
				new Transactions(rpin).setVisible(true);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		else {
			setVisible(false);
			new Transactions(pinnumber).setVisible(true);
		}
	}

}
