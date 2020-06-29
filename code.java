```
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;

public class project_swing extends JFrame
{
	String name[];
	int hours[];
	JLabel l1,l2;
	JTextField t1;
	JButton b1,b2,b3,b4;
	JTable table;
	int a,i=0,j=0;
  
	public project_swing(){};
  
	public project_swing(String s)
	{
		super(s);
	}
  
	public void set()
	{
		l1=new JLabel("Enter no. of employees");
		t1=new JTextField();
		b1=new JButton("Enter");
		l2=new JLabel("Employee 1");
		b2=new JButton("Enter employee detail");
		b3=new JButton("Overtime");
		b4=new JButton("Overall");

		add(l1);
		add(t1);
		add(b1);
		add(l2);
		add(b2);
		add(b3);
		add(b4);

		setLayout(null);
		l1.setBounds(50,50,150,30);
		t1.setBounds(220,50,100,30);
		b1.setBounds(340,50,100,30);
		l2.setBounds(50,175,300,30);
		b2.setBounds(340,175,300,30);
		b3.setBounds(50,250,100,30);
		b4.setBounds(220,250,100,30);
		b1.addActionListener(new h1());
		b2.addActionListener(new h2());
		b3.addActionListener(new h3());
		b4.addActionListener(new h4());
	}

	public class h1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			a=Integer.parseInt(t1.getText());
			name=new String[a];
			hours=new int[a];
		}
	}

	public class h2 implements ActionListener
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
				if(hours[j]>60)
				{
					JOptionPane.showMessageDialog(null,"Working hours more than 60 not allowed by company");
					j--;
				}
				j++;
				if(j<a)
				{
					l2.setText("Employee "+String.valueOf(j+1));
				}
				else
				{
					l2.setText("Employee Detail Completed !");
				}
			}
		}
	}

	public class h3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int n=a;
			JFrame f=new JFrame();
			f.setSize(700,500);
			String [] column={"Name","Overtime Hours","Total Hours","Normal Wages","Overtime Wages","Total Wages"};
			String [][] data=new String[a][6];
			int extra2[]=new int[n];
			int extra[]=new int[n];
			int eh[]=new int[n];
			int eh2[]=new int[n];
			int e1=0,e2=0;
			for(int i=0;i<n;i++)
			{
				if(hours[i]>60)
				{
					extra2[e2]=i;
					eh2[e2]=hours[i]-40;
					e2++;
				}
				else if(hours[i]>40)
				{
					extra[e1]=i;
					eh[e1]=hours[i]-40;
					e1++;
				}
			}
			for(int i=0;i<e1;i++)
			{
				data[i][0]=String.valueOf(name[extra[i]]);
				data[i][1]=String.valueOf(eh[i]);
				data[i][2]=String.valueOf(eh[i]+40);
				data[i][3]=String.valueOf(40*100);
				data[i][4]=String.valueOf(eh[i]*150);
				data[i][5]=String.valueOf(eh[i]*150+40*100);
			}
			table=new JTable(data,column);
		 	table.setBounds(30,40,200,300);
		  JScrollPane pane=new JScrollPane(table);
		  f.add(pane);
		  f.setVisible(true);
		}
	}
	public class h4 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame f=new JFrame();
			f.setSize(700,500);
			String [] column = {"Name","Total working hours","Total wage","Overtime"};
			String [][] data=new String[a][4];
			for(int i=0;i<a;i++)
			{
				data[i][0]=String.valueOf(name[i]);
				data[i][1]=String.valueOf(hours[i]);
				data[i][2]=String.valueOf((40*100)+(hours[i]-40)*150);
				if(hours[i]>40)
				{
					data[i][3]="Yes";
				}
				else
				{
					data[i][3]="No";
				}
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
		project_swing p1=new project_swing("Food Corporation");
		p1.setSize(700,500);
		p1.setLayout(null);
		p1.setVisible(true);
		p1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p1.set();
	}
}
```