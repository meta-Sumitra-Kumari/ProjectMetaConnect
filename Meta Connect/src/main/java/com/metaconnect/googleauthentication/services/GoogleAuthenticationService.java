package com.metaconnect.googleauthentication.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.metaconnect.googleauthentication.model.GooglePojo;

import com.metaconnect.user.model.User;
import com.metaconnect.user.service.UserService;


/**
 * @author MetaConnect
 *
 */
@Service
public class GoogleAuthenticationService {

	@Autowired
	UserService userServiceImpl;
	
	/**
	 * @param code the code which google send when a token is generated
	 * @return
	 */
	@Transactional
	public User GoogleAuthenticationServices(String code) {
		String urlParameters = GenerateUrlParameters(code);
		String token = GetAcessToken(urlParameters);
		String jsonToken = ConvertingTokenIntoJson(token);
		String userInformation = GetUserInformation(jsonToken);
		User user = covertingJsonIntoUserObject(userInformation);
		user=userServiceImpl.add(user);
        return user;
	}

	private String GenerateUrlParameters(String code) {
		String urlParameters = "code="
				+ code
				+ "&client_id=60843522167-mmj77k1qjg2iiqe6c3hqrnfged0ds5af.apps.googleusercontent.com"
				+ "&client_secret=lP0iNaagNRd2FklweXX6XC9k"
				+ "&redirect_uri=http://localhost:8080/MetaConnect/OAuth2"
				+ "&grant_type=authorization_code";
		return urlParameters;
	}

	private String ConvertingTokenIntoJson(String token) {
		JsonObject json = (JsonObject) new JsonParser().parse(token);
		String accessToken = json.get("access_token").getAsString();
		return accessToken;
	}

	private String GetUserInformation(String token) {
		URL url;
		String outputString = "", line;
		try {
			url = new URL(
					"https://www.googleapis.com/oauth2/v1/userinfo?access_token="
							+ token);
			URLConnection urlConn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputString;
	}

	private String GetAcessToken(String urlParameters) {

		URL url;
		String line, token = "";
		try {
			url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(
					urlConn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();

			// get output in outputString

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				token += line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;

	}

	private User covertingJsonIntoUserObject(String outputString) {
		GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
		User user = new User();
		user.setEmail(data.getEmail());
		user.setFirstName(data.getGiven_name());
		user.setLastName(data.getFamily_name());
		user.setImagePath(data.getPicture());
		return user;

	}
}
