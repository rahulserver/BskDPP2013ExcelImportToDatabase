package com.bsk.dpp13

class Center {
    String centerName
    String centerLevelSewadhariName
    String emailId
    Long centerTelephoneNumber
    static belongsTo=[state:State]
    static constraints = {
        emailId email:true,nullable:true
        centerName nullable:false,unique:true
        centerLevelSewadhariName nullable:true
        centerTelephoneNumber nullable:true
    }
}
