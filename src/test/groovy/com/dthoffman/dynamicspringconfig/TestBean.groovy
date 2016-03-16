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

}
