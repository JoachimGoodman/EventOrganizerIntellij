import javax.swing.*;

public interface MenuInterface {

    JPanel login();
    JPanel overview();
    JPanel arrangementInfo(int arrayIndex);
    JPanel modifyArrangement(int arrayIndex);
    JPanel createEvent(int arrangementIndex);
    JPanel updateEvent(int arrayIndex);
    JPanel eventInfo(int arrayIndex);
}
