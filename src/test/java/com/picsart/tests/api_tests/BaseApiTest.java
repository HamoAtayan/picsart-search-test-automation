package com.picsart.tests.api_tests;

import com.picsart.tests.BaseTest;

public class BaseApiTest extends BaseTest {
    protected String apiUrl = getConfig().get("apiUrl");
}
