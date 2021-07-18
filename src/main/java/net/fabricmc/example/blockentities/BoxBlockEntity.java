package net.fabricmc.example.blockentities;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.guis.BoxScreenHandler;
import net.fabricmc.example.interfaces.ImplemetedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class BoxBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplemetedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public BoxBlockEntity(BlockPos pos, BlockState state) {
        super(ExampleMod.BOX_BLOCK_ENTITY, pos, state);
    }

   @Override
   public DefaultedList<ItemStack> getItems() {
       return inventory;
   }

   @Override
   public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
       return new BoxScreenHandler(syncId, playerInventory, this);
   }

   @Override
   public Text getDisplayName() {
       return new TranslatableText(getCachedState().getBlock().getTranslationKey());
   }

   @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        return nbt;
    }
}
