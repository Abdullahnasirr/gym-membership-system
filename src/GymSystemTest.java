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

    /**
     * Tests that updateMemberInfo correctly updates a member's details.
     * Verifies that name, contact, and address are changed.
     */
    @Test
    void updateMemberInfoChangesStoredValues() {
        // Arrange
        String id = gymSystem.generateMemberId();
        Member member = new Member(id, "Old Name", "old@email.com",
                "Old Address", "Monthly");
        gymSystem.addMember(member);

        // Act
        boolean updated = gymSystem.updateMemberInfo(id,
                "New Name", "new@email.com", "New Address");
        Member updatedMember = gymSystem.findMemberById(id);

        // Assert
        assertTrue(updated);
        assertEquals("New Name", updatedMember.getFullName());
        assertEquals("new@email.com", updatedMember.getPhoneOrEmail());
        assertEquals("New Address", updatedMember.getAddress());
    }

    /**
     * Tests that recordCheckIn increases the visit count for a member.
     * Verifies that multiple check-ins are recorded correctly.
     */
    @Test
    void recordCheckInIncreasesVisits() {
        // Arrange
        String id = gymSystem.generateMemberId();
        Member member = new Member(id, "Brandon", "Brandon@email.com",
                "Calgary", "Quarterly");
        gymSystem.addMember(member);

        // Act
        boolean firstCheckIn = gymSystem.recordCheckIn(id);
        boolean secondCheckIn = gymSystem.recordCheckIn(id);
        int visits = gymSystem.findMemberById(id).getTotalVisits();

        // Assert
        assertTrue(firstCheckIn);
        assertTrue(secondCheckIn);
        assertEquals(2, visits);
    }

    /**
     * Tests that recordPayment correctly adds to a member's total paid.
     * Verifies that multiple payments are accumulated properly.
     */
    @Test
    void recordPaymentAddsToTotalPaid() {
        // Arrange
        String id = gymSystem.generateMemberId();
        Member member = new Member(id, "Brandon", "Brandon@email.com",
                "Calgary", "Annually");
        gymSystem.addMember(member);

        // Act
        boolean firstPayment = gymSystem.recordPayment(id, 50.0);
        boolean secondPayment = gymSystem.recordPayment(id, 25.5);
        double totalPaid = gymSystem.findMemberById(id).getTotalPaid();

        // Assert
        assertTrue(firstPayment);
        assertTrue(secondPayment);
        assertEquals(75.5, totalPaid, 0.0001);
    }

    /**
     * Tests that setMemberActive updates the member's active status.
     * Verifies that the status changes correctly.
     */
    @Test
    void setMemberActiveChangesStatus() {
        // Arrange
        String id = gymSystem.generateMemberId();
        Member member = new Member(id, "Brandon", "Brandon@email.com",
                "Calgary", "Monthly");
        gymSystem.addMember(member);

        // Act
        boolean result = gymSystem.setMemberActive(id, false);
        boolean isActive = gymSystem.findMemberById(id).isActive();

        // Assert
        assertTrue(result);
        assertFalse(isActive);
    }

    /**
     * Tests that getAllMembers returns all added members.
     * Verifies that the size of the collection matches the number of members added.
     */
    @Test
    void getAllMembersReturnsAllStoredMembers() {
        // Arrange
        String id1 = gymSystem.generateMemberId();
        String id2 = gymSystem.generateMemberId();

        gymSystem.addMember(new Member(id1, "One", "one@email.com", "A", "Monthly"));
        gymSystem.addMember(new Member(id2, "Two", "two@email.com", "B", "Quarterly"));

        // Act
        int size = gymSystem.getAllMembers().size();

        // Assert
        assertEquals(2, size);
    }
}
