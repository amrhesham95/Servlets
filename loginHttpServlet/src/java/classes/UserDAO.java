/*
 * This an implementation for the CRUD operations done on the user table in the DB.
 */
package classes;

import com.mysql.cj.jdbc.MysqlDataSource;
import classes.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.UserDAOInterface;
import classes.DataSourceFactory;


/**
 *
 * @author Mohamed Jamal
 */
public class UserDAO implements UserDAOInterface {

    MysqlDataSource mysqlDataSource = DataSourceFactory.getMySQLDataSource();

    PreparedStatement preparedStatementForUpdate = null;
    PreparedStatement preparedStatementForDelete = null;
    PreparedStatement registerPreparedStatement;
    boolean registerDone = false;
    int registerDoneint = 0;
    String updateQuery = "update chatdb.user set Name = ? , Gender = ? , "
            + " Country = ? , DOB = ?  , Password = ? ,"
            + " Email = ? , BIO = ? , Mode = ?, Picture = ?, ChatBotStatus = ?, Status = ?  , EntryTimes = ? where PhoneNum = ? ";

    String deleteQuery = " Delete from chatdb.user where PhoneNum = ? ";

    public UserDAO() {
    }

    @Override
    public boolean updateUser(User user) {
        boolean updateDone = false;

        try {
            preparedStatementForUpdate = mysqlDataSource.getConnection().prepareStatement(updateQuery);
            preparedStatementForUpdate.setString(1, user.getName());
            preparedStatementForUpdate.setString(2, user.getGender());
            preparedStatementForUpdate.setString(3, user.getCountry());
            preparedStatementForUpdate.setString(4, user.getDateOfBirth());
            preparedStatementForUpdate.setString(5, user.getPassword());
            preparedStatementForUpdate.setString(6, user.getEmail());
            preparedStatementForUpdate.setString(7, user.getBIO());
            preparedStatementForUpdate.setString(8, user.getMode());
            preparedStatementForUpdate.setBytes(9, user.getPicture());
            preparedStatementForUpdate.setInt(10, user.getChatBotStatus());
            preparedStatementForUpdate.setString(11, user.getStatus());
            preparedStatementForUpdate.setInt(12, user.getEntryTimes());
            preparedStatementForUpdate.setString(13, user.getPhoneNum());

            int check = preparedStatementForUpdate.executeUpdate();

            if (check == 1) {
                updateDone = true;
            } else {
                updateDone = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return updateDone;
    }

    @Override
    public boolean deleteUser(User user) {
        boolean checkDelete = false;

        try {
            preparedStatementForDelete = mysqlDataSource.getConnection().prepareStatement(deleteQuery);
            preparedStatementForDelete.setString(1, user.getPhoneNum());

            int check = preparedStatementForDelete.executeUpdate();

            if (check == 1) {
                checkDelete = true;
            } else {
                checkDelete = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return checkDelete;
    }

    ///////AMR function goes here
    @Override
    public boolean addUser(User user) {
        try {
            System.out.println("inside add user in service implementaiton");
            String insertQuery = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            registerPreparedStatement = mysqlDataSource.getConnection().prepareStatement(insertQuery);
            try {

                registerPreparedStatement.setString(1, user.getName());
                registerPreparedStatement.setString(2, user.getPhoneNum());
                registerPreparedStatement.setString(3, user.getGender());
                registerPreparedStatement.setString(4, user.getCountry());
                registerPreparedStatement.setString(5, user.getDateOfBirth());
                registerPreparedStatement.setString(6, null);
                registerPreparedStatement.setString(7, user.getPassword());
                registerPreparedStatement.setString(8, "offline");
                registerPreparedStatement.setString(9, "1");
                registerPreparedStatement.setString(10, user.getEmail());
                registerPreparedStatement.setString(11, user.getBIO());
                registerPreparedStatement.setString(12, "available");
                registerPreparedStatement.setInt(13, 0);

                registerDoneint = registerPreparedStatement.executeUpdate();
                System.out.println(registerDoneint);
                if (registerDoneint != 0) {
                    registerDone = true;

                }
            } catch (SQLException ex) {
                registerDone = false;
                ex.printStackTrace();
            } finally {
                return registerDone;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User retrieveUser(String phoneNumber) {
        User user = null;
        try {

            String query = " select Name, PhoneNum, Gender, Country, DOB, Picture, Password, Status, ChatBotStatus, Email, BIO ,Mode , EntryTimes from user where PhoneNum = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            preparedStmt.setString(1, phoneNumber);

            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (!resultSet.next()) {
                System.out.println("Doesn't Exist");
            } else {

                String Name = resultSet.getString(1);
                System.out.println("Exist Login successfully -> " + Name);

                user = new User();
                user.setName(resultSet.getString(1));
                user.setPhoneNum(resultSet.getString(2));
                user.setGender(resultSet.getString(3));
                user.setCountry(resultSet.getString(4));
                user.setDateOfBirth(resultSet.getString(5));
                //user.setPicture(resultSet.getBytes(6));
                user.setPassword(resultSet.getString(7));
                user.setStatus(resultSet.getString(8));
                user.setChatBotStatus(resultSet.getInt(9));
                user.setEmail(resultSet.getString(10));
                user.setBIO(resultSet.getString(11));
                user.setMode(resultSet.getString(12));
                user.setEntryTimes(resultSet.getInt(13));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return user;
        }
    }

    @Override
    public boolean updateEntryTimes(User user) {
        boolean updateDone = false;

        try {
            String query = "update chatdb.user set EntryTimes = ? where PhoneNum = ? ";

            preparedStatementForUpdate = mysqlDataSource.getConnection().prepareStatement(query);
            preparedStatementForUpdate.setInt(1, user.getEntryTimes());
            preparedStatementForUpdate.setString(2, user.getPhoneNum());

            int check = preparedStatementForUpdate.executeUpdate();

            if (check == 1) {
                updateDone = true;
            } else {
                updateDone = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return updateDone;
    }

    @Override
    public boolean updateStatus(User user) {
        boolean updateDone = false;

        try {
            String query = "update chatdb.user set Status = ? where PhoneNum = ? ";

            preparedStatementForUpdate = mysqlDataSource.getConnection().prepareStatement(query);
            preparedStatementForUpdate.setString(1, user.getStatus());
            preparedStatementForUpdate.setString(2, user.getPhoneNum());

            int check = preparedStatementForUpdate.executeUpdate();

            if (check == 1) {
                updateDone = true;
            } else {
                updateDone = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return updateDone;
    }

    @Override
    public boolean updateMode(User user) {
        boolean updateDone = false;

        try {
            String query = "update chatdb.user set Mode = ? where PhoneNum = ? ";

            preparedStatementForUpdate = mysqlDataSource.getConnection().prepareStatement(query);
            preparedStatementForUpdate.setString(1, user.getMode());
            preparedStatementForUpdate.setString(2, user.getPhoneNum());

            int check = preparedStatementForUpdate.executeUpdate();

            if (check == 1) {
                System.out.println("update mode done");
                updateDone = true;
            } else {
                System.out.println("update mode is failed");

                updateDone = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return updateDone;
    }
    
    @Override
    public User retrieveUserBoth(String phoneNumber,String password) {
        User user = null;
        try {

            String query = " select Name, PhoneNum, Gender, Country, DOB, Picture, Password, Status, ChatBotStatus, Email, BIO ,Mode , EntryTimes from user where PhoneNum = ? And Password = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = mysqlDataSource.getConnection().prepareStatement(query);
            preparedStmt.setString(1, phoneNumber);
            preparedStmt.setString(2, password);
            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet resultSet = preparedStmt.getResultSet();

            if (!resultSet.next()) {
                System.out.println("Doesn't Exist");
            } else {

                String Name = resultSet.getString(1);
                System.out.println("Exist Login successfully -> " + Name);

                user = new User();
                user.setName(resultSet.getString(1));
                user.setPhoneNum(resultSet.getString(2));
                user.setGender(resultSet.getString(3));
                user.setCountry(resultSet.getString(4));
                user.setDateOfBirth(resultSet.getString(5));
                //user.setPicture(resultSet.getBytes(6));
                user.setPassword(resultSet.getString(7));
                user.setStatus(resultSet.getString(8));
                user.setChatBotStatus(resultSet.getInt(9));
                user.setEmail(resultSet.getString(10));
                user.setBIO(resultSet.getString(11));
                user.setMode(resultSet.getString(12));
                user.setEntryTimes(resultSet.getInt(13));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return user;
        }
    }
}
