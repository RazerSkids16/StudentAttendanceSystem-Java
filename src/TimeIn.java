
import Database.Databasetable;
import Database.Rollcall;
import Database.JdbcDb;
import com.sun.glass.events.KeyEvent;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rachel Ann Ligad
 */
public class TimeIn extends java.awt.Frame {

    /**
     * Creates new form TimeIn
     */
    public TimeIn() {
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

    public void record() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentAttendanceSystemPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            String faculty = facultyBox.getSelectedItem().toString();
            String subj = subjectBox.getSelectedItem().toString();
            String room = roomBox.getSelectedItem().toString();

            try {
                Rollcall table = new Rollcall();
                if (firstnameField.getText().isEmpty() || lastnameField.getText().isEmpty() || faculty.equalsIgnoreCase("- FACULTY LIST -")
                        || subj.equalsIgnoreCase("- SELECT SUBJECT -") || room.equalsIgnoreCase("- SELECT ROOM -") || studID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill up all the fields!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    studID.setText(null);
                    subjectBox.setSelectedIndex(0);
                    facultyBox.setSelectedIndex(0);
                    roomBox.setSelectedIndex(0);
                    firstnameField.setText(null);
                    lastnameField.setText(null);
                    imageArea.setIcon(null);
                    studID.requestFocus();
                } else {
                    table.setStudentid(studID.getText().trim());
                    table.setFirstname(firstnameField.getText().trim());
                    table.setLastname(lastnameField.getText().trim());
                    table.setFaculty(facultyBox.getSelectedItem().toString());
                    table.setSubject(subjectBox.getSelectedItem().toString());
                    table.setRoom(roomBox.getSelectedItem().toString());
                    table.setTime(timelbl.getText().trim());
                    table.setDate(datelbl.getText().trim());

                    em.persist(table);
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "TIME IN!", "Student Attendance System",
                            JOptionPane.INFORMATION_MESSAGE);

                    studID.setText(null);
                    subjectBox.setSelectedIndex(0);
                    facultyBox.setSelectedIndex(0);
                    roomBox.setSelectedIndex(0);
                    firstnameField.setText(null);
                    lastnameField.setText(null);
                    imageArea.setIcon(null);
                    studID.requestFocus();

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
            } finally {
                em.close();
                emf.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void searchID() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentAttendanceSystemPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            String id = studID.getText();

            try {
                Query query = em.createNamedQuery("Databasetable.findByStudID");
                query.setParameter("studID", id);
                List<Databasetable> list = query.getResultList();

                for (Databasetable d : list) {
                    firstnameField.setText(d.getFirstname());
                    lastnameField.setText(d.getLastname());
                }

            } catch (Exception e) {
            } finally {
                em.close();
                emf.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        imageArea = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        firstnameField = new javax.swing.JTextField();
        facultyBox = new javax.swing.JComboBox<>();
        subjectBox = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        lastnameField = new javax.swing.JTextField();
        roomBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        studID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        datelbl = new javax.swing.JLabel();
        timelbl = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        addStudbtn = new javax.swing.JButton();
        recordsBtn = new javax.swing.JButton();
        attendancebtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Student Attendance System");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Doscst.png"))); // NOI18N

        setName(""); // NOI18N
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 128), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Doscst.png"))); // NOI18N
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 80));

        jLabel14.setFont(new java.awt.Font("UltraCondensedSansSerif", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 77, 128));
        jLabel14.setText("Student Attendance System");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 370, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 80));

        jPanel1.setBackground(new java.awt.Color(0, 77, 128));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageArea.setBackground(new java.awt.Color(153, 153, 153));
        imageArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(imageArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 180, 160));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("FIRSTNAME");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        firstnameField.setEditable(false);
        firstnameField.setBackground(new java.awt.Color(204, 204, 204));
        firstnameField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        firstnameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        firstnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(firstnameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 180, 30));

        facultyBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        facultyBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- FACULTY LIST -", "Jonathan Cabrera", "Arjay Sacay", "Dony Dongiapon", "Pete Christian Reyes", "Danver Palmiano", "Leslie Bargamento", "Cindy Lasco", "John Inoco", "Lanie Laureano", "Junee Riogelon" }));
        facultyBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyBoxActionPerformed(evt);
            }
        });
        jPanel1.add(facultyBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 200, 30));

        subjectBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        subjectBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SELECT SUBJECT -", "Object Oriented Programming", "Data Structure", "Platform Technologies", "Web Systems and Technology" }));
        subjectBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(subjectBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 200, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("LASTNAME");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, -1, -1));

        lastnameField.setEditable(false);
        lastnameField.setBackground(new java.awt.Color(204, 204, 204));
        lastnameField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lastnameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lastnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(lastnameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 180, 30));

        roomBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        roomBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SELECT ROOM -", "ICT room 1", "ICT room 2", "ICT room 3", "ComLab 1", "ComLab 2", "ComLab 3", "ComLab 4", "Multi/Mac Lab", "NetworkLab" }));
        roomBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(roomBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 200, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Enter ID Number");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, -1, -1));

        studID.setBackground(new java.awt.Color(0, 77, 128));
        studID.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        studID.setForeground(new java.awt.Color(255, 255, 255));
        studID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        studID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studIDKeyReleased(evt);
            }
        });
        jPanel1.add(studID, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 180, 40));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("TIME IN");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Faculty");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Subject");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Room");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, -1, -1));

        datelbl.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        datelbl.setForeground(new java.awt.Color(255, 255, 255));
        datelbl.setText("Date");
        jPanel1.add(datelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, -1, -1));

        timelbl.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        timelbl.setForeground(new java.awt.Color(255, 255, 255));
        timelbl.setText("Time");
        jPanel1.add(timelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, -1, -1));

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

        jLabel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Attendance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 560, 430));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void firstnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnameFieldActionPerformed

    private void facultyBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facultyBoxActionPerformed

    private void lastnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameFieldActionPerformed

    private void studIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studIDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            record();
        }
    }//GEN-LAST:event_studIDKeyPressed

    private void studIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studIDKeyReleased
        searchID();
        searchImage();
    }//GEN-LAST:event_studIDKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        record();
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void attendancebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendancebtnActionPerformed
        TimeIn time = new TimeIn();
        dispose();
        time.setVisible(true);
    }//GEN-LAST:event_attendancebtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TimeIn().setVisible(true);
//            }
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudbtn;
    private javax.swing.JButton attendancebtn;
    private javax.swing.JLabel datelbl;
    private javax.swing.JComboBox<String> facultyBox;
    private javax.swing.JTextField firstnameField;
    private javax.swing.JLabel imageArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField lastnameField;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton recordsBtn;
    private javax.swing.JComboBox<String> roomBox;
    private javax.swing.JTextField studID;
    private javax.swing.JComboBox<String> subjectBox;
    private javax.swing.JLabel timelbl;
    // End of variables declaration//GEN-END:variables

    public void searchImage(){
        String search = studID.getText();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sasystem", "root", "");
            String sql = "SELECT `image` FROM `databasetable` WHERE studID = '" + search +"'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()){
                byte[] image = rs.getBytes("image");
                ImageIcon image1 = new ImageIcon(image);
                Image mm = image1.getImage();
                Image img = mm.getScaledInstance(imageArea.getWidth(), imageArea.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image2 = new ImageIcon(img);
                imageArea.setIcon(image2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
