
import Database.Rollcall;
import Database.JdbcDb;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rachel Ann Ligad
 */
public class AttendanceSheet extends javax.swing.JFrame {

    /**
     * Creates new form AttendanceSheet
     */
    public AttendanceSheet() {
        initComponents();
        tableDesign();
        Date();
        enableTime();
        tableData();
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

    DefaultTableModel tablemodel = new DefaultTableModel() {
        public boolean cellManipulation(int row, int col) {
            if (col == 1) {
                return false;
            } else {
                return false;
            }
        }
    };

    public void tableDesign() {
        String[] colNames = {"STUDENT ID", "FIRSTNAME", "LASTNAME", "SUBJECT", "TIME", "DATE"};
        attendTable = new javax.swing.JTable(tablemodel);
        attendTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        attendTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attendTable.setShowGrid(true);
        attendTable.setFillsViewportHeight(true);
        attendTable.getTableHeader().setReorderingAllowed(false);
        attendTable.setRowSelectionAllowed(true);
        attendTable.setBackground(Color.getHSBColor(195, 100, 100));
        attendTable.setFont(new java.awt.Font("Century Gothic", 0, 12));
        attendTable.setRowHeight(20);
        jScrollPane1.setViewportView(attendTable);
        JTableHeader head = attendTable.getTableHeader();
        head.setFont(new java.awt.Font("Century Gothic", 1, 12));
        head.setBackground(Color.getHSBColor(45, 100, 100));
        head.setResizingAllowed(false);
        head.setPreferredSize(new Dimension(head.WIDTH, 30));
        for (int i = 0; i < colNames.length;) {
            tablemodel.addColumn(colNames[i]);
            i++;
        }
        TableColumn[] col = new TableColumn[6];
        col[0] = attendTable.getColumnModel().getColumn(0);
        col[0].setPreferredWidth(2);
        col[1] = attendTable.getColumnModel().getColumn(1);
        col[1].setPreferredWidth(25);
        col[2] = attendTable.getColumnModel().getColumn(2);
        col[2].setPreferredWidth(25);
        col[3] = attendTable.getColumnModel().getColumn(3);
        col[3].setPreferredWidth(100);
        col[4] = attendTable.getColumnModel().getColumn(4);
        col[4].setPreferredWidth(25);
        col[5] = attendTable.getColumnModel().getColumn(5);
        col[5].setPreferredWidth(25);
    }

    public void tableData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentAttendanceSystemPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Vector row = new Vector();

        try {
            Query query = em.createNamedQuery("Rollcall.findAll");
            List<Rollcall> list = query.getResultList();

            for (Rollcall r : list) {
                Vector col = new Vector();
                col.add(r.getStudentid());
                col.add(r.getFirstname());
                col.add(r.getLastname());
                col.add(r.getSubject());
                col.add(r.getTime());
                col.add(r.getDate());
                row.add(col);
            }
            tablemodel.setRowCount(0);
            for (int i = 0; i < row.size(); i++) {
                tablemodel.addRow((Vector) row.get(i));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void searchStudByDate() {
        try {
            String search = searchField1.getText();
            
            if (search.isEmpty()){
                JOptionPane.showMessageDialog(null, "There is nothing to SEARCH!", "WARNING!", JOptionPane.WARNING_MESSAGE);
            } else {
            String[][] datas = searchStuds.Arraylist(searchStuds.searchStudentUsingDate(search));
            String[] ColNames = {"Student Id", "Firstname", "Lastname", "Subject", "Time", "Date"};

            DefaultTableModel dtm = new DefaultTableModel(datas, ColNames);
            attendTable.setModel(dtm);
            TableColumnModel Col = attendTable.getColumnModel();
            attendTable.setColumnModel(Col);

            searchField1.setText(null);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Date Existed!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AttendanceSheet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void searchStudBySubj() {
        try {
            String search = searchField2.getSelectedItem().toString();

            if (search.equalsIgnoreCase("- SELECT SUBJECT -")) {
                JOptionPane.showMessageDialog(null, "There is nothing to SEARCH!", "WARNING!", JOptionPane.WARNING_MESSAGE);
            } else {
                String[][] datas = searchStuds.Arraylist(searchStuds.searchStudentUsingSubject(search));
                String[] ColNames = {"Student Id", "Firstname", "Lastname", "Subject", "Time", "Date"};

                DefaultTableModel dtm = new DefaultTableModel(datas, ColNames);
                attendTable.setModel(dtm);
                TableColumnModel Col = attendTable.getColumnModel();
                attendTable.setColumnModel(Col);

                searchField2.setSelectedIndex(0);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Subject Existed!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AttendanceSheet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void setStudTable() {
        try {
            String search = searchField1.getText();
            String[][] datas = searchStuds.Arraylist(searchStuds.searchStudentUsingDate(search));
            String[] ColNames = {"Student Id", "Firstname", "Lastname", "Subject", "Time", "Date"};

            DefaultTableModel dtm = new DefaultTableModel(datas, ColNames);
            attendTable.setModel(dtm);
            TableColumnModel Col = attendTable.getColumnModel();
            attendTable.setColumnModel(Col);

            searchField1.setText(null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Date Existed!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AttendanceSheet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void searchStudSubjAndDate() {
        try {
            String search1 = searchField1.getText();
            String search2 = searchField2.getSelectedItem().toString();
            String search3 = searchField3.getSelectedItem().toString();

            if (search1.isEmpty() || search2.equalsIgnoreCase("- SUBJECT -") || search3.equalsIgnoreCase("- SELECT FACULTY -")) {
                JOptionPane.showMessageDialog(null, "Incomplete data OR there is nothing to SEARCH!", "ERROR 404", JOptionPane.WARNING_MESSAGE);

                searchField1.setText(null);
                searchField2.setSelectedIndex(0);
                searchField3.setSelectedIndex(0);
            } else {

                String[][] datas = searchStuds.Arraylist(searchStuds.searchStudentUsingSubjectDateFaculty(search2, search1, search3));
                String[] ColNames = {"Student Id", "Firstname", "Lastname", "Subject", "Time", "Date"};

                DefaultTableModel dtm = new DefaultTableModel(datas, ColNames);
                attendTable.setModel(dtm);
                TableColumnModel Col = attendTable.getColumnModel();
                attendTable.setColumnModel(Col);

                searchField1.setText(null);
                searchField2.setSelectedIndex(0);
                searchField3.setSelectedIndex(0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Data Existed", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AttendanceSheet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        searchField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchField2 = new javax.swing.JComboBox<>();
        searchField3 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        addStudbtn = new javax.swing.JButton();
        recordsBtn = new javax.swing.JButton();
        attendancebtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        timelbl = new javax.swing.JLabel();
        datelbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 128), 2));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Doscst.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 80));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("UltraCondensedSansSerif", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 77, 128));
        jLabel4.setText("Student Attendance System");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 430, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 80));

        jPanel4.setBackground(new java.awt.Color(0, 77, 128));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh (1).png"))); // NOI18N
        jButton3.setText("REFRESH");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 150, 30));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/print_white_192x192.png"))); // NOI18N
        jButton1.setText("PRINT");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 150, 30));

        attendTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        attendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Firstname", "Lastname", "Subject", "Time", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        attendTable.setGridColor(new java.awt.Color(0, 0, 0));
        attendTable.setSelectionBackground(new java.awt.Color(51, 153, 255));
        attendTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(attendTable);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 650, 320));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Search by Date");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, -1));

        searchField1.setBackground(new java.awt.Color(0, 77, 128));
        searchField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchField1.setForeground(new java.awt.Color(255, 255, 255));
        searchField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        searchField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchField1KeyPressed(evt);
            }
        });
        jPanel4.add(searchField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 170, 30));

        jButton2.setBackground(new java.awt.Color(0, 77, 128));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search-icon-instagram-6.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, 30, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Search by Subject");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, -1, -1));

        jButton4.setBackground(new java.awt.Color(0, 77, 128));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search-icon-instagram-6.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 100, 30, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search by Faculty");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, -1));

        searchField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        searchField2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SELECT SUBJECT -", "Object Oriented Programming", "Data Structures and Algorithm", "Web Systems and Technologies", "Platform Technology" }));
        jPanel4.add(searchField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 170, 30));

        searchField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        searchField3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- FACULTY LIST -", "Jonathan Cabrera", "Arjay Sacay", "Dony Dongiapon", "Pete Christian Reyes", "Danver Palmiano", "Leslie Bargamento", "Cindy Lasco", "John Inoco", "Lanie Laureano", "Junee Riogelon" }));
        jPanel4.add(searchField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 170, 30));

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search-icon-instagram-6.png"))); // NOI18N
        jButton5.setText("SEARCH ALL");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 150, 30));

        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attendance Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 690, 480));

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

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 200, 190));

        timelbl.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        timelbl.setForeground(new java.awt.Color(255, 255, 255));
        timelbl.setText("Time");
        jPanel4.add(timelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, -1));

        datelbl.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        datelbl.setForeground(new java.awt.Color(255, 255, 255));
        datelbl.setText("Date");
        jPanel4.add(datelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 920, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setStudTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MessageFormat header = new MessageFormat("Attendance Sheet");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try {
            attendTable.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchStudByDate();
        }
    }//GEN-LAST:event_searchField1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        searchStudByDate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        searchStudBySubj();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        searchStudSubjAndDate();
    }//GEN-LAST:event_jButton5ActionPerformed

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
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AttendanceSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AttendanceSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AttendanceSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AttendanceSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AttendanceSheet().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudbtn;
    private javax.swing.JTable attendTable;
    private javax.swing.JButton attendancebtn;
    private javax.swing.JLabel datelbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton recordsBtn;
    private javax.swing.JTextField searchField1;
    private javax.swing.JComboBox<String> searchField2;
    private javax.swing.JComboBox<String> searchField3;
    private javax.swing.JLabel timelbl;
    // End of variables declaration//GEN-END:variables

}
