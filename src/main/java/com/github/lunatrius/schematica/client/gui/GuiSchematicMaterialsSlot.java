package com.github.lunatrius.schematica.client.gui;

import com.github.lunatrius.schematica.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;

class GuiSchematicMaterialsSlot extends GuiSlot {
	private final FontRenderer fontRenderer = Settings.instance.minecraft.fontRenderer;
	private final TextureManager renderEngine = Settings.instance.minecraft.renderEngine;

	private final GuiSchematicMaterials guiSchematicMaterials;

	protected int selectedIndex = -1;

	public GuiSchematicMaterialsSlot(GuiSchematicMaterials par1) {
		super(Minecraft.getMinecraft(), par1.width, par1.height, 16, par1.height - 34, 24);
		this.guiSchematicMaterials = par1;
		this.selectedIndex = -1;
	}

	@Override
	protected int getSize() {
		return this.guiSchematicMaterials.blockList.size();
	}

	@Override
	protected void elementClicked(int index, boolean par2, int par3, int par4) {
		this.selectedIndex = index;
	}

	@Override
	protected boolean isSelected(int index) {
		return index == this.selectedIndex;
	}

	@Override
	protected void drawBackground() {
	}

	@Override
	protected void drawContainerBackground(Tessellator tessellator) {
	}

	@Override
	protected void drawSlot(int index, int x, int y, int par4, Tessellator tessellator, int par6, int par7) {
		ItemStack itemStack = this.guiSchematicMaterials.blockList.get(index);

		String itemName;
		String amount = Integer.toString(itemStack.stackSize);

		if (itemStack != null && itemStack.getItem() != null) {
			itemName = itemStack.getItem().getItemStackDisplayName(itemStack);
		} else {
			itemName = "Unknown";
		}

		GuiHelper.drawItemStack(this.renderEngine, this.fontRenderer, x, y, itemStack);

		this.guiSchematicMaterials.drawString(this.fontRenderer, itemName, x + 24, y + 6, 16777215);
		this.guiSchematicMaterials.drawString(this.fontRenderer, amount, x + 215 - this.fontRenderer.getStringWidth(amount), y + 6, 16777215);
	}
}
