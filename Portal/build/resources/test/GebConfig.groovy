import org.openqa.selenium.chrome.ChromeDriver

baseUrl = 'http://localhost:8080/'
waiting {
    timeout = 2
}

environments {

    chrome {
        driver = { new ChromeDriver()}
    }
}