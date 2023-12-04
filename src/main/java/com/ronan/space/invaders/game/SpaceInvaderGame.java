package com.ronan.space.invaders.game;

import com.ronan.space.invaders.factory.RadarObjectFactory;
import com.ronan.space.invaders.model.RadarObject;
import com.ronan.space.invaders.model.SpaceInvaderImage;
import com.ronan.space.invaders.model.RadarImage;
import com.ronan.space.invaders.util.FileUtils;
import com.ronan.space.invaders.util.GameConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceInvaderGame {

    private final FileUtils fileUtils;

    @Autowired
    public SpaceInvaderGame(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    /**
     * Run game using Spring Boot
     */
    @PostConstruct
    public void playGame(){
        RadarImage radarImage = createImage(GameConstants.CRAB_RADAR_PATH, RadarImage::new);
        SpaceInvaderImage crab = createImage(GameConstants.CRAB_INVADER_PATH, SpaceInvaderImage::new);

        boolean found = scanRadarForInvaderImage(radarImage, crab);

        System.out.println("Invader found: " + found);
    }


    public boolean playGame(RadarImage radarImage, SpaceInvaderImage spaceInvaderImage){
        return scanRadarForInvaderImage(radarImage, spaceInvaderImage);
    }

    public <T extends RadarObject> T createImage(String filePath, RadarObjectFactory<T> factory){
        String[][] imageData = fileUtils.readFile(filePath);
        return factory.create(imageData);
    }


    /**
     * There are many ways of matching string patterns in 2d arrays but from what I found the sliding window pattern is the most straight forward.
     * For every position on the radar image we are comparing this window with the pattern we are trying to match
     * @param radarImage
     * @param spaceInvaderImage
     * @return
     */
    private boolean scanRadarForInvaderImage(RadarImage radarImage, SpaceInvaderImage spaceInvaderImage) {
        int radarRows = radarImage.getRows();
        int radarColumns = radarImage.getCols();

        int invaderRows = spaceInvaderImage.getRows();
        int invaderColumns = spaceInvaderImage.getCols();

        for (int i = 0; i <= radarRows - invaderRows; i++) {
            for (int j = 0; j <= radarColumns - invaderColumns; j++) {
                // check if the current window matches the pattern
                if (matchInvaderWithWindow(radarImage.getData(), spaceInvaderImage.getData(), i, j, invaderRows, invaderColumns)) {
                   // System.out.println("MATCH! " + i + " " + j + " " + invaderRows + " " + invaderColumns);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * this method compares the image at the current position on the radar with the image we are trying to match and returns true if matched
     * @param radarImage
     * @param invaderImage
     * @param startRow
     * @param startColumn
     * @param invaderRows
     * @param invaderColumns
     * @return
     */
    private static boolean matchInvaderWithWindow(String[][] radarImage, String[][] invaderImage, int startRow, int startColumn, int invaderRows, int invaderColumns) {
        for (int i = 0; i < invaderRows; i++) {
            for (int j = 0; j < invaderColumns; j++) {
                if (!radarImage[startRow + i][startColumn + j].equals(invaderImage[i][j])) {
                    //TODO implement fault counter to allow tolerance for edge cases
                    return false;
                }
            }
        }

        // image found in the array matching the invader pattern
        return true;
    }

}


