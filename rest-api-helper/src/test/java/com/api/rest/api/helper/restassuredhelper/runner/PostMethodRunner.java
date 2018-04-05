/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author sss033
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false, monochrome = true, features = "src/test/java/com/api/rest/api/helper/restassuredhelper/featurefile", glue = "com.api.rest.api.helper.restassuredhelper.stepdefination")
public class PostMethodRunner {

}
