package com.affehund.airplanes.util.tools;

import net.minecraftforge.energy.EnergyStorage;

public class AirplanesEnergyStorage extends EnergyStorage {


    public AirplanesEnergyStorage(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void consumePower(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void generatePower(int energy) {
        this.energy += energy;
        if (this.energy > capacity) {
            this.energy = capacity;
        }
    }

}
