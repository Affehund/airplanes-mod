package com.affehund.airplanes.client.render;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.rendering.Face;
import com.affehund.airplanes.client.rendering.objParser;
import com.affehund.airplanes.common.entities.EntityBoeing737;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
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
public class RenderBoeing737 extends Render<EntityBoeing737>
{
	public RenderBoeing737(RenderManager manager)
	{
		super(manager);
	}

	private final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID,
			":textures/entity/boeing_737_800.png");

	objParser objFile = new objParser(
			readStream(getResourceAsStream(Reference.MODID, ":models/entity/boeing_737_800.obj")));
	Tessellator TESR = Tessellator.getInstance();
	BufferBuilder buffer = TESR.getBuffer();

	public static InputStream getResourceAsStream(ResourceLocation resource)
	{
		return getResourceAsStream(resource.getResourceDomain(), resource.getResourcePath());
	}

	public static InputStream getResourceAsStream(String domain, String path)
	{
		return RenderBoeing737.class.getResourceAsStream("/assets/" + Reference.MODID + "/" + "models/entity/boeing_737_800.obj");
	}

	public static String readStream(InputStream stream)
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return reader.lines().collect(Collectors.joining());
	}

	public void doRender(EntityBoeing737 entity, double x, double y, double z, float entityYaw, float partialTicks,
			int color)
	{
		System.out.println("da render method");
//		objParser objFile = new objParser(
//				readStream(getResourceAsStream(Reference.MODID, ":models/entity/boeing_737_800.obj")));
//		Tessellator TESR = Tessellator.getInstance();
//		BufferBuilder buffer = TESR.getBuffer();

		renderList(objFile.facesQuad, color);
		GlStateManager.translate(x, y, z);

		GlStateManager.pushMatrix();
		float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;

		this.setupTranslation(x, y, z);
		this.setupRotation(entity, yaw);
		this.bindEntityTexture(entity);

		if (objFile.facesTri.size() > 0)
		{
			buffer.begin(GL11.GL_TRIANGLES, malisisVertexFormat);
			renderList(objFile.facesTri, color);
			TESR.draw();
		}

		if (objFile.facesQuad.size() > 0)
		{
			buffer.begin(GL11.GL_QUADS, malisisVertexFormat);
			renderList(objFile.facesQuad, color);
			TESR.draw();
		}

		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public void renderList(List<Face> faces, int color)
	{
		for (Face f : faces)
			for (int i = 0; i < f.getVertexes().length; i++)
			{
				buffer.addVertexData(f.getVertexes()[i].setColor((color)).setAlpha((color >> 24))
						.getVertexData(malisisVertexFormat, null));
			}
	}

	public VertexFormat malisisVertexFormat = new VertexFormat()
	{
		{
			addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT,
					VertexFormatElement.EnumUsage.POSITION, 3));
			addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE,
					VertexFormatElement.EnumUsage.COLOR, 4));
			addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV,
					2));
			addElement(new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUsage.UV,
					2));
			addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE,
					VertexFormatElement.EnumUsage.NORMAL, 3));
			addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE,
					VertexFormatElement.EnumUsage.PADDING, 1));
		}
	};

	public void setupRotation(EntityBoeing737 p_188311_1_, float p_188311_2_)
	{
		GlStateManager.rotate(180 - p_188311_2_, 0.0F, 1.0F, 0.0F);
	}

	public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
	{
		GlStateManager.translate((float) p_188309_1_, (float) p_188309_3_ + 0.375F, (float) p_188309_5_);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBoeing737 entity)
	{
		return TEXTURE;
	}
}