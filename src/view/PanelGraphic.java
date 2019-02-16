package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelGraphic extends JPanel {

	private static final long serialVersionUID = 1L;
	private int marginXLeft, marginXRight;
	private int marginYTop, marginYButton;
	private int intervalX, intervalY;
	private int totalSizeAxisX, totalSizeAxisY;
	private int numDivisionsX, numDivisionsY;
	private int x1,x2,x3,y1,y2,y3,x4,y4,x5,y5,x6,y6;
	private int vectorValuesX [] = null;
	private int vectorValuesY [] = null;

	public PanelGraphic() {
		panel();
	}
	
	public PanelGraphic(int vectorX[], int vectorY[]) {
		panel();
		vectorValuesX = vectorX;
		vectorValuesY = vectorY;
	}
	
	public void getRango() {
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		refreshMargins();

		paintLineX(g);
		paintLineY(g);
		g.setColor(Color.BLACK);
		marquillasX(g);
		marquillasY(g);
		
		cuadricula(g);
		
		refreshPointPoliLine();
		paintPoliline(g);
		paintPointInterseption(g);
	}
	/*
	 * este metodo actuliza las margenes y el largo de la linea teniendo en cuenta el tamaño de la ventana
	 */
	public void refreshMargins() {
		marginXLeft = getWidth()*5/100;
		marginXRight = getWidth()*90/100;
		marginYTop = getHeight()*5/100;
		marginYButton = getHeight()*90/100;

		totalSizeAxisX = getWidth()-(marginXLeft+(getWidth()-marginXRight));
		totalSizeAxisY = getHeight()-(marginYButton+(getHeight()-marginYTop));
	}
	
	/*
	 * este metodo pinta el eje X
	 */
	public void paintLineX(Graphics g) {
		g.drawLine(marginXLeft, marginYTop, marginXLeft, marginYButton);
	}
	/*
	 * Pinta el eje Y
	 */
	public void paintLineY(Graphics g) {
		g.drawLine(marginXLeft, marginYButton, marginXRight, marginYButton);
	}
	/*
	 * pone las marquillas del eje X
	 */
	public void marquillasX(Graphics g) {

//		System.out.println(getWidth() + " Ancho ventana");
		System.out.println("margen x left:"+ marginXLeft);
//		System.out.println("margen x Right:"+  (getWidth()-marginXRight));

		numDivisionsX = 20;
//		System.out.println("Tamaño de la linea en px: "+ totalSizeAxisX);
		intervalX = totalSizeAxisX/numDivisionsX;
		System.out.println("intervalo en px: " + intervalX);

		g.drawLine(intervalX+marginXLeft, marginYButton-5, intervalX+marginXLeft, marginYButton+5);
		System.out.println("primera marquilla se pinto en: " + (intervalX+marginXLeft));

		for (int i = 2; i < numDivisionsX+1; i++) {
			g.drawLine(intervalX*i+marginXLeft, marginYButton-5, intervalX*i+marginXLeft, marginYButton+5);
//			System.out.println(intervalX*i+marginXLeft);
		}
		System.out.println("-----------------------");
		numbersX(g, numDivisionsX, intervalX);
	}

	/*
	 * pone las marquillas del eje Y
	 */
	public void marquillasY(Graphics g) {
		numDivisionsY = 20;
		intervalY = totalSizeAxisY/numDivisionsY;
		g.drawLine(marginXLeft-5, intervalY+marginYButton, marginXLeft+5, intervalY+marginYButton);

		for (int i = 2; i < numDivisionsY+1; i++) {
			g.drawLine(marginXLeft-5, intervalY*i+marginYButton, marginXLeft+5, intervalY*i+marginYButton);
		}
		numbersY(g, numDivisionsY, intervalY);
	}
	/*
	 * pone los numeros en el eje X
	 */
	public void numbersX(Graphics g, int numDivisionsX, int intervalX) {
		int posLetter = marginYButton+20;

		for (int i = 1; i < numDivisionsX+1; i++) {
			String value = String.valueOf(10*i);
			g.drawString(value, intervalX*i+marginXLeft-5, posLetter);
		}
	}
	/*
	 * coloca los numeros del eje y
	 */
	public void numbersY(Graphics g, int numDivisionsY, int intervalY) {
		int posLetter = marginXLeft-25;

		for (int i = 0; i < numDivisionsY+1; i++) {
			String value = String.valueOf(10*i);
			g.drawString(value, posLetter, intervalY*i+marginYButton+5);
		}
	}
/*
 * metodo que refresca los puntos de la polilinea
 */
	public void refreshPointPoliLine() {
		x1 = marginXLeft+intervalX;
//		System.out.println("Margen X: " +marginXLeft);
//		System.out.println("intrvalo x1: "+ intervalX);
//		System.out.println("x1: " + marginXLeft+intervalX);
		y1= intervalY*10+marginYButton;
		x2 = marginXLeft+intervalX;
		y2= intervalY*9+marginYButton;
		x3 = marginXLeft+intervalX+intervalX/2;
		y3 = marginYButton+intervalY*8-intervalY/2;
		x4 = marginXLeft+intervalX*2+intervalX/2;
		y4 = marginYButton+intervalY*7-intervalY/2;
		x5 = marginXLeft + intervalX*3+intervalX/2;
		y5 = marginYButton + intervalY*6;
		x6 = marginXLeft + intervalX*6;
		y6 = marginYButton + intervalY*6;
	}
	
	public void paintPoliline(Graphics g) {
		g.setColor(Color.BLUE);
		int Xvalues[] = {x1, x2, x3, x4, x5, x6};
		int Yvalues[] = {y1, y2, y3, y4, y5, y6};
		g.drawString("A", x1, y1);
		g.drawString("B", x2, y2);
		g.drawString("C", x3, y3);
		g.drawString("D", x4, y4);		
		g.drawString("E", x5, y5);
		g.drawString("F", x6, y6);
		g.drawPolyline(Xvalues, Yvalues, 6);
	}
	
	public void cuadricula(Graphics g) {
		g.setColor(Color.GRAY);
		for (int i = 1; i < numDivisionsX+1; i++) {			
			g.drawLine(marginXLeft+intervalX*i, marginYTop, marginXLeft+intervalX*i, marginYButton);
		}
		for (int i = 1; i < numDivisionsY+1; i++) {
			g.drawLine(marginXLeft, marginYButton+intervalY*i, marginXRight, marginYButton+intervalY*i);
		}
	}
	
	public void paintPointInterseption(Graphics g) {
		g.setColor(Color.GREEN);
//		System.out.println(pontsXY[0]);
//		System.out.println(pontsXY[1]);
//		g.fillOval(100, 100, 1000, 1000);	
//		g.fillOval(200, 200, 90, 90);
		g.fillOval(intervalX+marginXLeft-5, marginYButton-5, 10, 10);
		g.fillOval(intervalX+marginXLeft-5, marginYButton-5+intervalY, 10, 10);
		g.fillOval(intervalX+marginXLeft-5, marginYButton-5+intervalY*2, 10, 10); //unto den la cuadricla
		g.fillOval(intervalX+marginXLeft-5, marginYButton-5+intervalY*3, 10, 10); //unto den la cuadricla
//		g.fillOval(intervalX*6+marginXLeft-5, marginYButton-5+intervalY*6, 10, 10); //Punto den la cuadricla
//		g.fillOval(x6-5, y6-5, 10, 10); // punto del ovalo
		
		g.setColor(Color.PINK);
		if (x6-5 == intervalX*6+marginXLeft-5 && y6-5 == marginYButton-5+intervalY*6) {
			g.fillOval(x6-5, y6-5, 10, 10);
		} 		
	}

	public void panel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(10, 100, 100, 100);
		JLabel lb = new JLabel("GOLA");
		panel.add(lb);
		add(panel);
	}

}
