/*************************************************************************
 * Author: Chris Rees
 * Date: 3/22/2010
 * File: About.java
 * Purpose: The about/license fiels in the Help menu
*************************************************************************/

package com.serneum.jsweeper.gui;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class About extends JFrame
{
	JEditorPane about, licField, change;
	JScrollPane licPanel, aboutPanel, changePanel;
	
	public About()
	{
		JTabbedPane tabPane = new JTabbedPane();
		makePanels();
		tabPane.addTab("About", aboutPanel);
		tabPane.addTab("Changelog", changePanel);
		tabPane.addTab("License", licPanel);
		
		add(tabPane);
		tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void makePanels()
	{
		try
		{
			about = new JEditorPane(About.class.getResource("/docs/about.html"));
			licField = new JEditorPane(About.class.getResource("/docs/license.txt"));
			change = new JEditorPane(About.class.getResource("/docs/changelog.txt"));
			about.setEditable(false);
			licField.setEditable(false);
		}
		catch(IOException IOE)
		{
			System.out.println("File not found");
		}
		
		aboutPanel = new JScrollPane(about, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		aboutPanel.setPreferredSize(new Dimension(450, 300));
		
		changePanel = new JScrollPane(change, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		changePanel.setPreferredSize(new Dimension(450, 300));
		
		licPanel = new JScrollPane(licField, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		licPanel.setPreferredSize(new Dimension(450, 300));
	}
}
