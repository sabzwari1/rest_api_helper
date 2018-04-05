#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Add a laptop with details to laptop bag

  @tag1
  Scenario: Add laptop details
    Given Accept the contents in JSON format
    And Content type as JSON
    When I perform the POST request with BrandName as "Dell", Features as "i7 8800k, 16GB RAM", LaptopName as "Latitude"
    Then Status code "200" should return
    And Response should have integer id

  @tag1
  Scenario: Add laptop details with transform data
    Given Accept the contents in JSON format
   And Content type as JSON
   When I perform the POST request with details "Dell, i7 8800k, 16GB RAM, 13.3 inch, Latitude"
   Then Status code "200" should return
   And Response should have integer id

  @tag2
  Scenario: Post the details with invalid json body
    Given Accept the contents in JSON format
    And Content type as JSON
    But I supply invalid json body
    When I perfom the post request
    Then Status code "400" should return
