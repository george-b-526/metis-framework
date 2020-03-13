package eu.europeana.metis.mediaprocessing.extraction;

import eu.europeana.metis.mediaprocessing.exception.MediaExtractionException;
import eu.europeana.metis.mediaprocessing.exception.MediaProcessorException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class PdfToImageConverter {

  private static String globalGhostScriptCommand;

  private final String ghostScriptCmd;

  private final CommandExecutor commandExecutor;

  /**
   * Constructor. This is a wrapper for {@link PdfToImageConverter#PdfToImageConverter(CommandExecutor,
   * String)} where the property is detected. It is advisable to use this constructor for
   * non-testing purposes.
   *
   * @param commandExecutor A command executor.
   * @throws MediaProcessorException In case the properties could not be initialized.
   */
  PdfToImageConverter(CommandExecutor commandExecutor) throws MediaProcessorException {
    this(commandExecutor, getGlobalGhostScriptCommand(commandExecutor));
  }

  /**
   * Constructor.
   *
   * @param commandExecutor A command executor.
   * @param ghostScriptCmd The ghostscript command (how to trigger ghostscript).
   */
  PdfToImageConverter(CommandExecutor commandExecutor, String ghostScriptCmd) {
    this.ghostScriptCmd = ghostScriptCmd;
    this.commandExecutor = commandExecutor;
  }

  private static String getGlobalGhostScriptCommand(CommandExecutor commandExecutor)
          throws MediaProcessorException {
    synchronized (AudioVideoProcessor.class) {
      if (globalGhostScriptCommand == null) {
        globalGhostScriptCommand = discoverGhostScriptCommand(commandExecutor);
      }
      return globalGhostScriptCommand;
    }
  }

  static String discoverGhostScriptCommand(CommandExecutor commandExecutor)
          throws MediaProcessorException {

    // Check whether ghostscript is installed.
    final String command = "gs";
    final String output;
    output = commandExecutor.execute(Arrays.asList(command, "--version"), true,
            (message, cause) -> new MediaProcessorException(
                    "Error while looking for ghostscript tools: " + message, cause));
    if (!output.startsWith("GPL Ghostscript 9.")) {
      throw new MediaProcessorException("Ghostscript 9.x not found.");
    }

    // So it is installed and available.
    return command;
  }

  Path convertToPdf(Path content) throws MediaExtractionException {

    // Sanity checking
    if (content == null) {
      throw new MediaExtractionException("File content is null or file does not exist.");
    }

    // Prepare the new file
    final Path pdfImage;
    try {
      pdfImage = Files.createTempFile("metis_pdf_image_", null);
    } catch (IOException e) {
      throw new MediaExtractionException("Could not create temporary file.", e);
    }

    // Execute the command
    final List<String> command = createPdfConversionCommand(content, pdfImage);
    this.commandExecutor.execute(command, false, MediaExtractionException::new);

    // Return the result
    return pdfImage;
  }

  List<String> createPdfConversionCommand(Path inputFile, Path outputFile) {
    return Arrays.asList(ghostScriptCmd,
            "-q",
            "-dQUIET",
            "-dSAFER",
            "-dBATCH",
            "-dNOPAUSE",
            "-dNOPROMPT",
            "-dMaxBitmap=500000000",
            "-dAlignToPixels=0",
            "-dGridFitTT=2",
            "-sDEVICE=pngalpha",
            "-dTextAlphaBits=4",
            "-dGraphicsAlphaBits=4",
            "-r72x72",
            "-dFirstPage=1",
            "-dLastPage=1",
            "-sOutputFile=" + outputFile.toAbsolutePath().toString(),
            "-f" + inputFile.toAbsolutePath().toString()
    );
  }
}
