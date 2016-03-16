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
    @Ignore('in progress')
    def "bean gets updated on config change event"() {
        when:
        propertyEventBus.publishChange('test.string', 'value-from-event')
        propertyEventBus.publishChange('test.integer', '2')

        then:
        testBean.testStringValue == 'value-from-event'
        testBean.testIntegerValue == 2
    }



}
