package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.*;

public class TelegramBot extends TelegramLongPollingBot {
    private Map<Long, Integer> levels = new HashMap<>();

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new TelegramBot());
    }

    @Override
    public String getBotUsername() {
        return "BanderoGusBot";
    }

    @Override
    public String getBotToken() {
        return "6223266853:AAGc-pLR9B7YJ6bpI3DUsKmmuzbHbAofziE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getchatid(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            //SetImage
            sendImage("level-1", chatId);
            //SendMessage
            SendMessage message = createMessage("Ga-ga-ga\n" +
                    "Welcome to the bot bio laboratory goose" +
                    "\n" +
                    "You get goose No. 523\n" +
                    "\n" +
                    "We created this bot in order for your goose to pump from the level of ordinary livestock to the level of biological weapons capable of destroying the enemy .\n" +
                    "\n" +
                    "In order for an ordinary goose to turn into a goose terminator, you need :\n" +
                    "✔\uFE0Fperform tasks\n" +
                    "✔\uFE0Fgo to next levels\n" +
                    "✔\uFE0Fearn enough coins to buy Javelin and defeat all enemies .\n" +
                    "\n" +
                    "* Normal goose. * Start level .\n" +
                    "Bonus: 5 coins .\n" +
                    "Choose a task to move to the next level");
            message.setChatId(chatId);

            List<String> buttons = Arrays.asList("Weave camouflage grid (+ 15 coins)",
                    "Raise funds with patriotic songs (+ 15 coins)",
                    "Charge the tank with a missile (+ 15 coins)",
                    "Launch a volunteer action (+ 15 coins)",
                    "Join the ranks of the Teroboron (+ 15 coins))");

            buttons = getRandom3(buttons);


            attachButtons(message, Map.of(buttons.get(0), "level_1_task",
                    buttons.get(1), "level_1_task",
                    buttons.get(2), "level_1_task"));

            sendApiMethodAsync(message);
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_1_task") && getLevel(chatId) == 1) {
                setLevel(chatId, 2);
                sendImage("level-2", chatId);
                SendMessage message = createMessage("* Welcome to the second level! Your goose is a bio goose. *\n " +
                        "Balance: 20 coins .\n" +
                        "Choose a task to move to the next level");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList("Collect mosquitoes for new biological weapons (+ 15 coins)",
                        "Take the course of a young fighter (+ 15 coins)",
                        "Charge on APU (+ 15 coins)",
                        "Shoot down a drone with a can of cucumbers (+ 15 coins)",
                        "Make stocks of Molotov cocktails (+ 15 coins)");

                buttons = getRandom3(buttons);

                attachButtons(message, Map.of(
                        buttons.get(0), "level_2_task",
                        buttons.get(1), "level_2_task",
                        buttons.get(2), "level_2_task"));
                sendApiMethodAsync(message);
            }
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_2_task") && getLevel(chatId) == 2) {
                setLevel(chatId, 3);
                sendImage("level-3", chatId);
                SendMessage message = createMessage("\n" +
                        "* Welcome to the third level! Your goose - fighter of APU. *\n "+
                        "Balance: 35 coins .\n" +
                        "Choose a task to move to the next level");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList("Take off for a test raid on four positions (+ 15 coins)",
                        "Take the humanitarian to the front (+ 15 coins)",
                        "Find a traitor and hand over to the SBU (+ 15 coins)",
                        "Bring art to orcs (+ 15 coins)",
                        "Attract a tank with a tractor (+ 15 coins)");

                buttons = getRandom3(buttons);

                attachButtons(message, Map.of(buttons.get(0), "level_3_task",
                        buttons.get(1), "level_3_task",
                        buttons.get(2), "level_3_task"));
                sendApiMethodAsync(message);
            }
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_3_task") && getLevel(chatId) == 3) {
                setLevel(chatId, 4);
                sendImage("level-4", chatId);
                SendMessage message = createMessage("* Welcome to the last level! Your goose - ready biological weapons - terminator goose. * "+
                        "Balance: 50 coins." +
                        "Now you can buy Javelin and win");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Buy Javelin (50 coins)", "level_4_task"));
                sendApiMethodAsync(message);
            }
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_4_task") && getLevel(chatId) == 4) {
                setLevel(chatId, 5);
                sendImage("final", chatId);
                SendMessage message = createMessage("* Javelin is yours. Full go! *");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }
        }
    }

    public Long getchatid(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }

    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setParseMode("markdown");
        return message;
    }

    public void attachButtons(SendMessage message, Map<String,String> buttons) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonName);
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public void sendImage(String name, Long chatId) {
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File( "images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);
    }

    public int getLevel(Long chatId) {
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevel(Long chatId, int level) {
        levels.put(chatId, level);
    }

    public List<String> getRandom3(List<String> variants) {
        ArrayList<String> copy = new ArrayList<>(variants);
        Collections.shuffle(copy);
        return copy.subList(0 ,3);
    }
}