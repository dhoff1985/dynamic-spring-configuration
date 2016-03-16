package com.dthoffman.dynamicspringconfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by dhoffman on 3/15/16.
 */
@ContextConfiguration(classes = [TestSpringConfig])
class SpringConfigIntegrationSpec extends Specification {

    @Autowired
    TestBean testBean

    @Autowired
    PropertyEventBus propertyEventBus

    def "spring wires config like normal" () {
        expect:
        testBean.testStringValue == "value-from-file"
        testBean.testIntegerValue == 1
    }

    @DirtiesContext
    def "bean gets updated on config change event"() {
        when:
        propertyEventBus.publishChange('test.string', 'value-from-event')
        propertyEventBus.publishChange('test.integer', '5')
        propertyEventBus.publishChange('test.int', '5')
        propertyEventBus.publishChange('test.long', '5')
        propertyEventBus.publishChange('test.primitive.long', '5')
        propertyEventBus.publishChange('test.double', '.5')
        propertyEventBus.publishChange('test.primitive.double', '.5')
        propertyEventBus.publishChange('test.float', '.5')
        propertyEventBus.publishChange('test.primitive.float', '.5')
        propertyEventBus.publishChange('test.short', '0')
        propertyEventBus.publishChange('test.primitive.short', '0')

        then:
        testBean.testStringValue == 'value-from-event'
        testBean.testIntegerValue == 5
        testBean.testIntValue == 5
        testBean.testLong == 5l
        testBean.testPrimitiveLong == 5l
        testBean.testDouble - 0.5 < 0.001
        testBean.testPrimitiveDouble - 0.5 < 0.001
        testBean.testFloat - 0.5 < 0.001
        testBean.testPrimitiveFloat - 0.5 < 0.001
        ((int)testBean.testShort) == 0
        ((int)testBean.testPrimitiveShort) == 0
    }



}
