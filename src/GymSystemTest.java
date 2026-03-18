import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GymSystemTest.java
 * Unit tests for GymSystem methods.
 */
class GymSystemTest {

    /**
     * Instance of GymSystem used for testing.
     */
    private GymSystem gymSystem;

    /**
     * Initializes a new GymSystem before each test.
     * Ensures tests run with a fresh and independent instance.
     */
    @BeforeEach
    void setUp() {
        gymSystem = new GymSystem();
    }

    /**
     * Tests that a member can be added and retrieved by memberId.
     * Verifies that the member is stored correctly and total count updates.
     */
    @Test
    void addMemberAndFindMemberById() {
        // Arrange
        String id = gymSystem.generateMemberId();
        Member member = new Member(id, "Brandon", "brandon@email.com",
                "Calgary", "Monthly");

        // Act
        gymSystem.addMember(member);
        Member found = gymSystem.findMemberById(id);

        // Assert
        assertNotNull(found);
        assertEquals("Brandon", found.getFullName());
        assertEquals(1, gymSystem.getTotalMembers());
    }

    @Test
    void updateMemberInfoChangesStoredValues() {
    }

    @Test
    void recordCheckInIncreasesVisits() {
    }

    @Test
    void recordPaymentAddsToTotalPaid() {
    }

    @Test
    void setMemberActiveChangesStatus() {
    }

    @Test
    void getAllMembersReturnsAllStoredMembers() {
    }
}
