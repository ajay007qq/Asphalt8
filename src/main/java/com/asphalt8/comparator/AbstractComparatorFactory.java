package com.asphalt8.comparator;

public abstract class AbstractComparatorFactory {

	public abstract SpeedComparator getSpeedComparator();

	public abstract AccelerationComparator getAccelerationComparator();

	public abstract HandlingComparator getHandlingComparator();

	public abstract NitroComparator getNitroComparator();

	public abstract NitroSpeedComparator getNitroSpeedComparator();
}
