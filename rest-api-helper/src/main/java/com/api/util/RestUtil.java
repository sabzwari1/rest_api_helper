/**
 * 
 */
package com.api.util;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author sss033
 *
 */
public class RestUtil {

	private static final String _Id = "Id";
	private static final String BRANDNAME = "BrandName";
	private static final String FEATURES = "Features";
	private static final String FEATURE = "Feature";
	private static final String LAPTOPNAME = "LaptopName";
	public static Object getJsonBody;

	private static int getRandomId() {
		return (int) (1000 * (Math.random()));
	}

	public static String getJsonBody(String brandName, Integer id, String laptopName, List<String> feature) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(BRANDNAME, brandName);
		jsonObject.addProperty(_Id, id == null ? getRandomId() + "" : id + "");
		JsonObject featuresObject = new JsonObject(); //feature child object
		JsonArray featureArrayObject = getJsonArray(feature); //Converting the string into a Json array
		// Add Feature array to Features Object
		featuresObject.add(FEATURE, featureArrayObject);
		//Add Features to Feature JsonObject 
		jsonObject.add(FEATURES, featuresObject);
		jsonObject.addProperty(LAPTOPNAME, laptopName);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	private static JsonArray getJsonArray(List<String> feature) {
		JsonArray featureArrayObject = new JsonArray();
		// Add all list feature to feature object
		for (String jsonElement : feature) {
			featureArrayObject.add(jsonElement);
		}
		return featureArrayObject;
	}
	
	@Test
	public void test(){
		String[] data = {"i7 8800k", "16GB RAM"};
		System.out.println(RestUtil.getJsonBody("Dell", null, "Latitude",Arrays.asList(data) ));
	}

}
