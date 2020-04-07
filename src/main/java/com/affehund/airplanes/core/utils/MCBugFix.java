package com.affehund.airplanes.core.utils;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */
public class MCBugFix extends Canvas implements Runnable
{
	private static final long serialVersionUID = -7716635570782190326L;

	JFrame frame = new JFrame("Minecraft 2");
	public boolean running = false;
	private int WIDTH = 600;
	private int HEIGHT = 480;

	public MCBugFix()
	{
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
	}

	public synchronized void start()
	{
		running = true;
		new Thread(this, "Game-Thread").start();
	}

	public void stop()
	{
		if (!running)
			return;
		running = false;
	}

	@Override
	public void run()
	{
		requestFocus();
		long lastTime = System.nanoTime();
		double ns = 1000000000.0 / 60.0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		double delta = 0;

		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1)
			{
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

			if (shouldRender)
			{
				frames++;
				render();
			}

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frame.setTitle("MINECRAFT 2" + " | " + ticks + " tps"+ " | " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
//		System.exit(0);
	}

	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// drawing
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, 400, 300);
		g.translate(400, 300);
		// end of drawing
		g.dispose();
		bs.show();
	}

	public void tick()
	{
	}

	public static void main()
	{
		MCBugFix game = new MCBugFix();
		game.frame.add(game);
		game.frame.pack();
		game.frame.setSize(400, 300);
		game.frame.setLocationRelativeTo(null);
		game.frame.setResizable(false);
		game.frame.setVisible(true);
		game.frame.requestFocus();
		game.frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.err.println("Exiting MC2EasterEgg");
				game.stop();
			}
		});
		game.start();
	}
}