package gg.ketsu.bot;

import gg.ketsu.api.model.ContentCategory;
import gg.ketsu.bot.command.NSFWCommand;
import gg.ketsu.bot.util.ConfigUtil;
import gg.ketsu.bot.util.TextUtil;
import gg.ketsu.bot.util.command.CommandOption;
import gg.ketsu.bot.util.command.CommandRegistry;
import lombok.extern.java.Log;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log
public final class KetsuBot {

	private static JDA jda;

	public static void main(final String[] args) {
		try {
			ConfigUtil config = new ConfigUtil();
			config.load();
			config.save();
			setup(config.getString("bot.token"));
		} catch (InterruptedException e) {
			log.severe("An error occurred while setting up Ketsu: " + e.getMessage());
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			if (jda != null) {
				jda.shutdown();
			}
			log.info("Ketsu has successfully stopped!");
		}));
	}

	private static void setup(String token) throws InterruptedException {
		jda = JDABuilder.createDefault(
						token,
						GatewayIntent.GUILD_MESSAGES,
						GatewayIntent.MESSAGE_CONTENT)
				.disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.STICKER)
				.addEventListeners(new NSFWCommand())
				.build();

		jda.awaitReady();
		log.info("Ketsu has successfully started!");

		final CommandRegistry registry = new CommandRegistry();

		final List<String> categories = Arrays.stream(ContentCategory.values())
				.map(category -> TextUtil.format(category.name()))
				.collect(Collectors.toList());

		final CommandOption categoryOption = new CommandOption(
				OptionType.STRING,
				"category",
				"Choose an NSFW category",
				true,
				categories);

		registry.registerSlashCommandWithOptions(
				"nsfw",
				"Fetch NSFW content based on category and content type",
				categoryOption
		);

		registry.registerCommands(jda);
	}
}
