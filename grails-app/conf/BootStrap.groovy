import com.bsk.dpp13.Samiti
//import javax.mail.internet.MimeMessage

class BootStrap {

    def init = { servletContext ->
        new Samiti(samitiName: "Ahmedabad",emailId: "blunderfoe@gmail.com").save(failOnError: true)
        new Samiti(samitiName: "Godhra",emailId:"bening123@gmail.com").save(failOnError: true)
        new Samiti(samitiName: "Baroda",emailId:"rahulserver@gmail.com").save(failOnError: true)
    }
    def destroy = {
    }
}
