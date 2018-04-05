/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper.transform;


import java.util.Arrays;
import java.util.List;

import cucumber.api.Transformer;

/**
 * @author sss033
 *
 */
public class TransformData extends Transformer<List<String>>{
	@Override
	public List<String> transform(String value) {
		
		String[] data=value.split(",");
		List<String> convertedValue=Arrays.asList(data);
		return convertedValue;
	}

}
