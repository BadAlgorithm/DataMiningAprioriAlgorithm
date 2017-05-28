import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by calebmacdonaldblack on 28/5/17.
 */
public class Preprocessing {
    public List<String[]> convert(final String filename) throws IOException {
        final String[] lines = new String(Files.readAllBytes(Paths.get(filename))).split("\n");
        final List<String[]> splitLines = Arrays.stream(lines).map(l -> (String[])l.split(",")).collect(Collectors.toList());
        return splitLines;
    }

    private String[] splitComma(String line) {
        return line.split(",");
    }
}
