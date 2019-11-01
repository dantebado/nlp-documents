package nlp.frba.utn.documents.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import nlp.frba.utn.documents.domain.test.RouteTest;

/*@RestController
@RequestMapping({"/debug"})*/
public class DebugController {
	
	@Autowired
	private Environment env;

    @Autowired
    MongoTemplate mongoTemplate;

	@GetMapping(path = {"/populate"})
	public ResponseEntity<String> clearDatabaseAndPopulateSample() {
		for (String collectionName : mongoTemplate.getCollectionNames()) {
            if (!collectionName.startsWith("system.")) {
                mongoTemplate.getCollection(collectionName).drop();
            }
        }
		
		int port = Integer.parseInt(env.getProperty("server.port"));
		
		String id1 = "93243B72B81365B6D28D7CFF89DFE191572469839132";
		String id2 = "93243B72B81365B6D28D7CFF89DFE191572473130471";
		String id3 = "93243B72B81365B6D28D7CFF89DFE191572473165552";
		String id4 = "93243B72B81365B6D28D7CFF89DFE191572473169144";
		String id5 = "93243B72B81365B6D28D7CFF89DFE191572473173773";
		String id6 = "93243B72B81365B6D28D7CFF89DFE191572473178588";
		String id7 = "93243B72B81365B6D28D7CFF89DFE191572473182104";
		String id8 = "93243B72B81365B6D28D7CFF89DFE191572473185729";
		String id9 = "93243B72B81365B6D28D7CFF89DFE191572473188797";
		
		{
			JsonObject payload = new JsonObject();
			
			JsonObject student = new JsonObject();
			student.addProperty("fullname", "Marcelo Tinelli");
			student.addProperty("student_id", "1580849");
			JsonArray students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id1);
			payload.addProperty("subject", "Economia");
			payload.addProperty("year", 2017);
			payload.addProperty("quarter", "2");
			payload.addProperty("email", "mtinelli@eltrece.tv");
			payload.add("students", students);
			payload.addProperty("document_name", "tp_marce.pdf");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/tp_marce.pdf");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Susana Gimenez");
			student.addProperty("student_id", "1495029");
			students = new JsonArray();
			students.add(student);

			payload.addProperty("id", id2);
			payload.addProperty("email", "su@telefe.com");
			payload.add("students", students);
			payload.addProperty("document_name", "tp_su_final.pdf");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/tp_su_final.pdf");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Guido Kaczka");
			student.addProperty("student_id", "1609875");
			students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id3);
			payload.addProperty("email", "losescalones@eltrece.tv");
			payload.add("students", students);
			payload.addProperty("document_name", "larepe.pdf");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/larepe.pdf");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Moria Casan");
			student.addProperty("student_id", "1489501");
			students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id4);
			payload.addProperty("email", "moria@americatv.com.ar");
			payload.add("students", students);
			payload.addProperty("document_name", "el_tp_de_la_one.pdf");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/el_tp_de_la_one.pdf");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Diego Maradona");
			student.addProperty("student_id", "1578105");
			students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id5);
			payload.addProperty("email", "diego_10@gimnasia.org.ar");
			payload.add("students", students);
			payload.addProperty("document_name", "eeeeee_final.doc");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/eeeeee_final.doc");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Mauricio Macri");
			student.addProperty("student_id", "1578043");
			students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id6);
			payload.addProperty("email", "mau@jxc.com.ar");
			payload.add("students", students);
			payload.addProperty("document_name", "tp_inundado.pdf");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/tp_inundado.pdf");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Alberto Fernandez");
			student.addProperty("student_id", "1569807");
			students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id7);
			payload.addProperty("email", "alferdez@casarosada.gob.ar");
			payload.add("students", students);
			payload.addProperty("document_name", "tp_version_entre_todos.pdf");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/tp_version_entre_todos.pdf");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Horacio Rodriguez Larreta");
			student.addProperty("student_id", "1689870");
			students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id8);
			payload.addProperty("email", "horacio@buenosaires.gob.ar");
			payload.add("students", students);
			payload.addProperty("document_name", "hola_soy_horacio.doc");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/hola_soy_horacio.doc");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
			student = new JsonObject();
			student.addProperty("fullname", "Lionel Messi");
			student.addProperty("student_id", "1698078");
			students = new JsonArray();
			students.add(student);
			
			payload.addProperty("id", id9);
			payload.addProperty("email", "lio@fcbarcelona.es");
			payload.add("students", students);
			payload.addProperty("document_name", "aprobamos_por_goleada.pdf");
			payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/aprobamos_por_goleada.pdf");
			
			perform("http://localhost:" + port + "/documents", HttpMethod.POST, payload.toString(), String.class);
			
		}
		{
			perform("http://localhost:" + port + "/documents/" + id1 + "/ner/tags/main/economia", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id2 + "/ner/tags/main/economia", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id3 + "/ner/tags/main/proyecto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id4 + "/ner/tags/main/economia", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id5 + "/ner/tags/main/economia", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id6 + "/ner/tags/main/proyecto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id7 + "/ner/tags/main/proyecto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id8 + "/ner/tags/main/proyecto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id9 + "/ner/tags/main/economia", HttpMethod.POST, null, String.class);
		}
		{
			perform("http://localhost:" + port + "/documents/" + id1 + "/ner/tags/secondary/proyecto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id1 + "/ner/tags/secondary/gasto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id1 + "/ner/tags/secondary/presupuesto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id2 + "/ner/tags/secondary/gasto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id2 + "/ner/tags/secondary/pbi", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id2 + "/ner/tags/secondary/ventas", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id2 + "/ner/tags/secondary/proyecto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id3 + "/ner/tags/secondary/ventas", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id3 + "/ner/tags/secondary/economia", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id3 + "/ner/tags/secondary/gasto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id4 + "/ner/tags/secondary/maquinaria", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id4 + "/ner/tags/secondary/sueldo", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id4 + "/ner/tags/secondary/presupuesto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id5 + "/ner/tags/secondary/ventas", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id5 + "/ner/tags/secondary/maquinaria", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id5 + "/ner/tags/secondary/inversion", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id6 + "/ner/tags/secondary/proyecto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id6 + "/ner/tags/secondary/presupuesto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id6 + "/ner/tags/secondary/ventas", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id6 + "/ner/tags/secondary/sueldo", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id7 + "/ner/tags/secondary/maquinaria", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id7 + "/ner/tags/secondary/pbi", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id7 + "/ner/tags/secondary/gasto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id8 + "/ner/tags/secondary/presupuesto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id8 + "/ner/tags/secondary/inversion", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id8 + "/ner/tags/secondary/economia", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id8 + "/ner/tags/secondary/gasto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id8 + "/ner/tags/secondary/maquinaria", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id9 + "/ner/tags/secondary/presupuesto", HttpMethod.POST, null, String.class);
			perform("http://localhost:" + port + "/documents/" + id9 + "/ner/tags/secondary/ventas", HttpMethod.POST, null, String.class);
		}
		
		return ResponseEntity.ok().build();
	}

	/* *
	 * https://www.freeformatter.com/json-escape.html
	 * https://jsonformatter.curiousconcept.com/
	 * */
	
	@GetMapping(path = {"/testRoutes"})
	public ResponseEntity<List<RouteTest>> testRoutesAndGetResponses() {
		List<RouteTest> list = new ArrayList<RouteTest>();
		
		int port = Integer.parseInt(env.getProperty("server.port"));
		
		//GENERAL
		{
			{
				JsonObject payload = new JsonObject();
				
				JsonObject student = new JsonObject();
				student.addProperty("fullname", "Marcelo Tinelli");
				student.addProperty("student_id", "1580849");
				JsonArray students = new JsonArray();
				students.add(student);				
				
				payload.addProperty("subject", "Economia");
				payload.addProperty("year", 2017);
				payload.addProperty("quarter", "2");
				payload.add("students", students);
				payload.addProperty("document_name", "tp_marce.pdf");
				payload.addProperty("document_uri", "http://tpseconomiautn.com/documentos/tp_marce.pdf");
				
				list.add(performToResult(port, DocumentController.class, "/documents", "/documents", HttpMethod.POST, payload.toString()));
			}
			{				
				list.add(performToResult(port, DocumentController.class, "/documents/{documentId}", "/documents/93243B72B81365B6D28D7CFF89DFE191572469839132", HttpMethod.GET, ""));
			}
			{				
				list.add(performToResult(port, DocumentController.class, "/documents/{documentId}", "/documents/unknown", HttpMethod.GET, ""));
			}
		}
		//NER
		{
			{
				list.add(performToResult(port, DocumentController.class, "/{documentId}/ner/tags/main/{tagName}", "/documents/93243B72B81365B6D28D7CFF89DFE191572469839132/ner/tags/main/inventario", HttpMethod.POST, ""));		
			}
			{
				list.add(performToResult(port, DocumentController.class, "/{documentId}/ner/tags/main/{tagName}", "/documents/unknown/ner/tags/main/inventario", HttpMethod.POST, ""));		
			}
			{
				list.add(performToResult(port, DocumentController.class, "/{documentId}/ner/tags/secondary/{tagName}", "/documents/93243B72B81365B6D28D7CFF89DFE191572469839132/ner/tags/secondary/inventario", HttpMethod.POST, ""));		
			}
			{
				list.add(performToResult(port, DocumentController.class, "/{documentId}/ner/tags/secondary/{tagName}", "/documents/unknown/ner/tags/secondary/inventario", HttpMethod.POST, ""));		
			}
			{
				list.add(performToResult(port, NerController.class, "/ner/tags", "/ner/tags", HttpMethod.GET, ""));		
			}
			{
				list.add(performToResult(port, NerController.class, "/ner/tags/{tagName}", "/ner/tags/economia", HttpMethod.GET, ""));
			}
			{
				list.add(performToResult(port, NerController.class, "/ner/tags/{tagName}", "/ner/tags/impuesto", HttpMethod.GET, ""));
			}
		}
		
		return ResponseEntity.ok(list);
	}
	
	<T> RouteTest performToResult(int port, Class<T> controller, String showuri, String uri, HttpMethod method, Object payload) throws HttpClientErrorException {
		RouteTest result = null;
		List<String> reqHeaders = new ArrayList<String>();
		List<String> resHeaders = new ArrayList<String>();
		try {
			ResponseEntity<String> out = perform("http://localhost:" + port + uri, method, payload, String.class);					
			for(String s : out.getHeaders().keySet()) {
				resHeaders.add(s);
			}
			result = new RouteTest(method.toString(), showuri, controller.getSimpleName(), payload.toString(), reqHeaders, out.getStatusCodeValue(), resHeaders, out.getBody());
		} catch (HttpClientErrorException e) {					
			for(String s : e.getResponseHeaders().keySet()) {
				resHeaders.add(s);
			}
			result = new RouteTest(method.toString(), showuri, controller.getSimpleName(), payload.toString(), reqHeaders, e.getRawStatusCode(), resHeaders, e.getResponseBodyAsString());
		}		
		return result;
	}
	
	<T> ResponseEntity<T> perform(String uri, HttpMethod method, Object payload, Class<T> responseType) throws HttpClientErrorException {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<Object> entity;
	    if(payload != null) {
	    	entity = new HttpEntity<Object>(payload, headers);
	    } else {
	    	entity = new HttpEntity<Object>(headers);
	    }
	    
	    ResponseEntity<T> out = null;
	    try {
		    out = restTemplate.exchange(uri, method, entity, responseType);
		    return out;
		} catch (HttpClientErrorException e) {
			throw(e);
		}
    }

}