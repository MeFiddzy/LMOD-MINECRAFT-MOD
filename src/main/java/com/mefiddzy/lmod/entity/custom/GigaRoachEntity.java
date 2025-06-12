package com.mefiddzy.lmod.entity.custom;

import com.mefiddzy.lmod.entity.ModEntities;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GigaRoachEntity extends Animal {

    public final AnimationState idleAnimS = new AnimationState();
    private int idleAnimTimeout = 0;

    public GigaRoachEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(Items.SUGAR), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        //scared of
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Player.class, 8f, 1.2f, 1f));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Fox.class, 4f, 1.2f, 1f));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Wolf.class, 10f, 1.2f, 1f));

        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAtr() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d)
                .add(Attributes.FOLLOW_RANGE, 24d);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.SUGAR);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.GIGA_ROACH.get().create(level);
    }

    private void setupAnimStates() {
        if (this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = 50;
            this.idleAnimS.start(this.tickCount);
        }
        else {
            this.idleAnimTimeout--;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide)
            this.setupAnimStates();
    }
}
