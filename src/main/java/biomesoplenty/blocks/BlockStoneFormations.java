package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStoneFormations extends BlockFlower
{
	private static final String[] forms = new String[] {"stalagmite", "stalactite"};
	private Icon[] textures;

	protected BlockStoneFormations(int blockID, Material material)
	{
		super(blockID, material);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	public BlockStoneFormations(int blockID)
	{
		this(blockID, Material.vine);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[forms.length];

		for (int i = 0; i < forms.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + forms[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
		default:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			break;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < forms.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return Block.blocksList[id] instanceof BlockStone;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
			return Block.blocksList[id] instanceof BlockStone;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int idbottom = world.getBlockId(x, y - 1, z);
		int idtop = world.getBlockId(x, y + 1, z);
		int meta = itemStack.getItemDamage();
		//boolean sky = world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z);

		if (itemStack.itemID == blockID) {
			switch (meta)
			{
			case 0: // Stalagmite
				return Block.blocksList[idbottom] instanceof BlockStone;
				
			case 1: // Stalactite
			    return Block.blocksList[idtop] instanceof BlockStone;

			default:
			    return Block.blocksList[idbottom] instanceof BlockStone;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		//super.onNeighborBlockChange(world, x, y, z, neighborID);
		this.checkFlowerChange(world, x, y, z);
	}
	
	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return meta;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & 15;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (world.getBlockId(x, y, z) != blockID)
		{
			if (meta == 1)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y + 1, z));
			else
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		}
		else
		{
			if (meta == 1)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y + 1, z), world.getBlockMetadata(x, y, z));
			else
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
		}
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}
}
