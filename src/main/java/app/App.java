package app;

import domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates"})
@Configuration
@EnableIntegration
@ComponentScan("cabinets")
@SpringBootApplication
public class App {
    private static final String[] MENU = {"makeUp", "manicure"};


//    @Bean
//    public QueueChannel clientComing(){
//        return MessageChannels.queue(10).get();
//    }
//
//    @Bean
//    public PublishSubscribeChannel clientOut(){
//        return MessageChannels.publishSubscribe().get();
//    }
//
//    @Bean(name = PollerMetadata.DEFAULT_POLLER)
//    public PollerMetadata poller (){
//        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
//    }

    @Bean
    DirectChannel clientOut() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow beautySalonFlow() {
        return flow -> flow
                .filter(person -> ((Person) person).isNotManicured())
                .gateway(manicureFlow())
                .filter(person -> ((Person) person).isWithoutMakeUp())
                .handle("makeUpService", "toDoMakeUp")
                .channel("clientOut");
    }

    @Bean
    public IntegrationFlow manicureFlow() {
        return flow -> flow
                .handle("manicureService", "makeManicure")
                .handle("manicureService", "varnishNails");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        ConfigurableApplicationContext ctx =
                SpringApplication.run(App.class, args);
        DirectChannel outputChannel = ctx.getBean("clientOut", DirectChannel.class);
        outputChannel.subscribe((x -> System.out.println(x)));

        ctx.getBean(BeautySalon.class)
                .process(new Person("Anna", true, true));
//        MessageChannel inputChannel =  ctx.getBean("clientComing.input",MessageChannel.class);
//        inputChannel.send(MessageBuilder.withPayload(new Person("Anna", false, false)).build());
        ctx.close();

        //AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( App.class );
        // ctx.registerShutdownHook();

//         BeautySalon beautySalon = ctx.getBean(BeautySalon.class);
//         beautySalon.process(new Person("Anna", false, false));


    }

}


//Ultimately, IntegrationFlows will always produce an instance
// of IntegrationFlow, which is the final product of any Spring Integration app.
//This pattern of taking input, performing the appropriate transformations, and
// emitting the results is fundamental to all Spring Integration apps.