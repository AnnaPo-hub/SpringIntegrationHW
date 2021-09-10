package ru.otus.SpringIntegrationProject;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.SpringIntegrationProject.domain.Person;
import ru.otus.SpringIntegrationProject.integration.BeautySalon;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@IntegrationComponentScan
@ComponentScan
@EnableIntegration
public class IntegrationFlowTests {

    @Autowired
    private BeautySalon beautySalon;

    @Test
    public void test() {
        Person tanya = beautySalon.process(new Person("Tanya", true, true, 0));
        Assertions.assertThat(tanya.isNotManicured()).isFalse();
        Assertions.assertThat(tanya.isWithoutMakeUp()).isFalse();
        Assert.assertEquals(200, tanya.getClientDebt());
    }
}
