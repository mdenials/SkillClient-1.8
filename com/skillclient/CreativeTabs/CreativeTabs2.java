package com.skillclient.CreativeTabs;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

class CreativeTabs$1 extends CreativeTabs {
    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(Blocks.brick_block);
    }
}
