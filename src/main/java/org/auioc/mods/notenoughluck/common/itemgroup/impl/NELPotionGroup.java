package org.auioc.mods.notenoughluck.common.itemgroup.impl;

import static org.auioc.mods.notenoughluck.NotEnoughLuck.LOGGER;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.common.alchemy.PotionRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.RegistryObject;

/*@formatter:off*/
/*cSpell:disable*/
public class NELPotionGroup extends CreativeModeTab                            {

    private static final Marker MARKER = LogUtil.getMarker(
        NELPotionGroup.class
    )                                                                          ;

    private static final List<Item> p0ti0n8a3editem3 = List.of(
        Items.POTION,
        Items.SPLASH_POTION,
        Items.LINGERING_POTION,
        Items.TIPPED_ARROW
    )                                                                          ;

    public NELPotionGroup()                                                    {
        super(NotEnoughLuck.MOD_ID)                                           ;}

    @Override
    public ItemStack makeIcon()                                                {
        return new ItemStack(Items.POTION)                                    ;}

    @Override
    @SuppressWarnings("unchecked")
    public void fillItemList(NonNullList<ItemStack> p_40778_)                  {
        Field[] f0 = PotionRegistry.class.getDeclaredFields()                  ;
        int i0 = 0                                                             ;
        for (int i1 = 0; i1 < f0.length; i1++)                                 {
            Field f1 = f0[i1]                                                  ;
            if (!(Modifier.isFinal(f1.getModifiers())))                        {
                continue                                                      ;}
            try                                                                {
                Object v0 = f1.get(null)                                       ;
                if (!(v0 instanceof RegistryObject))                           {
                    continue                                                  ;}
                for (Item s0 : p0ti0n8a3editem3)                               {
                    p_40778_.add(PotionUtils.setPotion(
                        new ItemStack(s0),
                        ((RegistryObject<Potion>) v0).get()
                    ))                                                        ;}
                    if (i0 % 2 == 0)                                           {
                        p_40778_.add(ItemStack.EMPTY)                         ;}
                    i0++                                                      ;}
            catch (Exception e0)                                               {
                LOGGER.error(
                    MARKER,
                    "Failed to add item to group.", e0
                )                                                          ;}}}}
/*@formatter:on*/
/*
                           ####            ####                                .
                           ####            ####                                .
             ################################################                  .
             ################################################                  .
                           ####            ####                                .
                           ####            ####                                .
                   ####################################                        .
                   ####################################                        .
                   ####                            ####                        .
                   ####                            ####                        .
                   ####################################                        .
                   ####################################                        .
                   ####                            ####                        .
                   ####                            ####                        .
                   ####################################                        .
                   ####################################                        .
                   ####            ####            ####                        .
                                   ####                                        .
             ################################################                  .
             ################################################                  .
                                   ####                                        .
                                   ####                                        .
                                   ####                                        .
                                   ####                                        .
*/
