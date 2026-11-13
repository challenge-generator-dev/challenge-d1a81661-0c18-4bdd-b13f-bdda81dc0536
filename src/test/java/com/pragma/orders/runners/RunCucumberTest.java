package com.pragma.orders.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.pragma.orders.steps", "com.pragma.orders.hooks"},
        plugin = {
                "pretty",
                "html:target/serenity-reports/cucumber-report.html",
                "json:target/serenity-reports/cucumber-report.json",
                "rerun:target/rerun.txt"
        },
        tags = "@integration",
        dryRun = false
)
public class RunCucumberTest {
}