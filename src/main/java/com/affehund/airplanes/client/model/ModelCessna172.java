package com.affehund.airplanes.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Cessna172 - Affehund
 * Created using Tabula 6.0.0
 */

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
public class ModelCessna172 extends ModelBase {
    public ModelRenderer center_bottom;
    public ModelRenderer center_bottom_back_1;
    public ModelRenderer center_bottom_back_4;
    public ModelRenderer center_bottom_back_2;
    public ModelRenderer center_bottom_back_3;
    public ModelRenderer back_top_2;
    public ModelRenderer back_top_1;
    public ModelRenderer back_top_3;
    public ModelRenderer back_top_5;
    public ModelRenderer center_top_1;
    public ModelRenderer center_top_2;
    public ModelRenderer center_top_3;
    public ModelRenderer front_top_3;
    public ModelRenderer front_bottom_1;
    public ModelRenderer summit_1;
    public ModelRenderer summit_2;
    public ModelRenderer pillar_r;
    public ModelRenderer pillar_l;
    public ModelRenderer hydrofoil_small_r;
    public ModelRenderer hydrofoil_small_l;
    public ModelRenderer inner_wall_1;
    public ModelRenderer propeller_center;
    public ModelRenderer wall_right_1;
    public ModelRenderer wall_left_1;
    public ModelRenderer propeller_1;
    public ModelRenderer propeller_l;
    public ModelRenderer propeller_r;
    public ModelRenderer wheel_handle_right;
    public ModelRenderer wheel_handle_l_right;
    public ModelRenderer wheel_handle_r_right;
    public ModelRenderer wheel_right;
    public ModelRenderer wheel_handle_left;
    public ModelRenderer wheel_handle_l_left;
    public ModelRenderer wheel_handle_r_left;
    public ModelRenderer wheel_left;
    public ModelRenderer wheel_handle_center;
    public ModelRenderer wheel_handle_l_center;
    public ModelRenderer wheel_handle_r_center;
    public ModelRenderer wheel_center;
    public ModelRenderer hydrofoil_top1;
    public ModelRenderer hydrofoil_top1_1;
    public ModelRenderer hydrofoil_top_r;
    public ModelRenderer hydrofoil_top_l;

    public ModelCessna172() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.wheel_handle_right = new ModelRenderer(this, 0, 0);
        this.wheel_handle_right.setRotationPoint(6.0F, 13.0F, 6.0F);
        this.wheel_handle_right.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4, 0.0F);
        this.hydrofoil_top_l = new ModelRenderer(this, 0, 0);
        this.hydrofoil_top_l.setRotationPoint(3.5F, -16.09999999999998F, -88.0F);
        this.hydrofoil_top_l.addBox(0.0F, 0.0F, 0.0F, 25, 2, 49, 0.0F);
        this.center_bottom_back_4 = new ModelRenderer(this, 0, 0);
        this.center_bottom_back_4.setRotationPoint(-65.5F, -21.0F, -4.0F);
        this.center_bottom_back_4.addBox(0.0F, 0.0F, 0.0F, 24, 4, 8, 0.0F);
        this.setRotateAngle(center_bottom_back_4, 0.0F, 0.0F, 1.1344640137963142F);
        this.wheel_handle_center = new ModelRenderer(this, 0, 0);
        this.wheel_handle_center.setRotationPoint(38.0F, 13.0F, -2.0F);
        this.wheel_handle_center.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4, 0.0F);
        this.pillar_l = new ModelRenderer(this, 0, 0);
        this.pillar_l.setRotationPoint(9.0F, -12.0F, -36.0F);
        this.pillar_l.addBox(0.0F, 0.0F, 0.0F, 16, 1, 26, 0.0F);
        this.setRotateAngle(pillar_l, -0.5585053606381855F, 0.0F, 0.0F);
        this.front_bottom_1 = new ModelRenderer(this, 0, 0);
        this.front_bottom_1.setRotationPoint(47.8F, 9.54F, -12.0F);
        this.front_bottom_1.addBox(0.0F, 0.0F, 0.0F, 7, 4, 24, 0.0F);
        this.setRotateAngle(front_bottom_1, 0.0F, 0.0F, -0.4553564018453205F);
        this.wheel_handle_left = new ModelRenderer(this, 0, 0);
        this.wheel_handle_left.setRotationPoint(6.0F, 13.0F, -10.0F);
        this.wheel_handle_left.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4, 0.0F);
        this.wheel_handle_r_left = new ModelRenderer(this, 0, 0);
        this.wheel_handle_r_left.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.wheel_handle_r_left.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1, 0.0F);
        this.wheel_left = new ModelRenderer(this, 0, 0);
        this.wheel_left.setRotationPoint(2.0F, 8.0F, 0.0F);
        this.wheel_left.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 4, 0.0F);
        this.setRotateAngle(wheel_left, 0.0F, 0.0F, -2.1816615649929116F);
        this.wheel_handle_r_right = new ModelRenderer(this, 0, 0);
        this.wheel_handle_r_right.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.wheel_handle_r_right.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1, 0.0F);
        this.hydrofoil_top1 = new ModelRenderer(this, 0, 0);
        this.hydrofoil_top1.setRotationPoint(0.0F, -14.100000000000003F, 12.0F);
        this.hydrofoil_top1.addBox(0.0F, 0.0F, 0.0F, 29, 3, 32, 0.0F);
        this.center_top_1 = new ModelRenderer(this, 0, 0);
        this.center_top_1.setRotationPoint(-21.0F, -4.0F, -10.0F);
        this.center_top_1.addBox(0.0F, 0.0F, 0.0F, 8, 12, 20, 0.0F);
        this.back_top_3 = new ModelRenderer(this, 0, 0);
        this.back_top_3.setRotationPoint(-48.3F, -10.4F, -6.0F);
        this.back_top_3.addBox(0.0F, 0.0F, 0.0F, 19, 12, 12, 0.0F);
        this.setRotateAngle(back_top_3, 0.0F, 0.0F, 0.19198621771937624F);
        this.back_top_2 = new ModelRenderer(this, 0, 0);
        this.back_top_2.setRotationPoint(-56.7F, -20.24F, -4.0F);
        this.back_top_2.addBox(0.0F, 0.0F, 0.0F, 18, 12, 8, 0.0F);
        this.setRotateAngle(back_top_2, 0.0F, 0.0F, 0.8726646259971648F);
        this.hydrofoil_top_r = new ModelRenderer(this, 0, 0);
        this.hydrofoil_top_r.setRotationPoint(3.5F, -16.09999999999998F, 39.0F);
        this.hydrofoil_top_r.addBox(0.0F, 0.0F, 0.0F, 26, 2, 49, 0.0F);
        this.propeller_center = new ModelRenderer(this, 0, 0);
        this.propeller_center.setRotationPoint(61.0F, 2.5F, -0.5F);
        this.propeller_center.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(propeller_center, -3.141592653589793F, 0.0F, 0.0F);
        this.propeller_r = new ModelRenderer(this, 0, 0);
        this.propeller_r.setRotationPoint(1.0F, 1.0F, 1.0F);
        this.propeller_r.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.center_bottom = new ModelRenderer(this, 0, 0);
        this.center_bottom.setRotationPoint(0.0F, 8.0F, -12.0F);
        this.center_bottom.addBox(0.0F, 0.0F, 0.0F, 50, 5, 24, 0.0F);
        this.center_bottom_back_2 = new ModelRenderer(this, 0, 0);
        this.center_bottom_back_2.setRotationPoint(-36.9F, 3.3F, -8.0F);
        this.center_bottom_back_2.addBox(0.0F, 0.0F, 0.0F, 17, 2, 16, 0.0F);
        this.setRotateAngle(center_bottom_back_2, 0.0F, 0.0F, 0.19198621771937624F);
        this.hydrofoil_top1_1 = new ModelRenderer(this, 0, 0);
        this.hydrofoil_top1_1.setRotationPoint(0.0F, -14.100000000000003F, -44.0F);
        this.hydrofoil_top1_1.addBox(0.0F, 0.0F, 0.0F, 29, 3, 32, 0.0F);
        this.center_top_3 = new ModelRenderer(this, 0, 0);
        this.center_top_3.setRotationPoint(0.0F, -16.0F, -12.0F);
        this.center_top_3.addBox(0.0F, 0.0F, 0.0F, 33, 1, 24, 0.0F);
        this.hydrofoil_small_r = new ModelRenderer(this, 0, 0);
        this.hydrofoil_small_r.setRotationPoint(-55.0F, -6.0F, 4.0F);
        this.hydrofoil_small_r.addBox(0.0F, 0.0F, 0.0F, 9, 1, 24, 0.0F);
        this.setRotateAngle(hydrofoil_small_r, 0.0F, -0.2617993877991494F, 0.17453292519943295F);
        this.wall_right_1 = new ModelRenderer(this, 0, 0);
        this.wall_right_1.setRotationPoint(43.0F, -3.7F, 11.0F);
        this.wall_right_1.addBox(-56.0F, 0.0F, 0.0F, 56, 14, 1, 0.0F);
        this.propeller_1 = new ModelRenderer(this, 0, 0);
        this.propeller_1.setRotationPoint(0.0F, -1.5F, -1.5F);
        this.propeller_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.center_bottom_back_3 = new ModelRenderer(this, 0, 0);
        this.center_bottom_back_3.setRotationPoint(-57.0F, -3.6F, -4.0F);
        this.center_bottom_back_3.addBox(0.0F, 0.0F, 0.0F, 22, 5, 8, 0.0F);
        this.setRotateAngle(center_bottom_back_3, 0.0F, 0.0F, 0.19198621771937624F);
        this.back_top_5 = new ModelRenderer(this, 0, 0);
        this.back_top_5.setRotationPoint(-34.5F, -7.5F, -8.0F);
        this.back_top_5.addBox(0.0F, 0.0F, 0.0F, 17, 12, 16, 0.0F);
        this.setRotateAngle(back_top_5, 0.0F, 0.0F, 0.19198621771937624F);
        this.summit_1 = new ModelRenderer(this, 0, 0);
        this.summit_1.setRotationPoint(55.0F, -0.5F, -3.0F);
        this.summit_1.addBox(0.0F, 0.0F, 0.0F, 5, 7, 6, 0.0F);
        this.hydrofoil_small_l = new ModelRenderer(this, 0, 0);
        this.hydrofoil_small_l.setRotationPoint(-47.0F, -6.0F, -4.0F);
        this.hydrofoil_small_l.addBox(0.0F, 0.0F, 0.0F, 8, 1, 24, 0.0F);
        this.setRotateAngle(hydrofoil_small_l, 0.0F, -2.8808404633418405F, 0.17453292519943295F);
        this.wheel_handle_r_center = new ModelRenderer(this, 0, 0);
        this.wheel_handle_r_center.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.wheel_handle_r_center.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1, 0.0F);
        this.front_top_3 = new ModelRenderer(this, 0, 0);
        this.front_top_3.setRotationPoint(42.5F, -3.8F, -12.0F);
        this.front_top_3.addBox(0.0F, 0.0F, 0.0F, 15, 12, 24, 0.0F);
        this.setRotateAngle(front_top_3, 0.0F, 0.0F, 0.12217304763960307F);
        this.wheel_handle_l_left = new ModelRenderer(this, 0, 0);
        this.wheel_handle_l_left.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.wheel_handle_l_left.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
        this.wheel_center = new ModelRenderer(this, 0, 0);
        this.wheel_center.setRotationPoint(2.0F, 8.0F, 0.0F);
        this.wheel_center.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 4, 0.0F);
        this.setRotateAngle(wheel_center, 0.0F, 0.0F, -2.1816615649929116F);
        this.center_top_2 = new ModelRenderer(this, 0, 0);
        this.center_top_2.setRotationPoint(-13.0F, -4.0F, -12.0F);
        this.center_top_2.addBox(0.0F, 0.0F, 0.0F, 18, 2, 24, 0.0F);
        this.setRotateAngle(center_top_2, 0.0F, 0.0F, -0.767944870877505F);
        this.wheel_handle_l_right = new ModelRenderer(this, 0, 0);
        this.wheel_handle_l_right.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.wheel_handle_l_right.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
        this.wheel_handle_l_center = new ModelRenderer(this, 0, 0);
        this.wheel_handle_l_center.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.wheel_handle_l_center.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
        this.wheel_right = new ModelRenderer(this, 0, 0);
        this.wheel_right.setRotationPoint(2.0F, 8.0F, 0.0F);
        this.wheel_right.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 4, 0.0F);
        this.setRotateAngle(wheel_right, 0.0F, 0.0F, -2.1816615649929116F);
        this.wall_left_1 = new ModelRenderer(this, 0, 0);
        this.wall_left_1.setRotationPoint(43.0F, -3.7F, -12.0F);
        this.wall_left_1.addBox(-56.0F, 0.0F, 0.0F, 56, 14, 1, 0.0F);
        this.back_top_1 = new ModelRenderer(this, 0, 0);
        this.back_top_1.setRotationPoint(-65.3F, -21.1F, -4.0F);
        this.back_top_1.addBox(0.0F, 0.0F, 0.0F, 9, 6, 8, 0.0F);
        this.setRotateAngle(back_top_1, 0.0F, 0.0F, 0.10471975511965977F);
        this.inner_wall_1 = new ModelRenderer(this, 0, 0);
        this.inner_wall_1.setRotationPoint(-13.0F, -4.0F, -12.0F);
        this.inner_wall_1.addBox(0.0F, 0.0F, 0.0F, 1, 14, 24, 0.0F);
        this.summit_2 = new ModelRenderer(this, 0, 0);
        this.summit_2.setRotationPoint(60.0F, 1.0F, -2.0F);
        this.summit_2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.pillar_r = new ModelRenderer(this, 0, 0);
        this.pillar_r.setRotationPoint(9.0F, -12.0F, 36.0F);
        this.pillar_r.addBox(0.0F, 0.0F, 0.0F, 16, 1, 26, 0.0F);
        this.setRotateAngle(pillar_r, -2.5830872929516078F, 0.0F, 0.0F);
        this.center_bottom_back_1 = new ModelRenderer(this, 0, 0);
        this.center_bottom_back_1.setRotationPoint(-20.0F, 6.2F, -11.0F);
        this.center_bottom_back_1.addBox(-1.0F, 0.0F, 0.0F, 22, 3, 22, 0.0F);
        this.setRotateAngle(center_bottom_back_1, 0.0F, 0.0F, 0.19198621771937624F);
        this.propeller_l = new ModelRenderer(this, 0, 0);
        this.propeller_l.setRotationPoint(1.0F, 1.0F, 1.0F);
        this.propeller_l.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(propeller_l, -3.141592653589793F, 0.0F, 0.0F);
        this.wheel_handle_left.addChild(this.wheel_handle_r_left);
        this.wheel_handle_left.addChild(this.wheel_left);
        this.wheel_handle_right.addChild(this.wheel_handle_r_right);
        this.propeller_1.addChild(this.propeller_r);
        this.propeller_center.addChild(this.propeller_1);
        this.wheel_handle_center.addChild(this.wheel_handle_r_center);
        this.wheel_handle_left.addChild(this.wheel_handle_l_left);
        this.wheel_handle_center.addChild(this.wheel_center);
        this.wheel_handle_right.addChild(this.wheel_handle_l_right);
        this.wheel_handle_center.addChild(this.wheel_handle_l_center);
        this.wheel_handle_right.addChild(this.wheel_right);
        this.propeller_1.addChild(this.propeller_l);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.wheel_handle_right.render(f5);
        this.hydrofoil_top_l.render(f5);
        this.center_bottom_back_4.render(f5);
        this.wheel_handle_center.render(f5);
        this.pillar_l.render(f5);
        this.front_bottom_1.render(f5);
        this.wheel_handle_left.render(f5);
        this.hydrofoil_top1.render(f5);
        this.center_top_1.render(f5);
        this.back_top_3.render(f5);
        this.back_top_2.render(f5);
        this.hydrofoil_top_r.render(f5);
        this.propeller_center.render(f5);
        this.center_bottom.render(f5);
        this.center_bottom_back_2.render(f5);
        this.hydrofoil_top1_1.render(f5);
        this.center_top_3.render(f5);
        this.hydrofoil_small_r.render(f5);
        this.wall_right_1.render(f5);
        this.center_bottom_back_3.render(f5);
        this.back_top_5.render(f5);
        this.summit_1.render(f5);
        this.hydrofoil_small_l.render(f5);
        this.front_top_3.render(f5);
        this.center_top_2.render(f5);
        this.wall_left_1.render(f5);
        this.back_top_1.render(f5);
        this.inner_wall_1.render(f5);
        this.summit_2.render(f5);
        this.pillar_r.render(f5);
        this.center_bottom_back_1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	
    }
}
