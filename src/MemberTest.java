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

    @Test
    void testRecordCheckInIncreasesVisitCount() {
    }

    @Test
    void testRecordPaymentAddsToTotalPaid() {
    }

    @Test
    void testEqualsReturnsTrueForSameMemberId() {
    }

    @Test
    void testHashCodeMatchesForEqualMembers() {
    }

    @Test
    void testCompareToOrdersMembersByMemberId() {
    }

    @Test
    void testSetActiveChangesMemberStatus() {
    }
}