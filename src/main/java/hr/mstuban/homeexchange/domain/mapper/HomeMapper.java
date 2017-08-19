package hr.mstuban.homeexchange.domain.mapper;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.form.EditHomeForm;
import hr.mstuban.homeexchange.domain.form.NewHomeForm;
import org.mapstruct.Mapper;

/**
 * Created by mstuban on 31/07/17.
 */
@Mapper(componentModel = "spring")
public interface HomeMapper {

    Home newHomeFormToHome(NewHomeForm form);
    NewHomeForm homeToNewHomeForm(Home home);

    Home editHomeFormToHome(EditHomeForm form);
    EditHomeForm homeToEditHomeForm(Home home);

}

