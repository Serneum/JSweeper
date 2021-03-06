/*************************************************************************
 * Author: Chris Rees
 * Date: 3/3/2010
 * File: Menu.java
 * Purpose: The difficulty selection screen
*************************************************************************/

package com.serneum.jsweeper.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DifficultyMenu extends JFrame
{
	JButton easy, med, hard;
	JLabel e, m, h, instruc, instruc2;
	
	public DifficultyMenu()
	{
		//Create difficulty buttons
		easy = new JButton("Beginner");
		med = new JButton("Intermediate");
		hard = new JButton("Expert");
		
		e = new JLabel("10 mines. 9x9 Grid");
		m = new JLabel("40 mines. 16x16 Grid");
		h = new JLabel("99 mines. 16x30 Grid");
		instruc = new JLabel("Choose the level of");
		instruc2 = new JLabel("difficulty you wish to play");
		
		//Create a button handler to determine which button is pressed
		handler bHandler;
		bHandler = new handler();
		
		//Add buttons to the handler
		easy.addActionListener(bHandler);
		med.addActionListener(bHandler);
		hard.addActionListener(bHandler);
		
		//Define pane and add elements
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Set default constraints
		c.gridwidth = 3;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		
		//Position and place buttons and labels
		c.gridy = 0;
		pane.add(instruc, c);
		c.gridy = 1;
		pane.add(instruc2, c);
		
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 2;
		pane.add(easy, c);
		c.gridy = 4;
		pane.add(med, c);
		c.gridy = 6;
		pane.add(hard, c);
		
		c.gridy = 3;
		pane.add(e, c);
		c.gridy = 5;
		pane.add(m, c);
		c.gridy = 7;
		pane.add(h, c);
		
		//Set defaults
  		setTitle("JSweeper");
		setSize(200,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	private class handler implements ActionListener
	{
	  	public void actionPerformed(ActionEvent e)
	  	{
	  		if(e.getSource() == easy)
	  		{
	  			Game.level = 1;
	  			new Game();
	  			dispose();
	  		}
	  		else if(e.getSource() == med)
	  		{
	  			Game.level = 2;
	  			new Game();
	  			dispose();
	  		}
	  		else if(e.getSource() == hard)
	  		{
	  			Game.level = 3;
	  			new Game();
	  			dispose();
	  		}
	  	}
	}
	
	public static void main(String[] args)
	{
		new DifficultyMenu();
	}
}
