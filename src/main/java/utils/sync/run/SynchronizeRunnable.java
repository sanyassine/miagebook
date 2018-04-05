package utils.sync.run;

import java.sql.Timestamp;

import beans.Bean;
import persistence.DataMapper;

public abstract class SynchronizeRunnable implements Runnable{
	protected Bean object;
	protected DataMapper mapper;
	public SynchronizeRunnable(Bean obj, DataMapper mapper) {
		object = obj;
		this.mapper = mapper;
	}
	public void run() {
		Bean newObject = findNewVersion();
		if(newObject != null && equalsObject(object,newObject)) {
			Timestamp lastChangeCurrent = object.getLastChangeDate();
			Timestamp lastChangeNew     = newObject.getLastChangeDate();
			if(lastChangeCurrent.before(lastChangeNew)) {
				// mettre a jour le post courant en y mettant les attribut du nouveau
			}
		}
	}
	public abstract Bean findNewVersion();
	
	public abstract boolean equalsObject(Bean b1, Bean b2);
}
