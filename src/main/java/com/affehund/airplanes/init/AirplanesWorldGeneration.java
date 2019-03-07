package com.affehund.airplanes.init;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.common.IWorldGenerator;

public class AirplanesWorldGeneration implements IWorldGenerator {
	
	private final WorldGenMinable bauxitegen = new WorldGenMinable(AirplanesBlocks.bauxite_ore.getDefaultState(), 5, (blockstate) -> blockstate.getBlock() == Blocks.STONE);
	private final WorldGenMinable coppergen = new WorldGenMinable(AirplanesBlocks.copper_ore.getDefaultState(), 5, (blockstate) -> blockstate.getBlock() == Blocks.STONE);
	private final WorldGenMinable tingen = new WorldGenMinable(AirplanesBlocks.tin_ore.getDefaultState(), 5, (blockstate) -> blockstate.getBlock() == Blocks.STONE);
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
		if (chunkGenerator instanceof ChunkGeneratorOverworld) {
			genOreMinMax(world, random, chunkPos, 5, bauxitegen, 30, 100);
			genOreMinMax(world, random, chunkPos, 10, coppergen, 40, 120);
			genOreMinMax(world, random, chunkPos, 2, tingen, 3, 20);
			
		}
	}
	
	
	protected void genOreMinMax(World worldIn, Random random, BlockPos chunkPos, int blockCount, WorldGenerator generator, int minHeight, int maxHeight) {
		if (maxHeight < minHeight) {
			int i = minHeight;
			minHeight = maxHeight;
			maxHeight = i;
		} else if (maxHeight == minHeight) {
			if (minHeight < 255) {
				++maxHeight;
			} else {
				--minHeight;
			}
		}

		for (int j = 0; j < blockCount; ++j) {
			BlockPos blockpos = chunkPos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
			generator.generate(worldIn, random, blockpos);
		}
	}
	protected void genOreCenterSpread(World worldIn, Random random, BlockPos chunkPos, int blockCount, WorldGenerator generator, int centerHeight, int spread) {
		for (int i = 0; i < blockCount; ++i) {
			BlockPos blockpos = chunkPos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
			generator.generate(worldIn, random, blockpos);
		}
	}
}