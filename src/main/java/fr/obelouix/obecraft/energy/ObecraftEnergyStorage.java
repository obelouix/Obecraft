package fr.obelouix.obecraft.energy;

import fr.obelouix.obecraft.Obecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.EnergyStorage;

public class ObecraftEnergyStorage extends EnergyStorage {


    private EnergySync clientEnergy;

    public ObecraftEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer, 0);
    }

    // Override this to (for example) call setChanged() on your block entity
    protected void onEnergyChanged() {
    }

    @OnlyIn(Dist.CLIENT)
    public EnergySync getGuiEnergy() {
        if (Dist.CLIENT.isClient() || Dist.DEDICATED_SERVER.isClient()) {
            return clientEnergy;
        }
        Obecraft.getLOGGER().warn("getGuiEnergy was called on server!");
        return new EnergySync(this.getEnergyStored(), this.getMaxEnergyStored());
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int rc = super.receiveEnergy(maxReceive, simulate);
        if (rc > 0 && !simulate) {
            onEnergyChanged();
        }
        return rc;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int rc = super.extractEnergy(maxExtract, simulate);
        if (rc > 0 && !simulate) {
            onEnergyChanged();
        }
        return rc;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(int energy) {
        if (this.energy >= getMaxEnergyStored()) {
            this.energy = getEnergyStored();
        } else this.energy += energy;

        onEnergyChanged();
    }

    public void consumeEnergy(int energy) {
        if (this.energy < 0) {
            this.energy = 0;
        } else this.energy -= energy;

        onEnergyChanged();
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }
}
