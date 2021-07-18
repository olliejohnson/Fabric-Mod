package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.blockentities.BoxBlockEntity;
import net.fabricmc.example.blocks.CustomCropBlock;
import net.fabricmc.example.blocks.BoxBlock;
import net.fabricmc.example.blocks.ExampleBlock;
import net.fabricmc.example.enchantments.FrostEnchantment;
import net.fabricmc.example.guis.BoxScreenHandler;
import net.fabricmc.example.items.BedrockIngot;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
	public static final ItemGroup Modtab = FabricItemGroupBuilder.create(new Identifier("tutorial", "modtab")).icon(() -> new ItemStack(Blocks.COBBLESTONE)).build();

	public static final Block BOX_BLOCK;
	public static final BlockItem BOX_BLOCK_ITEM;
	public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY;

	public static final Identifier BOX = new Identifier("tutorial","box_block");
	public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;

	static {
		BOX_BLOCK = Registry.register(Registry.BLOCK, BOX, new BoxBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf(Blocks.CHEST)));
		BOX_BLOCK_ITEM = Registry.register(Registry.ITEM, BOX, new BlockItem(BOX_BLOCK, new Item.Settings().group(Modtab)));

		BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX, BlockEntityType.Builder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));
		BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOX, BoxScreenHandler::new);
	}

	public static final Item BEDROCK_INGOT = new BedrockIngot(new FabricItemSettings().group(Modtab));
	private static Enchantment FROST = Registry.register(Registry.ENCHANTMENT, new Identifier("tutorial", "frost"), new FrostEnchantment());

	public static final Block EXAMPLE_BLOCK = new ExampleBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.of(Material.METAL).strength(4.0f));

	public static final Block DEMO_BLOCK = new BoxBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.of(Material.STONE).strength(4.0f, 2.0f));

	public static final CropBlock CUSTOM_CROP_BLOCK = new CustomCropBlock(AbstractBlock.Settings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

	public static final Item CUSTOM_SEEDS = new AliasedBlockItem(ExampleMod.CUSTOM_CROP_BLOCK, new Item.Settings().group(Modtab));

	
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("tutorial", "bedrock_ingot"), BEDROCK_INGOT);
		Registry.register(Registry.BLOCK, new Identifier("tutorial", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("tutorial", "example_block"), new BlockItem(EXAMPLE_BLOCK, new FabricItemSettings().group(Modtab)));
		Registry.register(Registry.BLOCK, new Identifier("tutorial", "demo_block"), DEMO_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("tutorial", "demo_block"), new BlockItem(DEMO_BLOCK, new FabricItemSettings().group(Modtab)));

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), CUSTOM_CROP_BLOCK);

		Registry.register(Registry.BLOCK, new Identifier("tutorial","custom_crop_block"), CUSTOM_CROP_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("tutorial","custom_seeds"), CUSTOM_SEEDS);
	}
}
