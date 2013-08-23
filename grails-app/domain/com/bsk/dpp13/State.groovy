package com.bsk.dpp13

class State {
    String stateName
    String emailId
    String stateLevelSewadhariName
    Long telephoneNumber
    static hasMany=[samiti:Samiti]
    static constraints = {
        emailId email:true,nullable:true
        stateName nullable:false,unique:true
        stateLevelSewadhariName nullable:true
        telephoneNumber nullable:true
    }
}
