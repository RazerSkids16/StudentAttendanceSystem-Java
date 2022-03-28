
import Database.JdbcDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class searchStuds {

    public static final String tableName = "rollcall";

    private String studsID;
    private String fname;
    private String lname;
    private String subj;
    private String time;
    private String date;

    public searchStuds(String studId, String firstname, String lastname, String subject, String time, String date) {
        studsID = studId;
        fname = firstname;
        lname = lastname;
        subj = subject;
        this.time = time;
        this.date = date;
    }

    public String getStudsID() {
        return studsID;
    }

    public void setStudsID(String studId) {
        studsID = studId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String first) {
        fname = first;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String last) {
        lname = last;
    }

    public String getSubject() {
        return subj;
    }

    public void setSubject(String subject) {
        subj = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static searchStuds getStudentByDate(String date) throws SQLException, Exception {

        String s = String.format("SELECT * FROM %s WHERE date %s", tableName, date);
        ResultSet rs = JdbcDb.Query(s);

        if (!rs.next()) {
            throw new Exception("Student with this date: " + date + " not found!");
        }
        searchStuds ss = getStudentFromResult(rs);
        return ss;
    }
    
    public static searchStuds getStudentByStudId(String studId) throws SQLException, Exception {

        String s = String.format("SELECT * FROM %s WHERE studentid %s", tableName, studId);
        ResultSet rs = JdbcDb.Query(s);

        if (!rs.next()) {
            throw new Exception("Student with this date: " + studId + " not found!");
        }
        searchStuds ss = getStudentFromResult(rs);
        return ss;
    }
    
    public static searchStuds getStudentBySubject(String subj) throws SQLException, Exception {

        String s = String.format("SELECT * FROM %s WHERE subject %s", tableName, subj);
        ResultSet rs = JdbcDb.Query(s);

        if (!rs.next()) {
            throw new Exception("Student with this subject: " + subj + " not found!");
        }
        searchStuds ss = getStudentFromResult(rs);
        return ss;
    }
    
    public static searchStuds getStudentBySubjectDateFaculty(String subj, String date, String faculty) throws SQLException, Exception {

        String s = String.format("SELECT * FROM %s WHERE subject %s and date %s and faculty %s", tableName, subj, date, faculty);
        ResultSet rs = JdbcDb.Query(s);

        if (!rs.next()) {
            throw new Exception("Student with this subject: " + subj + " not found! and date:" + date + " as well as: " + faculty);
        }
        searchStuds ss = getStudentFromResult(rs);
        return ss;
    }

    public static searchStuds getStudentFromResult(ResultSet res) throws SQLException {
        String studId = res.getString("studentid");
        String fname = res.getString("firstname");
        String lname = res.getString("lastname");
        String subj = res.getString("subject");
        String time = res.getString("time");
        String date = res.getString("date");

        searchStuds ss = new searchStuds(studId, fname, lname, subj, time, date);
        return ss;
    }

    public static ArrayList<searchStuds> searchStudentUsingDate(String Values) throws SQLException {
        ArrayList<searchStuds> stud = new ArrayList<>();
        String db = String.format("SELECT * from %s WHERE date LIKE \"%%%%%s%%%%\"", tableName, Values);

        ResultSet res = JdbcDb.Query(db);

        while (res.next()) {
            System.out.print("Query Found");
            searchStuds info = getStudentFromResult(res);
            System.out.println(info);
            stud.add(info);
        }
        return stud;
    }
    
    public static ArrayList<searchStuds> searchStudentUsingStudentID(String Values) throws SQLException {
        ArrayList<searchStuds> stud = new ArrayList<>();
        String db = String.format("SELECT * from %s WHERE studentid LIKE \"%%%%%s%%%%\"", tableName, Values);

        ResultSet res = JdbcDb.Query(db);

        while (res.next()) {
            System.out.print("Query Found");
            searchStuds info = getStudentFromResult(res);
            System.out.println(info);
            stud.add(info);
        }
        return stud;
    }
    
    public static ArrayList<searchStuds> searchStudentUsingSubject(String Values) throws SQLException {
        ArrayList<searchStuds> stud = new ArrayList<>();
        String db = String.format("SELECT * from %s WHERE subject LIKE \"%%%%%s%%%%\"", tableName, Values);

        ResultSet res = JdbcDb.Query(db);

        while (res.next()) {
            System.out.print("Query Found");
            searchStuds info = getStudentFromResult(res);
            System.out.println(info);
            stud.add(info);
        }
        return stud;
    }
    
    public static ArrayList<searchStuds> searchStudentUsingSubjectDateFaculty(String value1, String value2, String value3) throws SQLException {
        ArrayList<searchStuds> stud = new ArrayList<>();
        String db = "SELECT * FROM sasystem.rollcall where subject = '" + value1 + "' and date = '"+ value2+ "' and faculty = '" + value3 + "';";

        ResultSet res = JdbcDb.Query(db);

        while (res.next()) {
            System.out.print("Query Found");
            searchStuds info = getStudentFromResult(res);
            System.out.println(info);
            stud.add(info);
        }
        return stud;
    }

    public static String[][] Arraylist(ArrayList<searchStuds> ss) {
        int row = ss.size();
        int column = 6;
        String[][] size = new String[row][column];
        for (int i = 0; i < row; i++) {
            searchStuds stud = ss.get(i);

            size[i][0] = stud.getStudsID();
            size[i][1] = stud.getFname();
            size[i][2] = stud.getLname();
            size[i][3] = stud.getSubject();
            size[i][4] = stud.getTime();
            size[i][5] = stud.getDate();
        }
        return size;
    }
}
