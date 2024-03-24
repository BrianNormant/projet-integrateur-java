package gestionInformation;

import java.net.http.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.*;

public class Station {

	private String nom;
	private int x;
	private int y;
	
	public Station(String nom, int x, int y) {
	this.nom = nom;
	this.x = x;
	this.y = y;
	}
	
	 public static void getStation() {
	        HttpRequest request = null;
	        try {
	            request = HttpRequest.newBuilder()
	                .uri( new URI("https://equipe500.tch099.ovh/projet6/api/stations") )
	                .GET()
	                .build();
	        } catch (URISyntaxException ignored) {};
	        try {
	            var client = HttpClient.newHttpClient();
	            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	            System.out.println(response.statusCode());
	            // System.out.println(response.body());
	            
	            var json = new JSONArray(response.body());
	            for (var jo : json) {
	                System.out.printf("""
	                        id -> %d
	                        name -> %s
	                        pos_x -> %s
	                        pos_y -> %s
	                        """,
	                        ((JSONObject)jo).get("id"),
	                        ((JSONObject)jo).get("name"),
	                        ((JSONObject)jo).get("pos_x"),
	                        ((JSONObject)jo).get("pos_y")
	                        );
	            }
	            System.out.println(json);
	        } catch (Exception ignored) {};
	    }
	
}
