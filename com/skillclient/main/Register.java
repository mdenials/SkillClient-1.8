package com.skillclient.main;

import java.util.Iterator;
import com.skillclient.utils.exceptions.InvalidModuleException;
import com.skillclient.modules.misc.TestMod;
import com.skillclient.modules.misc.ClickGui;
import com.skillclient.modules.world.Tower;
import com.skillclient.modules.movement.Step;
import com.skillclient.modules.movement.Spider;
import com.skillclient.modules.misc.SkinStealer;
import com.skillclient.modules.render.SkinBlinker;
import com.skillclient.modules.movement.SafeWalk;
import com.skillclient.modules.movement.RapidJump;
import com.skillclient.modules.misc.Panic;
import com.skillclient.modules.player.NoSlowdown;
import com.skillclient.modules.player.NoFall;
import com.skillclient.modules.player.NoClip;
import com.skillclient.modules.world.NoAirPlace;
import com.skillclient.modules.movement.Jesus;
import com.skillclient.modules.misc.HUD;
import com.skillclient.modules.render.DataViewer;
import com.skillclient.modules.player.ChestLoot;
import com.skillclient.modules.player.ChestAura;
import com.skillclient.modules.combat.BowAimBot;
import com.skillclient.modules.movement.BetterIce;
import com.skillclient.modules.player.BackSprint;
import com.skillclient.modules.util.BackPort;
import com.skillclient.modules.auto.AutoWalk;
import com.skillclient.modules.auto.AutoTool;
import com.skillclient.modules.auto.AutoSwitchSlot;
import com.skillclient.modules.auto.AutoSprint;
import com.skillclient.modules.auto.AutoReSpawn;
import com.skillclient.modules.auto.AutoPlace;
import com.skillclient.modules.auto.AutoMine;
import com.skillclient.modules.auto.AutoJump;
import com.skillclient.modules.auto.AutoFish;
import com.skillclient.modules.auto.AutoEat;
import com.skillclient.modules.auto.AutoArmor;
import com.skillclient.modules.render.ArmorHUD;
import com.skillclient.modules.render.AntiInvisible;
import com.skillclient.modules.render.NameTags;
import com.skillclient.modules.movement.AirMovement;
import com.skillclient.modules.world.Timer;
import com.skillclient.modules.player.AirJump;
import com.skillclient.modules.combat.Range;
import com.skillclient.modules.util.NoRotation;
import com.skillclient.modules.world.FastPlace;
import com.skillclient.modules.auto.BetterSneak;
import com.skillclient.modules.auto.AutoSneak;
import com.skillclient.modules.render.Compass;
import com.skillclient.modules.render.XRay;
import com.skillclient.modules.movement.Glide;
import com.skillclient.modules.util.AntiPushoutoOfBlock;
import com.skillclient.modules.util.BowBoost;
import com.skillclient.modules.util.Blink;
import com.skillclient.modules.render.FullBright;
import com.skillclient.modules.render.ESP;
import com.skillclient.modules.render.FreeCam;
import com.skillclient.modules.world.Nuker;
import com.skillclient.modules.combat.Velocity;
import com.skillclient.modules.render.ChestESP;
import com.skillclient.modules.movement.Fly;
import com.skillclient.modules.movement.Speed;
import com.skillclient.modules.render.AntiBlind;
import com.skillclient.modules.player.InventoryMove;
import com.skillclient.modules.render.Tracers;
import com.skillclient.modules.combat.KillAura;
import com.skillclient.modules.combat.TriggerBot;
import com.skillclient.modules.movement.Strafe;
import com.skillclient.modules.render.WallHack;
import com.skillclient.modules.render.NoHurtCam;
import com.skillclient.modules.util.BlockBuild;
import com.skillclient.modules.movement.SlimeJump;
import com.skillclient.modules.combat.HitBox;
import com.skillclient.modules.player.NoSwing;
import com.skillclient.modules.world.FastBreak;
import com.skillclient.modules.combat.FastUse;
import com.skillclient.modules.render.CaveFinder;
import com.skillclient.modules.movement.FastLadder;
import com.skillclient.modules.world.ScaffoldWalk;
import com.skillclient.modules.world.WorldInteraction;
import com.skillclient.modules.misc.Spammer;
import com.skillclient.modules.player.InventoryManager;
import com.skillclient.modules.misc.LaggSign;
import com.skillclient.modules.exploit.Regen;
import com.skillclient.modules.exploit.BeaconExploit;
import com.skillclient.modules.exploit.GodMode;
import com.skillclient.modules.world.CivBreak;
import com.skillclient.modules.exploit.Teleport;
import com.skillclient.modules.exploit.ItemSpoof;
import com.skillclient.modules.misc.ItemAura;
import com.skillclient.modules.world.Fucker;
import com.skillclient.modules.exploit.PluginExploit;
import com.skillclient.modules.exploit.AnticheatDetection;
import com.skillclient.modules.combat.WiZARDHAX;
import com.skillclient.modules.combat.TargetUtil;
import com.skillclient.modules.combat.AntiBot;
import com.skillclient.modules.util.AdvancedToolBox;
import com.skillclient.CreativeTabs.SkillTabExploits;
import com.skillclient.CreativeTabs.SkillTabSpawner;
import com.skillclient.CreativeTabs.SkillTabPlayerhead;
import com.skillclient.CreativeTabs.SkillTabPotion;
import com.skillclient.CreativeTabs.SkillTabSword;
import com.skillclient.CreativeTabs.SkillTabArmor;
import com.skillclient.CreativeTabs.SkillTabBlock;
import com.skillclient.events.api.EventManager;
import java.util.HashMap;
import com.skillclient.misc.Module;
import java.util.Map;
import com.skillclient.misc.SCMC;

public class Register implements SCMC
{
    public static final Map<Class<Module>, Module> moduleMap;
    
    static {
        moduleMap = new HashMap<Class<Module>, Module>();
    }
    
    public static void registerModule(final Module module) {
        try {
            Register.sc.moduleList.add(module);
            Register.moduleMap.put((Class<Module>)module.getClass(), module);
            module.category.registerModule(module);
            EventManager.register(module);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Register() {
        new SkillTabBlock();
        new SkillTabArmor();
        new SkillTabSword();
        new SkillTabPotion();
        new SkillTabPlayerhead();
        new SkillTabSpawner();
        new SkillTabExploits();
        registerModule((Module)new AdvancedToolBox());
        registerModule((Module)new AntiBot());
        registerModule((Module)new TargetUtil());
        registerModule((Module)new WiZARDHAX());
        registerModule((Module)new AnticheatDetection());
        registerModule((Module)new PluginExploit());
        registerModule((Module)new Fucker());
        registerModule((Module)new ItemAura());
        registerModule((Module)new ItemSpoof());
        registerModule((Module)new Teleport());
        registerModule((Module)new CivBreak());
        registerModule((Module)new GodMode());
        registerModule((Module)new BeaconExploit());
        registerModule((Module)new Regen());
        registerModule((Module)new LaggSign());
        registerModule((Module)new InventoryManager());
        registerModule((Module)new Spammer());
        registerModule((Module)new WorldInteraction());
        registerModule((Module)new ScaffoldWalk());
        registerModule((Module)new FastLadder());
        registerModule((Module)new CaveFinder());
        registerModule((Module)new FastUse());
        registerModule((Module)new FastBreak());
        registerModule((Module)new NoSwing());
        registerModule((Module)new HitBox());
        registerModule((Module)new SlimeJump());
        registerModule((Module)new BlockBuild());
        registerModule((Module)new NoHurtCam());
        registerModule((Module)new WallHack());
        registerModule((Module)new Strafe());
        registerModule((Module)new TriggerBot());
        registerModule((Module)new KillAura());
        registerModule((Module)new Tracers());
        registerModule((Module)new InventoryMove());
        registerModule((Module)new AntiBlind());
        registerModule((Module)new Speed());
        registerModule((Module)new Fly());
        registerModule((Module)new ChestESP());
        registerModule((Module)new Velocity());
        registerModule((Module)new Nuker());
        registerModule((Module)new FreeCam());
        registerModule((Module)new ESP());
        registerModule((Module)new FullBright());
        registerModule((Module)new Blink());
        registerModule((Module)new BowBoost());
        registerModule((Module)new AntiPushoutoOfBlock());
        registerModule((Module)new Glide());
        registerModule((Module)new XRay());
        registerModule((Module)new Compass());
        registerModule((Module)new AutoSneak());
        registerModule((Module)new BetterSneak());
        registerModule((Module)new FastPlace());
        registerModule((Module)new NoRotation());
        registerModule((Module)new Range());
        registerModule((Module)new AirJump());
        registerModule((Module)new Timer());
        registerModule((Module)new AirMovement());
        registerModule((Module)new NameTags());
        registerModule((Module)new AntiInvisible());
        registerModule((Module)new ArmorHUD());
        registerModule((Module)new AutoArmor());
        registerModule((Module)new AutoEat());
        registerModule((Module)new AutoFish());
        registerModule((Module)new AutoJump());
        registerModule((Module)new AutoMine());
        registerModule((Module)new AutoPlace());
        registerModule((Module)new AutoReSpawn());
        registerModule((Module)new AutoSprint());
        registerModule((Module)new AutoSwitchSlot());
        registerModule((Module)new AutoTool());
        registerModule((Module)new AutoWalk());
        registerModule((Module)new BackPort());
        registerModule((Module)new BackSprint());
        registerModule((Module)new BetterIce());
        registerModule((Module)new BowAimBot());
        registerModule((Module)new ChestAura());
        registerModule((Module)new ChestLoot());
        registerModule((Module)new DataViewer());
        registerModule((Module)new HUD());
        registerModule((Module)new Jesus());
        registerModule((Module)new NoAirPlace());
        registerModule((Module)new NoClip());
        registerModule((Module)new NoFall());
        registerModule((Module)new NoSlowdown());
        registerModule((Module)new Panic());
        registerModule((Module)new RapidJump());
        registerModule((Module)new SafeWalk());
        registerModule((Module)new SkinBlinker());
        registerModule((Module)new SkinStealer());
        registerModule((Module)new Spider());
        registerModule((Module)new Step());
        registerModule((Module)new Tower());
        registerModule((Module)new ClickGui());
        registerModule((Module)new TestMod());
    }
    
    public static <M extends Module> M getModule(final Class<? extends M> clazz) {
        try {
            final M m = (M)Register.moduleMap.get(clazz);
            if (m != null) {
                return m;
            }
            throw new InvalidModuleException(clazz);
        }
        catch (Throwable $ex) {
            throw $ex;
        }
    }
    
    public static <M extends Module> boolean isActive(final Class<? extends M> clazz) {
        return Register.moduleMap.containsKey(clazz) && Register.moduleMap.get(clazz).isActive();
    }
    
    public static boolean isNoModuleOrActive(final Class<?> clazz) {
        try {
            for (final Module module : Register.sc.moduleList) {
                if (module.getClass().equals(clazz)) {
                    return module.isActive();
                }
            }
            return true;
        }
        catch (Throwable $ex) {
            throw $ex;
        }
    }
}

