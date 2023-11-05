package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.*;

public class MiniStatement extends JFrame{
	
	MiniStatement(String pinnumber){
		setTitle("Mini Statement");
		
		setLayout(null);
		
		JLabel mini = new JLabel();
		add(mini);
		
		JLabel bank = new JLabel("Punjab National Bank");
		bank.setBounds(115,20,200,20);
		bank.setFont(new Font("Raleway",Font.BOLD,16));
		add(bank);
		
		JLabel card = new JLabel();
		card.setBounds(20,80,300,20);
		add(card);
		
		JLabel balance = new JLabel();
		balance.setBounds(20,420,320,20);
		add(balance);
		
		try {
			Conn conn = new Conn();
			String query = "select * from login where pin = '"+pinnumber+"'";
			ResultSet rs = conn.s.executeQuery(query);
			while(rs.next()) {
				card.setText("Card Number : " + rs.getString("cardnumber").substring(0,4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			Conn conn = new Conn();
			String query = "select * from bank where pin = '"+pinnumber+"'";
			ResultSet rs = conn.s.executeQuery(query);
			int bal = 0;
			int count = 0;
			while(rs.next()) count++;
			rs = conn.s.executeQuery(query);
			int currIdx = 0;
			while(rs.next()) {
				if(currIdx>=count-5) mini.setText(mini.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
				if(rs.getString("type").equals("Deposit")) {
					bal += Integer.parseInt(rs.getString("amount"));
				}
				else bal -= Integer.parseInt(rs.getString("amount"));
				currIdx++;
			}
			balance.setText("Your current balance account is Rs : " + bal);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		mini.setBounds(20,100,400,400);
		
		setSize(400,600);
		setLocation(20,20);
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MiniStatement("");
	}

}
