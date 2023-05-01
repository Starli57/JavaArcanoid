package craftout.gameLevel;

import craftout.gameObjects.models.BrickPosition;

import java.util.ArrayList;

public class GameLevelData {
    public ArrayList<BrickPosition> brickPositions = new ArrayList<BrickPosition>();

    public GameLevelData() {
        BuildTestLevelData();
    }

    private void BuildTestLevelData(){
        int rows = 4;
        int cols = 11;
        int offsetHor = 54;
        int offsetVert = 30;
        int startOffsetHor = 40;
        int startOffsetVert = 60;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                float x = j * offsetHor + startOffsetHor;
                float y = i * offsetVert + startOffsetVert;

                brickPositions.add(new BrickPosition(x, y));
            }
        }
    }
}
