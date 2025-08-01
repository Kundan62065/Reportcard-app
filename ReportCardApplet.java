import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Student extends Person {
    int[] marks;

    Student(String name, int age, int[] marks) {
        super(name, age);
        this.marks = marks;
    }

    double calculatePercentage() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return (double) total / marks.length;
    }

    String calculateGrade() {
        double percentage = calculatePercentage();
        if (percentage >= 90) return "A";
        else if (percentage >= 75) return "B";
        else if (percentage >= 50) return "C";
        else return "D";
    }
}

public class ReportCardApplet extends JFrame implements ActionListener {
    private JTextField nameField, ageField, marksField;
    private JButton submitButton;
    private JLabel resultLabel;

    public ReportCardApplet() {
        // Set the title and layout
        setTitle("Report Card Generator");
        setLayout(new FlowLayout());

        // Add components
        add(new JLabel("Name: "));
        nameField = new JTextField(15);
        add(nameField);

        add(new JLabel("Age: "));
        ageField = new JTextField(5);
        add(ageField);

        add(new JLabel("Marks (comma-separated): "));
        marksField = new JTextField(20);
        add(marksField);

        submitButton = new JButton("Generate Report");
        submitButton.addActionListener(this);
        add(submitButton);

        resultLabel = new JLabel("");
        add(resultLabel);

        // Set frame properties
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String[] marksInput = marksField.getText().split(",");
            int[] marks = new int[marksInput.length];
            for (int i = 0; i < marksInput.length; i++) {
                marks[i] = Integer.parseInt(marksInput[i].trim());
            }

            Student student = new Student(name, age, marks);
            String grade = student.calculateGrade();
            resultLabel.setText("Grade: " + grade);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input! Please enter valid age and marks.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportCardApplet app = new ReportCardApplet();
            app.setVisible(true);
        });
    }
}
