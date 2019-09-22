//package com.affehund.airplanes.client.render;
//
//import com.affehund.airplanes.common.entities.EntityCessna172;
//
//import net.minecraft.client.model.ModelPlayer;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.entity.player.EntityPlayer;
//
//public class RenderCessna172 extends AbstractEntityRenderer<EntityCessna172>
//{
//	@Override
//    public void render(EntityCessna172 entity, float partialTicks)
//    {
//        GlStateManager.popMatrix();
//    }
//
//    
//    @Override
//    public void applyPlayerModel(EntityCessna172 entity, EntityPlayer player, ModelPlayer model, float partialTicks)
//    {
//        model.bipedRightLeg.rotateAngleX = (float) Math.toRadians(-85F);
//        model.bipedRightLeg.rotateAngleY = (float) Math.toRadians(10F);
//        model.bipedLeftLeg.rotateAngleX = (float) Math.toRadians(-85F);
//        model.bipedLeftLeg.rotateAngleY = (float) Math.toRadians(-10F);
//    }
//
//    @Override
//    public void applyPlayerRender(EntityCessna172 entity, EntityPlayer player, float partialTicks)
//    {
//        double offsetY = 24 * 0.0625 + entity.getMountedYOffset() + player.getYOffset() - 0.5; //TODO make this last variable a variable in entity plane
//        GlStateManager.translate(0, offsetY, 0);
//        float bodyPitch = entity.prevBodyRotationX + (entity.bodyRotationX - entity.prevBodyRotationX) * partialTicks;
//        float bodyRoll = entity.prevBodyRotationZ + (entity.bodyRotationZ - entity.prevBodyRotationZ) * partialTicks;
//        GlStateManager.rotate(-bodyPitch, 1, 0, 0);
//        GlStateManager.rotate(bodyRoll, 0, 0, 1);
//        GlStateManager.translate(0, -offsetY, 0);
//    }
//}
