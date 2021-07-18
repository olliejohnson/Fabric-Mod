package net.fabricmc.example.materials;

import net.fabricmc.example.ExampleMod;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class BedrockToolMaterial implements ToolMaterial {

    public static final BedrockToolMaterial INSTANCE = new BedrockToolMaterial();
    
    @Override
    public int getDurability() {
        return -1;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 50.0F;
    }

    @Override
    public float getAttackDamage() {
        return 500.0F;
    }

    @Override
    public int getMiningLevel() {
        return 2000;
    }

    @Override
    public int getEnchantability() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ExampleMod.BEDROCK_INGOT);
    }
}
