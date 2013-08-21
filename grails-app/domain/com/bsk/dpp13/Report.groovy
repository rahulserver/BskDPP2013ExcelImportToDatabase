package com.bsk.dpp13

class Report {
    String repId
    String karyalayaAssignedId
    Integer versionNumber
    static hasMany = [school:School]
    static belongsTo = [samiti:Samiti]
    Boolean mailedToSamiti=false
    Boolean mailedToCenter=false
    Boolean deleted=false
    long timestamp
    Date d
    String formFile
    static constraints = {
        //repId unique: true,nullable:false
        formFile maxSize: 1024 * 1024 * 10
        repId nullable:true
        versionNumber nullable:true
        karyalayaAssignedId nullable: false
    }
    def beforeInsert(){
        this.timestamp=System.nanoTime()
    }
    def afterInsert(){
        if(this.repId==null && this.versionNumber==null){
            this.repId="${this.samiti.samitiName}#${this.id}"
            this.versionNumber=0
        }
        else{
            this.versionNumber++
        }
    }
    def getTotalsForThisReport(){
        def cl3Total=school*.cl3.sum()
        def cl4Total=school*.cl4.sum()
        def cl5Total=school*.cl5.sum()
        def cl6Total=school*.cl6.sum()
        def cl7Total=school*.cl7.sum()
        def cl8Total=school*.cl8.sum()
        def cl9Total=school*.cl9.sum()
        def cl10Total=school*.cl10.sum()
        def cl11Total=school*.cl11.sum()
        def cl12Total=school*.cl12.sum()
        def cl13Total=school*.college.sum()
        return [cl3Total: cl3Total,cl4Total:cl4Total,cl5Total:cl5Total,cl6Total:cl6Total,cl7Total:cl7Total,cl8Total:cl8Total,cl9Total:cl9Total,cl10Total:cl10Total,cl11Total:cl11Total,cl12Total:cl12Total,cl13Total:cl13Total]
    }
    def mailSender(Report report){
        mailService.sendMail{
            to:report.samiti.emailId
            subject:"Hariom"
            html '<b>Hello</b> World'
        }
    }
}
