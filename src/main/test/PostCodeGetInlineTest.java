import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PostCodeGetInlineTest extends CommonFunction {
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
	@DisplayName("Status 200 check")
	public void getInlinePostcodeStatus() throws ClientProtocolException, IOException {
		String response = postCodeMain.getInlinePostcode("positive");
		String status = jsonReuse(response, "status");
		Assert.assertEquals(status, "200");
	}

	@Test
	@DisplayName("Wrong URL check")
	public void getQueryDetailPostcodeWrongUri() throws ClientProtocolException, IOException {
		String response = postCodeMain.getInlinePostcode("uri");

		Assert.assertEquals(jsonReuse(response, "status"), "404");
		Assert.assertEquals(jsonReuse(response, "error"), "\"Resource not found\"");
	}
	@Test
	@DisplayName("Wrong Postcode check")
	public void getQueryDetailPostcodeWrongCode() throws ClientProtocolException, IOException {
		String response = postCodeMain.getInlinePostcode("Postcode");

		Assert.assertEquals(jsonReuse(response, "status"), "404");
		Assert.assertEquals(jsonReuse(response, "error"), "\"Invalid postcode\"");
	}
	
	@Test
	@DisplayName("Response Validation")
	public void getQueryDetailPostcodeValidate() throws ClientProtocolException, IOException {
		String response = postCodeMain.getInlinePostcode("positive");
		Assert.assertEquals(jsonReuse(response, "result.postcode"), postcodeRandom);
		Assert.assertEquals(jsonReuse(response, "result.country"), countryRandom);
		Assert.assertEquals(jsonReuse(response, "result.longitude"), longiRandom);
		Assert.assertEquals(jsonReuse(response, "result.latitude"), latitudeRandom);
	}
}
