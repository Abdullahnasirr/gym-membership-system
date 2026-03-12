import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DataTest {
    // To Clear the data after each test
    @BeforeEach
    void setUp() {
        Data.membersById.clear();
        Data.memberIdList.clear();
    }

    // ====================== AddMember() Test Case =======================
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

    // ====================== UpdateMemberInfo Test Case =======================
    @Test
    void updateMemberInfo() {
        //Arrange
        int id = Data.addMember("Brandon", Data.TYPE_MONTHLY);

        //Act
        boolean updated = Data.updateMemberInfo(id, "Brandon Aung", Data.TYPE_ANNUALLY);
        Object[] member = Data.getMemberById(id);

        //Assert
        assertTrue(updated);
        assertEquals("Brandon Aung", member[Data.INDEX_NAME]);
        assertEquals(Data.TYPE_ANNUALLY, member[Data.INDEX_TYPE]);
    }

    // ====================== recordCheckIn() Test Case =======================
    @Test
    void recordCheckIn() {
        //Arrange
        int id = Data.addMember("Brandon", Data.TYPE_QUARTERLY);

        //Act
        boolean firstCheckIn = Data.recordCheckIn(id);
        boolean secondCheckIn = Data.recordCheckIn(id);
        Object[] member = Data.getMemberById(id);

        //Assert
        assertTrue(firstCheckIn);
        assertTrue(secondCheckIn);
        assertEquals(2, member[Data.INDEX_VISITS]);
        assertFalse(Data.recordCheckIn(999999));
    }

    // ====================== recordPayment() Test Case =======================
    @Test
    void recordPayment() {
        //Arrange
        int id = Data.addMember("Brandon", Data.TYPE_MONTHLY);

        //Act
        boolean firstPayment = Data.recordPayment(id, 15.4);
        boolean secondPayment = Data.recordPayment(id, 35.4);
        Object[] member = Data.getMemberById(id);

        //Assert
        assertTrue(firstPayment);
        assertTrue(secondPayment);
        assertEquals(50.8, (Double) member[Data.INDEX_TOTAL_PAID], 0.0001);
        assertFalse(Data.recordPayment(999999, 10.10));
    }

    // ====================== setActiveStatus() Test Case =======================
    @Test
    void setActiveStatus() {
        //Arrange
        int id = Data.addMember("Brandon", Data.TYPE_ANNUALLY);

        //Act
        boolean changedStatus = Data.setActiveStatus(id, false);
        Object[] member = Data.getMemberById(id);

        //Assert
        assertTrue(changedStatus);
        assertEquals(false, member[Data.INDEX_ACTIVE]);
        assertFalse(Data.setActiveStatus(999999, true));
    }

    @Test
    void getAllMembers() {
    }

    @Test
    void getMemberById() {
    }
}