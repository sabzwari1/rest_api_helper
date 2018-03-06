/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sss033
 *
 */

@XmlRootElement(name = "Laptop")
public class Model_Laptop {

	private String BrandName;
	private String Id;
	private String LaptopName;
	private Features Features;

	/**
	 * @return the brandName
	 */
	@XmlElement(name = "BrandName")
	public String getBrandName() {
		return BrandName;
	}

	/**
	 * @param brandName
	 *            the brandName to set
	 */
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	/**
	 * @return the id
	 */
	@XmlElement(name = "Id")
	public String getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the laptopName
	 */
	@XmlElement(name = "LaptopName")
	public String getLaptopName() {
		return LaptopName;
	}

	/**
	 * @param laptopName
	 *            the laptopName to set
	 */
	public void setLaptopName(String laptopName) {
		LaptopName = laptopName;
	}

	/**
	 * @return the features
	 */
	@XmlElement(name = "Features")
	public Features getFeatures() {
		return Features;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(Features features) {
		Features = features;
	}

}
