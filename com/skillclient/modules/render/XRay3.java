package com.skillclient.modules.render;

import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

class XRay$3 extends XRay.XRay_Block {
    public boolean action() {
        for (final XRay.XRay_Block xray_block : XRay.this.xray_blocks) {
            Block[] blocks;
            for (int length = (blocks = xray_block.getBlocks()).length, i = 0; i < length; ++i) {
                final Block block = blocks[i];
                XRay.map.put(block, xray_block.isActive_by_default());
            }
        }
        return false;
    }
}
