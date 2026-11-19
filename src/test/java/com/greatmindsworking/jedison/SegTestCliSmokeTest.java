package com.greatmindsworking.jedison;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Exercise the public SegTest entry point in a separate JVM because it
 * intentionally calls System.exit(). Uses a synthetic image, no GUI or X server.
 */
class SegTestCliSmokeTest {

    @TempDir
    Path tempDir;

    @Test
    void segmentsSmallImageWithTextDisabled() throws Exception {
        runSegmentation("quiet", "false", "0", null);
    }

    @Test
    void segmentsSmallImageWithTextEnabledAndHighSpeedFactor() throws Exception {
        runSegmentation("verbose", "true", "2", "0.5");
    }

    @Test
    void missingHighSpeedFactorShowsUsage() throws Exception {
        Path dir = Files.createDirectory(tempDir.resolve("usage"));
        String output = invoke(dir, "not-needed.png", "1", "false", "6.5", "2", "1", "2");
        assertTrue(output.contains("Usage:"), output);
        assertTrue(!Files.exists(dir.resolve("segment.png")));
    }

    private void runSegmentation(String directory, String verbose, String speed,
                                 String factor) throws Exception {
        Path dir = Files.createDirectory(tempDir.resolve(directory));
        Path imagePath = dir.resolve("input.png");

        BufferedImage input = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                input.setRGB(x, y, x < 8 ? 0x003366 : 0xCC9933);
            }
        }
        assertTrue(ImageIO.write(input, "png", imagePath.toFile()));

        List<String> args = new ArrayList<String>();
        args.add(imagePath.toString());
        args.add("1");
        args.add(verbose);
        args.add("6.5");
        args.add("2");
        args.add("1");
        args.add(speed);
        if (factor != null) {
            args.add(factor);
        }

        String log = invoke(dir, args.toArray(new String[0]));
        Path resultPath = dir.resolve("segment.png");
        assertTrue(Files.exists(resultPath), "Segmented PNG missing; CLI output:\n" + log);
        BufferedImage output = ImageIO.read(resultPath.toFile());
        assertNotNull(output, "CLI must write a readable PNG");
        assertEquals(16, output.getWidth());
        assertEquals(16, output.getHeight());
    }

    private String invoke(Path directory, String... parameters)
            throws IOException, InterruptedException {
        String binary = System.getProperty("os.name").toLowerCase().contains("windows")
                ? "java.exe" : "java";
        String java = Paths.get(System.getProperty("java.home"), "bin", binary).toString();
        String classpath = System.getProperty("surefire.test.class.path");
        if (classpath == null) {
            classpath = System.getProperty("java.class.path");
        }

        List<String> command = new ArrayList<String>();
        command.add(java);
        command.add("-Djava.awt.headless=true");
        command.add("-cp");
        command.add(classpath);
        command.add("com.greatmindsworking.jedison.SegTest");
        for (String param : parameters) {
            command.add(param);
        }

        Path logFile = directory.resolve("cli.log");
        Process process = new ProcessBuilder(command)
                .directory(directory.toFile())
                .redirectErrorStream(true)
                .redirectOutput(logFile.toFile())
                .start();
        boolean finished = process.waitFor(30, TimeUnit.SECONDS);
        if (!finished) {
            process.destroyForcibly();
        }
        String output = new String(Files.readAllBytes(logFile), StandardCharsets.UTF_8);
        assertTrue(finished, "CLI timed out:\n" + output);
        assertEquals(0, process.exitValue(), output);
        return output;
    }
}
