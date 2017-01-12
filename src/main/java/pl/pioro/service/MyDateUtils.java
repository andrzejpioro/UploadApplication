package pl.pioro.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyDateUtils {

	private static final String DATE_FORMAT_PATTERN="yyyy-MM-dd";
	private static final String TIME_FORMAT_PATTERN="yyyy-MM-dd HH:mm:ss";
	
	
	public static Date getDateFromString(String dataS){
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		Date data=null;
		try {
			data = df.parse(dataS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}


    public static String printDateTime(Date data) {
    	if(data!=null){
    		SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT_PATTERN);
            return df.format(data);	
    	}
        return "";
    }



    public static Date getTimeFromString(String dataS){
		SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		Date data=null;
		try {
			data = df.parse(dataS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String printTimeFromDate(Date date){
		SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		return df.format(date);
	}
	
	public static String printDate(Date date){
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return df.format(date);
	}
	
	public static String policzRozniceCzasuSlownie(Date czasTeraz, Date czasWydazenia){
//		log.info("");
//		log.info("Czas teraz:     ["+MyDateUtils.printTimeFromDate(czasTeraz)+"]");
//		log.info("Czas wydazenia: ["+MyDateUtils.printTimeFromDate(czasWydazenia)+"]");
		SimpleDateFormat dateFormatCzas = new SimpleDateFormat("HH:mm");
		long timeDiffSec = (czasTeraz.getTime() - czasWydazenia.getTime())/1000;
		int iloscSek = (int) ((czasTeraz.getTime() - czasWydazenia.getTime())/1000);
		int iloscMinut = (int) (iloscSek/60);
		int iloscGodzin = (int) (iloscMinut/60);
		int iloscDni = (int) (iloscGodzin/24);
		
//		log.info(String.format("Sec: [%s], Min: [%s], Godz: [%s], Dni: [%s]", iloscSek,iloscMinut,iloscGodzin,iloscDni));
		
//		log.info("Time delta: ["+timeDiffSec+"] sek");
		if(timeDiffSec < 60){
//			log.info("Mniej niz minute temu");
			return "mniej niż 1 min.";
		}
		if(iloscSek >= 60 && iloscSek < (60*59+59)){
			if(iloscMinut==1){
//				log.info(iloscMinut +" minutę temu");
				return "minutę temu";
			}else{
//				log.info(iloscMinut+ " minut temu");
				return iloscMinut+ " minut temu";
			}
		}
		if(iloscGodzin >= 1 && iloscGodzin < 24){
			String opis =  dateFormatCzas.format(czasWydazenia);
//			log.info(opis);
			return opis;
		}
		if(iloscDni >= 1){
			if(iloscDni==1){
//				log.info(iloscDni +" dzień temu");
				return "wczoraj";
			}else{
//				log.info(iloscDni+ " dni temu");
				return iloscDni+ " dni temu";
			}
		}
		return "";
	}
}
