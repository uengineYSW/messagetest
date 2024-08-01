package messagetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import messagetest.config.kafka.KafkaProcessor;
import messagetest.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class CreateConnectLogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        CreateConnectLogTest.class
    );

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MessageVerifier<Message<?>> messageVerifier;

    @Autowired
    public ConnectLogRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        ConnectLog existingEntity = new ConnectLog();

        existingEntity.setLogNo(null);
        existingEntity.setLoginDt(null);
        existingEntity.setLoginId(null);
        existingEntity.setLoginIdnm(null);
        existingEntity.setAccessIp(null);

        repository.save(existingEntity);

        //when:

        try {
            ConnectLog newEntity = new ConnectLog();

            newEntity.setLogNo(10655L);
            newEntity.setLoginDt(1720583340000L);
            newEntity.setLoginId("superadmin");
            newEntity.setLoginIdnm("관리자");
            newEntity.setAccessIp("192.168.139.202");

            repository.save(newEntity);

            //then:
            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(newEntity)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .build(),
                    "messagetest"
                );

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "messagetest",
                        5000,
                        TimeUnit.MILLISECONDS
                    );
            assertNotNull("Resulted event must be published", receivedMessage);

            String receivedPayload = (String) receivedMessage.getPayload();

            ConnectLogCreated outputEvent = objectMapper.readValue(
                receivedPayload,
                NewItemAdded.class
            );

            LOGGER.info("Response received: {}", outputEvent);

            assertEquals(outputEvent.getLogNo(), 10655L);
            assertEquals(outputEvent.getLoginDt(), 1720583340000L);
            assertEquals(outputEvent.getLoginId(), "superadmin");
            assertEquals(outputEvent.getLoginIdnm(), "관리자");
            assertEquals(outputEvent.getAccessIp(), "192.168.139.202");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }
}
