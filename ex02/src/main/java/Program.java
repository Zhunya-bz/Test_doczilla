import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

class Program {
    public static void PrintStudents(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");

        while (rs.next())
            System.out.println(rs.getString("id") + " " + rs.getString("name") + " " +rs.getString("surname") + " " +
                    rs.getString("patronymic") + " " + rs.getString("dateOfBirth") + " " + rs.getString("groups"));
        stmt.close();
    }

    public static void AddStudent(Connection connection, Student st) throws SQLException {

        String query = "INSERT INTO students (name, surname, patronymic, dateOfBirth, groups) VALUES (?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, st.getName());
        stmt.setString(2, st.getSurname());
        stmt.setString(3, st.getPatronymic());

        String [] str = st.getDateOfBirth().split("-");
        LocalDate stDate = LocalDate.of(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        Date date = Date.valueOf(stDate);
        stmt.setDate(4, date);
        stmt.setInt(5, st.getGroup());

        stmt.executeUpdate();
        stmt.close();
    }

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres", "postgres", "postgres");
        return connect;
    }

    public static void DeleteStudentById(Connection connection, int id) throws SQLException {
        String query = "DELETE from students WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void createTable() throws SQLException, ClassNotFoundException {
        String table = "CREATE TABLE IF NOT EXISTS students (\n" +
                "    id              SERIAL PRIMARY KEY,\n" +
                "    name            VARCHAR(100) NOT NULL,\n" +
                "    surname         VARCHAR(100) NOT NULL,\n" +
                "    patronymic      VARCHAR(100) NOT NULL,\n" +
                "    dateOfBirth     DATE NOT NULL,\n" +
                "    groups          INT NOT NULL\n" +
                ");";
        Connection connection = createConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(table);
        stmt.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        createTable();

        Student one = new Student("Masha", "Bahitova", "Ildarovna", "2000-11-03", 14);
        Student two = new Student("Roman", "Wtandorov", "Aleekseevich", "1995-10-10", 15);
        Student three = new Student("Zhunya", "Kanaeva", "Mihaylovna", "1998-08-13", 13);
        AddStudent(createConnection(), one);
        AddStudent(createConnection(), two);
        AddStudent(createConnection(), three);

        PrintStudents(createConnection());

        DeleteStudentById(createConnection(), 1);

        System.out.println("--------");
        PrintStudents(createConnection());
    }
}