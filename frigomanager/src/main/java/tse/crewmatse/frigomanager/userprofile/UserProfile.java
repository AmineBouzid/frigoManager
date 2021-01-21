package tse.crewmatse.frigomanager.userprofile;

public class UserProfile {
	
	//Attributes List
	private double userWeight;
	private double userHeight;
	private String username;
	private String userFirstName;
	private String userLastName;
	private String userGender;
	private double userBMI;
	private double idealWeightMin;
	private double idealWeightMax;
	private boolean healthyMode;
	private String birthDate;
	private int userId;

	
	//Getters & Setters List
	/**
	 * @return Integer 'Unique Id of a user'
	 */
	public int getuserId() {
		return userId;
	}
	/**
	 * @param userId : Integer 'Unique Id of a user'
	 */
	public void setuserId(int userId) {
		this.userId=userId;
	}
	/**
	 * @return double 'Weight of the user in kilograms'
	 */
	public double getUserWeight() {
		return userWeight;
	}
	/**
	 * @param userWeight : double 'Weight of the user in kilograms'
	 */
	public void setUserWeight(double userWeight) {
		//if(userWeight>0) 
		this.userWeight = userWeight;
		this.calculateBMI();
	}
	/**
	 * @return double 'Height of the user in meters'
	 */
	public double getUserHeight() {
		return userHeight;
	}
	/**
	 * @param userHeight : double 'Height of the user in meters'
	 */
	public void setUserHeight(double userHeight) {
		//if(userHeight>0) 
		this.userHeight = userHeight;
		this.calculateBMI();
		this.calculateIdealWeight();
	}
	/**
	 * @return String 'Date of birth of the user'
	 */
	public String getbirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate : String 'Date of birth of the user'
	 */
	public void setbirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return String 'Gender of the user'
	 */
	public String getuserGender() {
		return userGender;
	}
	/**
	 * @param userGender : String 'Gender of the user'
	 */
	public void setuserGender(String userGender) {
		this.userGender = userGender;
	}
	/**
	 * @return String 'Alias of the user'
	 */
	public String getusername() {
		return username;
	}
	/**
	 * @param username : String 'Alias of the user'
	 */
	public void setusername(String username) {
		this.username = username;
	}
	/**
	 * @return String 'First name of the user'
	 */
	public String getUserFirstName() {
		return userFirstName;
	}
	/**
	 * @param userFirstName : String 'First name of the user'
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	/**
	 * @return String 'Last name of the user'
	 */
	public String getUserLastName() {
		return userLastName;
	}
	/**
	 * @param userLastName : String 'Last name of the user'
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	/**
	 * @return double 'BMI calculated from the Weight & Height of the user'
	 */
	public double getUserBMI() {
		return userBMI;
	}
	/**
	 * @param userBMI : double 'BMI calculated from the Weight & Height of the user'
	 */
	public void setUserBMI(double userBMI) {
		this.userBMI = userBMI;
	}
	/**
	 * @return double 'Calculated weight of the user with a BMI of 18.5'
	 */
	public double getIdealWeightMin() {
		return idealWeightMin;
	}
	/**
	 * @param idealWeightMin : double 'Calculated weight of the user with a BMI of 18.5'
	 */
	public void setIdealWeightMin(double idealWeightMin) {
		this.idealWeightMin = idealWeightMin;
	}
	/**
	 * @return double 'Calculated weight of the user with a BMI of 24.9'
	 */
	public double getIdealWeightMax() {
		return idealWeightMax;
	}
	/**
	 * @param idealWeightMax : double 'Calculated weight of the user with a BMI of 24.9'
	 */
	public void setIdealWeightMax(double idealWeightMax) {
		this.idealWeightMax = idealWeightMax;
	}
	/**
	 * @return boolean 'Enable/Disable the healthy mode'
	 */
	public boolean getHealthyMode() {
		return healthyMode;
	}
	/**
	 * @param healthyMode : boolean 'Enable/Disable the healthy mode'
	 */
	public void setHealthyMode(boolean healthyMode) {
		this.healthyMode = healthyMode;
	}
	
	
	//Constructor
	/**
	 * Constructor of the class with all the different inputs
	 * @param userWeight : double 'Weight of the user in kilograms'
	 * @param userHeight : double 'Height of the user in meters'
	 * @param username : String 'Alias of the user'
	 * @param userFirstName : String 'First name of the user'
	 * @param userLastName : String 'Last name of the user'
	 * @param userGender : String 'Gender of the user'
	 * @param healthyMode : boolean 'Enable/Disable the healthy mode'
	 * @param birthDate : String 'Date of birth of the user'
	 * @param userId : Integer 'Unique Id of a user'
	 * 
	 * <br>Calculates automatically the BMI and the ideal weights
	 */
	public UserProfile(double userWeight, double userHeight,String username, String userFirstName, String userLastName, String userGender,
			boolean healthyMode, String birthDate, int userId) {
		this.username = username;
		this.userWeight = userWeight;
		this.userHeight = userHeight;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userGender = userGender;
		this.healthyMode = healthyMode;
		this.birthDate = birthDate;
		this.userId = userId;
		this.calculateBMI();
		this.calculateIdealWeight();
	}
	

	/**
	 *  Function (called by the constructor) that calculates the BMI of the user
	 * <br>Formula : BMI = (m/(h*h))
	 */
	public void calculateBMI() {
		double tempBMI=this.getUserWeight()/(((this.getUserHeight())/100)*((this.getUserHeight()))/100);
		this.setUserBMI(setDecimals(1,tempBMI));
	}
	/**
	  * Function (called by the constructor) that calculates the "ideal" weight interval
	 * <br>Minimal weight for a BMI of 18.5
	 * <br>Maximal weight for a BMI of 25 
	 */
	public void calculateIdealWeight() {
		double MinWeight,MaxWeight;
		MinWeight=18.5*(((this.getUserHeight())/100)*((this.getUserHeight())/100));
		MaxWeight=25*(((this.getUserHeight())/100)*((this.getUserHeight())/100));
		this.setIdealWeightMin(setDecimals(2,MinWeight));
		this.setIdealWeightMax(setDecimals(2,MaxWeight));
	}
	 /**
	  * Function that rounds a double
	 * @param DecimalNumber : integer 'Number of digits after the dot'
	 * @param Number : double 'The number you need to round'
	 * @return double 'The rounded number'
	 */
	static double setDecimals(int DecimalNumber,double Number) {
		double temp=Number;
		double powtemp=Math.pow(10,DecimalNumber);
		temp=(powtemp*Number);
		temp=(int)temp;
		temp=temp/powtemp;
		return temp;	
	}

}
