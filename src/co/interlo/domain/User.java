package co.interlo.domain;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class User {

	public ParseUser parseUser;
	
	public int gender;
	
	public User(){
		parseUser = ParseUser.getCurrentUser();
	}

	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;

	public int study;

	public static final int STUDY_ENGLISH = 1;
	public static final int STUDY_CHINESE = 2;

	public int getGender() {
		this.gender = parseUser.getInt("gender");
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
		parseUser.put("gender", gender);
		parseUser.saveInBackground();
	}

	public int getStudy() {
		this.study = parseUser.getInt("study");
		return study;
	}

	public void setStudy(int study) {
		this.study = study;
		parseUser.put("study", study);
		parseUser.saveInBackground();
	}

	public boolean hasGender() {
		return getGender() != 0;
	}

	public boolean hasStudy() {
		return getStudy() != 0;
	}
	
	
	public void signup(String username,String pass,String email,SignUpCallback callback){
		parseUser.setUsername(username);
		parseUser.setPassword(pass);
		parseUser.setEmail(email);
		parseUser.signUpInBackground(callback);
	}
	
	
	public void login(String username,String pass,LogInCallback callback){
		ParseUser.logInInBackground(username, pass, callback);
	}
}
