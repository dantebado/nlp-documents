package nlp.frba.utn.documents.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
	
	private static Gson gson;
	
	public static Gson getGson() {
		if(gson == null) {
			gson = new GsonBuilder()
					.setPrettyPrinting()
					.serializeNulls()
					.create();
		}
		return gson;
	}

}
