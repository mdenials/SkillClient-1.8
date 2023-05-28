package com.skillclient.CreativeTabs;

import com.skillclient.utils.ItemStackUtil;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import java.util.List;

public class SkillTabArmor extends CreativeTabs
{
    public SkillTabArmor() {
        super("ArmorTab");
        this.setNoScrollbar();
    }
    
    @Override
    public void displayAllReleventItems(final List<ItemStack> itemList) {
        final String[] array = { "{Unbreakable:1,ench:[{id:0,lvl:1000}]}", "{AttributeModifiers:[{AttributeName:\"generic.knockbackResistance\",Name:\"generic.knockbackResistance\",Amount:1,Operation:0,UUIDLeast:722576,UUIDMost:658559,Slot:\"head\"}],Unbreakable:1,ench:[{id:0,lvl:1000}]}", "{Unbreakable:1,ench:[{id:0,lvl:1000},{id:7,lvl:1000}]}", "{AttributeModifiers:[{AttributeName:\"generic.knockbackResistance\",Name:\"generic.knockbackResistance\",Amount:1,Operation:0,UUIDLeast:859071,UUIDMost:670308}],Unbreakable:1,ench:[{id:0,lvl:1000},{id:7,lvl:1000}]}" };
        String[] array3;
        for (int length = (array3 = array).length, i = 0; i < length; ++i) {
            final String NBT = array3[i];
            final ItemStack diamond_helmet = new ItemStack((Item)Items.diamond_helmet);
            final ItemStack diamond_chestplate = new ItemStack((Item)Items.diamond_chestplate);
            final ItemStack diamond_leggings = new ItemStack((Item)Items.diamond_leggings);
            final ItemStack diamond_boots = new ItemStack((Item)Items.diamond_boots);
            final ItemStack[] array2 = { diamond_helmet, diamond_chestplate, diamond_leggings, diamond_boots };
            ItemStack[] array4;
            for (int length2 = (array4 = array2).length, j = 0; j < length2; ++j) {
                final ItemStack stack = array4[j];
                try {
                    stack.setTagCompound(JsonToNBT.getTagFromJson(NBT));
                }
                catch (NBTException ex) {}
                itemList.add(stack);
            }
            ItemStackUtil.fillEmpty((List)itemList);
        }
    }
    
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack((Item)Items.diamond_chestplate);
    }
    
    @Override
    public String getTranslatedTabLabel() {
        return "Armor";
    }
}
