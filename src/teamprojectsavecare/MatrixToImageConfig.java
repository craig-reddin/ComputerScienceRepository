/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

import java.awt.image.BufferedImage;

/**
 * Encapsulates custom configuration used in methods of
 * {@link MatrixToImageWriter}.
 */



//Link to this class is below
//https://github.com/zxing/zxing/blob/master/javase/src/main/java/com/google/zxing/client/j2se/MatrixToImageConfig.java


public final class MatrixToImageConfig {

    public static final int BLACK = 0xFF000000;
    public static final int WHITE = 0xFFFFFFFF;

    private final int onColor;
    private final int offColor;

    public MatrixToImageConfig() {
        this(BLACK, WHITE);
    }

    /**
     * @param onColor pixel on color, specified as an ARGB value as an int
     * @param offColor pixel off color, specified as an ARGB value as an int
     */
    public MatrixToImageConfig(int onColor, int offColor) {
        this.onColor = onColor;
        this.offColor = offColor;
    }

    public int getPixelOnColor() {
        return onColor;
    }

    public int getPixelOffColor() {
        return offColor;
    }

    int getBufferedImageColorModel() {
        if (onColor == BLACK && offColor == WHITE) {
            // Use faster BINARY if colors match default
            return BufferedImage.TYPE_BYTE_BINARY;
        }
        if (hasTransparency(onColor) || hasTransparency(offColor)) {
            // Use ARGB representation if colors specify non-opaque alpha
            return BufferedImage.TYPE_INT_ARGB;
        }
        // Default otherwise to RGB representation with ignored alpha channel
        return BufferedImage.TYPE_INT_RGB;
    }

    private static boolean hasTransparency(int argb) {
        return (argb & 0xFF000000) != 0xFF000000;
    }

}
