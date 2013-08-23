package com.bsk.dpp13

class Samiti{

    String samitiName
    String emailId
    String samitiLevelSewadhariName
    Long samitiTelephoneNumber

    static belongsTo = [state:State]
    static hasMany=[report:Report]
    static constraints = {
        samitiName nullable:false,unique: true
        emailId email:true,nullable:true
        samitiLevelSewadhariName nullable:true
        samitiTelephoneNumber nullable:true
    }
    def getAggregatedReportMap(){
        def cl3Total=report*.totalsForThisReport.cl3Total.sum()
        def cl4Total=report*.totalsForThisReport.cl4Total.sum()
        def cl5Total=report*.totalsForThisReport.cl5Total.sum()
        def cl6Total=report*.totalsForThisReport.cl6Total.sum()
        def cl7Total=report*.totalsForThisReport.cl7Total.sum()
        def cl8Total=report*.totalsForThisReport.cl8Total.sum()
        def cl9Total=report*.totalsForThisReport.cl9Total.sum()
        def cl10Total=report*.totalsForThisReport.cl10Total.sum()
        def cl11Total=report*.totalsForThisReport.cl11Total.sum()
        def cl12Total=report*.totalsForThisReport.cl12Total.sum()
        def cl13Total=report*.totalsForThisReport.cl13Total.sum()
        return [cl3Total: cl3Total,cl4Total:cl4Total,cl5Total:cl5Total,cl6Total:cl6Total,cl7Total:cl7Total,cl8Total:cl8Total,cl9Total:cl9Total,cl10Total:cl10Total,cl11Total:cl11Total,cl12Total:cl12Total,cl13Total:cl13Total]
    }
}

