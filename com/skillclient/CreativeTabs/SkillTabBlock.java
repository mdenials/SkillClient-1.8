package com.skillclient.CreativeTabs;

import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import java.util.List;

public class SkillTabBlock extends CreativeTabs
{
    public SkillTabBlock() {
        super("MyBlocksTab");
        this.setNoScrollbar();
    }
    
    @Override
    public void displayAllReleventItems(final List<ItemStack> itemList) {
        itemList.add(new ItemStack(Blocks.command_block));
        itemList.add(new ItemStack(Blocks.dragon_egg));
        itemList.add(new ItemStack(Items.command_block_minecart));
        itemList.add(new ItemStack(Blocks.barrier));
        itemList.add(new ItemStack(Items.written_book));
        itemList.add(new ItemStack((Item)Items.filled_map));
        itemList.add(new ItemStack(Blocks.farmland));
        itemList.add(new ItemStack(Blocks.red_mushroom_block));
        itemList.add(new ItemStack(Blocks.brown_mushroom_block));
        itemList.add(new ItemStack(Blocks.mob_spawner));
    }
    
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Blocks.command_block);
    }
    
    @Override
    public String getTranslatedTabLabel() {
        return "MyBlocksTab";
    }
    
    @Override
    public boolean hasSearchBar() {
        return super.hasSearchBar();
    }
}

