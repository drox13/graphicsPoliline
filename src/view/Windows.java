package view;

import javax.swing.JFrame;

public class Windows extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Windows() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Graphics");
		setSize(500, 500);
		
		PanelGraphic panelGraphic = new PanelGraphic();
		add(panelGraphic);
		
		

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Windows();
	}
}
