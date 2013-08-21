package com.bsk.dpp13

import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFFont
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.usermodel.Font
import org.springframework.dao.DataIntegrityViolationException
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.apache.poi.hssf.usermodel.HSSFSheet

import java.util.concurrent.Executors


class ReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.setProperty("deleted", "false")
        params.setProperty("sort", "karyalayaAssignedId")
        params.setProperty("order", "asc")

        def repList = Report.findAllByDeleted(false, params).sort { it.karyalayaAssignedId }
        //render params
        [reportInstanceList: repList, reportInstanceTotal: repList.size()]
    }

    def uploadAndSave() {
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest) params.getRequest()
        CommonsMultipartFile file = ((CommonsMultipartFile) mpr.getFile("myExcelFile"))
        HSSFWorkbook wb = new HSSFWorkbook(file.inputStream);
        HSSFSheet sheet = wb.getSheetAt(2);       // first sheet
        def row
        def cell
        def firstRowIndex = 6
        def firstColIndex = 1
        def lastColIndex = 13
        def finishingCriteria
        def schoolCount = 0
        def schoolInstance
        def repId


        def schInstList = []
        def schSet = [] as Set<School>
        def sNo = 1
        def samitiInstance = Samiti.findById(params.samiti.id)
        def fileName = "${samitiInstance.samitiName}\\${params.karyalayaAssignedId}\\${samitiInstance.samitiName}#${params.karyalayaAssignedId}.xls"
        File f = new File(fileName)
        f.mkdirs()
        file.transferTo(f)
        params.put("formFile", "${samitiInstance.samitiName}#${params.karyalayaAssignedId}.xls")
        repId = new Report(params)
        for (def i = firstRowIndex; !finishingCriteria; i++) {
            row = sheet.getRow(i)
            def nSchool, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, cCol
            try {
                cell = row.getCell(1)
                nSchool = cell.getStringCellValue()
                if (String.valueOf(nSchool).compareTo("TOTAL") == 0) {
                    finishingCriteria = true
                    break
                }
            }
            catch (Exception e) {
                nSchool = "Sch${++schoolCount}"
            }
            try {
                cell = row.getCell(2)
                c3 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c3 = 0
            }
            try {
                cell = row.getCell(3)
                c4 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c4 = 0
            }
            try {
                cell = row.getCell(4)
                c5 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c5 = 0
            }
            try {
                cell = row.getCell(5)
                c6 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c6 = 0
            }
            try {
                cell = row.getCell(6)
                c7 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c7 = 0
            }
            try {
                cell = row.getCell(7)
                c8 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c8 = 0
            }
            try {
                cell = row.getCell(8)
                c9 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c9 = 0
            }
            try {
                cell = row.getCell(9)
                c10 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c10 = 0
            }
            try {
                cell = row.getCell(10)
                c11 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c11 = 0
            }
            try {
                cell = row.getCell(11)
                c12 = cell.getNumericCellValue()
            }
            catch (Exception e) {
                c12 = 0
            }
            try {
                cell = row.getCell(12)
                cCol = cell.getNumericCellValue()
            }
            catch (Exception e) {
                cCol = 0
            }
            def map = [schoolName: nSchool, cl3: c3, cl4: c4, cl5: c5, cl6: c6, cl7: c7,
                    cl8: c8, cl9: c9, cl10: c10, cl11: c11, cl12: c12, college: cCol, sNo: sNo]
            schoolInstance = new School(map)
            repId.addToSchool(schoolInstance)
            sNo = sNo + 1
            schInstList.add(schoolInstance)
        }
        repId.save(failOnError: true)
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                //My Background Job
                calculateAndGeneratePrizeTally(repId)
            }
        });

        flash.message = message(code: 'default.created.message', args: [message(code: 'report.label', default: 'Report'), repId.karyalayaAssignedId])
        redirect(action: "show", id: repId.id)

    }

    def ajaxDelete = {
        def reportInstance = Report.get(params.id)
        if (reportInstance) {
            try {
                reportInstance.delete(flush: true)
                render "Report ${params.id} deleted"
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                render "Report ${params.id} could not be deleted"
            }
        } else {
            flash.message = "Report not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def downloadFile() {

        def report = Report.findByKaryalayaAssignedIdAndDeleted(params.report,false)
        def pathToFile = "${report.samiti.samitiName}/${report.karyalayaAssignedId}/${params.formFile}"
        def file = new File("${pathToFile}")

        if (file.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "filename=${file.name}")
            response.outputStream << file.bytes
            println("file found")
        }
        println("${pathToFile}")
        def targetUri = params.targetUri ?: "/"
        redirect(uri: targetUri)
    }

   private def getPrizeNameListForRankClassAndStudentCount(rank, clas, sCount) {
        def prizeList = ["gb": "geometry box", "tg": "tulsi goli", "skk": "sapsidi ka khel", "db": "drawing book", "cl": "colour", "cg": "chhoti ghadi", "kk": "karmphal khel", "po": "pouch", "cb": "college bag", "cbb": "college bag(big)", "bg": "badi ghadi"]

        if (rank == 1) {
            if (clas == 3) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["gb"], prizeList["tg"]]
                } else if (sCount > 50) {
                    return [prizeList["gb"], prizeList["tg"]]
                }

            } else if (clas == 4) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["cg"]]
                } else if (sCount > 50) {
                    return [prizeList["cg"]]
                }
            } else if (clas == 5) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["cg"]]
                } else if (sCount > 50) {
                    return [prizeList["cg"]]
                }
            } else if (clas == 6) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["kk"]]
                } else if (sCount > 50) {
                    return [prizeList["kk"]]
                }
            } else if (clas == 7) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["kk"]]
                } else if (sCount > 50) {
                    return [prizeList["kk"]]
                }
            } else if (clas == 8) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["kk"]]
                } else if (sCount > 50) {
                    return [prizeList["kk"]]
                }
            } else if (clas == 9) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["cbb"]]
                } else if (sCount > 50) {
                    return [prizeList["cb"]]
                }
            } else if (clas == 10) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["cbb"]]
                } else if (sCount > 50) {
                    return [prizeList["cb"]]
                }
            } else if (clas == 11) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["cbb"]]
                } else if (sCount > 50) {
                    return [prizeList["cb"]]
                }
            } else if (clas == 12) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["cbb"]]
                } else if (sCount > 50) {
                    return [prizeList["cb"]]
                }
            } else if (clas == 13) {
                if (sCount in 0..14) {
                    return ["0"]
                } else if (sCount in 15..50) {
                    return [prizeList["cbb"]]
                } else if (sCount > 50) {
                    return [prizeList["cbb"]]
                }
            }
        } else if (rank == 2) {
            if (clas == 3) {
                if (sCount > 50) {
                    return [prizeList["skk"]]
                } else
                    return ["0"]

            } else if (clas == 4) {
                if (sCount > 50) {
                    return [prizeList["gb"], prizeList["tg"]]
                } else
                    return ["0"]
            } else if (clas == 5) {
                if (sCount > 50) {
                    return [prizeList["gb"], prizeList["tg"]]
                } else
                    return ["0"]
            } else if (clas == 6) {
                if (sCount > 50) {
                    return [prizeList["cg"]]
                } else
                    return ["0"]
            } else if (clas == 7) {
                if (sCount > 50) {
                    return [prizeList["bg"]]
                } else
                    return ["0"]
            } else if (clas == 8) {
                if (sCount > 50) {
                    return [prizeList["bg"]]
                } else
                    return ["0"]
            } else if (clas == 9) {
                if (sCount > 50) {
                    return [prizeList["bg"]]
                } else
                    return ["0"]
            } else if (clas == 10) {
                if (sCount > 50) {
                    return [prizeList["bg"]]
                } else
                    return ["0"]
            } else if (clas == 11) {
                if (sCount > 50) {
                    return [prizeList["bg"]]
                } else
                    return ["0"]
            } else if (clas == 12) {
                if (sCount > 50) {
                    return [prizeList["bg"]]
                } else
                    return ["0"]
            } else if (clas == 13) {
                if (sCount > 50) {
                    return [prizeList["cb"]]
                } else
                    return ["0"]
            }
        } else if (rank == 3) {
            if (clas == 3) {
                if (sCount > 50) {
                    return [prizeList["db"], prizeList["cl"]]
                } else
                    return ["0"]

            } else if (clas == 4) {
                if (sCount > 50) {
                    return [prizeList["skk"]]
                } else
                    return ["0"]
            } else if (clas == 5) {
                if (sCount > 50) {
                    return [prizeList["skk"]]
                } else
                    return ["0"]
            } else if (clas == 6) {
                if (sCount > 50) {
                    return [prizeList["po"]]
                } else
                    return ["0"]
            } else if (clas == 7) {
                if (sCount > 50) {
                    return [prizeList["po"]]
                } else
                    return ["0"]
            } else if (clas == 8) {
                if (sCount > 50) {
                    return [prizeList["po"]]
                } else
                    return ["0"]
            } else if (clas == 9) {
                if (sCount > 50) {
                    return [prizeList["po"]]
                } else
                    return ["0"]
            } else if (clas == 10) {
                if (sCount > 50) {
                    return [prizeList["po"]]
                } else
                    return ["0"]
            } else if (clas == 11) {
                if (sCount > 50) {
                    return [prizeList["po"]]
                } else
                    return ["0"]
            } else if (clas == 12) {
                if (sCount > 50) {
                    return [prizeList["po"]]
                } else
                    return ["0"]
            } else if (clas == 13) {
                if (sCount > 50) {
                    return [prizeList["cb"]]
                } else
                    return ["0"]
            }
        } else
            throw new Exception("Invalid argument!!")
    }

    private def calculateAndGeneratePrizeTally(Report report) {


        def schoolList = School.findAllByReport(report)
        def samitiName = report.samiti.samitiName
        def reportId = report.karyalayaAssignedId
        def originalFileName = "${samitiName}\\${reportId}\\${samitiName}#${reportId}.xls"

        HSSFWorkbook wb = new HSSFWorkbook(new BufferedInputStream(new FileInputStream(originalFileName)))

        HSSFSheet sheetOutput = wb.createSheet("Prize Tally")
        def sortedSchoolList=[]
        report.school.sort{it.sNo}.each{sortedSchoolList.add(it)}
        def i=1
        for(def j in sortedSchoolList){
            appendSchoolPrizesAndAggregateToPrizeTally(j,sheetOutput,i)
            i+=15
        }
        def fos = new FileOutputStream(originalFileName)
        wb.write(fos)

        fos.close()

    }

    private def appendSchoolPrizesAndAggregateToPrizeTally(School school, HSSFSheet hss, int beginRowIndex) {
        def row = hss.createRow(beginRowIndex)
        HSSFCellStyle hcs = hss.workbook.createCellStyle()
        HSSFFont font = hss.workbook.createFont()
        HSSFFont normalFont = hss.workbook.createFont()
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD)
        normalFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL)
        hcs.setFont(font)
        hcs.setWrapText(true)
        hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER)
        HSSFCellStyle boldTextStyle = hss.workbook.createCellStyle()
        boldTextStyle.setFont(font)
        boldTextStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER)
        boldTextStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER)

        boldTextStyle.setWrapText(true)
        HSSFCellStyle normalTextStyle = hss.workbook.createCellStyle()
        normalTextStyle.setFont(normalFont)
        normalTextStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER)
        normalTextStyle.setWrapText(true)

        row.setRowStyle(hcs)
        row.setHeightInPoints((float) (2 * hss.getDefaultRowHeightInPoints()))

        //row.createCell(0).setCellStyle(hss.workbook.createCellStyle().setFont(hss.workbook.createFont().setBoldweight(Font.BOLDWEIGHT_BOLD))
        //.setCellValue(1)

        row.createCell(3).setCellValue(school.schoolName)
        row.createCell(5).setCellValue("Total Prizes For This School")

        hss.addMergedRegion(new CellRangeAddress(
                beginRowIndex, //first row (0-based)
                beginRowIndex, //last row  (0-based)
                3, //first column (0-based)
                4  //last column  (0-based)

        ));
        hss.addMergedRegion(new CellRangeAddress(
                beginRowIndex, //first row (0-based)
                beginRowIndex, //last row  (0-based)
                1, //first column (0-based)
                2  //last column  (0-based)

        ));
        row.createCell(1)
        row.getCell(1)?.setCellValue("Vidyalaya")
        hss.addMergedRegion(new CellRangeAddress(
                beginRowIndex, //first row (0-based)
                beginRowIndex+12, //last row  (0-based)
                0, //first column (0-based)
                0  //last column  (0-based)

        ));
        row.createCell(0)
        row.getCell(0).setCellStyle(boldTextStyle)
        row.getCell(0).setCellValue(school.sNo)

        hss.addMergedRegion(new CellRangeAddress(
                beginRowIndex, //first row (0-based)
                beginRowIndex, //last row  (0-based)
                5, //first column (0-based)
                6  //last column  (0-based)

        ));


        for (def i = 0; i <= 6; i++) {
            row?.getCell(i)?.setCellStyle(boldTextStyle)
        }
        //Second Row
        row = hss.createRow(beginRowIndex + 1)
        row.createCell(2).setCellValue("First")
        row.createCell(3).setCellValue("Second")
        row.createCell(4).setCellValue("Third")
        for (def i in 2..4) {
            row.getCell(i).setCellStyle(hcs)
        }

        def aggregatePrize = []
        //Third Row
        row = hss.createRow(beginRowIndex + 2)
        row.createCell(1).setCellValue("3rd")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 3, school.cl3).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 3, school.cl3))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 3, school.cl3).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 3, school.cl3))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 3, school.cl3).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 3, school.cl3))
        row.getCell(4).setCellStyle(normalTextStyle)

        //fourth row
        row = hss.createRow(beginRowIndex + 3)
        row.createCell(1).setCellValue("4th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 4, school.cl4).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 4, school.cl4))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 4, school.cl4).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 4, school.cl4))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 4, school.cl4).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 4, school.cl4))
        row.getCell(4).setCellStyle(normalTextStyle)

        //fifth row
        row = hss.createRow(beginRowIndex + 4)
        row.createCell(1).setCellValue("5th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 5, school.cl5).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 5, school.cl5))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 5, school.cl5).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 5, school.cl5))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 5, school.cl5).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 5, school.cl5))
        row.getCell(4).setCellStyle(normalTextStyle)

        //sixth Row
        row = hss.createRow(beginRowIndex + 5)
        row.createCell(1).setCellValue("6th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 6, school.cl6).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 6, school.cl6))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 6, school.cl6).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 6, school.cl6))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 6, school.cl6).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 6, school.cl6))
        row.getCell(4).setCellStyle(normalTextStyle)

        //seventh Row
        row = hss.createRow(beginRowIndex + 6)
        row.createCell(1).setCellValue("7th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 7, school.cl7).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 7, school.cl7))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 7, school.cl7).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 7, school.cl7))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 7, school.cl7).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 7, school.cl7))
        row.getCell(4).setCellStyle(normalTextStyle)

        //eighth row
        row = hss.createRow(beginRowIndex + 7)
        row.createCell(1).setCellValue("8th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 8, school.cl8).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 8, school.cl8))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 8, school.cl8).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 8, school.cl8))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 8, school.cl8).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 8, school.cl8))
        row.getCell(4).setCellStyle(normalTextStyle)

        //nineth Row
        row = hss.createRow(beginRowIndex + 8)
        row.createCell(1).setCellValue("9th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 9, school.cl9).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 9, school.cl9))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 9, school.cl9).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 9, school.cl9))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 9, school.cl9).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 9, school.cl9))
        row.getCell(4).setCellStyle(normalTextStyle)

        //tenth Row
        row = hss.createRow(beginRowIndex + 9)
        row.createCell(1).setCellValue("10th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 10, school.cl10).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 10, school.cl10))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 10, school.cl10).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 10, school.cl10))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 10, school.cl10).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 10, school.cl10))
        row.getCell(4).setCellStyle(normalTextStyle)

        //eleventh Row

        row = hss.createRow(beginRowIndex + 10)
        row.createCell(1).setCellValue("11th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 11, school.cl11).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 11, school.cl11))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 11, school.cl11).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 11, school.cl11))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 11, school.cl11).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 11, school.cl11))
        row.getCell(4).setCellStyle(normalTextStyle)

        //twelfth Row

        row = hss.createRow(beginRowIndex + 11)
        row.createCell(1).setCellValue("12th")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 12, school.cl12).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 12, school.cl12))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 12, school.cl12).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 12, school.cl12))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 12, school.cl12).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 12, school.cl12))
        row.getCell(4).setCellStyle(normalTextStyle)

        //thirteenth Row

        row = hss.createRow(beginRowIndex + 12)
        row.createCell(1).setCellValue("College")
        row.getCell(1).setCellStyle(boldTextStyle)

        row.createCell(2).setCellValue(getPrizeNameListForRankClassAndStudentCount(1, 13, school.college).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(1, 13, school.college))
        row.getCell(2).setCellStyle(normalTextStyle)
        row.createCell(3).setCellValue(getPrizeNameListForRankClassAndStudentCount(2, 13, school.college).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(2, 13, school.college))
        row.getCell(3).setCellStyle(normalTextStyle)
        row.createCell(4).setCellValue(getPrizeNameListForRankClassAndStudentCount(3, 13, school.college).join("+\n"))
        aggregatePrize.add(getPrizeNameListForRankClassAndStudentCount(3, 13, school.college))
        row.getCell(4).setCellStyle(normalTextStyle)


        row = hss.getRow(beginRowIndex + 1)
        HSSFCell hssfCell = row.createCell(5)
        hssfCell.setCellValue("Geometry Box")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['geometry box', 'tulsi goli']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)


        row = hss.getRow(beginRowIndex + 2)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Tulsi Goli")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['geometry box', 'tulsi goli']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        //def prizeList=["gb":"geometry box","tg":"tulsi goli","skk":"sapsidi ka khel","db":"drawing book","cl":"colour","cg":"chhoti ghadi","kk":"karmphal khel","po":"pouch","cb":"college bag","cbb":"college bag(big)","bg":"badi ghadi"]

        row = hss.getRow(beginRowIndex + 3)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Color")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['drawing book', 'colour']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 4)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Drawing Book")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['drawing book', 'colour']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 5)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Sapsidi Ka\nKhel")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['sapsidi ka khel']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 6)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Chhoti Ghadi")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['chhoti ghadi']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 7)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Karmphal Khel")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['karmphal khel']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 8)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Pouch")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['pouch']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 9)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("College Bag")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['college bag']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 10)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("College Bag\n(Big)")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['college bag(big)']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        row = hss.getRow(beginRowIndex + 11)
        hssfCell = row.createCell(5)
        hssfCell.setCellValue("Badi Ghadi")
        hssfCell.setCellStyle(boldTextStyle)
        hssfCell = row.createCell(6)
        try {
            hssfCell.setCellValue(aggregatePrize.countBy { it }.get(['badi ghadi']))
        }
        catch (Exception e) {
            hssfCell.setCellValue("0")
        }
        hssfCell.setCellStyle(normalTextStyle)

        //row.createCell(5).setCellValue("Geometry Box")


        for (def i in 0..6) {

            try {
                hss.autoSizeColumn(i, true)
            } catch (Exception e) {
            }
        }


    }

    private def getPrizeMap(School schoolInstance) {
        def prMap = []
        def prSubMap = [:]
        def pName = getPrizeNameListForRankClassAndStudentCount(1, 3, schoolInstance.cl3)
        if (pName != "0") {
            prSubMap.put("I", pName)
        }
        pName = getPrizeNameListForRankClassAndStudentCount(2, 3, schoolInstance.cl4)
        if (pName != 0) {
            prSubMap.put("II", pName)
        }


    }

    private def formatter(int maxWidth = (120), ... text) {
        def cols = maxWidth / text.size()
        def idx = 0
        text.inject('') { s, t ->
            idx++
            if (t instanceof String) {
                s += t.center((int) cols)
            } else {
                switch (t.align) {
                    case 'left': s += t.text.padRight(cols, t.pad ?: ' '); break
                    case 'right': s += t.text.padLeft(cols, t.pad ?: ' '); break
                    default: s += t.text.center(cols, t.pad ?: ' ')
                }
            }
            if (s.length() < Math.ceil(cols * idx) && s.length() < maxWidth) s += ' '
            s
        }
    }

    def create() {
        [reportInstance: new Report(params)]
    }

    def save() {
        def reportInstance = new Report(params)
        if (!reportInstance.save(flush: true)) {
            render(view: "create", model: [reportInstance: reportInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'report.label', default: 'Report'), reportInstance.id])
        redirect(action: "show", id: reportInstance.id)
    }

    def show(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        [reportInstance: reportInstance]
    }

    def edit(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        [reportInstance: reportInstance]
    }

    def update(Long id, Long version) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reportInstance.version > version) {
                reportInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'report.label', default: 'Report')] as Object[],
                        "Another user has updated this Report while you were editing")
                render(view: "edit", model: [reportInstance: reportInstance])
                return
            }
        }
        reportInstance.deleted = true
        //reportInstance.properties = params

//        if (!reportInstance.save(flush: true)) {
//            render(view: "edit", model: [reportInstance: reportInstance])
//            return
//        }

        uploadAndSave()

    }

    def delete(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        try {
            reportInstance.setDeleted(true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "show", id: id)
        }
    }
    def ajaxEmail(){
        def report=Report.findById(params.id)
        def fullQualifiedFileName= "${report.samiti.samitiName}\\${report.karyalayaAssignedId}\\${report.samiti.samitiName}#${report.karyalayaAssignedId}.xls"
        try {
            sendMail{
                multipart true
                to report.samiti.emailId
                subject "Hariom!"
                html "This is the excel sheet contining the details of your report received on ${report.d.date}\\${report.d.month}\\${report.d.year}"
                attachBytes fullQualifiedFileName, "application/vnd.ms-excel", new File(fullQualifiedFileName).readBytes()
            }
            report.setMailedToSamiti(true)
            flash.message="Mail sent to Report ${report.karyalayaAssignedId} successfully!!"
        } catch (Exception e) {
          flash.message="Failed To Send Mail!! due to ${e.message} \n ${e.properties}"
        }
        redirect(uri:'/')
    }
}