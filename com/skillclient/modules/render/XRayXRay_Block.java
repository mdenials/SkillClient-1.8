package com.skillclient.modules.render;

import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class XRay_Block
{
    private ItemStack itemstack;
    private Block[] blocks;
    private String name;
    private boolean active_by_default;
    
    public XRay_Block disable() {
        this.active_by_default = false;
        return this;
    }
    
    public XRay_Block(final XRay xRay, final Block block) {
        this(xRay, new ItemStack(block), new Block[] { block });
    }
    
    public XRay_Block(final ItemStack itemstack, final Block... block) {
        this.active_by_default = true;
        this.itemstack = itemstack;
        this.blocks = block;
        this.name = block[0].getLocalizedName();
    }
    
    public XRay_Block(final ItemStack itemstack, final Block[] block, final String name) {
        this.active_by_default = true;
        this.itemstack = itemstack;
        this.blocks = block;
        this.name = name;
    }
    
    public boolean action() {
        return true;
    }
    
    public boolean toggled() {
        return false;
    }
    
    public ItemStack getItemstack() {
        return this.itemstack;
    }
    
    public Block[] getBlocks() {
        return this.blocks;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isActive_by_default() {
        return this.active_by_default;
    }
    
    public void setItemstack(final ItemStack itemstack) {
        this.itemstack = itemstack;
    }
    
    public void setBlocks(final Block[] blocks) {
        this.blocks = blocks;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setActive_by_default(final boolean active_by_default) {
        this.active_by_default = active_by_default;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XRay_Block)) {
            return false;
        }
        final XRay_Block other = (XRay_Block)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$itemstack = this.getItemstack();
        final Object other$itemstack = other.getItemstack();
        Label_0065: {
            if (this$itemstack == null) {
                if (other$itemstack == null) {
                    break Label_0065;
                }
            }
            else if (this$itemstack.equals(other$itemstack)) {
                break Label_0065;
            }
            return false;
        }
        if (!Arrays.deepEquals(this.getBlocks(), other.getBlocks())) {
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null) {
            if (other$name == null) {
                return this.isActive_by_default() == other.isActive_by_default();
            }
        }
        else if (this$name.equals(other$name)) {
            return this.isActive_by_default() == other.isActive_by_default();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof XRay_Block;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $itemstack = this.getItemstack();
        result = result * 59 + (($itemstack == null) ? 43 : $itemstack.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getBlocks());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        result = result * 59 + (this.isActive_by_default() ? 79 : 97);
        return result;
    }
    
    @Override
    public String toString() {
        return "XRay.XRay_Block(itemstack=" + this.getItemstack() + ", blocks=" + Arrays.deepToString(this.getBlocks()) + ", name=" + this.getName() + ", active_by_default=" + this.isActive_by_default() + ")";
    }
}
