package gg.ketsu.bot.util.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {

    private final List<CommandData> commands = new ArrayList<>();

    public void registerSlashCommand(final String name, final String description) {
        commands.add(Commands.slash(name, description));
    }

    public void registerSlashCommandWithOptions(final String name, final String description, final CommandOption... options) {
        final var command = Commands.slash(name, description);
        for (final CommandOption option : options) {
            command.addOptions(option.toOptionData());
        }
        commands.add(command);
    }

    public void registerSlashCommandWithOptions(final String name, final String description, final OptionData... options) {
        final var command = Commands.slash(name, description);
        for (final OptionData option : options) {
            command.addOptions(option);
        }
        commands.add(command);
    }

    public void registerCommands(@NotNull final JDA jda) {
        jda.updateCommands().addCommands(commands).queue();
    }

    public void clearCommands(@NotNull final JDA jda) {
        jda.updateCommands().queue();
    }
}
