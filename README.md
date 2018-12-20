Please write test(s) that will cover the following endpoint's

POST api.postcodes.io/postcodes
GET api.postcodes.io/postcodes?q=:postcode
GET api.postcodes.io/postcodes/:postcode

If in Need Of any Valid Post Codes please use this Endpoint:
GET api.postcodes.io/random/postcodes

With required Documentation here: http://postcodes.io/docs

1. Your tests should contain positive and negative scenarios for endpoint(s).
2. Also tests should include Data Validations for Response, in path and Query Params 
3. Tests should be Written in java utilising JUNIT5 framework and Below mentioned Libraries 
	i. Apache Http Client to make requests
	ii. Should Use Google's GSON library for data serialisation
	iii. Hamcrest Library for descriptive assertions (Optional)
  
  All the Junit test are present in Test folder. Different Java test files are there for different Endpoints along with TestSuite.
