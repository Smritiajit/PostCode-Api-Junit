import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author User
 *
 */
public class CommonFunction {

	/**
	 * Get Request method
	 *
	 */
	public String sendGetRequest(String baseUri) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(baseUri);

		// add request header
		request.addHeader("Accept", "application/json");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("User-Agent", "");
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		return responseString;
	}

	/**
	 * Post Request method
	 *
	 */
	public String sendPostRequest(String baseUri, String bodyRequest) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(baseUri);

		// add request header Content-Type
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("User-Agent", "");
		StringEntity entitybody = new StringEntity(bodyRequest);
		post.setEntity(entitybody);
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		return responseString;
	}

	/**
	 * Json value fetcher
	 *
	 */
	public String jsonReuse(String jsonLine, String key) {
		String[] arrKey = new String[10];
		String returnVal = "";
		JsonElement jelement = new JsonParser().parse(jsonLine);
		JsonObject jobject = jelement.getAsJsonObject();
		
		if (key.contains(".")) {
			arrKey = key.split("\\.");
			for (int i = 0; i < arrKey.length - 1; i++) {
				if (arrKey[i].contains("[]")) 
				{
					//JsonArray jobjectArr = jelement.getAsJsonArray();
					JsonArray jobjectArr = jobject.getAsJsonArray(arrKey[i].substring(0, arrKey[i].length()-2));
					jobject = jobjectArr.get(0).getAsJsonObject();
				}
				else
				{
				jobject = jobject.getAsJsonObject(arrKey[i]);
				}
			}
			returnVal = jobject.get(arrKey[arrKey.length - 1]).toString();
		} else {
			returnVal = jobject.get(key).toString();
		}
		return returnVal;

	}

	/**
	 * Post request body
	 *
	 */
	public String sampleJsonBody(String longitude, String latitude) {

		String jsonBody = "{\"geolocations\":[{\"longitude\": " + longitude + "," + "\"latitude\":" + latitude + "}]}";
		return jsonBody;
	}
}
