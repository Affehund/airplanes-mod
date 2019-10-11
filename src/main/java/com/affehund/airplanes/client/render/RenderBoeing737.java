package com.affehund.airplanes.client.render;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.rendering.Face;
import com.affehund.airplanes.client.rendering.objParser;
import com.affehund.airplanes.common.entities.EntityBoeing737;

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

	String source = "assets/airplanes/models/entity/boeing_737_800.obj";
	
    public static InputStream getResourceAsStream(ResourceLocation resource) 
    {
        return getResourceAsStream(resource.getResourceDomain(), resource.getResourcePath());
    }

    public static InputStream getResourceAsStream(String domain, String path) 
    {
        return AirplanesMod.class.getResourceAsStream("assets/"+domain+"/"+path);
    }

    public static String readStream(InputStream stream) 
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines().collect(Collectors.joining());
    }
	
	objParser objFile = new objParser(readStream(getResourceAsStream("airplanes", "models/entity/boeing_737_800.obj")));
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
	
	@Override
	public void doRender(EntityBoeing737 entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		buffer.begin(GL11.GL_QUADS, malisisVertexFormat);
		renderList(objFile.facesQuad, 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBoeing737 entity) 
  {
		return new ResourceLocation(Reference.MODID, "textures/entity/boeing_737_800.png");
	}
}
