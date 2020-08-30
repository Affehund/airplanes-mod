package com.affehund.airplanes.client.model;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.client.trender.OBJHandler;
import com.affehund.airplanes.client.trender.Obj;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

@SideOnly(Side.CLIENT)
public class ModelObj {

	private Obj model;
	private boolean compiled;
	private int displayList;

	public ModelObj(String file) {
		this.model = OBJHandler.INSTANCE.loadModel(getClass().getResourceAsStream("/assets/airplanes/models/entity/" + file));
		this.model.moveDown(6);
//		this.model.scale(0.0625F);
	}
	
	@SideOnly(Side.CLIENT)
	public void render() {
		if (!this.compiled) {
			this.compileDisplayList();
		}
		 GL11.glCallList(this.displayList);      
	}


	private void compileDisplayList() {
		this.displayList = OBJHandler.INSTANCE.createDisplayList(model);
		compiled = true;
	}
}
