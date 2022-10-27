package com.vti.halloween.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vti.halloween.model.CardEntity;
import com.vti.halloween.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
@Slf4j
public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public List<UserEntity> excelToUsers(InputStream is) {
        try {
//      Workbook workbook = new XSSFWorkbook(is);
//
//      Sheet sheet = workbook.getSheet(SHEET);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();

            List<UserEntity> userEntities = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                UserEntity userEntity = new UserEntity();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                        try {
                            userEntity.setCode(currentCell.getStringCellValue());
                        } catch (Exception e) {
                            userEntity.setCode(String.valueOf(currentCell.getNumericCellValue()));
                        }
                            break;

                        case 1:
                            try {
                                userEntity.setAccount(currentCell.getStringCellValue());
                            } catch (Exception e) {
                                userEntity.setAccount(String.valueOf(currentCell.getNumericCellValue()));
                            }
                            break;

                        case 2:
                            try {
                                userEntity.setFullName(currentCell.getStringCellValue());
                            } catch (Exception e) {
                                userEntity.setFullName(String.valueOf(currentCell.getNumericCellValue()));
                            }
                            break;

                        case 3:
                            try {
                                userEntity.setUnit(currentCell.getStringCellValue());
                            } catch (Exception e) {
                                userEntity.setUnit(String.valueOf(currentCell.getNumericCellValue()));
                            }
                            break;

                        default:
                            break;
                    }

                    userEntity.setPassword(passwordEncoder.encode("12345678"));

                    cellIdx++;
                }
                log.info(userEntity.getAccount());
                userEntities.add(userEntity);
            }

            workbook.close();

            return userEntities;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CardEntity> excelToCard(InputStream is) {
        try {
//      Workbook workbook = new XSSFWorkbook(is);
//
//      Sheet sheet = workbook.getSheet(SHEET);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();

            List<CardEntity> cardEntities = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                CardEntity cardEntity = new CardEntity();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            try {
                                cardEntity.setMessage(currentCell.getStringCellValue());
                            } catch (Exception e) {
                                cardEntity.setMessage(String.valueOf(currentCell.getNumericCellValue()));
                            }
                            break;

                        case 1:
                            try {
                                cardEntity.setIsLuckyCard((int) currentCell.getNumericCellValue());
                            } catch (Exception e) {
                                cardEntity.setIsLuckyCard(Integer.parseInt(currentCell.getStringCellValue()));
                            }
                            break;

                        default:
                            break;
                    }
                    cellIdx++;
                }
                log.info(cardEntity.getMessage());
                cardEntities.add(cardEntity);
            }
            workbook.close();
            return cardEntities;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
}