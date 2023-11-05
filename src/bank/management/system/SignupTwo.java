package bank.management.system;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener{
	
	JTextField pan, aadhar;
	JButton next;
	JRadioButton syes, sno, eyes, eno;
	JComboBox religion, category, occupation, education, income;
	
	String formno;
	
	SignupTwo(String formno){
		
		this.formno = formno;
		
		setLayout(null);
		
		setTitle("New Account Application Form - Page 2");
		
		JLabel additionalDetails = new JLabel("Page 2 : Additional Details");
		additionalDetails.setFont(new Font("Raleway",Font.BOLD, 22));
		additionalDetails.setBounds(290,80,400,30);
		add(additionalDetails);
		
		JLabel rel = new JLabel("Religion : ");
		rel.setFont(new Font("Raleway",Font.BOLD, 20));
		rel.setBounds(100,140,400,30);
		add(rel);
		
		String[] valReligion = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
		religion = new JComboBox(valReligion);
		religion.setBounds(300,140,400,30);
		add(religion);
		
		
		JLabel cat = new JLabel("Category : ");
		cat.setFont(new Font("Raleway",Font.BOLD, 20));
		cat.setBounds(100,190,200,30);
		add(cat);
		
		String[] valCategory = {"General", "OBC", "SC", "ST", "Other"};
		category = new JComboBox(valCategory);
		category.setBounds(300,190,400,30);
		add(category);
		
		JLabel inc = new JLabel("Income : ");
		inc.setFont(new Font("Raleway",Font.BOLD, 20));
		inc.setBounds(100,240,200,30);
		add(inc);
		
		String[] incomeCategory = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000"};
		income = new JComboBox(incomeCategory);
		income.setBounds(300,240,400,30);
		add(income);
		
		JLabel edu = new JLabel("Educational");
		edu.setFont(new Font("Raleway",Font.BOLD, 20));
		edu.setBounds(100,290,200,30);
		add(edu);
		
		JLabel qual = new JLabel("Qualification : ");
		qual.setFont(new Font("Raleway",Font.BOLD, 20));
		qual.setBounds(100,315,200,30);
		add(qual);
		
		String[] educationValues = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Others"};
		education = new JComboBox(educationValues);
		education.setBounds(300,315,400,30);
		add(education);
		
		JLabel occ = new JLabel("Occupation : ");
		occ.setFont(new Font("Raleway",Font.BOLD, 20));
		occ.setBounds(100,390,200,30);
		add(occ);
		
		String[] occupationValues = {"Salaried", "Self-Employed", "Bussiness", "Student", "Retired", "Others"};
		occupation = new JComboBox(occupationValues);
		occupation.setBounds(300,390,400,30);
		add(occupation);
		
		JLabel pno = new JLabel("PAN Number : ");
		pno.setFont(new Font("Raleway",Font.BOLD, 20));
		pno.setBounds(100,440,200,30);
		add(pno);
		
		pan = new JTextField();
		pan.setFont(new Font("Raleway", Font.BOLD, 14));
		pan.setBounds(300,440,400,30);
		add(pan);
		
		JLabel ano = new JLabel("Aadhar Number : ");
		ano.setFont(new Font("Raleway",Font.BOLD, 20));
		ano.setBounds(100,490,200,30);
		add(ano);
		
		aadhar = new JTextField();
		aadhar.setFont(new Font("Raleway", Font.BOLD, 14));
		aadhar.setBounds(300,490,400,30);
		add(aadhar);
		
		JLabel senCit = new JLabel("Senior Citizen : ");
		senCit.setFont(new Font("Raleway",Font.BOLD, 20));
		senCit.setBounds(100,540,200,30);
		add(senCit);
		
		syes = new JRadioButton("Yes");
		syes.setBounds(300,540,100,30);
		add(syes);
		
		sno = new JRadioButton("No");
		sno.setBounds(450,540,100,30);
		add(sno);
		
		ButtonGroup senCitButton = new ButtonGroup();
		senCitButton.add(syes);
		senCitButton.add(sno);
		
		JLabel exisAcc = new JLabel("Existing Account : ");
		exisAcc.setFont(new Font("Raleway",Font.BOLD, 20));
		exisAcc.setBounds(100,590,200,30);
		add(exisAcc);
		
		eyes = new JRadioButton("Yes");
		eyes.setBounds(300,590,100,30);
		add(eyes);
		
		eno = new JRadioButton("No");
		eno.setBounds(450,590,100,30);
		add(eno);
		
		ButtonGroup exisAccButton = new ButtonGroup();
		exisAccButton.add(eyes);
		exisAccButton.add(eno);
		
		next = new JButton("Next");
		next.setBackground(Color.BLACK);
		next.setForeground(Color.WHITE);
		next.setFont(new Font("Raleway",Font.BOLD,14));
		next.setBounds(620,660,80,30);
		next.setOpaque(true);
		next.setBorderPainted(false);
		next.addActionListener(this);
		add(next);
		
		getContentPane().setBackground(Color.WHITE);
		
		setSize(850,800);
		setLocation(350,10);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SignupTwo("");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String sreligion = (String)(religion.getSelectedItem());
		String scategory = (String)(category.getSelectedItem());
		String sincome = (String)(income.getSelectedItem());
		String seducation = (String)(education.getSelectedItem());
		String soccupation = (String)(occupation.getSelectedItem());
		String seniorcitizen = null;
		if(syes.isSelected()) seniorcitizen = "Yes";
		else if(sno.isSelected()) seniorcitizen = "No";
		String existingaccount = null;
		if(eyes.isSelected()) existingaccount = "Yes";
		else if(eno.isSelected()) existingaccount = "No";
		String span = pan.getText();
		String saadhar = aadhar.getText();
		
		try {
			if(span.equals("")) {
				JOptionPane.showMessageDialog(null, "Pan Number is Required");
			}
			else if(saadhar.equals("")) JOptionPane.showMessageDialog(null, "Aadhar Number is Required");
			else {
				Conn c = new Conn();
				String query = "insert into signuptwo values('"+formno+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"', '"+soccupation+"', '"+span+"','"+saadhar+"','"+seniorcitizen+"','"+existingaccount+"')";
				c.s.executeUpdate(query);
				
				setVisible(false);
				new SignupThree(formno).setVisible(true);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
