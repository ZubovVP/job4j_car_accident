package ru.job4j.accident.repository;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Map;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Repository
@Scope("singleton")
@Data
@EqualsAndHashCode
public class AccidentMem {
    private Map<Integer, Accident> accidents;

    public Map<Integer, Accident> getAllAccidents() {
        fillAccidents();
        return this.accidents;
    }

    private void fillAccidents() {
        if (this.accidents == null) {
            Accident accident = new Accident();
            accident.setId(1);
            accident.setName("name");
            accident.setAddress("adress");
            accident.setText("test");
            this.accidents.put(accident.getId(), accident);

            Accident accident2 = new Accident();
            accident2.setId(2);
            accident2.setName("name2");
            accident2.setAddress("adress2");
            accident2.setText("test2");
            this.accidents.put(accident2.getId(), accident2);

            Accident accident3 = new Accident();
            accident3.setId(3);
            accident3.setName("name3");
            accident3.setAddress("adress3");
            accident3.setText("test3");
            this.accidents.put(accident3.getId(), accident3);
        }
    }
}
