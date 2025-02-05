package gg.ketsu.bot.util.command;

import lombok.Getter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandOption {

    private final OptionType type;
    private final String name;
    private final String description;
    private final boolean required;
    private final List<String> choices;

    public CommandOption(final OptionType type, final String name, final String description, final boolean required) {
        this(type, name, description, required, new ArrayList<>());
    }

    public CommandOption(final OptionType type, final String name, final String description, final boolean required, final List<String> choices) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = choices;
    }

    public @NotNull OptionData toOptionData() {
        final OptionData optionData = new OptionData(this.type, this.name, this.description, this.required);
        for (final String choice : this.choices) {
            optionData.addChoice(choice, choice);
        }
        return optionData;
    }
}
