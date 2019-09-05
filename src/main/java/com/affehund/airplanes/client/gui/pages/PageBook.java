package com.affehund.airplanes.client.gui.pages;

import com.affehund.airplanes.client.gui.GuiBookOfAirplanes;

public class PageBook extends Page {
	
	@Override
	public void draw(GuiBookOfAirplanes book, int x, int y) {

		// LEFT SIDE
		book.drawString(x + 22, y + 18, 100, 1F, "The Book of Gears");
		book.drawLine(x + 22, y + 24);

		String text1 = "The book of gears is your guide through this mod. You can turn";

		book.drawString(x + 22 + 45, y + 32, 55, 0.7F, text1);

		String text2 = "pages by either clicking on the page or use your left/right keys (\"A\" and \"D\" by"
				+ " default). You can also click on blocks in the world to open the corresponding page if available."
				+ " The book will remember the last page you have opened unless you use shift-righclick.";

		book.drawString(x + 22, y + 72, 100, 0.7F, text2);
	}
}
