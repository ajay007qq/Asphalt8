package com.asphalt8.comparator;

import java.math.BigDecimal;
import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.asphalt8.entity.CarFeature;

@Component
public class NitroSpeedComparator implements Comparator<CarFeature> {

	@Override
	public int compare(CarFeature f1, CarFeature f2) {

		BigDecimal s1 = new BigDecimal(f1.getMaxNitroSpeed());
		BigDecimal s2 = new BigDecimal(f2.getMaxNitroSpeed());

		double cmp = s1.subtract(s2).doubleValue();

		if (cmp < 0) {
			return 1;
		} else if (cmp > 0) {
			return -1;
		}

		return 0;
	}

}
