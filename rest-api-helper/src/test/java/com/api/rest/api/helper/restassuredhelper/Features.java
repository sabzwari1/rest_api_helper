/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author sss033
 *
 */
public class Features {

	private List<String> Feature;

	
	public Features(){
		this.Feature = new ArrayList<String>();
	}
	/**
	 * @return the feature
	 */
	@XmlElement(name="Feature")
	public List<String> getFeature() {
		return Feature;
	}
	/**
	 * @param feature the feature to set
	 */
	public void setFeature(List<String> feature) {
		Feature = feature;
	}
	
	
}
