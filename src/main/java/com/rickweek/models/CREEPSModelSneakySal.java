package com.rickweek.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class CREEPSModelSneakySal extends ModelBase
{
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer body;
    public ModelRenderer tonyHead;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightArm;
    public ModelRenderer footR;
    public ModelRenderer footL;
    public ModelRenderer cigar;
    public ModelRenderer hatBrim;
    public ModelRenderer hat;
    public ModelRenderer watch;

    /**
     * Records whether the model should be rendered holding an item in the left hand, and if that item is a block.
     */
    public boolean heldItemLeft;

    /**
     * Records whether the model should be rendered holding an item in the right hand, and if that item is a block.
     */
    public boolean heldItemRight;
    public boolean isSneak;
    public boolean getvictim;
    public int raise;
    public boolean shooting;

    public CREEPSModelSneakySal()
    {
        this(0.0F);
    }

    public CREEPSModelSneakySal(float f)
    {
        this(f, 0.0F);
    }

    public CREEPSModelSneakySal(float f, float f1)
    {
        heldItemLeft = false;
        heldItemRight = false;
        isSneak = false;
        bipedLeftLeg = new ModelRenderer(this, 16, 22);
        bipedLeftLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        bipedLeftLeg.setRotationPoint(5F, 17F, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 16, 22);
        bipedRightLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        bipedRightLeg.setRotationPoint(-4F, 17F, 0.0F);
        body = new ModelRenderer(this, 28, 16);
        body.addBox(-6.5F, -4.5F, -3.5F, 11, 9, 7, 1.5F);
        body.setRotationPoint(1.0F, 12.6F, -0.5F);
        body.rotateAngleX = 0.118628F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        tonyHead = new ModelRenderer(this, 0, 0);
        tonyHead.addBox(-3F, -4F, -4F, 6, 4, 6, 0.0F);
        tonyHead.setRotationPoint(0.0F, 7F, 0.0F);
        tonyHead.rotateAngleX = -0.06981317F;
        tonyHead.rotateAngleY = 0.0F;
        tonyHead.rotateAngleZ = 0.0F;
        tonyHead.mirror = false;
        bipedLeftArm = new ModelRenderer(this, 0, 20);
        bipedLeftArm.addBox(0.0F, 0.0F, -2F, 4, 8, 4, 0.0F);
        bipedLeftArm.setRotationPoint(7F, 8F, 0.0F);
        bipedLeftArm.rotateAngleX = 0.0F;
        bipedLeftArm.rotateAngleY = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedLeftArm.mirror = false;
        bipedRightArm = new ModelRenderer(this, 0, 20);
        bipedRightArm.addBox(-4F, 0.0F, -2F, 4, 8, 4, 0.0F);
        bipedRightArm.setRotationPoint(-7F, 8F, 0.0F);
        bipedRightArm.rotateAngleX = 0.0F;
        bipedRightArm.rotateAngleY = 0.0F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedRightArm.mirror = false;
        footR = new ModelRenderer(this, 0, 16);
        footR.addBox(-1.5F, 6F, -4.5F, 3, 1, 3, 0.0F);
        footR.setRotationPoint(-4F, 17F, 0.0F);
        footR.rotateAngleX = 0.0F;
        footR.rotateAngleY = 0.0F;
        footR.rotateAngleZ = 0.0F;
        footR.mirror = false;
        footL = new ModelRenderer(this, 0, 16);
        footL.addBox(-1.5F, 6F, -4.5F, 3, 1, 3, 0.0F);
        footL.setRotationPoint(5F, 17F, 0.0F);
        footL.rotateAngleX = 0.0F;
        footL.rotateAngleY = 0.0F;
        footL.rotateAngleZ = 0.0F;
        footL.mirror = false;
        cigar = new ModelRenderer(this, 0, 10);
        cigar.addBox(0.0F, -2F, -7F, 1, 1, 3, -0.25F);
        cigar.setRotationPoint(0.0F, 7F, 0.0F);
        cigar.rotateAngleX = 0.04886922F;
        cigar.rotateAngleY = -0.3397574F;
        cigar.rotateAngleZ = 0.09308422F;
        cigar.mirror = false;
        hatBrim = new ModelRenderer(this, 32, 0);
        hatBrim.addBox(-4F, -4.8F, -5F, 8, 1, 8, 0.0F);
        hatBrim.setRotationPoint(0.0F, 7F, 0.0F);
        hatBrim.rotateAngleX = 0.0F;
        hatBrim.rotateAngleY = 0.0F;
        hatBrim.rotateAngleZ = 0.0F;
        hatBrim.mirror = false;
        hat = new ModelRenderer(this, 44, 9);
        hat.addBox(-2.5F, -6.8F, -3.5F, 5, 2, 5, 0.0F);
        hat.setRotationPoint(0.0F, 7F, 0.0F);
        hat.rotateAngleX = 0.0F;
        hat.rotateAngleY = 0.0F;
        hat.rotateAngleZ = 0.0F;
        hat.mirror = false;
        watch = new ModelRenderer(this, 8, 10);
        watch.addBox(-4F, 6F, -2F, 4, 1, 4, 0.25F);
        watch.setRotationPoint(-7F, 8F, 0.0F);
        watch.rotateAngleX = 0.0F;
        watch.rotateAngleY = 0.0F;
        watch.rotateAngleZ = 0.0F;
        watch.mirror = false;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        bipedLeftLeg.render(f5);
        bipedRightLeg.render(f5);
        body.render(f5);
        tonyHead.render(f5);
        bipedLeftArm.render(f5);
        bipedRightArm.render(f5);
        footR.render(f5);
        footL.render(f5);
        cigar.render(f5);
        hatBrim.render(f5);
        hat.render(f5);
        watch.render(f5);
    }

    /**
     * Sets the models various rotation angles.
     */
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        cigar.rotateAngleY = hatBrim.rotateAngleY = hat.rotateAngleY = tonyHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        watch.rotateAngleX = bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        footR.rotateAngleX = bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        footL.rotateAngleX = bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        footR.rotateAngleY = bipedRightLeg.rotateAngleY = 0.0F;
        footL.rotateAngleY = bipedLeftLeg.rotateAngleY = 0.0F;
        watch.rotateAngleZ = bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        watch.rotateAngleX = bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

        if (shooting)
        {
            watch.rotateAngleX = bipedRightArm.rotateAngleX = -1.257079F;
        }
        else
        {
            watch.rotateAngleX = bipedRightArm.rotateAngleX = 0.0F;
        }
    }
}