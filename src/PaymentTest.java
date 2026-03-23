import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void constructor_setsFieldsCorrectly() {
        Payment payment = new Payment(25.5, "2026-03-23");

        assertEquals(25.5, payment.getAmount(), 0.0001);
        assertEquals("2026-03-23", payment.getPaymentDate());
    }

    @Test
    public void setters_updateFieldsCorrectly() {
        Payment payment = new Payment(10.0, "2026-03-20");

        payment.setAmount(99.99);
        payment.setPaymentDate("2026-03-25");

        assertEquals(99.99, payment.getAmount(), 0.0001);
        assertEquals("2026-03-25", payment.getPaymentDate());
    }

    @Test
    public void toString_containsAmountAndDate() {
        Payment payment = new Payment(15.0, "2026-03-23");

        String text = payment.toString();

        assertTrue(text.contains("15.0") || text.contains("15.00"));
        assertTrue(text.contains("2026-03-23"));
    }
}
