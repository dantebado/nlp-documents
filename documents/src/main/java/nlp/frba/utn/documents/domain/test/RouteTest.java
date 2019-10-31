package nlp.frba.utn.documents.domain.test;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RouteTest {
	
	String verb;
	String route;
	String controller;
	String reqBody;
	List<String> reqHeaders = new ArrayList<String>();
	
	int status;
	List<String> resHeaders = new ArrayList<String>();
	String resBody;
	
	public RouteTest(String verb, String route, String controller, String reqBody, List<String> reqHeaders, int status, List<String> resHeaders, String resBody) {
		this.verb = verb;
		this.route = route;
		this.controller = controller;
		this.reqBody = reqBody;
		this.reqHeaders = reqHeaders;
		this.status = status;
		this.resHeaders = resHeaders;
		this.resBody = resBody;
	}
	
	private static Gson gson = null;
	private static Gson getGson() {
		if(gson == null) {
			gson = new GsonBuilder().setPrettyPrinting().create();
		}
		
		return gson;
	}

}
