package com.serneum.jsweeper.gui;

import javax.swing.*;

public class MenuBar
{	
	public static JMenuBar createMenu()
	{
		//Create a menu bar
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		JMenuItem dif = new JMenuItem("Difficulty");
		JMenuItem q = new JMenuItem("Quit");
		JMenuItem newGame = new JMenuItem("New Game");
		
		newGame.addActionListener(Game.bHandler);
		newGame.setActionCommand("New");
		dif.addActionListener(Game.bHandler);
		dif.setActionCommand("Diff");
		q.addActionListener(Game.bHandler);
		q.setActionCommand("Quit");
		about.addActionListener(Game.bHandler);
		about.setActionCommand("About");
		
		file.add(newGame);
		file.add(dif);
		file.add(q);
		help.add(about);
		menu.add(file);
		menu.add(Box.createHorizontalGlue());
		menu.add(help);
		
		return menu;
	}
}
