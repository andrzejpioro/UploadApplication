package pl.pioro.exceptions;

public class FileWithIdNotFoundException extends Exception{

	private String uuid;
	
	public FileWithIdNotFoundException(String msg, String uuid){
		super(msg);
		this.uuid=uuid;
	}
	
	public String getUuid() {
		return uuid;
	}
}
