/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.datastructuresca1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author craig
 */
public class PriorityGUI extends javax.swing.JFrame {

    ArrayList<String> months;
    PriorityQueue pQueue;
    String date;

    /**
     * Creates new form PriorityGUI
     */
    public PriorityGUI() {
        initComponents();

        months = new ArrayList<String>();
        pQueue = new PriorityQueue();
        loadData();
        //loadYears();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GUIBackground = new java.awt.Panel();
        heading = new javax.swing.JLabel();
        NameLbl = new javax.swing.JLabel();
        DOBLabel = new javax.swing.JLabel();
        dateOfBirthLbl = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        conditionsInput = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        clearButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        showAllButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        nextPatientButton = new javax.swing.JButton();
        day = new javax.swing.JComboBox<>();
        month = new javax.swing.JComboBox<>();
        year = new javax.swing.JComboBox<>();
        patientNumberButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        GUIBackground.setBackground(new java.awt.Color(102, 102, 255));

        heading.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        heading.setForeground(new java.awt.Color(255, 255, 255));
        heading.setText("Priority Queue Application");

        NameLbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        NameLbl.setForeground(new java.awt.Color(204, 204, 255));
        NameLbl.setText("Patient Name");

        DOBLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        DOBLabel.setForeground(new java.awt.Color(204, 204, 255));
        DOBLabel.setText("Does Patient have any medical conditions?");

        dateOfBirthLbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        dateOfBirthLbl.setForeground(new java.awt.Color(204, 204, 255));
        dateOfBirthLbl.setText("Date Of Birth (DD/MM/YYY)");

        nameInput.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        nameInput.setText("Fadi");
        nameInput.setToolTipText("");

        conditionsInput.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        conditionsInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Yes", "No" }));

        output.setEditable(false);
        output.setColumns(20);
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        clearButton.setBackground(new java.awt.Color(0, 0, 0));
        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(0, 0, 0));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        showAllButton.setBackground(new java.awt.Color(0, 0, 0));
        showAllButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        showAllButton.setForeground(new java.awt.Color(255, 255, 255));
        showAllButton.setText("Show All Queue");
        showAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllButtonActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(0, 0, 0));
        exitButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Exit");

        nextPatientButton.setBackground(new java.awt.Color(0, 0, 0));
        nextPatientButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nextPatientButton.setForeground(new java.awt.Color(255, 255, 255));
        nextPatientButton.setText("Next Patient");
        nextPatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPatientButtonActionPerformed(evt);
            }
        });

        day.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        day.setToolTipText("select day");

        month.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", " " }));
        month.setToolTipText("select month");
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        year.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901" }));
        year.setToolTipText("select year");

        patientNumberButton.setBackground(new java.awt.Color(0, 0, 0));
        patientNumberButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        patientNumberButton.setForeground(new java.awt.Color(255, 255, 255));
        patientNumberButton.setText("No. of Patients to be vaccinated");
        patientNumberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientNumberButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GUIBackgroundLayout = new javax.swing.GroupLayout(GUIBackground);
        GUIBackground.setLayout(GUIBackgroundLayout);
        GUIBackgroundLayout.setHorizontalGroup(
            GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GUIBackgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(DOBLabel)
                .addGap(231, 231, 231))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GUIBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GUIBackgroundLayout.createSequentialGroup()
                        .addComponent(heading)
                        .addGap(318, 318, 318))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GUIBackgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
            .addGroup(GUIBackgroundLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showAllButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextPatientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(GUIBackgroundLayout.createSequentialGroup()
                .addGroup(GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GUIBackgroundLayout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GUIBackgroundLayout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(NameLbl))
                    .addGroup(GUIBackgroundLayout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(patientNumberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GUIBackgroundLayout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addGroup(GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(conditionsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(GUIBackgroundLayout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(dateOfBirthLbl)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        GUIBackgroundLayout.setVerticalGroup(
            GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GUIBackgroundLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(heading)
                .addGap(53, 53, 53)
                .addComponent(NameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(DOBLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conditionsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(dateOfBirthLbl)
                .addGap(18, 18, 18)
                .addGroup(GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(GUIBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextPatientButton)
                    .addComponent(clearButton)
                    .addComponent(showAllButton)
                    .addComponent(addButton)
                    .addComponent(exitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientNumberButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GUIBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GUIBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loadYears() {
        for (int i = 2024; i > 1900; i--) {
            year.addItem(String.valueOf(i - 1));
        }
    }

    public void loadData() {
        months.add("31");
        months.add("28");
        months.add("31");
        months.add("30");
        months.add("31");
        months.add("30");
        months.add("31");
        months.add("31");
        months.add("30");
        months.add("31");
        months.add("30");
        months.add("31");

    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        //I had issuse with the date when it was in dd/mm/yyyy format so I decided to take another approach by breaking the day, months and years columns seperately
        //I kept the format on the gui of dd/mm/yyyy and rearranged them in the date variable and could then the Date function properly
        date = String.valueOf(year.getSelectedItem()) + "-" + String.valueOf(month.getSelectedItem()) + "-" + String.valueOf(day.getSelectedItem());

        
        //Ceck that no submitted fields are empty
        if (emtpyValidation(nameInput.getText()) == false
                || emtpyValidation(String.valueOf(conditionsInput.getSelectedItem())) == false
                || emtpyValidation(String.valueOf(day.getSelectedItem())) == false
                || emtpyValidation(String.valueOf(month.getSelectedItem())) == false
                || emtpyValidation(String.valueOf(year.getSelectedItem())) == false) {

            output.setText("");
            output.setText("Please fill In All Fields");
            return;
        }

        //Check that the string date is valid date format
        if (dateValidation(date) == false) {
            output.setText("");
            output.setText("Please enter a valid Date Of Birth");
            return;

        }
        
        if (getAge(date) < 0) {
            output.setText("The Date enter means you are not born yet");
            return;
        }
        //Chech that the date entered is a date in th past
        if (pastDateValidation(date) == false) {
            output.setText("You have entered a Date in the future, Please enter a past date");
            return;
        }

        
        

        output.setText(pQueue.enqueue(createPatient()));


    }//GEN-LAST:event_addButtonActionPerformed

    public Patient createPatient() {
        //This method creates a patient object and returns it.
        Patient p = new Patient();
        p.setName(nameInput.getText());
        p.setPriority(setPriority(getAge(date)));

        if (String.valueOf(conditionsInput.getSelectedItem()).matches("Yes")) {
            p.setMedicalCondition(true);
        }
        if (String.valueOf(conditionsInput.getSelectedItem()).matches("No")) {
            p.setMedicalCondition(false);
        }

        return p;
    }

    public int setPriority(int age) {
        // This mothod is used to determine the users priority by the day of birth and does the patient have any medical conditions 
        if (age > 18 && age < 64 && String.valueOf(conditionsInput.getSelectedItem()).matches("Yes")) {
            return 6;
        }
        if (age < 18 && age >= 0) {
            return 1;
        }
        if (age > 18 && age < 30) {
            return 2;
        }
        if (age > 29 && age < 45) {
            return 3;
        }
        if (age > 44 && age < 56) {
            return 4;
        }
        if (age > 54 && age < 65) {
            return 5;
        }
        if (age > 64 && age < 70) {
            return 7;
        }
        if (age > 69 && age < 80) {
            return 8;
        }
        if (age > 79 && age < 90) {
            return 9;
        }
        if (age > 89) {
            return 10;
        }
        return 0;
    }
    private void showAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllButtonActionPerformed
        output.setText(pQueue.printPQueue());
    }//GEN-LAST:event_showAllButtonActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed

        //This method determines the days available in the days combo box
        //I stored values in an arraylist that represent the days of each month
        //The user picks a month. When clicked it removes all items from the combo box
        //Then loop through the arraylist value selected in the month box
        //Then the number of days required is added to the combo box with each loop.
        String mont = String.valueOf(month.getSelectedItem());
        int months2 = Integer.parseInt(mont);
        String numbersss = months.get(months2 - 1);
        int num = Integer.parseInt(numbersss);

        day.removeAllItems();

        for (int i = 0; i < num; i++) {
            if ((i) + 1 < 10) {
                day.addItem(String.valueOf("0" + ((i) + 1)));
            }
            if ((i) + 1 > 10) {

                day.addItem(String.valueOf((i) + 1));
            }

        }
    }//GEN-LAST:event_monthActionPerformed

    private void patientNumberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientNumberButtonActionPerformed
        // This method prints the number patients to be vaccinated

        output.setText("");
        output.setText("There are " + pQueue.queueSize() + " patients in the Queue to be vaccinated");
    }//GEN-LAST:event_patientNumberButtonActionPerformed

    private void nextPatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPatientButtonActionPerformed
        //This method prints the next patient to be vaccinated and removes them frm the queue
        output.setText("");
        if (pQueue.queueIsEmpty()) {
            output.setText("There are no patients in the Queue to be vaccinated");
            return;
        }
        output.setText(pQueue.nextPatient());
    }//GEN-LAST:event_nextPatientButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        //This method clears the output text area and patients name
        output.setText("");
        nameInput.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    public LocalDate changeDate(String date) {

        //This method converts the string date into a date object after validated and returns it
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(date, formatter);
        return birthDate;
    }

    public boolean pastDateValidation(String date) {

        // This method checks if the date of birth entered is in the future of past
        // This isBefore function was found on :     https://geeksforgeeks.org/localdate-isbefore-method-in-java-with-examples/
        LocalDate dob = changeDate(date);
        LocalDate currentDate = LocalDate.now();

        return dob.isBefore(currentDate);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PriorityGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PriorityGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PriorityGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PriorityGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PriorityGUI().setVisible(true);
            }
        });
    }

    public Boolean emtpyValidation(String input) {

        //This method checks if a input entered and submitted is empty.
        if (input.matches("")) {
            return false;
        }
        return true;
    }

    public Boolean dateValidation(String date) {
        //This method checks if the date of birth entered in string format matches a valid date formate.
        // Regex was used determine if the string was a valid date 
        //Regex was found on a previous project done in a previous course. I dont not have direct access to the link but there are many links available to validate a string date.
        //Eg. https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
        String validDate = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        if (!date.matches(validDate)) {
            return false;
        }

        return true;
    }

    public int getAge(String date) {

        //this method subtracts the current dates year from the date of birth year to find out the age of the person
        //The below link was used to get the correct fuction name to get the years between two dates
        // https://www.tutorialspoint.com/localdate-getyear-method-in-java#:~:text=The%20year%20for%20a%20particular,range%20from%20MIN_YEAR%20to%20MAX_YEAR.
        LocalDate birthDate = changeDate(date);

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);

        int age = period.getYears();
        System.out.println("Age in years: " + age);

        return age;

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DOBLabel;
    private java.awt.Panel GUIBackground;
    private javax.swing.JLabel NameLbl;
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> conditionsInput;
    private javax.swing.JLabel dateOfBirthLbl;
    private javax.swing.JComboBox<String> day;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel heading;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JTextField nameInput;
    private javax.swing.JButton nextPatientButton;
    private javax.swing.JTextArea output;
    private javax.swing.JButton patientNumberButton;
    private javax.swing.JButton showAllButton;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
