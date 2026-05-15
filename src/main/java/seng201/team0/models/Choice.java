package seng201.team0.models;

public class Choice {
    private final String choiceDescription;
    private final String statChecked;
    Choice(String choiceDescription, String statChecked)
    {
        this.choiceDescription = choiceDescription;
        this.statChecked = statChecked;
    }
    public String  getChoice()
    {
        return choiceDescription;
    }
    public String getStat()
    {
        return switch (statChecked) {
            case "health" -> "lose health";
            case "stamina" -> "lose stamina";
            case "progress" -> "lose progress";
            case "none" -> "continue your expedition";
            case "perception" -> "IMPLEMENT PERCEPTION CHECK HERE";
            default -> "ERROR";
        };
    }
}
