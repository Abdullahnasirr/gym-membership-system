import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DataTest {
    @BeforeEach
    void setUp() {
        Data.membersById.clear();
        Data.memberIdList.clear();
    }

    @Test
    void addMember() {
        //Arrange
        String name = "Brandon";
        String type = Data.TYPE_MONTHLY;

        //Add
        int id = Data.addMember(name, type);
        Object[] member = Data.getMemberById(id);

        //Assert
        assertNotNull(member);
        assertEquals(1, Data.membersById.size());
        assertEquals(1, Data.memberIdList.size());
        assertTrue(Data.memberIdList.contains(id));
        assertEquals(id, member[Data.INDEX_ID]);
        assertEquals(name, member[Data.INDEX_NAME]);
        assertEquals(type, member[Data.INDEX_TYPE]);
        assertEquals(0, member[Data.INDEX_VISITS]);
        assertEquals(0.0, (Double) member[Data.INDEX_TOTAL_PAID], 0.0001);
        assertEquals(true, member[Data.INDEX_ACTIVE]);
    }

    @Test
    void updateMemberInfo() {
    }

    @Test
    void recordCheckIn() {
    }

    @Test
    void recordPayment() {
    }

    @Test
    void setActiveStatus() {
    }

    @Test
    void getAllMembers() {
    }

    @Test
    void getMemberById() {
    }
}