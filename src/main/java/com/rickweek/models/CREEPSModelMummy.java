package com.rickweek.models;

import com.rickweek.entities.CREEPSEntityMummy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class CREEPSModelMummy extends ModelBase
{
	  //fields
	    ModelRenderer head;
	    ModelRenderer body;
	    ModelRenderer mainhand;
	    ModelRenderer leftarm;
	    ModelRenderer rightleg;
	    ModelRenderer leftleg;
	  
	  public CREEPSModelMummy(float f)
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -8F, -4F, 8, 8, 8);
	      head.setRotationPoint(0F, 0F, 0F);
	      head.setTextureSize(64, 32);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 16, 16);
	      body.addBox(-4F, 0F, -2F, 8, 12, 4);
	      body.setRotationPoint(0F, 0F, 0F);
	      body.setTextureSize(64, 32);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, 0F);
	      mainhand = new ModelRenderer(this, 40, 16);
	      mainhand.addBox(-3F, -2F, -2F, 4, 12, 4);
	      mainhand.setRotationPoint(-5F, 2F, 0F);
	      mainhand.setTextureSize(64, 32);
	      mainhand.mirror = true;
	      setRotation(mainhand, 0F, 0F, 0F);
	      leftarm = new ModelRenderer(this, 40, 16);
	      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
	      leftarm.setRotationPoint(5F, 2F, 0F);
	      leftarm.setTextureSize(64, 32);
	      leftarm.mirror = true;
	      setRotation(leftarm, 0F, 0F, 0F);
	      rightleg = new ModelRenderer(this, 0, 16);
	      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	      rightleg.setRotationPoint(-2F, 12F, 0F);
	      rightleg.setTextureSize(64, 32);
	      rightleg.mirror = true;
	      setRotation(rightleg, 0F, 0F, 0F);
	      leftleg = new ModelRenderer(this, 0, 16);
	      leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	      leftleg.setRotationPoint(2F, 12F, 0F);
	      leftleg.setTextureSize(64, 32);
	      leftleg.mirror = true;
	      setRotation(leftleg, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    body.render(f5);
	    mainhand.render(f5);
	    leftarm.render(f5);
	    rightleg.render(f5);
	    leftleg.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	  {
	    super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, null);
	    /*
	    head.rotateAngleY = (netHeadYaw / 57.295776F);
	    head.rotateAngleX = (headPitch / 57.295776F);
	    leftleg.rotateAngleX = (MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount);
	    rightleg.rotateAngleX = (MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount);
	    mainhand.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
	    leftarm.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
	    */
	    
        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        head.rotateAngleY = (netHeadYaw / 57.295776F);
	    head.rotateAngleX = (headPitch / 57.295776F);
	    leftleg.rotateAngleX = (MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount);
	    rightleg.rotateAngleX = (MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount);
	    this.mainhand.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.mainhand.rotateAngleY = -(0.1F - f * 0.6F);
        this.leftarm.rotateAngleY = 0.1F - f * 0.6F;
        float f2 = -(float)Math.PI / 2.5F;
        this.mainhand.rotateAngleX = f2;
        this.leftarm.rotateAngleX = f2;
        this.mainhand.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.leftarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.mainhand.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.mainhand.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	  }

	}
