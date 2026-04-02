package gymsystem;

import org.junit.jupiter.api.Test;
// @author Ethan Chiu and Abdulah Nasir

import static org.junit.jupiter.api.Assertions.*;

public class MembershipTest {

    @Test
    public void monthlyMembership_returnsCorrectTypeAndDuration() {
        MonthlyMembership membership = new MonthlyMembership("2026-03-01", "2026-04-01");

        assertEquals("Monthly", membership.getMembershipType());
        assertEquals(1, membership.getDurationMonths());
        assertEquals("2026-03-01", membership.getStartDate());
        assertEquals("2026-04-01", membership.getEndDate());
    }

    @Test
    public void quarterlyMembership_returnsCorrectTypeAndDuration() {
        QuarterlyMembership membership = new QuarterlyMembership("2026-03-01", "2026-06-01");

        assertEquals("Quarterly", membership.getMembershipType());
        assertEquals(3, membership.getDurationMonths());
        assertEquals("2026-03-01", membership.getStartDate());
        assertEquals("2026-06-01", membership.getEndDate());
    }

    @Test
    public void annualMembership_returnsCorrectTypeAndDuration() {
        AnnualMembership membership = new AnnualMembership("2026-03-01", "2027-03-01");

        assertEquals("Annually", membership.getMembershipType());
        assertEquals(12, membership.getDurationMonths());
        assertEquals("2026-03-01", membership.getStartDate());
        assertEquals("2027-03-01", membership.getEndDate());
    }

    @Test
    public void membership_setters_updateDates() {
        MonthlyMembership membership = new MonthlyMembership("2026-03-01", "2026-04-01");

        membership.setStartDate("2026-05-01");
        membership.setEndDate("2026-06-01");

        assertEquals("2026-05-01", membership.getStartDate());
        assertEquals("2026-06-01", membership.getEndDate());
    }

    @Test
    public void membership_toString_containsImportantFields() {
        QuarterlyMembership membership = new QuarterlyMembership("2026-03-01", "2026-06-01");

        String text = membership.toString();

        assertTrue(text.contains("Quarterly"));
        assertTrue(text.contains("2026-03-01"));
        assertTrue(text.contains("2026-06-01"));
        assertTrue(text.contains("3"));
    }
}

