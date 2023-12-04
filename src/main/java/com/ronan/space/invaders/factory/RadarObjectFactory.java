package com.ronan.space.invaders.factory;

import com.ronan.space.invaders.model.RadarObject;

/**
 * This functional interface is a factory class that allows us to create subclasses of RadarObject. All RadarObjects are
 * 2D String arrays with common logic. This approach follows the open closed principle and allows us to later customise
 * subclasses of RadarObject if needed.
 */
@FunctionalInterface
public interface RadarObjectFactory<T extends RadarObject> {
    T create(String[][] data);
}