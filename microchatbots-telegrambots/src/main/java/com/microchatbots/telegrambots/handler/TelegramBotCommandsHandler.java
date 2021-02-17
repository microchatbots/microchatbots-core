/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microchatbots.telegrambots.handler;

import com.microchatbots.core.parser.SpaceParser;
import com.microchatbots.core.parser.TextParser;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.BotCommand;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.ParseMode;
import com.microchatbots.telegrambots.core.send.SendMessage;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.io.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TelegramBotCommandsHandler implements TelegramRequestHandler<SendMessage> {
    public static final String CLASSPATH = "classpath:";
    public static final String MARKDOWN_EXTENSION = ".md";

    private static final Logger LOG = LoggerFactory.getLogger(TelegramBotCommandsHandler.class);
    private final TextParser<Update> textParser;
    private final SpaceParser<Update> spaceParser;
    private final Map<String, String> markdown = new ConcurrentHashMap<>();

    public TelegramBotCommandsHandler(TelegramBotConfiguration telegramBotConfiguration,
                                      ResourceLoader resourceLoader,
                                      SpaceParser<Update> spaceParser,
                                      TextParser<Update> textParser) {
        for (BotCommand botCommand : telegramBotConfiguration.getBotCommands()) {
            Optional<InputStream> inputStreamOptional = resourceLoader.getResourceAsStream(CLASSPATH + botCommand.getCommand() + MARKDOWN_EXTENSION);
            if (inputStreamOptional.isPresent()) {
                try {
                    InputStream inputStream = inputStreamOptional.get();
                    markdown.put(botCommand.getCommand(), readMarkdown(inputStream));
                    inputStream.close();
                } catch (IOException e) {
                    LOG.warn("IO Exception reading markdown", e);
                }
            }
        }
        this.spaceParser = spaceParser;
        this.textParser = textParser;
    }

    /**
     *
     * @param inputStream InputStream
     * @return Markdown
     */
    protected String readMarkdown(@NonNull InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean canHandle(TelegramBotConfiguration telegramBotConfiguration, Update update) {
        if (!spaceParser.parseSpaceUniqueIdentifier(telegramBotConfiguration, update).isPresent()) {
            return false;
        }
        Optional<String> commandOptional = textParser.parserCommand(telegramBotConfiguration, update);
        if (!commandOptional.isPresent()) {
            return false;
        }
        String command = commandOptional.get();
        return markdown.containsKey(command);
    }

    @Override
    public SendMessage handle(TelegramBotConfiguration telegramBotConfiguration, Update update) {
        SendMessage sendMessage = new SendMessage();
        Optional<Serializable> chatId = spaceParser.parseSpaceUniqueIdentifier(telegramBotConfiguration, update);
        chatId.ifPresent(serializable -> sendMessage.setChatId(serializable.toString()));
        sendMessage.setParseMode(ParseMode.MARKDOWN.toString());

        Optional<String> commandOptional = textParser.parserCommand(telegramBotConfiguration, update);
        if (commandOptional.isPresent()) {
            String command = commandOptional.get();
            sendMessage.setText(markdown.get(command));
        }
        return sendMessage;
    }
}
