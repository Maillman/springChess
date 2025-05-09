package dataaccess.memory;

import java.util.HashMap;

import dataaccess.GameDAO;
import model.GameData;

public class MemoryGameDAO implements GameDAO {
    
    private final HashMap<String, GameData> games = new HashMap<>();
}