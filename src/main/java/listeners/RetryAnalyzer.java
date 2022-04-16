package listeners;

import constants.FrameworkConstants;
import enums.ConfigProperties;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.PropertiesFileImpl;

public final class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    private final int maxAttempt = FrameworkConstants.getRetryCounts();

    @Override
    public boolean retry(ITestResult result) {
        if ((PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.RETRYFAILEDTEST).equalsIgnoreCase("yes"))
                && (retryCount < maxAttempt)) {
            retryCount++;

            return true;
        }
        return false;
    }

}
