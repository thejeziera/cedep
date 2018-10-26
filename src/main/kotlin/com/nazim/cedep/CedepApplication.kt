package com.nazim.cedep

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.URL
import java.net.URLConnection
import java.util.*
//import org.apache.commons.mail.DefaultAuthenticator
//import org.apache.commons.mail.HtmlEmail

@SpringBootApplication
class CedepApplication

fun main(args: Array<String>) {
    var minutes = 5
    var cdpurl = "https://www.bankier.pl/inwestowanie/profile/quote.html?symbol=CDPROJEKT"
    runApplication<CedepApplication>(*args)
    while (true) {
        var content: String? = null
        var connection: URLConnection? = null
        try {
            connection = URL(cdpurl).openConnection()
            val scanner = Scanner(connection!!.getInputStream())
            scanner.useDelimiter("\\Z")
            content = scanner.next()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        if (content != null) {
            content = content.substring(content.indexOf("<div class=\"profilLast\">") + 24)
            content = content.substring(0, content.indexOf("<"))
            if(content.substring(0, content.indexOf(" ")).replace(",", ".").toFloat() < 130.0) {
//                val senderEmail = args[0]
//                val password = args[1]
//                val toMail = args[2]
//
//                val email = HtmlEmail()
//                email.hostName = "smtp.googlemail.com"
//                email.setSmtpPort(465)
//                email.setAuthenticator(DefaultAuthenticator(senderEmail, password))
//                email.isSSLOnConnect = true
//                email.setFrom(senderEmail)
//                email.addTo(toMail)
//                email.subject = "CDP STATUS"
//                email.setHtmlMsg("<html><h1>CDP</h1>$content</html>")
//                email.send()
            }
                print(content)
        }
        Thread.sleep(1000 * 60 * minutes.toLong())
    }
}
