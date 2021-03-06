package com.serneum.jsweeper.engine;

import javax.swing.*;

import com.serneum.jsweeper.gui.Game;

public class Images
{
	public static void initImages()
	{
		Game.BlackMine = createImageIcon("BlackMine");
		Game.Flag = createImageIcon("Flag");
	}
	
	private static ImageIcon createImageIcon(String name)
	{
		java.net.URL imgURL = Images.class.getResource("/images/" + name + ".png");
		
		return new ImageIcon(imgURL);
	}
}
