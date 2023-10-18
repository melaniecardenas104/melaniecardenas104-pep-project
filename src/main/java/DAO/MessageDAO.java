package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public Message createMessage(Message message){
    Connection connection = ConnectionUtil.getConnection();
    try {
        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1, message.getPosted_by());
        ps.setString(2, message.getMessage_text());
        ps.setLong(3, message.getTime_posted_epoch());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if(rs.next()) {
            int generated_message_id = (int) rs.getInt("message_id");
            return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}

    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch"));
                    messages.add(message);
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
//Retrieve a message by id
public Message getMessageById(int message_id) {
    Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "SELECT * FROM message WHERE message_id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, message_id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Message message = new Message(
                rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
            return message;
        }
    }catch(SQLException e) {
        System.out.println(e.getMessage());
    }
    return null;
}

public boolean deleteMessageById(String id) {
    Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "DELETE FROM message WHERE message_id = ?;";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(id));
        return ps.executeUpdate() > 0;
    } catch(SQLException e) {
        System.out.println(e.getMessage());
    }
    return false;
}

public Message updateMessageByInt(int message_id, Message message) {
    Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "UPDATE message SET posted_by=?, message_text=?, time_posted_epoch=? WHERE message_id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, message.getPosted_by());
        ps.setString(2, message.getMessage_text());
        ps.setLong(3, message.getTime_posted_epoch());
        ps.setInt(4, message_id);
        ps.executeUpdate();
    }catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return message;
}

public List<Message> getAllMessagesById(int message_id) {
    Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();
    try{
        String sql = "SELECT * FROM message INNER JOIN account ON message.posted_by = account.account_id WHERE account.account_id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, message_id);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Message message = new Message(
                rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
                messages.add(message);

        }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return messages;

    }
    
}




