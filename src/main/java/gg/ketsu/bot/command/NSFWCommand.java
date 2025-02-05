package gg.ketsu.bot.command;

import gg.ketsu.api.KetsuAPI;
import gg.ketsu.api.exception.InvalidContentTypeException;
import gg.ketsu.api.model.ContentCategory;
import gg.ketsu.api.model.ContentType;
import gg.ketsu.bot.util.TextUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

public class NSFWCommand extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

		if (!event.getName().equals("nsfw")) {
			return;
		}

		final String categoryOption = Objects.requireNonNull(event.getOption("category")).getAsString().toUpperCase().replace(" ", "_");
		final ContentCategory category = ContentCategory.valueOf(categoryOption);

		final ContentType type = ContentType.GIF;

		if (!Arrays.asList(category.getContentTypes()).contains(type)) {
			error(event, "This category doesn't support the selected content type.");
			return;
		}

		String content;
		try {
			// KetsuAPI is a wrapper of PurrAPI
			content = KetsuAPI.getInstance().getApi().get(category, type); // TODO: Let user select the content type (Image/GIF)
		} catch (final InvalidContentTypeException e) {
			error(event, "This category doesn't support the " + TextUtil.format(type.name()) + " content type.");
			return;
		}

		final EmbedBuilder embed = new EmbedBuilder()
				.setTitle(TextUtil.format(category.name()))
				.setColor(new Color(255, 105, 180))
				.setImage(content)
				.setFooter("Powered by KetsuAPI", "https://i.imghippo.com/files/ofcz6941dc.png")
				.setTimestamp(Instant.now());
		event.replyEmbeds(embed.build()).queue();
	}

	private void error(@NotNull final SlashCommandInteractionEvent event, @NotNull final String message) {
		final EmbedBuilder embed = new EmbedBuilder()
				.setTitle("Error")
				.setDescription(message)
				.setColor(new Color(255, 58, 66))
				.setFooter("Powered by KetsuAPI", "https://i.imghippo.com/files/ofcz6941dc.png")
				.setTimestamp(Instant.now());
		event.replyEmbeds(embed.build()).queue();
	}
}
