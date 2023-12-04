package com.ronan.space.invaders.game;

import com.ronan.space.invaders.model.RadarImage;
import com.ronan.space.invaders.model.SpaceInvaderImage;
import com.ronan.space.invaders.util.FileUtils;
import com.ronan.space.invaders.util.GameConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceInvaderGameTest {


    private FileUtils fileUtils = new FileUtils();

    private SpaceInvaderGame spaceInvaderGame = new SpaceInvaderGame(fileUtils);


    /**
     * Test with Fuga image - no invaders found
     */
    @Test
    public void testPlayGame_FugaImage(){
        RadarImage radarImage = spaceInvaderGame.createImage(GameConstants.FUGA_RADAR_PATH, RadarImage::new);
        SpaceInvaderImage crab = spaceInvaderGame.createImage(GameConstants.CRAB_INVADER_PATH, SpaceInvaderImage::new);

        boolean spaceInvadersFound = spaceInvaderGame.playGame(radarImage, crab);

        assertFalse(spaceInvadersFound);
    }

    /**
     * Test with Crab image - crab found
     */
    @Test
    public void testPlayGame_CrabImage(){
        RadarImage radarImage = spaceInvaderGame.createImage(GameConstants.CRAB_RADAR_PATH, RadarImage::new);
        SpaceInvaderImage crab = spaceInvaderGame.createImage(GameConstants.CRAB_INVADER_PATH, SpaceInvaderImage::new);

        boolean spaceInvadersFound = spaceInvaderGame.playGame(radarImage, crab);

        assertTrue(spaceInvadersFound);
    }

    /**
     * Test with Squid image - squid found
     */
    @Test
    public void testPlayGame_SquidImage(){
        RadarImage radarImage = spaceInvaderGame.createImage(GameConstants.SQUID_RADAR_PATH, RadarImage::new);
        SpaceInvaderImage squid = spaceInvaderGame.createImage(GameConstants.SQUID_PATH, SpaceInvaderImage::new);


        boolean spaceInvadersFound = spaceInvaderGame.playGame(radarImage, squid);

        assertTrue(spaceInvadersFound);
    }


    /**
     * Test with Squid image with crab invader - not found
     */
    @Test
    public void testPlayGame_SquidImageCrabInvader(){
        RadarImage radarImage = spaceInvaderGame.createImage(GameConstants.SQUID_RADAR_PATH, RadarImage::new);
        SpaceInvaderImage crab = spaceInvaderGame.createImage(GameConstants.CRAB_INVADER_PATH, SpaceInvaderImage::new);

        boolean spaceInvadersFound = spaceInvaderGame.playGame(radarImage, crab);

        assertFalse(spaceInvadersFound);
    }
}