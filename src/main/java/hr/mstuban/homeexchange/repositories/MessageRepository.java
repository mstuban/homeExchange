package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by mstuban on 03/08/17.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getMessagesByReceiver_UserNameOrderByCreatedOnDesc(String username);

    Integer countByReceiver_UserName_AndHasBeenRead(String username, boolean hasBeenRead);

    Message getById(Long id);

    @Modifying
    @Query("update Message m set m.hasBeenRead = ?1 where m.id = ?2")
    void updateIsMessageRead(boolean read, Long messageId);

    void deleteById(Long id);

}
