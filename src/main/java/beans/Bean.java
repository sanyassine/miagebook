package beans;

import java.sql.Timestamp;
import java.util.Date;

public abstract class Bean {
	public Bean() {
		updateLastChangeDate();
	}
	protected Timestamp lastChangeDate;
	public Timestamp getLastChangeDate() {
		return lastChangeDate;
	}
	void setLastChangeDate(Timestamp lcd) {
		lastChangeDate = lcd;
	}
	
	void updateLastChangeDate() {
		lastChangeDate = new Timestamp(new Date().getTime());
	}
}
