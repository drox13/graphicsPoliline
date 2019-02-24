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
	private String vectorValuesX [] = null;
	private double vectorValuesY [] = null;
	private int rankY;
	private int xValuesForPoints [];
	private int yValuesForPoints[];

	public PanelGraphic() {
		panel();
	}

	public PanelGraphic(String vectorX[], double vectorY[]) {
		panel();
		vectorValuesX = vectorX;
		vectorValuesY = vectorY;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		refreshMargins();
		checkSizeVectors();

		paintLineX(g);
		paintLineY(g);
		g.setColor(Color.BLACK);
		marquillasX(g);
		marquillasY(g);

		cuadricula(g);

		paintPointInterseption(g);
		paintPoliline(g);
		drawBarGrafhics(g);
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
	 * El metodo se encarda de comprobar que para cada x exista un y y asi determine el tamaño de los vectores
	 */
	private void checkSizeVectors() {
		if (vectorValuesX.length>vectorValuesY.length) {
			xValuesForPoints = new int [vectorValuesY.length]; 
			yValuesForPoints = new int [vectorValuesY.length];	
		}else if(vectorValuesX.length<vectorValuesY.length){
			xValuesForPoints = new int [vectorValuesX.length]; 
			yValuesForPoints = new int [vectorValuesX.length];				
		}else {
			xValuesForPoints = new int [vectorValuesX.length]; 
			yValuesForPoints = new int [vectorValuesY.length];				
		}
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
		numDivisionsX = xValuesForPoints.length;
		intervalX = totalSizeAxisX/numDivisionsX;
		g.drawLine(intervalX+marginXLeft, marginYButton-5, intervalX+marginXLeft, marginYButton+5);
		for (int i = 2; i < numDivisionsX+1; i++) {
			g.drawLine(intervalX*i+marginXLeft, marginYButton-5, intervalX*i+marginXLeft, marginYButton+5);
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

	/*
	 * pone las marquillas del eje Y
	 */
	public void marquillasY(Graphics g) {
		int higher = (int)Math.ceil(higherValueY());
		if (higher%2 !=0) {
			int div= 3;
			higher=higher+div; //se puede poner +1
			numDivisionsY = higher/div;
		}else {
			numDivisionsY = higher/2;
		}
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
	public double higherValueY() {
		double higher = vectorValuesY[0];
		for (int i = 0; i < yValuesForPoints.length; i++) {
			if (vectorValuesY[i]>higher) {
				higher = vectorValuesY[i];
			}
		}
		return higher;
	}


	/*
	 * Forma la cuadricula teniendo en cienta la marquillas de X y Y
	 */
	public void cuadricula(Graphics g) {
		g.setColor(Color.GRAY);
		for (int i = 1; i < numDivisionsX+1; i++) {			
			g.drawLine(marginXLeft+intervalX*i, marginYTop, marginXLeft+intervalX*i, marginYButton);
		}
		for (int i = 1; i < numDivisionsY+1; i++) {
			g.drawLine(marginXLeft, marginYButton+intervalY*i, marginXRight, marginYButton+intervalY*i);
		}
	}

	/*
	 * metodo se encarga de posicionar los puntos dentro del plano
	 */
	public void paintPointInterseption(Graphics g) {
		g.setColor(Color.GREEN);
		//		checkSizeVectors();
		int x, y = 0;
		for (int i = 0; i < xValuesForPoints.length; i++) {
			x= marginXLeft+intervalX*(i+1);
			y = (int) (marginYButton+(intervalY*vectorValuesY[i])/rankY);
			g.fillOval(x-5, y-5, 10, 10);
			xValuesForPoints[i] = x;
			yValuesForPoints[i] = y;
		}
	}


	public void paintPoliline(Graphics g) {
		g.setColor(Color.BLUE);
		for (int i = 0; i < xValuesForPoints.length; i++) {
			g.drawPolyline(xValuesForPoints, yValuesForPoints, xValuesForPoints.length);
		}
	}

	public void panel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(5, 100, 100, 100);
		JLabel lb = new JLabel("GRAFICA");
		panel.add(lb);
		add(panel);
	}

	public void drawBarGrafhics(Graphics g) {
		g.setColor(Color.PINK);
		for (int i = 0; i < xValuesForPoints.length; i++) {
			g.fillRect(marginXLeft+intervalX*(i+1)-getWidth()*2/100, (int)(marginYButton+(intervalY*vectorValuesY[i])/rankY),
					getWidth()*4/100, (int)(intervalY*vectorValuesY[i]/rankY)*-1);
		}
	}
}