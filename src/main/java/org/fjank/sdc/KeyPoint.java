package org.fjank.sdc;

/**
 * Represents a KeyPoint i.e. a point feature found by one of many available KeyPoint
 * detectors in OpenCV.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public interface KeyPoint {
    /**
     * Return the response for this KeyPoint.
     * @return the response for this KeyPoint.
     */
    float getResponse();

    /**
     * Return the x position of this KeyPoint.
     * @return the x position of this KeyPoint.
     */
    int getXPos();

    /**
     * Return the y position of this KeyPoint.
     * @return the y position of this KeyPoint.
     */
    int getYPos();
}
