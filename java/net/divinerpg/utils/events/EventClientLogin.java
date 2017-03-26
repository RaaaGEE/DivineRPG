package net.divinerpg.utils.events;

import java.io.IOException;

import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventClientLogin {

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent evt) {
		EntityPlayer p = evt.player;
		if (!p.worldObj.isRemote) {
			if (!ConfigurationHelper.canShowOverlay) {
				p.addChatMessage(Util.addChatMessage(MessageLocalizer.standard(p.getDisplayName())));
				p.addChatMessage(Util.addChatMessage(MessageLocalizer.normal("message.version.internet", Util.LIGHT_PURPLE)));
			} else {
				p.addChatMessage(Util.addChatMessage(MessageLocalizer.standard(p.getDisplayName())));
			}
		}
	}
}
