import java.text.SimpleDateFormat;
import java.util.Date;

public class Window {
	boolean state;	//창문 상태(1:열림, 0:닫힘)
	String date;
	
	public Window(boolean state) {
		super();
		this.state = state;
		this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public void setState(boolean state) {
		this.state = state;
		
	}
	public boolean isState() {
		return state;
	}
	public String getDate() {
		return date;
	}
}
