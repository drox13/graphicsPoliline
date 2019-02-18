package view;

import javax.swing.JFrame;

public class Windows extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Windows() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Graphics");
		setSize(500, 500);
		
		int vectorX[] = {1,2,8, 9,12};
		int vectorY[] = {4,3,8, 9,10};
		PanelGraphic panelGraphic = new PanelGraphic(vectorX, vectorY);
		add(panelGraphic);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Windows();
	}
}
