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
	private int vectorValuesX [] = null;
	private int vectorValuesY [] = null;
	private int rankX, rankY;
	private int xValues [];
	private int yValues[];

	public PanelGraphic() {
		panel();
	}

	public PanelGraphic(int vectorX[], int vectorY[]) {
		panel();
		vectorValuesX = vectorX;
		vectorValuesY = vectorY;
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

		paintPointInterseption(g);
		paintPoliline(g);
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
		////		System.out.println(getWidth() + " Ancho ventana");
		//		System.out.println("margen x left:"+ marginXLeft);
		////		System.out.println("margen x Right:"+  (getWidth()-marginXRight));
		int higherX = higherValueX();
		//
		numDivisionsX = vectorValuesX.length;
		rankX = higherX/numDivisionsX;
		////		System.out.println("Tamaño de la linea en px: "+ totalSizeAxisX);
		intervalX = totalSizeAxisX/numDivisionsX;
		//		System.out.println("intervalo en px: " + intervalX);
		//
		g.drawLine(intervalX+marginXLeft, marginYButton-5, intervalX+marginXLeft, marginYButton+5);
		//		System.out.println("primera marquilla se pinto en: " + (intervalX+marginXLeft));
		//
		for (int i = 2; i < numDivisionsX+1; i++) {
			g.drawLine(intervalX*i+marginXLeft, marginYButton-5, intervalX*i+marginXLeft, marginYButton+5);
			//			System.out.println(intervalX*i+marginXLeft);
		}
		numbersX(g, numDivisionsX, intervalX);
	}

	/*
	 * pone los numeros en el eje X
	 */
	public void numbersX(Graphics g, int numDivisionsX, int intervalX) {
		int posLetter = marginYButton+20;

		for (int i = 0; i < numDivisionsX; i++) {
			String value = String.valueOf(vectorValuesX[i]);
			int r = i+1;
			g.drawString(value, intervalX*r+marginXLeft-5, posLetter);
		}
	}

	public int higherValueX() {
		int higherX = vectorValuesX[0];
		for (int i = 0; i < vectorValuesX.length; i++) {
			if (vectorValuesX[i]>higherX) {
				higherX = vectorValuesX[i];
			}
		}
		return higherX;
	}

	/*
	 * pone las marquillas del eje Y
	 */
	public void marquillasY(Graphics g) {
		int higher = higherValueY();
		numDivisionsY = higher/2;
		rankY = higher/numDivisionsY;
		intervalY = totalSizeAxisY/numDivisionsY;

		g.drawLine(marginXLeft-5, intervalY+marginYButton, marginXLeft+5, intervalY+marginYButton);
		for (int i = 2; i < numDivisionsY+1; i++) {
			g.drawLine(marginXLeft-5, intervalY*i+marginYButton, marginXLeft+5, intervalY*i+marginYButton);
		}
		numbersY(g, numDivisionsY, intervalY);
	}

	/*
	 * coloca los numeros del eje y
	 */
	public void numbersY(Graphics g, int numDivisionsY, int intervalY) {
		int posLetter = marginXLeft-25;

		for (int i = 0; i < numDivisionsY+1; i++) {
			String value = String.valueOf(rankY*i);
			g.drawString(value, posLetter, intervalY*i+marginYButton+5);
		}
	}

	/*
	 * metodo determina el valor mas alto dentro del vector
	 * retorna el valor mas alto
	 */
	public int higherValueY() {
		int higher = vectorValuesY[0];
		for (int i = 0; i < vectorValuesY.length; i++) {
			if (vectorValuesY[i]>higher) {
				higher = vectorValuesY[i];
			}
		}
		return higher;
	}

	public void paintPoliline(Graphics g) {
		g.setColor(Color.BLUE);

		for (int i = 0; i < xValues.length; i++) {
			g.drawPolyline(xValues, yValues, xValues.length);
		}
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
		xValues = new int [vectorValuesX.length]; 
		yValues = new int [vectorValuesY.length];
		int x, y = 0;
		for (int i = 0; i < vectorValuesX.length; i++) {
			x= marginXLeft+intervalX*(i+1);
			y = marginYButton+(intervalY*vectorValuesY[i])/rankY;
			g.fillOval(x-5, y-5, 10, 10);
			xValues[i] = x;
			yValues[i] = y;
		}
		g.setColor(Color.PINK);
	}

	public void panel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(5, 100, 100, 100);
		JLabel lb = new JLabel("GRAFICA");
		panel.add(lb);
		add(panel);
	}

}
