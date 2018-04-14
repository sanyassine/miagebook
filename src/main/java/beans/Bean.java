package beans;

import java.sql.Timestamp;
import java.util.Date;

public abstract class Bean {
	protected boolean inserted;
	public Bean() {
		updateLastChangeDate();
	}
	
	
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
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
