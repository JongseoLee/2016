package com.msc.adams.automation.core.database;

public class DatabaseStep4 {
	
	public static final String GSTIFF = "GSTIFF";
	public static final String WSTIFF = "WSTIFF";
	public static final String I3 = "I3";
	public static final String SI2 = "SI2";
	public static final String Original = "Original";
	public static final String Modified = "Modified";
	public static final String ErrorValue = "1.0E-003";
	public static final String HmaxValue = "(NONE)";
	public static final String NumberOfStepValue = "3000";
	public static final String EndTimeValue = "3";
	public static final String ExtraMassValue = "0";
	public static final String ExtraMassRatioValue = "1";
	public static final String NumberOfCyclesValue = "2";
	public static final String StartTimeRangeValue = "0.0";
	public static final String EndTimeRangeValue = "3";
	public static final String IncrementFrameValue = "1";
		
	private String AdamsSolving;
	private String ExportModel;
	private String ExportDAC;
	private String ExportModelBin;
	private String ExportForce;

	private String Integrator;
	private String Formulation;
	private String Corrector;
	private String Error;
	private String hmax;
	
	private String NubmerOfStep;
	private String EndTime;
	private boolean isExtraMassOn;
	private String ExtraMass;
	private String ExtraMassRatio;
	
	private String NumberOfCycles;
	private String StartTimeRange;
	private String EndTimeRange;
	private String IncrementFrame;
	
	private String ExportResultName;
	
	private boolean FirstRun;
	
	public DatabaseStep4() {
		// TODO Auto-generated constructor stub
		this.AdamsSolving = "false";
		this.ExportModel = "true";
		this.ExportDAC = "true";
		this.ExportModelBin = "true";
		this.ExportForce = "true";
		
		this.Integrator = this.GSTIFF;
		this.Formulation = this.I3;
		this.Corrector = this.Original;
		this.Error = "1.0E-003";
		this.hmax = "(None)";
		
		this.NubmerOfStep = "3000";
		this.EndTime = "3";
		this.isExtraMassOn = false;
		this.ExtraMass = "0";
		this.ExtraMassRatio = "1";
		
		this.NumberOfCycles = "2";
		this.StartTimeRange = "0.0";
		this.EndTimeRange = "3";
		this.IncrementFrame = "1";
		
		this.FirstRun = true;
	}
	
	
	
	
	

	public String getAdamsSolving() {
		return AdamsSolving;
	}

	public void setAdamsSolving(String adamsSolving) {
		AdamsSolving = adamsSolving;
	}

	public String getExportModel() {
		return ExportModel;
	}

	public void setExportModel(String exportModel) {
		ExportModel = exportModel;
	}

	public String getExportDAC() {
		return ExportDAC;
	}

	public void setExportDAC(String exportDAC) {
		ExportDAC = exportDAC;
	}

	public String getExportModelBin() {
		return ExportModelBin;
	}
	
	public void setExportModelBin(String exportModelBin) {
		ExportModelBin = exportModelBin;
	}

	public String getExportForce() {
		return ExportForce;
	}

	public void setExportForce(String exportForce) {
		ExportForce = exportForce;
	}

	public String getIntegrator() {
		return Integrator;
	}

	public void setIntegrator(String integrator) {
		Integrator = integrator;
	}

	public String getFormulation() {
		return Formulation;
	}

	public void setFormulation(String formulation) {
		Formulation = formulation;
	}

	public String getCorrector() {
		return Corrector;
	}

	public void setCorrector(String corrector) {
		Corrector = corrector;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getHmax() {
		return hmax;
	}

	public void setHmax(String hmax) {
		this.hmax = hmax;
	}
	
	public String getNubmerOfStep() {
		return NubmerOfStep;
	}

	public void setNubmerOfStep(String nubmerOfStep) {
		NubmerOfStep = nubmerOfStep;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	/**
	 * @return the isExtraMassOn
	 */
	public boolean isExtraMassOn() {
		return isExtraMassOn;
	}






	/**
	 * @param isExtraMassOn the isExtraMassOn to set
	 */
	public void setExtraMassOn(boolean isExtraMassOn) {
		this.isExtraMassOn = isExtraMassOn;
	}






	/**
	 * @return the extraMass
	 */
	public String getExtraMass() {
		return ExtraMass;
	}






	/**
	 * @param extraMass the extraMass to set
	 */
	public void setExtraMass(String extraMass) {
		ExtraMass = extraMass;
	}






	/**
	 * @return the extraMassRatio
	 */
	public String getExtraMassRatio() {
		return ExtraMassRatio;
	}






	/**
	 * @param extraMassRatio the extraMassRatio to set
	 */
	public void setExtraMassRatio(String extraMassRatio) {
		ExtraMassRatio = extraMassRatio;
	}






	/**
	 * @return the numberOfCycles
	 */
	public String getNumberOfCycles() {
		return NumberOfCycles;
	}






	/**
	 * @param numberOfCycles the numberOfCycles to set
	 */
	public void setNumberOfCycles(String numberOfCycles) {
		NumberOfCycles = numberOfCycles;
	}






	/**
	 * @return the startTimeRange
	 */
	public String getStartTimeRange() {
		return StartTimeRange;
	}






	/**
	 * @param startTimeRange the startTimeRange to set
	 */
	public void setStartTimeRange(String startTimeRange) {
		StartTimeRange = startTimeRange;
	}






	/**
	 * @return the endTimeRange
	 */
	public String getEndTimeRange() {
		return EndTimeRange;
	}






	/**
	 * @param endTimeRange the endTimeRange to set
	 */
	public void setEndTimeRange(String endTimeRange) {
		EndTimeRange = endTimeRange;
	}






	/**
	 * @return the incrementFrame
	 */
	public String getIncrementFrame() {
		return IncrementFrame;
	}






	/**
	 * @param incrementFrame the incrementFrame to set
	 */
	public void setIncrementFrame(String incrementFrame) {
		IncrementFrame = incrementFrame;
	}






	public String getExportResultName() {
		return ExportResultName;
	}

	public void setExportResultName(String exportResultName) {
		ExportResultName = exportResultName;
	}






	public boolean isFirstRun() {
		return FirstRun;
	}






	public void setFirstRun(boolean firstRun) {
		FirstRun = firstRun;
	}

}
