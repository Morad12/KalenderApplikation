package utilities;

import java.io.Serializable;
import java.util.Date;

public class Termin implements Serializable {
	
	private int terminId;
	private String terminDate;
	private String terminTime;
	private String terminName;
	
	public Termin() {
		super();
		this.terminId = 0;
		this.terminDate = "";
		this.terminTime = "";
		this.terminName = "";
	}

	public int getTerminId() {
		return terminId;
	}

	public void setTerminId(int terminId) {
		this.terminId = terminId;
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

}
