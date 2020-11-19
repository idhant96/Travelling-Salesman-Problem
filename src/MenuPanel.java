import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	MenuPanel() {
		super(new FlowLayout(FlowLayout.LEFT));
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = createFileMenu();
		JMenu projectMenu = createProjectMenu();
		JMenu aboutMenu  = createAboutMenu();
		
		menuBar.add(fileMenu);
		menuBar.add(projectMenu);
		menuBar.add(aboutMenu);
		add(menuBar);
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		
		return fileMenu;
	}
	
	private JMenu createProjectMenu() {
		JMenu projectMenu = new JMenu("Project");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem runMenuItem = new JMenuItem("Run");
		JMenuItem stopMenuItem = new JMenuItem("Stop");
		
		
		runMenuItem.addActionListener(new RunActionListener());
		
		projectMenu.add(newMenuItem);
		projectMenu.add(runMenuItem);
		projectMenu.add(stopMenuItem);
		
		return projectMenu;
	}
	
	private JMenu createAboutMenu() {
		JMenu aboutMenu = new JMenu("About");
		return aboutMenu;
	}
	
	

}
