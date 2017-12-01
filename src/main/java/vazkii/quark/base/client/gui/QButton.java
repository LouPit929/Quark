package vazkii.quark.base.client.gui;

import java.util.List;

import com.google.common.collect.ImmutableSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.module.GlobalConfig;

public final class QButton {

	@SubscribeEvent
	public static void onGuiInit(GuiScreenEvent.InitGuiEvent event) {
		GuiScreen gui = event.getGui();
		if(GlobalConfig.enableQButton && (gui instanceof GuiMainMenu || gui instanceof GuiIngameMenu)) {
			ImmutableSet<String> targets = ImmutableSet.of(I18n.format("menu.options"), I18n.format("fml.menu.mods"));
			List<GuiButton> buttons = event.getButtonList();
			for(GuiButton b : buttons)
				if(targets.contains(b.displayString)) {
					GuiButton qButton = new GuiButtonQ(b.x - 24, b.y);
					buttons.add(qButton);
					return;
				}
		}
	}
	
	@SubscribeEvent
	public static void onButtonClick(GuiScreenEvent.ActionPerformedEvent event) {
		if(event.getButton() instanceof GuiButtonQ)
			Minecraft.getMinecraft().displayGuiScreen(new GuiConfigRoot(event.getGui()));
	}
	
}
