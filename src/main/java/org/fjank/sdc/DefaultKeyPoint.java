package org.fjank.sdc;

/**
 * Represents a KeyPoint i.e. a point feature found by one of many available KeyPoint
 * detectors in OpenCV.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class DefaultKeyPoint implements KeyPoint {
    private final int intXPos;
    private final int intYPos;
    private final float response;

    /**
     * Creates a new KeyPoint with the specified position and response.
     * @param xPos the x position for this KeyPoint.
     * @param yPos the y position for this KeyPoint.
     * @param response the response, that is "how strong" the KeyPoint is.
     */
    public DefaultKeyPoint(int xPos, int yPos, float response) {
        this.intXPos = xPos;
        this.intYPos = yPos;
        this.response = response;
    }

    @Override
    public float getResponse() {
        return response;
    }

    @Override
    public int getXPos() {
        return intXPos;
    }

    @Override
    public int getYPos() {
        return intYPos;
    }
}
