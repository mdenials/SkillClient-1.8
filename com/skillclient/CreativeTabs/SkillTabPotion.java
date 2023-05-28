package com.skillclient.CreativeTabs;

import net.minecraft.item.Item;
import com.skillclient.utils.ItemStackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import java.util.List;

public class SkillTabPotion extends CreativeTabs
{
    public SkillTabPotion() {
        super("PotionsTab");
        this.setBackgroundImageName("item_search.png");
    }
    
    @Override
    public void displayAllReleventItems(final List<ItemStack> itemList) {
        final String[] array = { "{AttributeModifiers:[{AttributeName:\"generic.maxHealth\",Name:\"generic.maxHealth\",Amount:20,Operation:0,UUIDLeast:967858,UUIDMost:899140}]}", "{AttributeModifiers:[{AttributeName:\"generic.maxHealth\",Name:\"generic.maxHealth\",Amount:-20,Operation:0,UUIDLeast:967858,UUIDMost:899140}]}", "{AttributeModifiers:[{AttributeName:\"generic.movementSpeed\",Name:\"generic.movementSpeed\",Amount:0.1,Operation:1,UUIDLeast:15939,UUIDMost:395317}]}", "{AttributeModifiers:[{AttributeName:\"generic.movementSpeed\",Name:\"generic.movementSpeed\",Amount:1,Operation:1,UUIDLeast:15939,UUIDMost:395317}]}", "{AttributeModifiers:[{AttributeName:\"generic.movementSpeed\",Name:\"generic.movementSpeed\",Amount:10,Operation:1,UUIDLeast:15939,UUIDMost:395317}]}" };
        String[] array2;
        for (int length = (array2 = array).length, i = 0; i < length; ++i) {
            final String NBT = array2[i];
            final ItemStack stick = new ItemStack(Items.stick);
            try {
                stick.setTagCompound(JsonToNBT.getTagFromJson(NBT));
            }
            catch (NBTException ex) {}
            itemList.add(stick);
        }
        itemList.add(new ItemStack(Items.milk_bucket));
        final String[] types = { "1", "2", "3" };
        final Integer[] amplifiers = { 1, 2, 5, 127, 128, 254, 255 };
        final int[] durations = { 0, 5, 60, 100000 };
        int[] array3;
        for (int length2 = (array3 = new int[] { 8193, 16385 }).length, j = 0; j < length2; ++j) {
            final int type = array3[j];
            for (int effect = 0; effect < 32; ++effect) {
                if (Potion.potionTypes[effect] != null) {
                    Integer[] array4;
                    for (int length3 = (array4 = amplifiers).length, k = 0; k < length3; ++k) {
                        final Integer amplifier = array4[k];
                        int[] array5;
                        for (int length4 = (array5 = durations).length, l = 0; l < length4; ++l) {
                            final int duration = array5[l];
                            itemList.add(ItemStackUtil.stringtostack("potion 1 " + type + " {CustomPotionEffects:[{Id:" + effect + ",Amplifier:" + (amplifier - 1) + ",Duration:" + duration * 20 + "}],display:{Name:\"" + I18n.format(Potion.potionTypes[effect].getName(), new Object[0]) + " " + amplifier + " for " + ((duration == 100000) ? "ever" : (String.valueOf(duration) + " sec")) + "\"}}"));
                        }
                    }
                }
            }
        }
        SkillTabExploits.removeSuspiciousTags(itemList);
    }
    
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack((Item)Items.potionitem);
    }
    
    @Override
    public String getTranslatedTabLabel() {
        return "Potions";
    }
    
    @Override
    public boolean hasSearchBar() {
        return true;
    }
}

