package interfaces;

import entity.Hobby;
import exception.HobbyNotFoundException;
import java.util.List;

public interface IHobbyFacade {
    public Hobby addHobby(Hobby h);

    public Hobby deleteHobby(int id);

    public Hobby getHobby(int id) throws HobbyNotFoundException;

    public List<Hobby> getHobbies();

    public Hobby editHobby(Hobby p);
}
