package com.affehund.airplanes.core.init;

import java.util.Random;

import com.affehund.airplanes.core.config.ConfigAirplanes;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class OreGenInit implements IWorldGenerator
{

	private final WorldGenMinable bauxiteGen = new WorldGenMinable(BlockInit.BAUXITE_ORE.getDefaultState(), 5,
			(blockstate) -> blockstate.getBlock() == Blocks.STONE);
	private final WorldGenMinable copperGen = new WorldGenMinable(BlockInit.COPPER_ORE.getDefaultState(), 5,
			(blockstate) -> blockstate.getBlock() == Blocks.STONE);
	private final WorldGenMinable tinGen = new WorldGenMinable(BlockInit.TIN_ORE.getDefaultState(), 5,
			(blockstate) -> blockstate.getBlock() == Blocks.STONE);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider)
	{
		BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
		if (chunkGenerator instanceof ChunkGeneratorOverworld)
		{
			if (ConfigAirplanes.Global.OreGen.generateAllOres)
			{
				if (ConfigAirplanes.Global.OreGen.generateBauxiteOre)
					genOreMinMax(world, random, chunkPos, 5, bauxiteGen, 4, 64);
				if (ConfigAirplanes.Global.OreGen.generateCopperOre)
					genOreMinMax(world, random, chunkPos, 8, copperGen, 16, 128);
				if (ConfigAirplanes.Global.OreGen.generateTinOre)
					genOreMinMax(world, random, chunkPos, 2, tinGen, 4, 32);
			}
		}
	}

	protected void genOreMinMax(World worldIn, Random random, BlockPos chunkPos, int blockCount,
			WorldGenerator generator, int minHeight, int maxHeight)
	{
		if (maxHeight < minHeight)
		{
			int i = minHeight;
			minHeight = maxHeight;
			maxHeight = i;
		} else if (maxHeight == minHeight)
		{
			if (minHeight < 255)
			{
				++maxHeight;
			} else
			{
				--minHeight;
			}
		}

		for (int j = 0; j < blockCount; ++j)
		{
			BlockPos blockpos = chunkPos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight,
					random.nextInt(16));
			generator.generate(worldIn, random, blockpos);
		}
	}

	protected void genOreCenterSpread(World worldIn, Random random, BlockPos chunkPos, int blockCount,
			WorldGenerator generator, int centerHeight, int spread)
	{
		for (int i = 0; i < blockCount; ++i)
		{
			BlockPos blockpos = chunkPos.add(random.nextInt(16),
					random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
			generator.generate(worldIn, random, blockpos);
		}
	}
}