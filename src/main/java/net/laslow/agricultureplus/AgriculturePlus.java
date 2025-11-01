package net.laslow.agricultureplus;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgriculturePlus implements ModInitializer {
	public static final String MOD_ID = "agriculture-plus";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        PlayerBlockBreakEvents.AFTER.register((world, playerEntity, blockPos, blockState, blockEntity) -> {
            if (world.isClient()) return;
            if (playerEntity.isCreative() || playerEntity.isSpectator()) return;
            Block block = blockState.getBlock();
            if (!(block instanceof net.minecraft.block.CropBlock)) return;
            int age = blockState.get(CropBlock.AGE);
            if (age < CropBlock.MAX_AGE) return;
            int xp = 1;
            net.minecraft.entity.ExperienceOrbEntity.spawn(
                    (net.minecraft.server.world.ServerWorld) world,
                    net.minecraft.util.math.Vec3d.ofCenter(blockPos),
                    xp
            );
        });
	}
}