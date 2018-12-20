import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PostCodePostApiTest extends CommonFunction {

	PostCodeMain postCodeMain = new PostCodeMain();

	private String longiRandom = null;
	private String latitudeRandom = null;
	private String postcodeRandom = null;
	private String countryRandom = null;

	@Before
	public void setUp() throws ClientProtocolException, IOException {
		String response = postCodeMain.getRandomPostcode();

		longiRandom = jsonReuse(response, "result.longitude");
		latitudeRandom = jsonReuse(response, "result.latitude");
		postcodeRandom = jsonReuse(response, "result.postcode");
		countryRandom = jsonReuse(response, "result.country");

	}

	@Test
	@DisplayName("Response Validation -Status 200 Success")
	public void postApiPostCodeStatus() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode(this.longiRandom,this.latitudeRandom, "positive");
		String status = jsonReuse(response, "status");
		Assert.assertEquals(status, "200");

	}

	@Test
	@DisplayName("Response Validation")
	public void postApiPostCodeQuery() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode(this.longiRandom,this.latitudeRandom, "positive");
		String longitude = jsonReuse(response, "result[].query.longitude");
		Assert.assertEquals(longitude, this.longiRandom);
		String latitude = jsonReuse(response, "result[].query.latitude");
		Assert.assertEquals(latitude, this.latitudeRandom);

	}

	@Test
	@DisplayName("Wrong BaseURL validation")
	public void postApiPostCodeWrongUri() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode(this.longiRandom, this.latitudeRandom, "Uri");

		Assert.assertEquals(jsonReuse(response, "status"), "404");

		Assert.assertEquals(jsonReuse(response, "error"), "\"Resource not found\"");
	}

	@Test
	@DisplayName("Incorrect Body passed")
	public void postApiPostCodeInCorrectBody() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode("longitude", this.latitudeRandom, "positive");
		String status = jsonReuse(response, "status");
		Assert.assertEquals(status, "400");
	}

	@Test
	@DisplayName("Random Body passed")
	public void postApiPostCodeRandomBody() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode("900000", "800", "Random");
		String status = jsonReuse(response, "status");
		Assert.assertEquals(status, "404");
	}

	@Test
	@DisplayName("No Body passed")
	public void postApiPostCodeNoBody() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode(" ", " ", "Nobody");
		String status = jsonReuse(response, "status");
		Assert.assertEquals(status, "400");
	}
	

	@Test
	@DisplayName("Response Validation - Longitude Latitude")
	public void getQueryDetailPostcodeValidate() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode(this.longiRandom, this.latitudeRandom, "positive");
		
		Assert.assertEquals(jsonReuse(response, "result[].result[].longitude"), longiRandom);
		Assert.assertEquals(jsonReuse(response, "result[].result[].latitude"), latitudeRandom);
	}
	@Test
	@DisplayName("Response Validation - PostCode Country")
	public void getQueryDetailPostcodeValidation() throws ClientProtocolException, IOException {
		String response = postCodeMain.postApiPostCode(this.longiRandom, this.latitudeRandom, "positive");
		Assert.assertEquals(jsonReuse(response, "result[].result[].postcode"), postcodeRandom);
		Assert.assertEquals(jsonReuse(response, "result[].result[].country"), countryRandom);
		
	}
}
