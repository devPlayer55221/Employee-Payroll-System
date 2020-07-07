import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.sql.*;

public class EmployeeProject extends JFrame
{
	String name[];
	int hours[];
	int ntime=0, mtime=0, nwage=0, owage=0;
	JLabel lempno,lsetemp,lsetnormaltime,lmaxtime,lnormalwage,loverwage;
	JTextField tempno,tsetnormaltime,tmaxtime,tnormalwage,toverwage;
	JButton bempno,bempdtl,bovertime,boverall,bsetnormaltime,bmaxtime,bnormalwage,boverwage;
	JTable table;
	int a,i=0,j=0;
  
	EmployeeProject(){};
  
	EmployeeProject(String s)
	{
		super(s);
	}
  
	public void set()
	{
		lempno=new JLabel("Enter no. of employees");
		tempno=new JTextField();
		bempno=new JButton("Enter");

		lsetnormaltime=new JLabel("Enter normal working hours");
		tsetnormaltime=new JTextField();
		bsetnormaltime=new JButton("Enter");

		lmaxtime=new JLabel("Enter maximum working hours allowed");
		tmaxtime=new JTextField();
		bmaxtime=new JButton("Enter");

		lnormalwage=new JLabel("Enter normal wage per hour");
		tnormalwage=new JTextField();
		bnormalwage=new JButton("Enter");

		loverwage=new JLabel("Enter overtime wage per hour");
		toverwage=new JTextField();
		boverwage=new JButton("Enter");

		lsetemp=new JLabel("Employee 1");
		bempdtl=new JButton("Enter employee detail");
		bovertime=new JButton("Overtime");
		boverall=new JButton("Overall");

		add(lempno);
		add(tempno);
		add(bempno);

		add(lsetemp);
		add(bempdtl);
		add(bovertime);
		add(boverall);
		
		add(lsetnormaltime);
		add(tsetnormaltime);
		add(bsetnormaltime);

		add(lmaxtime);
		add(tmaxtime);
		add(bmaxtime);

		add(lnormalwage);
		add(tnormalwage);
		add(bnormalwage);

		add(loverwage);
		add(toverwage);
		add(boverwage);

		setLayout(null);
		lempno.setBounds(50,50,150,30);
		tempno.setBounds(350,50,100,30);
		bempno.setBounds(500,50,100,30);

		lsetnormaltime.setBounds(50,100,250,30);
		tsetnormaltime.setBounds(350,100,100,30);
		bsetnormaltime.setBounds(500,100,100,30);

		lmaxtime.setBounds(50,150,250,30);
		tmaxtime.setBounds(350,150,100,30);
		bmaxtime.setBounds(500,150,100,30);

		lnormalwage.setBounds(50,200,200,30);
		tnormalwage.setBounds(350,200,100,30);
		bnormalwage.setBounds(500,200,100,30);

		loverwage.setBounds(50,250,200,30);
		toverwage.setBounds(350,250,100,30);
		boverwage.setBounds(500,250,100,30);
		
		lsetemp.setBounds(50,310,300,30);
		bempdtl.setBounds(300,310,300,30);
		bovertime.setBounds(50,350,100,30);
		boverall.setBounds(220,350,100,30);

		bempno.addActionListener(new TotalEmployees());
		bsetnormaltime.addActionListener(new NormalTime());
		bmaxtime.addActionListener(new MaxTime());
		bnormalwage.addActionListener(new NormalWage());
		boverwage.addActionListener(new OverWage());

		bempdtl.addActionListener(new EmployeeDetails());
		bovertime.addActionListener(new OvertimeHoursTable());
		boverall.addActionListener(new OverallHoursTable());
	}

	public class TotalEmployees implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			a=Integer.parseInt(tempno.getText());
			name=new String[a];
			hours=new int[a];
		}
	}

	public class NormalTime implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			ntime=Integer.parseInt(tsetnormaltime.getText());
		}
	}

	public class MaxTime implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mtime=Integer.parseInt(tmaxtime.getText());
		}
	}

	public class NormalWage implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			nwage=Integer.parseInt(tnormalwage.getText());
		}
	}

	public class OverWage implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			owage=Integer.parseInt(toverwage.getText());
		}
	}

	public class EmployeeDetails implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(j==a)
			{
				JOptionPane.showMessageDialog(null,"Employee detail completed !!");
			}
			else
			{
				String str1 = JOptionPane.showInputDialog("Enter employee "+(j+1)+" name");
				String str2 = JOptionPane.showInputDialog("Enter employee "+(j+1)+" working hour");
				name[j]=str1;
				hours[j]=Integer.parseInt(str2);
				if(hours[j]>mtime)
				{
					JOptionPane.showMessageDialog(null,"Working hours more than"+ mtime +"not allowed by company");
					j--;
				}
				j++;
				if(j<a)
				{
					lsetemp.setText("Employee "+String.valueOf(j+1));
				}
				else
				{
					lsetemp.setText("Employee Detail Completed !");
				}
			}
		}
	}

	public class OvertimeHoursTable implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int n=a;
			JFrame f=new JFrame();
			f.setSize(1000,700);
			String [] column={"Name","Overtime Hours","Total Hours","Normal Wages","Overtime Wages","Total Wages"};
			String [][] data=new String[a][6];
			int extra2[]=new int[n];
			int extra[]=new int[n];
			int eh[]=new int[n];
			int eh2[]=new int[n];
			int e1=0,e2=0;
			
			for(int i=0;i<n;i++)
			{
				if(hours[i]>mtime)
				{
					extra2[e2]=i;
					eh2[e2]=hours[i]-ntime;
					e2++;
				}
				else if(hours[i]>ntime)
				{
					extra[e1]=i;
					eh[e1]=hours[i]-ntime;
					e1++;
				}
			}
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","password");
				Statement stmt = con.createStatement();
				
				for(int i=0;i<e1;i++)
				{
					data[i][0]=String.valueOf(name[extra[i]]);
					data[i][1]=String.valueOf(eh[i]);
					data[i][2]=String.valueOf(eh[i]+ntime);
					data[i][3]=String.valueOf(ntime*nwage);
					data[i][4]=String.valueOf(eh[i]*owage);
					data[i][5]=String.valueOf(eh[i]*owage+ntime*nwage);
				
					String insertst = "insert into overtime "+"values ('" + data[i][0]+"', " + data[i][1]+", " + data[i][2]+", " + data[i][3]+", " + data[i][4]+", " + data[i][5] + ");";
					stmt.executeUpdate(insertst);
				}
				
				con.close();
				
			}catch(Exception ex) { 
				System.out.println(e);
			}
			
			table=new JTable(data,column);
		 	table.setBounds(30,40,200,300);
		  JScrollPane pane=new JScrollPane(table);
		  f.add(pane);
		  f.setVisible(true);
		}
	}
	public class OverallHoursTable implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame f=new JFrame();
			f.setSize(1000,700);
			String [] column = {"Name","Total working hours","Total wage","Overtime"};
			String [][] data=new String[a][4];
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","password");
				Statement stmt = con.createStatement();
				
				for(int i=0;i<a;i++)
				{
					data[i][0]=String.valueOf(name[i]);
					data[i][1]=String.valueOf(hours[i]);
					data[i][2]=String.valueOf((ntime*nwage)+(hours[i]-ntime)*owage);
					if(hours[i]>ntime)
					{
						data[i][3]="Yes";
					}
					else
					{
						data[i][3]="No";
					}
					
					String insertst = "insert into overall "+"values ('" + data[i][0]+"', " + data[i][1]+", " + data[i][2]+", '" + data[i][3] + "');";
					stmt.executeUpdate(insertst);
					
				}
				
				con.close();
			}catch(Exception ex2) { 
				System.out.println(e);
			}
			
			
			table=new JTable(data,column);
		  table.setBounds(30,40,200,300);
		  JScrollPane pane=new JScrollPane(table);
		  f.add(pane);
		  f.setVisible(true);
		}
	}
	public static void main(String args[])
	{
		EmployeeProject p1=new EmployeeProject("Food Corporation");
		p1.setSize(1000,700);
		p1.setLayout(null);
		p1.setVisible(true);
		p1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p1.set();
	}
}
