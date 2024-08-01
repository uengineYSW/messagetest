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
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class CreateItemCodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        CreateItemCodeTest.class
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
    public ItemCodeRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        ItemCode existingEntity = new ItemCode();

        existingEntity.setId(null);
        existingEntity.setItemCode(null);
        existingEntity.setCodeNo(null);
        existingEntity.setCode(null);
        existingEntity.setCodeName(null);
        existingEntity.setIsSys(null);
        existingEntity.setIsUse(null);
        existingEntity.setEtc(null);
        existingEntity.setEtc1(null);
        existingEntity.setEtc2(null);
        existingEntity.setEtc3(null);
        existingEntity.setEtc4(null);
        existingEntity.setEtc5(null);

        repository.save(existingEntity);

        //when:

        try {
            ItemCode newEntity = new ItemCode();

            newEntity.setId(0L);
            newEntity.setItemCode("M001");
            newEntity.setCodeNo("154");
            newEntity.setCode("");
            newEntity.setCodeName("test0710원소재재질");
            newEntity.setIsSys("N");
            newEntity.setIsUse("Y");
            newEntity.setEtc("");
            newEntity.setEtc1(null);
            newEntity.setEtc2(null);
            newEntity.setEtc3(null);
            newEntity.setEtc4(null);
            newEntity.setEtc5(null);

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

            ItemCodeCreated outputEvent = objectMapper.readValue(
                receivedPayload,
                ItemCodeCreated.class
            );

            LOGGER.info("Response received: {}", outputEvent);

            assertEquals(outputEvent.getId().longValue(), 0L);
            assertEquals(outputEvent.getItemCode(), "M001");
            assertEquals(outputEvent.getCodeNo(), "154");
            assertEquals(outputEvent.getCode(), "");
            assertEquals(outputEvent.getCodeName(), "test0710원소재재질");
            assertEquals(outputEvent.getIsSys(), "N");
            assertEquals(outputEvent.getIsUse(), "Y");
            assertEquals(outputEvent.getEtc(), "");
            assertEquals(outputEvent.getEtc1(), null);
            assertEquals(outputEvent.getEtc2(), null);
            assertEquals(outputEvent.getEtc3(), null);
            assertEquals(outputEvent.getEtc4(), null);
            assertEquals(outputEvent.getEtc5(), null);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            assertTrue("exception", false);
        }
    }
}
