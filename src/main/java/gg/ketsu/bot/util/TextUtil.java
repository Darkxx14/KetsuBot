package gg.ketsu.bot.util;

import org.jetbrains.annotations.NotNull;

public class TextUtil {

	public static @NotNull String format(@NotNull final String enumName) {
		final String[] words = enumName.split("_");
		final StringBuilder formattedName = new StringBuilder();

		for (final String word : words) {
			formattedName.append(word.substring(0, 1).toUpperCase());
			formattedName.append(word.substring(1).toLowerCase());
			formattedName.append(" ");
		}

		return formattedName.toString().trim();
	}
}
