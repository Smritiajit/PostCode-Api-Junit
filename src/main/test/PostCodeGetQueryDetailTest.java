import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PostCodeGetQueryDetailTest extends CommonFunction {

	PostCodeMain postCodeMain = new PostCodeMain();
	private String postcodeRandom = null;
	private String countryRandom = null;
	private String longiRandom = null;
	private String latitudeRandom = null;

	@Before
	public void setUp() throws ClientProtocolException, IOException {
		String response = postCodeMain.getRandomPostcode();
		postcodeRandom = jsonReuse(response, "result.postcode");
		countryRandom = jsonReuse(response, "result.country");
		longiRandom = jsonReuse(response, "result.longitude");
		latitudeRandom = jsonReuse(response, "result.latitude");

	}
	@Test
	@DisplayName("Statuc Code 200 Check - Success")
	public void getQueryDetailPostcodeStatus() throws ClientProtocolException, IOException {
		String response = postCodeMain.getQueryDetailPostcode("positive");
		String status = jsonReuse(response, "status");
		Assert.assertEquals(status, "200");
	}

	@Test
	@DisplayName("Wrong Base URL")
	public void getQueryDetailPostcodeWrongUri() throws ClientProtocolException, IOException {
		String response = postCodeMain.getQueryDetailPostcode("uri");

		Assert.assertEquals(jsonReuse(response, "status"), "404");
		Assert.assertEquals(jsonReuse(response, "error"), "\"Resource not found\"");
	}

	@Test
	@DisplayName("Wrong PostCode Check")
	public void getQueryDetailPostcodeWrongCode() throws ClientProtocolException, IOException {
		String response = postCodeMain.getQueryDetailPostcode("Postcode");

		Assert.assertEquals(jsonReuse(response, "status"), "200");
		Assert.assertEquals(jsonReuse(response, "result"), "null");
	}
	
	
	@Test
	@DisplayName("Response Query Validation")
	public void getQueryDetailPostcodeQueryValidate() throws ClientProtocolException, IOException {
		String response = postCodeMain.getQueryDetailPostcode("positive");
		Assert.assertEquals(jsonReuse(response, "result[].postcode"), postcodeRandom);
		Assert.assertEquals(jsonReuse(response, "result[].country"), countryRandom);
		Assert.assertEquals(jsonReuse(response, "result[].longitude"), longiRandom);
		Assert.assertEquals(jsonReuse(response, "result[].latitude"), latitudeRandom);
	}

}
