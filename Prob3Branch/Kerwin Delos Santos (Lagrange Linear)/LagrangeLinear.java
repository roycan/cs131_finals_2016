import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class LagrangeLinear {
	
	public void plot(float[] x, float[] y){
		JFrame plotframe=new JFrame("Graph");
		JPanel plotpanel=new JPanel();
		MyGraph graph=new MyGraph();
		graph.setx(x);
		graph.sety(y);
		plotpanel.add(graph);
		plotframe.add(plotpanel);
		plotframe.pack();
		plotframe.setVisible(true);
	}
	
	public void plotlinear(float a, float b){
		JFrame plotframe=new JFrame("Graph");
		JPanel plotpanel=new JPanel();
		GraphLinear graph=new GraphLinear();
		graph.seta(a);
		graph.setb(b);
		plotpanel.add(graph);
		plotframe.add(plotpanel);
		plotframe.pack();
		plotframe.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Lagrange window = new Lagrange();
            }
        });
	}
	
	public Lagrange(){
		buildwindow();
	};
		
	private void buildwindow(){
		JFrame frame=new JFrame("Lagrange Interpolation and Linear Regression");
		JButton lagrange=new JButton("Lagrange");
		JButton linear=new JButton("Linear");
		JTextField xfields=new JTextField();
		JTextField yfields=new JTextField();
		JTextField xintfields=new JTextField();
		JLabel xlabel= new JLabel("X values: (separate values with commas)");
		JLabel ylabel= new JLabel("Y values: (separate values with commas)");
		JLabel xintlabel= new JLabel("X Int value: (Only required for Lagrange)");
		
		lagrange.setBounds(50, 220, 100, 25);
		linear.setBounds(200, 220, 100, 25);
		xfields.setBounds(50, 50, 500, 25);
		yfields.setBounds(50, 110, 500, 25);
		xintfields.setBounds(50, 180, 100, 25);
		xlabel.setBounds(50, 30, 250, 25);
		ylabel.setBounds(50, 90, 250, 25);
		xintlabel.setBounds(50, 150, 250, 25);
		
		frame.add(xlabel);
		frame.add(ylabel);
		frame.add(xintlabel);
		frame.add(xfields);
		frame.add(yfields);
		frame.add(xintfields);
		frame.add(lagrange);
		frame.add(linear);
		frame.setSize(700,350);
		frame.setLayout(null);
		frame.setVisible(true);
		
		lagrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xfields.getText().split(",").length == yfields.getText().split(",").length && !xintfields.getText().equals("")){
					String[] text = xfields.getText().split(",");
					int len=text.length;
					float[] x = new float[len+1];
					for (int i = 0; i < len; ++i){
						x[i] = Float.parseFloat(text[i]);
					}
					text = yfields.getText().split(",");
					float[] y = new float[len+1];
					for (int i = 0; i < len; ++i){
						y[i] = Float.parseFloat(text[i]);
					}
					
					float xint = Float.parseFloat(xintfields.getText());
					
					System.out.println("Lagrange Interpolation");
					
					for (int i = 0; i < len; ++i){
						System.out.println(x[i]);
					}
					for (int i = 0; i < len; ++i){
						System.out.println(y[i]);
					}
					
					System.out.println(xint);
					
					float[] L = new float[len];
					
					for (int i = 0; i < len; ++i){
						L[i]=1;
						for (int j = 0; j < len; ++j){
							if(i!=j){
								L[i] = L[i]*(xint-x[j])/(x[i]-x[j]);
							}
						}
					}
					for (int i = 0; i < L.length; ++i){
						System.out.println(L[i]);
					}
					for (int i = 0; i < L.length; ++i){
						L[i]=y[i]*L[i];
					}
					float sum=0;
					for (int i = 0; i < len; ++i){
						sum=sum+L[i];
					}
					for (int i = len-1; i > -1; --i){
						if(x[i]<xint){
							x[i+1]=xint;
							y[i+1]=sum;
							break;
						}
						else{
							x[i+1]=x[i];
							y[i+1]=y[i];
						}
					}
					JOptionPane.showMessageDialog(null, "Xint will be "+sum, "Alert", JOptionPane.INFORMATION_MESSAGE);
					plot(x, y);
				}
				else if (xintfields.getText().equals("")){JOptionPane.showMessageDialog(null, "Xint must not be empty", "Alert", JOptionPane.INFORMATION_MESSAGE);}
				else{
					JOptionPane.showMessageDialog(null, "X and Y must be equal.", "Alert", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		linear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xfields.getText().split(",").length == yfields.getText().split(",").length){
					String[] text = xfields.getText().split(",");
					float[] x = new float[text.length];
					for (int i = 0; i < text.length; ++i){
						x[i] = Float.parseFloat(text[i]);
					}
					text = yfields.getText().split(",");
					float[] y = new float[text.length];
					for (int i = 0; i < text.length; ++i){
						y[i] = Float.parseFloat(text[i]);
					}
					
					System.out.println("Linear Regression");
					
					for (int i = 0; i < x.length; ++i){
						System.out.println(x[i]);
					}
					for (int i = 0; i < y.length; ++i){
						System.out.println(y[i]);
					}
					float sumx=0;
					for (int i = 0; i < x.length; ++i){
						sumx=sumx+x[i];
					}
					float sumy=0;
					for (int i = 0; i < y.length; ++i){
						sumy=sumy+y[i];
					}
					float sumxy=0;
					for (int i = 0; i < x.length; ++i){
						sumxy=sumxy+(x[i]*y[i]);
					}
					float sumxx=0;
					for (int i = 0; i < x.length; ++i){
						sumxx=sumxx+(x[i]*x[i]);
					}
					float a=(x.length*sumxy-(sumx*sumy))/((x.length*sumxx)-(sumx*sumx));
					float b=((sumxx*sumy)-(sumxy*sumx))/((x.length*sumxx)-(sumx*sumx));
					plotlinear(a,b);
					JOptionPane.showMessageDialog(null, "A0: "+a+"\nA1: "+b, "Alert", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "X and Y must be equal.", "Alert", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
}

class MyGraph extends JPanel{
	private static final long serialVersionUID = 1L;
	public static float[] x1;
	public static float[] y1;
	public void setx(float[] x){
		x1=x;
		for (int i = 0; i < x1.length; ++i){
			System.out.println(x1[i]);
			x1[i]=(x1[i]*10)+5;
		}
	}
	
	public void sety(float[] y){
		y1=y;
		for (int i = 0; i < y1.length; ++i){
			System.out.println(y1[i]);
			y1[i]=390-y1[i]*10;
		}
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Line2D yaxis = new Line2D.Float(5, 395, 5, 5);
		g2.draw(yaxis);
		Line2D xaxis = new Line2D.Float(5, 395, 395, 395);
		g2.draw(xaxis);
		for(int i=0;i<39;++i){
			Line2D ytick = new Line2D.Float(5, 395-10*i, 10, 395-10*i);
			g2.draw(ytick);
		}
		for(int i=0;i<39;++i){
			Line2D xtick = new Line2D.Float(395-10*i, 395, 395-10*i, 390);
			g2.draw(xtick);
		}
		for(int i=0;i<y1.length-1;++i){
			Line2D line1 = new Line2D.Float(x1[i], y1[i], x1[i+1], y1[i+1]);
			g2.draw(line1);
			g2.draw(new Ellipse2D.Float(x1[i]-5, y1[i]-5, 10, 10));
		}
		g2.draw(new Ellipse2D.Float(x1[x1.length-1]-5, y1[y1.length-1]-5, 10, 10));
    }
	
	public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}

class GraphLinear extends JPanel{
	private static final long serialVersionUID = 1L;
	public static float a1;
	public static float b1;
	public void seta(float a){
		a1=a;
	}
	
	public void setb(float b){
		b1=b;
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Line2D yaxis = new Line2D.Float(5, 395, 5, 5);
		g2.draw(yaxis);
		Line2D xaxis = new Line2D.Float(5, 395, 395, 395);
		g2.draw(xaxis);
		for(int i=0;i<39;++i){
			Line2D ytick = new Line2D.Float(5, 395-10*i, 10, 395-10*i);
			g2.draw(ytick);
		}
		for(int i=0;i<39;++i){
			Line2D xtick = new Line2D.Float(395-10*i, 395, 395-10*i, 390);
			g2.draw(xtick);
		}
		for(int i=0;i<200;++i){
			Line2D line1 = new Line2D.Float(i+5, 390-(a1*i+b1), i+1+5, 390-(a1*(i+1)+b1));
			g2.draw(line1);
		}
    }
	
	public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}