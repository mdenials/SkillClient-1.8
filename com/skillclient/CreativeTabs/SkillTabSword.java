package com.skillclient.CreativeTabs;

import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.init.Items;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class SkillTabSword extends CreativeTabs
{
    ItemStack empty;
    
    public SkillTabSword() {
        super("SwordTabs");
        this.empty = new ItemStack(Blocks.air);
        this.setNoScrollbar();
    }
    
    @Override
    public void displayAllReleventItems(final List<ItemStack> itemList) {
        final String[] array = { "{Unbreakable:1,ench:[{id:16,lvl:1000}]}", "{Unbreakable:1,ench:[{id:21,lvl:1000}]}", "{Unbreakable:1,ench:[{id:16,lvl:1000},{id:21,lvl:1000}]}", "{Unbreakable:1,ench:[{id:19,lvl:5}]}", "{Unbreakable:1,ench:[{id:19,lvl:1000}]}" };
        String[] array3;
        for (int length = (array3 = array).length, i = 0; i < length; ++i) {
            final String NBT = array3[i];
            final ItemStack diamond_sword = new ItemStack(Items.diamond_sword);
            final ItemStack golden_sword = new ItemStack(Items.golden_sword);
            final ItemStack iron_sword = new ItemStack(Items.iron_sword);
            final ItemStack stone_sword = new ItemStack(Items.stone_sword);
            final ItemStack wooden_sword = new ItemStack(Items.wooden_sword);
            final ItemStack stick = new ItemStack(Items.stick);
            final ItemStack[] array2 = { diamond_sword, golden_sword, iron_sword, stone_sword, wooden_sword, stick };
            ItemStack[] array4;
            for (int length2 = (array4 = array2).length, j = 0; j < length2; ++j) {
                final ItemStack stack = array4[j];
                try {
                    stack.setTagCompound(JsonToNBT.getTagFromJson(NBT));
                }
                catch (NBTException ex) {}
                itemList.add(stack);
            }
            itemList.add(this.empty);
            itemList.add(this.empty);
            itemList.add(this.empty);
        }
    }
    
    @Override
    public String getTranslatedTabLabel() {
        return "Swords";
    }
    
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Items.diamond_sword);
    }
}

