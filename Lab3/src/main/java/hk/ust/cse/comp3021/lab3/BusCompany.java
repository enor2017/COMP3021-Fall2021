package hk.ust.cse.comp3021.lab3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * {@link Bus} class represents a bus that  can be added to the instance of {@link BusCompany} via {@link BusCompany#createAndAddBus(int, String)}.
 */
record Bus(int id, String model) {
}

/**
 * {@link BusCompany} represents a bus company class, whose instance can possess a list of {@link Bus} instances.
 * {@link BusCompany} should also keep tracking the total number of {@link BusCompany} instances that have ever been created (those that have been garbage-collected should also be counted).
 */
public class BusCompany {
    public static int totalNum = 0;

    private final String name;
    private final ArrayList<Bus> busList = new ArrayList<>();

    /**
     * Initialize a new BusCompany instance.
     *
     * @param name the name of the bus company.
     */
    public BusCompany(@NotNull String name) {
        this.name = name;
        ++totalNum;
    }

    /**
     * @return the name of the bus company.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * @return the number of {@link Bus} instances that current bus company has.
     */
    public int getNumBuses() {
        return busList.size();
    }

    /**
     * @param id the id of the bus instance in current bus company.
     * @return the {@link Bus} instance with the {@code id} in the current bus company. Return null if not exist.
     */
    @Nullable
    public Bus getBusByID(int id) {
        if (id < 0 || id >= busList.size()) {
            return null;
        }
        return busList.get(id);
    }

    /**
     * @return an array of unique models used in current bus company. There should not be two equivalent models in the returned array.
     * If there are no {@link Bus} instances in the current bus company, return an empty array with length 0.
     */
    @NotNull
    public String[] getModels() {
        HashSet<String> set = new HashSet<>();
        for (var bus : busList) {
            set.add(bus.model());
        }
        String[] modelsArray = new String[set.size()];
        int counter = 0;
        for (var model : set) {
            modelsArray[counter++] = model;
        }
        return modelsArray;
    }

    /**
     * Create a new {@link Bus} instance and add to the current bus company.
     * Each {@link Bus} instance added to the {@link BusCompany} instance should have unique {@link Bus} id.
     * If the given id already exists in the current {@link BusCompany} instance, do not add the new {@link Bus} instance and return false.
     * Otherwise, if a new {@link Bus} instance is successfully added, return true.
     *
     * @param id    the id of the bus to be created.
     * @param model the model of the bus to be created.
     * @return whether a new bus instance is added to the bus company.
     */
    public boolean createAndAddBus(int id, String model) {
        for (var bus : busList) {
            if (bus.id() == id) {
                return false;
            }
        }
        busList.add(new Bus(id, model));
        return true;
    }

    /**
     * Remove all {@link Bus} instances that have been added to the current bus company via {@link BusCompany#createAndAddBus(int, String)}.
     */
    public void removeAllBuses() {
        busList.clear();
    }

    /**
     * @return the total number of {@link BusCompany} instances that have been created.
     */
    public static int getNumCompanies() {
        return totalNum;
    }

}

