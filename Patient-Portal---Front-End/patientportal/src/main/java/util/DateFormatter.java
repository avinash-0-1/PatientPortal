package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateFormatter {
	static Logger logger = Logger.getLogger(DateFormatter.class);

	public String formatDate(String dateAsParam) {
		String dateOfBirth = dateAsParam;
		try {
			logger.info("Parsing the date: "+ dateOfBirth);
			if (dateOfBirth != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateInString = dateOfBirth;
				try {
					Date date = formatter.parse(dateInString);
					dateOfBirth = formatter.format(date);
					logger.info("Parsed the date to: "+dateOfBirth);
				} catch (ParseException e) {
					logger.error("Unable to parse the date:" + dateOfBirth);
				}
			}

		} catch (Exception e) {
			logger.error("Caught an exception: " + e.getMessage());
		}
		return dateOfBirth;
	}
}
