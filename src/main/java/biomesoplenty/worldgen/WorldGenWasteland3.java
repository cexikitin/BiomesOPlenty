package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenWasteland3 extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);
		int var95 = var1.getBlockId(var3 - 1, var4, var5);
		int var96 = var1.getBlockId(var3 + 1, var4, var5);
		int var97 = var1.getBlockId(var3, var4, var5 - 1);
		int var98 = var1.getBlockId(var3, var4, var5 + 1);

		if (var6 != Blocks.driedDirt.get().blockID || var95 != Blocks.driedDirt.get().blockID || var96 != Blocks.driedDirt.get().blockID || var97 != Blocks.driedDirt.get().blockID || var98 != Blocks.driedDirt.get().blockID )
			return false;
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8) && var1.isAirBlock(var3 + var7, var4 - 2, var5 + var8))
						return false;
				}
			}

			int var999 = var2.nextInt(2);

			if (var999 == 0)
			{
				var1.setBlock(var3, var4, var5, Blocks.driedDirt.get().blockID);
				var1.setBlock(var3 - 1, var4, var5, Blocks.driedDirt.get().blockID);
				var1.setBlock(var3 + 1, var4, var5, Blocks.driedDirt.get().blockID);
				var1.setBlock(var3, var4, var5 - 1, Blocks.driedDirt.get().blockID);
				var1.setBlock(var3, var4, var5 + 1, Blocks.driedDirt.get().blockID);
				this.setBlockAndMetadata(var1, var3, var4 + 1, var5, Blocks.driedDirt.get().blockID, 0);
				this.setBlockAndMetadata(var1, var3 + 1, var4 + 1, var5, Blocks.driedDirt.get().blockID, 0);
				this.setBlockAndMetadata(var1, var3 - 1, var4 + 1, var5, Blocks.driedDirt.get().blockID, 0);
				this.setBlockAndMetadata(var1, var3, var4 + 1, var5 + 1, Blocks.driedDirt.get().blockID, 0);
				this.setBlockAndMetadata(var1, var3, var4 + 1, var5 - 1, Blocks.driedDirt.get().blockID, 0);
				this.setBlockAndMetadata(var1, var3, var4 + 2, var5, Blocks.driedDirt.get().blockID, 0);
				return true;
			}
			if (var999 == 1)
			{
				var1.setBlock(var3, var4, var5, Blocks.driedDirt.get().blockID);
				this.setBlockAndMetadata(var1, var3, var4 + 1, var5, Blocks.driedDirt.get().blockID, 0);
				return true;
			}

			return true;
		}
	}
}
