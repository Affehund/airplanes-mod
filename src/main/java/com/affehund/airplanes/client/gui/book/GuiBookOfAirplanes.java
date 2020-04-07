package com.affehund.airplanes.client.gui.book;

import java.util.Stack;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.gui.book.buttons.FrontPageButton;
import com.affehund.airplanes.client.gui.book.buttons.NextPageButton;
import com.affehund.airplanes.client.gui.book.pages.BookLoader;
import com.affehund.airplanes.client.gui.book.pages.ChapterPage;
import com.affehund.airplanes.client.gui.book.pages.MainPage;
import com.affehund.airplanes.client.gui.book.pages.DefaultPage;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class GuiBookOfAirplanes extends GuiScreen
{
	public static int BOOK_WIDTH = 256;
	public static int BOOK_HEIGHT = 177;

	public final Stack<DefaultPage[]> history;
	public int currPage;
	public int realCurrPage;
	private DefaultPage[] openPages;

	public static ResourceLocation bookPageTexture = new ResourceLocation(
			Reference.MODID + ":textures/gui/book/book.png");

	// Buttons
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPrevPage;
	private GuiButton buttonFrontPage;

	public GuiBookOfAirplanes()
	{
		this.openPages = new DefaultPage[16];
		this.history = new Stack<>();
	}

	private int getPageCount()
	{
		int count = 0;
		for (DefaultPage page : this.openPages)
		{
			if (page != null)
			{
				count++;
			} else
			{
				return count;
			}
		}
		return count;
	}

	@Override
	public void initGui()
	{
		this.openPages[0] = BookLoader.INSTANCE.getMainPage();
		this.reloadButtons();
	}

	private void reloadButtons()
	{
		int xOffset = (width - BOOK_WIDTH) / 2;
		int yOffset = (this.height / 2) - (BOOK_HEIGHT / 2) - 20;

		this.buttonNextPage = new NextPageButton(1, xOffset + 180, 240, true);
		this.buttonPrevPage = new NextPageButton(2, xOffset + 38, 240, false);
		this.buttonFrontPage = new FrontPageButton(3, xOffset + 114, 240);

		this.buttonList.clear();
		this.buttonList.add(buttonNextPage);
		this.buttonList.add(buttonPrevPage);
		this.buttonList.add(buttonFrontPage);

		updateScreen();

		this.openPages[this.currPage].init(this.buttonList, 4, xOffset, yOffset);
	}

	@Override
	public void updateScreen()
	{
		this.buttonPrevPage.visible = currPage > 0;
		this.buttonNextPage.visible = (currPage < this.getPageCount() - 1);
		this.buttonFrontPage.visible = !this.history.isEmpty();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(bookPageTexture);
		int xOffset = (width - BOOK_WIDTH) / 2;
		int yOffset = (this.height / 2) - (BOOK_HEIGHT / 2) - 20;
		drawTexturedModalRect(xOffset, yOffset, 0, 0, BOOK_WIDTH, BOOK_HEIGHT);
		this.openPages[this.currPage].drawPage(this, fontRenderer, itemRender, xOffset, yOffset);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void drawBackground(int tint)
	{
		super.drawBackground(tint);
	}

	@Override
	protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick)
	{

	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		BookLoader loader = BookLoader.INSTANCE;
		if (button instanceof ChapterPage.ChapterButton)
		{
			this.history.push(this.openPages);
			this.currPage = 0;
			String destination = ((MainPage) this.openPages[this.currPage]).getDestination(((ChapterPage.ChapterButton) button));
			this.openPages = loader.parsePages(loader.parseFile("assets/airplanes/book/" + destination + ".json"));
			this.reloadButtons();
		} else if (button == this.buttonNextPage)
		{
			if (this.currPage < this.getPageCount() - 1)
			{
				++this.currPage;
				this.reloadButtons();
			}
		} else if (button == this.buttonPrevPage)
		{
			if (this.currPage > 0)
			{
				--this.currPage;
				this.reloadButtons();
			}
		} else if (button == buttonFrontPage)
		{
			if (!this.history.isEmpty())
			{
				this.openPages = this.history.pop();
				this.currPage = 0;
				this.reloadButtons();
			}
		}
	}

	@Override
	public void onGuiClosed()
	{
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return true;
	}
}