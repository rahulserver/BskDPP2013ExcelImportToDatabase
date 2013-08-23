import com.bsk.dpp13.Samiti
import com.bsk.dpp13.State
import com.bsk.dpp13.Center
//import javax.mail.internet.MimeMessage

class BootStrap {

    def init = { servletContext ->
        def state1=new State(stateName: "Gujarat").save(failOnError: true)
        def state2=new State(stateName: "Maharashtra").save(failOnError: true)
        def state3=new State(stateName: "Uttar Pradesh").save(failOnError: true)
        new Samiti(samitiName: "Ahmedabad",emailId: "blunderfoe@gmail.com",state:state1).save(failOnError: true)
        new Samiti(samitiName: "Pune",emailId:"benign123@gmail.com",state:state2).save(failOnError: true)
        new Samiti(samitiName: "Lucknow",emailId:"rahulserver@gmail.com",state:state3).save(failOnError: true)
    }
    def destroy = {
    }
}
