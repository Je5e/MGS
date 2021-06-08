package option;

import com.aspose.email.MailAddress;
import com.aspose.email.MailMessage;
import com.aspose.email.SecurityOptions;
import com.aspose.email.SmtpClient;
import com.aspose.words.Document;
import com.aspose.words.LineStyle;
import com.aspose.words.SaveFormat;
import com.aspose.words.SaveOptions;
import data.DataBase;
import helper.StatisticManager;
import start.MainProgram;
import student.Student;
import subject.Subject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateAndSendReports extends Option {

    Scanner scanner=new Scanner(System.in);
    StringBuilder reportDocument = new StringBuilder();
    public GenerateAndSendReports(Integer code) {
        this.code = code;
        this.description = "Generate and Send Reports.";
    }

    @Override
    public void executeAction() {
        System.out.println("Select the subject to work with:");
        for(Subject subject: MainProgram.getSubjects()){
            System.out.println(MessageFormat.format("{0} - {1}", subject.getId(), subject.getName()));
        }
        System.out.print("Input: ");
        int subjectSelected = Integer.parseInt(scanner.next());

        Subject subject = MainProgram.getSubeject(subjectSelected);

        System.out.println("Working with "+subject.getName());
        System.out.println("Name\tGrade");
        System.out.println("-------------------------");
        for (Student student:subject.getStudents()){
            System.out.println(MessageFormat.format("{0}\t {1}",
                    student.getName(),
                    student.getGrade()));

        }
         subjectSelected = Integer.parseInt(scanner.next());
        reportDocument.append("Name\tGrade\n");
        reportDocument.append("------------------------\n");
        List<Double> grades= new ArrayList<>();
        for (Student student:subject.getStudents()){
            grades.add(student.getGrade());
            reportDocument.append(MessageFormat.format("{0}\t {1}\n", student.getName(), student.getGrade()));
        }
        reportDocument.append("------------------------\n");
        Double avegare = StatisticManager.getAverageGrades(grades);
        reportDocument.append("Average: "+avegare+"\n");
        double minGrade = StatisticManager.getMinGrade(grades);
        reportDocument.append("Min Grade: "+minGrade+"\n");
        double maxGrade = StatisticManager.getMaxGrade(grades);
        reportDocument.append("Max Grade: "+maxGrade+"\n");
        double mostRepeated = StatisticManager.getMostRepeatedGrade(grades);
        reportDocument.append("Most Repeated: "+mostRepeated+"\n");
        double lessRepeated = StatisticManager.getLessRepeatedGrade(grades);
        reportDocument.append("Less Repeated: "+lessRepeated+"\n");

        reportDocument.append("Students with the most repeated grade: \n");
        reportDocument.append("Name\tGrade\n");
        reportDocument.append("------------------------\n");
        for (Student student:subject.getStudents()){
            if (student.getGrade()==mostRepeated){
                reportDocument.append(student.getName()+"\t"+student.getGrade()+"\n");
            }

        }
        reportDocument.append("Students with the less repeated grade: \n");
        reportDocument.append("Name\tGrade\n");
        reportDocument.append("------------------------\n");
        for (Student student:subject.getStudents()){
            if (student.getGrade()==lessRepeated){
                reportDocument.append(student.getName()+"\t"+student.getGrade()+"\n");
            }

        }
        System.out.println(reportDocument.toString());

        DataBase.writeToFile(reportDocument.toString(),"report"+subject.getName());

        try {
            Document document =new Document("report"+subject.getName());
            document.save("report.pdf",SaveFormat.PDF);
            System.out.println("Pdf file was generated.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // For complete examples and data files, please go to https://github.com/aspose-email/Aspose.Email-for-Java

// Create an instance of the MailMessage class
        MailMessage message = new MailMessage();

// Set From field, To field and Plain text body
        message.setFrom(MailAddress.to_MailAddress("ppejalvarado@gmail.com"));
        message.getTo().add("jese.alvarado@outlook.com");
        message.setBody(reportDocument.toString());

// Create an instance of the SmtpClient class
        SmtpClient client = new SmtpClient();

// And Specify your mailing host server, Username, Password and Port
        client.setHost("smtp.gmail.com");
        client.setUsername("ppejalvarado@gmail.com");
        client.setPassword("P2ssw.rd");
        client.setPort(587);
        //client.setSecurityOptions(SecurityOptions.SSLExplicit);

        try {
            // Client.Send will send this message
            client.send(message);
            System.out.println("Message sent");
        } catch (Exception ex) {
            System.err.println(ex);
        }



    }
}
