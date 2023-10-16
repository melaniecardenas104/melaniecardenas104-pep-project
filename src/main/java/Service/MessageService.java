package Service;

import java.util.List;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {

    public MessageDAO messageDAO;
    public MessageService() {
        messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }
//create new message
    public Message createMessage(Message message) {
        return messageDAO.createMessage(message);
    }
//get all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
//get messages by id
    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }
//delete messages by id
    public boolean deleteMessagesById(String messageId) {
        return messageDAO.deleteMessageById(messageId);
    }
    public Message updateMessageById(int id, Message message) {
        return messageDAO.updateMessageByInt(id, message);
    }
    public List<Message> getAllMessagesById(int id) {
        return messageDAO.getAllMessagesById(id);
    }
    public boolean deleteMessagesById(int messageId) {
        return false;
    }

}
    
