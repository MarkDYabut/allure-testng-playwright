package com.example.testng;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

public class StepTest {

    private static final String GLOBAL_PARAMETER = "global value set as a static variable";
    ThreadLocal<StepService> stepServiceThreadLocal = new ThreadLocal<>();
    ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();

    @Test
    public void annotatedStepTest() {
        System.out.println("StepTest.annotatedStepTest");
        annotatedStep("local value");
        Page page = browserContextThreadLocal.get().newPage();
        page.navigate("https://www.google.com/");
    }
    @Test
    public void lambdaStepTest() {
        System.out.println("StepTest.lambdaStepTest");

        Page page = browserContextThreadLocal.get().newPage();
        page.navigate("https://www.youtube.com/");

        final String localParameter = "parameter value";
        Allure.step(String.format("Parent lambda step with parameter [%s]", localParameter), (step) -> {
            step.parameter("parameter", localParameter);
            Allure.step(String.format("Nested lambda step with global parameter [%s]", GLOBAL_PARAMETER));
        });
    }

    @BeforeMethod
    public void beforeMethod() {
        long id = 1;
        stepServiceThreadLocal.set(new StepService());
        browserContextThreadLocal.set(stepServiceThreadLocal.get().getBrowser().newContext());
        Allure.step(String.format("Parent lambda step with parameter [%s]", id), (step) -> {
            step.parameter("parameter", id);
            step.parameter("hardcoded", "hardcoded parameter1");
            Allure.step(String.format("Nested lambda step with global parameter [%s]", GLOBAL_PARAMETER));
        });
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("StepTest.afterMethod");
        Page page = browserContextThreadLocal.get().pages().get(0);
        Allure.addAttachment("addAttachmentName", "image/png", new ByteArrayInputStream(
                page.screenshot()
        ), "png");
        stepServiceThreadLocal.get().getPlaywright().close();
    }

    @Test
    public void zeroTest() {
        Page page = browserContextThreadLocal.get().newPage();
        page.navigate("https://www.youtube.com/");
        Allure.addAttachment("addAttachmentName", "image/png", new ByteArrayInputStream(
                page.screenshot()
        ), "png");
        System.out.println("StepTest.zeroTest");
        System.out.println("No Allure Annotations");
    }
    @Test
    public void firstTest () {
        Page page = browserContextThreadLocal.get().newPage();
        page.navigate("https://www.youtube.com/");
        System.out.println("StepTest.firstTest");
        final String localParameter = "parameter value";
        Allure.step(String.format("Parent lambda step with parameter [%s]", localParameter), (step) -> {
            step.parameter("parameter", localParameter);
            step.parameter("hardcoded", "hardcoded parameter1");
            Allure.step(String.format("Nested lambda step with global parameter [%s]", GLOBAL_PARAMETER));
            Allure.addAttachment("addAttachmentName", "image/png", new ByteArrayInputStream(
                    page.screenshot()
            ), "png");
        });
        Allure.step(String.format("Parent lambda step with parameter [%s]", localParameter), (step) -> {
            step.parameter("parameter", localParameter);
            step.parameter("hardcoded", "hardcoded parameter1");
            Allure.step(String.format("Nested lambda step with global parameter [%s]", GLOBAL_PARAMETER));
            Allure.addAttachment("addAttachmentName", "image/png", new ByteArrayInputStream(
                    page.screenshot()
            ), "png");
        });
        Allure.step(String.format("Parent lambda step with parameter [%s]", localParameter), (step) -> {
            step.parameter("parameter", localParameter);
            step.parameter("hardcoded", "hardcoded parameter1");
            Allure.step(String.format("Nested lambda step with global parameter [%s]", GLOBAL_PARAMETER));
            Allure.addAttachment("addAttachmentName", "image/png", new ByteArrayInputStream(
                    page.screenshot()
            ), "png");
        });
    }

    @Step("Parent annotated step with parameter [{parameter}]")
    public void annotatedStep(final String parameter) {
        System.out.println("StepTest.annotatedStep");
        nestedAnnotatedStep();
    }

    @Step("Nested annotated step with global parameter [{this.GLOBAL_PARAMETER}]")
    public void nestedAnnotatedStep() {
        System.out.println("StepTest.nestedAnnotatedStep");
    }

}
