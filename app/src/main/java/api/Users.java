package api;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import gestionInformation.Station;
import gestionInformation.Rail;

public final class Users implements Endpoint {

	private static final String URL = "https://equipe500.tch099.ovh/projet6/api/";

	public static Optional<List<String>> requestUsers() {
		HttpRequest request = null;
		ArrayList<String> users = new ArrayList<>();
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + "users") )
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
			System.exit(1);
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			for (var jo : json)
						users.add(((JSONObject)jo).get("user_name").toString());
		} catch (Exception fail) {
			return Optional.empty();
		};
		return Optional.of(users);
	}

	public static Optional<List<Station>> requestStations() {
		HttpRequest request = null;
		ArrayList<Station> stations = new ArrayList<>();
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + "stations") )
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
			System.exit(1);
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			for (var jo : json) {
				var data = (JSONObject)jo;
				stations.add(Station.createOrGetStation(
							(Integer)data.get("id"),
							(String)data.get("name"),
							Integer.parseInt((String)data.get("pos_x")),
							Integer.parseInt((String)data.get("pos_y"))
							));
			}
		} catch (Exception fail) {
			return Optional.empty();
		};
		return Optional.of(stations);
	}

	public static Optional<List<Rail>> requestRails() {
		HttpRequest request = null;
		ArrayList<Rail> rails = new ArrayList<>();
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + "rails") )
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
			System.exit(1);
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			for (var jo : json) {
				var data = (JSONObject)jo;
				rails.add(new Rail(
							(Integer) data.get("con1"),
							(Integer) data.get("con2")
						));
			}
		} catch (Exception fail) {
			return Optional.empty();
		};
		return Optional.of(rails);
	}
	
}
