package tse.crewmatse.frigomanager.userprofile;


public class UserProfile {
	
	private double userWeight;
	private double userHeight;
	private String userFirstName;
	private String userLastName;
	private double userBMI;
	private double idealWeightMin;
	private double idealWeightMax;
	private boolean healthyMode;
/*
 * tableau de recettes epinglees
 * tableau recettes favorites
 * 
 */
	//----------------------------------------Getters & Setters
	public double getUserWeight() {
		return userWeight;
	}
	public void setUserWeight(double userWeight) { //input in kilograms
		//if(userWeight>0) 
		this.userWeight = userWeight;
		this.calculateBMI();
	}
	public double getUserHeight() {
		return userHeight;
	}
	public void setUserHeight(double userHeight) { //input in meters
		//if(userHeight>0) 
		this.userHeight = userHeight;
		this.calculateBMI();
		this.calculateIdealWeight();
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public double getUserBMI() {
		return userBMI;
	}
	public void setUserBMI(double userBMI) {
		this.userBMI = userBMI;
	}
	public double getIdealWeightMin() {
		return idealWeightMin;
	}
	public void setIdealWeightMin(double idealWeightMin) {
		this.idealWeightMin = idealWeightMin;
	}
	public double getIdealWeightMax() {
		return idealWeightMax;
	}
	public void setIdealWeightMax(double idealWeightMax) {
		this.idealWeightMax = idealWeightMax;
	}
	public boolean getHealthyMode() {
		return healthyMode;
	}
	public void setHealthyMode(boolean healthyMode) {
		this.healthyMode = healthyMode;
	}
	//Getters & Setters----------------------------------------
	
	//----------------------------------------Constructor
	public UserProfile(double userWeight, double userHeight, String userFirstName, String userLastName,
			boolean healthyMode) {
		//super();
		this.userWeight = userWeight;
		this.userHeight = userHeight;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.healthyMode = healthyMode;
		this.calculateBMI();
		this.calculateIdealWeight();
	}
	//Constructor----------------------------------------
	
	
	//----------------------------------------BMI Functions
	public void calculateBMI() {
		//bmi = (kg/m�)
		double tempBMI=this.getUserWeight()/(((this.getUserHeight())/100)*((this.getUserHeight()))/100);
		this.setUserBMI(setDecimals(1,tempBMI));
	}
	
	public void calculateIdealWeight() {
		//Ideal BMI between 18.5 & 25
		//p=bmi*h�
		double MinWeight,MaxWeight;
		MinWeight=18.5*(((this.getUserHeight())/100)*((this.getUserHeight())/100));
		MaxWeight=25*(((this.getUserHeight())/100)*((this.getUserHeight())/100));
		this.setIdealWeightMin(setDecimals(2,MinWeight));
		this.setIdealWeightMax(setDecimals(2,MaxWeight));
	}
	//BMI Functions----------------------------------------
	
	 static double setDecimals(int DecimalNumber,double Number) {
		double temp=Number;
		double powtemp=Math.pow(10,DecimalNumber);
		temp=(powtemp*Number);
		temp=(int)temp;
		temp=temp/powtemp;
		return temp;	
	}

}
