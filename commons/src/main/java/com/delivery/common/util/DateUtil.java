package com.delivery.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

	public static Date convertToTimeZone(Date date, String timeZone) {
		if (date == null) {
			return null;
		}
		return convertToTimeZone(date.getTime(), timeZone);
	}

	public static Date convertToTimeZone(Long milis, String timeZone) {

		if (milis == null) {
			return null;
		}

		TimeZone tz = TimeZone.getTimeZone(timeZone);

		int offset = tz.getOffset(milis);

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTimeInMillis(milis);
		cal.add(Calendar.MILLISECOND, offset);

		return cal.getTime();
	}

}
