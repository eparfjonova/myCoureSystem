package domain;

//Basisklasse für alle folgenden Entities (Video 5)
public abstract class BaseEntity {

    //keine Instanzen von dieser Klasse können direkt erstellt werden, aber wir können Funktionen und Variablen implementeiren die in anderen Klassen verwendet werden

    private Long id;

    public BaseEntity(Long id) {
        setId(id);
    }

    public void setId(Long id) {
        if (id == null || id >= 0) {
            this.id = id;
        }else {
            throw new InvalidValueException("Kurs-ID muss größer gleich 0 sein");
        }
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
