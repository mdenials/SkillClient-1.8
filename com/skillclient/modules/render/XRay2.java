package com.skillclient.modules.render;

import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

class XRay$2 extends XRay.XRay_Block {
    public boolean action() {
        for (final XRay.XRay_Block block1 : XRay.this.xray_blocks) {
            Block[] blocks;
            for (int length = (blocks = block1.getBlocks()).length, i = 0; i < length; ++i) {
                final Block block2 = blocks[i];
                XRay.map.put(block2, true);
            }
        }
        return false;
    }
}
