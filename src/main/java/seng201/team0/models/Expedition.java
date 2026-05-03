package seng201.team0.models;

public class Expedition {
    int numberOfAreas;

    public final String[] ExpeditionLocation = {"Plains", "Cave", "Mountain", "Jungle"};

    private final String[] PlainsAreas = {"Field", "Flower Patch", "Stream", "Bridge"};
    private final String[] CaveAreas = {"Dark Cave", "Waterfall", "Underground Lake", "Large Open Area"};
    private final String[] MountainAreas = {"Ledge", "Uphill", "Cliff", "Rocky Area"};
    private final String[] JungleAreas = {"Thin Jungle", "Thicc Jungle", "Opening", "Pond"};

    public Expedition(int length)
    {
        this.numberOfAreas = length;
    }

    public String[] getAreas(String location)
    {
        return switch (location) {
            case "Plains" -> PlainsAreas;
            case "Cave" -> CaveAreas;
            case "Mountain" -> MountainAreas;
            case "Jungle" -> JungleAreas;
            default -> null;
        };
    }
}
