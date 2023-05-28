package com.skillclient.CreativeTabs;

import com.skillclient.utils.ItemStackUtil;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import java.util.List;

public class SkillTabPlayerhead extends CreativeTabs
{
    public static List<ItemStack> skulls;
    
    static {
        SkillTabPlayerhead.skulls = new ArrayList<ItemStack>();
    }
    
    public SkillTabPlayerhead() {
        super("PlayerheadsTab");
        this.setBackgroundImageName("item_search.png");
    }
    
    @Override
    public void displayAllReleventItems(final List<ItemStack> itemList) {
        itemList.addAll(SkillTabPlayerhead.skulls);
    }
    
    @Override
    public ItemStack getIconItemStack() {
        return ItemStackUtil.stringtostack("minecraft:skull 1 3 {SkullOwner:{Id:\"1ff73791-d8f1-435f-b233-3c255928af84\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFkN2MwYTA0ZjE0ODVjN2EzZWYyNjFhNDhlZTgzYjJmMWFhNzAxYWIxMWYzZmM5MTFlMDM2NmE5Yjk3ZSJ9fX0=\"}]}}}");
    }
    
    @Override
    public String getTranslatedTabLabel() {
        return "Playerheads";
    }
    
    @Override
    public boolean hasSearchBar() {
        return true;
    }
}

