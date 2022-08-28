package com.mrcrayfish.configured.api.simple;

import com.electronwill.nightconfig.core.ConfigSpec;
import com.google.common.base.Preconditions;

/**
 * Author: MrCrayfish
 */
public final class DoubleProperty extends ConfigProperty<Double>
{
    private final double minValue;
    private final double maxValue;

    DoubleProperty(double defaultValue, double minValue, double maxValue)
    {
        super(defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public double getMinValue()
    {
        return this.minValue;
    }

    public double getMaxValue()
    {
        return this.maxValue;
    }

    @Override
    public void defineSpec(ConfigSpec spec)
    {
        Preconditions.checkState(this.data != null, "Config property is not initialized yet");
        spec.defineInRange(this.data.getPath(), this.defaultValue, this.minValue, this.maxValue);
    }

    @Override
    public boolean isValid(Double value)
    {
        return value != null && value.compareTo(this.minValue) >= 0 && value.compareTo(this.maxValue) <= 0;
    }

    public static DoubleProperty create(double defaultValue)
    {
        return create(defaultValue, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public static DoubleProperty create(double defaultValue, double minValue, double maxValue)
    {
        return new DoubleProperty(defaultValue, minValue, maxValue);
    }
}
