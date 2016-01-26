import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GrabAPIInfo {
	static final String SITE = "https://api.weathersource.com/v1/";
	static final String API_KEY = "1c1b6574bb1b22de6f63/";
	static final String GET_TYPE = "history_by_postal_code.";
	static final String FILE_TYPE = "json?";
	static final String DETAILS = "period=day&postal_code_eq=85383"
								+ "&country_eq=US"
								+ "&timestamp_eq=2012-02-10T00:00:00-05:00"
								+ "&fields=postal_code,country,timestamp,tempMax,tempAvg,tempMin";
	
	public static String giveData() throws IOException, JSONException {
		String url = SITE + API_KEY + GET_TYPE + FILE_TYPE + DETAILS;
		//IOUtils' 'toString' method gets content from a URI, stores data toString
		String source = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		//string now viewed as a JSON Array, 
		JSONArray json = new JSONArray(source);
		//Now we take the first JSONobject out of the jsonArray
		JSONObject jsonObj = json.getJSONObject(0);
	    System.out.println(jsonObj.toString());
	    //Let's say we wanted to get the tempMax value from this object...
	    //int max = (Integer) jsonObj.get("tempMax"); //NEEDS TO BE CASED!!!
	    //System.out.println(max);
	    //OR we can do it as
	    //System.out.println(jsonObj.getInt("tempMax"));
	    return "(" + jsonObj.getInt("postal_code") + ", '"
	    		+ jsonObj.getString("timestamp") + "', "
	    		+ jsonObj.getDouble("tempAvg") + ")";
	}
}
