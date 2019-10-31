package liars.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgentsNameGenerator {
    static List<String> names = new ArrayList<>();
    static Random generator = new Random();


    static  {
        names.add("Malchiel");
        names.add("Amon");
        names.add("Ramiah");
        names.add("Mary");
        names.add("Reuben");
        names.add("Rueben");
        names.add("Jehoram");
        names.add("Levi");
        names.add("Boaz");
        names.add("Lucius");
        names.add("Jesus");
        names.add("Moses");
        names.add("Noah");
        names.add("Peter");
        names.add("Paul");
        names.add("Simon");
        names.add("Saul");
        names.add("Judas");
        names.add("Tobiah");
        names.add("Asher");
        names.add("Reuel");
        names.add("Abraham");
        names.add("Moshe");
        names.add("Allon");
        names.add("Thomas");
        names.add("Job");
        names.add("Dathan");
        names.add("Ramah");
        names.add("Mehida");
        names.add("Ahinoam");
        names.add("Jeriel");
        names.add("John the Baptiste");
        names.add("Ava");
        names.add("Theodora");
        names.add("Cornelia");
        names.add("Lucina");
        names.add("Magdalene");
        names.add("God");
        names.add("Abida");
        names.add("Eden");
        names.add("Esther");
        names.add("Japhlet");
        names.add("Holy Spirit");
        names.add("Devil");
        names.add("Eve");
        names.add("Adam");
        names.add("Pope Francis");
        names.add("Eden");

    }

    public static String getName(){
        if (names.isEmpty()) {
            return "the devil";
        }
        int randomIndex = generator.nextInt(names.size());
        String name = names.get(randomIndex);
        names.remove(randomIndex);

        return name;
    }


}
