package com.affehund.airplanes.client.gui;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.client.gui.pages.Page;
import com.affehund.airplanes.client.gui.pages.PageBlocks;
import com.affehund.airplanes.client.gui.pages.PageBook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiBookOfAirplanes extends GuiScreen {
	
	public static int getInventoryKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindInventory.getKeyCode();
	}

	public static int getLeftKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode();
	}	

	public static int getRightKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode();
	}

	protected static final ResourceLocation bookTexture = new ResourceLocation("airplanes", "textures/gui/book/BookOfAirplanes.png");
	protected static final ResourceLocation frameTexture = new ResourceLocation("airplanes", "textures/gui/book/frame.png");
	protected static final ResourceLocation arrowTexture = new ResourceLocation("airplanes", "textures/gui/book/arrows.png");
	protected static final ResourceLocation blockTextures = new ResourceLocation("airplanes", "textures/gui/book/blockTextures.png");

	protected static int index;

	protected FontRenderer fontRenderer;


	protected int xSize = 256, ySize = 200;
	protected Page[] pages;
	protected EntityPlayer player;

	public GuiBookOfAirplanes(EntityPlayer player) {

		this.player = player;
		init();

		if (player.isSneaking()) {
			index = 0;
		}
	}
	
	public GuiBookOfAirplanes(World world, int x, int y, int z, EntityPlayer player) {

		this.player = player;
		init();

		int i = getPageFor(world, x, y, z);
		if (i != 0) {
			this.index = i;
		}
	}

	private void init() {
		this.fontRenderer = Minecraft.getMinecraft().fontRenderer;

		pages = new Page[23];
		int i = 0;
		pages[i++] = new PageBook();
		pages[i++] = new PageBlocks();
		// TODO: INDEX 
	}

	private int getPageFor(World world, int x, int y, int z) {
		for (int i = 0; i < pages.length; i++) {
			if (pages[i].isInfoAbout(world, x, y, z)) {
				return i;
			}
		}
		return 0;
	}


	public boolean doesGuiPauseGame() {
		return false;
	}

	protected void keyTyped(char par1, int par2) {
		if (par2 == 1 || par2 == GuiBookOfAirplanes.getInventoryKey()) {
			this.mc.player.closeScreen();
		}

		else if (par2 == GuiBookOfAirplanes.getLeftKey()) {
			if (index > 0) {
				index--;
			}
		}

		else if (par2 == GuiBookOfAirplanes.getRightKey()) {
			if (index < pages.length - 1) {
				index++;
			}
		}
	}

	protected void onPageChanged() {
	}

	protected void mouseMovedOrUp(int x, int y, int buttonID) {
//		super.mouseMovedOrUp(x, y, buttonID);
		if (buttonID == 0) {
			int xBook = (width - xSize) / 2;
		    int yBook = (height - ySize) / 2;
			if (x > xBook && x < xBook + 128 && y > yBook && y < yBook + 180) {
				if (index > 0) {
					index--;
					onPageChanged();
				}
			}
			if (x >= xBook + 128 && x < xBook + 256 && y > yBook && y < yBook + 180) {
				if (index < pages.length - 1) {
					index++;
					onPageChanged();
				}
			}
		}	
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.mc.getTextureManager().bindTexture(bookTexture);
	    int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
	    this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

	    pages[index].draw(this, x, y);

		super.drawScreen(par1, par2, par3);
	}

	public void drawArrow(int x, int y, int dir, float scaling) {
		float antiScaling = 1/scaling;

		GL11.glScalef(scaling, scaling, scaling);

		int localX = (int) (x * antiScaling);
		int localY = (int) (y * antiScaling);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(arrowTexture);
		int xOffset = (dir <= 12) ? 80 : 0;
		this.drawTexturedModalRect(localX, localY, xOffset + 20 * dir, 20 * (dir / 12), 20, 20);

		GL11.glScalef(antiScaling, antiScaling, antiScaling);
	}

	public void drawLine(int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(bookTexture);
		this.drawTexturedModalRect(x - 2, y, 10, 0, 100, 4);
	}

	public void drawLine(int x, int y, int length) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(bookTexture);
		this.drawTexturedModalRect(x - 2, y, 10, 0, length, 4);
	}

	public void drawVerticalLine(int x, int y, int length) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(bookTexture);
		this.drawTexturedModalRect(x, y, 0, 10, 4, length);
	}

	public void drawFramedStack(ItemStack stack, int x, int y, float scaling) {
		drawFramedStack(stack, 0, x, y, scaling);
	}

	public void drawFramedStack(ItemStack stack, int meta, int x, int y, float scaling) {
		drawFrame(x, y, scaling / 3F);
		int localX = x + (int) (3F * scaling);
		int localY = y + (int) (3F* scaling);
		drawStack(stack, meta, localX, localY, scaling);
	}

	public void drawFrame(int x, int y, float scaling) {

		float antiScaling = 1/scaling;

		GL11.glScalef(scaling, scaling, scaling);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(frameTexture);
		this.drawTexturedModalRect((int) (x * antiScaling), (int) (y * antiScaling), 0, 0, 64, 64);

		GL11.glScalef(antiScaling, antiScaling, antiScaling);
	}

	public void drawTexture(ResourceLocation texture, int x, int y, int u, int v, int width, int height, int dir, float scaling) {
		float antiScaling = 1/scaling;

		GL11.glScalef(scaling, scaling, scaling);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect((int) (x * antiScaling), (int) (y * antiScaling), u, v, width, height);

		GL11.glScalef(antiScaling, antiScaling, antiScaling);
	}

	public void drawBlockTexture(int x, int y, int id, float scaling) {
		int u = (id * 16) % 256;
		int v = (id / 16) * 16;
		this.drawTexture(blockTextures, x, y, u, v, 16, 16, id, scaling);
	}

	public void drawBlockArray(int x, int y, int[][] ids, float scaling) {

		float antiScaling = 1/scaling;
		
		GL11.glScalef(scaling, scaling, scaling);

		int localX = (int) (x * antiScaling);
		int localY = (int) (y * antiScaling);

	
		for (int xID = 0; xID < ids.length; xID++) {
			for (int yID = 0; yID < ids[0].length; yID++) {
				drawBlockTexture(localX + 16 * yID, localY + 16 * xID, ids[xID][yID], 1F);
			}
		}

		GL11.glScalef(antiScaling, antiScaling, antiScaling);
	}

	public void drawStack(ItemStack stack, int x, int y, float scaling) {
		drawStack(stack, 0, x, y, scaling);
	}

	public void drawStack(ItemStack stack, int meta, int x, int y, float scaling) {
		float antiScaling = 1/scaling;
		GL11.glScalef(scaling, scaling, scaling);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int localX = (int) (x * antiScaling);
		int localY = (int) (y * antiScaling);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(antiScaling, antiScaling, antiScaling);

   		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
	}
	
	public void drawString(int x, int y, int width, float scaling, String text) {
		float antiScaling = 1/scaling;

		GL11.glScalef(scaling, scaling, scaling);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		List<String> list = fontRenderer.listFormattedStringToWidth(text, (int) (width * antiScaling));
		for (int i = 0; i < list.size(); i++) {
			int localX = (int) (x * antiScaling);
			int localY = (int) ((y * antiScaling) + i * (fontRenderer.FONT_HEIGHT + 1));
			fontRenderer.drawString(list.get(i), localX, localY, 4210752);
		}

		GL11.glScalef(antiScaling, antiScaling, antiScaling);
	}
}