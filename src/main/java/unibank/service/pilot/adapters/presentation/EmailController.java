//package unibank.service.pilot.adapters.presentation;
//
//import jakarta.mail.MessagingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import unibank.service.pilot.domain.ReportRequest;
//import unibank.service.pilot.services.EmailService;
//
//
//@RestController
//@RequestMapping("/api")
//public class EmailController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/sendReport")
//    public String sendReport(@RequestBody ReportRequest reportRequest) {
//        String to = reportRequest.getEmail();
//        String subject = "API Test Report";
//        String text = "Passed Tests:\n" + reportRequest.getPassedTests() + "\n\nFailed Tests:\n" + reportRequest.getFailedTests();
//
//        try {
//            emailService.sendEmail(to, subject, text);
//            return "Report sent successfully!";
//        } catch (MessagingException e) {
//            return "Failed to send report.";
//        }
//    }
//}
