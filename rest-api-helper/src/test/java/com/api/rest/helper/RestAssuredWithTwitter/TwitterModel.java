/**
 * 
 */
package com.api.rest.helper.RestAssuredWithTwitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sss033
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class TwitterModel {

@JsonProperty("id")	
public long id;
@JsonProperty("id_str")
public String id_str;
	
	
	
}
