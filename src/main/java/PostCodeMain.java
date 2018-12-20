import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class PostCodeMain extends CommonFunction {

	private String baseURI = "https://api.postcodes.io";
	private String randomURI = "/random/postcodes";
	private String postURI = "/postcodes";
	private String getURIpostcode = "/postcodes/%%postcodes%%";
	private String getURIQuery = "/postcodes?q=%%postcodes%%";
	private String randompostCodeResponse = null;
	private String postCode = "";

	/**
	 * To get random postCode
	 *
	 */
	public String getRandomPostcode() throws ClientProtocolException, IOException {
		String randomBaseURI = baseURI + randomURI;
		randompostCodeResponse = sendGetRequest(randomBaseURI);
		// System.out.print(randompostCodeResponse.toString());
		postCode = (jsonReuse(randompostCodeResponse, "result.postcode"));
		if (!postCode.isEmpty()) {
			postCode = postCode.replaceAll("\\s", "");
			postCode = postCode.substring(1, postCode.length() - 1);
		}
		return randompostCodeResponse.toString();
	}

	/**
	 * Post api call Endpoint 1
	 */
	public String postApiPostCode(String longitude, String latitude, String flag)
			throws ClientProtocolException, IOException {
		String PostbaseURI = baseURI + postURI;
		if (flag.equals("positive")) {

			randompostCodeResponse = sendPostRequest(PostbaseURI, sampleJsonBody(longitude, latitude));
		} else if (flag.equals("Uri")) {
			randompostCodeResponse = sendPostRequest(baseURI, sampleJsonBody(longitude, latitude));
		} else if (flag.equals("Random")) {
			randompostCodeResponse = sendPostRequest(baseURI, sampleJsonBody(longitude, latitude));
		} else if (flag.equals("Nobody")) {
			randompostCodeResponse = sendPostRequest(PostbaseURI, " ");
		}
		System.out.print(randompostCodeResponse.toString());
		return randompostCodeResponse.toString();
	}

	/**
	 * Get query call Endpoint 2
	 */
	public String getQueryDetailPostcode(String flag) throws ClientProtocolException, IOException {
		String getQuerybaseURI = baseURI + getURIQuery;
		getQuerybaseURI = getQuerybaseURI.replaceAll("%%postcodes%%", postCode);
		if (flag.equals("positive")) {
			randompostCodeResponse = sendGetRequest(getQuerybaseURI);
		} else if (flag.equals("uri")) {
			randompostCodeResponse = sendGetRequest(getQuerybaseURI.replaceAll("\\?", ""));
		} else if (flag.equals("Postcode")) {
			randompostCodeResponse = sendGetRequest(getQuerybaseURI + "abc");
		}
		System.out.print(randompostCodeResponse.toString());
		return randompostCodeResponse.toString();
	}

	/**
	 * Get Inline call Endpoint 3
	 */
	public String getInlinePostcode(String flag) throws ClientProtocolException, IOException {
		String inLinebaseURI = baseURI + getURIpostcode;
		inLinebaseURI = inLinebaseURI.replaceAll("%%postcodes%%", postCode);
		if (flag.equals("positive")) {
			randompostCodeResponse = sendGetRequest(inLinebaseURI);
		} else if (flag.equals("uri")) {
			randompostCodeResponse = sendGetRequest(inLinebaseURI + "/invalid");
		} else if (flag.equals("Postcode")) {
			randompostCodeResponse = sendGetRequest(inLinebaseURI + "abc");
		}
		System.out.print(randompostCodeResponse.toString());
		return randompostCodeResponse.toString();
	}
}
