package net.trentv.dimensions.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.client.event.EntityViewRenderEvent.FOVModifier;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.trentv.dimensions.common.CommonProxy;
import net.trentv.dimensions.common.DimensionsObjects;
import net.trentv.dimensions.common.block.BlockEnormousBook.BlockEnormousBookOpen;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}

	public static class ClientEvents
	{
		private int prevLookTime = 0;
		private int lookTime = 0;
		private int MAX_LOOK_TIME = 60;

		@SubscribeEvent
		public void onFOVModififier(FOVModifier event)
		{
			event.setFOV(event.getFOV() * (1.0F + (MAX_LOOK_TIME - getInterpolatedLookTime()) / MAX_LOOK_TIME) / 2.0f);
		}

		@SubscribeEvent
		public void onViewport(CameraSetup event)
		{
			if (event.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getEntity();
				player.eyeHeight = player.getDefaultEyeHeight() * (scale(getInterpolatedLookTime()));
			}
		}

		@SubscribeEvent
		public void onClientTick(ClientTickEvent event)
		{
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			if (player != null)
			{
				prevLookTime = lookTime;
				if (isOnBook(player.getEntityWorld(), Minecraft.getMinecraft().objectMouseOver, player))
				{
					if (lookTime < MAX_LOOK_TIME)
						lookTime++;
				}
				else
				{
					if (lookTime > 0)
						lookTime--;
				}
			}
		}

		private float getInterpolatedLookTime()
		{
			float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
			return (1.0f - partialTicks) * prevLookTime + partialTicks * lookTime;
		}

		public float scale(float time)
		{
			return (1.075F * MAX_LOOK_TIME - time) / (1.075f * MAX_LOOK_TIME);
		}

		public boolean isOnBook(World world, RayTraceResult result, Entity entity)
		{
			if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK && result.sideHit == EnumFacing.UP)
			{
				BlockPos resultPos = result.getBlockPos();
				BlockPos entityPos = new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
				if (world.getBlockState(resultPos).getBlock() instanceof BlockEnormousBookOpen)
				{
					if (world.getBlockState(entityPos).getBlock() instanceof BlockEnormousBookOpen)
					{
						return true;
					}
				}
			}
			return false;
		}

		@SubscribeEvent
		public void registerModels(ModelRegistryEvent event)
		{
			for (Item item : DimensionsObjects.items)
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
			for (ItemBlock block : DimensionsObjects.itemBlocks)
			{
				ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
			}
		}
	}
}
