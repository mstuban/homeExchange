package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Message;

import java.util.List;

/**
 * Created by mstuban on 03/08/17.
 */

public interface MessageService {

    void store(Message message);

    List<Message> getMessagesByReceiverUsername(String username);

    Integer countByReceiverUsernameUnread(String username, boolean hasBeenRead);

    Message findById(Long id);

    void updateIsMessageRead(boolean read, Long messageId);

    void delete(Long id);

}
