package utilities;

import java.io.Serializable;
import java.util.Date;

public class Termin implements Serializable {
	
	private int terminId;
	private Date terminDate;
	private String terminName;
	
	public Termin() {
		super();
		terminId = 0;
		terminDate = null;
		terminName = "";
	}

	public int getTerminId() {
		return terminId;
	}

	public void setTerminId(int terminId) {
		this.terminId = terminId;
	}

	public Date getTerminDate() {
		return terminDate;
	}

	public void setTerminDate(Date terminDate) {
		this.terminDate = terminDate;
	}

	public String getTerminName() {
		return terminName;
	}

	public void setTerminName(String terminName) {
		this.terminName = terminName;
	}

}
