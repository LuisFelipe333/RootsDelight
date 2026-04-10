package com.roots.roots_delight.block;
import com.roots.roots_delight.RootsDelight;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class CocolmecaBlock extends PotatoBlock {
    public CocolmecaBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        // Esto le dice al bloque que, al romperse, suelte la Raíz de Cocolmeca
        return RootsDelight.RAIZ_COCOLMECA.get();
    }
}