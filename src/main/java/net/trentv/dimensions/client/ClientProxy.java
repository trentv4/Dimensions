package net.trentv.dimensions.client;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
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
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.trentv.dimensions.common.CommonProxy;
import net.trentv.dimensions.common.DimensionsObjects;
import net.trentv.dimensions.common.block.BlockEnormousBook.BlockEnormousBookOpen;
import net.trentv.dimensions.common.libraria.DimensionLibraria;
import net.trentv.dimensions.common.libraria.LibrariaObjects;
import net.trentv.dimensions.common.libraria.block.BlockLibrariaBookshelf;

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

		private char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ',', ' ', '.' };

		@SubscribeEvent
		public void onRightClick(RightClickBlock event)
		{
			if (event.getEntityPlayer().dimension != DimensionLibraria.dimensionID)
				return;
			BlockPos pos = event.getPos();
			Block block = event.getWorld().getBlockState(pos).getBlock();
			// This needs work.
			if (block instanceof BlockLibrariaBookshelf | block == LibrariaObjects.SMOLDERING_BOOKSHELF | block == LibrariaObjects.SOAKED_BOOKSHELF)
			{
				if (!event.getEntityPlayer().isSneaking())
				{
					Random random = new Random();

					random.setSeed(pos.hashCode());
					String s = "";
					if (random.nextInt(100) == 0)
					{
						s = "WHAT WAS WILL BE WHAT WILL BE WAS GRAVITY IS DESIRE TIME IS SIGHT THE TRINE THE QUINE "
								+ "THE TRINE THE LOOP EVERYTHING AND NOTHING THE END IN THE BEGINNING WHAT WAS WILL "
								+ "BE WHAT WILL BE WAS GRAVITY IS DESIRE TIME IS SIGHT THE TRINE THE QUINE THE TRINE "
								+ "THE LOOP EVERYTHING AND NOTHING THE WORM LOVES US";
					}
					else
					{
						for (int i = 0; i < 300; i++)
						{
							s += alphabet[random.nextInt(alphabet.length)];
						}
					}
					if (event.getWorld().isRemote)
					{
						Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBookOutput(s));
					}
					event.setCanceled(true);
				}
			}
		}

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

			ModelLoader.setCustomStateMapper(LibrariaObjects.MARMOR_DOOR, (new StateMap.Builder()).ignore(BlockDoor.POWERED).build());
		}
	}
}
