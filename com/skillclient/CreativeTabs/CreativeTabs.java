package com.skillclient.CreativeTabs;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.enchantment.Enchantment;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.EnumEnchantmentType;

public abstract class CreativeTabs
{
    public static CreativeTabs[] creativeTabArray;
    public static final CreativeTabs tabBlock;
    public static final CreativeTabs tabDecorations;
    public static final CreativeTabs tabRedstone;
    public static final CreativeTabs tabTransport;
    public static final CreativeTabs tabMisc;
    public static final CreativeTabs tabAllSearch;
    public static final CreativeTabs tabFood;
    public static final CreativeTabs tabTools;
    public static final CreativeTabs tabCombat;
    public static final CreativeTabs tabBrewing;
    public static final CreativeTabs tabMaterials;
    public static final CreativeTabs tabInventory;
    private final int tabIndex;
    private final String tabLabel;
    private String theTexture;
    private boolean hasScrollbar;
    private boolean drawTitle;
    private EnumEnchantmentType[] enchantmentTypes;
    private ItemStack iconItemStack;
    
    static {
        CreativeTabs.creativeTabArray = new CreativeTabs[12];
        tabBlock = (CreativeTabs)new CreativeTabs.CreativeTabs$1(0, "buildingBlocks");
        tabDecorations = (CreativeTabs)new CreativeTabs.CreativeTabs$2(1, "decorations");
        tabRedstone = (CreativeTabs)new CreativeTabs.CreativeTabs$3(2, "redstone");
        tabTransport = (CreativeTabs)new CreativeTabs.CreativeTabs$4(3, "transportation");
        tabMisc = new CreativeTabs.CreativeTabs$5(4, "misc").setRelevantEnchantmentTypes(new EnumEnchantmentType[] { EnumEnchantmentType.ALL });
        tabAllSearch = new CreativeTabs.CreativeTabs$6(5, "search").setBackgroundImageName("item_search.png");
        tabFood = (CreativeTabs)new CreativeTabs.CreativeTabs$7(6, "food");
        tabTools = new CreativeTabs.CreativeTabs$8(7, "tools").setRelevantEnchantmentTypes(new EnumEnchantmentType[] { EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE });
        tabCombat = new CreativeTabs.CreativeTabs$9(8, "combat").setRelevantEnchantmentTypes(new EnumEnchantmentType[] { EnumEnchantmentType.ARMOR, EnumEnchantmentType.ARMOR_FEET, EnumEnchantmentType.ARMOR_HEAD, EnumEnchantmentType.ARMOR_LEGS, EnumEnchantmentType.ARMOR_TORSO, EnumEnchantmentType.BOW, EnumEnchantmentType.WEAPON });
        tabBrewing = (CreativeTabs)new CreativeTabs.CreativeTabs$10(9, "brewing");
        tabMaterials = (CreativeTabs)new CreativeTabs.CreativeTabs$11(10, "materials");
        tabInventory = new CreativeTabs.CreativeTabs$12(11, "inventory").setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
    }
    
    public CreativeTabs(final String label) {
        this(getNextID(), label);
    }
    
    public CreativeTabs(final int index, final String label) {
        this.theTexture = "items.png";
        this.hasScrollbar = true;
        this.drawTitle = true;
        if (index >= CreativeTabs.creativeTabArray.length) {
            final CreativeTabs[] tmp = new CreativeTabs[index + 1];
            for (int x = 0; x < CreativeTabs.creativeTabArray.length; ++x) {
                tmp[x] = CreativeTabs.creativeTabArray[x];
            }
            CreativeTabs.creativeTabArray = tmp;
        }
        this.tabIndex = index;
        this.tabLabel = label;
        CreativeTabs.creativeTabArray[index] = this;
    }
    
    public int getTabIndex() {
        return this.tabIndex;
    }
    
    public CreativeTabs setBackgroundImageName(final String texture) {
        this.theTexture = texture;
        return this;
    }
    
    public String getTabLabel() {
        return this.tabLabel;
    }
    
    public String getTranslatedTabLabel() {
        return "itemGroup." + this.getTabLabel();
    }
    
    public ItemStack getIconItemStack() {
        if (this.iconItemStack == null) {
            this.iconItemStack = new ItemStack(this.getTabIconItem(), 1, this.getIconItemDamage());
        }
        return this.iconItemStack;
    }
    
    public Item getTabIconItem() {
        try {
            throw new Exception("Override this or getIconItemStack");
        }
        catch (Throwable $ex) {
            throw $ex;
        }
    }
    
    public int getIconItemDamage() {
        return 0;
    }
    
    public String getBackgroundImageName() {
        return this.theTexture;
    }
    
    public boolean drawInForegroundOfTab() {
        return this.drawTitle;
    }
    
    public CreativeTabs setNoTitle() {
        this.drawTitle = false;
        return this;
    }
    
    public boolean shouldHidePlayerInventory() {
        return this.hasScrollbar;
    }
    
    public CreativeTabs setNoScrollbar() {
        this.hasScrollbar = false;
        return this;
    }
    
    public int getTabColumn() {
        if (this.tabIndex > 11) {
            return (this.tabIndex - 12) % 10 % 5;
        }
        return this.tabIndex % 6;
    }
    
    public boolean isTabInFirstRow() {
        if (this.tabIndex > 11) {
            return (this.tabIndex - 12) % 10 < 5;
        }
        return this.tabIndex < 6;
    }
    
    public EnumEnchantmentType[] getRelevantEnchantmentTypes() {
        return this.enchantmentTypes;
    }
    
    public CreativeTabs setRelevantEnchantmentTypes(final EnumEnchantmentType... types) {
        this.enchantmentTypes = types;
        return this;
    }
    
    public boolean hasRelevantEnchantmentType(final EnumEnchantmentType enchantmentType) {
        if (this.enchantmentTypes == null) {
            return false;
        }
        EnumEnchantmentType[] enchantmentTypes;
        for (int length = (enchantmentTypes = this.enchantmentTypes).length, i = 0; i < length; ++i) {
            final EnumEnchantmentType enumenchantmenttype = enchantmentTypes[i];
            if (enumenchantmenttype == enchantmentType) {
                return true;
            }
        }
        return false;
    }
    
    public void displayAllReleventItems(final List<ItemStack> p_78018_1_) {
        for (final Item item : Item.itemRegistry) {
            if (item != null && item.getCreativeTab() == this) {
                item.getSubItems(item, this, (List)p_78018_1_);
            }
        }
        if (this.getRelevantEnchantmentTypes() != null) {
            this.addEnchantmentBooksToList(p_78018_1_, this.getRelevantEnchantmentTypes());
        }
    }
    
    public void addEnchantmentBooksToList(final List<ItemStack> itemList, final EnumEnchantmentType... enchantmentType) {
        Enchantment[] enchantmentsBookList;
        for (int length = (enchantmentsBookList = Enchantment.enchantmentsBookList).length, j = 0; j < length; ++j) {
            final Enchantment enchantment = enchantmentsBookList[j];
            if (enchantment != null && enchantment.type != null) {
                boolean flag = false;
                for (int i = 0; i < enchantmentType.length && !flag; ++i) {
                    if (enchantment.type == enchantmentType[i]) {
                        flag = true;
                    }
                }
                if (flag) {
                    itemList.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
                }
            }
        }
    }
    
    public int getTabPage() {
        if (this.tabIndex > 11) {
            return (this.tabIndex - 12) / 10 + 1;
        }
        return 0;
    }
    
    public static int getNextID() {
        return CreativeTabs.creativeTabArray.length;
    }
    
    public boolean hasSearchBar() {
        return this.tabIndex == CreativeTabs.tabAllSearch.tabIndex;
    }
    
    public int getSearchbarWidth() {
        return 89;
    }
}

