import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MemberTest.java, JUnit tests for the Member class.
 */
class MemberTest {

    /**
     * Tests that the constructor correctly sets all member fields.
     * Verifies that each getter returns the expected value.
     */
    @Test
    void testConstructorStoresInitialValuesCorrectly() {
        // Arrange
        String memberId = "M1000";
        String fullName = "Brandon";
        String phoneOrEmail = "brandon@gmail.com";
        String address = "Calgary";
        String membershipType = "Monthly";

        // Act
        Member member = new Member(memberId, fullName, phoneOrEmail, address, membershipType);

        // Assert
        assertEquals(memberId, member.getMemberId());
        assertEquals(fullName, member.getFullName());
        assertEquals(phoneOrEmail, member.getPhoneOrEmail());
        assertEquals(address, member.getAddress());
        assertEquals(membershipType, member.getMembershipType());
    }

    /**
     * Tests that calling recordCheckIn increases the total visit count.
     * Verifies that multiple check-ins correctly update the visit total.
     */
    @Test
    void testRecordCheckInIncreasesVisitCount() {
        // Arrange
        Member member = new Member("M1000", "Brandon", "Brandon@email.com",
                "Calgary", "Quarterly");

        // Act
        member.recordCheckIn();
        member.recordCheckIn();

        // Assert
        assertEquals(2, member.getTotalVisits());
    }

    /**
     * Tests that recordPayment correctly adds to the total amount paid.
     * Verifies that multiple payments are accumulated properly.
     */
    @Test
    void testRecordPaymentAddsToTotalPaid() {
        // Arrange
        Member member = new Member("M1000", "Brandon", "Brandon@email.com",
                "Calgary", "Annually");

        // Act
        member.recordPayment(25.0);
        member.recordPayment(15.5);

        // Assert
        assertEquals(40.5, member.getTotalPaid(), 0.0001);
    }

    /**
     * Tests that equals returns true for members with the same memberId.
     * Verifies that memberId is used to determine equality.
     */
    @Test
    void testEqualsReturnsTrueForSameMemberId() {
        // Arrange
        Member noNameChange = new Member("M1000", "Brandon", "Brandon@email.com",
                "Calgary", "Annually");
        Member nameChanged = new Member("M1000", "Brandon Aung", "Brandon@email.com",
                "Calgary", "Annually");

        // Act
        boolean result = noNameChange.equals(nameChanged);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests that hashCode is the same for members with the same memberId.
     * Verifies consistency with equals method.
     */
    @Test
    void testHashCodeMatchesForEqualMembers() {
        // Arrange
        Member noNameChange = new Member("M1000", "Brandon", "Brandon@email.com",
                "Calgary", "Annually");
        Member nameChanged = new Member("M1000", "Brandon Aung", "Brandon@email.com",
                "Calgary", "Annually");

        // Act
        int hash1 = noNameChange.hashCode();
        int hash2 = nameChanged.hashCode();

        // Assert
        assertEquals(hash1, hash2);
    }

    @Test
    void testCompareToOrdersMembersByMemberId() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void testSetActiveChangesMemberStatus() {
        // Arrange

        // Act

        // Assert
    }
}