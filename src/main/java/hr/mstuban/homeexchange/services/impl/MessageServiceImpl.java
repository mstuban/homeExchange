package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Message;
import hr.mstuban.homeexchange.repositories.MessageRepository;
import hr.mstuban.homeexchange.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mstuban on 03/08/17.
 */
@Component
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public void store(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesByReceiverUsername(String username) {
        return messageRepository.getMessagesByReceiver_UserNameOrderByCreatedOnDesc(username);
    }

    @Override
    public Integer countByReceiverUsernameUnread(String username, boolean hasBeenRead) {
        return messageRepository.countByReceiver_UserName_AndHasBeenRead(username, hasBeenRead);
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.getById(id);
    }

    @Transactional
    @Override
    public void updateIsMessageRead(boolean read, Long messageId) {
        messageRepository.updateIsMessageRead(read, messageId);
    }

    @Override
    public void delete(Long id) {
        messageRepository.delete(id);
    }


}
