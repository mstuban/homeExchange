package hr.mstuban.homeexchange.domain.mapper;

import hr.mstuban.homeexchange.domain.Message;
import hr.mstuban.homeexchange.domain.form.NewMessageForm;
import org.mapstruct.Mapper;

/**
 * Created by mstuban on 03/08/17.
 */
@Mapper(componentModel = "spring", uses = UserReceiverMapper.class)
public interface MessageMapper {

    Message newMessageFormToMessage(NewMessageForm form);
    NewMessageForm messageToNewMessageForm(Message message);

}
