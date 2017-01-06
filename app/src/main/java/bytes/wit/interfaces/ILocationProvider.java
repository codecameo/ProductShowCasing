package bytes.wit.interfaces;

/**
 * Created by Md. Sifat-Ul Haque on 1/5/2017.
 */

public interface ILocationProvider {

    void getAllShowroom();

    void getShowRoom(String showroomId);

    void getFilteredShowRoom(String query);
}
