package utilities;

import java.io.Serializable;
import java.util.Date;

public class Termin implements Serializable {
	
	private int terminId;
	private String terminInhaber;
	private String terminName;
	private String terminDate;
	private String terminTime;
	
	
	public Termin() {
		super();
		this.terminId = 0;
		this.terminInhaber = "";
		this.terminDate = "";
		this.terminTime = "";
		this.terminName = "";
	}
	
	public Termin(int terminId, String terminInhaber, String terminDate, String terminTime, String terminName) {
		super();
		this.terminId = terminId;
		this.terminInhaber = terminInhaber;
		this.terminName = terminName;
		this.terminDate = terminDate;
		this.terminTime = terminTime;		
	}

	public int getTerminId() {
		return terminId;
	}

	public void setTerminId(int terminId) {
		this.terminId = terminId;
	}

	public String getTerminInhaber() {
		return terminInhaber;
	}

	public void setTerminInhaber(String terminInhaber) {
		this.terminInhaber = terminInhaber;
	}

	public String getTerminDate() {
		return terminDate;
	}

	public void setTerminDate(String terminDate) {
		this.terminDate = terminDate;
	}

	public String getTerminName() {
		return terminName;
	}

	public void setTerminName(String terminName) {
		this.terminName = terminName;
	}

	public String getTerminTime() {
		return terminTime;
	}

	public void setTerminTime(String terminTime) {
		this.terminTime = terminTime;
	}
	
	@Override
	public String toString() {
		return "Termin [getTerminId()=" + getTerminId() + ", getTerminDate()=" + getTerminDate() + ", getTerminName()="
				+ getTerminName() + ", getTerminTime()=" + getTerminTime() + "]";
	}
}
