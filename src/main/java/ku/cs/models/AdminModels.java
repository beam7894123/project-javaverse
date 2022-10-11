package ku.cs.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdminModels extends RegisterModel {

//    private String ban;
    private final String takeDateTime = getDate() + " " + getTime();
    Locale locale = new Locale("en","en"); //SET LOCALE (if u sys is พศ. it will auto set to คศ. yay~ \^w^/ )
    //(ps. this wont do the "M A G I C" if u CSV is พศ. sooo... not fixed? 'm')
    //(ps2. so ok i know what to do now, just let SignIn page use this locale thing too! fixed! No more pollution data! 'w'b)
    // https://nahmkahw.wordpress.com/2010/07/15/%E0%B8%9B%E0%B8%B1%E0%B8%8D%E0%B8%AB%E0%B8%B2%E0%B9%80%E0%B8%81%E0%B8%B5%E0%B9%88%E0%B8%A2%E0%B8%A7%E0%B8%81%E0%B8%B1%E0%B8%9A-date-%E0%B8%A3%E0%B8%B0%E0%B8%AB%E0%B8%A7%E0%B9%88%E0%B8%B2%E0%B8%87-java/
    // ^^ useful when time are f up 'w'b
    SimpleDateFormat timeFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
    private final Date defaultdateTime; {try {defaultdateTime = timeFormat1.parse("01-01-2000 00:00:00");} catch (ParseException e) {throw new RuntimeException(e);}}
    private Date dateTime; // String --> Date
    {
        try {
            dateTime = timeFormat1.parse(takeDateTime);
        } catch (ParseException e) {
            System.err.println("\n!!TIME CONVERTER(v.2) ERROR!!");
            System.err.println("Time, Dr. Freeman?");
            System.err.println("Look like user date " + "\"" + getName()+ " " + getSurname() + "\"" + " is mess up -w-");
            System.err.println("Here input is: " + takeDateTime + "");
//          takeDateTime = "00-00-0000 00:00:00";
            dateTime = defaultdateTime;
            System.out.println("Continue running...\n");
//          dateTime = new GregorianCalendar(2000, 2, 1).getTime();
//          throw new RuntimeException(e);
        }
    }
    private final String stringDateTime = timeFormat1.format(dateTime); //Date --> String
    public String getStringDateTime() {
        return stringDateTime;
    }

    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

//    public AdminModels(String name, String surname, String username, String password, String confirmPassword, String date, String time, String fileNameImage) {
//        super(name, surname, username, password, confirmPassword, date, time, fileNameImage);
//    }
    public AdminModels(String name, String surname, String username, String password, String date, String time) {
        super(name, surname, username, password, date, time);
    }

//    public AdminModels(String name, String surname, String username, String password, String confirmPassword, String image, String date, String time, Date datetime) {
//        super(name, surname, username, password, confirmPassword, date, time, image);
//        this.dateTime = datetime;
//    }


    @Override
    public String toString() {
        return "AdminModels{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", image='" + getImage() + '\'' +
                ", date='" + getDate() + '\'' +
                ", time='" + getTime() + '\'' +
                ", takeDateTime='" + takeDateTime + '\'' +
                ", locale=" + locale +
                ", dateTime=" + dateTime +
                ", stringDateTime='" + stringDateTime + '\'' +
                '}';
    }
}
