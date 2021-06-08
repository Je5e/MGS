package option;

import com.aspose.email.MailAddress;
import com.aspose.email.MailMessage;
import com.aspose.email.SmtpClient;
import data.DataBase;
import helper.*;
import start.MainProgram;
import student.Student;
import subject.Subject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateAndSendReports extends Option {

    Scanner scanner = new Scanner(System.in);

    IConverterFile converterFile;
    ISenderEmail senderEmail;

    public GenerateAndSendReports(Integer code) {
        this.code = code;
        this.description = "Generate and Send Reports.";
        this.converterFile= new ConverterToFileImp();
        this.senderEmail=new SenderEmailImp();
    }

    @Override
    public void executeAction() {
        StringBuilder reportDocument = new StringBuilder();
        System.out.println("Select the subject to work with:");
        for (Subject subject : MainProgram.getSubjects()) {
            System.out.println(MessageFormat.format("{0} - {1}", subject.getId(), subject.getName()));
        }
        System.out.print("Input: ");
        int subjectSelected = Integer.parseInt(scanner.next());

        Subject subject = MainProgram.getSubeject(subjectSelected);
        System.out.println("Working with " + subject.getName());
        System.out.println("Name\tGrade");
        System.out.println("-------------------------");
        for (Student student : subject.getStudents()) {
            System.out.println(MessageFormat.format("{0}\t {1}",
                    student.getName(),
                    student.getGrade()));

        }
        subjectSelected = Integer.parseInt(scanner.next());
        reportDocument.append(subject.getName()+"\n");
        reportDocument.append("Name\tGrade\n");
        reportDocument.append("------------------------\n");
        List<Double> grades = new ArrayList<>();
        for (Student student : subject.getStudents()) {
            grades.add(student.getGrade());
            reportDocument.append(MessageFormat.format("{0}\t {1}\n", student.getName(), student.getGrade()));
        }
        reportDocument.append("------------------------\n");
        Double avegare = StatisticManager.getAverageGrades(grades);
        reportDocument.append("Average: " + avegare + "\n");
        double minGrade = StatisticManager.getMinGrade(grades);
        reportDocument.append("Min Grade: " + minGrade + "\n");
        double maxGrade = StatisticManager.getMaxGrade(grades);
        reportDocument.append("Max Grade: " + maxGrade + "\n");
        double mostRepeated = StatisticManager.getMostRepeatedGrade(grades);
        reportDocument.append("Most Repeated: " + mostRepeated + "\n");
        double lessRepeated = StatisticManager.getLessRepeatedGrade(grades);
        reportDocument.append("Less Repeated: " + lessRepeated + "\n");

        reportDocument.append("Students with the most repeated grade: \n");
        reportDocument.append("Name\tGrade\n");
        reportDocument.append("------------------------\n");
        for (Student student : subject.getStudents()) {
            if (student.getGrade() == mostRepeated) {
                reportDocument.append(student.getName() + "\t" + student.getGrade() + "\n");
            }

        }
        reportDocument.append("Students with the less repeated grade: \n");
        reportDocument.append("Name\tGrade\n");
        reportDocument.append("------------------------\n");
        for (Student student : subject.getStudents()) {
            if (student.getGrade() == lessRepeated) {
                reportDocument.append(student.getName() + "\t" + student.getGrade() + "\n");
            }

        }
        System.out.println(reportDocument.toString());

        DataBase.writeToFile(reportDocument.toString(), "report" + subject.getName());

        // Covert to PDF
        try {
            converterFile.convertToPDF("report" +subject.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send Email
        senderEmail.sendEmail(reportDocument.toString());


    }
}
