import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class filterExcel {
    public static void main(String[] args) {
        String inputFilePath = "input.xlsx"; // Path to the input file
        String outputFilePath = "output.xlsx"; // Path where the filtered file will be saved

        try (FileInputStream fis = new FileInputStream(inputFilePath); Workbook workbook = new XSSFWorkbook(fis); Workbook newWorkbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            Sheet newSheet = newWorkbook.createSheet("Filtered Results");

            int rowCount = 0;
            for (Row row : sheet) {
                Cell firstCell = row.getCell(0);
                if (firstCell != null && firstCell.getCellType() == CellType.NUMERIC && firstCell.getNumericCellValue() > 10) {
                    Row newRow = newSheet.createRow(rowCount++);
                    copySelectedColumns(row, newRow, new int[]{0, 2}); // Indices of columns to copy (0-based)
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                newWorkbook.write(fos);
            }

            System.out.println("Filtered data saved to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copySelectedColumns(Row sourceRow, Row destRow, int[] selectedColumns) {
        for (int i : selectedColumns) {
            Cell oldCell = sourceRow.getCell(i);
            Cell newCell = destRow.createCell(destRow.getPhysicalNumberOfCells());

            if (oldCell != null) {
                switch (oldCell.getCellType()) {
                    case STRING:
                        newCell.setCellValue(oldCell.getStringCellValue());
                        break;
                    case NUMERIC:
                        newCell.setCellValue(oldCell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        newCell.setCellValue(oldCell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        newCell.setCellFormula(oldCell.getCellFormula());
                        break;
                    default:
                        newCell.setCellValue("");
                }
            }
            
            
        }
    }
}

