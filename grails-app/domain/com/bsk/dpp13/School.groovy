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
}
