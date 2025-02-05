package gg.ketsu.bot.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigUtil {

	@Getter
	private JsonObject configData;
	private final String configPath = "bot.json";

	public void load() {
		try {
			if (Files.exists(Paths.get(configPath))) {
				FileReader reader = new FileReader(configPath);
				configData = JsonParser.parseReader(reader).getAsJsonObject();
				reader.close();
			} else {
				loadDefaults();
			}
		} catch (IOException e) {
			System.err.println("Error loading config: " + e.getMessage());
		}
	}

	public void save() {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			String json = gsonBuilder.create().toJson(configData);

			FileWriter writer = new FileWriter(configPath);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			System.err.println("Error saving config: " + e.getMessage());
		}
	}

	private void loadDefaults() {
		configData = new JsonObject();

		JsonObject botConfig = new JsonObject();
		botConfig.addProperty("token", "your-bot-token-here");
		configData.add("bot", botConfig);

		save();
	}

	public int getInt(String path, int defaultValue) {
		try {
			String[] keys = path.split("\\.");
			JsonObject current = configData;
			for (String key : keys) {
				if (current.has(key)) {
					current = current.getAsJsonObject(key);
				} else {
					return defaultValue;
				}
			}
			return current.getAsInt();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public String getString(String path) {
		try {
			String[] keys = path.split("\\.");
			JsonElement current = configData;

			for (String key : keys) {
				if (current.isJsonObject() && current.getAsJsonObject().has(key)) {
					current = current.getAsJsonObject().get(key);
				} else if (current.isJsonPrimitive() && current.getAsJsonPrimitive().getAsString() != null) {
					return current.getAsJsonPrimitive().getAsString();
				} else {
					System.err.println("Key not found or wrong type: " + key);
					return null;
				}
			}

			return current.getAsString();
		} catch (Exception e) {
			System.err.println("Error getting string: " + e.getMessage());
			return null;
		}
	}
}