package com.skillclient.CreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

class CreativeTabs$12 extends CreativeTabs {
    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock((Block)Blocks.chest);
    }
}
