package domain;

import java.sql.Date;

public class Course extends BaseEntity{


    private String name;
    private String description;
    private int hours;
    private Date beginDate;
    private Date endDate;
    private CourseType courseType;


    //Superklassenkonstruktor mit null in id ausführen
    //mit throws InvalideValue Exception am ende da Setter verwendet werden
    public Course(String name, String description, int hours, Date beginDate, Date endDate, CourseType courseType) throws InvalidValueException{
        super(null);
        this.setName(name);
        this.setDescription(description);
        this.setHours(hours);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setCourseType(courseType);
    }

    //alle Parameter eingegeben
    //dasselbe mit throws ...
    public Course(Long id, String name, String description, int hours, Date beginDate, Date endDate, CourseType courseType) throws InvalidValueException{
        super(id);
        this.setName(name);
        this.setDescription(description);
        this.setHours(hours);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setCourseType(courseType);
    }




    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidValueException{
        if (name != null && name.length() > 2){
            this.name = name;
        }else {
            throw new InvalidValueException("Der Name muss mindestens 2 Zeichen enthalten");
        }
    }



    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours > 0 && hours < 10){
            this.hours = hours;
        }else{
            throw new InvalidValueException("Anzahl der Kursstunden pro Kurs darf nur zwischen 1 und 10 liegen");
        }
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidValueException{
        if (description != null && description.length()>10) {
            this.description = description;
        }else {
            throw new InvalidValueException("Die Kursbeschreibung muss mindestens 10 Zeichen lang sein");
        }

    }



    public Date getBeginDate() {
        return beginDate;
    }

    //Das datum muss vor dem Enddatum sein (Businesslogik)
    public void setBeginDate(Date beginDate) throws InvalidValueException{
        if (beginDate != null) {
            if (this.endDate != null){
                if (beginDate.before(this.endDate)){
                    this.beginDate = beginDate;
                }else{
                    throw new InvalidValueException("Kursbeginn muss vor Kursende sein");
                }
            } else {
                this.beginDate = beginDate;
            }
        } else {
            throw new InvalidValueException("Startdatum darf nicht null/leer sein");
        }
    }



    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) throws InvalidValueException{
        if (endDate != null) {
            if (this.beginDate != null){
                if (endDate.after(this.beginDate)){
                    this.endDate = beginDate;
                }else{
                    throw new InvalidValueException("Kursende muss nach Kursbeginn sein");
                }
            } else {
                this.endDate = beginDate;
            }
        } else {
            throw new InvalidValueException("Enddatum darf nicht null/leer sein");
        }
    }



    public CourseType getCourseType() throws InvalidValueException{
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        if (courseType != null) {
            this.courseType = courseType;
        }else{
            throw new InvalidValueException ("Kurstyp darf nicht null/leer sein");
        }
    }



    @Override
    public String toString() {
        return "Course{" +
                "id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hours=" + hours +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", courseType=" + courseType +
                '}';
    }
}
