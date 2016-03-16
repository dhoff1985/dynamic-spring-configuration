package com.dthoffman.dynamicspringconfig

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Created by dhoffman on 3/15/16.
 */
@Component
class TestBean {

    @Value('${test.string}')
    String testStringValue

    @Value('${test.integer}')
    Integer testIntegerValue

    @Value('${test.int}')
    int testIntValue

    @Value('${test.long}')
    Long testLong

    @Value('${test.primitive.long}')
    long testPrimitiveLong

    @Value('${test.double}')
    Double testDouble

    @Value('${test.primitive.double}')
    double testPrimitiveDouble

    @Value('${test.float}')
    Float testFloat

    @Value('${test.primitive.float}')
    float testPrimitiveFloat

    @Value('${test.short}')
    Short testShort

    @Value('${test.primitive.short}')
    short testPrimitiveShort

}
