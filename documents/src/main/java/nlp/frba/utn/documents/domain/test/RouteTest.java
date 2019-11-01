package nlp.frba.utn.documents.domain.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RouteTest {
	
	String verb;
	String route;
	String controller;
	String reqType;
	HashMap<String, Object> reqBody;
	List<String> reqHeaders = new ArrayList<String>();
	
	int status;
	List<String> resHeaders = new ArrayList<String>();
	String resType;
	HashMap<String, Object> resBody;
	
	public RouteTest(String verb, String route, String controller, String reqBody, List<String> reqHeaders, int status, List<String> resHeaders, String resBody) {
		this.verb = verb;
		this.route = route;
		this.controller = controller;
		this.reqBody = getAsShowableJson(reqBody, "req");		
		this.reqHeaders = reqHeaders;
		this.status = status;
		this.resHeaders = resHeaders;
		this.resBody = getAsShowableJson(resBody, "res");
	}
	
	public static Gson gson = null;
	public static Gson getGson() {
		if(gson == null) {
			gson = new GsonBuilder().setPrettyPrinting().create();
		}
		
		return gson;
	}
	
	private HashMap<String, Object> getAsShowableJson(String s, String t) {
		HashMap<String, Object> r = null;
		try {
			r = mapObject(getGson().fromJson(s, JsonObject.class));
			if(t.equalsIgnoreCase("res")) {
				resType = "JSONObject";
			} else if(t.equalsIgnoreCase("req")) {
				reqType = "JSONObject";
			}
		} catch (com.google.gson.JsonSyntaxException e) {
			if(e.getMessage().equals("Expected a com.google.gson.JsonObject but was com.google.gson.JsonArray")) {
				r = new HashMap<String, Object>(); 
				r.put("ArrayValue", getAsParseableValue(getGson().fromJson(s, JsonArray.class)));
				if(t.equalsIgnoreCase("res")) {
					resType = "JSONArray";
				} else if(t.equalsIgnoreCase("req")) {
					reqType = "JSONArray";
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}
	
	private HashMap<String, Object> mapObject(JsonObject o) {
		HashMap<String, Object> r = new HashMap<String, Object>();
		
		if(o == null) return r;
		for(String k : o.keySet()) {
			r.put(k, getAsParseableValue(o.get(k)));
		}
		return r;
	}
	
	private Object getAsParseableValue(JsonElement e) {
		if(e.isJsonPrimitive()) {
			JsonPrimitive p = e.getAsJsonPrimitive();
			if(p.isNumber()) {
				return p.getAsNumber();
			} else if(p.isBoolean()) {
				return p.getAsBoolean();
			} else {
				return p.getAsString();
			}
		} else if (e.isJsonArray()) {
			JsonArray a = e.getAsJsonArray();
			List<Object> r = new ArrayList<Object>();
			a.forEach(me -> {
				r.add(getAsParseableValue(me));
			});
			return r;
		} else if (e.isJsonObject()) {
			return mapObject(e.getAsJsonObject());
		}
		return null;
	}

}
