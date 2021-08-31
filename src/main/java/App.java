import domain.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;

@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement"})
@ComponentScan
@Configuration
@EnableIntegration
public class App {
    private static final String [] MENU = {"makeUp", "manicure"};


    @Bean
    public QueueChannel clientComing(){
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel clientsOut(){
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller (){
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow beautySalonFlow(){
        return IntegrationFlows.from("clientComing")
                .filter(onlyNotManicured())
                .handle("makeManicure")
                .channel("clientsOut")
                .get();

    }

    @Bean
    public GenericSelector<Person> onlyNotManicured(){
        return  new GenericSelector<Person>() {
            public boolean accept(Person person) {
                return person.isManicured();
            }
        };
    }

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( App.class );
         BeautySalon beautySalon = ctx.getBean(BeautySalon.class);


    }

}


//Ultimately, IntegrationFlows will always produce an instance
// of IntegrationFlow, which is the final product of any Spring Integration app.
//This pattern of taking input, performing the appropriate transformations, and
// emitting the results is fundamental to all Spring Integration apps.