package at.hakimst.dataaccess;


import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Optional;

//Video 6
//das ist ein Interface
//es gibt Standartcrutmethoden vor
//Es müssen 2 Typ Informationen angegeben
public interface BaseReposetory<T,I> {

    //alle Klassen die vom BaseRepository erben geben zwei Informationen mit (T;I)

    //jede DAO Implemetierung/Klasse die von BaseEntity erbt muss eine insert methode haben
    Optional <T> insert(T entity);
    Optional <T> getByID(I id);
    //T sind Platzhalter und | I ist ein Referenztyp wie Long

    List<T> getAll();

    Optional <T> update(T entity);

    void deleatById(I id);

    //Wer jetzt ein Interface implementiert muss alle oben gendannten Dinge implementieren

    //ich brauche Mehrere Repositorys für z.B. Kurs, KursTyp, KursTeilnemer
}
