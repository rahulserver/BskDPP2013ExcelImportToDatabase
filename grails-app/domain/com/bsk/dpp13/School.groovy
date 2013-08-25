package com.bsk.dpp13

class School {
    String schoolName
    Integer sNo
    Integer cl3
    Integer cl4
    Integer cl5
    Integer cl6
    Integer cl7
    Integer cl8
    Integer cl9
    Integer cl10
    Integer cl11
    Integer cl12
    Integer college
    static belongsTo = [report:Report]
    static constraints = {
        cl3 min:0
        cl4 min:0
        cl5 min:0
        cl6 min:0
        cl7 min:0
        cl8 min:0
        cl9 min:0
        cl10 min:0
        cl11 min:0
        cl12 min:0
        college min:0
        sNo min:0
        schoolName nullable: false
    }
    def getGeometryBoxAndTulsiGoli(){
        (cl3>14?1:0)+(cl4>50?1:0)+(cl5>50?1:0)
    }
    def getSapsidiKaKhel(){
        (cl3>50?1:0)+(cl4>50?1:0)+(cl5>50?1:0)
    }
    def getDrawingBook(){
        (cl3>50?1:0)
    }
    def getColour(){
        (cl3>50?1:0)
    }
    def getChhotiGhadi(){
        (cl4>14?1:0)+(cl5>14?1:0)+(cl6>50?1:0)
    }
    def getKarmphalKhel(){
        (cl6>14?1:0)+(cl7>14?1:0)+(cl8>14?1:0)
    }
    def getPouch(){
        (cl6>50?1:0)+(cl7>50?1:0)+(cl8>50?1:0)+(cl9>50?1:0)+(cl10>50?1:0)+(cl11>50?1:0)+(cl12>50?1:0)
    }
    def getCollegeBag(){
        (cl9>50?1:0)+(cl10>50?1:0)+(cl11>50?1:0)+(cl12>50?1:0)+2*(college>50?1:0)
    }
    def getCollegeBagBig(){
        (cl9 in 15..50 ? 1:0)+(cl10 in 15..50 ? 1:0)+(cl11 in 15..50 ? 1:0)+(cl12 in 15..50 ? 1:0)+(college>50 ? 1:0)
    }
    def getBadiGhadi(){
        (cl7>50?1:0)+(cl8>50?1:0)+(cl9>50?1:0)+(cl10>50?1:0)+(cl11>50?1:0)+(cl12>50?1:0)
    }
    def getDiarySms(){
        (cl3+cl4+cl5+cl6+cl7+cl8+cl9+cl10+cl11+cl12+college)>=100?1:0
    }
}
