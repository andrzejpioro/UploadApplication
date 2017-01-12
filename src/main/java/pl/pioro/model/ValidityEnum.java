package pl.pioro.model;

public enum ValidityEnum {

	ONE_MIN("1m"),
	ONE_HOUR("1h"),
	ONE_DAY("1d"),
	SEVEN_DAYS("7d"),
	THIRTY_DAYS("30d"),
	UNLIMITED("0")
	;
	
	ValidityEnum(String kod){
		this.kod=kod;
	}
	
	private String kod;
	
	public static ValidityEnum fromValue(String kod){
		if(kod.equals("1m"))
			return ValidityEnum.ONE_MIN;
		if(kod.equals("1h"))
			return ValidityEnum.ONE_HOUR; 
		if(kod.equals("1d"))
			return ValidityEnum.ONE_DAY; 
		if(kod.equals("7d"))
			return ValidityEnum.SEVEN_DAYS; 
		if(kod.equals("30d"))
			return ValidityEnum.THIRTY_DAYS; 
		if(kod.equals("0"))
			return ValidityEnum.UNLIMITED; 

		throw new IllegalArgumentException("Nieprawidlowy symbol atrybuty: ["+kod+"], dozwolony atrybuty to: 1h, 1d, 7d, 30d, 0");
	}
	
}
