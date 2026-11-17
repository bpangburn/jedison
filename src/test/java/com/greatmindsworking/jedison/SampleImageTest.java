package com.greatmindsworking.jedison;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

class SampleImageTest {

    @Test
    void sampleImageIsBundledAndReadable() throws IOException {
        try (InputStream imageStream =
                SegTest.class.getClassLoader().getResourceAsStream("my_image.png")) {
            assertNotNull(imageStream, "Demo image must be on the runtime classpath");
            BufferedImage image = ImageIO.read(imageStream);
            assertNotNull(image, "Bundled sample must be a valid PNG");
            assertTrue(image.getWidth() > 0);
            assertTrue(image.getHeight() > 0);
        }
    }

    @Test
    void publicConstantsHaveExpectedDisplayNames() {
        assertEquals("color", ImageType.COLOR.toString());
        assertEquals("grayscale", ImageType.GRAYSCALE.toString());
        assertEquals("no speedup", SpeedUpLevel.NO_SPEEDUP.toString());
        assertEquals("medium speedup", SpeedUpLevel.MED_SPEEDUP.toString());
        assertEquals("high speedup", SpeedUpLevel.HIGH_SPEEDUP.toString());
    }
}
