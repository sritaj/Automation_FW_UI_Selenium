package utils;

import enums.ConfigProperties;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

/**
 * TakeVideoImpl class to capture video recordings for the Test
 */
public class TakeVideoImpl extends ScreenRecorder {

    private String fileName;
    private File currentFile;

    public TakeVideoImpl(File movieFolder) throws IOException, AWTException {
        super(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(),
                new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height),
                new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null,
                movieFolder);
    }

    /**
     * Method to create Movie file
     *
     * @fileFormat - specifying the file format
     */
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        currentFile = getFileWithUniqueName(movieFolder.getAbsolutePath() + File.separator + fileName + "." + Registry.getInstance().getExtension(fileFormat));
        return currentFile;
    }

    /**
     * Method to get File with unique name
     *
     * @return File with filename
     * @fileName - The name of the file
     */
    private File getFileWithUniqueName(String fileName) {
        String extension = "";
        String name = "";

        int idxOfDot = fileName.lastIndexOf('.'); // Get the last index of . to separate extension
        extension = fileName.substring(idxOfDot + 1);
        name = fileName.substring(0, idxOfDot);

        Path path = Paths.get(fileName);
        int counter = 1;
        while (Files.exists(path)) {
            fileName = name + "-" + counter + "." + extension;
            path = Paths.get(fileName);
            counter++;
        }
        return new File(fileName);
    }

    /**
     * Method to get File with unique name
     *
     * @fileName - The name of the file
     * @captureMouse -  true/false to capture the mouse movements
     */
    public void startRecording(String fileName, boolean captureMouse) {

        if (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENRECORDING).trim().equalsIgnoreCase("yes")
                && (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.RUNMODE).trim().equalsIgnoreCase("local"))) {
            this.fileName = fileName;
            try {
                start();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
                LoggerImpl.logSteps("Null Pointer Exception -> Please set 'screenrecording' property value to NO for parallel execution");
            }
        }
    }

    /**
     * Method to get File with unique name
     *
     * @keepFile - true/false for keeping the file
     */
    public void stopRecording(boolean keepFile) {
        if (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENRECORDING).trim().equalsIgnoreCase("yes")
                && (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.RUNMODE).trim().equalsIgnoreCase("local"))) {
            try {
                stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!keepFile) {
                deleteRecording();
            }
        }
    }

    /**
     * Method to delete the recording
     */
    private void deleteRecording() {
        boolean deleted = false;
        try {
            if (currentFile.exists()) {
                deleted = currentFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (deleted)
            currentFile = null;
        else
            System.out.println("Could not delete the screen-record!");
    }
}
