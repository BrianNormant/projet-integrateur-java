package api;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import io.vavr.control.Either;
import main.Main;

import org.json.JSONArray;
import org.json.JSONObject;

import composanteGraphique.*;

import gestionInformation.Reservation;

public final class RestApi {

	private static final String URL = "https://equipe500.tch099.ovh/projet6/api/";

	/*
	 *
	 */
	public static Either<String, LoginError> requestLogin(String user, String password) {
		HttpRequest request = null;
		System.out.println("1111");
		try {
			request = HttpRequest.newBuilder()
				.header("Authorization", password)
				.uri( new URI(URL + "login/" + user) )
				.PUT(HttpRequest.BodyPublishers.noBody())
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			switch (response.statusCode()) {
				case 200 -> {
					var json = new JSONObject(response.body());
					System.out.println(json.get("token").toString());
					return Either.left(json.get("token").toString());
				}
				case 401 -> {
					return Either.right(LoginError.NOT_AUTHORIZED);
				}
				case 404 -> {
					return Either.right(LoginError.USER_NOT_FOUND);
				}
			}
		} catch (Exception fail) {
			return null;
		};
		assert false;
		return null;
	}

	public static boolean requestCheckLogin(String user, String token) {
		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
				.header("Authorization", token)
				.uri( new URI(URL + "check_login/" + user) )
				.POST(HttpRequest.BodyPublishers.noBody())
				.build();
		} catch (URISyntaxException fail) {
			System.err.println("Fatal, Invalid URL");
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			return switch (response.statusCode()) {
				case 200 -> true;
				case 417,404,408 -> false;
				default -> false;
			};
		} catch (Exception ignored) {};
		return false;
	}

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
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			for (var jo : json) {
				var data = (JSONObject)jo;
				rails.add(Rail.createRail(
							(Integer) data.get("con1"),
							(Integer) data.get("con2"),
							(Integer) data.get("id")
							
						));
				//System.out.println("Rail: " +(Integer) data.get("con1")+" "+(Integer) data.get("con2")+" "+(Integer) data.get("id"));
			}
		} catch (Exception fail) {
			return Optional.empty();
		};
		return Optional.of(rails);
	}
	
	public static Optional<List<Train>> requestTrains() {
		HttpRequest request = null;
		ArrayList<Train> trains = new ArrayList<>();
		
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + "trains") )
				.header("Authorization", Main.getToken())
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.out.println("Fatal, Invalid URL");
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			for (var jo : json) {
				var data = (JSONObject)jo;
				trains.add(Train.createOrGet(
							(Integer) data.get("id"),
							(Integer) data.get("rail_id"),
							(Integer) data.get("pos")
						));
			}
		} catch (Exception fail) {
			fail.printStackTrace();
			return Optional.empty();
		};
		return Optional.of(trains);
	
	}
	
	public static Optional<List<Train>> requestTrainsPourStation(String token, int id) {
		HttpRequest request = null;
		ArrayList<Train> trains = new ArrayList<>();
		
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL +"stations/"+ id +"/arrival") )
				.header("Authorization", token)
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			for (var jo : json) {
				var data = (JSONObject)jo;
				trains.add(Train.createOrGet(
							(Integer) data.get("id"),
							(Integer) data.get("rail_id"),
							(Integer) data.get("pos")
						));
			}
		} catch (Exception fail) {
			return Optional.empty();
		};
		return Optional.of(trains);
	
	}

	/*public static boolean requestPutReservation(String token, Reservation reservation) {
		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + String.format("reservations/%d/%d?date=%s&period=%s",
								reservation.origin.getId(), reservation.destination.getId(),
								reservation.date, reservation.period.name)))
				.header("Authorization", token)
				.PUT(HttpRequest.BodyPublishers.noBody())
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return switch (response.statusCode()) {
				case 200 -> true;
				default -> false;
			};
		} catch (Exception e) { }
		return false;
	}*/

	/**
	 * Recuperer et cree/update un train par rapport au details du dit train sur le reseaux actuel.
	 * @param train l'id du train
	 * @return un nouveau ou le meme train updater
	 */
	public static Optional<Train> requestTrain(Integer train) {
		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + "train/" + train + "/details") )
				.header("Authorization", Main.getToken() )
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
		}

		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			Train trainRef = Train.createOrGet(train);
			
			var json = new JSONObject(response.body());
			
			trainRef.setRail((Integer) json.get("rail"));
			trainRef.setPos( (Integer)  json.get("pos"));

			return Optional.of(trainRef);

		} catch (Exception fail) {
			System.out.print("Failed");
			fail.printStackTrace();

			return Optional.empty();
		}
	}
	
	public static Optional<List<Reservation>> requestReservations() {
		HttpRequest request = null;
		ArrayList<Reservation> reservations = new ArrayList<>();
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + "list_reservations") )
				.header("Authorization", "placeholder")
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
		};
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			for (var jo : json) {
				var data = (JSONObject)jo;
				var rail = new Reservation(
						(Integer)data.get("id"),
						(String)data.get("company_id"),
						(String)data.get("date"),
						(String)data.get("period"),
						Rail.getRailIfExists((Integer)(data.get("rail")))
						);
				reservations.add(rail);
				//System.out.println(rail);

			}
			return Optional.of(reservations);
		} catch (Exception fail) {
			System.out.print("Failed");
			fail.printStackTrace();

			return Optional.empty();
		}
	}
	

	
	public static Optional<Rail> requestRail(int railNumber) {
		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
				.uri( new URI(URL + "rail/"+railNumber) )
				.GET()
				.build();
		} catch (URISyntaxException fatal) {
			System.err.println("Fatal, Invalid URL");
		}
		try {
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			var json = new JSONArray(response.body());
			Rail rail = null;
			for (var jo : json) {
				var data = (JSONObject)jo;
				rail = Rail.createRail(
							(Integer) data.get("con1"),
							(Integer) data.get("con2"),
							(Integer) data.get("id")
						);
			}
			return Optional.of(rail);

		} catch (Exception fail) {
			return Optional.empty();
		}
	}
}
