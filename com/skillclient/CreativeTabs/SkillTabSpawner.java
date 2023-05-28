package com.skillclient.CreativeTabs;

import java.util.Iterator;
import net.minecraft.init.Items;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.init.Blocks;
import com.skillclient.chat.CmdExploit;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import java.util.List;

public class SkillTabSpawner extends CreativeTabs
{
    public SkillTabSpawner() {
        super("SpawnerTab");
    }
    
    @Override
    public void displayAllReleventItems(final List<ItemStack> itemList) {
        final List<String> spawner = new ArrayList<String>();
        spawner.add("{display:{Name:\"Empty Spawner\"}}");
        spawner.add("{display:{Name:\"Diamond Spawner\"},BlockEntityTag:{EntityId:Item,SpawnData:{Item:{id:diamond,Count:1}},SpawnCount:1,SpawnRange:5,RequiredPlayerRange:100,Delay:0,MinSpawnDelay:20,MaxSpawnDelay:20,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"Command-Block Spawner\"},BlockEntityTag:{EntityId:FallingSand,SpawnData:{Block:command_block,Data:0,TileEntityData:{auto:1b,Command:" + CmdExploit.cmd + ",TrackOutput:0},Time:1,DropItem:0},SpawnRange:1}}");
        spawner.add("{display:{Name:\"CommandBlock Minecart Spawner\"},BlockEntityTag:{EntityId:MinecartCommandBlock,SpawnData:{Motion:[0.0,0.0,0.0],Command:" + CmdExploit.cmd + "},SpawnCount:1,SpawnRange:1,RequiredPlayerRange:100,Delay:20,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"XP Fake Spawner\"},BlockEntityTag:{EntityId:XPOrb,SpawnData:{Value:-1},SpawnCount:1000,SpawnRange:5,RequiredPlayerRange:100,Delay:0,MinSpawnDelay:1,MaxSpawnDelay:1,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"1000*100 TNT Spawner\"},BlockEntityTag:{EntityId:FallingSand,SpawnData:{Motion:[0.0,0.0,0.0],Block:mob_spawner,Data:0,TileEntityData:{EntityId:PrimedTnt,SpawnData:{Motion:[0.0,0.0,0.0],Fuse:40,CustomNameVisible:1},SpawnCount:1000,SpawnRange:10,RequiredPlayerRange:100,Delay:0,MaxNearbyEntities:100},Time:1,DropItem:0},SpawnCount:100,SpawnRange:100,RequiredPlayerRange:100,Delay:20,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"Ocean Spawner\"},BlockEntityTag:{EntityId:FallingSand,SpawnData:{Motion:[0.0,0.0,0.0],Block:flowing_water,Data:0,Time:1,DropItem:0},SpawnCount:100,SpawnRange:50,RequiredPlayerRange:100,Delay:20,MinSpawnDelay:20,MaxSpawnDelay:20,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"Fire Spawner\"},BlockEntityTag:{EntityId:FallingSand,SpawnData:{Motion:[0.0,0.0,0.0],Block:fire,Data:0,Time:1,DropItem:0},SpawnCount:100,SpawnRange:50,RequiredPlayerRange:100,Delay:20,MinSpawnDelay:20,MaxSpawnDelay:20,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"Creeper Spawner\"},BlockEntityTag:{EntityId:Creeper,SpawnData:{ExplosionRadius:100,Fuse:0},SpawnCount:5,SpawnRange:5,RequiredPlayerRange:100,Delay:0,MinSpawnDelay:20,MaxSpawnDelay:20,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"Slime Spawner 4K\"},BlockEntityTag:{EntityId:Slime,SpawnData:{Size:4000},SpawnCount:5,SpawnRange:5,RequiredPlayerRange:100,Delay:0,MinSpawnDelay:20,MaxSpawnDelay:20,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"EnderDragon Spawner\"},BlockEntityTag:{EntityId:EnderDragon,SpawnData:{Motion:[0.0,0.0,0.0]},SpawnCount:1,SpawnRange:1,RequiredPlayerRange:100,Delay:0,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"WitherBoss Spawner\"},BlockEntityTag:{EntityId:WitherBoss,SpawnData:{Motion:[0.0,0.0,0.0]},SpawnCount:1,SpawnRange:1,RequiredPlayerRange:100,Delay:0,MaxNearbyEntities:100}}");
        spawner.add("{display:{Name:\"End-Portal  Spawner\"},BlockEntityTag:{EntityId:EnderDragon,SpawnData:{Motion:[0.0,0.0,0.0],Attributes:[{Name:generic.maxHealth,Base:-1}]},SpawnCount:1,SpawnRange:1,RequiredPlayerRange:100,Delay:0,MaxNearbyEntities:100}}");
        for (final String s : spawner) {
            try {
                final ItemStack stack = new ItemStack(Blocks.mob_spawner);
                stack.setTagCompound(JsonToNBT.getTagFromJson(s));
                itemList.add(stack);
            }
            catch (NBTException e) {
                e.printStackTrace();
            }
        }
        for (int m = 0; m <= 120; ++m) {
            if (EntityList.getStringFromID(m) != null) {
                itemList.add(new ItemStack(Items.spawn_egg, 1, m));
            }
        }
        SkillTabExploits.removeSuspiciousTags(itemList);
    }
    
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Items.spawn_egg);
    }
    
    @Override
    public String getTranslatedTabLabel() {
        return "SpawnerTab";
    }
}


