package com.skillclient.modules.render;

import com.skillclient.gui.GuiOverlay;
import java.awt.Color;
import java.util.Iterator;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.skillclient.main.Register;
import java.util.HashMap;
import java.util.ArrayList;
import net.minecraft.block.Block;
import java.util.Map;
import com.skillclient.misc.Module;

public class XRay extends Module
{
    public static Map<Block, Boolean> map;
    public ArrayList<XRay.XRay_Block> xray_blocks;
    
    static {
        XRay.map = new HashMap<Block, Boolean>();
    }
    
    public XRay() {
        super("XRay", Register.Category.RENDER, "Xray through Stone etc. Thanks a lot @JigsawDev(Robof\u00e5n)");
        (this.xray_blocks = new ArrayList<XRay.XRay_Block>()).add(new XRay.XRay_Block(this, new ItemStack(this.b(1)), new Block[] { this.b(1) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(2)), new Block[] { this.b(2) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(3)), new Block[] { this.b(3) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(4)), new Block[] { this.b(4) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(5)), new Block[] { this.b(5) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(6)), new Block[] { this.b(6) }, "Sapling"));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(7)), new Block[] { this.b(7) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.water_bucket), new Block[] { this.b(8), this.b(9) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.lava_bucket), new Block[] { this.b(10), this.b(11) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(12)), new Block[] { this.b(12) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(13)), new Block[] { this.b(13) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(14)), new Block[] { this.b(14) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(15)), new Block[] { this.b(15) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(16)), new Block[] { this.b(16) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(17)), new Block[] { this.b(17), this.b(162) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(18)), new Block[] { this.b(18), this.b(161) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(19)), new Block[] { this.b(19) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(20)), new Block[] { this.b(20) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(21)), new Block[] { this.b(21) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(22)), new Block[] { this.b(22) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(23)), new Block[] { this.b(23) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(24)), new Block[] { this.b(24) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(25)), new Block[] { this.b(25) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.bed), new Block[] { this.b(26) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(29)), new Block[] { this.b(29) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(30)), new Block[] { this.b(30) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(33)), new Block[] { this.b(33), this.b(34) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(35)), new Block[] { this.b(35), Blocks.carpet }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(37)), new Block[] { this.b(31), this.b(32), this.b(37), this.b(38), this.b(39), this.b(40), this.b(175) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(41)), new Block[] { this.b(41) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(42)), new Block[] { this.b(42) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(44)), new Block[] { this.b(44), this.b(43) }, "Slab"));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(45)), new Block[] { this.b(45) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(46)), new Block[] { this.b(46) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(47)), new Block[] { this.b(47) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(48)), new Block[] { this.b(48) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(49)), new Block[] { this.b(49) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(50)), new Block[] { this.b(50) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.flint_and_steel), new Block[] { this.b(51) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(52)), new Block[] { this.b(52) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(53)), new Block[] { this.b(53), this.b(163), this.b(164), this.b(134), this.b(108), this.b(135), this.b(136) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(54)), new Block[] { this.b(54), this.b(146), this.b(130) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.redstone), new Block[] { this.b(55) }, "Redstone Wire"));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(56)), new Block[] { this.b(56) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(57)), new Block[] { this.b(57) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(58)), new Block[] { this.b(58) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.wheat_seeds), new Block[] { this.b(59), this.b(104), this.b(105) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(60)), new Block[] { this.b(60) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(61)), new Block[] { this.b(61), this.b(62) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.sign), new Block[] { this.b(63), this.b(68) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.oak_door), new Block[] { this.b(64), this.b(193), this.b(194), this.b(195), this.b(196), this.b(197) }, "Door"));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(65)), new Block[] { this.b(65) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(66)), new Block[] { this.b(66), this.b(157), this.b(27), this.b(28) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(67)), new Block[] { this.b(67) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(69)), new Block[] { this.b(69) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(70)), new Block[] { this.b(70) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.iron_door), new Block[] { this.b(71) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(72)), new Block[] { this.b(72) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(73)), new Block[] { this.b(73), this.b(74) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(76)), new Block[] { this.b(76), this.b(75) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(77)), new Block[] { this.b(77) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(78)), new Block[] { this.b(78) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(79)), new Block[] { this.b(79), this.b(212), this.b(174) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(80)), new Block[] { this.b(80) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(81)), new Block[] { this.b(81) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(82)), new Block[] { this.b(82) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.reeds), new Block[] { this.b(83) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(84)), new Block[] { this.b(84) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(86)), new Block[] { this.b(86) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(87)), new Block[] { this.b(87) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(88)), new Block[] { this.b(88) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(89)), new Block[] { this.b(89) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(91)), new Block[] { this.b(91) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.cake), new Block[] { this.b(92) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.repeater), new Block[] { this.b(93), this.b(94) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(95)), new Block[] { this.b(95) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(96)), new Block[] { this.b(96) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(97)), new Block[] { this.b(97) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(98)), new Block[] { this.b(98) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(100)), new Block[] { this.b(100), this.b(99) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(101)), new Block[] { this.b(101) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(102)), new Block[] { this.b(102) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(103)), new Block[] { this.b(103) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(106)), new Block[] { this.b(106) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(109)), new Block[] { this.b(109) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(110)), new Block[] { this.b(110) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(111)), new Block[] { this.b(111) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(112)), new Block[] { this.b(112) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(114)), new Block[] { this.b(114) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.nether_wart), new Block[] { this.b(115) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(116)), new Block[] { this.b(116) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.brewing_stand), new Block[] { this.b(117) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.cauldron), new Block[] { this.b(118) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(120)), new Block[] { this.b(120) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(121)), new Block[] { this.b(121) }).disable());
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(122)), new Block[] { this.b(122) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(123)), new Block[] { this.b(123), this.b(124) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(126)), new Block[] { this.b(126), this.b(125) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.dye, 1, 3), new Block[] { this.b(127) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(128)), new Block[] { this.b(128) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(129)), new Block[] { this.b(129) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(131)), new Block[] { this.b(131) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.string), new Block[] { this.b(132) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(133)), new Block[] { this.b(133) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(137)), new Block[] { this.b(137), this.b(211), this.b(210) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(138)), new Block[] { this.b(138) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(139)), new Block[] { this.b(139) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.flower_pot), new Block[] { this.b(140) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.carrot), new Block[] { this.b(141) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.potato), new Block[] { this.b(142) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(143)), new Block[] { this.b(143) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.skull), new Block[] { this.b(144) }, "Skull"));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(145)), new Block[] { this.b(145) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(147)), new Block[] { this.b(147) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(148)), new Block[] { this.b(148) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.comparator), new Block[] { this.b(149), this.b(150) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(151)), new Block[] { this.b(151), this.b(178) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(152)), new Block[] { this.b(152) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(153)), new Block[] { this.b(153) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(154)), new Block[] { this.b(154) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(155)), new Block[] { this.b(155) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(156)), new Block[] { this.b(156) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(158)), new Block[] { this.b(158) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(159)), new Block[] { this.b(159) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(160)), new Block[] { this.b(160) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(165)), new Block[] { this.b(165) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(166)), new Block[] { this.b(166) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(167)), new Block[] { this.b(167) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(168)), new Block[] { this.b(168) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(169)), new Block[] { this.b(169) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(170)), new Block[] { this.b(170) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(172)), new Block[] { this.b(172) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(173)), new Block[] { this.b(173) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(Items.banner), new Block[] { this.b(176), this.b(177) }, "Banner"));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(179)), new Block[] { this.b(179) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(180)), new Block[] { this.b(180) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(182)), new Block[] { this.b(182), this.b(181) }));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(183)), new Block[] { this.b(107), this.b(183), this.b(184), this.b(185), this.b(186), this.b(187) }, "Fence Gate"));
        this.xray_blocks.add(new XRay.XRay_Block(this, new ItemStack(this.b(188)), new Block[] { this.b(85), this.b(188), this.b(189), this.b(190), this.b(191), this.b(192), this.b(113) }, "Fence"));
        try {
            final ItemStack no = new ItemStack(Items.banner, 1, 0);
            no.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{Base:15,Patterns:[{Pattern:cr,Color:1}]}}"));
            this.xray_blocks.add((XRay.XRay_Block)new XRay.XRay$1(this, this, no, new Block[0], "Deactivate All"));
            final ItemStack yes = new ItemStack(Items.banner, 1, 0);
            yes.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{Base:10,Patterns:[]}}"));
            this.xray_blocks.add((XRay.XRay_Block)new XRay.XRay$2(this, this, yes, new Block[0], "Activate All"));
            this.xray_blocks.add((XRay.XRay_Block)new XRay.XRay$3(this, this, new ItemStack(Blocks.diamond_ore), new Block[0], "Default"));
        }
        catch (NBTException e) {
            e.printStackTrace();
        }
        for (final XRay.XRay_Block xray_block : this.xray_blocks) {
            Block[] blocks;
            for (int length = (blocks = xray_block.getBlocks()).length, i = 0; i < length; ++i) {
                final Block block = blocks[i];
                XRay.map.put(block, xray_block.isActive_by_default());
            }
        }
    }
    
    public static void loadRenders() {
        XRay.mc.renderGlobal.loadRenderers();
    }
    
    public static boolean renderblock(final Block block) {
        if (!XRay.map.containsKey(block)) {
            XRay.map.put(block, false);
            GuiOverlay.notify(String.valueOf(block.getLocalizedName()) + " has been missing.", Color.RED);
        }
        return XRay.map.get(block);
    }
    
    private Block b(final int id) {
        return Block.getBlockById(id);
    }
    
    public void onDisable() {
        loadRenders();
        super.onDisable();
    }
    
    public void onEnable() {
        loadRenders();
        super.onEnable();
    }
}

