package net.trentv.dimensions.client;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.trentv.dimensions.Dimensions;

public class GuiScreenBookOutput extends GuiScreen
{
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(Dimensions.MODID, "textures/gui/book.png");
	private static int WIDTH = 146;
	private static int HEIGHT = 180;
	private String output;
	private GuiButton doneBtn;

	public GuiScreenBookOutput(String displayedText)
	{
		this.output = displayedText;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.enabled)
		{
			this.mc.displayGuiScreen((GuiScreen) null);
		}
	}

	@Override
	public void initGui()
	{
		this.doneBtn = this.addButton(new GuiButton(0, this.width / 2 - 100, this.height - this.height / 6, I18n.format("gui.done")));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
		this.drawTexturedModalRect((this.width - WIDTH) / 2, this.height / 20, 0, 0, WIDTH, HEIGHT);
		this.fontRenderer.drawSplitString(output, (this.width - WIDTH) / 2 + 20, this.height / 20 + 10, 110, 0);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
