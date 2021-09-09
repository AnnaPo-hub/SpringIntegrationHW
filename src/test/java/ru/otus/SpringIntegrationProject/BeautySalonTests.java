package ru.otus.SpringIntegrationProject;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.test.context.SpringIntegrationTest;
import ru.otus.SpringIntegrationProject.domain.Person;

@ComponentScan
@SpringIntegrationTest
public class BeautySalonTests {
    @Autowired
    private BeautySalon beautySalon;

    @Test
    public void test(){
         Person tanya = beautySalon.process(new Person("Tanya", true, true));
        Assertions.assertThat(tanya.isNotManicured()).isFalse();
        Assertions.assertThat(tanya.isWithoutMakeUp()).isFalse();
    }
}
