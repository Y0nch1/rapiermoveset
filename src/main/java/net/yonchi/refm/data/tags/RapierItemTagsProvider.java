package net.yonchi.refm.data.tags;

import net.minecraft.core.HolderLookup.*;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.registry.entries.RapierAddonItems;

import java.util.concurrent.CompletableFuture;

public class RapierItemTagsProvider extends ItemTagsProvider {
    public RapierItemTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @org.jetbrains.annotations.Nullable net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper){super(output, lookupProvider, blockTags, RapierForEpicfight.MOD_ID, existingFileHelper);}

    @Override
    protected void addTags(Provider provider) {
        this.tag(ItemTags.SWORDS)
                .add(
                        RapierAddonItems.IRON_RAPIER.get(),
                        RapierAddonItems.GOLDEN_RAPIER.get(),
                        RapierAddonItems.DIAMOND_RAPIER.get(),
                        RapierAddonItems.NETHERITE_RAPIER.get(),
                        RapierAddonItems.ENDERITE_RAPIER.get(),
                        RapierAddonItems.OCEANITE_RAPIER.get(),
                        RapierAddonItems.WITHERITE_RAPIER.get()
                );
    }
}
