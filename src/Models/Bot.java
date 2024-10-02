package Models;

import Strategy.BotPlayingStrategy;
import Strategy.EasyBotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;

    public Bot(char symbol, String name, BotDifficultyLevel botDifficultyLevel){
        super(symbol,name,PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;

        // ToDo: better way is via factory based on the bot difficulty level
        this.botPlayingStrategy = new EasyBotPlayingStrategy();
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public Move devcideMove(){
        
        return null;
    }
}
