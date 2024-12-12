package domain;/*package domain;

import java.sql.Date;

public class Student extends BaseEntity{


    private String vn;
    private String nn;
    private Date geburtsdatum;


    //Superklassenkonstruktor mit null in id ausführen
    //mit throws InvalideValue Exception am ende da Setter verwendet werden


    public Student(Long id, String vn, String nn, Date geburtsdatum) {
        super(id);
        this.vn = vn;
        this.nn = nn;
        this.geburtsdatum = geburtsdatum;
    }

    public Student(String vn, String nn, Date geburtsdatum) {
        super(null);
        this.vn = vn;
        this.nn = nn;
        this.geburtsdatum = geburtsdatum;
    }


    public String getVn() {
        return vn;
    }

    public void setVn(String vn) throws InvalidValueException{
        if (vn != null && vn.length() > 2){
            this.vn = vn;
        }else {
            throw new InvalidValueException("Der Name muss mindestens 2 Zeichen enthalten");
        }
    }



    public String getNn() {
        return nn;
    }

    public void setNn(String nn) throws InvalidValueException{
        if (nn != null) {
            this.nn = nn;
        }else {
            throw new InvalidValueException("Der Nachname darf nicht Null sein");
        }

    }



    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    //Das datum muss vor dem Enddatum sein (Businesslogik)
    public void setGeburtsdatum(Date geburtsdatum) throws InvalidValueException{
        if (geburtsdatum != null) {
                    this.geburtsdatum = geburtsdatum;
        } else {
            throw new InvalidValueException("Geburtsdatum darf nicht null/leer sein");
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + super.getId() + '\'' +
                "vn='" + vn + '\'' +
                ", nn='" + nn + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                '}';
    }
}*/



import domain.BaseEntity;
import domain.InvalidValueException;

import java.sql.Date;

public class Student extends BaseEntity {
    private String vn;
    private String nn;
    private Date birthdate;

    public Student(Long id, String vn, String nn, Date birthdate) {
        super(id);
        this.vn = vn;
        this.nn = nn;
        this.birthdate = birthdate;
    }

    public Student(long id, String vn, String nn, Date birthdate, String email) {
        super(null);
        this.vn = vn;
        this.nn = nn;
        this.birthdate = birthdate;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        if (vn != null && vn.length() > 0) {
            this.vn = vn;
        } else {
            throw new InvalidValueException("Vorname darf nicht leer sein!");
        }
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        if (nn != null && nn.length() > 0) {
            this.nn = nn;
        } else {
            throw new InvalidValueException("Nachname darf nicht leer sein!");

        }
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() + // Ruft die ID aus BaseEntity ab
                ", vn='" + vn + '\'' +
                ", nn='" + nn + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
