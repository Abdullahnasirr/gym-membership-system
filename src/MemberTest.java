import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MemberTest.java
 *
 * CPSC 219 W26
 * Demo 2 - Gym Membership System
 *
 * Name: Wai Yan Aung
 * Date: 18 March 2026
 * Tutorial: T05
 *
 * JUnit tests for the Member class.
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
        Membership membership = new MonthlyMembership("2026-03-19", "2026-04-19");

        // Act
        Member member = new Member(memberId, fullName, phoneOrEmail, address, membership);

        // Assert
        assertEquals(memberId, member.getMemberId());
        assertEquals(fullName, member.getFullName());
        assertEquals(phoneOrEmail, member.getPhoneOrEmail());
        assertEquals(address, member.getAddress());
    }

    /**
     * Tests that calling recordCheckIn increases the total visit count.
     * Verifies that multiple check-ins correctly update the visit total.
     */
    @Test
    void testRecordCheckInIncreasesVisitCount() {
        // Arrange
        Member member = new Member("M1000", "Brandon", "Brandon@email.com",
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));

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
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));

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
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));
        Member nameChanged = new Member("M1000", "Brandon Aung", "Brandon@email.com",
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));

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
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));
        Member nameChanged = new Member("M1000", "Brandon Aung", "Brandon@email.com",
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));

        // Act
        int hash1 = noNameChange.hashCode();
        int hash2 = nameChanged.hashCode();

        // Assert
        assertEquals(hash1, hash2);
    }

    /**
     * Tests that compareTo orders members by memberId.
     * Verifies less than, greater than, and equal comparisons.
     */
    @Test
    void testCompareToOrdersMembersByMemberId() {
        // Arrange
        Member Brandon1 = new Member("M1001", "Brandon1", "Brandon1@email.com",
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));
        Member Brandon2 = new Member("M1002", "Brandon2", "Brandon2@email.com",
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));

        // Act
        int firstComparedToSecond = Brandon1.compareTo(Brandon2);
        int secondComparedToFirst = Brandon2.compareTo(Brandon1);
        int firstComparedToSameId = Brandon1.compareTo(Brandon1);

        // Assert
        assertTrue(firstComparedToSecond < 0);
        assertTrue(secondComparedToFirst > 0);
        assertEquals(0, firstComparedToSameId);
    }

    /**
     * Tests that setActive correctly updates the member's active status.
     * Verifies that the status changes from active to inactive.
     */
    @Test
    void testSetActiveChangesMemberStatus() {
        // Arrange
        Member member = new Member("M1001", "Brandon1", "Brandon1@email.com",
                "Calgary", new MonthlyMembership("2026-03-19", "2026-04-19"));

        // Act
        member.setActive(false);

        // Assert
        assertFalse(member.isActive());
    }
}