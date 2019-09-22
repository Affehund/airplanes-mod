package com.affehund.airplanes.client.render;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.rendering.Face;
import com.affehund.airplanes.client.rendering.objParser;
import com.affehund.airplanes.common.entities.EntityBoeing737;

import net.minecraftforge.fml.client.registry.IRenderFactory;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBoeing737 extends Render<EntityBoeing737>
{
	public RenderBoeing737(RenderManager renderManager)
    {
        super(renderManager);
    }

	public static Factory FACTORY = new Factory();
	private static final ResourceLocation textureLoc = new ResourceLocation(Reference.MODID, "textures/entity/boeing_737_800.png");
	
	objParser objFile = new objParser("airplanes:models/entity/boeing_737_800.obj");	
	Tessellator TESR = Tessellator.getInstance();
	BufferBuilder buffer = TESR.getBuffer();
	

	public void renderList(List<Face> faces, int color) {
        for (Face f : faces) for (int i = 0; i < f.getVertexes().length; i++) {
            buffer.addVertexData(f.getVertexes()[i].setColor((color)).setAlpha((color >> 24)).getVertexData(malisisVertexFormat, null));
        }
    }

	public VertexFormat malisisVertexFormat = new VertexFormat() {
        {
            addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
            addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.COLOR, 4));
            addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV, 2));
            addElement(new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUsage.UV, 2));
            addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.NORMAL, 3));
            addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.PADDING, 1));
        }
	};

	protected ResourceLocation getEntityTexture(EntityBoeing737 parEntityLiving) 
	{
		return textureLoc;
	}

    public static class Factory implements IRenderFactory<EntityBoeing737> 
    {
        @Override
        public Render<? super EntityBoeing737> createRenderFor(RenderManager manager) 
        {
            return new RenderBoeing737(manager);
        }
    }
}





