package seedu.edulink.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.edulink.commons.core.LogsCenter;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;


public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);
    private static final String FOLDER_PATH = "exports/";
    public static void convertToCsv(List<Student> students, String fileName) throws IOException {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("id,name,phone,email,address,major,intake,tags\n");
        for (Student student : students) {
            csvContent.append(student.getId().toString()).append(",")
                .append(student.getName().toString()).append(",")
                .append(student.getPhone().toString()).append(",")
                .append(student.getEmail().toString()).append(",")
                .append(student.getAddress().toString().replace(",", " |")).append(",")
                .append(student.getMajor().toString()).append(",")
                .append(student.getIntake().toString()).append(",")
                .append(student.getTags().stream()
                   .map(Tag::getTagName).collect(Collectors.joining(" | ")))
                .append("\n");
        }
        Path filePath = Paths.get(FOLDER_PATH + fileName);
        FileUtil.createIfMissing(filePath);
        FileUtil.writeToFile(filePath, csvContent.toString());
        logger.info("================= [ Exporting Data to CSV ] =========================");
    }
}
