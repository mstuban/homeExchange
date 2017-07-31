package hr.mstuban.homeexchange.domain.mapper;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.form.NewHomeForm;
import org.mapstruct.Mapper;

/**
 * Created by mstuban on 31/07/17.
 */
@Mapper(componentModel = "spring")
public interface HomeMapper {

    Home formToHome(NewHomeForm form);
    NewHomeForm homeToForm(Home home);

}

