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
        Member member = new Member("M1001", "Brandon2", "Brandon2@email.com",
                "Calgary", "Quarterly");

        // Act
        member.recordCheckIn();
        member.recordCheckIn();

        // Assert
        assertEquals(2, member.getTotalVisits());
    }

    @Test
    void testRecordPaymentAddsToTotalPaid() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void testEqualsReturnsTrueForSameMemberId() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void testHashCodeMatchesForEqualMembers() {
        // Arrange

        // Act

        // Assert
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