
import Database.JdbcDb;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import Database.Databasetable;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rachel Ann Ligad
 */
public class Enroll extends javax.swing.JFrame {

    /**
     * Creates new form Enroll
     */
    public Enroll() {
        initComponents();
        Date();
        enableTime();
        this.setLocationRelativeTo(null);
    }

    void Date() {
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("MM-dd-yyyy");
        datelbl.setText(time.format(date));
    }

    void enableTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date date = new Date();
                SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
                timelbl.setText(time.format(date));
            }
        }
        ).start();
    }
    public void addData() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentAttendanceSystemPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String stud = studIDField.getText();
        String firstname = fname.getText();
        String lastname = lname.getText();
        String midName = mName.getText();
        String section = sectionField.getText();
        Databasetable table = new Databasetable();

        if (stud.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || midName.isEmpty()
                || section.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill up all the fields!", "ERROR!",
                    JOptionPane.ERROR_MESSAGE);

        } else {
            try {

                table.setStudID(stud);
                table.setFirstname(firstname);
                table.setLastname(lastname);
                table.setMiddlename(midName);
                table.setSection(section);
                table.setImage(photo);

                em.persist(table);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "ADDED SUCCESSFULLY!", "Student Attendance System",
                        JOptionPane.INFORMATION_MESSAGE);

                studIDField.setText(null);
                fname.setText(null);
                lname.setText(null);
                sectionField.setText(null);
                mName.setText(null);
                imageArea.setIcon(null);
                imagePath.setText(null);
                studIDField.requestFocus();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "THERE IS A DUPLICATION OF ID NUMBER!", "ERROR!", JOptionPane.ERROR_MESSAGE);

            } finally {
                em.close();
                emf.close();
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        imageArea = new javax.swing.JLabel();
        imageChooser = new javax.swing.JButton();
        imagePath = new javax.swing.JTextField();
        studIDField = new javax.swing.JTextField();
        fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        mName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        sectionField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        submitBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        datelbl = new javax.swing.JLabel();
        timelbl = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        addStudbtn = new javax.swing.JButton();
        recordsBtn = new javax.swing.JButton();
        attendancebtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 128), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Doscst.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 80));

        jLabel4.setFont(new java.awt.Font("UltraCondensedSansSerif", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 77, 128));
        jLabel4.setText("Student Attendance System");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 370, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 80));

        jPanel1.setBackground(new java.awt.Color(0, 77, 128));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        imageArea.setBackground(new java.awt.Color(255, 255, 255));
        imageArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imageArea.setMaximumSize(new java.awt.Dimension(208, 212));
        imageArea.setMinimumSize(new java.awt.Dimension(208, 212));
        jDesktopPane1.add(imageArea);
        imageArea.setBounds(0, 0, 180, 170);

        jPanel1.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 180, 170));

        imageChooser.setBackground(new java.awt.Color(0, 0, 0));
        imageChooser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        imageChooser.setForeground(new java.awt.Color(255, 255, 255));
        imageChooser.setText("Add Picture");
        imageChooser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        imageChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageChooserActionPerformed(evt);
            }
        });
        jPanel1.add(imageChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 120, 30));

        imagePath.setEditable(false);
        imagePath.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(imagePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 200, 30));

        studIDField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        studIDField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        studIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studIDFieldActionPerformed(evt);
            }
        });
        studIDField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studIDFieldKeyReleased(evt);
            }
        });
        jPanel1.add(studIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 180, 30));

        fname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fnameKeyReleased(evt);
            }
        });
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 180, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student ID");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 90, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Firstname");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        mName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mNameKeyReleased(evt);
            }
        });
        jPanel1.add(mName, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 180, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Middle name");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

        lname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lnameKeyReleased(evt);
            }
        });
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 180, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lastname");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        sectionField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sectionField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sectionField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sectionFieldKeyReleased(evt);
            }
        });
        jPanel1.add(sectionField, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 180, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Section");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        submitBtn.setBackground(new java.awt.Color(0, 0, 0));
        submitBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/submit.png"))); // NOI18N
        submitBtn.setText("SUBMIT");
        submitBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(submitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 160, 40));

        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Student", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 610, 400));

        datelbl.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        datelbl.setForeground(new java.awt.Color(255, 255, 255));
        datelbl.setText("Date");
        jPanel1.add(datelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, -1, -1));

        timelbl.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        timelbl.setForeground(new java.awt.Color(255, 255, 255));
        timelbl.setText("Time");
        jPanel1.add(timelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addStudbtn.setBackground(new java.awt.Color(0, 0, 0));
        addStudbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addStudbtn.setForeground(new java.awt.Color(255, 255, 255));
        addStudbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/addstud.png"))); // NOI18N
        addStudbtn.setText("ADD STUDENT");
        addStudbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudbtnActionPerformed(evt);
            }
        });
        jPanel5.add(addStudbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 40));

        recordsBtn.setBackground(new java.awt.Color(0, 0, 0));
        recordsBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        recordsBtn.setForeground(new java.awt.Color(255, 255, 255));
        recordsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/record.png"))); // NOI18N
        recordsBtn.setText("RECORDS");
        recordsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordsBtnActionPerformed(evt);
            }
        });
        jPanel5.add(recordsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 40));

        attendancebtn.setBackground(new java.awt.Color(0, 0, 0));
        attendancebtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        attendancebtn.setForeground(new java.awt.Color(255, 255, 255));
        attendancebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/time-icon-white.png"))); // NOI18N
        attendancebtn.setText("ATTENDANCE");
        attendancebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendancebtnActionPerformed(evt);
            }
        });
        jPanel5.add(attendancebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 40));

        logoutBtn.setBackground(new java.awt.Color(0, 0, 0));
        logoutBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LogOut-icon.png"))); // NOI18N
        logoutBtn.setText("LOG OUT");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        jPanel5.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 200, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 200, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 850, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imageChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageChooserActionPerformed
        JFileChooser choose = new JFileChooser();
        choose.showOpenDialog(null);
        File f = choose.getSelectedFile();
        imageArea.setIcon(new ImageIcon(f.toString()));
        filename = f.getAbsolutePath();
        imagePath.setText(filename);

        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1 ;){
                bos.write(buf, 0, readNum);
            }
            photo = bos.toByteArray();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_imageChooserActionPerformed

    private void studIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studIDFieldActionPerformed

    }//GEN-LAST:event_studIDFieldActionPerformed

    private void studIDFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studIDFieldKeyReleased
        studIDField.setText(studIDField.getText().toUpperCase());
    }//GEN-LAST:event_studIDFieldKeyReleased

    private void fnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fnameKeyReleased
        // TODO add your handling code here:
        fname.setText(fname.getText().toUpperCase());
    }//GEN-LAST:event_fnameKeyReleased

    private void mNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mNameKeyReleased
        // TODO add your handling code here:
        mName.setText(mName.getText().toUpperCase());
    }//GEN-LAST:event_mNameKeyReleased

    private void lnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnameKeyReleased
        // TODO add your handling code here:
        lname.setText(lname.getText().toUpperCase());
    }//GEN-LAST:event_lnameKeyReleased

    private void sectionFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sectionFieldKeyReleased
        // TODO add your handling code here:
        sectionField.setText(sectionField.getText().toUpperCase());
    }//GEN-LAST:event_sectionFieldKeyReleased

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        addData();
    }//GEN-LAST:event_submitBtnActionPerformed

    private void addStudbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudbtnActionPerformed
        Enroll add = new Enroll();
        dispose();
        add.setVisible(true);
    }//GEN-LAST:event_addStudbtnActionPerformed

    private void recordsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordsBtnActionPerformed
        AttendanceSheet sheet = new AttendanceSheet();
        dispose();
        sheet.setVisible(true);
    }//GEN-LAST:event_recordsBtnActionPerformed

    private void attendancebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendancebtnActionPerformed
        TimeIn time = new TimeIn();
        dispose();
        time.setVisible(true);
    }//GEN-LAST:event_attendancebtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "LOG OUT?", "Student Attendance System",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (reply == JOptionPane.YES_OPTION) {
            Login log = new Login();
            dispose();
            log.setVisible(true);
        } else {

        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Enroll.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Enroll.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Enroll.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Enroll.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Enroll().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudbtn;
    private javax.swing.JButton attendancebtn;
    private javax.swing.JLabel datelbl;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel imageArea;
    private javax.swing.JButton imageChooser;
    private javax.swing.JTextField imagePath;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField lname;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTextField mName;
    private javax.swing.JButton recordsBtn;
    private javax.swing.JTextField sectionField;
    private javax.swing.JTextField studIDField;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel timelbl;
    // End of variables declaration//GEN-END:variables
byte[] photo = null;
    String filename = null;

}
