import edu.udacity.java.nano.WebSocketChatApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebSocketChatApplication.class)
public class SeleniumChatRoomTest {

    @LocalServerPort
    private int port;

    private SeleniumConfig config;

    @Test
    public void testChatApplication() throws InterruptedException {
        config = new SeleniumConfig();
        WebDriver driver = config.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost:" + port);
        String title = config.getWebDriver().getTitle();
        Assert.assertEquals("Chat Room Login", title);
        //TEST LOGIN
        driver.findElement(By.id("username")).sendKeys("CP");
        driver.findElement(By.linkText("Login")).click();
        Thread.sleep(2000);
        Assert.assertEquals("1", driver.findElement(By.className("chat-num")).getText());
        //TEST CHAT
        driver.findElement(By.id("msg")).sendKeys("Hello!!!");
        driver.findElement(By.id("sendMessage")).click();
        Thread.sleep(2000);
        Assert.assertEquals("CP:Hello!!!", driver.findElement(By.className("message-content")).getText());

        //TEST LEAVE
        driver.findElement(By.partialLinkText("exit_to_app")).click();
        Assert.assertEquals("Chat Room Login", driver.getTitle());
        driver.close();
    }
}
