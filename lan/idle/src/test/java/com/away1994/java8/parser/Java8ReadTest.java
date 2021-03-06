package com.away1994.java8.parser;

import com.away1994.lang.parser.Parser;
import com.away1994.lang.parser.impl.java.JavaLanguageParser;
import com.away1994.lang.symbols.impl.PathImpl;
import com.away1994.constants.TestConstants;
import org.junit.Before;

import java.util.logging.Logger;

import static com.away1994.common.constants.CommonConstants.LOGGING_PROPERTIES_PATH;
import static com.away1994.common.utils.log.LogUtils.readLoggerConfigurationFromResourceFromClassClassLoader;
import static com.away1994.constants.TestConstants.TEST_RESOURCES_PATH;

public class Java8ReadTest {
    private static final transient Logger LOGGER = Logger.getLogger(Java8ReadTest.class.getName());

    @Before
    public void parseFiles() throws Exception {
        readLoggerConfigurationFromResourceFromClassClassLoader(LOGGING_PROPERTIES_PATH,
                Java8ReadTest.class);

        Parser parser = new Parser(new PathImpl(TestConstants.IDLE_LAN_PROJECT_PATH),
                new JavaLanguageParser());
        parser.setOutputPath(TEST_RESOURCES_PATH + "out/symbols");
        parser.runParseStateMachine();
    }
}
