import domain.Person;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface BeautySalon {

    @Gateway(requestChannel = "clientComing", replyChannel = "beautifulClients")
    Person process(Person person);
}
