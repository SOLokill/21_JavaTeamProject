package frames;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Notices {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe"; // 드라이버 경로

	public static String[] Notices() {
		// 드라이버 설정
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();
		// 브라우저가 눈에 보이지 않고 내부적으로 돈다.
		// 설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
		options.addArguments("headless");

		// 위에서 설정한 옵션은 담은 드라이버 객체 생성
		// 옵션을 설정하지 않았을 때에는 생략 가능하다.
		// WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);

		// 이동을 원하는 url
		String url = "http://skhu.ac.kr/board/boardlist.aspx?bsid=10004&searchBun=51";

		// WebDriver을 해당 url로 이동한다.
		driver.get(url);

		// 브라우저 이동시 생기는 로드시간을 기다린다.
		// HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// List<WebElement> el1 = driver.findElements(By.className("depth2 submenu2"));

		// 1초 대기
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		WebElement el2 = driver.findElement(By.className("board_list"));

		// ctl00_ContentPlaceHolder1_ctl00_rptList2_ctl00_lblTitle
		List<WebElement> el3 = el2.findElements(By.className("left15"));

		String[] notice = new String[10];

		int count = 0;
		for (int i = 0; i < 10; i++) {
			notice[i] = el3.get(i).getText();
		}

		try {
			// 드라이버가 null이 아니라면
			if (driver != null) {
				// 드라이버 연결 종료
				driver.close(); // 드라이버 연결 해제

				// 프로세스 종료
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return notice;
	}

	public static void main(String[] args) {
		String arr[];
		arr = Notices();

		for (int i = 0; i < 10; i++) {
			System.out.println(arr[i]);
		}
	}
}