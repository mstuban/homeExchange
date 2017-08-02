package hr.mstuban.homeexchange.domain.mapper;

import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.form.EditUserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by mstuban on 01/08/17.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "userName")
    User formToUser(EditUserForm form);

    @Mapping(source = "userName", target = "username")
    EditUserForm userToForm(User user);

}

