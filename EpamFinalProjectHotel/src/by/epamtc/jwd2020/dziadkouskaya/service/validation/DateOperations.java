package by.epamtc.jwd2020.dziadkouskaya.service.validation;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class DateOperations {
	public static long subtractDays(Date startDate, Date endDate) {

		long duration = endDate.getTime() - startDate.getTime();
		long diffInHours = TimeUnit.MILLISECONDS.toDays(duration);

		return diffInHours;
	}

}
