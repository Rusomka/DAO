package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Client;
import ua.kiev.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            // remove this
            try {
                try (Statement st = conn.createStatement()) {
                    //st.execute("DROP TABLE IF EXISTS Clients");
                    //st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            ClientDAOImpl2 dao = new ClientDAOImpl2(conn, "Clients");
            dao.createTable(Client.class);


            List<Client> list = dao.getAll(Client.class, "name", "age");
            for (Client cli : list)
                System.out.println(cli);


            Client obj = new Client("User", 574);

            System.out.println(obj.getId());
            dao.add(obj);
            int n = obj.getId();
            System.out.println(n);


            list = dao.getAll(Client.class, "User");
            for (Client cli : list)
                System.out.println(cli);

        }
    }
}
