package com.asphalt8.comparator;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComparatorFactory extends AbstractComparatorFactory {

	@Resource
	SpeedComparator speed;

	@Resource
	AccelerationComparator acceleration;

	@Resource
	HandlingComparator handling;

	@Resource
	NitroComparator nitro;

	@Autowired
	NitroSpeedComparator nitroSpeed;

	@Override
	public SpeedComparator getSpeedComparator() {
		return speed;
	}

	@Override
	public AccelerationComparator getAccelerationComparator() {
		return acceleration;
	}

	@Override
	public HandlingComparator getHandlingComparator() {
		return handling;
	}

	@Override
	public NitroComparator getNitroComparator() {
		return nitro;
	}

	@Override
	public NitroSpeedComparator getNitroSpeedComparator() {
		return nitroSpeed;
	}

}
