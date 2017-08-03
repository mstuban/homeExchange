package hr.mstuban.homeexchange.validator;


import hr.mstuban.homeexchange.domain.form.NewMessageForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.codehaus.plexus.util.StringUtils.isBlank;

/**
 * Created by mstuban on 03/08/17.
 */
@Component
public class NewMessageFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NewMessageForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewMessageForm newMessageForm = (NewMessageForm) target;

        validateTitle(newMessageForm.getTitle(), errors);
        validateContent(newMessageForm.getContent(), errors);
        validateReceiver(newMessageForm.getTitle(), errors);
    }

    private void validateReceiver(String receiver, Errors errors) {

        if(isBlank(receiver)){
            errors.rejectValue("receiver", "message.receiver.empty", "You must select a receiver.");
        }

    }

    private void validateContent(String content, Errors errors) {

        if(isBlank(content)){
            errors.rejectValue("content", "message.content.empty", "You must enter some content.");
        }

        if(content.length() > 200){
            errors.rejectValue("content", "message.content.too-long", "Your content can only have 200 characters.");

        }
    }

    private void validateTitle(String title, Errors errors) {

        if(isBlank(title)){
            errors.rejectValue("title", "message.title.empty", "You must enter a title.");
        }

        if(title.length() > 30){
            errors.rejectValue("title", "message.title.too-long", "Your title can only have 30 characters.");
        }

    }



}
