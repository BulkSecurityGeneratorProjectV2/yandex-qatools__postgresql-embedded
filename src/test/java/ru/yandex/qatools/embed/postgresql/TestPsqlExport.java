package ru.yandex.qatools.embed.postgresql;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestPsqlExport extends AbstractPsqlTest {

    @Test
    public void testPsqlExport() throws Exception {
        process.importFromFile(new File("src/test/resources/test.backup"));
        assertThat(conn, not(nullValue()));

        File fullExportDump = Files.createTempFile("full_", ".dmp").toFile();
        try {
            process.exportToFile(fullExportDump);
            assertTrue(fullExportDump.length() > 0);
        } finally {
            assertTrue(fullExportDump.delete());
        }

        File schemeDump = Files.createTempFile("scheme_", ".dmp").toFile();
        try {
            process.exportSchemeToFile(schemeDump);
            assertTrue(schemeDump.length() > 0);
        } finally {
            assertTrue(schemeDump.delete());
        }

        File dataExportDump = Files.createTempFile("data_", ".dmp").toFile();
        try {
            process.exportToFile(dataExportDump);
            assertTrue(dataExportDump.length() > 0);
        } finally {
            assertTrue(dataExportDump.delete());
        }
    }
}
