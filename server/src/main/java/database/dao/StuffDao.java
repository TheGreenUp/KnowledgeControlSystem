package database.dao;

import database.ConnectionManager;
import entity.Stuff;
import exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import response.ErrorResponse;
import response.Response;
import response.stuffManagment.UpdateStuffResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public final class StuffDao {

    private static final StuffDao stuffDao = new StuffDao();
    private final ConnectionManager connectionManager;

    public static StuffDao getInstance() {
        return stuffDao;
    }

    private StuffDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    //region SQL команды
    public static final String GET_BY_EMAIL_AND_PASSWORD = "SELECT * FROM stuff WHERE email = ? AND password = ?";
    public static final String GET_ALL = "SELECT * FROM stuff";
    public static final String GET_BY_ID = "SELECT * FROM stuff WHERE id = ?";
    public static final String UPDATE_BY_ID =
            "UPDATE stuff SET email = ?, name = ?,surname = ?, password = ?, role = ? WHERE id = ?";
    public static final String ADD_STUFF = "INSERT INTO stuff(name, surname, email, password, role) VALUES(?,?,?,?,?)";
    public static final String DELETE_STUFF = "DELETE FROM stuff WHERE id = ? AND email = ?";



    //endregion

    public Stuff getByEmailAndPassword(String email, String password) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Stuff(resultSet.getInt("id"),
                        resultSet.getInt("role"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            } else {
                throw new UserNotFoundException("Failed to find user by email " + email + " and password " + password);
            }
        }
    }

    public List<Stuff> getAll() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            List<Stuff> stuffList = new ArrayList<>();

            while (resultSet.next()) {
                Stuff stuff = Stuff.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getInt("role"))
                        .build();
                stuffList.add(stuff);
            }
            return stuffList;
        }
    }

    public void addStuff(Stuff stuff) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_STUFF);
            statement.setString(1, stuff.getName());
            statement.setString(2, stuff.getSurname());
            statement.setString(3, stuff.getEmail());
            statement.setString(4, stuff.getPassword());
            statement.setInt(5, stuff.getRole());
            statement.executeUpdate();
        }
    }

    public Stuff getById(Integer id) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Stuff.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getInt("role"))
                        .build();
            } else {
                throw new UserNotFoundException("Failed to find user by id " + id);
            }
        }
    }
    public void updateById(Stuff stuff) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            //"UPDATE stuff SET email = ?, name = ?,surname = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, stuff.getEmail());
            statement.setString(2, stuff.getName());
            statement.setString(3, stuff.getSurname());
            statement.setString(4, stuff.getPassword());
            statement.setInt(5, stuff.getRole());
            statement.setInt(6, stuff.getId());
            statement.executeUpdate();
        }
    }

    public Response updateStuff(Stuff stuff) throws SQLException {
        Stuff updatedUser = stuff;
        Integer id = updatedUser.getId();
        log.info("Update user with values {}", updatedUser);
        try {
            Stuff existing = stuffDao.getById(id);
            existing.setEmail(updatedUser.getEmail());
            existing.setName(updatedUser.getName());
            existing.setSurname(updatedUser.getSurname());
            existing.setPassword(updatedUser.getPassword());
            existing.setRole(updatedUser.getRole());
            stuffDao.updateById(existing);
            return new UpdateStuffResponse();
        } catch (UserNotFoundException e) {
            log.error("User not found by id {}", id);
            return new ErrorResponse("Пользователь с id " + id + " не найден ");
        }
    }

    public void deleteStuff(Stuff stuff) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_STUFF);
            statement.setInt(1, stuff.getId());
            statement.setString(2, stuff.getEmail());
            statement.executeUpdate();
        }
    }
}

